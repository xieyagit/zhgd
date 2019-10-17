package com.hujiang.project.xiamenyizhitong;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.xh.Api.xhApi;
import com.hujiang.project.xh.tokenApi.TokenApi;
import com.hujiang.project.xh.utils.HttpUtilsXh;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.hjTeam.pcApi.PcTeamApi;
import com.hujiang.project.zhgd.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
//@RestController
//@RequestMapping(value = "/provider/o")
public class yiZhitongTask extends AutoTaskBase {
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    @Resource
    private TokenApi tokenApi;
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;
    private Logger logger = Logger.getLogger(yiZhitongTask.class.getName());

    @Scheduled(cron="0 0/30 * * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    add();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }
    /**
     * 每个小时获取一指通考勤记录
     */
    //    @PostMapping(value = "/oa")
    public void add() throws Exception{

        //获取一个小时内的考勤数据
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String endTime=dateFormat.format(date.getTime());
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -30);//
        String startTime=dateFormat.format(beforeTime.getTime());
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setPlatformName("YIZHITONG4");
        hs.setApiType("keytype1");
        hs.setState(1);
        List<HjSynchronizationInformation> hsList=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        if(hsList.size()>0){
            String pid;
            String token;

            for(HjSynchronizationInformation hsi:hsList ){
                pid=hsi.getProjectId().toString();
                token=tokenApi.yiZhiTongTokenThree(Integer.valueOf(pid));
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("xmjc","API");
                jsonObject.put("xmcode",hsi.getProjectNumber());
                jsonObject.put("start",startTime);
//                jsonObject.put("end", endTime);
                //获取一指通的数据
                String result= HttpUtilsXh.xhHttpPost(Constants.YIZHITONG+"/ApiService/GetCockIn.ashx?token="+token,jsonObject.toString(),"");
                JSONObject  results = JSONObject.parseObject(result);
                String data=results.getString("data");
                data=data.replace("\\","");
                JSONArray list=JSONArray.parseArray(data);
                if(list.size()>0){
                    HjProjectWorkers hp;
                    JSONObject a;

                    JSONArray c=new JSONArray();
                    for(Object o:list){
                        hp=new HjProjectWorkers();

                        HjAttendanceRecord hr;
                        a  = JSONObject.parseObject(o.toString());
                        hp.setIdCode(a.getString("Shenfenzheng"));
                        List<HjProjectWorkers> workersList=hjProjectWorkersService.selectHjProjectWorkersList(hp);
                        if(workersList.size()>0){

                            hr=new HjAttendanceRecord();
                            hr.setProjectId(workersList.get(0).getProjectId());
                            hr.setEmployeeId(workersList.get(0).getId());

                            String time=a.getString("CheckTime").replace("/","-");
                            String hh= time.substring(time.lastIndexOf(" ")+1,time.indexOf(":"));
                            if(hh.length()<=1){
                                time=time.replace(" "," 0");
                            }
                            String s1=time.substring(0,time.indexOf("-")+1);
                            String s2=time.substring(time.indexOf("-")+1,time.lastIndexOf("-")+1);
                            String s3=time.substring(time.lastIndexOf("-")+1);
                            if(s2.length()<=2){
                                s2="0"+s2;
                            }
                            if(s3.length()<=10){
                                s3="0"+s3;
                            }
                            time=s1+s2+s3;

                            hr.setPassedTime(time);
                            hr.setSitePhoto(a.getString("photopath"));
                            hr.setDirection("1".equals(a.getString("inout")) ? "in" : "2".equals(a.getString("inout")) ? "out" : "0");
                            //判断是否重复插入
                            List<HjAttendanceRecord> aList=hjAttendanceRecordService.selectHjAttendanceRecordList(hr);
                            if(aList.size()<=0) {

                                hr.setUploadTime(a.getString("CheckTime").replace("/", "-"));
                                hr.setWay(1);

                                hjAttendanceRecordService.insertHjAttendanceRecord(hr);
                            }


                        }


                    }



                }
            }
        }

    }
}
