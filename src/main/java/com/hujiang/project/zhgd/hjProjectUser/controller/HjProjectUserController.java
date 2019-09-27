package com.hujiang.project.zhgd.hjProjectUser.controller;

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
import com.hujiang.project.zhgd.hjProjectUser.domain.HjProjectUser;
import com.hujiang.project.zhgd.hjProjectUser.service.IHjProjectUserService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 项目用户 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjProjectUser")
public class HjProjectUserController extends BaseController
{
    private String prefix = "zhgd/hjProjectUser";
	
	@Autowired
	private IHjProjectUserService hjProjectUserService;
	
	@RequiresPermissions("zhgd:hjProjectUser:view")
	@GetMapping()
	public String hjProjectUser()
	{
	    return prefix + "/hjProjectUser";
	}
	
	/**
	 * 查询项目用户列表
	 */
	@RequiresPermissions("zhgd:hjProjectUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjProjectUser hjProjectUser)
	{
		startPage();
        List<HjProjectUser> list = hjProjectUserService.selectHjProjectUserList(hjProjectUser);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出项目用户列表
	 */
	@RequiresPermissions("zhgd:hjProjectUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjProjectUser hjProjectUser)
    {
    	List<HjProjectUser> list = hjProjectUserService.selectHjProjectUserList(hjProjectUser);
        ExcelUtil<HjProjectUser> util = new ExcelUtil<HjProjectUser>(HjProjectUser.class);
        return util.exportExcel(list, "hjProjectUser");
    }
	
	/**
	 * 新增项目用户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目用户
	 */
	@RequiresPermissions("zhgd:hjProjectUser:add")
	@Log(title = "项目用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjProjectUser hjProjectUser)
	{		
		return toAjax(hjProjectUserService.insertHjProjectUser(hjProjectUser));
	}

	/**
	 * 修改项目用户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjProjectUser hjProjectUser = hjProjectUserService.selectHjProjectUserById(id);
		mmap.put("hjProjectUser", hjProjectUser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目用户
	 */
	@RequiresPermissions("zhgd:hjProjectUser:edit")
	@Log(title = "项目用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjProjectUser hjProjectUser)
	{		
		return toAjax(hjProjectUserService.updateHjProjectUser(hjProjectUser));
	}
	
	/**
	 * 删除项目用户
	 */
	@RequiresPermissions("zhgd:hjProjectUser:remove")
	@Log(title = "项目用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjProjectUserService.deleteHjProjectUserByIds(ids));
	}
	
}
