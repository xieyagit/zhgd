package com.hujiang.project.zhgd.sbCraneAddparams.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectUser.domain.HjProjectUser;
import com.hujiang.project.zhgd.hjProjectUser.service.IHjProjectUserService;
import com.hujiang.project.zhgd.hjRolePrivileges.domain.HjRolePrivileges;
import com.hujiang.project.zhgd.hjRolePrivileges.service.IHjRolePrivilegesService;
import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;
import com.hujiang.project.zhgd.hjSystemUser.service.IHjSystemUserService;
import com.hujiang.project.zhgd.hjUserRole.domain.HjUserRole;
import com.hujiang.project.zhgd.hjUserRole.service.IHjUserRoleService;
import com.hujiang.project.zhgd.moduleToPush.domain.ModuleToPush;
import com.hujiang.project.zhgd.moduleToPush.service.IModuleToPushService;
import com.hujiang.project.zhgd.sbCraneAddparams.domain.OptionsCrane;
import com.hujiang.project.zhgd.sbCraneAddparams.service.ISbCraneAddparamsService;
import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import com.hujiang.project.zhgd.sbCraneBinding.service.ISbCraneBindingService;
import com.hujiang.project.zhgd.utils.Tools;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/provider/OptionsCraneApi",method = RequestMethod.POST)
public class OptionsCraneApi {
    @Autowired
    private ISbCraneAddparamsService craneAddparamsService;
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
    @Autowired
    private IHjProjectService iHjProjectService;

    //塔吊模块权限
    private static final  int PRIVILEGESID=7;

    @PostMapping("/getCraneList")
    public JSONObject getCraneList(@RequestParam("projectId")Integer projectId){
        JSONObject jsonObject = new JSONObject();
        List<OptionsCrane> optionsCraneList = craneAddparamsService.getOptionsCraneList(projectId);
        if(optionsCraneList != null && optionsCraneList.size()>0){
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",optionsCraneList);
        }
        else {
            jsonObject.put("msg", "查询失败");
            jsonObject.put("code", -1);
            jsonObject.put("data", optionsCraneList);
        }
        return jsonObject;
    }
    @PostMapping("/insertCrane")
    public JSONObject insertCrane(@RequestParam("craneName")String craneName,
                                  @RequestParam("hxzId")String hxzId,
                                  @RequestParam("projectId")Integer projectId,
                                  @RequestParam("SerialNum")String SerialNum,//广东省统一安装告 知编号（产权备案 编号）
                                  @RequestParam("TCMaxScope")String TCMaxScope,//最大幅度（M）
                                  @RequestParam("TCMaxHeight")String TCMaxHeight,//最大高度（M)
                                  @RequestParam("TCLoadCapacity")String TCLoadCapacity,//最大载重（kg）
                                  @RequestParam("tcLoadMoment")String tcLoadMoment//额定起重力矩（N·m）
    ) throws IOException, URISyntaxException {
        JSONObject jsonObject = new JSONObject();
        OptionsCrane optionsCrane = new OptionsCrane();
        optionsCrane.setDeviceNo(Tools.encodeToMD5s(hxzId));
        optionsCrane.setHxzId(hxzId);
        optionsCrane.setCraneName(craneName);
        optionsCrane.setProjectId(projectId);
        JSONObject object = new JSONObject();
        object.put("hxzId",hxzId);
        object.put("craneName",craneName);
        object.put("projectId",projectId);
        object.put("SerialNum",SerialNum);
        object.put("TCMaxScope",TCMaxScope);
        object.put("TCMaxHeight",TCMaxHeight);
        object.put("TCLoadCapacity",TCLoadCapacity);
        object.put("tcLoadMoment",tcLoadMoment);
        String f = TDCAY(object);
        JSONObject object1 = JSONObject.parseObject(f);
        if (object1.getString("errcode").equals("0")) {
            int result = craneAddparamsService.insertCrane(optionsCrane);
            if (result > 0) {
                jsonObject.put("msg", "成功");
                jsonObject.put("code", 0);
            } else {
                jsonObject.put("msg", "失败");
                jsonObject.put("code", -1);
            }
        }else{
            jsonObject.put("msg", "保存异常："+object1.getString("msg"));
            jsonObject.put("code", -2);
        }
        return jsonObject;
    }

    @PostMapping("/updateCrane")
    public JSONObject updateCrane(@RequestParam("id")Integer id,
                                  @RequestParam("craneName")String craneName,
                                  @RequestParam("hxzId")String hxzId){
        JSONObject jsonObject = new JSONObject();
        OptionsCrane optionsCrane = new OptionsCrane();
        optionsCrane.setDeviceNo(Tools.encodeToMD5s(hxzId));
        optionsCrane.setHxzId(hxzId);
        optionsCrane.setCraneName(craneName);
        optionsCrane.setId(id);
        int result = craneAddparamsService.updateCrane(optionsCrane);
        if(result>0){
            jsonObject.put("msg","成功");
            jsonObject.put("code",0);
        }
        else {
            jsonObject.put("msg", "失败");
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }

    @PostMapping("/deleteCrane")
    public JSONObject deleteCrane(@RequestParam("id")Integer id){
        JSONObject jsonObject = new JSONObject();
        int result = craneAddparamsService.deleteCrane(id);
        if(result>0){
            jsonObject.put("msg","成功");
            jsonObject.put("code",0);
        }
        else {
            jsonObject.put("msg", "失败");
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }

    @PostMapping("/getCraneUserList")
    public JSONObject getCraneUserList(@RequestParam("projectId")Integer projectId,
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
        moduleToPush.setPrivilegesId(PRIVILEGESID);
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
                        a-> a.getPrivilegesId().equals(PRIVILEGESID) &&
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
                }
                userArray.add(userMap);
            }
        }
        jsonObject.put("msg","查询成功");
        jsonObject.put("code",0);
        jsonObject.put("data",userArray);

        //projectUserList
        return jsonObject;
    }

    /**塔吊上传至城安院*/
    @Autowired
    private ISbCraneBindingService iSb;
    public String TDCAY(JSONObject jsonObject) throws IOException, URISyntaxException {
        HjProject hjProject = iHjProjectService.selectHjProjectById(Integer.valueOf(jsonObject.getString("projectId")));
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("curpage","1");
        jsonObject1.put("name",hjProject.getProjectName());
        JSONArray array = new JSONArray();
        JSONArray array1 = new JSONArray();
        String f = null;
        /** (市管项目)*/
        String xmid = ZCAPIClient.reportedCay2019("authorize/getProjInfos",jsonObject1);
        if (xmid != null) {
            //上报城安院基本信息（开始）
            JSONObject j = new JSONObject();
            j.put("pguid", xmid);
            JSONObject object = ZCAPIClient.reportedCay("authorize/getGcbyProj", j);
            JSONArray data = object.getJSONArray("res");
            JSONObject datas = data.getJSONObject(0);
            JSONObject json = new JSONObject();
            json.put("ProjectID", datas.getString("xmid"));//所属项目编号
            json.put("Jdbh", datas.getString("jdbh"));//项目监督编号
            json.put("Dev_GUID", Tools.encodeToMD5s(jsonObject.getString("hxzId")));//设备编号
            json.put("Dev_UID", jsonObject.getString("craneName"));//设备用户编号
            List<SbCraneBinding> list = iSb.selectByHxzId(jsonObject.getInteger("projectId"));
            int k = list.size()+1;
            json.put("Dev_Name", k+"#塔吊");//设备名称（命名规 则：阿拉伯数字# 塔吊；示例：1#塔 吊 2#塔吊，同个 项目下数字不能 重复）
            json.put("Jc_dev_company", "深圳一指通智能科技有限公司");//监测设备厂商
            json.put("Serial_Num", jsonObject.getString("SerialNum"));//广东省统一安装告 知编号（产权备案 编号）
            json.put("sub_id", datas.getString("gcid"));//工程 id
            array.add(json);
            JSONObject object1 = new JSONObject();
            object1.put("PList",array);
            f = ZCAPIClient.SGXMCAY("tower/towerInfo",object1);
            //上报城安院参数信息
            JSONObject object2 = new JSONObject();
            object2.put("TC_PGUID", datas.getString("xmid"));//所属项目编号
            object2.put("Jdbh", datas.getString("jdbh"));//项目监督编号
            object2.put("TC_GUID", Tools.encodeToMD5s(jsonObject.getString("hxzId")));//设备编号
            object2.put("TC_MaxScope",jsonObject.getString("TCMaxScope"));//最大幅度(M)
            object2.put("TC_MaxHeight",jsonObject.getString("TCMaxHeight"));//最大高度（M）
            object2.put("TC_LoadCapacity",jsonObject.getString(" TCLoadCapacity"));//最大载重（KG）
            object2.put("Tower_type",2);//塔机类型（ 1-动臂塔式起重机， 2-其他, 3-塔头塔式起重机， 4-平头塔式起重机）
            object2.put("tc_load_moment",jsonObject.getString("tcLoadMoment"));//额定起重力矩（N·m）
            object2.put("sub_id", datas.getString("gcid"));//工程 id
            JSONObject object3 = new JSONObject();
            JSONArray array2 = new JSONArray();
            array2.add(object2);
            object3.put("PList",array2);
            f += ZCAPIClient.SGXMCAY("tower/towerParams",object3);
        }
        /** (区管项目)*/
        JSONObject jsonObject2 = ZCAPIClient.reportedCay2019s("authorize/getProjInfos",jsonObject1);
        if (jsonObject2 != null) {
            JSONObject object1 = new JSONObject();
            object1.put("ProjectID", jsonObject2.getString("xmid"));//所属项目编号
            object1.put("Jdbh", jsonObject2.getString("jdbh"));//项目监督编号
            object1.put("Dev_GUID", Tools.encodeToMD5s(jsonObject.getString("hxzId")));//设备编号
            object1.put("Dev_UID", jsonObject.getString("craneName"));//设备用户编号
            object1.put("Jc_dev_company", "深圳一指通智能科技有限公司");//监测设备厂商
            object1.put("Serial_Num", jsonObject.getString("SerialNum"));//广东省统一安装告 知编号（产权备案 编号）
            array1.add(object1);
            JSONObject object = new JSONObject();
            object.put("PList",array1);
            f = ZCAPIClient.QGXMCAY("tower/towerInfo",object);
            System.out.println("上报城安院塔吊基本信息状态："+f);
            //上报城安院参数信息
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("TC_PGUID",jsonObject2.getString("xmid"));//所属项目ID
            jsonObject3.put("Jdbh",jsonObject2.getString("jdbh"));//项目监督编号
            jsonObject3.put("TC_GUID",Tools.encodeToMD5s(jsonObject.getString("hxzId")));//设备号
            jsonObject3.put("TC_MaxScope",jsonObject.getString("TCMaxScope"));//最大幅度
            jsonObject3.put("TC_MaxHeight",jsonObject.getString("TCMaxHeight"));//最大高度
            jsonObject3.put("TC_LoadCapacity",jsonObject.getString("TCLoadCapacity"));//最大载重（KG)
            jsonObject3.put("Tower_type",2);//塔机类型（ 1-动臂塔式起重机， 2-其他, 3-塔头塔式起重机， 4-平头塔式起重机）
            jsonObject3.put("tc_load_moment",jsonObject.getString("tcLoadMoment"));//额定起重力矩（N·m）
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject3);
            JSONObject jsonObject4 = new JSONObject();
            jsonObject4.put("PList",jsonArray);
            f += ZCAPIClient.QGXMCAY("tower/towerParams",jsonObject4);
        }
        return f;
    }
}
