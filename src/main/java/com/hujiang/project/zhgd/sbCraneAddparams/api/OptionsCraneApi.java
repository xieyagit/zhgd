package com.hujiang.project.zhgd.sbCraneAddparams.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.common.utils.ThreadUtils;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
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
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import com.hujiang.project.zhgd.utils.Tools;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/provider/OptionsCraneApi",method = RequestMethod.POST)
public class OptionsCraneApi {

    private final Logger logger = LoggerFactory.getLogger(ZCAPIClient.class);
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
    private ISbCraneBindingService iSb;
    @Autowired
    private com.hujiang.project.cay.cay cay;
    @Autowired
    private SendCraneToPERSONNEL sendCraneToPERSONNEL;
    @Autowired
    private ISbCraneBindingService iSbCraneBindingService;
    @Autowired
    private Queue craneCayQueue;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

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
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", -1);
            jsonObject.put("data", optionsCraneList);
        }
        return jsonObject;
    }
    @PostMapping("/insertCrane")
    public JSONObject insertCrane(@RequestParam("craneName")String craneName,
                                  @RequestParam("hxzId")String hxzId,
                                  @RequestParam("projectId")Integer projectId,
                                  @RequestParam("serialNum")String serialNum,//广东省统一安装告 知编号（产权备案 编号）
                                  @RequestParam("tcMaxScope")String tcMaxScope,//最大幅度（M）
                                  @RequestParam("tcMaxHeight")String tcMaxHeight,//最大高度（M)
                                  @RequestParam("tcLoadCapacity")String tcLoadCapacity,//最大载重（kg）
                                  @RequestParam("tcLoadMoment")String tcLoadMoment,//额定起重力矩（N·m）
                                  @RequestParam(value = "jdbh",required = false)String jdbh,
                                  @RequestParam(value = "xmid",required = false)String xmid,
                                  @RequestParam(value = "subId",required = false)String subId,
                                  @RequestParam("scznl")String scznl,
                                  @RequestParam("manufacturerId")String manufacturerId,
                                  @RequestParam("installCompany")String installCompany //设备安装单位
    ) throws IOException, URISyntaxException {
        JSONObject jsonObject = new JSONObject();
        SbCraneBinding sbCraneBinding = new SbCraneBinding();
        sbCraneBinding.setDeviceNo(Tools.encodeToMD5s(hxzId));
        sbCraneBinding.setHxzid(hxzId);
        sbCraneBinding.setDname(craneName);
        sbCraneBinding.setPid(projectId);
        sbCraneBinding.setSerialNum(serialNum);
        sbCraneBinding.setTcMaxScope(Double.valueOf(tcMaxScope));
        sbCraneBinding.setTcMaxHeight(Double.valueOf(tcMaxHeight));
        sbCraneBinding.setTcLoadCapacity(Double.valueOf(tcLoadCapacity));
        sbCraneBinding.setTcLoadMoment(Double.valueOf(tcLoadMoment));
        sbCraneBinding.setJdbh(jdbh);
        sbCraneBinding.setXmid(xmid);
        sbCraneBinding.setSubId(subId);
        sbCraneBinding.setScznl(scznl);
        sbCraneBinding.setManufacturerId(Integer.parseInt(manufacturerId));
        sbCraneBinding.setInstallCompany(installCompany);

        JSONObject object = new JSONObject();
        object.put("hxzId",hxzId);
        object.put("craneName",craneName);
        object.put("projectId",projectId);
        object.put("SerialNum",serialNum);
        object.put("TCMaxScope",tcMaxScope);
        object.put("TCMaxHeight",tcMaxHeight);
        object.put("TCLoadCapacity",tcLoadCapacity);
        object.put("tcLoadMoment",tcLoadMoment);
        object.put("jdbh",jdbh);
        object.put("xmid",xmid);
        object.put("subId",subId);
        object.put("scznl",scznl);
        object.put("manufacturerId",manufacturerId);
        object.put("installCompany",installCompany);
  	    int result = iSb.insertSbCraneBinding(sbCraneBinding);
  	    if(scznl.equals("CAY")) {
            sbCraneBinding.setHxzid(sbCraneBinding.getHxzid());
            List<SbCraneBinding> sbCraneBindingList = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
            sbCraneBinding.setDname((sbCraneBindingList.size()+1)+"#塔吊");
            JmsMessageInfo<SbCraneBinding> messageMachine = new JmsMessageInfo<SbCraneBinding>();
            messageMachine.setBody(sbCraneBinding);
            messageMachine.setType(JmsMessageType.Machine);
            jmsMessagingTemplate.convertAndSend(craneCayQueue, JsonUtils.toJson(messageMachine));
        }
  	    if(scznl.equals("RCAJ")){
            SbCraneBinding craneBinding = new SbCraneBinding();
            craneBinding.setHxzid(sbCraneBinding.getHxzid());
            List<SbCraneBinding> sbCraneBindingList = iSbCraneBindingService.selectSbCraneBindingList(craneBinding);
            sbCraneBinding.setDname((sbCraneBindingList.size()+1)+"#"+craneName);
            sendCraneToPERSONNEL.rcajMachine(sbCraneBinding);
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

    @PostMapping("/updateCrane")
    public JSONObject updateCrane(@RequestParam("id")Integer id,
                                  @RequestParam("craneName")String craneName,
                                  @RequestParam("hxzId")String hxzId,
                                  @RequestParam("serialNum")String serialNum,//广东省统一安装告 知编号（产权备案 编号）
                                  @RequestParam("tcMaxScope")String tcMaxScope,//最大幅度（M）
                                  @RequestParam("tcMaxHeight")String tcMaxHeight,//最大高度（M)
                                  @RequestParam("tcLoadCapacity")String tcLoadCapacity,//最大载重（kg）
                                  @RequestParam("tcLoadMoment")String tcLoadMoment,//额定起重力矩（N·m）
                                  @RequestParam(value = "jdbh",required = false)String jdbh,
                                  @RequestParam(value = "xmid",required = false)String xmid,
                                  @RequestParam(value = "subId",required = false)String subId,
                                  @RequestParam("scznl")String scznl,
                                  @RequestParam("manufacturerId")String manufacturerId,
                                  @RequestParam("installCompany")String installCompany //设备安装单位
    ){
        JSONObject jsonObject = new JSONObject();
        SbCraneBinding sbCraneBinding = new SbCraneBinding();
        sbCraneBinding.setId(id);
        sbCraneBinding.setDeviceNo(Tools.encodeToMD5s(hxzId));
        sbCraneBinding.setHxzid(hxzId);
        sbCraneBinding.setDname(craneName);
        sbCraneBinding.setSerialNum(serialNum);
        sbCraneBinding.setTcMaxScope(Double.valueOf(tcMaxScope));
        sbCraneBinding.setTcMaxHeight(Double.valueOf(tcMaxHeight));
        sbCraneBinding.setTcLoadCapacity(Double.valueOf(tcLoadCapacity));
        sbCraneBinding.setTcLoadMoment(Double.valueOf(tcLoadMoment));
        sbCraneBinding.setJdbh(jdbh);
        sbCraneBinding.setXmid(xmid);
        sbCraneBinding.setSubId(subId);
        sbCraneBinding.setScznl(scznl);
        sbCraneBinding.setManufacturerId(Integer.parseInt(manufacturerId));
        sbCraneBinding.setInstallCompany(installCompany);
        int result = iSb.updateSbCraneBinding(sbCraneBinding);
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
    public JSONObject deleteCrane(@RequestParam("id")Integer id,@RequestParam(value = "devCcrq",required =false)String devCcrq) throws IOException, URISyntaxException {
        JSONObject jsonObject = new JSONObject();

        if (devCcrq != null) {
            SbCraneBinding sbCraneBinding = iSb.selectSbCraneBindingById(id);
            if (sbCraneBinding != null) {
                ThreadUtils.async(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            cay.delete(sbCraneBinding.getDeviceNo(), sbCraneBinding.getXmid(), "塔吊", devCcrq, sbCraneBinding.getSubId());
                        } catch (IOException e) {
                            logger.error("城安院错误(deleteCrane): " + e.getMessage() + ", 参数错误："+sbCraneBinding.getDeviceNo(), sbCraneBinding.getXmid(), "塔吊", devCcrq, sbCraneBinding.getSubId());
                        } catch (URISyntaxException e) {
                            logger.error("城安院错误(deleteCrane): " + e.getMessage() + ", 参数错误："+sbCraneBinding.getDeviceNo(), sbCraneBinding.getXmid(), "塔吊", devCcrq, sbCraneBinding.getSubId());
                        }
                    }
                });
            }
        }

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
}
