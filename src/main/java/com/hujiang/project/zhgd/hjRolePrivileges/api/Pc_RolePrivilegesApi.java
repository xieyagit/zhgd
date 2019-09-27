package com.hujiang.project.zhgd.hjRolePrivileges.api;


import com.hujiang.project.zhgd.hjRolePrivileges.domain.PrivilegesRoleParam;
import com.hujiang.project.zhgd.hjRolePrivileges.service.IHjRolePrivilegesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: Provider01
 * @description: 角色-权限
 * @create: 2019-05-21 15:32
 **/
@RestController
@RequestMapping(value = "/provider/RolePrivileges/pc", method = RequestMethod.POST)
public class Pc_RolePrivilegesApi {


    @Autowired
    private IHjRolePrivilegesService hjRolePrivilegesService;


    /**
     * 角色添加权限
     * @param roleId 角色id
     * @param privilegesId 权限id
     * @return
     */
    @RequestMapping(value = "insertRolePrivileges")
    public Map<String, Object> insertRolePrivileges(@RequestParam(value = "roleId") Integer roleId,@RequestParam(value = "privilegesId") String privilegesId) {
        return hjRolePrivilegesService.insertRolePrivileges(roleId,privilegesId);
    }


    /**
     * 根据角色查询权限
     * @param privilegesRoleParam
     * @return
     */
    @RequestMapping(value = "selectRolePrivileges")
    public Map<String, Object> selectRolePrivileges(@RequestBody PrivilegesRoleParam privilegesRoleParam) {
        return hjRolePrivilegesService.selectRolePrivileges(privilegesRoleParam);
    }












}
