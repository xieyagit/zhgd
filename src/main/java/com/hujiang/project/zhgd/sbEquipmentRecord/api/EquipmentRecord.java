package com.hujiang.project.zhgd.sbEquipmentRecord.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbDeviceimei.service.ISbDeviceimeiService;
import com.hujiang.project.zhgd.sbEquipmentRecord.domain.SbEquipmentRecord;
import com.hujiang.project.zhgd.sbEquipmentRecord.service.ISbEquipmentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/provider/equipmentRecordApi")
public class EquipmentRecord {
    @Autowired
    private ISbEquipmentRecordService equipmentRecordService;
    @Autowired
    private ISbDeviceimeiService deviceimeiService;

    @PostMapping("/addEquipmentRecord")
    public Map<String,Object> addEquipmentRecord(@RequestBody String json){
        JSONObject jsonResult = JSONObject.parseObject(json);
        SbEquipmentRecord sbEquipmentRecord = JSONObject.parseObject(jsonResult.toJSONString(), SbEquipmentRecord.class);
        return this.insertEquipmentRecord(sbEquipmentRecord);
    }

    private Map<String,Object> insertEquipmentRecord(SbEquipmentRecord sbEquipmentRecord){
        Map<String,Object> map = new HashMap<>();
        List<String> imeiList =deviceimeiService.selectDeviceimeiListAll(); //所有imei（设备编号）
        if (imeiList.contains(sbEquipmentRecord.getImei())) {
            if( equipmentRecordService.insertSbEquipmentRecord(sbEquipmentRecord)>0){
                map.put("msg","成功");
                map.put("code",0);
            }
        }else {
            map.put("msg","失败");
            map.put("code",-1);
        }
        return map;
    }
}
