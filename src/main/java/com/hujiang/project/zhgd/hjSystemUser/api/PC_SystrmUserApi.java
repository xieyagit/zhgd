package com.hujiang.project.zhgd.hjSystemUser.api;


import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjCompanyHierarchy.domain.HjCompanyHierarchy;
import com.hujiang.project.zhgd.hjCompanyHierarchy.service.IHjCompanyHierarchyService;
import com.hujiang.project.zhgd.hjCompanyLibrary.service.IHjCompanyLibraryService;
import com.hujiang.project.zhgd.hjCompanyUser.domain.HjCompanyUser;
import com.hujiang.project.zhgd.hjCompanyUser.service.IHjCompanyUserService;
import com.hujiang.project.zhgd.hjProjectUser.domain.HjProjectUser;
import com.hujiang.project.zhgd.hjProjectUser.service.IHjProjectUserService;
import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;

import com.hujiang.project.zhgd.hjSystemUser.domain.SystemUserParam;
import com.hujiang.project.zhgd.hjSystemUser.domain.UserParam;
import com.hujiang.project.zhgd.hjSystemUser.service.IHjSystemUserService;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @program: Provider01
 * @description: 用户接口
 * @author: Mr.LiuYong
 * @create: 2019-05-15 18:30
 **/
@RestController
@RequestMapping(value = "/provider/systemuser/pc", method = RequestMethod.POST)
public class PC_SystrmUserApi extends BaseController {


    @Autowired
    private IHjSystemUserService hjSystemUserService;
    @Autowired
    private IHjProjectUserService hjProjectUserService;
    @Autowired
    private IHjCompanyUserService companyUserService;
    @Autowired
    private IHjCompanyHierarchyService companyHierarchyService;

    @RequestMapping("test")
    public AjaxResult test(){
        System.out.println("8081");
        return AjaxResult.success("8081");
    }

    @RequestMapping(value = "login")
    public AjaxResult login(@RequestBody HjSystemUser user) {
        Integer tag =user.getEntry();
        user.setEntry(null);
        List<HjSystemUser> hjSystemUsers = hjSystemUserService.selectHjSystemUserList(user);
        if (hjSystemUsers.size() > 0 && tag != null) {//账户密码登录正确
            HjSystemUser result = hjSystemUsers.get(0);
            result.setUserPassword(null);
            if (result.getUserState().equals("1")) {//账户是启用的
                if (result.getEntry().equals(tag) || result.getEntry() == 2) {//判断账户是否能登录平台或app.
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id",result.getId());
                    jsonObject.put("userName",result.getUserName());
                    jsonObject.put("userPhone",result.getUserPhone());
                    jsonObject.put("userAccount",result.getUserAccount());
                    jsonObject.put("userType",result.getUserType());
                    //公司账户
//                    if(result.getUserType()==1){
//                        HjCompanyUser companyUser = new HjCompanyUser();
//                        companyUser.setUserId(result.getId());
//                        //根据账户id查询公司id
//                        List<HjCompanyUser> hjCompanyUsers = companyUserService.selectHjCompanyUserList(companyUser);
//                        if(hjCompanyUsers.size()>0){
//                            jsonObject.put("companyId",hjCompanyUsers.get(0).getCompanyId());
//                            //查询公司上级编号
//                            HjCompanyHierarchy hierarchy = new HjCompanyHierarchy();
//                            hierarchy.setCompanyId(hjCompanyUsers.get(0).getCompanyId());
//                            List<HjCompanyHierarchy> hjCompanyHierarchies = companyHierarchyService.selectHjCompanyHierarchyList(hierarchy);
//                            if(hjCompanyHierarchies.size()>0){
//                                jsonObject.put("pid",hjCompanyHierarchies.get(0).getId());
//                            }
//                        }
//                    }
                    System.out.println(result);
                    if(result.getUserType()==2){
                        HjProjectUser projectUser = new HjProjectUser();
                        projectUser.setUserId(result.getId());
                        HjProjectUser hjProjectUser = hjProjectUserService.selectHjProjectUserList(projectUser).get(0);
                        jsonObject.put("projectId",hjProjectUser.getProjectId());
                    }else if(result.getUserType()==1 || result.getUserType()==0){
                        jsonObject.put("projectId",null);
                        HjCompanyUser companyUser = new HjCompanyUser();
                        companyUser.setUserId(result.getId());
                        //根据账户id查询公司id
                        List<HjCompanyUser> hjCompanyUsers = companyUserService.selectHjCompanyUserList(companyUser);
                        if(hjCompanyUsers.size()>0){
                            jsonObject.put("companyId",hjCompanyUsers.get(0).getCompanyId());
                            //查询公司上级编号
                            HjCompanyHierarchy hierarchy = new HjCompanyHierarchy();
                            hierarchy.setCompanyId(hjCompanyUsers.get(0).getCompanyId());
                            List<HjCompanyHierarchy> hjCompanyHierarchies = companyHierarchyService.selectHjCompanyHierarchyList(hierarchy);
                            if(hjCompanyHierarchies.size()>0){
                                jsonObject.put("pid",hjCompanyHierarchies.get(0).getId());
                            }
                        }
                    }
                    return AjaxResult.success(jsonObject);
                }
                return AjaxResult.error(-1, "没有访问权限，请联系系统管理员");
            }
            return AjaxResult.error(-1, "账户已停用，请联系系统管理员");
        }
        return AjaxResult.error(-1, "账户或密码错误");
    }





    /**
     * 查看账号是否存在
     * @param user
     * @return
     */
    @RequestMapping(value = "selectSystemUser")
    public Map<String, Object> selectSystemUser(@RequestBody HjSystemUser user) {
        return hjSystemUserService.selectSystemUser(user);
    }


    /**
     * 创建账号
     * @param systemUserParam
     * @return
     */
    @RequestMapping(value = "insertSystemUser")
    public Map<String, Object> insertSystemUser(@RequestBody SystemUserParam systemUserParam) {
        return hjSystemUserService.insertSystemUser(systemUserParam);
    }

    /**
     * 修改账号
     * @param hjSystemUser
     * @return
     */
    @RequestMapping(value = "updateUser")
    public Map<String, Object> updateUser(@RequestBody HjSystemUser hjSystemUser
            ,@RequestParam("ids") String ids) {
        return hjSystemUserService.updateUser(hjSystemUser,ids);
    }

    /**
     * 删除账号
     * @param ids 角色id(1,2,3)
     * @return
     */
    @RequestMapping(value = "deleteSystemUser")
    public Map<String, Object> deleteSystemUser(@RequestParam("ids") String ids) {
        return hjSystemUserService.deleteSystemUser(ids);
    }


    /**
     * 列表账号
     * @param userParam
     * @return
     */
    @RequestMapping(value = "querySystemUser")
    public Map<String, Object> querySystemUser(@RequestBody UserParam userParam) {
        return hjSystemUserService.querySystemUser(userParam);
    }

    /**
     * 修改前显示
     * @param hjSystemUser
     * @return
     */
    @RequestMapping(value = "selectUserId")
    public Map<String, Object> selectUserId(@RequestBody HjSystemUser hjSystemUser) {
        return hjSystemUserService.selectUserId(hjSystemUser);
    }

    /**
     * 查询已有tsp通知权限
     * */
    @RequestMapping(value = "")
    public JSONObject addtsp(Integer projectId){
        JSONObject jsonObject = new JSONObject();
        return jsonObject;
    }








}
