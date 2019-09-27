package com.hujiang.project.zhgd.sbElevatorAddparams.api;

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
import com.hujiang.project.zhgd.sbElevatorAddparams.domain.OptionsElevator;
import com.hujiang.project.zhgd.sbElevatorAddparams.service.ISbElevatorAddparamsService;
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import com.hujiang.project.zhgd.sbElevatorBinding.service.ISbElevatorBindingService;
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
@RequestMapping(value = "/provider/OptionsElevatorApi",method = RequestMethod.POST)
public class OptionsElevatorApi {
    @Autowired
    private ISbElevatorAddparamsService elevatorAddparamsService;
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
    @Autowired
    private ISbElevatorBindingService iSbElevatorBindingService;

    //升降机模块权限
    private static final  int PRIVILEGESID=8;

    @PostMapping("/getElevatorList")
    public JSONObject getElevatorList(@RequestParam("projectId")Integer projectId){
        JSONObject jsonObject = new JSONObject();
        List<OptionsElevator> optionsElevatorList = elevatorAddparamsService.getElevatorList(projectId);
        if(optionsElevatorList != null && optionsElevatorList.size()>0){
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",optionsElevatorList);
        }
        else {
            jsonObject.put("msg", "查询失败");
            jsonObject.put("code", -1);
            jsonObject.put("data", optionsElevatorList);
        }
        return jsonObject;
    }

    @PostMapping("/insertElevator")
    public JSONObject insertElevator(@RequestParam("elevatorName")String elevatorName,
                                        @RequestParam("hxzId")String hxzId,
                                        @RequestParam("projectId")Integer projectId,
                                        @RequestParam("serialNum")String serialNum,
                                        @RequestParam("capacity")String capacity,
                                        @RequestParam("height")String height) throws IOException, URISyntaxException {
        JSONObject jsonObject = new JSONObject();
        OptionsElevator optionsElevator = new OptionsElevator();
        optionsElevator.setDeviceNo(Tools.encodeToMD5s(hxzId));
        optionsElevator.setHxzId(hxzId);
        optionsElevator.setElevatorName(elevatorName);
        optionsElevator.setProjectId(projectId);

        HjProject hjProject = iHjProjectService.selectHjProjectById(projectId);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("curpage","1");
        jsonObject1.put("name",hjProject.getProjectName());

        /** 获取城安院项目id&项目监管编号(区管项目)*/
        JSONObject jsonObject2 = ZCAPIClient.reportedCay2019s("authorize/getProjInfos",jsonObject1);
        System.out.println(jsonObject2);
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        if (jsonObject2 != null) {
            JSONObject object1 = new JSONObject();
            object1.put("ProjectID",jsonObject2.getString("xmid"));//所属项目编号
            object1.put("Jdbh",jsonObject2.getString("jdbh"));//项目监督编号
            object1.put("Dev_GUID",Tools.encodeToMD5s(hxzId));//设备编号
            object1.put("Dev_UID",elevatorName);//设备用户编号
            object1.put("Jc_dev_company","深圳一指通智能科技有限公司");//设备安装单位（监测设备厂商）
            object1.put("Serial_Num",serialNum);//广东省统一安装告知编号（使用登记号）
            jsonArray.add(object1);
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("PList",jsonArray);
            //上报城安院升降机基本信息
            String f = ZCAPIClient.QGXMCAY("lifter/ele_info",jsonObject3);
            System.out.println("上报城安院升降机基本信息状态："+f);
            JSONObject object = new JSONObject();
            object.put("L_PGUID",jsonObject2.getString("xmid"));//所属项目
            object.put("Jdbh",jsonObject2.getString("jdbh"));//项目监督编号
            object.put("L_DGUID",Tools.encodeToMD5s(hxzId));//设备编号
            object.put("L_Load_Capacity",capacity);//最大载重（KG)
            object.put("L_Height",height);//最大高度(M)
            jsonArray1.add(object);
            JSONObject jsonObject4 = new JSONObject();
            jsonObject4.put("PList",jsonArray1);
            //上报城安院升降机参数信息
            String k = ZCAPIClient.QGXMCAY("lifter/ele_par",jsonObject4);
            System.out.println("上报城安院升降机基本信息状态："+k);
        }

        /** 获取城安院项目id&项目监管编号(市管项目)*/
        JSONArray array = new JSONArray();
        JSONArray array1 = new JSONArray();
        String xmid = ZCAPIClient.reportedCay2019("authorize/getProjInfos",jsonObject1);
        if (xmid != null) {
            //上报城安院升降机基本信息（开始）
            JSONObject j = new JSONObject();
            j.put("pguid", xmid);
            JSONObject object = ZCAPIClient.reportedCay("authorize/getGcbyProj", j);
            JSONArray data = object.getJSONArray("res");
            JSONObject datas = data.getJSONObject(0);
            JSONObject json = new JSONObject();
            json.put("ProjectID",datas.getString("xmid"));//所属项目编号
            json.put("Jdbh",datas.getString("jdbh"));//项目监督编号
            json.put("Dev_GUID",Tools.encodeToMD5s(hxzId));//设备编号
            json.put("Dev_UID",elevatorName);//设备用户编号
            json.put("Jc_dev_company","深圳一指通智能科技有限公司");//设备安装单位（监测设备厂商）
            json.put("Serial_Num",serialNum);//广东省统一安装告知编号（使用登记号）
            String hxzid = null;
            List<SbElevatorBinding> list = iSbElevatorBindingService.list(projectId,hxzid);
            int i = list.size()+1;
            json.put("Dev_Name",i+"#升降机");//设备名称
            json.put("sub_id",datas.getString("gcid"));//工程ID
            array.add(json);
            JSONObject object1 = new JSONObject();
            object1.put("PList",array);
            String h = ZCAPIClient.SGXMCAY("lifter/ele_info",object1);
            System.out.println("上报城安院状态："+h);
            //上报城安院升降机基本信息（结束）
            //上报城安院升降机参数信息 （开始）
            JSONObject object2 = new JSONObject();
            object2.put("L_PGUID",jsonObject2.getString("xmid"));// 所属项目
            object2.put("Jdbh",jsonObject2.getString("jdbh"));//项目监督编号
            object2.put("L_DGUID",hxzId);//设备编号
            object2.put("L_Load_Capacity",capacity);//最大载重（KG)
            object2.put("L_Height",height);//最大高度(M)
            object2.put("sub_id",datas.getString("gcid"));//工程ID
            array1.add(object2);
            JSONObject jsonObject4 = new JSONObject();
            jsonObject4.put("PList",array1);
            //上报城安院升降机参数信息
            String k = ZCAPIClient.SGXMCAY("lifter/ele_par",jsonObject4);
            System.out.println("上报城安院升降机基本信息状态："+k);
            //上报城安院升降机参数信息 （结束）
        }
        int result = elevatorAddparamsService.insertElevator(optionsElevator);
        if (result > 0) {
            jsonObject.put("msg", "成功");
            jsonObject.put("code", 0);
        } else {
            jsonObject.put("msg", "失败");
            jsonObject.put("code", -1);
        }

        return jsonObject;
    }

    @PostMapping("/updateElevator")
    public JSONObject updateElevator(@RequestParam("id")Integer id,
                                  @RequestParam("elevatorName")String elevatorName,
                                  @RequestParam("hxzId")String hxzId){
        JSONObject jsonObject = new JSONObject();
        OptionsElevator optionsElevator = new OptionsElevator();
        optionsElevator.setDeviceNo(Tools.encodeToMD5s(hxzId));
        optionsElevator.setHxzId(hxzId);
        optionsElevator.setElevatorName(elevatorName);
        optionsElevator.setId(id);
        int result = elevatorAddparamsService.updateElevator(optionsElevator);
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

    @PostMapping("/deleteElevator")
    public JSONObject deleteElevator(@RequestParam("id")Integer id){
        JSONObject jsonObject = new JSONObject();
        int result = elevatorAddparamsService.deleteElevator(id);
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
    @PostMapping("/getElevatorUserList")
    public JSONObject getElevatorUserList(@RequestParam("projectId")Integer projectId,
                                       @RequestParam(value = "filed",required = false)String filed
                                        ){
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
}
