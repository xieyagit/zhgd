package com.hujiang.project.zhgd.hjUserRole.api;


import com.hujiang.project.zhgd.hjUserRole.domain.UserRoleParam;
import com.hujiang.project.zhgd.hjUserRole.service.IHjUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户-角色
 *
 * @author hujiang
 * @date 2019-05-20
 */
@RestController
@RequestMapping(value = "/provider/UserRoleApi",method = RequestMethod.POST)
public class Pc_UserRoleApi {




    @Autowired
    private IHjUserRoleService hjUserRoleService;


    /**
     * 用户添加角色
     * @param userId 用户id
     * @param ids 角色id 字符串
     */
    @RequestMapping(value = "insertUserRole")
    public Map<String, Object> insertUserRole(@RequestParam(value = "userId") Integer userId,@RequestParam(value = "ids") String ids) {
        return hjUserRoleService.insertUserRole(userId,ids);
    }


    /**
     * 用户拥有角色
     * @param userRoleParam
     */
    @RequestMapping(value = "queryUserRole")
    public Map<String, Object> queryUserRole(@RequestBody UserRoleParam userRoleParam) {
        return hjUserRoleService.queryUserRole(userRoleParam);
    }



































}
