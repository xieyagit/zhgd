package com.hujiang.project.zhgd.sbArea.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.PcInspectionRecord;
import com.hujiang.project.zhgd.sbArea.domain.OptionsLocation;
import com.hujiang.project.zhgd.sbArea.domain.OptionsUser;
import com.hujiang.project.zhgd.sbArea.service.ISbAreaService;
import com.hujiang.project.zhgd.sbHire.service.ISbHireService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/LocationApi",method = RequestMethod.POST)
public class OptionsLocationApi extends BaseController {
    @Autowired
    private ISbAreaService areaService;

    @PostMapping("/getAreaList")
    public JSONObject getAreaList(@RequestParam("projectId")Integer projectId){
        JSONObject jsonObject = new JSONObject();
        List<OptionsLocation> optionsLocationsList = areaService.getAreaList(projectId);
        if(optionsLocationsList != null && optionsLocationsList.size()>0){
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",optionsLocationsList);
        }
        else {
            jsonObject.put("msg", "查询失败");
            jsonObject.put("code", -1);
            jsonObject.put("data", optionsLocationsList);
        }
        return jsonObject;
    }
    @PostMapping("/getAreaById")
    public JSONObject getAreaById(@RequestParam("areaId")Integer areaId){
        JSONObject jsonObject = new JSONObject();
        OptionsLocation optionsLocations = areaService.getAreaById(areaId);
        if(optionsLocations != null){
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",optionsLocations);
        }
        else {
            jsonObject.put("msg", "查询失败");
            jsonObject.put("code", -1);
            jsonObject.put("data",optionsLocations);
        }
        return jsonObject;
    }

    @PostMapping("/updateArea")
    public JSONObject updateArea(@RequestParam("areaId")Integer areaId,
                                     @RequestParam("areaName")String areaName,
                                     @RequestParam("areaAddress")String areaAddress,
                                     @RequestParam("constructionId")Integer constructionId){
        JSONObject jsonObject = new JSONObject();
        OptionsLocation optionsLocation = new OptionsLocation();
        optionsLocation.setAreaId(areaId);
        optionsLocation.setAreaName(areaName);
        optionsLocation.setAreaAddress(areaAddress);
        optionsLocation.setConstructionId(constructionId);
        int result = areaService.updateArea(optionsLocation);
        if(result>0){
            jsonObject.put("msg","修改成功");
            jsonObject.put("code",0);
        }else {
            jsonObject.put("msg", "修改失败");
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }
    @PostMapping("/addArea")
    public JSONObject addArea(@RequestParam("areaName")String areaName,
                                     @RequestParam("areaAddress")String areaAddress,
                                     @RequestParam("constructionId")Integer constructionId,
                                     @RequestParam("areaXloc")Double areaXloc,
                                     @RequestParam("areaYloc")Double areaYloc,
                                     @RequestParam("radius")Double radius,
                                     @RequestParam("projectId")Integer projectId){
        JSONObject jsonObject = new JSONObject();
        OptionsLocation optionsLocation = new OptionsLocation();
        optionsLocation.setAreaName(areaName);
        optionsLocation.setAreaAddress(areaAddress);
        optionsLocation.setConstructionId(constructionId);
        optionsLocation.setAreaXloc(areaXloc);
        optionsLocation.setAreaYloc(areaYloc);
        optionsLocation.setRadius(radius);
        optionsLocation.setProjectId(projectId);
        int result = areaService.addArea(optionsLocation);
        if(result>0){
            jsonObject.put("msg","成功");
            jsonObject.put("code",0);
        }else {
            jsonObject.put("msg", "失败");
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }
    @PostMapping("/deleteArea")
    public JSONObject deleteArea(@RequestParam("areaId")Integer areaId){
        JSONObject jsonObject = new JSONObject();
        int result = areaService.deleteArea(areaId);
        if(result>0){
            jsonObject.put("msg","成功");
            jsonObject.put("code",0);
        }else {
            jsonObject.put("msg", "失败");
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }
    @PostMapping("/getAreaUserList")
    public JSONObject getUserList(@RequestParam(value = "areaId",required = false) Integer areaId,
                                  @RequestParam(value = "filed",required = false)String filed,
                                  @RequestParam(value = "projectId")Integer projectId){
        JSONObject jsonObject = new JSONObject();
        startPage();
        List<OptionsUser> optionsUserList = areaService.getUserList(areaId,filed, projectId);
        TableDataInfo dataTable = getDataTable(optionsUserList);
        //处理分页数据
        List<OptionsUser> rows = (List<OptionsUser>)dataTable.getRows();
        if(rows!=null&& rows.size()>0){
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("total",dataTable.getTotal());//总记录数
            jsonObject.put("data",rows);
        }else {
            jsonObject.put("msg","查询失败");
            jsonObject.put("code",-1);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }

    @PostMapping("/updateAreaUser")
    public JSONObject updateAreaUser(@RequestParam(value = "areaId") Integer areaId,
                                 @RequestParam(value = "userName")String userName,
                                 @RequestParam(value = "userPhone",required = false)String userPhone,
                                 @RequestParam(value = "imei")String imei,
                                 @RequestParam(value = "userId")Integer userId){
        JSONObject jsonObject = new JSONObject();
       OptionsUser optionsUser = new OptionsUser();
       optionsUser.setAreaId(areaId);
       optionsUser.setUserName(userName);
       optionsUser.setUserPhone(userPhone);
       optionsUser.setUserId(userId);
       optionsUser.setImei(imei);
        int result = areaService.updateAreaUser(optionsUser);
        int result2 = areaService.updateUserPhone(optionsUser);
        if(result>0 && result2>0){
            jsonObject.put("msg","成功");
            jsonObject.put("code",0);
        }else {
            jsonObject.put("msg", "失败");
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }
    @PostMapping("/deleteAreaUser")
    public JSONObject deleteAreaUser(@RequestParam(value = "userId") Integer userId,
                                     @RequestParam(value = "areaId")Integer areaId){
        JSONObject jsonObject = new JSONObject();
        int result = areaService.deleteAreaUser(userId,areaId);
        if(result>0){
            jsonObject.put("msg","成功");
            jsonObject.put("code",0);
        }else {
            jsonObject.put("msg", "失败");
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }
    @PostMapping("/addAreaUser")
    public JSONObject addAreaUser(@RequestParam(value = "areaId")Integer areaId,
                                     @RequestParam(value = "userName")String userName,
                                     @RequestParam(value = "userPhone",required = false)String userPhone,
                                     @RequestParam(value = "imei")String imei){
        JSONObject jsonObject = new JSONObject();
        OptionsUser optionsUser = new OptionsUser();
        optionsUser.setAreaId(areaId);
        optionsUser.setUserName(userName);
        optionsUser.setUserPhone(userPhone);
        optionsUser.setImei(imei);
        int result = areaService.insertUserPhone(optionsUser);
        if(result>0){
            optionsUser.setUserId(optionsUser.getId());
            int result2 = areaService.insertAreaUser(optionsUser);
            if(result2>0){
            jsonObject.put("msg","成功");
            jsonObject.put("code",0);
            }else {
                jsonObject.put("msg", "失败");
                jsonObject.put("code", -1);
            }
        }else {
            jsonObject.put("msg", "失败");
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }

}
