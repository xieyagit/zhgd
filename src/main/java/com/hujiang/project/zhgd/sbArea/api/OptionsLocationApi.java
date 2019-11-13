package com.hujiang.project.zhgd.sbArea.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjProjectUser.domain.HjProjectUser;
import com.hujiang.project.zhgd.hjProjectUser.service.IHjProjectUserService;
import com.hujiang.project.zhgd.hjRolePrivileges.domain.HjRolePrivileges;
import com.hujiang.project.zhgd.hjRolePrivileges.service.IHjRolePrivilegesService;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.PcInspectionRecord;
import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;
import com.hujiang.project.zhgd.hjSystemUser.service.IHjSystemUserService;
import com.hujiang.project.zhgd.hjUserRole.domain.HjUserRole;
import com.hujiang.project.zhgd.hjUserRole.service.IHjUserRoleService;
import com.hujiang.project.zhgd.moduleToPush.domain.ModuleToPush;
import com.hujiang.project.zhgd.moduleToPush.service.IModuleToPushService;
import com.hujiang.project.zhgd.sbArea.domain.OptionsLocation;
import com.hujiang.project.zhgd.sbArea.domain.OptionsUser;
import com.hujiang.project.zhgd.sbArea.service.ISbAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/provider/OptionsLocationApi",method = RequestMethod.POST)
public class OptionsLocationApi extends BaseController {
    @Autowired
    private ISbAreaService areaService;
    @Autowired
    private IHjProjectUserService userService;
    @Autowired
    private IHjUserRoleService userRoleService;
    @Autowired
    private IHjSystemUserService systemUserService;
    @Autowired
    private IHjRolePrivilegesService rolePrivilegesService;
    @Autowired
    private IModuleToPushService moduleToPushService;

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
            jsonObject.put("msg", "查询成功");
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
            jsonObject.put("msg", "查询成功");
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

    @PostMapping(value = "/getWarningPeopleList")
    public JSONObject getWarningPeopleList(@RequestParam("projectId")Integer projectId,
                                       @RequestParam(value = "filed",required = false)String filed){
        JSONObject jsonObject = new JSONObject();
        JSONArray userArray = new JSONArray();
        HjProjectUser projectUser = new HjProjectUser();
        projectUser.setProjectId(projectId);//p.getProjectId().intValue()
        List<HjProjectUser> projectUserList = userService.selectHjProjectUserList(projectUser);
        List<HjUserRole> userRoleList = userRoleService.selectHjUserRoleList(null);
        List<HjRolePrivileges> rolePrivilegesList = rolePrivilegesService.selectHjRolePrivilegesList(null);
        List<HjSystemUser> systemUserList = systemUserService.getCraneUserList(null,filed);
        ModuleToPush moduleToPush = new ModuleToPush();
        moduleToPush.setPrivilegesId(3);
        List<ModuleToPush> moduleToPushesList = moduleToPushService.selectModuleToPushList(moduleToPush);

        if (projectUserList == null || projectUserList.size() < 1) {
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",userArray);
            return jsonObject;
        }
        for (HjProjectUser hjProjectUser : projectUserList) {
            HjUserRole userRole = new HjUserRole();
            userRole.setUserId(hjProjectUser.getUserId());
            List<HjUserRole> myUserRoleList = userRoleList.stream()
                    .filter(a->a.getUserId().equals(hjProjectUser.getUserId()))
                    .collect(Collectors.toList());
            if (userRoleList == null || userRoleList.size() < 1) {
                continue;
            }
            List<HjRolePrivileges> myRolePrivilegesList = rolePrivilegesList.stream()
                    .filter(
                            a-> a.getPrivilegesId().equals(3) &&
                                    myUserRoleList.stream().map(b->b.getRoleId()).collect(Collectors.toList()).contains(a.getRoleId())
                    )
                    .collect(Collectors.toList());
            if(myRolePrivilegesList == null || myRolePrivilegesList.size() < 1) {
                continue;
            }
            List<HjSystemUser> mySystemUserList = systemUserList.stream()
                    .filter(a->a.getId().equals(hjProjectUser.getUserId()))
                    .collect(Collectors.toList());
            if(mySystemUserList == null || mySystemUserList.size() < 1){
                continue;
            }
            for (HjSystemUser systemUser:mySystemUserList){
                JSONObject userMap = new JSONObject();
                userMap.put("id",systemUser.getId());
                userMap.put("userName",systemUser.getUserName());
                userMap.put("userPhone",systemUser.getUserPhone());
                userMap.put("userAccount",systemUser.getUserAccount());
                if(!moduleToPushesList.stream().filter(a->a.getUserId().equals(systemUser.getId())).findAny().isPresent()) {
                    userMap.put("onOff",0);
                }
                else {
                    ModuleToPush moduleToPushs = moduleToPushesList.stream().filter(a->a.getUserId().equals(systemUser.getId())).findAny().get();
                    userMap.put("onOff",moduleToPushs.getOnOff());
                    userMap.put("bat",moduleToPushs.getBat());
                    userMap.put("fall",moduleToPushs.getFall());
                    userMap.put("move",moduleToPushs.getMove());
                }
                userArray.add(userMap);
            }
        }
        jsonObject.put("msg","查询成功");
        jsonObject.put("code",0);
        jsonObject.put("data",userArray);
        return jsonObject;
    }
}
