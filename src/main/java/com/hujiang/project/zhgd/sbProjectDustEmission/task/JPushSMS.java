package com.hujiang.project.zhgd.sbProjectDustEmission.task;

import com.hujiang.project.zhgd.hjProjectUser.domain.HjProjectUser;
import com.hujiang.project.zhgd.hjProjectUser.service.IHjProjectUserService;
import com.hujiang.project.zhgd.hjRolePrivileges.domain.HjRolePrivileges;
import com.hujiang.project.zhgd.hjRolePrivileges.service.IHjRolePrivilegesService;
import com.hujiang.project.zhgd.hjUserRole.domain.HjUserRole;
import com.hujiang.project.zhgd.hjUserRole.service.IHjUserRoleService;
import com.hujiang.project.zhgd.moduleToPush.domain.ModuleToPush;
import com.hujiang.project.zhgd.moduleToPush.service.IModuleToPushService;
import com.hujiang.project.zhgd.sbDustEmission.domain.BaseSmsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("qwert")
public class JPushSMS {

    @Autowired
    private IHjProjectUserService userService;
    @Autowired
    private IHjUserRoleService userRoleService;
    @Autowired
    private IHjRolePrivilegesService rolePrivilegesService;

    @Autowired
    private IModuleToPushService moduleToPushService;



    public void JPushAndJSMS(BaseSmsMessage smsMessage, Integer projectId){

        HjProjectUser projectUser = new HjProjectUser();
        projectUser.setProjectId(projectId);//p.getProjectId().intValue()
        //根据项目Id查询当前项目下的所有人员（ID）
        List<HjProjectUser> projectUserList = userService.selectHjProjectUserList(projectUser);
        List<HjUserRole> userRoleList = userRoleService.selectHjUserRoleList(null);
        List<HjRolePrivileges> rolePrivilegesList = rolePrivilegesService.selectHjRolePrivilegesList(null);
        ModuleToPush moduleToPush = new ModuleToPush();
        moduleToPush.setPrivilegesId(smsMessage.getPrivilegesId());
        List<ModuleToPush> moduleToPushes = moduleToPushService.getModuleToPushList(moduleToPush);
        List<SmsMessageInfo> canPushUserRoles = pushFilter(smsMessage.getPrivilegesId(), userRoleList, rolePrivilegesList, moduleToPushes, projectUserList);

        for (SmsMessageInfo smsMessageInfo : canPushUserRoles) {
            smsMessage.push(smsMessageInfo);
        }
    }

    private List<SmsMessageInfo> pushFilter(Integer privilegesId, List<HjUserRole> userRoleList, List<HjRolePrivileges> rolePrivilegesList, List<ModuleToPush> moduleToPushes, List<HjProjectUser> projectUserList) {
        List<ModuleToPush> pushes = moduleToPushes.stream()
                .filter(a -> a.getPrivilegesId().equals(privilegesId) && (a.getOnOff().equals(1) || a.getFall().equals(1) ||
                        a.getMove().equals(1) || a.getBat().equals(1)))
                .collect(Collectors.toList());

        List<HjRolePrivileges> rolePrivileges = rolePrivilegesList.stream().filter(
                a -> pushes.stream().map( b -> b.getPrivilegesId()).collect(Collectors.toList()).contains(a.getPrivilegesId())
        ).collect(Collectors.toList());

        List<HjUserRole> userRoles = userRoleList.stream().filter(
                a->rolePrivileges.stream().map( b -> b.getRoleId()).collect(Collectors.toList()).contains(a.getRoleId())
        ).collect(Collectors.toList());

        projectUserList = projectUserList.stream()
                .filter(
                        a->userRoles.stream().map(b->b.getUserId()).collect(Collectors.toList()).contains(a.getUserId())
                ).collect(Collectors.toList());

        List<SmsMessageInfo> smsMessageInfos = new ArrayList<SmsMessageInfo>();
        for(HjProjectUser user : projectUserList) {
            if(!pushes.stream().filter(a->a.getUserId().equals(user.getUserId())).findFirst().isPresent()) {
                continue;
            }
            ModuleToPush item = pushes.stream().filter(a->a.getUserId().equals(user.getUserId())).findFirst().get();
            SmsMessageInfo messageInfo = new SmsMessageInfo();
            messageInfo.setProjectId(user.getProjectId());
            messageInfo.setUserId(user.getUserId());
            messageInfo.setAlias(user.getAlias());
            messageInfo.setUserPhone(user.getUserPhone());
            messageInfo.setOnOff(item.getOnOff());
            messageInfo.setBat(item.getBat());
            messageInfo.setFall(item.getFall());
            messageInfo.setMove(item.getMove());
            smsMessageInfos.add(messageInfo);
        }

        return smsMessageInfos;
    }

}
