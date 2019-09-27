package com.hujiang.project.zhgd.sbHire.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.project.zhgd.sbHire.domain.Hire;
import com.hujiang.project.zhgd.sbHire.domain.HirePeople;
import com.hujiang.project.zhgd.sbHire.service.ISbHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 移动端
 */
@RestController
@RequestMapping(value = "/provider/hireAppApi",method = RequestMethod.POST)
public class SbHireAppApi extends BaseController {
    @Autowired
    private ISbHireService hireService;
    /**
     * 实时监控
     * @return
     * @throws ParseException
     */
    @PostMapping("/getHirePeople")
    public JSONObject getHirePeople(@RequestParam(value = "projectId")int projectId) throws ParseException {
        JSONObject result = new JSONObject();
        JSONArray projectArray = new JSONArray();       //项目array
        JSONArray areaArray = new JSONArray();          //工区array
        JSONArray userArray = new JSONArray();//人员array
        List<HirePeople> projectList = hireService.selectProject(projectId);
        List<HirePeople> areaList = hireService.selectArea(null);
        List<HirePeople> peopleList = hireService.selectPeople(null);
        List<HirePeople> timeList = hireService.selectTimeList(null);
        if(projectList == null && projectList.size()<1){
            return result;
        }
        for (HirePeople project:projectList) {
            JSONObject projectMap = new JSONObject();       //项目map
            projectMap.put("projectId",project.getpId());
            projectMap.put("projectName", project.getProjectName());
            List<HirePeople> myAreaList = areaList.stream().filter(
                    a->a.getpId().equals(project.getpId())
            ).collect(Collectors.toList());
            if (myAreaList == null || myAreaList.size() < 1) {
                continue;
            }
            for (HirePeople area : myAreaList){
                JSONObject areaMap = new JSONObject();      //工区map
                areaMap.put("areaId",area.getAreaId());
                areaMap.put("areaName", area.getAreaName());
                List<HirePeople> myPeopleList = peopleList.stream().filter(
                        a->a.getAreaId().equals(area.getAreaId())
                ).collect(Collectors.toList());
                if (myPeopleList == null || myPeopleList.size() < 1) {
                    continue;
                }
                for (HirePeople times : myPeopleList){
                    JSONObject userMap = new JSONObject();//人员map
                    if(!timeList.stream().filter(
                            a->a.getImei().equals(times.getImei())).findAny().isPresent()) {
                       continue;
                    }
                    else {
                        HirePeople myTime = timeList.stream().filter(
                                a->a.getImei().equals(times.getImei())).findAny().get();

                        userMap.put("userId",times.getPeopleId());
                        userMap.put("userName",times.getPeopleName());
                        userMap.put("userXloc",myTime.getXloc());
                        userMap.put("userYloc",myTime.getYloc());

                        //时间转毫秒
                        long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(myTime.getWatchDate()).getTime();
                        //系统毫秒
                        long l = System.currentTimeMillis();
                        //监测时间加一个小时大于当前时间
                        if((time+3600000)>l){
                            userMap.put("userStatus",true);//在线
                        }else{
                            userMap.put("userStatus",false);//离线
                        }
                    }
                    userArray.add(userMap);
                }
                areaMap.put("userList",userArray);
                areaArray.add(areaMap);

            }
            projectMap.put("areaList",areaArray);
            projectArray.add(projectMap);
        }
        result.put("msg","查询成功");
        result.put("code",0);
        result.put("data",projectArray);
        return result;
    }
    /**
     * 搜索查询
     * @return
     */
        @PostMapping("/getHireByNameSearch")
    public JSONObject getHireByNameSearch(@RequestParam(value = "userName",required = false) String userName,
                                          @RequestParam(value = "projectId")int projectId)throws ParseException {
        JSONObject result = new JSONObject();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        String format = sp.format(new Date());  //获取当天信息
        if (userName == null) {
            result.put("msg","查询失败");
            result.put("code",0);
            result.put("data", Collections.emptyList());
        } else {
            List<Hire> hires = hireService.selectHireByName(userName,projectId);
            if(hires!=null && hires.size()>0) {
                JSONArray hireArray = new JSONArray();
                for (Hire hire : hires) {
                    HirePeople hirePeople = hireService.selectTimeTwo(hire.getImei(),format);
                    if(hirePeople!=null) {
                        JSONObject hireMap = new JSONObject();
                        hireMap.put("userId", hire.getId());
                        hireMap.put("userName", hire.getUserName());
                        hireMap.put("userPhone", hire.getUserPhone());
                        hireMap.put("constructionName", hire.getConstructionName());
                        hireMap.put("imei", hire.getImei());
                        hireMap.put("areaName",hire.getAreaName());
                        hireMap.put("bat", hirePeople.getBat());
                        hireMap.put("watchDate", hirePeople.getWatchDate());
                        hireMap.put("userXloc", hirePeople.getXloc());
                        hireMap.put("userYloc", hirePeople.getYloc());
                        hireMap.put("address", hirePeople.getAddress());
                        long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(hirePeople.getWatchDate()).getTime();
                        //系统毫秒
                        long l = System.currentTimeMillis();
                        //监测时间加一个小时大于当前时间
                        if ((time + 3600000) > l) {
                            hireMap.put("userStatus", true);//在线
                        } else {
                            hireMap.put("userStatus", false);//离线
                        }
                        hireArray.add(hireMap);
                    }
                }
                result.put("msg", "查询成功");
                result.put("code", 0);
                result.put("data", hireArray);
            }else{
                result.put("msg","查询失败");
                result.put("code",-1);
                result.put("data", Collections.emptyList());
            }
        }
        return result;
    }
    /**
     * 历史记录
     * @return
     */
    @PostMapping("/getHireByNameHistory")
    public JSONObject getHireByNameHistory(@RequestParam(value = "userName",required = false) String userName,
                                           @RequestParam(value = "projectId")int projectId) {
        JSONObject result = new JSONObject();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        String format = sp.format(new Date());  //获取当天信息
        if (userName == null) {
            result.put("data", Collections.emptyList());
        } else {
            List<Hire> hires = hireService.selectHireByName(userName,projectId);
            JSONArray hireArray = new JSONArray();
            if(hires!=null && hires.size()>0) {
                for (Hire hire : hires) {
                    JSONObject hireMap = new JSONObject();
                    Hire hireIn = hireService.selectHireByTimeOne(hire.getImei(), format);
                    Hire hireOut = hireService.selectHireByTimeTwo(hire.getImei(), format);
                    hireMap.put("userId", hire.getId());
                    hireMap.put("userName", hire.getUserName());
                    hireMap.put("userPhone", hire.getUserPhone());
                    hireMap.put("constructionName", hire.getConstructionName());
                    //进
                    hireMap.put("inbat", hireIn.getBat());
                    hireMap.put("inTime",hireIn.getWatchDate());
                    hireMap.put("inAddress",hireIn.getAddress());
                    //出
                    hireMap.put("outbat", hireOut.getBat());
                    hireMap.put("outTime",hireOut.getWatchDate());
                    hireMap.put("outAddress",hireOut.getAddress());
                    List<Hire> hireHistoryList = hireService.selectHireByTimeHistory(hire.getImei(), format);
                    JSONArray jsonArray = new JSONArray();
                    if(hireHistoryList!=null && hireHistoryList.size()>0){
                        for (Hire hireHistory:hireHistoryList){
                            JSONObject hireHistoryMap = new JSONObject();
                            hireHistoryMap.put("userXloc",hireHistory.getXloc());
                            hireHistoryMap.put("userYloc",hireHistory.getYloc());
                            jsonArray.add(hireHistoryMap);
                        }
                    }else{
                        hireMap.put("list",Collections.emptyList());
                    }
                    hireMap.put("list",jsonArray);
                    hireArray.add(hireMap);

                }
                result.put("msg","查询成功");
                result.put("code",0);
                result.put("data",hireArray);
            }else{
                result.put("msg","查询失败");
                result.put("code",-1);
                result.put("data",hireArray);
            }
        }
        return result;
    }
    /* *//**
     *  根据人员姓名查询多个判断是否状态：在线/离线
     *//*
    @PostMapping("/getHirePeopleList")
    public JSONObject getHirePeopleList(@RequestParam(value = "userName",required = false)String userName,
                                        @RequestParam(value = "projectId")int projectId) throws ParseException{
        JSONObject result = new JSONObject();
        if(userName == null ){
            result.put("data", Collections.emptyList());
        }else{
            List<HirePeople> hirePeopleList = hireService.selectPeopleList(userName,projectId);
            if ((hirePeopleList != null && hirePeopleList.size()>0)) {
                JSONArray jsonArray = new JSONArray();
                for (HirePeople hirePeople : hirePeopleList) {
                    JSONObject hireMap = new JSONObject();
                    hireMap.put("userId",hirePeople.getPeopleId());
                    hireMap.put("userName",hirePeople.getPeopleName());
                    HirePeople peopleTimes = hireService.selectTime(hirePeople.getImei());
                    //时间转毫秒
                    long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(peopleTimes.getWatchDate()).getTime();
                    //系统毫秒
                    long l = System.currentTimeMillis();
                    //监测时间加一个小时大于当前时间
                    if((time+3600000)>l){
                        hireMap.put("userStatus",true);//在线
                    }else{
                        hireMap.put("userStatus",false);//离线
                    }
                    hireMap.put("constructionName",hirePeople.getConstructionName());
                    hireMap.put("areaId",hirePeople.getAreaId());
                    hireMap.put("areaName",hirePeople.getAreaName());
                    jsonArray.add(hireMap);
                }
                result.put("msg","查询成功");
                result.put("code",0);
                result.put("data",jsonArray);
            }
        }
        return result;
    }*/
}
