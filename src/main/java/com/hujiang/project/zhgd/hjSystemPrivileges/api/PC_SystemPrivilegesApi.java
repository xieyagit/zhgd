package com.hujiang.project.zhgd.hjSystemPrivileges.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjSystemPrivileges.domain.HjSystemPrivileges;
import com.hujiang.project.zhgd.hjSystemPrivileges.domain.SystemRoleParam;
import com.hujiang.project.zhgd.hjSystemPrivileges.service.IHjSystemPrivilegesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-05-21 15:32
 **/
@RestController
@RequestMapping(value = "/provider/systemPrivileges/pc", method = RequestMethod.POST)
public class PC_SystemPrivilegesApi extends BaseController {

    @Autowired
    private IHjSystemPrivilegesService hjSystemPrivilegesService;

    /**
     * pc菜单权限
     * @param uid
     * @param appOrPc
     * @return
     */
    @RequestMapping(value = "getSystemPrivileges")
    public AjaxResult getSystemPrivileges_pc(Integer uid, Integer appOrPc,Integer parentId) {
        JSONArray hjSystemPrivileges = hjSystemPrivilegesService.selectHjSystemPrivilegesByUser(uid, appOrPc,parentId);
        if (hjSystemPrivileges != null && hjSystemPrivileges.size() > 0) {
            return AjaxResult.success(hjSystemPrivileges);
        }
        return AjaxResult.error(-1, "无权限，请联系系统管理员");
    }

    /**
     * 查询权限列表
     * @param systemRoleParam
     * @return
     */
    @RequestMapping(value = "selectSystemPrivileges")
    public Map<String, Object> selectSystemPrivileges(@RequestBody SystemRoleParam systemRoleParam) {

        return hjSystemPrivilegesService.selectSystemPrivileges(systemRoleParam);
    }
    @PostMapping(value = "/getPrivilegesList")
    public JSONObject getPrivilegesList(@RequestParam("userId")Integer userId){
        JSONObject jsonObject = new JSONObject();
        SystemRoleParam one = new SystemRoleParam();
        one.setUserId(userId);
        List<SystemRoleParam> privilegesOneList = hjSystemPrivilegesService.getPrivilegesOne(one);
            if(privilegesOneList != null && privilegesOneList.size()>0){
                JSONArray oneArray = new JSONArray();
                for (SystemRoleParam ones : privilegesOneList){
                    JSONObject oneMap = new JSONObject();
                    oneMap.put("privilegesId",ones.getId());
                    oneMap.put("privilegesName",ones.getPrivilegesName());
                    oneMap.put("url",ones.getUrl());
                    SystemRoleParam two = new SystemRoleParam();
                    two.setUserId(userId);
                    two.setParentId(ones.getId());
                    List<SystemRoleParam> privilegesTwoList = hjSystemPrivilegesService.getPrivilegesTwo(two);
                        if(privilegesTwoList != null && privilegesTwoList.size()>0){
                            JSONArray twoArray = new JSONArray();
                            for (SystemRoleParam twos : privilegesTwoList){
                                JSONObject twoMap = new JSONObject();
                                twoMap.put("privilegesId",twos.getId());
                                twoMap.put("privilegesName",twos.getPrivilegesName());
                                twoMap.put("url",twos.getUrl());
                                twoArray.add(twoMap);
                            }
                            oneMap.put("subset",twoArray);
                        }

                    oneArray.add(oneMap);
                }
                jsonObject.put("msg","查询成功");
                jsonObject.put("code",0);
                jsonObject.put("data",oneArray);
            }
        return jsonObject;
    }

    /**
     * 查询全部
     * @param hjSystemPrivileges
     * @return
     */
    @RequestMapping(value = "querySystemPrivileges")
    public Map<String, Object> querySystemPrivileges(HjSystemPrivileges hjSystemPrivileges) {
        return hjSystemPrivilegesService.querySystemPrivileges(hjSystemPrivileges);
    }


    /**
     * 添加权限菜单
     * @param hjSystemPrivileges
     * @return
     */
    @RequestMapping(value = "insertSystemPrivileges")
    public Map<String, Object> insertSystemPrivileges(@RequestBody HjSystemPrivileges hjSystemPrivileges) {
        return hjSystemPrivilegesService.insertSystemPrivileges(hjSystemPrivileges);
    }




}
