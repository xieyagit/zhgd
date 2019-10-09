package com.hujiang.project.zhgd.sbCurrentTemperature.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbCurrentTemperature.service.ISbCurrentTemperatureService;
import com.hujiang.project.zhgd.sbDoorLock.domain.SbDoorLock;
import com.hujiang.project.zhgd.sbDoorLock.service.ISbDoorLockService;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import com.hujiang.project.zhgd.sbProjectElectricityBox.service.ISbProjectElectricityBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@RestController
@RequestMapping(value = "/provider/appCurrentTemperature",method = RequestMethod.POST)
public class appCurrentTemperatureApi extends BaseController {
    Logger logger = Logger.getLogger(appCurrentTemperatureApi.class.getName());

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
    public JSONObject getEquipmentInformation(@RequestParam(value = "electricityBoxId")String electricityBoxId)
                                              throws Exception{
        //获取设备最新数据

        SbCurrentTemperature sbCurrentTemperature = sbCurrentTemperatureService.getSbCurrentTemperatureToOne(electricityBoxId);
        JSONObject result =new JSONObject();
        JSONObject o =null;
        JSONObject r = new JSONObject();
        if(sbCurrentTemperature!=null){
            //电箱漏电（kwh）,电箱温度
            o = (JSONObject)JSONObject.toJSON(sbCurrentTemperature);
            //时间转毫秒
            long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sbCurrentTemperature.getTm()).getTime();
            long l = System.currentTimeMillis();//系统毫秒
            //电箱运行状态
            if((time+3600000)>l){         //监测时间加一个小时大于当前时间
                o.put("runningStatus",true);//设备正常

            }else{
                o.put("runningStatus",false);//设备异常
            }
            o.put("doorLock",Integer.parseInt(sbCurrentTemperature.getDoorType())==0?"关":"开");//门锁状态
            result.put("data",o);
            result.put("msg","查询成功");
            result.put("code",0);
        }else{
            r.put("runningStatus",false);//设备异常
            r.put("doorLock","开");//门锁状态异常
            result.put("data",r);
            result.put("msg","查询成功");
            result.put("code",0);
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
    public JSONObject list(@RequestParam(value = "sn")String sn,
                           @RequestParam(value = "dateTime",required = false)String dateTime) throws ParseException {
        startPage();
        JSONObject result = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("sn",sn);
        map.put("dateTime",dateTime);
        //获取分页数据
        List<SbCurrentTemperature> list = sbCurrentTemperatureService.selectSbCurrentTemperatureByTimes(map);
        if(list!=null && list.size()>0){
            JSONArray array = new JSONArray();
                for (SbCurrentTemperature sbCurrentTemperature:list){
                    result.put("msg","查询成功");
                    result.put("code",0);
                    JSONObject maps = new JSONObject();
                    maps.put("time",sbCurrentTemperature.getTm());
                    long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sbCurrentTemperature.getTm()).getTime();
                    long l = System.currentTimeMillis();//系统毫秒
                    //电箱运行状态
                    if((time+3600000)>l){         //监测时间加一个小时大于当前时间
                        maps.put("runningStatus",true);//设备正常
                    }else{
                        maps.put("runningStatus",false);//设备异常
                    }
                    maps.put("current",sbCurrentTemperature.getCurrent());
                    maps.put("awarm",sbCurrentTemperature.getAwarm());
                    maps.put("bwarm",sbCurrentTemperature.getBwarm());
                    maps.put("cwarm",sbCurrentTemperature.getCwarm());
                    maps.put("nwarm",sbCurrentTemperature.getNwarm());
                    array.add(maps);
                }
           result.put("data",array);
        }else{
            result.put("msg","暂无数据");
            result.put("code",-1);
            result.put("data",null);
        }
        return result;
    }



}
