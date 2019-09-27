package com.hujiang.project.zhgd.hjSafetyAbarbeitung.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjSafetyArea.domain.HjSafetyArea;
import com.hujiang.project.zhgd.hjSafetyArea.domain.SafetyArea;
import com.hujiang.project.zhgd.hjSafetyArea.service.IHjSafetyAreaService;
import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;
import com.hujiang.project.zhgd.hjSystemUser.service.IHjSystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/provider/optionsSafetyApi",method = RequestMethod.POST)
public class OptionsSafetyApi {
    @Autowired
    private IHjSafetyAreaService areaService;
    @Autowired
    private IHjSystemUserService  systemUserService;

    /**
     * 列表
     * @param projectId
     * @return
     */
    @PostMapping("/getOptionsList")
    public JSONObject getOptionsList(@RequestParam(value = "projectId")Integer projectId){
        JSONObject jsonObject = new JSONObject();
        List<SafetyArea> safetyAreaList = areaService.getOptionsList(projectId);
        List<SafetyArea> userList = areaService.getUserByArea(null);
        JSONArray safetyArray = new JSONArray();
        if(safetyAreaList != null && safetyAreaList.size()>0){
            for(SafetyArea safetyArea:safetyAreaList){
                JSONObject safetyMap = new JSONObject();
                safetyMap.put("areaId",safetyArea.getAreaId());
                safetyMap.put("areaName",safetyArea.getAreaName());
                safetyMap.put("address",safetyArea.getAddress());
                safetyMap.put("constructionId",safetyArea.getConstructionId());
                safetyMap.put("constructionName",safetyArea.getConstructionName());
                List<SafetyArea> myUserList = userList.stream()
                        .filter(a->a.getAreaId().equals(safetyArea.getAreaId()))
                        .collect(Collectors.toList());
                if(myUserList == null && myUserList.size()<1){
                    continue;
                }
                JSONArray userArray = new JSONArray();
                for (SafetyArea user:myUserList){
                    JSONObject userMap = new JSONObject();
                    userMap.put("userId",user.getUserId());
                    userMap.put("userName",user.getUserName());
                    userArray.add(userMap);
                    }
                    safetyMap.put("userList",userArray);

                safetyArray.add(safetyMap);
            }
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",safetyArray);
        }
        else {
        jsonObject.put("msg","查询失败");
        jsonObject.put("code",-1);
        jsonObject.put("data", safetyArray);
    }

        return jsonObject;
    }

    /**
     * 用户列表
     * @param projectId
     * @return
     */
    @PostMapping("/getUserList")
    public JSONObject getUserList(@RequestParam(value = "projectId")Integer projectId){
        JSONObject jsonObject = new JSONObject();
        List<HjSystemUser> systemUserList = systemUserService.getUserList(projectId);
        if(systemUserList != null && systemUserList.size()>0){
            JSONArray jsonArray = new JSONArray();
            for (HjSystemUser systemUser : systemUserList){
                JSONObject projectMap = new JSONObject();
                projectMap.put("userId",systemUser.getId());
                projectMap.put("userName",systemUser.getUserName());
                jsonArray.add(projectMap);
            }
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",jsonArray);
        }else {
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }

    /**
     * 增加工业区
     * @return
     */
    @PostMapping("/addArea")
    public JSONObject addArea(@RequestParam("areaName")String areaName,
                              @RequestParam("address")String address,
                              @RequestParam("constructionId")Integer constructionId){
        JSONObject jsonObject = new JSONObject();
        HjSafetyArea hjSafetyArea = new HjSafetyArea();
        hjSafetyArea.setArea(areaName);
        hjSafetyArea.setAddress(address);
        int result = areaService.insertHjSafetyArea(hjSafetyArea);
        if(result >0){
            SafetyArea safetyArea = new SafetyArea();
            safetyArea.setAreaId(hjSafetyArea.getId());
            safetyArea.setConstructionId(constructionId);
            int result2 = areaService.insertConstructionArea(safetyArea);
            if(result2>0){
                jsonObject.put("msg","成功");
                jsonObject.put("code",0);
            }else {
                jsonObject.put("msg","失败");
                jsonObject.put("code",-1);
            }
        }else {
            jsonObject.put("msg","失败");
            jsonObject.put("code",-1);
        }
        return jsonObject;
    }
    /**
     * 修改工业区
     * @return
     */
    @PostMapping("/updateArea")
    public JSONObject updateArea(@RequestParam("areaId")Integer areaId,
                                @RequestParam("areaName")String areaName,
                              @RequestParam("address")String address,
                              @RequestParam("constructionId")Integer constructionId){
        JSONObject jsonObject = new JSONObject();
        HjSafetyArea hjSafetyArea = new HjSafetyArea();
        hjSafetyArea.setId(areaId);
        hjSafetyArea.setArea(areaName);
        hjSafetyArea.setAddress(address);
        int result = areaService.updateHjSafetyArea(hjSafetyArea);
        if(result >0){
            SafetyArea safetyArea = new SafetyArea();
            safetyArea.setAreaId(areaId);
            safetyArea.setConstructionId(constructionId);
            int result2 = areaService.updateConstructionArea(safetyArea);
            if(result2>0){
                jsonObject.put("msg","成功");
                jsonObject.put("code",0);
            }else {
                jsonObject.put("msg","失败");
                jsonObject.put("code",-1);
            }
        }else {
            jsonObject.put("msg","失败");
            jsonObject.put("code",-1);
        }
        return jsonObject;
    }

    /**
     * 增加负责人
     * @param userId
     * @param areaId
     * @return
     */
    @PostMapping("/addAreaUser")
    public JSONObject addAreaUser(@RequestParam("userId")Integer userId,
                              @RequestParam("areaId")Integer areaId){
        JSONObject jsonObject = new JSONObject();
        SafetyArea safetyArea = new SafetyArea();
        safetyArea.setAreaId(areaId);
        safetyArea.setUserId(userId);
        SafetyArea areaUser = areaService.getAreaUser(safetyArea);
        if(areaUser == null){
            areaService.insertAreaUser(safetyArea);
            jsonObject.put("msg","成功");
            jsonObject.put("code",0);
        }else {
            jsonObject.put("msg","失败");
            jsonObject.put("code",-1);
        }
        return jsonObject;
    }

    /**
     * 删除负责人
     * @return
     */
    @PostMapping("deleteAreaUser")
    public JSONObject deleteAreaUser(@RequestParam("userId")Integer userId,
                                 @RequestParam("areaId")Integer areaId){
        JSONObject jsonObject = new JSONObject();
        int result = areaService.deleteAreaUser(userId, areaId);
        if(result>0){
            jsonObject.put("msg","成功");
            jsonObject.put("code",0);
        }else {
            jsonObject.put("msg","失败");
            jsonObject.put("code",-1);
        }
        return jsonObject;
    }

    /**
     * 删除工业区
     * @return
     */
    @PostMapping("deleteArea")
    public JSONObject deleteArea(@RequestParam("areaId")Integer areaId){
        JSONObject jsonObject = new JSONObject();
        areaService.deleteAreaAndUser(areaId);
        areaService.deleteAreaAndConstruction(areaId);
        int area = areaService.deleteArea(areaId);
        if(area>0){
            jsonObject.put("msg","成功");
            jsonObject.put("code",0);
        }else {
            jsonObject.put("msg","失败");
            jsonObject.put("code",-1);
        }

        return jsonObject;
    }
}
