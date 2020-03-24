package com.hujiang.project.zhgd.lyPersonnel.task;


import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjStatistics.domain.HjStatistics;
import com.hujiang.project.zhgd.hjStatistics.service.IHjStatisticsService;
import com.hujiang.project.zhgd.lyDevicePersonnel.service.ILyDevicePersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CleanFKTask extends AutoTaskBase {

    @Autowired
    private ILyDevicePersonnelService lyDevicePersonnelService;
    @Scheduled(cron="0 0 23 * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    clean();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }
    /**
     * 每天清除临时人员
     */
    public void clean() {
        lyDevicePersonnelService.deleteLyDevicePersonnelTypeTwo();
        lyDevicePersonnelService.updateLyDevicePersonnelTypeTwo();
    }
}
