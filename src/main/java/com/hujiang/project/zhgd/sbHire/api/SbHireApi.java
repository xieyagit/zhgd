package com.hujiang.project.zhgd.sbHire.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.sbArea.domain.SbArea;
import com.hujiang.project.zhgd.sbArea.service.ISbAreaService;
import com.hujiang.project.zhgd.sbHire.domain.*;
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
 * 人员定位pc
 */
@RestController
@RequestMapping(value = "/provider/hireApi",method = RequestMethod.POST)
public class SbHireApi extends BaseController{
        @Autowired
        private ISbHireService hireService;
        @Autowired
        private ISbAreaService sbAreaService;
        @Autowired
        private IHjProjectWorkersService hjProjectWorkersService;

        @PostMapping("/addHire")
        public JSONObject addHire(@RequestBody String json){
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonResult = JSONObject.parseObject(json);
            SbHire sbHire = JSONObject.parseObject(jsonResult.toJSONString(), SbHire.class);
            HjProjectWorkers hjProjectWorkers = hjProjectWorkersService.getProjectWorkersById(sbHire.getIdCode());
            sbHire.setPeopleId(hjProjectWorkers.getId());
            if(hireService.insertSbHire(sbHire)>0){
                jsonObject.put("msg","成功");
                jsonObject.put("code",0);
            }else {
                jsonObject.put("msg","失败");
                jsonObject.put("code",-1);
            }
            return jsonObject;
        }


        @PostMapping("/getHirePeople")
        public JSONObject getHirePeople(@RequestParam(value = "projectId")int projectId) throws ParseException {
            JSONObject result = new JSONObject();
            JSONArray projectArray = new JSONArray();       //项目array
            JSONArray areaArray = new JSONArray();          //工区array

            List<HirePeople> projectList = hireService.selectProject(projectId);
            List<HirePeople> areaList = hireService.selectArea(null);
            List<SbAreaLocaltion> areaLocaltionList = hireService.selectAreaLocaltion();
            List<HirePeople> peopleList = hireService.selectPeople(null);
            List<HirePeople> timeList = hireService.selectTimeList(null);
            if(projectList == null && projectList.size()<1){
                return result;
            }

            JSONObject projectMap = new JSONObject();       //项目map
            projectMap.put("projectId",projectList.get(0).getpId());
            projectMap.put("projectName", projectList.get(0).getProjectName());
            //工业区列表
            List<HirePeople> myAreaList = areaList.stream().filter(
                    a->a.getpId().equals(projectId)).collect(Collectors.toList());

            if (myAreaList == null || myAreaList.size() < 1) {
                return result;
            }
            //工业区
            for (HirePeople area : myAreaList){
                JSONObject areaMap = new JSONObject();      //工区map
                areaMap.put("areaId",area.getAreaId());
                areaMap.put("areaName", area.getAreaName());
                areaMap.put("way", area.getWay());
                if(area.getWay()==1){
                    List<SbAreaLocaltion> myAreaLocaltionList = areaLocaltionList.stream().filter(
                            a->a.getAreaId().equals(area.getAreaId())).collect(Collectors.toList());
                    areaMap.put("localtion", myAreaLocaltionList);
                }else{
                    areaMap.put("areaXloc",area.getXloc());
                    areaMap.put("areaYloc",area.getYloc());
                    areaMap.put("areaRadius",area.getRadius());
                }
                List<HirePeople> myPeopleList = peopleList.stream().filter(
                        a->a.getAreaId().equals(area.getAreaId())
                ).collect(Collectors.toList());
                //工业区下的人员
                JSONArray onLineArray = new JSONArray();//在线人员array
                JSONArray offLineArray = new JSONArray();//离线人员array
                JSONObject lineMap = new JSONObject();//离线人员array
                if (myPeopleList.size() > 0) {
                    for (HirePeople times : myPeopleList) {
                        JSONObject onLineMap = new JSONObject();//人员map
                        JSONObject offLineMap = new JSONObject();//人员map
                        if (!timeList.stream().filter(
                                a -> a.getImei().equals(times.getImei())).findAny().isPresent()) {
                            offLineMap.put("userId", times.getPeopleId());
                            offLineMap.put("userName", times.getPeopleName());
                            offLineMap.put("userStatus", false);//离线
                            offLineArray.add(offLineMap);
                            lineMap.put("offLine", offLineArray);
                        } else {
                            HirePeople myTime = timeList.stream().filter(
                                    a -> a.getImei().equals(times.getImei())).findAny().get();
                            //时间转毫秒
                            long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(myTime.getWatchDate()).getTime();
                            //系统毫秒
                            long l = System.currentTimeMillis();
                            //监测时间加一个小时大于当前时间
                            if ((time + 3600000) > l) {
                                onLineMap.put("userId", times.getPeopleId());
                                onLineMap.put("userName", times.getPeopleName());
                                onLineMap.put("userXloc", myTime.getXloc());
                                onLineMap.put("userYloc", myTime.getYloc());
                                onLineMap.put("userStatus", true);//在线
                                onLineArray.add(onLineMap);
                                lineMap.put("onLine", onLineArray);
                            } else {
                                offLineMap.put("userId", times.getPeopleId());
                                offLineMap.put("userName", times.getPeopleName());
                                offLineMap.put("userStatus", false);//离线
                                offLineArray.add(offLineMap);
                                lineMap.put("offLine", offLineArray);
                            }
                        }
                    }

                }else{
                    lineMap.put("onLine", onLineArray);
                    lineMap.put("offLine", offLineArray);
                }
                areaMap.put("line",lineMap);
                areaArray.add(areaMap);

            }
            projectMap.put("areaList",areaArray);
            projectArray.add(projectMap);

            result.put("msg","查询成功");
            result.put("code",0);
            result.put("data",projectArray);
            return result;
        }
        /**
         * 搜索查询
         *
         * @param filed 按名字、设备、电话查询当天的定位信息
         * @return
         */
        @PostMapping("/getHireSearch")
        public JSONObject getPeopleAreaSearch(@RequestParam(value = "filed", required = false) String filed,
                                              @RequestParam(value = "projectId")int projectId) {
            JSONObject result = new JSONObject();
            SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
            String format = sp.format(new Date());  //获取当天信息\
            List<SbAreaLocaltion> areaLocaltionList = hireService.selectAreaLocaltion();
            List<SbAreaCertificate> areaCertificateList = hireService.selectAreaCertificate();
            if (filed == null) {
                result.put("data", Collections.emptyList());
            } else {
                List<Hire> hires = hireService.selectHireSearch(filed,projectId);
                JSONArray hireArray = new JSONArray();
                if(hires!=null && hires.size()>0){
                    for (Hire hire:hires) {
                        HirePeople hirePeople = hireService.selectTimeTwo(hire.getImei(), format);
                        if (hirePeople != null) {
                            JSONObject hireMap = new JSONObject();
                            hireMap.put("id", hire.getId());
                            hireMap.put("userName", hire.getUserName());
                            hireMap.put("userPhone", hire.getUserPhone());
                            hireMap.put("constructionName", hire.getConstructionName());
                            hireMap.put("idCode", hire.getIdCode());
                            hireMap.put("startTime", hire.getStartTime());
                            hireMap.put("workType", hire.getWorkType());
                            hireMap.put("imei", hire.getImei());
                            hireMap.put("areaName", hire.getAreaName());
                            hireMap.put("empNaticeplace", hire.getEmpNaticeplace());
                            hireMap.put("way", hire.getWay());
                            if(hire.getWay() == 1){
                                List<SbAreaLocaltion> myAreaLocaltionList = areaLocaltionList.stream().filter(
                                        a->a.getAreaId().equals(hire.getAreaId())).collect(Collectors.toList());
                                hireMap.put("localtion", myAreaLocaltionList);
                            }else{
                                hireMap.put("areaXloc", hire.getAreaXloc());
                                hireMap.put("areaYloc", hire.getAreaYloc());
                                hireMap.put("areaRadius", hire.getAreaRadius());
                            }
                            //一人只有一条，多条也只取一条
                            List<SbAreaCertificate> myAreaCertificateList = areaCertificateList.stream().filter(
                                    a->a.getUserId().equals(hire.getId())).collect(Collectors.toList());

                            if(myAreaCertificateList.size()<=0){
                                hireMap.put("tertiaryEducation",0);
                                hireMap.put("laborContract",0);
                                hireMap.put("digitalTraining",0);
                                hireMap.put("skilCertificate","");
                            }else{
                                hireMap.put("tertiaryEducation",myAreaCertificateList.get(0).getTertiaryEducation());
                                hireMap.put("laborContract",myAreaCertificateList.get(0).getLaborContract());
                                hireMap.put("digitalTraining",myAreaCertificateList.get(0).getDigitalTraining());
                                hireMap.put("skilCertificate",myAreaCertificateList.get(0).getSkilCertificate());
                            }
                            hireMap.put("bat", hirePeople.getBat());
                            hireMap.put("watchDate", hirePeople.getWatchDate());
                            hireMap.put("xloc", hirePeople.getXloc());
                            hireMap.put("yloc", hirePeople.getYloc());
                            hireMap.put("address", hirePeople.getAddress());

                            hireArray.add(hireMap);
                        }
                    }
                    result.put("msg", "查询成功");
                    result.put("code", 0);
                    result.put("data",hireArray);
                }else{
                    result.put("msg","查询成功");
                    result.put("code",-1);
                    result.put("data", Collections.emptyList());
                }
            }
            return result;
        }

    /**
     * 历史轨迹 按照时间排序
     * @return
     */
    @PostMapping("/getHireHistory")
    public JSONObject getHireHistory(@RequestParam(value = "filed", required = false) String filed,
                                     @RequestParam(value = "projectId", required = false) int projectId,
                                     @RequestParam(value = "startTime", required = false) String startTime) {
        JSONObject result = new JSONObject();
        if (filed == null && startTime == null) {
            result.put("data", Collections.emptyList());
        } else {
            List<Hire> hireList = hireService.selectHireHistory(filed,projectId);
            JSONArray hireArray = new JSONArray();
            if(hireList!=null && hireList.size()>0){
                for (Hire hireOne:hireList){        //人员
                    JSONArray hireTwoArray = new JSONArray();

                        JSONObject hireMaps = new JSONObject();
                        hireMaps.put("id",hireOne.getId());
                        hireMaps.put("userName",hireOne.getUserName());
                        hireMaps.put("userPhone",hireOne.getUserPhone());
                        hireMaps.put("constructionName",hireOne.getConstructionName());
                        hireMaps.put("imei",hireOne.getImei());

                        if(startTime != null){
                        List<Hire> hires = hireService.selectHireHistoryTime(hireOne.getImei(),startTime);
                            if(hires!=null && hires.size()>0){
                            for (Hire hireTwo:hires){
                                JSONObject hireMap = new JSONObject();
                                hireMap.put("bat",hireTwo.getBat());
                                hireMap.put("watchDate",hireTwo.getWatchDate());
                                hireMap.put("xloc",hireTwo.getXloc());
                                hireMap.put("yloc",hireTwo.getYloc());
                                hireMap.put("address",hireTwo.getAddress());
                                hireTwoArray.add(hireMap);
                            }
                            }
                        }else{
                            List<Hire> hires = hireService.selectHireHistoryTimeTwo(hireOne.getImei());
                            if(hires!=null && hires.size()>0){
                                for (Hire hireTwo:hires){
                                    JSONObject hireMap = new JSONObject();
                                    hireMap.put("bat",hireTwo.getBat());
                                    hireMap.put("watchDate",hireTwo.getWatchDate());
                                    hireMap.put("xloc",hireTwo.getXloc());
                                    hireMap.put("yloc",hireTwo.getYloc());
                                    hireMap.put("address",hireTwo.getAddress());
                                    hireTwoArray.add(hireMap);
                                }
                            }
                        }
                        hireMaps.put("list",hireTwoArray);
                        hireArray.add(hireMaps);
                }
                result.put("msg", "查询成功");
                result.put("code", 0);
                result.put("data",hireArray);
            }else {
                result.put("msg","查询失败");
                result.put("code",-1);
                result.put("data", Collections.emptyList());
            }
        }
        return result;
    }

    /**
     * 修改电子围栏半径
     * @return
     */
    @PostMapping(value = "/edit")
    public JSONObject updateRadius(@RequestBody SbArea sbArea){
        SbArea area = new SbArea();
        area.setId(sbArea.getId());
        area.setRadius(sbArea.getRadius());
        int i = sbAreaService.updateRadius(area);
        JSONObject jsonObject = new JSONObject();
        if (i<=0){
            jsonObject.put("img","修改失败");
            jsonObject.put("data",0);
            return jsonObject;
        }else {
            jsonObject.put("img","修改成功");
            jsonObject.put("data",i);
            return jsonObject;
        }
    }
}

