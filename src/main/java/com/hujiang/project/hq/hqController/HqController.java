package com.hujiang.project.hq.hqController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.jianshishimingzhi.JiAnShiUtil;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjLogging.domain.HjLogging;
import com.hujiang.project.zhgd.hjLogging.service.IHjLoggingService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyAttendanceRecord.service.ILyAttendanceRecordService;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.service.ILyPersonnelService;
import com.hujiang.project.zhgd.utils.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/provider/Subscribe",method = RequestMethod.POST)
public class HqController {
    private Logger logger = LoggerFactory.getLogger(HqController.class);
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
    @Autowired
    private IHjLoggingService hjLoggingService;
    @Autowired
    private ILyPersonnelService lyPersonnelService;
    @Autowired
    private ILyAttendanceRecordService lyAttendanceRecordService;
    @RequestMapping("/Verify")
    public void zp(@RequestBody String json)throws Exception{
//        System.out.println(json);
        JSONObject str=JSONObject.parseObject(json);
        JSONObject content=JSONObject.parseObject(str.getString("content"));
        JSONArray logs=JSONArray.parseArray(content.getString("logs"));
        for(Object jo : logs){
            JSONObject log=JSONObject.parseObject(jo.toString());
//        JSONObject info=JSONObject.parseObject(s.getString("info"));
        String deviceId=log.getString("sn");
            System.out.println(deviceId);
            String userId=log.getString("user_id");
            System.out.println(userId);
            Pattern pattern = Pattern.compile("^[0-9]*$");
            if(pattern.matcher(userId).matches()){



        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setDeviceNo(deviceId);

        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
        //有此设备才进行操作
        if(hadList.size()>0){
            HjAttendanceDevice had2=hadList.get(0);
            String twoway=had2.getTwoway();
            String direction=null;
            if("0".equals(twoway)){
                direction=had2.getDirection();
            }else{
                direction=getDirection(log.getString("recog_time"));
            }
            //判断是智慧工地数据还是智慧楼宇(楼宇)
            if("zhgd".equals(had2.getSystemType())){
                HjProjectWorkers hpw=hjProjectWorkersService.selectHjProjectWorkersById(Integer.valueOf(userId));
                if(hpw!=null) {
                    getZhgd(log, hpw, had2,direction);
                }
            }else{
                LyPersonnel hpw=lyPersonnelService.selectLyPersonnelById(Integer.valueOf(userId));
                if(hpw!=null) {
                    getLy(log, hpw, had2,direction);
                }
            }




        }}}
    }

    /**
     * 智慧工地操作
     */
    private void getZhgd(JSONObject log, HjProjectWorkers hpw,HjAttendanceDevice had2,String direction)throws  Exception{
        List<HjSynchronizationInformation> list = this.queryHjSynchronizationInformation(had2.getProjectId());  // 项目密钥

        String passedTime=log.getString("recog_time");


        //考勤时间是30分钟之内的才上传
        Boolean flag=comparisonDate(passedTime);
        Boolean flag2=false;
        if("1".equals(hpw.getIsUpload())) {
            if ("1".equals(had2.getStatus())) {
                if (flag) {

                    for (int i = 0; i < list.size(); i++) {
                        flag2 = true;
                        JSONObject jsonObject = new JSONObject();
                        JSONArray jsonArray = new JSONArray();
                        JSONObject jsonData = new JSONObject();

                        jsonObject.put("Device_ID", Tools.encodeToMD5s(log.getString("sn")));
                        jsonData.put("person_type", "1");
                        jsonData.put("person_id", hpw.getIdCode());
                        jsonData.put("person_name", hpw.getEmpName());
                        jsonData.put("passed_time", passedTime);
                        jsonData.put("direction", direction);
                        jsonData.put("way", "1");

                        HjSynchronizationInformation h = list.get(i);
                        if (h.getPlatformName().equals("HOUS")) {//住建
                            jsonObject.put("Project_ID", h.getProjectNumber());
                            jsonData.put("site_photo", log.getString("photoes"));
                            jsonArray.add(jsonData);
                            jsonObject.put("passedlog_list", jsonArray);

                            String url = ZCAPIClientTwo.getUrl(h.getApiSecret(), h.getApiKey(), "1.1", h.getClientSerial(), jsonObject.toString(), Constants.HJ_FORMALHOST + "UploadPassedLog");
                            String result = ZCAPIClientTwo.httpPostWithJSON(url, jsonObject);


                        } else if (h.getPlatformName().equals("MUNICIPAL")) {//市政
                            jsonObject.put("Project_ID", h.getProjectNumber());
                            jsonData.put("site_photo", log.getString("photoes"));
                            jsonArray.add(jsonData);
                            jsonObject.put("passedlog_list", jsonArray);

                            String url = ZCAPIClientTwo.getUrl(h.getApiSecret(), h.getApiKey(), "1.1", h.getClientSerial(), jsonObject.toString(), Constants.ZHGD_FORMALHOST + "UploadPassedLog");
                            String result = ZCAPIClientTwo.httpPostWithJSON(url, jsonObject);

                        } else if (h.getPlatformName().equals("WORKSBUREAU")) {//工务署
//                        System.out.println("000000000000");
                            jsonObject.put("Project_ID", h.getEngineeringCode());
                            jsonData.put("data_id", h.getApiKey() + Tools.encodeToMD5s(hpw.getIdCode() + passedTime));
                            jsonData.put("site_photo", ZCgetImageId.base64ToimgId(h.getApiKey(), log.getString("photoes")));
                            jsonArray.add(jsonData);
                            jsonObject.put("passedlog_list", jsonArray);
                            String url = ZCAPIClientTwo.getUrl(h.getApiSecret(), h.getApiKey(), "1.0", h.getApiKey(), jsonObject.toString(), Constants.ZC_FORMALHOST + "UploadPassedLog");
                            String result = ZCAPIClientTwo.httpPostWithJSON(url, jsonObject);


                        } else if (h.getPlatformName().equals("DGHOUS")) {//东莞住建
                            jsonData.clear();
                            jsonData.put("RosterWokerId", hpw.getRosterWokerId());
                            jsonData.put("CheckDate", passedTime);
                            jsonData.put("DeviceNo", log.getString("sn"));
                            jsonData.put("Type", 6);
                            HjProject hjProject = hjProjectService.selectHjProjectById(hpw.getProjectId());
                            jsonData.put("ItemId", hjProject.getItemId());

                            jsonArray.add(jsonData);
                            jsonObject.put("Data", jsonArray);

                            String url = APIClient.getUrlDG(h.getApiKey(), jsonObject.toString(), Constants.DG_HOUS + "/UploadAttendance");
                            String result = APIClient.httpPostWithJSONDG(url, jsonObject);


                        }else if(h.getPlatformName().equals("JIANSHI")){
                            jsonData.clear();
                            String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                            String time2=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                            jsonData.put("keyId",h.getClientSerial());
                            jsonData.put("projectKeyId",h.getApiKey());
                            jsonData.put("projectCode",h.getProjectNumber());
                            jsonData.put("systemTime",time2);
                            jsonData.put("signature", JiAnShiUtil.getSignature(h.getEngineeringCode(),h.getProjectNumber(),time2,h.getApiSecret()));
                            jsonData.put("idCard",hpw.getIdCode());
                            jsonData.put("serialNo",had2.getDeviceNo());
                            jsonData.put("name",hpw.getEmpName());
                            jsonData.put("recordTime",time);
                            jsonData.put("inOrOut","in".equals(direction)?0:1);
                            jsonData.put("atteTime",passedTime);
                            jsonData.put("atteType",0);
//                                System.out.println(jsonData);
                            jsonData.put("photo",log.getString("photoes"));
                            String result= ZCAPIClientTwo.httpPostWithJSON(Constants.JIANSHI_SHIMINGZHI+"/attendance.rest/attendance",jsonData);
                            JSONObject s=JSONObject.parseObject(result);

                            if(!"0".equals(s.getString("code"))){
                                flag2=false;
                                HjLogging hl=new HjLogging();
                                hl.setProjectId(hpw.getProjectId());
                                hl.setLoggingMessage(s.getString("message"));
                                hl.setLoggingData(result);
                                hl.setInOut("向人脸机下发人脸失败！");
                                hl.setUserName(hpw.getEmpName());
                                hl.setLoggingTag("RecordDevice");
                                hl.setLoggingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                hjLoggingService.insertHjLogging(hl);

                            }


                        }

                    }
                }
            }
        }
//            System.out.println(s.getString("SanpPic").substring(s.getString("SanpPic").indexOf(",")+));
        HjAttendanceRecord har=new HjAttendanceRecord();
        if (flag) {
            MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(log.getString("photoes"));
            String FileImgurl = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", hpw.getIdCode() + System.currentTimeMillis() + ".jpg");
            String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
            har.setSitePhoto(name);
            har.setProjectId(had2.getProjectId());
            har.setEmployeeId(hpw.getId());
            har.setPassedTime(passedTime);
            har.setDirection(direction);
            har.setWay(1);
            har.setDeviceType("face");
            har.setDeviceSn(log.getString("sn"));
            har.setTemperature(log.getString("temperature"));
            if(flag2){
                har.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
            hjAttendanceRecordService.insertHjAttendanceRecord(har);
        }
    }

    /**
     * 智慧楼宇操作
     */
    private void getLy(JSONObject log, LyPersonnel hpw,HjAttendanceDevice had2,String direction)throws Exception{
        LyAttendanceRecord har=new LyAttendanceRecord();
        String passedTime=log.getString("recog_time");
            MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(log.getString("photoes"));
            String FileImgurl = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", hpw.getIdCode() + System.currentTimeMillis() + ".jpg");
            String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
            har.setSitePhoto(name);
            har.setProjectId(had2.getProjectId());
            har.setEmployeeId(hpw.getId());
            har.setPassedTime(passedTime);
            har.setDirection(direction);
            har.setWay(1);
            har.setDeviceType("face");
            har.setDeviceSn(log.getString("sn"));
            har.setTemperature(log.getString("temperature"));

                lyAttendanceRecordService.insertLyAttendanceRecord(har);


    }
    /**
     * 判断时间是否超过15分钟
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
private  String getDirection(String time)throws  Exception{

    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
    String str = new SimpleDateFormat("HH").format(date);
    System.out.println("++++++++++++++++++++"+str);
    int a=Integer.valueOf(str);
    if(a<14){
        return "in";
    }else {
        return "out";
    }
}

}
