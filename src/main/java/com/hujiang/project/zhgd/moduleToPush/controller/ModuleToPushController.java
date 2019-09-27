package com.hujiang.project.zhgd.moduleToPush.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
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
import com.hujiang.project.zhgd.moduleToPush.domain.ModuleToPush;
import com.hujiang.project.zhgd.moduleToPush.service.IModuleToPushService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 用户推送开关 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-05
 */
@Controller
@RequestMapping("/zhgd/moduleToPush")
public class ModuleToPushController extends BaseController
{
    private String prefix = "zhgd/moduleToPush";
	
	@Autowired
	private IModuleToPushService moduleToPushService;
	
	@RequiresPermissions("zhgd:moduleToPush:view")
	@GetMapping()
	public String moduleToPush()
	{
	    return prefix + "/moduleToPush";
	}
	
	/**
	 * 查询用户推送开关列表
	 */
	@RequiresPermissions("zhgd:moduleToPush:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ModuleToPush moduleToPush)
	{
		startPage();
        List<ModuleToPush> list = moduleToPushService.selectModuleToPushList(moduleToPush);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户推送开关列表
	 */
	@RequiresPermissions("zhgd:moduleToPush:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ModuleToPush moduleToPush)
    {
    	List<ModuleToPush> list = moduleToPushService.selectModuleToPushList(moduleToPush);
        ExcelUtil<ModuleToPush> util = new ExcelUtil<ModuleToPush>(ModuleToPush.class);
        return util.exportExcel(list, "moduleToPush");
    }
	
	/**
	 * 新增用户推送开关
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户推送开关
	 */
	@RequiresPermissions("zhgd:moduleToPush:add")
	@Log(title = "用户推送开关", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ModuleToPush moduleToPush)
	{		
		return toAjax(moduleToPushService.insertModuleToPush(moduleToPush));
	}

	/**
	 * 修改用户推送开关
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ModuleToPush moduleToPush = moduleToPushService.selectModuleToPushById(id);
		mmap.put("moduleToPush", moduleToPush);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户推送开关
	 */
	@RequiresPermissions("zhgd:moduleToPush:edit")
	@Log(title = "用户推送开关", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ModuleToPush moduleToPush)
	{		
		return toAjax(moduleToPushService.updateModuleToPush(moduleToPush));
	}
	
	/**
	 * 删除用户推送开关
	 */
	@RequiresPermissions("zhgd:moduleToPush:remove")
	@Log(title = "用户推送开关", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(moduleToPushService.deleteModuleToPushByIds(ids));
	}
	
}
