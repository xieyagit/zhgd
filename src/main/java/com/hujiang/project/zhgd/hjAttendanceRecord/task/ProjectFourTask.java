package com.hujiang.project.zhgd.hjAttendanceRecord.task;

import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-07-05 11:14
 **/
@Component
public class ProjectFourTask extends AutoTaskBase {
    private Logger logger = Logger.getLogger(ProjectFourTask.class.getName());
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;

    @Scheduled(cron="0 0 6 * * ?")
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
    /**
     * 每天给项目4添加考勤数据
     */
    public void addBB() throws  Exception{

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        HjProjectWorkers hpwa=new HjProjectWorkers();
        hpwa.setProjectId(4);
       List<HjProjectWorkers> list=hjProjectWorkersService.selectHjProjectWorkersList(hpwa);
        HjAttendanceRecord hr=null;
        HjProjectWorkers hpw=null;
        for(int i=0;i<list.size();i++){
            hpw=list.get(i);
            hr=new HjAttendanceRecord();
            System.out.println(i);
            beforeTime.add(Calendar.MINUTE, 1);//
            String time=sdf.format(beforeTime.getTime());//当前时间
            System.out.println(time);
            hr.setProjectId(4);
            hr.setEmployeeId(hpw.getId());
            hr.setPassedTime(time);
            hr.setDirection("in");
            hr.setWay(1);
            hr.setSitePhoto(hpw.getFaceUrl());
            hjAttendanceRecordService.insertHjAttendanceRecord(hr);




        }


    }
}
