package com.hujiang.project.zhgd.lyStatistics.task;


import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyDevicePersonnel.service.ILyDevicePersonnelService;
import com.hujiang.project.zhgd.lyPersonnel.service.ILyPersonnelService;
import com.hujiang.project.zhgd.lyStatistics.domain.LyStatistics;
import com.hujiang.project.zhgd.lyStatistics.service.ILyStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Component
public class lyStatisticsTask extends AutoTaskBase {

    @Autowired
    private IHjProjectService hjProjectService;
    @Autowired
    private ILyStatisticsService lyStatisticsService;
    @Autowired
    private ILyPersonnelService lyPersonnelService;
    @Scheduled(cron="0 30 2 * * ?")
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
     * 每天统计前天人员
     */
    public void add() {
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,-24);
        String time=dateFormat.format(calendar.getTime());
        HjProject hp=new HjProject();
        hp.setProjectType("ly");
        List<HjProject> hpList=hjProjectService.selectHjProjectList(hp);
        HjProject hjProject;
        LyAttendanceRecord lyAttendanceRecord;
        LyStatistics lyStatistics;
        int count;
        for(int i=0;i<hpList.size();i++){
            hjProject=hpList.get(i);
            lyAttendanceRecord=new LyAttendanceRecord();
            lyStatistics=new LyStatistics();
            lyStatistics.setPid(hjProject.getId());
            lyStatistics.setTime(time);
            lyStatistics.setType("1");
            lyAttendanceRecord.setProjectId(hjProject.getId());
            lyAttendanceRecord.setPassedTime(time);
            count=lyPersonnelService.zzryinout(lyAttendanceRecord);
            lyStatistics.setInout(String.valueOf(count));
            lyStatisticsService.insertLyStatistics(lyStatistics);
            lyStatistics.setType("2");
            count=lyPersonnelService.fkryinout(lyAttendanceRecord);
            lyStatistics.setInout(String.valueOf(count));
            lyStatisticsService.insertLyStatistics(lyStatistics);

        }


    }
}
