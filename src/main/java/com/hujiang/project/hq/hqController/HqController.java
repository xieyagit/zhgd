package com.hujiang.project.hq.hqController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
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
    @RequestMapping("/Verify")
    public void zp(@RequestBody String json)throws Exception{

        JSONObject str=JSONObject.parseObject(json);
        JSONObject content=JSONObject.parseObject(str.getString("content"));
        JSONArray logs=JSONArray.parseArray(content.getString("logs"));
        for(Object jo : logs){
            JSONObject log=JSONObject.parseObject(jo.toString());
//        JSONObject info=JSONObject.parseObject(s.getString("info"));
        String deviceId=log.getString("sn");
            HjProjectWorkers hpw=hjProjectWorkersService.selectHjProjectWorkersById(log.getInteger("user_id"));

        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setDeviceNo(deviceId);
        had.setStatus("1");
        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
        //有此设备才进行操作
        if(hadList.size()>0){
            HjAttendanceDevice had2=hadList.get(0);


            List<HjSynchronizationInformation> list = this.queryHjSynchronizationInformation(had2.getProjectId());  // 项目密钥

            String passedTime=log.getString("recog_time");


            //考勤时间是30分钟之内的才上传
            Boolean flag=comparisonDate(passedTime);
            Boolean flag2=false;
            if(flag) {

                for (int i = 0; i < list.size(); i++) {
                    flag2=true;
                    JSONObject jsonObject = new JSONObject();
                    JSONArray jsonArray = new JSONArray();
                    JSONObject jsonData = new JSONObject();

                    jsonObject.put("Device_ID", Tools.encodeToMD5s(deviceId));
                    jsonData.put("person_type", "1");
                    jsonData.put("person_id", hpw.getIdCode());
                    jsonData.put("person_name", hpw.getEmpName());
                    jsonData.put("passed_time",passedTime);
                    jsonData.put("direction",had2.getDirection());
                    jsonData.put("way","1");

                    HjSynchronizationInformation h=list.get(i);
                    if(h.getPlatformName().equals("HOUS")){//住建
                        jsonObject.put("Project_ID",h.getProjectNumber());
                        jsonData.put("site_photo",log.getString("photoes"));
                        jsonArray.add(jsonData);
                        jsonObject.put("passedlog_list",jsonArray);

                        String url = ZCAPIClientTwo.getUrl(h.getApiSecret(),h.getApiKey(),"1.1",h.getClientSerial(),jsonObject.toString(), Constants.HJ_FORMALHOST + "UploadPassedLog");
                        String result = ZCAPIClientTwo.httpPostWithJSON(url,jsonObject);


                    }else if(h.getPlatformName().equals("MUNICIPAL")){//市政
                        jsonObject.put("Project_ID",h.getProjectNumber());
                        jsonData.put("site_photo",log.getString("photoes"));
                        jsonArray.add(jsonData);
                        jsonObject.put("passedlog_list",jsonArray);

                        String url = ZCAPIClientTwo.getUrl(h.getApiSecret(),h.getApiKey(),"1.1",h.getClientSerial(),jsonObject.toString(), Constants.ZHGD_FORMALHOST + "UploadPassedLog");
                        String result = ZCAPIClientTwo.httpPostWithJSON(url,jsonObject);

                    }else if(h.getPlatformName().equals("WORKSBUREAU")){//工务署
//                        System.out.println("000000000000");
                        jsonObject.put("Project_ID",h.getEngineeringCode());
                        jsonData.put("data_id",h.getApiKey()+Tools.encodeToMD5s(hpw.getIdCode()+passedTime));
                        jsonData.put("site_photo", ZCgetImageId.base64ToimgId(h.getApiKey(),log.getString("photoes")));
                        jsonArray.add(jsonData);
                        jsonObject.put("passedlog_list",jsonArray);
                        String url = ZCAPIClientTwo.getUrl(h.getApiSecret(),h.getApiKey(),"1.0",h.getApiKey(),jsonObject.toString(), Constants.ZC_FORMALHOST + "UploadPassedLog");
                        String result = ZCAPIClientTwo.httpPostWithJSON(url,jsonObject);


                    }

                }
            }
//            System.out.println(s.getString("SanpPic").substring(s.getString("SanpPic").indexOf(",")+));
            MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(log.getString("photoes"));
            String FileImgurl=AliyunOSSClientUtil.uploadFileImg(file, "hujiang", hpw.getIdCode()+ System.currentTimeMillis() + ".jpg");
            String name = FileImgurl.substring(0,FileImgurl.lastIndexOf("?"));
            HjAttendanceRecord har=new HjAttendanceRecord();
            har.setProjectId(had2.getProjectId());
            har.setEmployeeId(hpw.getId());
            har.setPassedTime(passedTime);
            har.setDirection(had2.getDirection());
            har.setWay(1);
            har.setSitePhoto(name);
            if(flag2){
                har.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
            hjAttendanceRecordService.insertHjAttendanceRecord(har);
        }
    }}

    /**
     * 判断时间是否超过30分钟
     * @param time
     * @return
     * @throws ParseException
     */
    public boolean comparisonDate(String time) throws ParseException {
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


}
