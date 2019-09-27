package com.hujiang.project.zhgd.hjRolePrivileges.controller;

import org.springframework.stereotype.Controller;
import java.util.List;
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
import com.hujiang.project.zhgd.hjRolePrivileges.domain.HjRolePrivileges;
import com.hujiang.project.zhgd.hjRolePrivileges.service.IHjRolePrivilegesService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 角色-权限 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjRolePrivileges")
public class HjRolePrivilegesController extends BaseController
{
    private String prefix = "zhgd/hjRolePrivileges";
	
	@Autowired
	private IHjRolePrivilegesService hjRolePrivilegesService;
	
	@RequiresPermissions("zhgd:hjRolePrivileges:view")
	@GetMapping()
	public String hjRolePrivileges()
	{
	    return prefix + "/hjRolePrivileges";
	}
	
	/**
	 * 查询角色-权限列表
	 */
	@RequiresPermissions("zhgd:hjRolePrivileges:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjRolePrivileges hjRolePrivileges)
	{
		startPage();
        List<HjRolePrivileges> list = hjRolePrivilegesService.selectHjRolePrivilegesList(hjRolePrivileges);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出角色-权限列表
	 */
	@RequiresPermissions("zhgd:hjRolePrivileges:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjRolePrivileges hjRolePrivileges)
    {
    	List<HjRolePrivileges> list = hjRolePrivilegesService.selectHjRolePrivilegesList(hjRolePrivileges);
        ExcelUtil<HjRolePrivileges> util = new ExcelUtil<HjRolePrivileges>(HjRolePrivileges.class);
        return util.exportExcel(list, "hjRolePrivileges");
    }
	
	/**
	 * 新增角色-权限
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存角色-权限
	 */
	@RequiresPermissions("zhgd:hjRolePrivileges:add")
	@Log(title = "角色-权限", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjRolePrivileges hjRolePrivileges)
	{		
		return toAjax(hjRolePrivilegesService.insertHjRolePrivileges(hjRolePrivileges));
	}

	/**
	 * 修改角色-权限
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjRolePrivileges hjRolePrivileges = hjRolePrivilegesService.selectHjRolePrivilegesById(id);
		mmap.put("hjRolePrivileges", hjRolePrivileges);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存角色-权限
	 */
	@RequiresPermissions("zhgd:hjRolePrivileges:edit")
	@Log(title = "角色-权限", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjRolePrivileges hjRolePrivileges)
	{		
		return toAjax(hjRolePrivilegesService.updateHjRolePrivileges(hjRolePrivileges));
	}
	
	/**
	 * 删除角色-权限
	 */
	@RequiresPermissions("zhgd:hjRolePrivileges:remove")
	@Log(title = "角色-权限", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjRolePrivilegesService.deleteHjRolePrivilegesByIds(ids));
	}
	
}
