package com.hujiang.project.zhgd.sbElevatorAddparams.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;
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
    private ISbElevatorBindingService iSbElevatorBindingService;
    @Autowired
    private com.hujiang.project.cay.cay cay;
    @Resource
    private SendElevatorToPERSONNEL sendElevatorToPERSONNEL;

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
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", -1);
            jsonObject.put("data", optionsElevatorList);
        }
        return jsonObject;
    }

    @PostMapping("/insertElevator")
    public JSONObject insertElevator(@RequestBody SbElevatorBinding elevator) throws IOException, URISyntaxException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONArray jsonArray1 = new JSONArray();
        JSONArray array = new JSONArray();
        JSONArray array1 = new JSONArray();
       
        elevator.setDeviceNo(Tools.encodeToMD5s(elevator.getHxzId()));
        elevator.setDname(elevator.getElevatorName());
        elevator.setHxzid(elevator.getHxzId());
        elevator.setPid(elevator.getProjectId());
        elevator.setUserid(4018);
        int result = iSbElevatorBindingService.insertSbElevatorBinding(elevator);

        /** 获取城安院项目id&项目监管编号(市管项目)*/
        if (elevator.getScznl().equals("CAY")) {
            if (elevator.getSubId() != null) {
                //上报城安院升降机基本信息（开始）
                JSONObject json = new JSONObject();
                json.put("ProjectID", elevator.getXmid());//所属项目编号
                json.put("Jdbh", elevator.getJdbh());//项目监督编号
                json.put("Dev_GUID", Tools.encodeToMD5s(elevator.getHxzId()));//设备编号
                json.put("Dev_UID", elevator.getElevatorName());//设备用户编号
                json.put("Jc_dev_company", elevator.getInstallCompany());//设备安装单位（监测设备厂商）
                json.put("Serial_Num", elevator.getSerialNum());//广东省统一安装告知编号（使用登记号）
	            SbElevatorBinding sbElevatorBinsing = new SbElevatorBinding();
	            sbElevatorBinsing.setProjectId(elevator.getProjectId());
                List<SbElevatorBinding> list = iSbElevatorBindingService.list(sbElevatorBinsing);
                int i = list.size() ;
                json.put("Dev_Name", i + "#升降机");//设备名称
                json.put("sub_id", elevator.getSubId());//工程ID
                array.add(json);
                JSONObject object1 = new JSONObject();
                object1.put("PList", array);
                String h = ZCAPIClient.SGXMCAY("lifter/ele_info", object1);
                System.out.println("上报城安院状态：" + h);
                //上报城安院升降机基本信息（结束）
                //上报城安院升降机参数信息 （开始）
                JSONObject object2 = new JSONObject();
                object2.put("L_PGUID", elevator.getXmid());// 所属项目
                object2.put("Jdbh", elevator.getJdbh());//项目监督编号
                object2.put("L_DGUID", elevator.getHxzid());//设备编号
                object2.put("L_Load_Capacity", elevator.getCapacity());//最大载重（KG)
                object2.put("L_Height", elevator.getHeight());//最大高度(M)
                object2.put("sub_id", elevator.getSubId());//工程ID
                array1.add(object2);
                JSONObject jsonObject4 = new JSONObject();
                jsonObject4.put("PList", array1);
                //上报城安院升降机参数信息
                String k = ZCAPIClient.SGXMCAY("lifter/ele_par", jsonObject4);
                System.out.println("上报城安院升降机基本信息状态：" + k);
                //上报城安院升降机参数信息 （结束）
            } else {
                /** 获取城安院项目id&项目监管编号(区管项目) */
                JSONObject object1 = new JSONObject();
                object1.put("ProjectID", elevator.getXmid());//所属项目编号
                object1.put("Jdbh", elevator.getJdbh());//项目监督编号
                object1.put("Dev_GUID", Tools.encodeToMD5s(elevator.getHxzid()));//设备编号
                object1.put("Dev_UID", elevator.getElevatorName());//设备用户编号
                object1.put("Jc_dev_company", elevator.getInstallCompany());//设备安装单位（监测设备厂商）
                object1.put("Serial_Num", elevator.getSerialNum());//广东省统一安装告知编号（使用登记号）
                jsonArray.add(object1);
                JSONObject jsonObject3 = new JSONObject();
                jsonObject3.put("PList", jsonArray);
                //上报城安院升降机基本信息
                String f = ZCAPIClient.QGXMCAY("lifter/ele_info", jsonObject3);
                System.out.println("上报城安院升降机基本信息状态：" + f);
                JSONObject object = new JSONObject();
                object.put("L_PGUID", elevator.getXmid());//所属项目
                object.put("Jdbh", elevator.getJdbh());//项目监督编号
                object.put("L_DGUID", Tools.encodeToMD5s(elevator.getHxzid()));//设备编号
                object.put("L_Load_Capacity", elevator.getCapacity());//最大载重（KG)
                object.put("L_Height", elevator.getHeight());//最大高度(M)
                jsonArray1.add(object);
                JSONObject jsonObject4 = new JSONObject();
                jsonObject4.put("PList", jsonArray1);
                //上报城安院升降机参数信息
                String k = ZCAPIClient.QGXMCAY("lifter/ele_par", jsonObject4);
                System.out.println("上报城安院升降机基本信息状态：" + k);
            }
        }else if(elevator.getScznl().equals("RCAJ")){
            SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
            sbElevatorBinding.setHxzId(sbElevatorBinding.getHxzId());
            List<SbElevatorBinding> sbElevatorBindingList = iSbElevatorBindingService.selectSbElevatorBindingList(sbElevatorBinding);
            elevator.setDname((sbElevatorBindingList.size()+1)+"#"+elevator.getDname());
            sendElevatorToPERSONNEL.rcajMachine(elevator);
        }
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
    public JSONObject updateElevator(@RequestBody SbElevatorBinding optionsElevator){
        JSONObject jsonObject = new JSONObject();
        optionsElevator.setDeviceNo(Tools.encodeToMD5s(optionsElevator.getHxzId()));
        optionsElevator.setDname(optionsElevator.getElevatorName());
        optionsElevator.setHxzid(optionsElevator.getHxzId());
        int result = iSbElevatorBindingService.updateSbElevatorBinding(optionsElevator);
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
    public JSONObject deleteElevator(@RequestParam("id")Integer id,@RequestParam(value = "devCcrq",required =false)String devCcrq) throws IOException, URISyntaxException {
        JSONObject jsonObject = new JSONObject();

        if (devCcrq != null) {
            SbElevatorBinding sbElevatorBinding = iSbElevatorBindingService.selectSbElevatorBindingById(id);
            if (sbElevatorBinding != null) {
                cay.delete(sbElevatorBinding.getDeviceNo(), sbElevatorBinding.getXmid(), "升降机", devCcrq, sbElevatorBinding.getSubId());
            }
        }
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
