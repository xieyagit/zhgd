package com.hujiang.project.zhgd.sbGroup.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.DateUtils;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.project.zhgd.sbCraneAddrecord.service.ISbCraneAddrecordService;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbCurrentTemperature.service.ISbCurrentTemperatureService;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.service.ISbDustEmissionService;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbElevatorAddrecord.service.ISbElevatorAddrecordService;
import com.hujiang.project.zhgd.sbGroup.domain.CraneKB;
import com.hujiang.project.zhgd.sbGroup.domain.ElevatorKB;
import com.hujiang.project.zhgd.sbGroup.domain.SbProject;
import com.hujiang.project.zhgd.sbGroup.service.ISbGroupService;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集团端
 */
@RestController
@RequestMapping(value = "/provider")
public class SbGroupApi extends BaseController{
        @Autowired
        private ISbGroupService sbGroupService;
    @Autowired
    private ISbCraneAddrecordService sbCraneAddrecordService;
    @Autowired
    private ISbCurrentTemperatureService sbCurrentTemperatureService;
    @Autowired
    private ISbDustEmissionService dustEmissionService;
    @Autowired
    private ISbElevatorAddrecordService elevatorAddrecordService;


    @PostMapping(value = "/equipment")
    public JSONObject equipment(@RequestParam(value = "cid")int cid) throws ParseException {
        JSONObject jsonObject = new JSONObject();
        JSONObject result = new JSONObject();
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("pid",cid);

        JSONObject margin = new JSONObject();
        List<SbCraneAddrecord> SbCraneAddrecordList = sbCraneAddrecordService.selectCraneAddrecordList(paramMap);
        int onCraneLine = 0;
        int offCraneLine = 0;
        for(SbCraneAddrecord crane :SbCraneAddrecordList){
            long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(crane.getRuntime()).getTime();
            long now = System.currentTimeMillis();//系统毫秒
            //电箱运行状态
            if((time+3600000)>now){         //监测时间加一个小时大于当前时间
                onCraneLine++;
            }else{
                offCraneLine++;
            }
        }
        margin.put("total",SbCraneAddrecordList.size());
        margin.put("onLine",onCraneLine);
        margin.put("offLine",offCraneLine);

        JSONObject electricityBox = new JSONObject();
        List<SbCurrentTemperature> SbCurrentTemperatureList = sbCurrentTemperatureService.SbCurrentTemperatureListKB(paramMap);
        int onCurrentLine = 0;
        int offCurrentLine = 0;
        for(SbCurrentTemperature current :SbCurrentTemperatureList){
            long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(current.getTm()).getTime();
            long now = System.currentTimeMillis();//系统毫秒
            //电箱运行状态
            if((time+3600000)>now){         //监测时间加一个小时大于当前时间
                onCurrentLine++;
            }else{
                offCurrentLine++;
            }
        }
        electricityBox.put("total",SbCurrentTemperatureList.size());
        electricityBox.put("onLine",onCurrentLine);
        electricityBox.put("offLine",offCurrentLine);

        JSONObject environment = new JSONObject();
        List<SbDustEmission> sbDustEmissions = dustEmissionService.selectSbDustEmissionListKB(paramMap);
        int onDustLine = 0;
        int offDustLine = 0;
        for(SbDustEmission dust :sbDustEmissions){
            long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dust.getDate()).getTime();
            long now = System.currentTimeMillis();//系统毫秒
            //电箱运行状态
            if((time+3600000)>now){         //监测时间加一个小时大于当前时间
                onDustLine++;
            }else{
                offDustLine++;
            }
        }
        environment.put("total",sbDustEmissions.size());
        environment.put("onLine",onDustLine);
        environment.put("offLine",offDustLine);

        JSONObject lifter = new JSONObject();
        List<SbElevatorAddrecord> SbElevatorAddrecordList = elevatorAddrecordService.selectSbElevatorAddrecordListKB(paramMap);
        int onElevatorLine = 0;
        int offElevatorLine = 0;
        for(SbElevatorAddrecord elevator :SbElevatorAddrecordList){
            long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(elevator.getRuntime()).getTime();
            long now = System.currentTimeMillis();//系统毫秒
            //电箱运行状态
            if((time+3600000)>now){         //监测时间加一个小时大于当前时间
                onElevatorLine++;
            }else{
                offElevatorLine++;
            }
        }
        lifter.put("total",SbElevatorAddrecordList.size());
        lifter.put("onLine",onElevatorLine);
        lifter.put("offLine",offElevatorLine);

        result.put("margin",margin);
        result.put("electricityBox",electricityBox);
        result.put("environment",environment);
        result.put("lifter",lifter);
        jsonObject.put("data",result);
        return jsonObject;
    }

    @PostMapping(value = "/marginAlarm")
    public JSONObject marginAlarm(@RequestParam(value = "cid")int cid){
        JSONObject jsonObject = new JSONObject();
        JSONObject result = new JSONObject();
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("pid",cid);
        CraneKB kb = sbCraneAddrecordService.selectCount(paramMap);
        result.put("limit",kb.getLlimit()+kb.getRlimit()+kb.getFlimit()+kb.getBlimit());
        result.put("incline",kb.getIncline());
        result.put("hoisting",kb.getHoisting());
        result.put("windSpeed",kb.getWindSpeed());
        result.put("barrier",0);
        result.put("towerCrane",0);
        result.put("sensor",0);
        result.put("noGo",kb.getLlimit()+kb.getRlimit()+kb.getFlimit()+kb.getBlimit()+kb.getIncline()+kb.getHoisting()+kb.getWindSpeed());
        jsonObject.put("data",result);
        return jsonObject;
    }

    @PostMapping(value = "/lifterAlarm")
    public JSONObject lifterAlarm(@RequestParam(value = "cid")int cid){
        JSONObject jsonObject = new JSONObject();
        JSONObject result = new JSONObject();
        Map<String,Object> paramMap=new HashMap<String,Object>();
        paramMap.put("pid",cid);

        ElevatorKB kb = elevatorAddrecordService.selectCount(paramMap);
        result.put("load",kb.getLoad());
        result.put("people",kb.getPeople());
        result.put("incline",kb.getXincline()+kb.getYincline());
        result.put("header",0);
        jsonObject.put("data",result);
        return jsonObject;
    }
        @PostMapping("/company")
        public AjaxResult company(@RequestParam(value = "cid") Integer cid){
            Object json = sbGroupService.selectSbGroupById(cid);
            return AjaxResult.success(json);
        }

        @PostMapping("/totalList")
        public AjaxResult totalList(@Param("cid") Integer cid){
            Object json = sbGroupService.selectSbGroupMU(cid);
            return AjaxResult.success(json);
       }

        @PostMapping("/projectList")
        public AjaxResult projectList(@Param("cid") Integer cid){
            JSONObject result = new JSONObject();
            List<SbProject> list = sbGroupService.selectProjectList(cid);
            result.put("projectList",list);
            int normal = sbGroupService.selectProjectCount(cid,"1");
            int abnormal = sbGroupService.selectProjectCount(cid,"2");
            int complete = sbGroupService.selectProjectCount(cid,"3");
            int allProject = sbGroupService.selectProjectCount(cid,null);
            result.put("normal",normal);
            result.put("abnormal",abnormal);
            result.put("complete",complete);
            result.put("allProject",allProject);
            return AjaxResult.success(result);
        }
    @PostMapping("/searchProjectList")
    public AjaxResult searchProjectList(@Param("cid") Integer cid,@Param("name") String name){
        List<SbProject> list = sbGroupService.searchProjectList(cid,name);
        return AjaxResult.success(list);
    }
        @PostMapping("/count")
        public AjaxResult count(@Param("cid") Integer cid){
            JSONObject result = new JSONObject();
            int onGuard = sbGroupService.selectOnGuard(cid);
            result.put("onGuard",onGuard);
            return AjaxResult.success(result);
        }

        @PostMapping("/clickCard")
        public AjaxResult clickCard(@Param("cid") Integer cid,@Param("start")Long start,@Param("end")Long end){
            List<JSONObject> list = new ArrayList<>();
            long nd = 1000 * 24 * 60 * 60;
            List<Integer> administrators = sbGroupService.selectAdministorAttendance(cid,DateUtils.timstamp2DateTime(start),DateUtils.timstamp2DateTime(end+nd));
            List<Integer> workers = sbGroupService.selectWorkerAttendance(cid,DateUtils.timstamp2DateTime(start),DateUtils.timstamp2DateTime(end+nd));
            Long time = start;
            if (administrators.size()<0){
                return AjaxResult.error("无数据");
            }else {
                for (int i=0;i<administrators.size();i++){
                    JSONObject result = new JSONObject();
                    result.put("administrator", administrators.get(i));
                    result.put("worker",workers.get(i));
                    result.put("date",time);
                    time = time+nd;
                    list.add(result);
                }
                return AjaxResult.success(list);
            }
        }
    @PostMapping("/plateList")
    public AjaxResult plateList(@Param("cid") Integer cid,@Param("start") Long start,@Param("end")Long end){
        List<JSONObject> list = new ArrayList<>();
        Long num = DateUtils.getDateToDay(end,start);
        Long time = start;
        long nd = 1000 * 24 * 60 * 60;
        for (int i=0;i<=num;i++){
            JSONObject result = new JSONObject();
            Integer carIn = sbGroupService.selectPlate(cid,1,DateUtils.timstamp2DateTime(time));
            Integer carOut = sbGroupService.selectPlate(cid,2,DateUtils.timstamp2DateTime(time));
            time = time+nd;
            result.put("carIn",carIn);
            result.put("carOut",carOut);
            result.put("date",time);
            list.add(result);
        }
        return AjaxResult.success(list);
    }

    @PostMapping("/environmentList")
    public AjaxResult environmentList(@Param("cid") Integer cid){
        JSONObject result = new JSONObject();
        Integer excellent = sbGroupService.selectTsp(cid,0,50);
        Integer favorable = sbGroupService.selectTsp(cid,50,100);
        Integer ordinary = sbGroupService.selectTsp(cid,100,150);
        Integer slight = sbGroupService.selectTsp(cid,150,200);
        Integer severity = sbGroupService.selectTsp(cid,200,300);
        result.put("excellent",excellent);
        result.put("favorable",favorable);
        result.put("ordinary",ordinary);
        result.put("slight",slight);
        result.put("severity",severity);
        return AjaxResult.success(result);
    }
}

