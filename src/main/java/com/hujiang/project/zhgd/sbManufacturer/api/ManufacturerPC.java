package com.hujiang.project.zhgd.sbManufacturer.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbManufacturer.domain.SbManufacturer;
import com.hujiang.project.zhgd.sbManufacturer.service.ISbManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/provider/manufacturer" ,method =  RequestMethod.POST)
public class ManufacturerPC {

    @Autowired
    private ISbManufacturerService iSbManufacturerService;

    @RequestMapping("/list")
    public JSONObject all(SbManufacturer sbManufacturer){
        JSONArray jsonArray = new JSONArray();
        List<SbManufacturer> list = iSbManufacturerService.selectSbManufacturerList(sbManufacturer);
        for (SbManufacturer manufacturer : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("manufacturerName", manufacturer.getManufacturerName());
            jsonObject.put("id",manufacturer.getId());
            jsonArray.add(jsonObject);
        }
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }

}
