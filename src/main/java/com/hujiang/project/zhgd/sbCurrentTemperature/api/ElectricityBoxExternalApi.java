package com.hujiang.project.zhgd.sbCurrentTemperature.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbCurrentTemperature.service.ISbCurrentTemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/provider/electricityBoxExternal")
public class ElectricityBoxExternalApi {

    @Autowired
    private ISbCurrentTemperatureService currentTemperatureService;

    @PostMapping("/addElectricityBox")
    public Map<String,Object> addElectricityBox(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        SbCurrentTemperature sbCurrentTemperature = JSONObject.parseObject(jsonResult.toJSONString(), SbCurrentTemperature.class);
        int result = currentTemperatureService.insertSbCurrentTemperature(sbCurrentTemperature);
        if(result > 0){
            map.put("msg","成功");
            map.put("code",0);
        }else {
            map.put("msg","失败");
            map.put("code",-1);
        }
        return map;
    }
}
