package com.hujiang.project.zhgd.sbElevatorAddrecord.task;

import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbElevatorAddrecord.service.ISbElevatorAddrecordService;
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
//@RequestMapping(value = "/provider/taskElevatorAddrecord",method = RequestMethod.POST)
public class ElevatorAddrecordTask extends AutoTaskBase {
    @Autowired
    private ISbElevatorAddrecordService elevatorAddrecordService;
    @Scheduled(cron="0 0 6 * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    elevatorAddrecord();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }
//    @PostMapping(value = "insert")
    public void elevatorAddrecord() throws  Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        SbElevatorAddrecord sbElevatorAddrecord = new SbElevatorAddrecord();
        sbElevatorAddrecord.setHxzid("18012902");
        sbElevatorAddrecord.setRuntime("2020-03-25");
        List<SbElevatorAddrecord> elevatorAddrecords = elevatorAddrecordService.selectSbElevatorAddrecordList(sbElevatorAddrecord);
        for(SbElevatorAddrecord ele:elevatorAddrecords){
            SbElevatorAddrecord sb=new SbElevatorAddrecord();
            beforeTime.add(Calendar.MINUTE, 1);//
            String time=sdf.format(beforeTime.getTime());//当前时间
            System.out.println(time);
            sb.setDeviceNo("0DFA378622D43F2A2D76DD26F19960E2");
            sb.setRuntime(time);
            sb.setLaod(ele.getLaod());
            sb.setLoadRatio(ele.getLoadRatio());
            sb.setSign(ele.getSign());
            sb.setLoadingCapacity(ele.getLoadingCapacity());
            sb.setOperatorName(ele.getOperatorName());
            sb.setIcNum(ele.getIcNum());
            sb.setDirection(ele.getDirection());
            sb.setHeight(ele.getHeight());
            sb.setRealTimeFlag(ele.getRealTimeFlag());
            sb.setStatus(ele.getStatus());
            sb.setBrakingDistance(ele.getBrakingDistance());
            sb.setIsUpWarning(ele.getIsUpWarning());
            sb.setIsDownWarning(ele.getIsDownWarning());
            sb.setIsSafetyDeviceWarn(ele.getIsSafetyDeviceWarn());
            sb.setIsOverWarning(ele.getIsOverWarning());
            sb.setIsForwardWarning(ele.getIsForwardWarning());
            sb.setIsBackwardWarning(ele.getIsBackwardWarning());
            sb.setHxzid(sbElevatorAddrecord.getHxzid());
            sb.setPeopleCntAlarm(ele.getPeopleCntAlarm());
            sb.setWeightAlarm(ele.getWeightAlarm());
            sb.setSpeedAlarm(ele.getSpeedAlarm());
            sb.setObliguityXAlarm(ele.getObliguityXAlarm());
            sb.setObliguityYAlarm(ele.getObliguityYAlarm());
            sb.setSpeed(ele.getSpeed());
            sb.setWindSpeed(ele.getWindSpeed());
            sb.setWindLevel(ele.getWindLevel());
            sb.setFloor(ele.getFloor());
            sb.setObliguityX(ele.getObliguityX());
            sb.setObliguityY(ele.getObliguityY());
            elevatorAddrecordService.insertSbElevatorAddrecord(sb);
        }
    }
}
