package com.hujiang.project.zhgd.sbCurrentTemperature.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbCurrentTemperature.service.ISbCurrentTemperatureService;
import com.hujiang.project.zhgd.sbDoorLock.service.ISbDoorLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogManager;
import java.util.logging.Logger;


@RestController
@RequestMapping(value = "/provider/appCurrentTemperature", method = RequestMethod.POST)
public class AppCurrentTemperatureApi extends BaseController {
    Logger logger = LogManager.getLogManager().getLogger(AppCurrentTemperatureApi.class.getName());

    @Autowired
    private ISbCurrentTemperatureService sbCurrentTemperatureService;
    @Autowired
    private ISbDoorLockService sbDoorLockService;


    /**
     * 智能电箱界面数据
     * @param electricityBoxId 设备编号
     * @return
     */
    @PostMapping(value = "getEquipmentInformation")
    public JSONObject GetEquipmentInformation(@RequestParam(value = "electricityBoxId") String electricityBoxId)
            throws Exception {
        //获取设备最新数据
        SbCurrentTemperature sbCurrentTemperature = sbCurrentTemperatureService.getSbCurrentTemperatureToOne(electricityBoxId);
//        logger.info("sbCurrentTemperature start-------------------------------->");
        JSONObject result = new JSONObject();
        JSONObject o = null;
        JSONObject r = new JSONObject();
        if (sbCurrentTemperature != null) {
//            logger.info("sbCurrentTemperature not null-------------------------------->");
            //电箱漏电（kwh）,电箱温度
            o = (JSONObject) JSONObject.toJSON(sbCurrentTemperature);
            //时间转毫秒
            long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sbCurrentTemperature.getTm()).getTime();
            long l = System.currentTimeMillis();//系统毫秒
            //电箱运行状态
            if ((time + 3600000) > l) {         //监测时间加一个小时大于当前时间
                o.put("runningStatus", true);//设备正常

            } else {
                o.put("runningStatus", false);//设备异常
            }
//            logger.info("sbCurrentTemperature getDoorType-------------------------------->" + sbCurrentTemperature.getDoorType());
            o.put("doorLock", "关");//门锁状态

            if (sbCurrentTemperature.getDoorType() != null && !"".equals(sbCurrentTemperature.getDoorType())) {
                if ("1".equals(sbCurrentTemperature.getDoorType())) {
                    logger.info("sbCurrentTemperature getDoorType-------------------------------->开");
                    o.put("doorLock", "开");//门锁状态
                }
            }
//            o.put("doorLock",Integer.parseInt(sbCurrentTemperature.getDoorType())==0?"关":"开");//门锁状态
            result.put("data", o);
            result.put("msg", "查询成功");
            result.put("code", 0);
        } else {
            r.put("runningStatus", false);//设备异常
            r.put("doorLock", "开");//门锁状态异常
            result.put("data", r);
            result.put("msg", "查询成功");
            result.put("code", 0);
        }

//        //箱门开关记录
//        SbDoorLock sbDoorLock = sbDoorLockService.selectSbDoorLockToOne(electricityBoxId);
//        if(sbDoorLock!=null){
//            o.put("doorLock",Integer.parseInt(sbDoorLock.getDoorType())==0?"关":"开");//门锁状态
//        }else{
//            o.put("doorLock","开");//门锁状态异常
//        }


        return result;
    }

    /**
     * 电箱数据分页
     * @return
     */
    @PostMapping("/list")
    public JSONObject list(@RequestParam(value = "sn") String sn,
                           @RequestParam(value = "dateTime", required = false) String dateTime,
                           @RequestParam(value = "endTime", required = false) String endTime
    ) throws ParseException {
        startPage();
        JSONObject result = new JSONObject();
        Map<String, Object> map = new HashMap<>();
        map.put("sn", sn);
        map.put("dateTime", dateTime);
        map.put("endTime", endTime);
        //获取分页数据
        List<SbCurrentTemperature> list = sbCurrentTemperatureService.selectSbCurrentTemperatureByTimes(map);
        if (list != null && list.size() > 0) {
            JSONArray array = new JSONArray();
            for (SbCurrentTemperature sbCurrentTemperature : list) {
                result.put("msg", "查询成功");
                result.put("code", 0);
                JSONObject maps = new JSONObject();
                maps.put("time", sbCurrentTemperature.getTm());

                BigDecimal current = sbCurrentTemperature.getCurrent();
                BigDecimal awarm = sbCurrentTemperature.getAwarm();
                BigDecimal bwarm = sbCurrentTemperature.getBwarm();
                BigDecimal cwarm = sbCurrentTemperature.getCwarm();
                BigDecimal nwarm = sbCurrentTemperature.getNwarm();

                //漏电量大于150 或ABCD有一个大于50   就不正常
                if (current.intValue() >= 150 || awarm.intValue() >= 50 || bwarm.intValue() >= 50 || cwarm.intValue() >= 50 || nwarm.intValue() >= 50) {
                    maps.put("runningStatus", false);
                } else {
                    //设备正常
                    maps.put("runningStatus", true);
                }
                maps.put("current", current);
                maps.put("awarm", awarm);
                maps.put("bwarm", bwarm);
                maps.put("cwarm", cwarm);
                maps.put("nwarm", nwarm);
                array.add(maps);
            }
            result.put("data", array);
        } else {
            result.put("msg", "暂无数据");
            result.put("code", -1);
            result.put("data", null);
        }
        return result;
    }


}
