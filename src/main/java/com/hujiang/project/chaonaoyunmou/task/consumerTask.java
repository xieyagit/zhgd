package com.hujiang.project.chaonaoyunmou.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.StringUtils;
import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.chaonaoyunmou.util.YunMouUtil;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyAttendanceRecord.service.ILyAttendanceRecordService;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.service.ILyPersonnelService;
import com.hujiang.project.zhgd.utils.Constants;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 * 获取云眸消费任务
 */
@Component
public class consumerTask extends AutoTaskBase {
    private Logger logger = Logger.getLogger(consumerTask.class.getName());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private YunMouUtil yunMouUtil;
    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @Autowired
    private ILyAttendanceRecordService lyAttendanceRecordService;
    @Autowired
            private ILyPersonnelService lyPersonnelService;

    String consumerId="";

    @Scheduled(cron="0/20 * * * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    addBB();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }

    public void addBB() throws  Exception{
        if(StringUtils.isBlank(consumerId)){
            consumerId=getConsumerId();
        }
        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("consumerId", consumerId));
        params1.add(new BasicNameValuePair("autoCommit", "true"));
        String result=yunMouUtil.httpPostWithJSONX(Constants.YUNMOU+"/api/v1/mq/consumer/messages",params1,yunMouUtil.getToken());
        JSONObject s=JSONObject.parseObject(result);
        if("200".equals(s.getString("code"))){
            JSONObject a;
            JSONArray list=s.getJSONArray("data");
            for(Object o : list  ){
                a  = JSONObject.parseObject(o.toString());
                if("open_event_super_brain_faces_comparison".equals(a.getString("msgType"))){
                    //超脑人脸比对消息类型
                    facesComparison(a.getString("content"));
                }else if("open_device_alarm".equals(a.getString("msgType"))){
                    //设备报警消息
                    deviceAlarm(a.getString("content"));
                }else if("open_message_tma".equals(a.getString("msgType"))){
                    //测温报警消息
                    messageTma(a.getString("content"));
                }

            }

        }
    }
    public void   messageTma(String s){
        JSONObject a=JSONObject.parseObject(s);
        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setDeviceNo(a.getString("deviceSerial"));
        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
        //有此设备才进行操作
        if(hadList.size()>0) {
            //一般设备只会有一个，所以只取第一条
            HjAttendanceDevice had2 = hadList.get(0);
            Integer pid = had2.getProjectId();
            LyAttendanceRecord lr=new LyAttendanceRecord();
            lr.setProjectId(pid);
            String time = a.getString("dateTime").replaceAll("T"," ");
            time=   time.substring(0,time.indexOf("+"));
            lr.setPassedTime(time);
            lr.setSitePhoto(a.getString("visibleLightURL"));
            lr.setDeviceType("chaonan_messageTma");
            lr.setDeviceSn(a.getString("deviceSerial"));
            lyAttendanceRecordService.insertLyAttendanceRecord(lr);
        }
    }
    /**
     * 设备报警消息
     * @param s
     */
    public void   deviceAlarm(String s){
        JSONObject a=JSONObject.parseObject(s);
        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setDeviceNo(a.getString("devSerial"));
        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
        //有此设备才进行操作
        if(hadList.size()>0) {
            //一般设备只会有一个，所以只取第一条
            HjAttendanceDevice had2 = hadList.get(0);
            Integer pid=had2.getProjectId();
            LyAttendanceRecord lr=new LyAttendanceRecord();
            lr.setProjectId(pid);
            lr.setPassedTime(a.getString("alarmTime").replaceAll("T"," "));
            JSONObject picture=(JSONObject) a.getJSONArray("pictureList").get(0);
            lr.setSitePhoto(picture.getString("url"));
            lr.setDeviceType("chaonan_alarm");
            lr.setDeviceSn(a.getString("devSerial"));
            lyAttendanceRecordService.insertLyAttendanceRecord(lr);
        }
    }
    /**
     * 超脑比对人脸消息
     * @param s
     * @throws Exception
     */
    public void facesComparison(String s)throws  Exception{
        JSONArray list=JSONArray.parseArray(s);
        JSONObject a;
        for(Object o : list){
            a  = JSONObject.parseObject(o.toString());
            HjAttendanceDevice had=new HjAttendanceDevice();
            had.setDeviceNo(a.getString("deviceSeiral"));

            List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
            //有此设备才进行操作
            if(hadList.size()>0) {
                //一般设备只会有一个，所以只取第一条
                HjAttendanceDevice had2=hadList.get(0);
                Integer pid=had2.getProjectId();
                String time = sdf.format(sdf.parse(a.getJSONObject("targetdAttrs").getString("faceTime")));

                JSONArray faces=a.getJSONArray("faces");
                JSONObject a2;
                for(Object o2 : faces) {
                    a2  = JSONObject.parseObject(o2.toString());

                    JSONObject identify=(JSONObject) a2.getJSONArray("identify").get(0);
                    JSONObject candidate=(JSONObject) identify.getJSONArray("candidate").get(0);
                    String idCode=candidate.getString("employeeNo");
                    LyPersonnel lp=new LyPersonnel();
                    lp.setPid(pid);
                    lp.setIdCode(idCode);
                    List<LyPersonnel> lpList=lyPersonnelService.selectLyPersonnelList(lp);
                    if(lpList.size()>0){
                        LyAttendanceRecord lr=new LyAttendanceRecord();
                        lr.setProjectId(pid);
                        lr.setEmployeeId(lpList.get(0).getId());
                        lr.setPassedTime(time);
                        lr.setWay(1);
                        lr.setSitePhoto(a2.getString("url"));
                        lr.setDeviceType("chaonao");
                        lr.setDeviceSn(had2.getDeviceNo());
                        lr.setTemperature(a2.getJSONObject("temperature").getString("currTemperature"));
                        lyAttendanceRecordService.insertLyAttendanceRecord(lr);
                    }

                }
            }
        }


    }

    public String getConsumerId()throws Exception{
        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("consumerName", "group1"));
        String result=yunMouUtil.httpPostWithJSONX(Constants.YUNMOU+"/api/v1/mq/consumer/group1",params1,yunMouUtil.getToken());
        JSONObject s=JSONObject.parseObject(result);
        if("200".equals(s.getString("code"))){
            JSONObject data=s.getJSONObject("data");
            return data.getString("consumerId");
        }
        logger.info("=====:"+s.getString("message"));
        return "";
    }
}
