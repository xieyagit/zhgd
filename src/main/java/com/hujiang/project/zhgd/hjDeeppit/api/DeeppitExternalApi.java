package com.hujiang.project.zhgd.hjDeeppit.api;


import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.Md5Utils;
import com.hujiang.project.zhgd.hjDeeppit.domain.*;
import com.hujiang.project.zhgd.hjDeeppit.service.*;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.sbDustEmission.domain.SdDustEmission;
import com.hujiang.project.zhgd.utils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 深基坑对外接口
 */
@RestController
@RequestMapping(value = "/provider/deeppitExternalApi",method = RequestMethod.POST)
public class DeeppitExternalApi {
    @Autowired
    private IHjProjectService projectService;
    @Autowired
    private ISbDeeppitDisplayService displayService;
    @Autowired
    private ISbDeeppitStructuresService structuresService;
    @Autowired
    private ISbDeeppitGroupService groupService;
    @Autowired
    private ISbDeeppitFactorService factorService;
    @Autowired
    private IHjDeeppitDataService dataService;
    @Autowired
    private IDeeppitAlarmDataService alarmDataService;
    //21dec18c46ae8c063b199ef991e5c373
    private final static String PUBLIC_KEY = "Hujiang888";
    @PostMapping(value = "/encryption")
    public Map<String,Object> encryption(){
        Map<String,Object> map = new HashMap<>();
        String  projectId = EncryptionUtil.encryptByAES("4",PUBLIC_KEY);
        map.put("projectId",projectId);
        return map;
    }

    /**
     * 深基坑结构物
     * @param json
     * @return
     */
    @PostMapping(value = "/addStructures")
    public Map<String,Object> addStructures(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        try {
            String projectId = jsonResult.getString("projectId"); //给过来的密文
            projectId = EncryptionUtil.decryptByAES(projectId, PUBLIC_KEY);
            //解密项目ID
            HjProject hjProject = projectService.selectHjProjectById(Integer.parseInt(projectId));
            if(null != hjProject) {
                SbDeeppitStructures sbDeeppitStructures = new SbDeeppitStructures();
                sbDeeppitStructures.setId(jsonResult.getInteger("id"));
                sbDeeppitStructures.setName(jsonResult.getString("name"));
                sbDeeppitStructures.setTypeId(jsonResult.getInteger("typeId"));
                sbDeeppitStructures.setTypeName(jsonResult.getString("typeName"));
                sbDeeppitStructures.setLatitude(jsonResult.getString("latitude"));
                sbDeeppitStructures.setLongitude(jsonResult.getString("longitude"));
                sbDeeppitStructures.setPortrait(jsonResult.getString("portrait"));
                sbDeeppitStructures.setReservedO(jsonResult.getString(projectId));
                sbDeeppitStructures.setSupplier(jsonResult.getInteger("supplier")); //供应商Id
                sbDeeppitStructures.setMasterKey(EncryptionUtil.encryptByAES(jsonResult.getInteger("supplier").toString() +
                        jsonResult.getInteger("id").toString(), Md5Utils.hash(jsonResult.getInteger("supplier").toString())));
                int result = structuresService.insertSbDeeppitStructures(sbDeeppitStructures);
                if(result > 0){
                    map.put("msg","成功");
                    map.put("code",0);
                }else {
                    map.put("msg","失败");
                    map.put("code",-1);
                }

            }
        }catch (Exception e){
            map.put("msg","参数错误");
            map.put("code",-1);
        }
        return map;
    }

    /**
     * 结构物因素
     * @param json
     * @return
     */
    @PostMapping(value = "/addDisplay")
    public Map<String,Object> addDisplay(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        try {
            SbDeeppitDisplay sbDeeppitDisplay = new SbDeeppitDisplay();
            sbDeeppitDisplay.setId(jsonResult.getInteger("id"));
            sbDeeppitDisplay.setReserved(jsonResult.getString("reserved"));
            sbDeeppitDisplay.setName(jsonResult.getString("name"));
            sbDeeppitDisplay.setChecked(jsonResult.getString("checked"));
            sbDeeppitDisplay.setTimeDivision(jsonResult.getString("timeDivision"));
            sbDeeppitDisplay.setSupplier(jsonResult.getInteger("supplier"));
            sbDeeppitDisplay.setDisplayKey(EncryptionUtil.encryptByAES(jsonResult.getInteger("supplier").toString()+
                    jsonResult.getInteger("id").toString(), Md5Utils.hash(jsonResult.getInteger("supplier").toString())));
            int result = displayService.insertSbDeeppitDisplay(sbDeeppitDisplay);
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

    /**
     * 深基坑测点
     * @param json
     * @return
     */
    @PostMapping(value = "/addGroup")
    public Map<String,Object> addGroup(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        try {
            SbDeeppitGroup sbDeeppitGroup = new SbDeeppitGroup();
            sbDeeppitGroup.setId(jsonResult.getInteger("id"));
            sbDeeppitGroup.setName(jsonResult.getString("name"));   //分组名称
            sbDeeppitGroup.setType(jsonResult.getString("type"));   //分组类型
            sbDeeppitGroup.setReserved(jsonResult.getString("reserved"));   //检测因素id
            sbDeeppitGroup.setSupplier(jsonResult.getInteger("supplier"));   //供应商ID
            sbDeeppitGroup.setGroupKey(
                    EncryptionUtil.encryptByAES(jsonResult.getInteger("supplier").toString()+
                            jsonResult.getInteger("id").toString(), Md5Utils.hash(jsonResult.getInteger("supplier").toString())));
            int result = groupService.insertSbDeeppitGroup(sbDeeppitGroup);
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

    /**
     * 深基坑传感器
     * @param json
     * @return
     */
    @PostMapping(value = "/addFactor")
    public  Map<String,Object> addFactor(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        try {
            SbDeeppitFactor sbDeeppitFactor = new SbDeeppitFactor();
            sbDeeppitFactor.setId(jsonResult.getInteger("id"));
            sbDeeppitFactor.setName(jsonResult.getString("name"));
            sbDeeppitFactor.setReserved(jsonResult.getString("reserved"));
            sbDeeppitFactor.setLabels(jsonResult.getString("labels"));
            sbDeeppitFactor.setPortrait(jsonResult.getString("portrait"));
            sbDeeppitFactor.setDistance(jsonResult.getString("distance"));
            sbDeeppitFactor.setSupplier(jsonResult.getInteger("supplier"));
            sbDeeppitFactor.setFactorKey(EncryptionUtil.encryptByAES(jsonResult.getInteger("supplier").toString()+
                    jsonResult.getInteger("id").toString(), Md5Utils.hash(jsonResult.getInteger("supplier").toString())));
            int result = factorService.insertSbDeeppitFactor(sbDeeppitFactor);
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

    /**
     * 深基坑数据记录
     * @param json
     * @return
     */
    @PostMapping(value = "/addDeeppitData")
    public  Map<String,Object> addDeeppitData(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        try {
            HjDeeppitData hjDeeppitData = JSONObject.parseObject(jsonResult.toJSONString(),HjDeeppitData.class);
            int result = dataService.insertHjDeeppitData(hjDeeppitData);
            if(result >0){
                map.put("msg","成功");
                map.put("code",-1);
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

    /**
     * 深基坑报警记录
     * @param json
     * @return
     */
    @PostMapping(value = "/addDeeppitAlarmData")
    public Map<String,Object> addDeeppitAlarmData(@RequestBody String json){
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonResult = JSONObject.parseObject(json);
        try {
            DeeppitAlarmData deeppitAlarmData = new DeeppitAlarmData();
            deeppitAlarmData.setId(EncryptionUtil.encryptByAES(jsonResult.getInteger("supplier").toString()+
                    jsonResult.getInteger("id").toString(), Md5Utils.hash(jsonResult.getInteger("supplier").toString())));
            deeppitAlarmData.setEndTime(jsonResult.getString("endTime"));
            deeppitAlarmData.setSourceName(jsonResult.getString("sourceName"));
            deeppitAlarmData.setStructuresId(jsonResult.getInteger("structuresId"));
            deeppitAlarmData.setAlarmTypeCode(jsonResult.getString("alarmTypeCode"));
            deeppitAlarmData.setContent(jsonResult.getString("content"));
            deeppitAlarmData.setCount(jsonResult.getInteger("count"));
            deeppitAlarmData.setLevel(jsonResult.getInteger("level"));
            deeppitAlarmData.setSourceId(jsonResult.getString("sourceId"));
            deeppitAlarmData.setSourceTypeId(jsonResult.getInteger("sourceTypeId"));
            deeppitAlarmData.setSourceTypeName(jsonResult.getString("sourceTypeName"));
            deeppitAlarmData.setStartTime(jsonResult.getString("startTime"));
            deeppitAlarmData.setState(jsonResult.getInteger("state"));
            deeppitAlarmData.setStructuresName(jsonResult.getString("structuresName"));
            deeppitAlarmData.setAlarmId(jsonResult.getString("alarmId"));
            int result = alarmDataService.insertDeeppitAlarmData(deeppitAlarmData);
            if(result >0){
                map.put("msg","成功");
                map.put("code",-1);
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
