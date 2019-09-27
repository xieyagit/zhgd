package com.hujiang.project.zhgd.hjAttendanceRecord.task;

import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-07-05 11:14
 **/
//@Component
public class AttendanceTask {
    private Logger logger = Logger.getLogger(AttendanceTask.class.getName());
    @Autowired
    private IHjAttendanceRecordService attendanceRecordService;
    @Autowired
    private IHjProjectService projectService;
    @Autowired
    private IHjProjectWorkersService workersService;

    /**
     * 每天凌晨0点添加考勤报表数据
     */
//    @Scheduled(cron="0 0 0 */1 * ?")
    public void addBB(){
        logger.info("\r com.hujiang.project.zhgd.hjAttendanceRecord.task.AttendanceTask.addBB:每天凌晨0点添加考勤报表数据  START");
        HjProject project = new HjProject();
        project.setShowState(0);
        //获取所有显示的项目
        List<HjProject> hjProjects = projectService.selectHjProjectList(project);
        if(hjProjects!=null && hjProjects.size()>0){
            for(HjProject hjProject:hjProjects){//遍历项目信息
                HjProjectWorkers workers = new HjProjectWorkers();
                workers.setProjectId(hjProject.getId());
                //获取项目下所有人
                List<HjProjectWorkers> workers1 = workersService.selectHjProjectWorkersList(workers);
                if(workers1!=null && workers1.size()>0){
                    for(HjProjectWorkers workers2:workers1){//遍历人员信息
                        //添加前一天考勤工时
                        attendanceRecordService.addTime(workers2.getId(),workers2.getEmpName(),workers2.getProjectId());
                    }
                }
            }//-----------------FOR END
        }//-----------------IF END
        logger.info("\r com.hujiang.project.zhgd.hjAttendanceRecord.task.AttendanceTask.addBB:每天凌晨0点添加考勤报表数据  END");
    }//----------------addBB END
}
