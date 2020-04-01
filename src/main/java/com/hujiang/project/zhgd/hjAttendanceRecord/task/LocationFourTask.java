package com.hujiang.project.zhgd.hjAttendanceRecord.task;

import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Vehicle;
import com.hujiang.project.zhgd.hjZhgdVehicle.service.IVehicleService;
import com.hujiang.project.zhgd.sbEquipmentRecord.domain.SbEquipmentRecord;
import com.hujiang.project.zhgd.sbEquipmentRecord.service.ISbEquipmentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
public class LocationFourTask extends AutoTaskBase {
    private Logger logger = Logger.getLogger(LocationFourTask.class.getName());

    @Autowired
    private ISbEquipmentRecordService sbEquipmentRecordService;

    @Scheduled(cron="0 0 4 * * ?")
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
     * 每天给项目4添加定位数据
     */
    public void addBB() throws  Exception{

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time2=sdf.format(new Date());
        SbEquipmentRecord se=new SbEquipmentRecord();
        se.setAddress("科伦特");
        se.setImei("661804021000845100101");
        se.setWatchDate(sdf.parse("2020-03-26"));
        List<SbEquipmentRecord> eList = sbEquipmentRecordService.selectSbEquipmentRecordList(se);
        SbEquipmentRecord s=null;
        for(int i=0;i<eList.size();i++){
            s=eList.get(i);
            s.setId(null);
            String time=sdf2.format(s.getWatchDate());

            s.setWatchDate(sdf2.parse(time2+time.substring(time.indexOf(" "))));
            sbEquipmentRecordService.insertSbEquipmentRecord(s);
        }






    }
}
