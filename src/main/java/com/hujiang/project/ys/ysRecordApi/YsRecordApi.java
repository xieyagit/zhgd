package com.hujiang.project.ys.ysRecordApi;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.common.utils.RSAUtil;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjDeviceHikvision.domain.HjDeviceHikvision;
import com.hujiang.project.zhgd.hjDeviceHikvision.service.IHjDeviceHikvisionService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/provider/ys")
public class YsRecordApi {
    private static final String publicKey="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKKummBg8P8LfiqtVAxauxA40Bm_TdZZYGPZxUTN3rXpSPAnhBGdSkllsVnjdAm5yr1iOPvotDV4xRTkcocWfhsCAwEAAQ";
    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    @Autowired
    private IHjDeviceHikvisionService hjDeviceHikvisionService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjProjectService hjProjectService;
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;
    @PostMapping(value = "/setRecord")
    public String setRecordYs(@RequestBody String json) throws  Exception{
        try {


            //json=json.replace("\"","");
            String publicString= RSAUtil.decryt(json,publicKey,0);
//        publicString=publicString.replace("\\","");
            System.out.println(publicString);
            Map a=JsonUtils.parse(publicString, HashMap.class);
            Map b = (Map)a.get("eps");
            List alert = (List)b.get("alert");
            Map c=(Map) alert.get(0);
            JSONObject body=JSONObject.parseObject(c.get("body").toString());


//            JSONObject a= JSONUtils.parse(publicString,JSONObject.class);
//            JSONObject b=a.getJSONObject("eps");
//        JSONArray  alert=b.getJSONArray("alert");
//        Object o=alert.get(0);
//        JSONObject body=JSONObject.parseObject(o.toString()).getJSONObject("body");
        String sn=body.getString("deviceSerial");
        if(!"".equals(body.getString("employeeNoString"))&&body.getString("employeeNoString")!=null) {
            HjAttendanceDevice had = new HjAttendanceDevice();
            had.setDeviceNo(sn);

            List<HjAttendanceDevice> hadList = hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
            //有此设备才进行操作
            if (hadList.size() > 0) {

                HjAttendanceDevice had2 = hadList.get(0);
                List<HjSynchronizationInformation> list = this.queryHjSynchronizationInformation(had2.getProjectId());  // 项目密钥
                String passedTime = body.getString("createTime");
                //考勤时间是30分钟之内的才上传
                Boolean flag = comparisonDate(passedTime);
                HjProjectWorkers hw = this.getHjProjectWorkers(body.getInteger("employeeNoString"), had2.getProjectId());
                String faceUrl = body.getString("pictureUrl");
                String base64 = PrintJobTo.zxToWatermark(faceUrl, passedTime);
                Boolean flag2 = false;
                if ("1".equals(had2.getStatus())) {
                    if (flag) {
                        for (int i = 0; i < list.size(); i++) {
                            flag2 = true;
                            JSONObject jsonObject = new JSONObject();
                            JSONArray jsonArray = new JSONArray();
                            JSONObject jsonData = new JSONObject();

                            jsonObject.put("Device_ID", Tools.encodeToMD5s(sn));
                            jsonData.put("person_type", "1");
                            jsonData.put("person_id", hw.getIdCode());
                            jsonData.put("person_name", hw.getEmpName());
                            jsonData.put("passed_time", passedTime);
                            jsonData.put("direction", had2.getDirection());
                            jsonData.put("way", "1");
                            HjSynchronizationInformation h = list.get(i);
                            if (h.getPlatformName().equals("HOUS")) {//住建
                                jsonObject.put("Project_ID", h.getProjectNumber());
                                jsonData.put("site_photo", base64);
                                jsonArray.add(jsonData);
                                jsonObject.put("passedlog_list", jsonArray);

                                String url = ZCAPIClientTwo.getUrl(h.getApiSecret(), h.getApiKey(), "1.1", h.getClientSerial(), jsonObject.toString(), Constants.HJ_FORMALHOST + "UploadPassedLog");
                                String result = ZCAPIClientTwo.httpPostWithJSON(url, jsonObject);


                            } else if (h.getPlatformName().equals("MUNICIPAL")) {//市政
                                jsonObject.put("Project_ID", h.getProjectNumber());
                                jsonData.put("site_photo", base64);
                                jsonArray.add(jsonData);
                                jsonObject.put("passedlog_list", jsonArray);

                                String url = ZCAPIClientTwo.getUrl(h.getApiSecret(), h.getApiKey(), "1.1", h.getClientSerial(), jsonObject.toString(), Constants.ZHGD_FORMALHOST + "UploadPassedLog");
                                String result = ZCAPIClientTwo.httpPostWithJSON(url, jsonObject);

                            } else if (h.getPlatformName().equals("WORKSBUREAU")) {//工务署
//                        System.out.println("000000000000");
                                jsonObject.put("Project_ID", h.getEngineeringCode());
                                jsonData.put("data_id", h.getApiKey() + Tools.encodeToMD5s(hw.getIdCode() + new Date().getTime()));
                                jsonData.put("site_photo", ZCgetImageId.base64ToimgId(h.getApiKey(), base64));
                                jsonArray.add(jsonData);
                                jsonObject.put("passedlog_list", jsonArray);
                                String url = ZCAPIClientTwo.getUrl(h.getApiSecret(), h.getApiKey(), "1.0", h.getApiKey(), jsonObject.toString(), Constants.ZC_FORMALHOST + "UploadPassedLog");
                                String result = ZCAPIClientTwo.httpPostWithJSON(url, jsonObject);


                            } else if (h.getPlatformName().equals("DGHOUS")) {//东莞住建
                                jsonData.clear();
                                jsonData.put("RosterWokerId", hw.getRosterWokerId());
                                jsonData.put("CheckDate", passedTime);
                                jsonData.put("DeviceNo", sn);
                                jsonData.put("Type", 6);
                                HjProject hjProject = hjProjectService.selectHjProjectById(hw.getProjectId());
                                jsonData.put("ItemId", hjProject.getItemId());

                                jsonArray.add(jsonData);
                                jsonObject.put("Data", jsonArray);

                                String url = APIClient.getUrlDG(h.getApiKey(), jsonObject.toString(), Constants.DG_HOUS + "/UploadAttendance");
                                String result = APIClient.httpPostWithJSONDG(url, jsonObject);


                            }
                        }

                    }
                }
                HjAttendanceRecord har = new HjAttendanceRecord();
                har.setProjectId(had2.getProjectId());
                har.setEmployeeId(hw.getId());
                har.setPassedTime(passedTime);
                har.setDirection(had2.getDirection());
                har.setWay(1);
                har.setSitePhoto(faceUrl);
                if (flag2) {
                    har.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                }
                hjAttendanceRecordService.insertHjAttendanceRecord(har);


            }
        }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JSONObject response=new JSONObject();
            response.put("code","200");
            response.put("msg","成功");
            return response.toString();
        }
    }

    /**
     * 获取人员信息
     * @param id
     * @param pid
     * @return
     */
    private  HjProjectWorkers getHjProjectWorkers(Integer id,Integer pid){
        HjDeviceHikvision hik=hjDeviceHikvisionService.selectHjDeviceHikvisionById(id);
        HjProjectWorkers hpw=new HjProjectWorkers();
        hpw.setProjectId(pid);
        hpw.setIdCode(hik.getIdCard());
        return hjProjectWorkersService.selectHjProjectWorkersList(hpw).get(0);
    }
    /**
     * 获取秘钥列表
     * @param projectId
     * @return
     */
    private List<HjSynchronizationInformation> queryHjSynchronizationInformation(Integer projectId){
        HjSynchronizationInformation hjSynchronizationInformation = new HjSynchronizationInformation();
        hjSynchronizationInformation.setProjectId(projectId);
        hjSynchronizationInformation.setApiType("keytype1");
        hjSynchronizationInformation.setState(1);
        List<HjSynchronizationInformation> list = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hjSynchronizationInformation);
        return list;
    }
    /**
     * 判断时间是否超过30分钟
     * @param time
     * @return
     * @throws ParseException
     */
    private boolean comparisonDate(String time) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date kqTime=sdf.parse(time);//考勤时间
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -15);//
        Date dqTime=sdf.parse(sdf.format(beforeTime.getTime()));//当前时间
        if(dqTime.before(kqTime)){
            return true;
        } else{
            return false;
        }

    }
}
