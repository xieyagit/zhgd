package com.hujiang.project.zhgd.hjSystemPrivileges.controller;

import com.hujiang.project.system.role.domain.Role;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.project.zhgd.hjSystemPrivileges.domain.HjSystemPrivileges;
import com.hujiang.project.zhgd.hjSystemPrivileges.service.IHjSystemPrivilegesService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 系统-权限 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjSystemPrivileges")
public class HjSystemPrivilegesController extends BaseController
{
    private String prefix = "zhgd/hjSystemPrivileges";
	
	@Autowired
	private IHjSystemPrivilegesService hjSystemPrivilegesService;
	
	@RequiresPermissions("zhgd:hjSystemPrivileges:view")
	@GetMapping()
	public String hjSystemPrivileges()
	{
	    return prefix + "/hjSystemPrivileges";
	}
	
	/**
	 * 查询系统-权限列表
	 */
	@RequiresPermissions("zhgd:hjSystemPrivileges:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjSystemPrivileges hjSystemPrivileges)
	{
		startPage();
        List<HjSystemPrivileges> list = hjSystemPrivilegesService.selectHjSystemPrivilegesList(hjSystemPrivileges);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出系统-权限列表
	 */
	@RequiresPermissions("zhgd:hjSystemPrivileges:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjSystemPrivileges hjSystemPrivileges)
    {
    	List<HjSystemPrivileges> list = hjSystemPrivilegesService.selectHjSystemPrivilegesList(hjSystemPrivileges);
        ExcelUtil<HjSystemPrivileges> util = new ExcelUtil<HjSystemPrivileges>(HjSystemPrivileges.class);
        return util.exportExcel(list, "hjSystemPrivileges");
    }
	
	/**
	 * 新增系统-权限
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		mmap.put("hjSystemPrivileges",hjSystemPrivilegesService.selectHjSystemPrivilegesList(null));
		return prefix + "/add";
	}
	
	/**
	 * 新增保存系统-权限
	 */
	@RequiresPermissions("zhgd:hjSystemPrivileges:add")
	@Log(title = "系统-权限", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjSystemPrivileges hjSystemPrivileges)
	{
		hjSystemPrivileges.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return toAjax(hjSystemPrivilegesService.insertHjSystemPrivileges(hjSystemPrivileges));
	}

	/**
	 * 修改系统-权限
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjSystemPrivileges hjSystemPrivileges = hjSystemPrivilegesService.selectHjSystemPrivilegesById(id);
		mmap.put("hjSystemPrivileges", hjSystemPrivileges);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存系统-权限
	 */
	@RequiresPermissions("zhgd:hjSystemPrivileges:edit")
	@Log(title = "系统-权限", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjSystemPrivileges hjSystemPrivileges)
	{		
		return toAjax(hjSystemPrivilegesService.updateHjSystemPrivileges(hjSystemPrivileges));
	}
	
	/**
	 * 删除系统-权限
	 */
	@RequiresPermissions("zhgd:hjSystemPrivileges:remove")
	@Log(title = "系统-权限", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjSystemPrivilegesService.deleteHjSystemPrivilegesByIds(ids));
	}


}
