package com.hujiang.project.zhgd.sbArea.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbArea.domain.SbArea;
import com.hujiang.project.zhgd.sbArea.service.ISbAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/provider/areaApi",method = RequestMethod.POST)
public class SbAreaApi {
    @Autowired
    private ISbAreaService areaService;

    @PostMapping("/addArea")
    public JSONObject addArea(@RequestBody SbArea sbArea){
        JSONObject jsonObject = new JSONObject();
        JSONObject json = new JSONObject();
        if(areaService.insertSbArea(sbArea)>0){
            json.put("id",sbArea.getId());
            jsonObject.put("msg","成功");
            jsonObject.put("code",0);
            jsonObject.put("data",json);
        }else {
            jsonObject.put("msg","失败");
            jsonObject.put("code",-1);
        }
        return jsonObject;
    }
}
