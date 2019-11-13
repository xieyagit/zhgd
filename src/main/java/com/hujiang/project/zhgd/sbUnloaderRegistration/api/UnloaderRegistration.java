package com.hujiang.project.zhgd.sbUnloaderRegistration.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbUnloaderAlarminformation.domain.SbUnloaderAlarminformation;
import com.hujiang.project.zhgd.sbUnloaderAlarminformation.service.ISbUnloaderAlarminformationService;
import com.hujiang.project.zhgd.sbUnloaderAlarmtime.domain.SbUnloaderAlarmtime;
import com.hujiang.project.zhgd.sbUnloaderAlarmtime.service.ISbUnloaderAlarmtimeService;
import com.hujiang.project.zhgd.sbUnloaderLocatordata.service.ISbUnloaderLocatordataService;
import com.hujiang.project.zhgd.sbUnloaderParameter.domain.SbUnloaderParameter;
import com.hujiang.project.zhgd.sbUnloaderParameter.service.ISbUnloaderParameterService;
import com.hujiang.project.zhgd.sbUnloaderRealtime.domain.SbUnloaderRealtime;
import com.hujiang.project.zhgd.sbUnloaderRealtime.service.ISbUnloaderRealtimeService;
import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.SbUnloaderRegistration;
import com.hujiang.project.zhgd.sbUnloaderRegistration.service.ISbUnloaderRegistrationService;
import com.hujiang.project.zhgd.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 卸料
 */
@RestController
@RequestMapping(value = "/provider/zhgd",method = RequestMethod.POST)
public class UnloaderRegistration {

    //卸料注册信息
    @Autowired
    private ISbUnloaderRegistrationService registrationService;
    //卸料基础参数
    @Autowired
    private ISbUnloaderParameterService parameterService;
    //卸料实时数据
    @Autowired
    private ISbUnloaderRealtimeService realtimeService;
    //卸料报警时段数据
    @Autowired
    private ISbUnloaderAlarminformationService alarminformationService;
    //卸料报警时刻数据
    @Autowired
    private ISbUnloaderAlarmtimeService alarmtimeService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 注册信息
     * @param json
     * @return
     */
    @PostMapping(value = "/LoginDataUnloadingPlatform")
    public JSONObject LoginDataUnloadingPlatform(@RequestBody String json){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonData = JSONObject.parseObject(json);
        SbUnloaderRegistration sbUnloaderRegistration = new SbUnloaderRegistration();
        sbUnloaderRegistration.setHxzId(jsonData.getString("HxzId"));
        List<SbUnloaderRegistration> sbUnloaderRegistrationList =  registrationService.selectSbUnloaderRegistrationList(sbUnloaderRegistration);
        if(sbUnloaderRegistrationList.size()<=0){
            sbUnloaderRegistration.setHxzFactory(jsonData.getString("HxzFactory"));                 //黑匣子厂家
            sbUnloaderRegistration.setDeviceNo(Tools.encodeToMD5s(jsonData.getString("HxzId")));    //黑匣子编号
            sbUnloaderRegistration.setHardwareVer(jsonData.getString("HardwareVer"));   //硬件版本号
            sbUnloaderRegistration.setSoftwareVer(jsonData.getString("SoftwareVer"));   //软件版本号
            sbUnloaderRegistration.setSimCardNo(jsonData.getString("SimCardNo"));       //SIM卡号
            registrationService.insertSbUnloaderRegistration(sbUnloaderRegistration);
        }
        JSONObject parameters = new JSONObject();
        parameters.put("HxzFactory",jsonData.getString("HxzFactory"));    //黑匣子厂家
        parameters.put("HxzId",jsonData.getString("HxzId"));               //黑匣子编号
        parameters.put("RecordId",jsonData.getString("HxzId"));            //平台备案编号
        parameters.put("ServerTime",dateFormat.format(new Date()));            //平台时间
        parameters.put("HeartBeatInterval","30");                               //心跳间隔
        parameters.put("WorkInterval","10");                                    //工作期间实时数据上传间隔
        parameters.put("NoWorkInterval","60");                                  //非工作期间实时数据上传间隔
        parameters.put("ErrorDelay","90");                                       //GPRS掉线后重连间隔
        parameters.put("WeightSetError","0");                                   //屏蔽载重监测功能0：未屏蔽1：屏蔽
        parameters.put("ObliguityXSetError","0");                               //屏蔽倾角X监测功能0：未屏蔽1：屏蔽
        parameters.put("ObliguityYSetError","0");                               //屏蔽倾角Y监测功能0：未屏蔽1：屏蔽
        parameters.put("GpsSetError","0");                                       //屏蔽GPS定位功能
        parameters.put("ServerIp","47.106.71.3");                               //服务器IP地址
        parameters.put("ServerPort","8080");                                    //服务器端口号
        jsonObject.put("msg","LoginDataUnloadingPlatform");
        jsonObject.put("data",parameters);
        return jsonObject;
    }

    /** 基础参数 */
    @PostMapping(value = "/BaseDataUnloadingPlatform")
    public JSONObject BaseDataUnloadingPlatform(@RequestBody String json){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonData = JSONObject.parseObject(json);  SbUnloaderParameter sbUnloaderParameter = new SbUnloaderParameter();
        sbUnloaderParameter.setHxzId(jsonData.getString("HxzId"));
        List<SbUnloaderParameter> sbUnloaderParameterList = parameterService.selectSbUnloaderParameterList(sbUnloaderParameter);
        if(sbUnloaderParameterList.size() <= 0){
            sbUnloaderParameter.setDeviceNo(Tools.encodeToMD5s(jsonData.getString("HxzId")));    //黑匣子厂家
            sbUnloaderParameter.setUnloadingPlatformType(jsonData.getString("UnloadingPlatformType"));//升降机类型
            sbUnloaderParameter.setWeightSet(jsonData.getString("WeightSet"));      //配置载重检测功能
            sbUnloaderParameter.setObliguityXSet(jsonData.getString("ObliguityXSet"));//配置倾角X检测功能
            sbUnloaderParameter.setObliguityYSet(jsonData.getString("ObliguityYSet"));//配置倾角Y检测功能
            sbUnloaderParameter.setGpsSet(jsonData.getString("GpsSet"));               //配置GPS定位功能
            sbUnloaderParameter.setWeightPreAlarmValue(jsonData.getFloat("WeightPreAlarmValue"));//载重预警阈值
            sbUnloaderParameter.setObliguityXPreAlarmValue(jsonData.getFloat("ObliguityXPreAlarmValue"));//倾角X预警阈值
            sbUnloaderParameter.setObliguityYPreAlarmValue(jsonData.getFloat("ObliguityYPreAlarmValue"));//倾角Y预警阈值
            sbUnloaderParameter.setBatteryPreAlarmValue(jsonData.getFloat("BatteryPreAlarmValue")); //电池电量预警阈值%
            sbUnloaderParameter.setWeightAlarmValue(jsonData.getFloat("WeightAlarmValue"));         //载重报警阈值
            sbUnloaderParameter.setObliguityXAlarmValue(jsonData.getFloat("ObliguityXAlarmValue"));//倾角X报警阈值
            sbUnloaderParameter.setObliguityYAlarmValue(jsonData.getFloat("ObliguityYAlarmValue"));//倾角Y报警阈值
            sbUnloaderParameter.setBatteryAlarmValue(jsonData.getFloat("BatteryAlarmValue"));       //电池电量报警阈值%
            parameterService.insertSbUnloaderParameter(sbUnloaderParameter);
        }
        jsonObject.put("cmd","BaseDataUnloadingPlatform");
        jsonObject.put("data","{}");
        return jsonObject;
    }

    /** 实时数据 */
    @PostMapping(value = "/RealtimeDataUnloadingPlatform")
    public JSONObject RealtimeDataUnloadingPlatform(@RequestBody String json){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonData = JSONObject.parseObject(json);
        SbUnloaderRegistration sbUnloaderRegistration = new SbUnloaderRegistration();
        sbUnloaderRegistration.setHxzId(jsonData.getString("HxzId"));
        List<SbUnloaderRegistration> sbUnloaderRegistrationList =  registrationService.selectSbUnloaderRegistrationList(sbUnloaderRegistration);
        //编号是否注册
        if(sbUnloaderRegistrationList.size()>0){
            SbUnloaderRealtime sbUnloaderRealtime = new SbUnloaderRealtime();
            sbUnloaderRealtime.setHxzId(jsonData.getString("HxzId"));
            sbUnloaderRealtime.setDeviceNo(Tools.encodeToMD5s(jsonData.getString("HxzId")));
            sbUnloaderRealtime.setRTime(jsonData.getString("RTime"));       //采集时间
            sbUnloaderRealtime.setWeight(jsonData.getFloat("Weight"));      //载重
            sbUnloaderRealtime.setWeightPercent(jsonData.getFloat("WeightPercent"));//载重百分比
            sbUnloaderRealtime.setObliguityX(jsonData.getFloat("ObliguityX"));      //倾角X
            sbUnloaderRealtime.setObliguityY(jsonData.getFloat("ObliguityY"));      //倾角Y
            sbUnloaderRealtime.setObliguity(jsonData.getFloat("Obliguity"));         //合成倾角
            sbUnloaderRealtime.setBatteryPercent(jsonData.getFloat("BatteryPercent"));  //电量百分比
            sbUnloaderRealtime.setWeightStatus(jsonData.getInteger("WeightStatus"));    //载重状态
            sbUnloaderRealtime.setObliguityXStatus(jsonData.getInteger("ObliguityXStatus"));    //倾角X状态
            sbUnloaderRealtime.setObliguityYStatus(jsonData.getInteger("ObliguityYStatus"));    //倾角Y状态
            sbUnloaderRealtime.setBatteryStatus(jsonData.getInteger("BatteryStatus"));   //倾角Y状态
            realtimeService.insertSbUnloaderRealtime(sbUnloaderRealtime);
        }
        jsonObject.put("cmd","RealtimeDataUnloadingPlatform");
        jsonObject.put("data","{}");
        return jsonObject;
    }

    /** 报警时段数据 */
    @PostMapping(value = "/AlarmDataUnloadingPlatform")
    public JSONObject AlarmDataUnloadingPlatform(@RequestBody String json){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonData = JSONObject.parseObject(json);
        SbUnloaderRegistration sbUnloaderRegistration = new SbUnloaderRegistration();
        sbUnloaderRegistration.setHxzId(jsonData.getString("HxzId"));
        List<SbUnloaderRegistration> sbUnloaderRegistrationList =  registrationService.selectSbUnloaderRegistrationList(sbUnloaderRegistration);
        //编号是否注册
        if(sbUnloaderRegistrationList.size()>0) {
            SbUnloaderAlarminformation sbUnloaderAlarminformation = new SbUnloaderAlarminformation();
            sbUnloaderAlarminformation.setHxzId(jsonData.getString("HxzId"));
            sbUnloaderAlarminformation.setDeviceNo(Tools.encodeToMD5s(jsonData.getString("HxzId")));
            sbUnloaderAlarminformation.setStartTime(jsonData.getString("StartTime"));       //报警开始时间
            sbUnloaderAlarminformation.setEndTime(jsonData.getString("EndTime"));           //报警结束时间
            sbUnloaderAlarminformation.setAlarmType(jsonData.getInteger("AlarmType"));      //报警类型
            sbUnloaderAlarminformation.setMaxValue(jsonData.getFloat("MaxValue"));          //最大值
            sbUnloaderAlarminformation.setMaxValuePercent(jsonData.getFloat("MaxValuePercent"));    //最大值百分比
            alarminformationService.insertSbUnloaderAlarminformation(sbUnloaderAlarminformation);

        }
        jsonObject.put("cmd","AlarmDataUnloadingPlatform");
        jsonObject.put("data","{}");
        return jsonObject;
    }
    /** 报警时刻数据*/
    @PostMapping(value = "/AlarmStartUnloadingPlatform")
    public JSONObject AlarmStartUnloadingPlatform(@RequestBody String json){
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonData = JSONObject.parseObject(json);
        SbUnloaderRegistration sbUnloaderRegistration = new SbUnloaderRegistration();
        sbUnloaderRegistration.setHxzId(jsonData.getString("HxzId"));
        List<SbUnloaderRegistration> sbUnloaderRegistrationList =  registrationService.selectSbUnloaderRegistrationList(sbUnloaderRegistration);
        //编号是否注册
        if(sbUnloaderRegistrationList.size()>0) {
            SbUnloaderAlarmtime sbUnloaderAlarmtime = new SbUnloaderAlarmtime();
            sbUnloaderAlarmtime.setHxzId(jsonData.getString("HxzId"));
            sbUnloaderAlarmtime.setDeviceNo(Tools.encodeToMD5s(jsonData.getString("HxzId")));
            sbUnloaderAlarmtime.setStartTime(jsonData.getString("StartTime"));
            sbUnloaderAlarmtime.setAlarmType(jsonData.getInteger("AlarmType"));
            sbUnloaderAlarmtime.setAlarmValue(jsonData.getFloat("AlarmValue"));
            alarmtimeService.insertSbUnloaderAlarmtime(sbUnloaderAlarmtime);
        }
        jsonObject.put("cmd","AlarmStartUnloadingPlatform");
        jsonObject.put("data","{}");
        return jsonObject;
    }
}
