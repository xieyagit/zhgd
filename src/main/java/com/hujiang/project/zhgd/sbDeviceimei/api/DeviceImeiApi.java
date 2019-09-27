package com.hujiang.project.zhgd.sbDeviceimei.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbDeviceimei.domain.SbDeviceimei;
import com.hujiang.project.zhgd.sbDeviceimei.service.ISbDeviceimeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/provider/deviceImeiApi",method = RequestMethod.POST)
public class DeviceImeiApi {
    @Autowired
    private ISbDeviceimeiService deviceimeiService;

    @PostMapping("/addDeviceImei")
    public JSONObject addDeviceImei(@RequestBody SbDeviceimei sbDeviceimei){
    JSONObject jsonObject = new JSONObject();
    if(deviceimeiService.insertSbDeviceimei(sbDeviceimei)>0){
        jsonObject.put("msg","成功");
        jsonObject.put("code",0);
    }else {
        jsonObject.put("msg","失败");
        jsonObject.put("code",-1);
    }
    return jsonObject;
    }
}
