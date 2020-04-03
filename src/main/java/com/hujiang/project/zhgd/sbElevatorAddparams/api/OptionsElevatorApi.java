package com.hujiang.project.zhgd.sbElevatorAddparams.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.ConfigurationProperties;
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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@EnableConfigurationProperties(ConfigurationProperties.class)
@RestController
@RequestMapping(value = "/provider/OptionsElevatorApi", method = RequestMethod.POST)
public class OptionsElevatorApi {

    private static Logger logger = Logger.getLogger(OptionsElevatorApi.class);
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
    @Resource
    private ConfigurationProperties configurationProperties;

    //升降机模块权限
    private static final int PRIVILEGESID = 8;

    @PostMapping("/getElevatorList")
    public JSONObject getElevatorList(@RequestParam("projectId") Integer projectId) {
        JSONObject jsonObject = new JSONObject();
        List<OptionsElevator> optionsElevatorList = elevatorAddparamsService.getElevatorList(projectId);
        if (optionsElevatorList != null && optionsElevatorList.size() > 0) {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", optionsElevatorList);
        } else {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", -1);
            jsonObject.put("data", optionsElevatorList);
        }
        return jsonObject;
    }

    @PostMapping("/insertElevator")
    public JSONObject insertElevator(@RequestBody SbElevatorBinding elevator) throws IOException, URISyntaxException {
        JSONObject jsonObject = new JSONObject();

        JSONArray jsonArray1 = new JSONArray();
        JSONArray array = new JSONArray();
        JSONArray array1 = new JSONArray();

        elevator.setDeviceNo(Tools.encodeToMD5s(elevator.getHxzId()));
        elevator.setDname(elevator.getElevatorName());
        elevator.setHxzid(elevator.getHxzId());
        elevator.setPid(elevator.getProjectId());
        elevator.setUserid(elevator.getUserid());
        int result = iSbElevatorBindingService.insertSbElevatorBinding(elevator);

        //增加是否上传开关精确到项目
        if ("CAY".equals(elevator.getScznl())) {
            /** 获取城安院项目id&项目监管编号(市管项目)*/
            if(elevator.getGctype() == 1){

                SbElevatorBinding sbElevatorBinsing = new SbElevatorBinding();
                sbElevatorBinsing.setProjectId(elevator.getProjectId());
                List<SbElevatorBinding> list = iSbElevatorBindingService.list(sbElevatorBinsing);
                int i = list.size();
                //组装入参
                JSONObject json = setParamEleInfo(elevator, i);
                array.add(json);
                JSONObject object1 = new JSONObject();
                object1.put("PList", array);
                System.out.println("object1" + object1);
                String h = ZCAPIClient.SGXMCAY("lifter/ele_info", object1);

                //组装入参
                JSONObject object2 = setParamElePar(elevator);
                array1.add(object2);
                JSONObject jsonObject4 = new JSONObject();
                jsonObject4.put("PList", array1);
                String k = ZCAPIClient.SGXMCAY("lifter/ele_par", jsonObject4);

            }else if(elevator.getGctype() == 2){
                /** 对接cay(区管项目) */
                JSONObject qgJson = setEleInfoParamJson(elevator);
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(qgJson);
                JSONObject jsonObject3 = new JSONObject();
                jsonObject3.put("PList", jsonArray);
                //上报城安院升降机基本信息
                String f = ZCAPIClient.QGXMCAY("lifter/ele_info", jsonObject3);
                JSONObject cayInsertReturn = JSONObject.parseObject(f);
                if("OK".equals(cayInsertReturn.get("res"))){
                    logger.info("升降机基本信息上报城安院成功");
                }

                //入参
                JSONObject eleParJson = setEleParParamJson(elevator);
                jsonArray1.add(eleParJson);
                JSONObject jsonObject4 = new JSONObject();
                jsonObject4.put("PList", jsonArray1);
                //上报升降机参数
                String k = ZCAPIClient.QGXMCAY("lifter/ele_par", jsonObject4);
//                if(){
//
//                }
                System.out.println("上报城安院升降机基本信息状态：" + k);
            }

            SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
            sbElevatorBinding.setHxzId(sbElevatorBinding.getHxzId());
            List<SbElevatorBinding> sbElevatorBindingList = iSbElevatorBindingService.selectSbElevatorBindingList(sbElevatorBinding);
            elevator.setDname((sbElevatorBindingList.size() + 1) + "#升降机");
            elevator.setElevatorName(elevator.getElevatorName());
            elevator.setInstallCompany(elevator.getInstallCompany());
            elevator.setSerialNum(elevator.getSerialNum());
            sendElevatorToPERSONNEL.cayMachine(elevator);
        }

        if ("RCAJ".equals(elevator.getScznl())) {
            SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
            sbElevatorBinding.setHxzId(sbElevatorBinding.getHxzId());
            List<SbElevatorBinding> sbElevatorBindingList = iSbElevatorBindingService.selectSbElevatorBindingList(sbElevatorBinding);
            elevator.setDname((sbElevatorBindingList.size() + 1) + "#" + elevator.getDname());
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

    private JSONObject setParamEleInfo(SbElevatorBinding elevator, Integer i){
        //上报城安院升降机基本信息（开始）
        JSONObject json = new JSONObject();
        //所属项目编号
        json.put("ProjectID", elevator.getXmid());
        //项目监督编号
        json.put("Jdbh", elevator.getJdbh());
        //设备编号
        json.put("Dev_GUID", Tools.encodeToMD5s(elevator.getHxzId()));
        //设备用户编号
        json.put("Dev_UID", elevator.getElevatorName());
        //设备安装单位（监测设备厂商）
        json.put("Jc_dev_company", elevator.getInstallCompany());
        //广东省统一安装告知编号（使用登记号）
        json.put("Serial_Num", elevator.getSerialNum());
        //设备名称
        json.put("Dev_Name", i + "#升降机");
        //工程ID
        json.put("sub_id", elevator.getSubId());
        return json;
    }

    /**
     * @Author xieya
     * @Description  市管
     * @Date 2020/4/1 15:47
     * @param elevator
     * @return com.alibaba.fastjson.JSONObject
     **/
    private JSONObject setParamElePar(SbElevatorBinding elevator){
        JSONObject object2 = new JSONObject();
        // 所属项目
        object2.put("L_PGUID", elevator.getXmid());
        //项目监督编号
        object2.put("Jdbh", elevator.getJdbh());
        //设备编号
        object2.put("L_DGUID", elevator.getHxzid());
        //最大载重（KG)
        object2.put("L_Load_Capacity", elevator.getCapacity());
        //最大高度(M)
        object2.put("L_Height", elevator.getHeight());
        //工程ID
        object2.put("sub_id", elevator.getSubId());
        return object2;
    }

    /**
     * @Author xieya
     * @Description  上报升降机设备基本信息入参(区管)
     * @Date 2020/4/1 14:25
     * @param elevator
     * @return com.alibaba.fastjson.JSONObject
     **/
    private JSONObject setEleInfoParamJson(SbElevatorBinding elevator){
        JSONObject object = new JSONObject();
        //所属项目编号
        object.put("ProjectID", elevator.getXmid());
        //项目监督编号
        object.put("Jdbh", elevator.getJdbh());
        //设备编号
        object.put("Dev_GUID", Tools.encodeToMD5s(elevator.getHxzid()));
        //设备用户编号
        object.put("Dev_UID", elevator.getElevatorName());
        //设备安装单位（监测设备厂商）
        object.put("Jc_dev_company", elevator.getInstallCompany());
        //广东省统一安装告知编号（使用登记号）
        object.put("Serial_Num", elevator.getSerialNum());
        return object;
    }

    /**
     * @Author xieya
     * @Description 上报升降机参数入参(区管)
     * @Date 2020/4/1 14:36
     * @param elevator
     * @return com.alibaba.fastjson.JSONObject
     **/
    private JSONObject setEleParParamJson(SbElevatorBinding elevator){
        JSONObject object = new JSONObject();
        //所属项目
        object.put("L_PGUID", elevator.getXmid());
        //项目监督编号
        object.put("Jdbh", elevator.getJdbh());
        //设备编号
        object.put("L_DGUID", Tools.encodeToMD5s(elevator.getHxzid()));
        //最大载重（KG)
        object.put("L_Load_Capacity", elevator.getCapacity());
        //最大高度(M)
        object.put("L_Height", elevator.getHeight());
        return object;
    }

    @PostMapping("/updateElevator")
    public JSONObject updateElevator(@RequestBody SbElevatorBinding optionsElevator) {
        JSONObject jsonObject = new JSONObject();
        optionsElevator.setDeviceNo(Tools.encodeToMD5s(optionsElevator.getHxzId()));
        optionsElevator.setDname(optionsElevator.getElevatorName());
        optionsElevator.setHxzid(optionsElevator.getHxzId());
        int result = iSbElevatorBindingService.updateSbElevatorBinding(optionsElevator);
        if (result > 0) {
            jsonObject.put("msg", "成功");
            jsonObject.put("code", 0);
        } else {
            jsonObject.put("msg", "失败");
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }

    @PostMapping("/deleteElevator")
    public JSONObject deleteElevator(@RequestParam("id") Integer id, @RequestParam(value = "devCcrq", required = false) String devCcrq) throws IOException, URISyntaxException {
        JSONObject jsonObject = new JSONObject();

        if (devCcrq != null) {
            SbElevatorBinding sbElevatorBinding = iSbElevatorBindingService.selectSbElevatorBindingById(id);
            if (sbElevatorBinding != null) {
                cay.delete(sbElevatorBinding.getDeviceNo(), sbElevatorBinding.getXmid(), "升降机", devCcrq, sbElevatorBinding.getSubId());
            }
        }
        int result = elevatorAddparamsService.deleteElevator(id);

        if (result > 0) {
            jsonObject.put("msg", "成功");
            jsonObject.put("code", 0);
        } else {
            jsonObject.put("msg", "失败");
            jsonObject.put("code", -1);
        }
        return jsonObject;
    }

    @PostMapping("/getElevatorUserList")
    public JSONObject getElevatorUserList(@RequestParam("projectId") Integer projectId,
                                          @RequestParam(value = "filed", required = false) String filed
    ) {
        JSONObject jsonObject = new JSONObject();
        JSONArray userArray = new JSONArray();
        HjProjectUser projectUser = new HjProjectUser();
        projectUser.setProjectId(projectId);//p.getProjectId().intValue()
        List<HjProjectUser> projectUserList = userService.selectHjProjectUserList(projectUser);
        List<HjUserRole> userRoleList = userRoleService.selectHjUserRoleList(null);
        List<HjRolePrivileges> rolePrivilegesList = rolePrivilegesService.selectHjRolePrivilegesList(null);
        List<HjSystemUser> systemUserList = systemUserService.getCraneUserList(null, filed);
        ModuleToPush moduleToPush = new ModuleToPush();
        moduleToPush.setPrivilegesId(PRIVILEGESID);
        List<ModuleToPush> moduleToPushesList = moduleToPushService.selectModuleToPushList(moduleToPush);

        if (projectUserList == null || projectUserList.size() < 1) {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", userArray);
            return jsonObject;
        }
        for (HjProjectUser hjProjectUser : projectUserList) {
            HjUserRole userRole = new HjUserRole();
            userRole.setUserId(hjProjectUser.getUserId());
            List<HjUserRole> myUserRoleList = userRoleList.stream()
                    .filter(a -> a.getUserId().equals(hjProjectUser.getUserId()))
                    .collect(Collectors.toList());
            if (userRoleList == null || userRoleList.size() < 1) {
                continue;
            }
            List<HjRolePrivileges> myRolePrivilegesList = rolePrivilegesList.stream()
                    .filter(
                            a -> a.getPrivilegesId().equals(PRIVILEGESID) &&
                                    myUserRoleList.stream().map(b -> b.getRoleId()).collect(Collectors.toList()).contains(a.getRoleId())
                    )
                    .collect(Collectors.toList());
            if (myRolePrivilegesList == null || myRolePrivilegesList.size() < 1) {
                continue;
            }
            List<HjSystemUser> mySystemUserList = systemUserList.stream()
                    .filter(a -> a.getId().equals(hjProjectUser.getUserId()))
                    .collect(Collectors.toList());
            if (mySystemUserList == null || mySystemUserList.size() < 1) {
                continue;
            }
            for (HjSystemUser systemUser : mySystemUserList) {
                JSONObject userMap = new JSONObject();
                userMap.put("id", systemUser.getId());
                userMap.put("userName", systemUser.getUserName());
                userMap.put("userPhone", systemUser.getUserPhone());
                userMap.put("userAccount", systemUser.getUserAccount());
                if (!moduleToPushesList.stream().filter(a -> a.getUserId().equals(systemUser.getId())).findAny().isPresent()) {
                    userMap.put("onOff", 0);
                } else {
                    ModuleToPush moduleToPushs = moduleToPushesList.stream().filter(a -> a.getUserId().equals(systemUser.getId())).findAny().get();
                    userMap.put("onOff", moduleToPushs.getOnOff());
                }
                userArray.add(userMap);
            }
        }
        jsonObject.put("msg", "查询成功");
        jsonObject.put("code", 0);
        jsonObject.put("data", userArray);

        //projectUserList
        return jsonObject;
    }
}
