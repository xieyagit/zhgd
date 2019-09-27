package com.hujiang.project.zhgd.sbUnloaderRegistration.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.sbUnloaderAlarmtime.domain.SbUnloaderAlarmtime;
import com.hujiang.project.zhgd.sbUnloaderAlarmtime.service.ISbUnloaderAlarmtimeService;
import com.hujiang.project.zhgd.sbUnloaderBinding.domain.SbUnloaderBinding;
import com.hujiang.project.zhgd.sbUnloaderBinding.service.ISbUnloaderBindingService;
import com.hujiang.project.zhgd.sbUnloaderEquipment.domain.SbUnloaderEquipment;
import com.hujiang.project.zhgd.sbUnloaderEquipment.service.ISbUnloaderEquipmentService;
import com.hujiang.project.zhgd.sbUnloaderRealtime.domain.SbUnloaderRealtime;
import com.hujiang.project.zhgd.sbUnloaderRealtime.service.ISbUnloaderRealtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/unloaderAppApi",method = RequestMethod.POST)
public class AppUnloader extends BaseController {
    @Autowired
    private ISbUnloaderBindingService bindingService;
    @Autowired
    private ISbUnloaderRealtimeService realtimeService;
    @Autowired
    private ISbUnloaderAlarmtimeService alarmtimeService;

    @PostMapping(value = "/getUnloader")
    public JSONObject getUnloader(@RequestParam("projectId")Integer projectId){
       JSONObject jsonObject = new JSONObject();
        SbUnloaderBinding sbUnloaderBinding = new SbUnloaderBinding();
        sbUnloaderBinding.setPid(projectId);
        List<SbUnloaderBinding> unloaderBindingList = bindingService.selectSbUnloaderBindingList(sbUnloaderBinding);
        JSONArray jsonArray = new JSONArray();
        if(unloaderBindingList != null && unloaderBindingList.size()>0){
            for(SbUnloaderBinding binding : unloaderBindingList){
                JSONObject bMap = new JSONObject();
                bMap.put("projectId",binding.getPid());
                bMap.put("deviceId",binding.getHxzId());
                bMap.put("deviceName",binding.getDName());
                jsonArray.add(bMap);
            }
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",jsonArray);
        }else {
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",Collections.emptyList());
        }

       return jsonObject;
    }

    /**
     * 卸料app界面数据
     * @param projectId
     * @param deviceId
     * @return
     */
    @PostMapping(value = "/getSbUnloaderRealtimeList")
    public JSONObject getSbUnloaderRealtimeList(@RequestParam("projectId")Integer projectId,
                                                @RequestParam("deviceId") String deviceId){
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        String format = sp.format(new Date());  //获取当天信息
        SbUnloaderRealtime sbUnloaderRealtime = new SbUnloaderRealtime();
        sbUnloaderRealtime.setHxzId(deviceId);
        sbUnloaderRealtime.setProjectId(projectId);
        sbUnloaderRealtime.setRTime(format);
        SbUnloaderRealtime unloaderRealtime = realtimeService.getSbUnloaderRealtimeList(sbUnloaderRealtime);
        JSONObject uMap = new JSONObject();
        if(unloaderRealtime != null){
            uMap.put("id",unloaderRealtime.getId());
            uMap.put("weight",unloaderRealtime.getWeight());    //载重
            uMap.put("weightPercent",unloaderRealtime.getWeightPercent());  //载重百分比
            uMap.put("ObliguityXStatus",unloaderRealtime.getObliguityXStatus());    //倾角X状态0：正常1：预警2：报警3：故障
            uMap.put("ObliguityYStatus",unloaderRealtime.getObliguityYStatus()); //倾角y状态0：正常1：预警2：报警3：故障
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

    /**
     * 卸料app历史记录
     * @param projectId
     * @param deviceId
     * @param time
     * @param pageDomain
     * @return
     */
    @PostMapping(value = "/getSbUnloaderHistory")
    public JSONObject getSbUnloaderHistory(@RequestParam("projectId")Integer projectId,
                                           @RequestParam("deviceId") String deviceId,
                                           @RequestParam(value = "time",required = false)String time,
                                           PageDomain pageDomain){
        JSONObject jsonObject = new JSONObject();
        SbUnloaderRealtime sbUnloaderRealtime = new SbUnloaderRealtime();
        sbUnloaderRealtime.setProjectId(projectId);
        sbUnloaderRealtime.setHxzId(deviceId);
        sbUnloaderRealtime.setRTime(time);
        startPage();
        List<SbUnloaderRealtime> sbUnloaderRealtimeList = realtimeService.getSbUnloaderHistory(sbUnloaderRealtime);
        TableDataInfo dataTable = getDataTable(sbUnloaderRealtimeList);
        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            JSONArray jsonArray = new JSONArray();
            for(SbUnloaderRealtime unloaderRealtime:sbUnloaderRealtimeList){
                JSONObject uMap = new JSONObject();
                uMap.put("id",unloaderRealtime.getId());
                uMap.put("time",unloaderRealtime.getRTime());    //时间
                uMap.put("weight",unloaderRealtime.getWeight());    //载重
                uMap.put("weightPercent",unloaderRealtime.getWeightPercent());  //载重百分比
                uMap.put("obliguityX",unloaderRealtime.getObliguityX());    //倾角x
                uMap.put("obliguityY",unloaderRealtime.getObliguityY());    //倾角y
                jsonArray.add(uMap);
            }
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", jsonArray);
        }else {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }

    /**
     * 卸料app报警记录
     * @param projectId
     * @param deviceId
     * @param time
     * @param pageDomain
     * @return
     */
    @PostMapping(value = "/getSbUnloaderAlarmtimeList")
    public JSONObject getSbUnloaderAlarmtimeList(@RequestParam("projectId")Integer projectId,
                                                 @RequestParam("deviceId") String deviceId,
                                                 @RequestParam(value = "time",required = false)String time,
                                                 PageDomain pageDomain){
        JSONObject jsonObject = new JSONObject();
        JSONObject re = new JSONObject();
        SbUnloaderAlarmtime sbUnloaderAlarmtime = new SbUnloaderAlarmtime();
        sbUnloaderAlarmtime.setProjectId(projectId);
        sbUnloaderAlarmtime.setHxzId(deviceId);
        sbUnloaderAlarmtime.setStartTime(time);
        int load = alarmtimeService.count(projectId,deviceId,1,time);
        int dipX = alarmtimeService.count(projectId,deviceId,2,time);
        int dipY = alarmtimeService.count(projectId,deviceId,3,time);
        int bat = alarmtimeService.count(projectId,deviceId,4,time);
        startPage();
        List<SbUnloaderAlarmtime> sbUnloaderAlarmtimeList = alarmtimeService.getSbUnloaderAlarmtimeList(sbUnloaderAlarmtime);
        TableDataInfo dataTable = getDataTable(sbUnloaderAlarmtimeList);
        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            JSONArray jsonArray = new JSONArray();
            for (SbUnloaderAlarmtime unloaderAlarmtime:sbUnloaderAlarmtimeList){
                JSONObject uMap = new JSONObject();
                uMap.put("id",unloaderAlarmtime.getId());
                uMap.put("startTime",unloaderAlarmtime.getStartTime()); //时间
                uMap.put("alarmType",unloaderAlarmtime.getAlarmType()); //报警类型 1：载重2：倾角3：电池电量报警
                uMap.put("alarmValue",unloaderAlarmtime.getAlarmValue());   //报警值 载重(t) 倾角(°)

                jsonArray.add(uMap);
            }
            re.put("list",jsonArray);
            re.put("load",load);  //载重
            re.put("dipX",dipX);    //倾角
            re.put("dipY",dipY);    //倾角
            re.put("bat",bat);    //电量
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", re);
        }else {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", re);
        }
        return jsonObject;
    }


}
