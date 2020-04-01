package com.hujiang.project.zhgd.hjAttendanceRecord.task;

import com.alibaba.fastjson.JSON;
import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjZhgdPkcount.service.IHjZhgdPkcountService;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Vehicle;
import com.hujiang.project.zhgd.hjZhgdVehicle.service.IVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class VehicleFourTask extends AutoTaskBase {
    private Logger logger = Logger.getLogger(VehicleFourTask.class.getName());

    @Autowired
    private IVehicleService vehicleService;

    @Scheduled(cron="0 0 5 * * ?")
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

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.DATE, -1);//
        String time=sdf.format(beforeTime.getTime());//当前时间
        Vehicle v=new Vehicle();
        v.setDeptID(20191211);
        v.setLiftTime(time);
//        System.out.println(JSON.toJSONString(v));
        String time2=sdf.format(new Date());
       List<Vehicle> vList= vehicleService.selectVehicleListTwo(v);
        Vehicle c=null;
        for(int i=0;i<vList.size();i++){
            c=vList.get(i);
            c.setId(null);
            String b=c.getLiftTime();
            c.setLiftTime(time2+b.substring(b.indexOf(" ")));
            vehicleService.insertVehicle(c);
        }





    }
}
