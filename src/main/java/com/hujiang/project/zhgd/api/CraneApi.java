package com.hujiang.project.zhgd.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.deye.DeyeCraneApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/zhgd")
public class CraneApi {
    @Autowired
    private DeyeCraneApi client;
    /**
     * 塔吊注册帧
     */
    @RequestMapping(value="/LoginDataCrane",method = RequestMethod.POST)
    public JSONObject loginDataCrane(@RequestBody String json)throws Exception {
        return client.loginDataCrane(json);
    }

    /**
     * 上报基础信息
     */
    @RequestMapping(value="/BaseDataCrane",method = RequestMethod.POST)
    public JSONObject baseDataCrane(@RequestBody String json)throws Exception{
        return client.baseDataCrane(json);
    }

    /**
     * 接收德业塔吊设备实时信息
     */
    @RequestMapping(value="/RealtimeDataCrane",method = RequestMethod.POST)
    public JSONObject setCraneData(@RequestBody String json)throws Exception{
        return client.setCraneData(json);
    }

    /**
     * 2.4上报深圳版塔机报警数据
     */
    @RequestMapping(value="/ShenZhenAlarmDataCrane",method = RequestMethod.POST)
    public JSONObject shenZhenAlarmDataCrane(@RequestBody String json)throws Exception{
        return client.shenZhenAlarmDataCrane(json);
    }

    /**
     * 2.5上报GPS定位数据
     */

    @RequestMapping(value="/GpsData",method = RequestMethod.POST)
    public JSONObject gpsData(@RequestBody String json)throws Exception{
        return client.gpsData(json);
    }
    /**
     * 2.6上报设备运行时长
     */
    @RequestMapping(value="/RuntimeData",method = RequestMethod.POST)
    public JSONObject runtimeData(@RequestBody String json)throws Exception{
        return client.runtimeData(json);
    }

    /**
     * 2.7上报塔机工作循环数据
     */
    @RequestMapping(value="/WorkDataCrane",method = RequestMethod.POST)
    public JSONObject workDataCrane(@RequestBody String json)throws Exception{
        return client.workDataCrane(json);
    }
    /**
     * 2.8上报塔机报警数据
     */
    @RequestMapping(value="/AlarmDataCrane",method = RequestMethod.POST)
    public JSONObject alarmDataCrane(@RequestBody String json)throws Exception{
        return client.alarmDataCrane(json);
    }
    /**
     * 2.9上报司机打卡记录信息
     */

    @RequestMapping(value="/OperatorRecord",method = RequestMethod.POST)
    public JSONObject operatorRecord(@RequestBody String json)throws Exception{
        return client.operatorRecord(json);
    }

    /**
     *22.6上报升降机工作循环数据
     */
    @RequestMapping(value="/WorkDataElevator",method = RequestMethod.POST)
    public JSONObject workDataElevator(@RequestBody String json)throws Exception{
        return client.workDataElevator(json);
    }
    /**
     * 接收升降机实时信息
     */
    @RequestMapping(value="/RealtimeDataElevator",method = RequestMethod.POST)
    public JSONObject realtimeDataElevator(@RequestBody String json)throws Exception{
        return client.realtimeDataElevator(json);
    }
    /**
     *上报升降机基础参数
     */

    @RequestMapping(value="/BaseDataElevator",method = RequestMethod.POST)
    public JSONObject baseDataElevator(@RequestBody String json)throws Exception{
        return client.baseDataElevator(json);
    }
    /**
     * 升降机注册帧
     */
    @RequestMapping(value="/LoginDataElevator",method = RequestMethod.POST)
    public JSONObject loginDataElevator(@RequestBody String json)throws Exception{
        return client.loginDataElevator(json);
    }

}
