package com.hujiang.project.zhgd.moredian.moredianGroup.controller;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.moredian.MoredianMethod;
import com.hujiang.project.zhgd.moredian.moredianGroup.domain.MoredianGroup;
import com.hujiang.project.zhgd.moredian.moredianGroup.service.IMoredianGroupService;
import com.hujiang.project.zhgd.moredian.moredianOrg.domain.MoredianOrg;
import com.hujiang.project.zhgd.moredian.moredianOrg.service.IMoredianOrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目群组 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Controller
@RequestMapping("/moredian/moredianGroup")
public class MoredianGroupController extends BaseController
{
    private String prefix = "moredian/moredianGroup";
	
	@Autowired
	private IMoredianGroupService moredianGroupService;
	@Autowired
	private IMoredianOrgService iMoredianOrgService;
	
	@RequiresPermissions("moredian:moredianGroup:view")
	@GetMapping()
	public String moredianGroup()
	{
	    return prefix + "/moredianGroup";
	}
	
	/**
	 * 查询项目群组列表
	 */
	@RequiresPermissions("moredian:moredianGroup:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MoredianGroup moredianGroup)
	{
		startPage();
        List<MoredianGroup> list = moredianGroupService.selectMoredianGroupList(moredianGroup);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出项目群组列表
	 */
	@RequiresPermissions("moredian:moredianGroup:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MoredianGroup moredianGroup)
    {
    	List<MoredianGroup> list = moredianGroupService.selectMoredianGroupList(moredianGroup);
        ExcelUtil<MoredianGroup> util = new ExcelUtil<MoredianGroup>(MoredianGroup.class);
        return util.exportExcel(list, "moredianGroup");
    }
	
	/**
	 * 新增项目群组
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		mmap.put("org",iMoredianOrgService.selectMoredianOrgList(null));
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目群组
	 */
	@RequiresPermissions("moredian:moredianGroup:add")
	@Log(title = "项目群组", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MoredianGroup moredianGroup)
	{
		System.out.println(moredianGroup);
		MoredianOrg m = new MoredianOrg();
		m.setOrgId(moredianGroup.getOrgId());
		//获取机构
		MoredianOrg moredianOrg = iMoredianOrgService.selectMoredianOrgList(m).get(0);
		System.out.println(moredianOrg);
		//获取机构accessToken
		String orgAccessToken = MoredianMethod.getOrgAccessToken(moredianOrg.getOrgAuthKey(), moredianOrg.getOrgId());
		System.out.println("获取机构accessToken:"+orgAccessToken);
		//创建群组
		//json
		JSONObject o = new JSONObject();
		o.put("tpGroupId",moredianGroup.getProjectId());
		o.put("groupName",moredianGroup.getProjectName());
		JSONObject group = MoredianMethod.createGroup(orgAccessToken, o);
		System.out.println("创建群组："+group);
		moredianGroup.setGroupId(group.getString("data"));
		return toAjax(moredianGroupService.insertMoredianGroup(moredianGroup));
	}

	/**
	 * 修改项目群组
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MoredianGroup moredianGroup = moredianGroupService.selectMoredianGroupById(id);
		mmap.put("org",iMoredianOrgService.selectMoredianOrgList(null));
		mmap.put("moredianGroup", moredianGroup);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目群组
	 */
	@RequiresPermissions("moredian:moredianGroup:edit")
	@Log(title = "项目群组", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MoredianGroup moredianGroup)
	{
		System.out.println(moredianGroup);
		MoredianOrg m = new MoredianOrg();
		m.setOrgId(moredianGroup.getOrgId());
		//获取机构
		MoredianOrg moredianOrg = iMoredianOrgService.selectMoredianOrgList(m).get(0);
		System.out.println(moredianOrg);
		//获取机构accessToken
		String orgAccessToken = MoredianMethod.getOrgAccessToken(moredianOrg.getOrgAuthKey(), moredianOrg.getOrgId());
		System.out.println("获取机构accessToken:"+orgAccessToken);
		//创建群组
		//json
		JSONObject o = new JSONObject();
		o.put("tpGroupId",moredianGroup.getGroupId());
		o.put("groupName",moredianGroup.getProjectName());
		JSONObject group = MoredianMethod.updateGroup(orgAccessToken, o);
		System.out.println("修改群组："+group);
		return toAjax(moredianGroupService.updateMoredianGroup(moredianGroup));
	}
	
	/**
	 * 删除项目群组
	 */
	@RequiresPermissions("moredian:moredianGroup:remove")
	@Log(title = "项目群组", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(moredianGroupService.deleteMoredianGroupByIds(ids));
	}
	
}
