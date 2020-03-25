package com.hujiang.project.zhgd.sbUnloaderRegistration.task;

import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbUnloaderRealtime.domain.SbUnloaderRealtime;
import com.hujiang.project.zhgd.sbUnloaderRealtime.service.ISbUnloaderRealtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Component
//@RestController
//@RequestMapping(value = "/provider/taskUnloaderRealtime",method = RequestMethod.POST)
public class UnloaderRealtimeTask extends AutoTaskBase {
    @Autowired
    private ISbUnloaderRealtimeService realtimeService;
    @Scheduled(cron="0 0 6 * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    unloaderRealtime();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }
//    @PostMapping(value = "insert")
    public void unloaderRealtime() throws  Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        SbUnloaderRealtime sbUnloaderRealtime = new SbUnloaderRealtime();
        sbUnloaderRealtime.setHxzId("123456");
        sbUnloaderRealtime.setRTime("2020-02-05");
        List<SbUnloaderRealtime> sbUnloaderRealtimeList = realtimeService.getSbUnloaderHistory(sbUnloaderRealtime);
        for(SbUnloaderRealtime unloaderRealtime:sbUnloaderRealtimeList){
            SbUnloaderRealtime sb=new SbUnloaderRealtime();
            beforeTime.add(Calendar.MINUTE, 1);//
            String time=sdf.format(beforeTime.getTime());//当前时间
            System.out.println(time);
            sb.setDeviceNo("E10ADC3949BA59ABBE56E057F20F883E");
            sb.setHxzId("123456");
            sb.setRTime(time);
            sb.setWeight(unloaderRealtime.getWeight());
            sb.setWeightPercent(unloaderRealtime.getWeightPercent());
            sb.setObliguityX(unloaderRealtime.getObliguityX());
            sb.setObliguityY(unloaderRealtime.getObliguityY());
            sb.setObliguity(unloaderRealtime.getObliguityY());
            sb.setBatteryPercent(unloaderRealtime.getBatteryPercent());
            sb.setWeightStatus(unloaderRealtime.getWeightStatus());
            sb.setObliguityXStatus(unloaderRealtime.getObliguityXStatus());
            sb.setObliguityYStatus(unloaderRealtime.getObliguityYStatus());
            sb.setBatteryStatus(unloaderRealtime.getBatteryStatus());
            realtimeService.insertSbUnloaderRealtime(sb);
        }
    }
}
