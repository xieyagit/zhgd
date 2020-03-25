package com.hujiang.project.zhgd.sbDustEmission.task;

import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.service.ISbDustEmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
//@RestController
//@RequestMapping(value = "/provider/taskDustEmission",method = RequestMethod.POST)
public class DustEmission extends AutoTaskBase {
    @Autowired
    private ISbDustEmissionService dustEmissionService;
    @Scheduled(cron="0 0 6 * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    dustEmission();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }
    /**
     * 每天给项目4添加扬尘数据
     */
//    @PostMapping(value = "insert")
    public void dustEmission() throws  Exception{

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        Map<String,Object> map = new HashMap<>();
        map.put("sn","MjAxOTAzMjAwMTEwMDAwNw==");
        map.put("dateTime","2020-03-25");
        //获取分页数据
        List<SbDustEmission> sbDustEmissions = dustEmissionService.selectSbDustEmissionByTimes(map);

        for(SbDustEmission dust:sbDustEmissions){
            SbDustEmission sb=new SbDustEmission();
            beforeTime.add(Calendar.MINUTE, 1);//
            String time=sdf.format(beforeTime.getTime());//当前时间
            System.out.println(time);
            sb.setSn("MjAxOTAzMjAwMTEwMDAwNw==");
            sb.setDate(time);
            sb.setPm25(dust.getPm25());
            sb.setPm10(dust.getPm10());
            sb.setTsp(dust.getTsp());
            sb.setNoise(dust.getNoise());
            sb.setTemperature(dust.getTemperature());
            sb.setHumidity(dust.getHumidity());
            sb.setWindSpeed(dust.getWindSpeed());
            sb.setWinddirection(dust.getWinddirection());
            sb.setWindPower(dust.getWindPower());
            dustEmissionService.insertSbDustEmission(sb);
        }


    }
}
