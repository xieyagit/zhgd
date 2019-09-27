package com.hujiang.project.xh.task;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.xh.Api.xhApi;
import com.hujiang.project.xh.tokenApi.TokenApi;
import com.hujiang.project.xh.utils.HttpUtilsXh;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjStatistics.domain.HjStatistics;
import com.hujiang.project.zhgd.hjStatistics.service.IHjStatisticsService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

//@Component
public class TimerTask {
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    @Resource
    private xhApi xhHttpApi;
    @Resource
    private TokenApi tokenApi;
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;
    /**
     * 每个小时推送星河数据
     */
//    @Scheduled(cron="0 0 0/1 * * ?")
    public void add() throws Exception{
        //获取一个小时内的考勤数据
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date=new Date();
        String endTime=dateFormat.format(date.getTime());
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -60);//
        String startTime=dateFormat.format(beforeTime.getTime());
        HjSynchronizationInformation hs=new HjSynchronizationInformation();
        hs.setPlatformName("YIZHITONG");
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hsList=hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
        if(hsList.size()>0){
            String pid;
            String token;

            for(HjSynchronizationInformation hsi:hsList ){
                pid=hsi.getProjectId().toString();
                token=tokenApi.yiZhiTongToken(Integer.valueOf(pid));
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
                    JSONObject b;
                    JSONArray c=new JSONArray();
                    for(Object o:list){
                        hp=new HjProjectWorkers();
                        b=new JSONObject();
                        HjAttendanceRecord hr;
                        a  = JSONObject.parseObject(o.toString());
                        hp.setIdCode(a.getString("Shenfenzheng"));
                        List<HjProjectWorkers> workersList=hjProjectWorkersService.selectHjProjectWorkersList(hp);
                        if(workersList.size()>0){

                            hr=new HjAttendanceRecord();
                            hr.setProjectId(workersList.get(0).getProjectId());
                            hr.setEmployeeId(workersList.get(0).getId());
                            hr.setPassedTime(a.getString("CheckTime").replace("/","-"));
                            hr.setDirection("1".equals(a.getString("inout"))?"in":"2".equals(a.getString("inout"))?"out":"0");
                            //判断是否重复插入
                            List<HjAttendanceRecord> aList=hjAttendanceRecordService.selectHjAttendanceRecordList(hr);
                            if(aList.size()<=0) {
                                hr.setUploadTime(a.getString("CheckTime").replace("/", "-"));
                                hr.setWay(1);

                                b.put("userId",workersList.get(0).getRemark());
                                b.put("phone",workersList.get(0).getEmpPhon());
                                b.put("type","1".equals(a.getString("inout"))?2:"2".equals(a.getString("inout"))?1:0);

                                c.add(b);
                                hjAttendanceRecordService.insertHjAttendanceRecord(hr);
                            }
                        }


                    }
                    xhHttpApi.setRecord(c,pid);


                }
            }
        }

    }
}
