package com.hujiang.project.zhgd.sbUnloaderRegistration.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.support.Convert;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.sbUnloaderAlarmtime.domain.SbUnloaderAlarmtime;
import com.hujiang.project.zhgd.sbUnloaderAlarmtime.service.ISbUnloaderAlarmtimeService;
import com.hujiang.project.zhgd.sbUnloaderBinding.domain.SbUnloaderBinding;
import com.hujiang.project.zhgd.sbUnloaderBinding.service.ISbUnloaderBindingService;
import com.hujiang.project.zhgd.sbUnloaderEquipment.domain.SbUnloaderEquipment;
import com.hujiang.project.zhgd.sbUnloaderEquipment.service.ISbUnloaderEquipmentService;
import com.hujiang.project.zhgd.sbUnloaderRealtime.domain.SbUnloaderRealtime;
import com.hujiang.project.zhgd.sbUnloaderRealtime.service.ISbUnloaderRealtimeService;
import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.ExportUnloaderAlarmtime;
import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.ExportUnloaderRealtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/unloaderPcApi",method = RequestMethod.POST)
public class PcUnloader extends BaseController {
    @Autowired
    private ISbUnloaderBindingService bindingService;
    @Autowired
    private ISbUnloaderEquipmentService equipmentService;
    @Autowired
    private IHjProjectService projectService;
    @Autowired
    private ISbUnloaderAlarmtimeService alarmtimeService;
    @Autowired
    private ISbUnloaderRealtimeService realtimeService;
    @PostMapping(value = "/getUnloaderList")
    public JSONObject getUnloaderList(@RequestParam("projectId")Integer projectId){
        JSONObject jsonObject = new JSONObject();
        JSONObject re = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        Integer onLine = 0;     //在线
        Integer offLine = 0;    //离线
        SbUnloaderBinding sbUnloaderBinding = new SbUnloaderBinding();
        sbUnloaderBinding.setPid(projectId);
        List<SbUnloaderBinding> unloaderBindingList = bindingService.selectSbUnloaderBindingList(sbUnloaderBinding);
        HjProject project = projectService.selectHjProjectById(projectId);
        int call = alarmtimeService.count(projectId,null,null,null);
        if(unloaderBindingList != null && unloaderBindingList.size()>0){
            for(SbUnloaderBinding binding : unloaderBindingList){
                SbUnloaderEquipment sbUnloaderEquipment = new SbUnloaderEquipment();
                sbUnloaderEquipment.setHxzid(binding.getHxzId());
                SbUnloaderEquipment equipment = equipmentService.getSbUnloaderEquipment(sbUnloaderEquipment);
                if(equipment.getOperation().equals(0)){ //0 离线
                    offLine++;
                }else { //在线
                    onLine++;
                }
                JSONObject bMap = new JSONObject();
                bMap.put("projectId",binding.getPid());
                bMap.put("deviceId",binding.getHxzId());
                bMap.put("deviceName",binding.getDName());
                bMap.put("status",equipment.getOperation());
                jsonArray.add(bMap);
            }
            re.put("projectName",project.getProjectName());
            re.put("deviceList",jsonArray);
            re.put("offLine",offLine);
            re.put("onLine",onLine);
            re.put("call",call);
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",re);
        }else {
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }
    @PostMapping(value = "/getSbUnloaderRealtimeList")
    public JSONObject getSbUnloaderRealtimeList(@RequestParam(value = "projectId")Integer projectId,
                                                @RequestParam(value = "deviceId") String deviceId){
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        String format = sp.format(new Date());  //获取当天信息
        SbUnloaderRealtime sbUnloaderRealtime = new SbUnloaderRealtime();
        sbUnloaderRealtime.setHxzId(deviceId);
        sbUnloaderRealtime.setProjectId(projectId);
        sbUnloaderRealtime.setRTime(format);
        SbUnloaderRealtime unloaderRealtime = realtimeService.getSbUnloaderRealtimeList(sbUnloaderRealtime);
        int load = alarmtimeService.count(projectId,deviceId,1,null);
        int dipX = alarmtimeService.count(projectId,deviceId,2,null);
        int dipY = alarmtimeService.count(projectId,deviceId,3,null);
        int bat = alarmtimeService.count(projectId,deviceId,4,null);
        JSONObject uMap = new JSONObject();
        if(unloaderRealtime != null){
            uMap.put("id",unloaderRealtime.getId());
            uMap.put("weight",unloaderRealtime.getWeight());    //载重
            uMap.put("weightPercent",unloaderRealtime.getWeightPercent());  //载重百分比
            uMap.put("ObliguityXStatus",unloaderRealtime.getObliguityXStatus());    //倾角X状态0：正常1：预警2：报警3：故障
            uMap.put("ObliguityYStatus",unloaderRealtime.getObliguityYStatus()); //倾角y状态0：正常1：预警2：报警3：故障
            uMap.put("load",load);  //载重
            uMap.put("dipX",dipX);    //倾角
            uMap.put("dipY",dipY);    //倾角
            uMap.put("bat",bat);    //电量
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",uMap);
        }else {
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",uMap);
        }
        return jsonObject;
    }
    @PostMapping(value = "/getRealtimeHistory")
    public JSONObject getRealtimeHistory(@RequestParam("projectId")Integer projectId,
                                         @RequestParam(value = "deviceId") String deviceId,
                                         @RequestParam(value = "time",required = false)String time,
                                         @RequestParam(value = "endTime",required = false)String endTime,
                                         @RequestParam(value = "alarmType")Integer alarmType,
                                         PageDomain pageDomain
    ){
        JSONObject jsonObject = new JSONObject();
        SbUnloaderAlarmtime sbUnloaderAlarmtime = new SbUnloaderAlarmtime();
        sbUnloaderAlarmtime.setProjectId(projectId);
        sbUnloaderAlarmtime.setHxzId(deviceId);
        sbUnloaderAlarmtime.setStartTime(time);
        sbUnloaderAlarmtime.setEndTime(endTime);
        sbUnloaderAlarmtime.setAlarmType(alarmType);
        startPage();
        List<SbUnloaderAlarmtime> sbUnloaderAlarmtimeList =  alarmtimeService.getSbUnloaderAlarmtimeList(sbUnloaderAlarmtime);
        TableDataInfo dataTable = getDataTable(sbUnloaderAlarmtimeList);
        if(sbUnloaderAlarmtimeList != null && sbUnloaderAlarmtimeList.size()>0){
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("total",dataTable.getTotal());
            jsonObject.put("data", sbUnloaderAlarmtimeList);
        }else {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("total",dataTable.getTotal());
            jsonObject.put("data", sbUnloaderAlarmtimeList);
        }
        return jsonObject;
    }



    @PostMapping(value = "/getSbUnloaderHistory")
    public JSONObject getSbUnloaderHistory(@RequestParam(value = "projectId")Integer projectId,
                                           @RequestParam(value = "deviceId") String deviceId,
                                           @RequestParam(value = "time",required = false)String time,
                                           @RequestParam(value = "endTime",required = false)String endTime,
                                           PageDomain pageDomain){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        SbUnloaderRealtime sbUnloaderRealtime = new SbUnloaderRealtime();
        sbUnloaderRealtime.setProjectId(projectId);
        sbUnloaderRealtime.setHxzId(deviceId);
        sbUnloaderRealtime.setRTime(time);
        sbUnloaderRealtime.setEndTime(endTime);
        startPage();
        List<SbUnloaderRealtime> sbUnloaderRealtimeList = realtimeService.getSbUnloaderHistory(sbUnloaderRealtime);
        TableDataInfo dataTable = getDataTable(sbUnloaderRealtimeList);
        if(sbUnloaderRealtimeList != null && sbUnloaderRealtimeList.size()>0){
            for(SbUnloaderRealtime unloaderRealtime:sbUnloaderRealtimeList){
                JSONObject uMap = new JSONObject();
                uMap.put("id",unloaderRealtime.getId());
                uMap.put("hxzId",unloaderRealtime.getHxzId());
                uMap.put("time",unloaderRealtime.getRTime());    //时间
                uMap.put("weight",unloaderRealtime.getWeight());    //载重
                uMap.put("weightPercent",unloaderRealtime.getWeightPercent());  //载重百分比
                uMap.put("obliguityX",unloaderRealtime.getObliguityX());    //倾角x
                uMap.put("obliguityY",unloaderRealtime.getObliguityY());    //倾角y
                jsonArray.add(uMap);
            }
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("total",dataTable.getTotal());
            jsonObject.put("data", jsonArray);
        }else {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("total",dataTable.getTotal());
            jsonObject.put("data", jsonArray);
        }

        return jsonObject;
    }
    @PostMapping("/exportUnloaderAlarmtime")
    public List<ExportUnloaderAlarmtime> exportUnloaderAlarmtime(@RequestParam(value = "ids",required = false)String[] ids,
                                                                 @RequestParam(value = "startTime",required = false)String startTime,
                                                                 @RequestParam(value = "endTime",required = false)String endTime,
                                                                 @RequestParam(value = "deviceId",required = false)String deviceId,
                                                                 @RequestParam(value = "alarmType",required = false)Integer alarmType)
    {
        List<ExportUnloaderAlarmtime> exportUnloaderAlarmtimeList =  alarmtimeService.getSbUnloaderAlarmtimeListById(ids,startTime,endTime,deviceId,alarmType);


        return exportUnloaderAlarmtimeList;
    }
    @PostMapping("/exportUnloaderRealtime")
    public List<ExportUnloaderRealtime> exportUnloaderRealtime(@RequestParam(value = "ids",required = false)String[] ids,
                                                               @RequestParam(value = "startTime",required = false)String startTime,
                                                               @RequestParam(value = "endTime",required = false)String endTime,
                                                               @RequestParam(value = "deviceId",required = false)String deviceId)
    {
        List<ExportUnloaderRealtime> exportUnloaderRealtimeList =  realtimeService.getSbUnloaderRealtimeListByIds(ids,startTime,endTime,deviceId);

        return exportUnloaderRealtimeList;
    }
}
