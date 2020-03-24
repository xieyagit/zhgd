package com.hujiang.project.consumer;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.HttpUtils;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.common.utils.Md5Utils;
import com.hujiang.framework.config.JmsConfig;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.project.client.SystemClient;
import com.hujiang.project.ys.util.YsUtil;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjDeviceHikvision.domain.HjDeviceHikvision;
import com.hujiang.project.zhgd.hjDeviceHikvision.service.IHjDeviceHikvisionService;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
import com.hujiang.project.zhgd.hjLogging.domain.HjLogging;
import com.hujiang.project.zhgd.hjLogging.service.IHjLoggingService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import com.hujiang.project.zhgd.utils.Constants;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class InOutAttendanceService {


    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @Autowired
    private IHjDeviceProjectworkersService hjDeviceProjectworkersService;
    @Resource
    private YsUtil ysUtil;
    @Autowired
    private IHjLoggingService hjLoggingService;
    @Autowired
    private IHjDeviceHikvisionService hjDeviceHikvisionService;
    @Autowired
    private SystemClient client;
    //log日志
    private static Logger logger = LoggerFactory.getLogger(InOutAttendanceService.class);

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = JmsConfig.OPEN_YS)
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo(JmsConfig.OPEN_YS + "_OUT") //双向队列
    public String handleMessage(String message)throws Exception {

        try {
            JmsMessageInfo info = JsonUtils.parse(message, JmsMessageInfo.class);
            if (JmsMessageType.INSERT_ATTENDANCE.equals(info.getType())) {
                insertAttendance(info);
                System.out.println("消息队列进场");
            }

            if (JmsMessageType.DELETE_ATTENDANCE.equals(info.getType())) {
                deleteAttendance(info);
                System.out.println("消息队列离场");
            }
        }
        catch(IOException e) {
            return message;
        }
        return null;
    }
    //进场操作
    private void insertAttendance(JmsMessageInfo info) throws Exception {

        HjProjectWorkers hw = JsonUtils.convert(info.getBody(), HjProjectWorkers.class);
        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setProjectId(hw.getProjectId());
        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
        HjDeviceProjectworkers hdpw=new HjDeviceProjectworkers();
        hdpw.setProjectWorkersId(hw.getId());
        List<HjDeviceProjectworkers> list;
        for(HjAttendanceDevice h: hadList){
            if("ys".equals(h.getDeviceFactory())){
                //负责处理海康设备的
                isYsUser(hw,h.getDeviceNo());
//                System.out.println("我是测试2");
            }else{
                //看是否是待删除人脸
                hdpw.setStatus("2");
                hdpw.setDeviceNo(h.getDeviceNo());
                list=hjDeviceProjectworkersService.selectHjDeviceProjectworkersList(hdpw);
                //是的话就不用删除了，直接改状态
                if(list.size()>0){
                    hdpw.setStatus("1");
                    hjDeviceProjectworkersService.updateHjDeviceProjectworkersTwo(hdpw);
                }else  if("yushi".equals(h.getDeviceFactory())){
                    if(comparisonDate(h.getConnectTime())){
                        String photo=BASE64DecodedMultipartFile.ImageToBase64ByOnline(hw.getFaceUrl()).replaceAll("\r|\n", "");
                        JSONObject json=new JSONObject();
                        json.put("Num",1);

                        JSONObject personInfoList=new JSONObject();
                        personInfoList.put("PersonID",hw.getId());
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
                        imageObject.put("FaceID",hw.getId());
                        imageObject.put("Size",photo.length());
                        imageObject.put("Data",photo);
                        imageArray.add(imageObject);
                        personInfoList.put("ImageList",imageArray);
                        JSONArray perList=new JSONArray();
                        perList.add(personInfoList);
                        json.put("PersonInfoList",perList);
                        client.insertPerson(json.toJSONString(),h.getDeviceNo());
                        hdpw.setStatus("1");
                        hjDeviceProjectworkersService.insertHjDeviceProjectworkers(hdpw);
                    }else {
                        hdpw.setStatus("0");
                        hjDeviceProjectworkersService.insertHjDeviceProjectworkers(hdpw);
                    }

                }else{
                    //否的话就待添加
                    hdpw.setStatus("0");
                    hjDeviceProjectworkersService.insertHjDeviceProjectworkers(hdpw);
                }
                //如果是宇视设备，就立刻添加

            }

        }
    }
    //离场操作
    private void deleteAttendance(JmsMessageInfo info) throws Exception {

        HjProjectWorkers hw = JsonUtils.convert(info.getBody(), HjProjectWorkers.class);
        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setProjectId(hw.getProjectId());
        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
        HjDeviceProjectworkers hdpw=new HjDeviceProjectworkers();
        hdpw.setProjectWorkersId(hw.getId());
        List<HjDeviceProjectworkers> list;
        for(HjAttendanceDevice h: hadList){
            if("ys".equals(h.getDeviceFactory())){
                //负责处理海康设备的
                deleteYs(hw,h.getDeviceNo());
            }else{
                //看是否是待添加人脸
                hdpw.setStatus("12");
                hdpw.setDeviceNo(h.getDeviceNo());
                list=hjDeviceProjectworkersService.selectHjDeviceProjectworkersListTwo(hdpw);
                //是的话就直接删除
                if(list.size()>0){
                    hjDeviceProjectworkersService.deleteHjDeviceProjectworkersByIds(list.get(0).getId().toString());
                }else if("yushi".equals(h.getDeviceFactory())){
                    if(comparisonDate(h.getConnectTime())){
                        client.deletePerson(hw.getId(),h.getDeviceNo());
                        hjDeviceProjectworkersService.deleteHjDeviceProjectworkersTwo(hdpw);
                    }else{
                        //否的话就待删除
                        hdpw.setStatus("2");
                        hdpw.setProjectWorkersId(hw.getId());
                        hjDeviceProjectworkersService.updateHjDeviceProjectworkersTwo(hdpw);}
                }else{
                    //否的话就待删除
                    hdpw.setStatus("2");
                    hdpw.setProjectWorkersId(hw.getId());
                    hjDeviceProjectworkersService.updateHjDeviceProjectworkersTwo(hdpw);
                }
            }

        }
    }
    private void deleteYs(HjProjectWorkers hw,String sn)throws URISyntaxException,IOException{
        HjDeviceHikvision hdh=new HjDeviceHikvision();
        hdh.setIdCard(hw.getIdCode());
        List<HjDeviceHikvision> hdhList=hjDeviceHikvisionService.selectHjDeviceHikvisionList(hdh);
        String accessToken=ysUtil.getAccessToken();
        if(hdhList.size()>0){
            List<NameValuePair> params2=new ArrayList<NameValuePair>();
            params2.add(new BasicNameValuePair("accessToken",accessToken));
            params2.add(new BasicNameValuePair("personId",hdhList.get(0).getId().toString()));
            params2.add(new BasicNameValuePair("deviceSerial",sn));
            String a= ysUtil.httpPostWithJSON(Constants.OPEN_YS+"acs/person/delete",params2);
            JSONObject result=JSONObject.parseObject(a);
            if("200".equals(result.getString("code"))){
                HjDeviceProjectworkers hdpw=new HjDeviceProjectworkers();
                hdpw.setDeviceNo(sn);
                hdpw.setProjectWorkersId(hw.getId());
                hjDeviceProjectworkersService.deleteHjDeviceProjectworkersTwo(hdpw);
            }else{
                HjLogging hl=new HjLogging();
                hl.setProjectId(hw.getProjectId());
                hl.setLoggingMessage(result.getString("msg"));
                hl.setLoggingData(a);
                hl.setInOut("人脸机删除人脸失败！");
                hl.setUserName(hw.getEmpName());
                hl.setLoggingTag("RecordDevice");
                hl.setLoggingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                hjLoggingService.insertHjLogging(hl);
            }
        }
    }
    //是否在萤石平台添加了用户
    private void isYsUser(HjProjectWorkers hw,String sn)throws URISyntaxException,IOException{
        HjDeviceHikvision hdh=new HjDeviceHikvision();
        hdh.setIdCard(hw.getIdCode());
        List<HjDeviceHikvision> hdhList=hjDeviceHikvisionService.selectHjDeviceHikvisionList(hdh);
        String accessToken=ysUtil.getAccessToken();
        if(hdhList.size()<=0){
            //没添加就先添加用户
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("accessToken",accessToken));
            params.add(new BasicNameValuePair("personName",hw.getEmpName()));
            params.add(new BasicNameValuePair("phoneNumber",hw.getEmpPhon()));
            params.add(new BasicNameValuePair("certificateNumber",hw.getIdCode()));
            params.add(new BasicNameValuePair("base64FaceImageFile","data:image/jpg;base64,"+ BASE64DecodedMultipartFile.ImageToBase64ByOnline(hw.getFaceUrl()).replaceAll("\r|\n", "")));
            params.add(new BasicNameValuePair("extension.userType","2"));
            String s=   ysUtil.httpPostWithJSON(Constants.OPEN_YS+"person/add",params);
            JSONObject result=JSONObject.parseObject(s);
            //添加成功则保存进萤石用户表，否则添加异常记录
            if("200".equals(result.getString("code"))){
                HjDeviceHikvision hdh2=new HjDeviceHikvision();
                hdh2.setId(result.getJSONObject("data").getJSONObject("person").getInteger("id"));
                hdh2.setIdCard(hw.getIdCode());
                hjDeviceHikvisionService.insertHjDeviceHikvision(hdh2);
                //下发权限
                setJurisdiction(hdh2.getId().toString(),sn,hw.getId(),accessToken);
            }else {
                HjLogging hl=new HjLogging();
                hl.setProjectId(hw.getProjectId());
                hl.setLoggingMessage(result.getString("msg"));
                hl.setLoggingData(s);
                hl.setInOut("向人脸机下发人脸失败！");
                hl.setUserName(hw.getEmpName());
                hl.setLoggingTag("RecordDevice");
                hl.setLoggingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                hjLoggingService.insertHjLogging(hl);

            }
        }else{
            HjDeviceHikvision hdh3=hdhList.get(0);
            setJurisdiction(hdh3.getId().toString(),sn,hw.getId(),accessToken);
        }
    }
    //下发海康人脸机权限
    private void setJurisdiction(String personId,String sn,Integer id,String accessToken)throws URISyntaxException,IOException{
        List<NameValuePair> params2=new ArrayList<NameValuePair>();
        params2.add(new BasicNameValuePair("accessToken",accessToken));
        params2.add(new BasicNameValuePair("personId",personId));
        params2.add(new BasicNameValuePair("deviceSerial",sn));
        String a= ysUtil.httpPostWithJSON(Constants.OPEN_YS+"acs/person/add",params2);
        if("200".equals(JSONObject.parseObject(a).getString("code"))){
            HjDeviceProjectworkers hdp=new HjDeviceProjectworkers();
            hdp.setProjectWorkersId(id);
            hdp.setDeviceNo(sn);
            hdp.setStatus("1");
            hjDeviceProjectworkersService.insertHjDeviceProjectworkers(hdp);
        }
    }
    public boolean comparisonDate(String time) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date kqTime=sdf.parse(time);//考勤时间
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -10);//
        Date dqTime=sdf.parse(sdf.format(beforeTime.getTime()));//当前时间
        if(dqTime.before(kqTime)){
            return true;
        } else{
            return false;
        }

    }
}
