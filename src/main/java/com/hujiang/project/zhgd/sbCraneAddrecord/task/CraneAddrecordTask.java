package com.hujiang.project.zhgd.sbCraneAddrecord.task;

import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.project.zhgd.sbCraneAddrecord.service.ISbCraneAddrecordService;
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
//@RequestMapping(value = "/provider/taskCraneAddrecord",method = RequestMethod.POST)
public class CraneAddrecordTask extends AutoTaskBase {
    @Autowired
    private ISbCraneAddrecordService craneAddrecordService;
    @Scheduled(cron="0 0 6 * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    craneAddrecord();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }
//    @PostMapping(value = "insert")
    public void craneAddrecord() throws  Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        String deviceId ="18012901";
        String dateTime = "2020-03-26";
        List<SbCraneAddrecord> craneAddrecords = craneAddrecordService.selectSbCraneAddRecordHistory(deviceId,dateTime,null);
        for(SbCraneAddrecord crane:craneAddrecords){
            SbCraneAddrecord sb=new SbCraneAddrecord();
            beforeTime.add(Calendar.MINUTE, 1);//
            String time=sdf.format(beforeTime.getTime());//当前时间
            System.out.println(time);
            sb.setDeviceNo("CD0078C39FE163D1A75B2D0159D9A003");
            sb.setRuntime(time);
            sb.setLoad(crane.getLoad());
            sb.setLoadRatio(crane.getLoadRatio());
            sb.setMoment(crane.getMoment());
            sb.setMomentRatio(crane.getMomentRatio());
            sb.setSlewingSpeed(crane.getSlewingSpeed());
            sb.setIsLeftWarning(crane.getIsLeftWarning());
            sb.setIsRightWarning(crane.getIsRightWarning());
            sb.setRange(crane.getRange());
            sb.setIsForwardWarning(crane.getIsForwardWarning());
            sb.setIsBackwardWarning(crane.getIsBackwardWarning());
            sb.setHeight(crane.getHeight());
            sb.setIsUpWarning(crane.getIsUpWarning());
            sb.setWorkEnvironment(crane.getWorkEnvironment());
            sb.setRealTimeFlag(crane.getRealTimeFlag());
            sb.setInstallationMethod(crane.getInstallationMethod());
            sb.setWindSpeed(crane.getWindSpeed());
            sb.setCardNum(crane.getCardNum());
            sb.setOperatorName(crane.getOperatorName());
            sb.setHxzid(deviceId);
            sb.setMagnification(crane.getMagnification());
            sb.setRatedWeight(crane.getRatedWeight());
            sb.setObliguity(crane.getObliguity());
            craneAddrecordService.insertSbCraneAddrecord(sb);
        }

    }
}
