package com.hujiang.project.zhgd.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbUnloaderRegistration.api.UnloaderRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/zhgd",method = RequestMethod.POST)
public class zhgd {
    @Autowired
    private UnloaderRegistration unloaderRegistration;
    /**  注册信息 */
    @PostMapping("/LoginDataUnloadingPlatform")
    public JSONObject LoginDataUnloadingPlatform(@RequestBody String json){

        return unloaderRegistration.LoginDataUnloadingPlatform(json);
    }

    /** 基础参数 */
    @PostMapping("/BaseDataUnloadingPlatform")
    public JSONObject BaseDataUnloadingPlatform(@RequestBody String json){
        return unloaderRegistration.BaseDataUnloadingPlatform(json);
    }

    /** 实时数据 */
    @PostMapping("/RealtimeDataUnloadingPlatform")
    public JSONObject RealtimeDataUnloadingPlatform(@RequestBody String json){
        return unloaderRegistration.RealtimeDataUnloadingPlatform(json);
    }

    /** 报警时段数据 */
    @PostMapping("/AlarmDataUnloadingPlatform")
    public JSONObject AlarmDataUnloadingPlatform(@RequestBody String json){
        return unloaderRegistration.AlarmDataUnloadingPlatform(json);
    }
    /** 报警时刻数据*/
    @PostMapping("/AlarmStartUnloadingPlatform")
    public JSONObject AlarmStartUnloadingPlatform(@RequestBody String json){
        return unloaderRegistration.AlarmStartUnloadingPlatform(json);
    }
}
