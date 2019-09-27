package com.hujiang.project.zhgd.hjSystemRole.api;

import com.hujiang.project.zhgd.hjSystemRole.domain.HjSystemRole;
import com.hujiang.project.zhgd.hjSystemRole.domain.RoleParam;
import com.hujiang.project.zhgd.hjSystemRole.service.IHjSystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-05-20 19:15
 **/
@RestController
@RequestMapping(value = "/provider/systemRoleApi",method = RequestMethod.POST)
public class Pc_SystemRoleApi {


    @Autowired
    private IHjSystemRoleService hjSystemRoleService;

    /**
     * 修改前显示
     * @param hjSystemRole
     * @return
     */
    @RequestMapping(value = "selectSystemRoleId")
    public Map<String, Object> selectSystemRoleId(@RequestBody HjSystemRole hjSystemRole) {
        return hjSystemRoleService.selectSystemRoleId(hjSystemRole);
    }


    /**
     * 创建角色
     * @param hjSystemRole
     * @return
     */
    @RequestMapping(value = "addSystemRole")
    public Map<String, Object> addSystemRole(@RequestBody HjSystemRole hjSystemRole,
                                                @RequestParam(value = "ids") String ids) {
        return hjSystemRoleService.addSystemRole(hjSystemRole,ids);
    }

    /**
     * 修改角色
     * @param hjSystemRole
     * @return
     */
    @RequestMapping(value = "updateRole")
    public Map<String, Object> updateRole(@RequestBody HjSystemRole hjSystemRole
            ,@RequestParam(value = "ids") String ids) {
        return hjSystemRoleService.updateRole(hjSystemRole,ids);
    }

    /**
     * 删除角色
     * @param ids 角色id(1,2,3)
     * @return
     */
    @RequestMapping(value = "deleteSystemRole")
    public Map<String, Object> deleteSystemRole(@RequestParam("ids") String ids) {
        return hjSystemRoleService.deleteSystemRole(ids);
    }


    /**
     * 角色列表
     * @param systemRoleParam
     * @return
     */
    @RequestMapping(value = "selectSystemRole")
    public Map<String, Object> selectSystemRole(@RequestBody RoleParam systemRoleParam) {
        return hjSystemRoleService.selectSystemRole(systemRoleParam);
    }

    /**
     * 项目 或者 集团 公司 有那些角色
     * @param hjSystemRole
     * @return
     */
    @RequestMapping(value = "querySystemRole")
    public Map<String, Object> querySystemRole(@RequestBody HjSystemRole hjSystemRole) {
        return hjSystemRoleService.querySystemRole(hjSystemRole);
    }


}
