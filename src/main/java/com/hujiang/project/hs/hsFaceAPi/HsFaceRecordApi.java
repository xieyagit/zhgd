package com.hujiang.project.hs.hsFaceAPi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ha.sdk.HaCamera;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkersParam;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
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
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/provider/hsRecord")
public class HsFaceRecordApi {
    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjProjectService hjProjectService;
    /**
     * 接收艾达信人脸机考勤设备
     * @param json
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/setRecord")
    public void  setRecord(@RequestBody String json)throws Exception{
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"考勤记录 ："+json);
        JSONObject a=JSONObject.parseObject(json);
        String sn=a.getString("device_sn"); HjAttendanceDevice had=new HjAttendanceDevice();
        had.setDeviceNo(sn);

        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
        //有此设备才进行操作
        if(hadList.size()>0) {

            HjAttendanceDevice had2 = hadList.get(0);
            List<HjSynchronizationInformation> list = this.queryHjSynchronizationInformation(had2.getProjectId());  // 项目密钥
            String passedTime = a.getString("cap_time").replace("/", "-");
            passedTime = passedTime.substring(0, passedTime.lastIndexOf("."));
            JSONObject s = JSONObject.parseObject(a.getString("match"));
            //考勤时间是30分钟之内的才上传
            Boolean flag = comparisonDate(passedTime);
            if (s.getString("person_id") != null) {
                HjProjectWorkers hw = hjProjectWorkersService.selectHjProjectWorkersById(s.getInteger("person_id"));
                if (hw!=null) {
                    MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(JSONObject.parseObject(a.getString("closeup_pic")).getString("data"));
                    String FileImgurl = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", s.getString("person_id") + System.currentTimeMillis() + ".jpg");
                    String faceUrl = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
                    String base64 = PrintJobTo.zxToWatermark(faceUrl, passedTime);
                    Boolean flag2 = false;
                    if("1".equals(hw.getIsUpload())) {
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
                                    jsonData.put("person_name", s.getString("person_name"));
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
                                        jsonData.put("data_id", h.getApiKey() + Tools.encodeToMD5s(hw.getIdCode() + a.getString("cap_time")));
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
                    }
                    HjAttendanceRecord har = new HjAttendanceRecord();
                    har.setProjectId(had2.getProjectId());
                    har.setEmployeeId(s.getInteger("person_id"));
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
        }
    }
    /**
     * 获取秘钥列表
     * @param projectId
     * @return
     */
    public List<HjSynchronizationInformation> queryHjSynchronizationInformation(Integer projectId){
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
    public boolean comparisonDate(String time) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
