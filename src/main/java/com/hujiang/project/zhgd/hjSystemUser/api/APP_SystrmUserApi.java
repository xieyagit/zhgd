package com.hujiang.project.zhgd.hjSystemUser.api;


import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjCompanyHierarchy.domain.HjCompanyHierarchy;
import com.hujiang.project.zhgd.hjCompanyHierarchy.service.IHjCompanyHierarchyService;
import com.hujiang.project.zhgd.hjCompanyUser.domain.HjCompanyUser;
import com.hujiang.project.zhgd.hjCompanyUser.service.IHjCompanyUserService;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectUser.domain.HjProjectUser;
import com.hujiang.project.zhgd.hjProjectUser.service.IHjProjectUserService;
import com.hujiang.project.zhgd.hjSystemUser.domain.*;
import com.hujiang.project.zhgd.hjSystemUser.service.HjSystemUserServiceImpl;
import com.hujiang.project.zhgd.hjSystemUser.service.IHjSystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @program: Provider01
 * @description: 用户接口
 * @author: Mr.LiuYong
 * @create: 2019-05-15 18:30
 **/
@RestController
@RequestMapping(value = "/provider/systemuser/app", method = RequestMethod.POST)
public class APP_SystrmUserApi extends BaseController {


    @Autowired
    private IHjSystemUserService hjSystemUserService;
    @Autowired
    private IHjProjectUserService hjProjectUserService;
    @Autowired
    private IHjProjectService projectService;
    @Autowired
    private IHjCompanyUserService hjCompanyUserService;
    @Autowired
    private IHjCompanyHierarchyService hjCompanyHierarchyService;




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
                    jsonObject.put("userAlias",result.getAlias());
                    if(result.getUserType()==2){
                        HjProjectUser projectUser = new HjProjectUser();
                        projectUser.setUserId(result.getId());
                        HjProjectUser hjProjectUser = hjProjectUserService.selectHjProjectUserList(projectUser).get(0);
                        jsonObject.put("projectId",hjProjectUser.getProjectId());
                        jsonObject.put("projectName",projectService.selectHjProjectById(hjProjectUser.getProjectId()).getProjectName());
                    }else if(result.getUserType()==1 || result.getUserType()==0){

                        // 找到公司
                        HjCompanyUser hjCompanyUser = new HjCompanyUser();
                        hjCompanyUser.setUserId(result.getId());
                        List<HjCompanyUser> list = hjCompanyUserService.selectHjCompanyUserList(hjCompanyUser);
                        // 找到公司下是否有公司
                        HjCompanyHierarchy hjCompanyHierarchy = new HjCompanyHierarchy(); // 公司层级表
                        hjCompanyHierarchy.setCompanyId(list.get(0).getCompanyId());    // 公司id 查询公司层级表关系
                        List <HjCompanyHierarchy> hjCompanyHierarchyList =  hjCompanyHierarchyService.selectHjCompanyHierarchyList(hjCompanyHierarchy);
                        if(hjCompanyHierarchyList.size() > 0) {
                            List<ProjectParam> projectParamList = hjSystemUserService.selectProjectName(hjCompanyHierarchyList);
                            if(projectParamList.size() > 0){
                                jsonObject.put("projectId",projectParamList.get(0).getProjectId());
                                jsonObject.put("projectName",projectService.selectHjProjectById(projectParamList.get(0).getProjectId()).getProjectName());
                                return AjaxResult.success(jsonObject);
                            }
                        }
                        jsonObject.put("projectId",null);
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
     * 修改密码
     * @param passwordParam 密码
     * @return id 用户id
     */
    @RequestMapping(value = "updateUserPassword")
    public Map<String, Object> updateUserPassword(@RequestBody PasswordParam passwordParam) {
        return hjSystemUserService.updateUserPassword(passwordParam);
    }


    /**
     * 切换项目
     * @param userId 人员id
     * @return
     */
    @RequestMapping("/queryProject")
    public Map<String, Object> queryProject(@RequestParam(value = "userId") Integer userId)
    {
        return hjSystemUserService.queryProject(userId);
    }

    /**
     * 根据ID获取用户基本信息
     * @param userId
     * @return
     */
    @RequestMapping("/getUserById")
    public JSONObject getUserById(@RequestParam(value = "userId")Integer userId){
        JSONObject result = new JSONObject();
        UpdateUser user = hjSystemUserService.getUserById(userId);
       /* if(user!=null) {
            result.put("msg","查询成功");
            result.put("code",0);
            result.put("data", user);
        }else{
            result.put("date","");
        }*/
        result.put("msg",user !=null ? "查询成功":"查询失败");
        result.put("code",user !=null ? 0:-1);

        return result;
    }

    /**
     * 修改用户基本信息
     * @param userName
     * @param userPhone
     * @return
     */
    @RequestMapping("/updateUserById")
    public JSONObject updateUserById(@RequestParam(value = "userName",required = false)String userName,
                                  @RequestParam(value = "userPhone",required = false)String userPhone,
                                     @RequestParam(value = "userId",required = false)Integer userId){
        JSONObject result = new JSONObject();
        int user = hjSystemUserService.updateUserById(userName,userPhone,userId);
//        if(user>0) {
//            result.put("msg","保存成功");
//            result.put("code",0);
//        }else {
//            result.put("msg","保存失败");
//            result.put("data","");
//        }
        result.put("msg",user >0 ? "保存成功":"保存失败");
        result.put("code",user >0 ? 0:-1);
        return result;
    }

















}
