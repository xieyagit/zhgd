package com.hujiang.project.huayuan.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjDeviceTemperature.domain.HjDeviceTemperature;
import com.hujiang.project.zhgd.hjDeviceTemperature.service.IHjDeviceTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/hy")
public class TemperatureApi {
    private Logger logger = Logger.getLogger(TemperatureApi.class.getName());
    @Autowired
    private IHjDeviceTemperatureService hjDeviceTemperatureService;
@Autowired
private IHjAttendanceRecordService hjAttendanceRecordService;
    @PostMapping(value = "/temperature")
    public String temperature(@RequestBody String json)throws Exception{
        logger.info("=====体温数据：" + json);
//        Thread.sleep(2000);
        JSONObject j=JSONObject.parseObject(json);
        String temperatureSn=j.getString("sn");
        HjDeviceTemperature hjDeviceTemperature=new HjDeviceTemperature();
        hjDeviceTemperature.setTemperatureSn(temperatureSn);
        List<HjDeviceTemperature> tList=hjDeviceTemperatureService.selectHjDeviceTemperatureList(hjDeviceTemperature);
        if(tList.size()>0){
            String deviceSn=tList.get(0).getDeviceSn();
            String time=j.getString("time");
            HjAttendanceRecord hjAttendanceRecord=new HjAttendanceRecord();
            hjAttendanceRecord.setDeviceSn(deviceSn);
            hjAttendanceRecord.setPassedTime(time);
            hjAttendanceRecord.setAlarm(j.getString("alarm"));
            hjAttendanceRecord.setTemperature(j.getString("temperature"));
            hjAttendanceRecord.setProjectId(tList.get(0).getPid());
            hjAttendanceRecordService.updateHjAttendanceRecordTwo(hjAttendanceRecord);
        }


        JSONObject result=new JSONObject();
        result.put("code",0);
        result.put("msg","成功");
        return result.toJSONString();
    }

}
