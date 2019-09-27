package com.hujiang.project.zhgd.hjUserRole.controller;

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
import com.hujiang.project.zhgd.hjUserRole.domain.HjUserRole;
import com.hujiang.project.zhgd.hjUserRole.service.IHjUserRoleService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 用户-角色 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjUserRole")
public class HjUserRoleController extends BaseController
{
    private String prefix = "zhgd/hjUserRole";
	
	@Autowired
	private IHjUserRoleService hjUserRoleService;
	
	@RequiresPermissions("zhgd:hjUserRole:view")
	@GetMapping()
	public String hjUserRole()
	{
	    return prefix + "/hjUserRole";
	}
	
	/**
	 * 查询用户-角色列表
	 */
	@RequiresPermissions("zhgd:hjUserRole:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjUserRole hjUserRole)
	{
		startPage();
        List<HjUserRole> list = hjUserRoleService.selectHjUserRoleList(hjUserRole);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户-角色列表
	 */
	@RequiresPermissions("zhgd:hjUserRole:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjUserRole hjUserRole)
    {
    	List<HjUserRole> list = hjUserRoleService.selectHjUserRoleList(hjUserRole);
        ExcelUtil<HjUserRole> util = new ExcelUtil<HjUserRole>(HjUserRole.class);
        return util.exportExcel(list, "hjUserRole");
    }
	
	/**
	 * 新增用户-角色
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户-角色
	 */
	@RequiresPermissions("zhgd:hjUserRole:add")
	@Log(title = "用户-角色", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjUserRole hjUserRole)
	{		
		return toAjax(hjUserRoleService.insertHjUserRole(hjUserRole));
	}

	/**
	 * 修改用户-角色
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjUserRole hjUserRole = hjUserRoleService.selectHjUserRoleById(id);
		mmap.put("hjUserRole", hjUserRole);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户-角色
	 */
	@RequiresPermissions("zhgd:hjUserRole:edit")
	@Log(title = "用户-角色", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjUserRole hjUserRole)
	{		
		return toAjax(hjUserRoleService.updateHjUserRole(hjUserRole));
	}
	
	/**
	 * 删除用户-角色
	 */
	@RequiresPermissions("zhgd:hjUserRole:remove")
	@Log(title = "用户-角色", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjUserRoleService.deleteHjUserRoleByIds(ids));
	}
	
}
