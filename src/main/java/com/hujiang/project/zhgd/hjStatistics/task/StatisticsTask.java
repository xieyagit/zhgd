package com.hujiang.project.zhgd.hjStatistics.task;


import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjStatistics.domain.HjStatistics;
import com.hujiang.project.zhgd.hjStatistics.service.IHjStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Component
public class StatisticsTask {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private IHjProjectService projectService;
    @Autowired
    private IHjStatisticsService statisticsService;
    /**
     * 每天统计考勤
     */
//    @Scheduled(cron="0 0 23 * * ?")
    public void add() {
        Date date=new Date();
        String time=dateFormat.format(date.getTime());
        HjProject hp=new HjProject();
        //在建项目
        hp.setProjectState("ABUILDING");
        List<HjProject> hpList=projectService.selectHjProjectList(hp);
        HjStatistics hs;
        Map<String,String> map=new HashMap<String,String>();
        map.put("time",time);
        for (HjProject project:hpList){
            map.put("pid",project.getId().toString());
            hs=new HjStatistics();
            hs.setProjectId(project.getId());
            hs.setTime(time);
            //管理人员
            hs.setType("1");
            hs.setNumberOne(statisticsService.selectOneOne(map));
            hs.setNumberTwo(statisticsService.selectOneTwo(map));
            statisticsService.insertHjStatistics(hs);

            //劳务工人
            hs.setType("2");
            hs.setNumberOne(statisticsService.selectTwoOne(map));
            hs.setNumberTwo(statisticsService.selectTwoTwo(map));
            statisticsService.insertHjStatistics(hs);
        }




    }
}
