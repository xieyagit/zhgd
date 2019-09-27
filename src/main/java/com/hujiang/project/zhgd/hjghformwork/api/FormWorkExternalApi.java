package com.hujiang.project.zhgd.hjghformwork.api;


import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.Md5Utils;
import com.hujiang.project.zhgd.hjDeeppit.domain.*;
import com.hujiang.project.zhgd.hjDeeppit.service.*;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjghformwork.domain.*;
import com.hujiang.project.zhgd.hjghformwork.service.*;
import com.hujiang.project.zhgd.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 高支模对外接口
 */
@RestController
@RequestMapping(value = "/provider/formWorkExternalApi",method = RequestMethod.POST)
public class FormWorkExternalApi {
    @Autowired
    private IHighformworkDisplayService displayService;
    @Autowired
    private IHighformworkFactorService factorService;
    @Autowired
    private IHighformworkGroupService groupService;
    @Autowired
    private IHighformworkDataService dataService;
    @Autowired
    private IHighformworkAlarmDataService alarmDataService;

    @PostMapping(value ="/addFormWorkDisplay")
        public Map<String,Object> addFormWorkDisplay(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        try {
            HighformworkDisplay highformworkDisplay = new HighformworkDisplay();
            highformworkDisplay.setId(jsonResult.getInteger("id"));
            highformworkDisplay.setReserved(jsonResult.getString("reserved"));
            highformworkDisplay.setChecked(jsonResult.getString("checked"));
            highformworkDisplay.setName(jsonResult.getString("name"));
            highformworkDisplay.setSupplier(jsonResult.getInteger("supplier"));
            highformworkDisplay.setTimeDivision(jsonResult.getString("timeDivision"));
            highformworkDisplay.setDisplayKey(EncryptionUtil.encryptByAES(jsonResult.getInteger("supplier").toString() +
                    jsonResult.getInteger("id").toString(), Md5Utils.hash(jsonResult.getInteger("supplier").toString())));
            int result = displayService.insertHighformworkDisplay(highformworkDisplay);
                if(result > 0){
                    map.put("msg","成功");
                    map.put("code",0);
                }else {
                    map.put("msg","失败");
                    map.put("code",-1);
                }


        }catch (Exception e){
            map.put("msg","参数错误");
            map.put("code",-1);
        }
        return map;
        }


    @PostMapping(value ="/addFormWorkFactor")
    public Map<String,Object> addFormWorkFactor(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        try {
            HighformworkFactor highformworkFactor = new HighformworkFactor();
            highformworkFactor.setId(jsonResult.getInteger("id"));
            highformworkFactor.setDistance(jsonResult.getString("distance"));
            highformworkFactor.setLabels(jsonResult.getString("labels"));
            highformworkFactor.setName(jsonResult.getString("name"));
            highformworkFactor.setPortrait(jsonResult.getString("portrait"));
            highformworkFactor.setReserved(jsonResult.getString("reserved"));
            highformworkFactor.setSupplier(jsonResult.getInteger("supplier"));
            highformworkFactor.setFactorKey(EncryptionUtil.encryptByAES(jsonResult.getInteger("supplier").toString() +
                    jsonResult.getInteger("id").toString(), Md5Utils.hash(jsonResult.getInteger("supplier").toString())));

            int result = factorService.insertHighformworkFactor(highformworkFactor);
            if(result > 0){
                map.put("msg","成功");
                map.put("code",0);
            }else {
                map.put("msg","失败");
                map.put("code",-1);
            }

        }catch (Exception e){
            map.put("msg","参数错误");
            map.put("code",-1);
        }
        return map;
    }


    @PostMapping(value = "/addFormWorkGroup")
    public Map<String,Object> addFormWorkGroup(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        try {
            HighformworkGroup highformworkGroup = new HighformworkGroup();
            highformworkGroup.setId(jsonResult.getInteger("id"));
            highformworkGroup.setReserved(jsonResult.getString("reserved"));
            highformworkGroup.setName(jsonResult.getString("name"));
            highformworkGroup.setSupplier(jsonResult.getInteger("supplier"));
            highformworkGroup.setType(jsonResult.getString("type"));
            highformworkGroup.setGroupKey(EncryptionUtil.encryptByAES(jsonResult.getInteger("supplier").toString() +
                    jsonResult.getInteger("id").toString(), Md5Utils.hash(jsonResult.getInteger("supplier").toString())));
            int result = groupService.insertHighformworkGroup(highformworkGroup);
            if(result > 0){
                map.put("msg","成功");
                map.put("code",0);
            }else {
                map.put("msg","失败");
                map.put("code",-1);
            }
        }catch (Exception e){
            map.put("msg","参数错误");
            map.put("code",-1);
        }

        return map;
    }

    @PostMapping(value = "/addFormWorkData")
    public Map<String,Object> addFormWorkData(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        try {
            HighformworkData highformworkData = JSONObject.parseObject(jsonResult.toJSONString(),HighformworkData.class);
            int result = dataService.insertHighformworkData(highformworkData);
            if(result > 0){
                map.put("msg","成功");
                map.put("code",0);
            }else {
                map.put("msg","失败");
                map.put("code",-1);
            }
        }catch (Exception e){
            map.put("msg","参数错误");
            map.put("code",-1);
        }

        return map;
    }

    @PostMapping(value = "/addFormWorkAlarmData")
    public Map<String,Object> addFormWorkAlarmData(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        try {
            HighformworkAlarmData highformworkAlarmData = new HighformworkAlarmData();
            highformworkAlarmData.setId(EncryptionUtil.encryptByAES(jsonResult.getInteger("supplier").toString()+
                    jsonResult.getInteger("id").toString(), Md5Utils.hash(jsonResult.getInteger("supplier").toString())));
            highformworkAlarmData.setEndTime(jsonResult.getString("endTime"));
            highformworkAlarmData.setSourceName(jsonResult.getString("sourceName"));
            highformworkAlarmData.setStructuresId(jsonResult.getInteger("structuresId"));
            highformworkAlarmData.setStructuresName(jsonResult.getString("structuresName"));
            highformworkAlarmData.setAlarmId(jsonResult.getString("alarmId"));
            highformworkAlarmData.setAlarmTypeCode(jsonResult.getString("alarmTypeCode"));
            highformworkAlarmData.setContent(jsonResult.getString("content"));
            highformworkAlarmData.setCount(jsonResult.getInteger("count"));
            highformworkAlarmData.setLevel(jsonResult.getInteger("level"));
            highformworkAlarmData.setSourceId(jsonResult.getString("sourceId"));
            highformworkAlarmData.setSourceTypeId(jsonResult.getInteger("sourceTypeId"));
            highformworkAlarmData.setSourceTypeName(jsonResult.getString("sourceTypeName"));
            highformworkAlarmData.setStartTime(jsonResult.getString("startTime"));
            highformworkAlarmData.setState(jsonResult.getInteger("state"));
            int result = alarmDataService.insertHighformworkAlarmData(highformworkAlarmData);
            if(result > 0){
                map.put("msg","成功");
                map.put("code",0);
            }else {
                map.put("msg","失败");
                map.put("code",-1);
            }
        }catch (Exception e){
            map.put("msg","参数错误");
            map.put("code",-1);
        }
        return map;
    }
}
