package com.hujiang.project.zhgd.hjghformwork.task;

import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkData;
import com.hujiang.project.zhgd.hjghformwork.service.IHighformworkDataService;
import com.hujiang.project.zhgd.sbUnloaderRealtime.domain.SbUnloaderRealtime;
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
//@RequestMapping(value = "/provider/taskGHformworkt",method = RequestMethod.POST)
public class HjGHformworktTask extends AutoTaskBase {
    @Autowired
    IHighformworkDataService highformworkDataService;
    @Scheduled(cron="0 0 6 * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    GHformworktTask();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }
//    @PostMapping(value = "insert")
    public void GHformworktTask() throws  Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        HighformworkData formworkData=new HighformworkData();
        formworkData.setCreation("2020-03-25");
        List<HighformworkData> hjDeeppitDataList = highformworkDataService.selectHighformworkDataList(formworkData);
        for(HighformworkData highformworkData:hjDeeppitDataList){
            HighformworkData high=new HighformworkData();
            beforeTime.add(Calendar.MINUTE, 1);//
            String time=sdf.format(beforeTime.getTime());//当前时间
            System.out.println(time);
            high.setStructuresId(highformworkData.getStructuresId());
            high.setDisplayId(highformworkData.getDisplayId());
            high.setFactorId(highformworkData.getFactorId());
            high.setForce(highformworkData.getForce());
            high.setDisplacement(highformworkData.getDisplacement());
            high.setSubside(highformworkData.getSubside());
            high.setTiltX(highformworkData.getTiltX());
            high.setTiltY(highformworkData.getTiltY());
            high.setReservedO(highformworkData.getReservedO());
            high.setReservedT(highformworkData.getReservedT());
            high.setCreation(time);
            highformworkDataService.insertHighformworkData(high);
        }
    }
}
