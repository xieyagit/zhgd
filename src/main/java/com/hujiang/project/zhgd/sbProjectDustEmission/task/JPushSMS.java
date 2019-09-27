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

        //如果当前pm2.5大于阈值推送给当前项目具有扬尘权限的人员
        HjProjectUser projectUser = new HjProjectUser();
        projectUser.setProjectId(projectId);//p.getProjectId().intValue()
        //根据项目Id查询当前项目下的所有人员（ID）
        List<HjProjectUser> projectUserList = userService.selectHjProjectUserList(projectUser);
        List<HjUserRole> userRoleList = userRoleService.selectHjUserRoleList(null);
        List<HjRolePrivileges> rolePrivilegesList = rolePrivilegesService.selectHjRolePrivilegesList(null);
        ModuleToPush moduleToPush = new ModuleToPush();
        moduleToPush.setOnOff(1);
        moduleToPush.setPrivilegesId(smsMessage.getPrivilegesId());
        List<ModuleToPush> moduleToPushes = moduleToPushService.getModuleToPushList(moduleToPush);
        List<HjProjectUser> canPushUserRoles = pushFilter(smsMessage.getPrivilegesId(), userRoleList, rolePrivilegesList, moduleToPushes, projectUserList);

        for (HjProjectUser hjProjectUser : canPushUserRoles) {
            smsMessage.push(hjProjectUser.getUserId(), projectId, hjProjectUser.getAlias(),
                    false, hjProjectUser.getUserPhone());
        }
    }

    private List<HjProjectUser> pushFilter(Integer privilegesId, List<HjUserRole> userRoleList, List<HjRolePrivileges> rolePrivilegesList, List<ModuleToPush> moduleToPushes, List<HjProjectUser> projectUserList) {
        List<ModuleToPush> pushes = moduleToPushes.stream()
                .filter(a -> a.getPrivilegesId().equals(privilegesId) && a.getOnOff().equals(1))
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

        return projectUserList;
    }

}
