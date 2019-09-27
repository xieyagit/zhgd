package com.hujiang.project.zhgd.hjSystemPrivileges.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjSystemPrivileges.domain.HjSystemPrivileges;
import com.hujiang.project.zhgd.hjSystemPrivileges.service.IHjSystemPrivilegesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-05-21 15:32
 **/
@RestController
@RequestMapping(value = "provider/systemPrivileges/app", method = RequestMethod.POST)
public class APP_SystemPrivilegesApi extends BaseController {

    @Autowired
    private IHjSystemPrivilegesService hjSystemPrivilegesService;

    /**
     * app账户菜单权限
     * @param uid
     * @param appOrPc
     * @return
     */
    @RequestMapping(value = "getSystemPrivileges")
    public AjaxResult getSystemPrivileges_app(Integer uid, Integer appOrPc,Integer parentId) {
        JSONArray hjSystemPrivileges = hjSystemPrivilegesService.selectHjSystemPrivilegesByUser(uid, appOrPc,parentId);
        System.out.println("**************");
        System.out.println(hjSystemPrivileges);
        if (hjSystemPrivileges != null && hjSystemPrivileges.size() > 0) {
            return AjaxResult.success(hjSystemPrivileges);
        }
        return AjaxResult.error(-1, "无权限，请联系系统管理员");
    }

}
