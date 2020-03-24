package com.hujiang.project.yushi.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.support.Convert;
import com.hujiang.project.client.SystemClient;
import com.hujiang.project.jianshishimingzhi.JiAnShiUtil;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkersParam;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
import com.hujiang.project.zhgd.hjDeviceTemperature.domain.HjDeviceTemperature;
import com.hujiang.project.zhgd.hjDeviceTemperature.service.IHjDeviceTemperatureService;
import com.hujiang.project.zhgd.hjLogging.domain.HjLogging;
import com.hujiang.project.zhgd.hjLogging.service.IHjLoggingService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;
import com.hujiang.project.zhgd.hjSystemUser.service.IHjSystemUserService;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyAttendanceRecord.service.ILyAttendanceRecordService;
import com.hujiang.project.zhgd.lyDevicePersonnel.domain.LyDevicePersonnel;
import com.hujiang.project.zhgd.lyDevicePersonnel.service.ILyDevicePersonnelService;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.service.ILyPersonnelService;
import com.hujiang.project.zhgd.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/lapis")
public class Lapi {
@Autowired
private IHjProjectWorkersService hjProjectWorkersService;
@Autowired
private IHjAttendanceDeviceService hjAttendanceDeviceService;
@Autowired
private IHjDeviceProjectworkersService hjDeviceProjectworkersService;
@Autowired
private ILyDevicePersonnelService lyDevicePersonnelService;
@Autowired
private ILyPersonnelService lyPersonnelService;
@Autowired
private IHjLoggingService hjLoggingService;
@Autowired
private IHjProjectService hjProjectService;
@Autowired
private ILyAttendanceRecordService lyAttendanceRecordService;
@Autowired
private IHjAttendanceRecordService hjAttendanceRecordService;
@Autowired
private IHjSynchronizationInformationService hjSynchronizationInformationService;
@Autowired
private SystemClient client;
    @PostMapping(value = "/heartReportInfo")
    public void  heartReportInfo(String sn)throws Exception {
        HjAttendanceDevice hds = new HjAttendanceDevice();
        hds.setDeviceNo(sn);
        List<HjAttendanceDevice> hdsList = hjAttendanceDeviceService.selectHjAttendanceDeviceList(hds);
        if (hdsList.size() > 0) {
            HjAttendanceDevice hds2 = hdsList.get(0);
            String systemType = hds2.getSystemType();
            if ("zhgd".equals(systemType)) {
                 getZhgd(sn);
            } else {
                 getLy(sn);
            }
        }

    }
    @PostMapping(value = "/getRecord")
    public void  getResult(@RequestBody  String json)throws Exception {
        System.out.println(json);
        JSONObject json2=     JSONObject.parseObject(json.substring(json.indexOf("{")));
        String deviceId=json2.getString("DeviceCode");
        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setDeviceNo(deviceId);
        JSONObject faceInfo=(JSONObject) json2.getJSONArray("FaceInfoList").get(0);
        JSONObject libMatInfo=(JSONObject) json2.getJSONArray("LibMatInfoList").get(0);
        Long timestamp=faceInfo.getLong("Timestamp");
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=sdf.format(new Date(timestamp));
        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
        //有此设备才进行操作
        if(hadList.size()>0){
            HjAttendanceDevice had2=hadList.get(0);
            String twoway=had2.getTwoway();
            String direction=null;
            if("0".equals(twoway)){
                direction=had2.getDirection();
            }else{
                direction=getDirection(time);
            }
            //判断是智慧工地数据还是智慧楼宇(楼宇)
            if("zhgd".equals(had2.getSystemType())){
                HjProjectWorkers hpw=hjProjectWorkersService.selectHjProjectWorkersById(libMatInfo.getInteger("MatchPersonID"));
                if(hpw!=null) {
                    zhgd(faceInfo, hpw, had2,direction,deviceId);
                }
            }else{
                LyPersonnel hpw=lyPersonnelService.selectLyPersonnelById(libMatInfo.getInteger("MatchPersonID"));
                if(hpw!=null) {
                    ly(faceInfo, hpw, had2,direction,deviceId);
                }
            }

        }

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
    private   void getZhgd(String deviceNo){

        Map<String,String > param=new HashMap<String ,String>();
        param.put("deviceNo",deviceNo);
        param.put("status","0");


        List<HjDeviceProjectworkersParam> hList1=hjDeviceProjectworkersService.selectHjProjectworkersListTwo(param);

        if(hList1.size()>0){
            HjDeviceProjectworkersParam hw=hList1.get(0);
            String photo=BASE64DecodedMultipartFile.ImageToBase64ByOnline(hw.getFaceUrl()).replaceAll("\r|\n", "");
            JSONObject json=new JSONObject();
            json.put("Num",1);
            JSONObject personInfoList=new JSONObject();
            personInfoList.put("PersonID",hw.getPid());
            personInfoList.put("LastChange",System.currentTimeMillis()/1000);
            personInfoList.put("PersonName",hw.getEmpName());
            JSONObject timeTemplate=new JSONObject();
            timeTemplate.put("BeginTime",0);
            timeTemplate.put("EndTime",4294967295L);
            timeTemplate.put("Index",0);
            personInfoList.put("TimeTemplate",timeTemplate);
            personInfoList.put("IdentificationNum",1);
            JSONArray codeArray=new JSONArray();
            JSONObject codeObject=new JSONObject();
            codeObject.put("Type",0);
            codeObject.put("Number",hw.getIdCode());
            codeArray.add(codeObject);
            personInfoList.put("IdentificationList",codeArray);
            personInfoList.put("ImageNum",1);
            JSONArray imageArray=new JSONArray();
            JSONObject imageObject=new JSONObject();
            imageObject.put("FaceID",hw.getPid());
            imageObject.put("Size",photo.length());
            imageObject.put("Data",photo);
            imageArray.add(imageObject);
            personInfoList.put("ImageList",imageArray);
            JSONArray perList=new JSONArray();
            perList.add(personInfoList);
            json.put("PersonInfoList",perList);
            client.insertPerson(json.toJSONString(),deviceNo);
            HjDeviceProjectworkers hd=new HjDeviceProjectworkers();
            hd.setStatus("1");
            hd.setId(hw.getDid());

            hjDeviceProjectworkersService.updateHjDeviceProjectworkers(hd);
        }
        param.put("status","2");
        List<HjDeviceProjectworkersParam> hList2=hjDeviceProjectworkersService.selectHjProjectworkersListTwo(param);
        if(hList2.size()>0){

            HjDeviceProjectworkersParam hw2=hList2.get(0);
            client.deletePerson(hw2.getPid(),deviceNo);
            HjDeviceProjectworkers hdpw=new HjDeviceProjectworkers();
            hdpw.setDeviceNo(deviceNo);
            hdpw.setProjectWorkersId(hw2.getPid());
            hjDeviceProjectworkersService.deleteHjDeviceProjectworkersTwo(hdpw);
        }

        updateConnectTime(deviceNo);
    }
    private  void getLy(String deviceNo){

        Map<String,String > param=new HashMap<String ,String>();
        param.put("deviceNo",deviceNo);
        param.put("status","0");

        List<HjDeviceProjectworkersParam> hList1=lyDevicePersonnelService.selectLyDevicePersonnelListTwo(param);

        if(hList1.size()>0){
            HjDeviceProjectworkersParam hw=hList1.get(0);
            String photo=BASE64DecodedMultipartFile.ImageToBase64ByOnline(hw.getFaceUrl()).replaceAll("\r|\n", "");
            JSONObject json=new JSONObject();
            json.put("Num",1);
            JSONObject personInfoList=new JSONObject();
            personInfoList.put("PersonID",hw.getPid());
            personInfoList.put("LastChange",System.currentTimeMillis()/1000);
            personInfoList.put("PersonName",hw.getEmpName());
            JSONObject timeTemplate=new JSONObject();
            timeTemplate.put("BeginTime",0);
            timeTemplate.put("EndTime",4294967295L);
            timeTemplate.put("Index",0);
            personInfoList.put("TimeTemplate",timeTemplate);
            personInfoList.put("IdentificationNum",1);
            JSONArray codeArray=new JSONArray();
            JSONObject codeObject=new JSONObject();
            codeObject.put("Type",0);
            codeObject.put("Number",hw.getIdCode());
            codeArray.add(codeObject);
            personInfoList.put("IdentificationList",codeArray);
            personInfoList.put("ImageNum",1);
            JSONArray imageArray=new JSONArray();
            JSONObject imageObject=new JSONObject();
            imageObject.put("FaceID",hw.getPid());
            imageObject.put("Size",photo.length());
            imageObject.put("Data",photo);
            imageArray.add(imageObject);
            personInfoList.put("ImageList",imageArray);
            JSONArray perList=new JSONArray();
            perList.add(personInfoList);
            json.put("PersonInfoList",perList);
            client.insertPerson(json.toJSONString(),deviceNo);
            LyDevicePersonnel ld=new LyDevicePersonnel();
            ld.setStatus("1");
            ld.setId(hw.getDid());
            lyDevicePersonnelService.updateLyDevicePersonnel(ld);
        }
        param.put("status","2");
        List<HjDeviceProjectworkersParam> hList2=lyDevicePersonnelService.selectLyDevicePersonnelListTwo(param);
        if(hList2.size()>0){
            HjDeviceProjectworkersParam hw2=hList2.get(0);
            client.deletePerson(hw2.getPid(),deviceNo);
            LyDevicePersonnel lp=new LyDevicePersonnel();
            lp.setPersonnelId(hw2.getPid());
            lp.setDeviceNo(deviceNo);
            lyDevicePersonnelService.deleteLyDevicePersonnelTwo(lp);
        }

        updateConnectTime(deviceNo);
    }
    public void  updateConnectTime(String deviceNo){
        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setDeviceNo(deviceNo);
        had.setConnectTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        hjAttendanceDeviceService.updateHjAttendanceDeviceTwo(had);
    }
    /**
     * 智慧工地操作
     */
    private void zhgd(JSONObject faceInfo, HjProjectWorkers hpw,HjAttendanceDevice had2,String direction,String sn)throws  Exception{
        List<HjSynchronizationInformation> list = this.queryHjSynchronizationInformation(had2.getProjectId());  // 项目密钥

        String passedTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.valueOf(faceInfo.getString("Timestamp")+"000")));


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

                        jsonObject.put("Device_ID", Tools.encodeToMD5s(sn));
                        jsonData.put("person_type", "1");
                        jsonData.put("person_id", hpw.getIdCode());
                        jsonData.put("person_name", hpw.getEmpName());
                        jsonData.put("passed_time", passedTime);
                        jsonData.put("direction", direction);
                        jsonData.put("way", "1");

                        HjSynchronizationInformation h = list.get(i);
                        if (h.getPlatformName().equals("HOUS")) {//住建
                            jsonObject.put("Project_ID", h.getProjectNumber());
                            jsonData.put("site_photo",faceInfo.getJSONObject("PanoImage").getString("Data"));
                            jsonArray.add(jsonData);
                            jsonObject.put("passedlog_list", jsonArray);

                            String url = ZCAPIClientTwo.getUrl(h.getApiSecret(), h.getApiKey(), "1.1", h.getClientSerial(), jsonObject.toString(), Constants.HJ_FORMALHOST + "UploadPassedLog");
                            String result = ZCAPIClientTwo.httpPostWithJSON(url, jsonObject);


                        } else if (h.getPlatformName().equals("MUNICIPAL")) {//市政
                            jsonObject.put("Project_ID", h.getProjectNumber());
                            jsonData.put("site_photo", faceInfo.getJSONObject("PanoImage").getString("Data"));
                            jsonArray.add(jsonData);
                            jsonObject.put("passedlog_list", jsonArray);

                            String url = ZCAPIClientTwo.getUrl(h.getApiSecret(), h.getApiKey(), "1.1", h.getClientSerial(), jsonObject.toString(), Constants.ZHGD_FORMALHOST + "UploadPassedLog");
                            String result = ZCAPIClientTwo.httpPostWithJSON(url, jsonObject);

                        } else if (h.getPlatformName().equals("WORKSBUREAU")) {//工务署
//                        System.out.println("000000000000");
                            jsonObject.put("Project_ID", h.getEngineeringCode());
                            jsonData.put("data_id", h.getApiKey() + Tools.encodeToMD5s(hpw.getIdCode() + passedTime));
                            jsonData.put("site_photo", ZCgetImageId.base64ToimgId(h.getApiKey(), faceInfo.getJSONObject("PanoImage").getString("Data")));
                            jsonArray.add(jsonData);
                            jsonObject.put("passedlog_list", jsonArray);
                            String url = ZCAPIClientTwo.getUrl(h.getApiSecret(), h.getApiKey(), "1.0", h.getApiKey(), jsonObject.toString(), Constants.ZC_FORMALHOST + "UploadPassedLog");
                            String result = ZCAPIClientTwo.httpPostWithJSON(url, jsonObject);


                        } else if (h.getPlatformName().equals("DGHOUS")) {//东莞住建
                            jsonData.clear();
                            jsonData.put("RosterWokerId", hpw.getRosterWokerId());
                            jsonData.put("CheckDate", passedTime);
                            jsonData.put("DeviceNo", sn);
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
                            jsonData.put("photo",faceInfo.getJSONObject("PanoImage").getString("Data"));
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
        HjAttendanceRecord har=new HjAttendanceRecord();
        if (flag) {
            MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(faceInfo.getJSONObject("PanoImage").getString("Data"));
            String FileImgurl = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", hpw.getIdCode() + System.currentTimeMillis() + ".jpg");
            String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
            har.setSitePhoto(name);
            har.setProjectId(had2.getProjectId());
            har.setEmployeeId(hpw.getId());
            har.setPassedTime(passedTime);
            har.setDirection(direction);
            har.setWay(1);
            har.setDeviceType("face");
            har.setDeviceSn(sn);
            if(flag2){
                har.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
            har.setTemperature(faceInfo.getString("Temperature"));
            hjAttendanceRecordService.insertHjAttendanceRecord(har);
        }
    }
    /**
     * 智慧楼宇操作
     */
    private void ly(JSONObject faceInfo, LyPersonnel hpw,HjAttendanceDevice had2,String direction,String sn)throws Exception{
        LyAttendanceRecord har=new LyAttendanceRecord();
        String passedTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(faceInfo.getLong("Timestamp")));
        MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(faceInfo.getJSONObject("PanoImage").getString("Data"));
        String FileImgurl = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", hpw.getIdCode() + System.currentTimeMillis() + ".jpg");
        String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
        har.setSitePhoto(name);
        har.setProjectId(had2.getProjectId());
        har.setEmployeeId(hpw.getId());
        har.setPassedTime(passedTime);
        har.setDirection(direction);
        har.setWay(1);
        har.setDeviceType("face");
        har.setDeviceSn(sn);
        har.setTemperature(faceInfo.getString("Temperature"));

        lyAttendanceRecordService.insertLyAttendanceRecord(har);


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
     * 判断时间是否超过15分钟
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
}
