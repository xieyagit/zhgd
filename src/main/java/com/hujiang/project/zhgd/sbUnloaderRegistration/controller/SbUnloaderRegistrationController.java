package com.hujiang.project.zhgd.sbUnloaderRegistration.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.project.zhgd.sbUnloaderRegistration.domain.SbUnloaderRegistration;
import com.hujiang.project.zhgd.sbUnloaderRegistration.service.ISbUnloaderRegistrationService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 卸料注册 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-09
 */
@RestController
@RequestMapping("/zhgd/sbUnloaderRegistration")
public class SbUnloaderRegistrationController extends BaseController
{
    private String prefix = "zhgd/sbUnloaderRegistration";
	
	@Autowired
	private ISbUnloaderRegistrationService sbUnloaderRegistrationService;
	
	@RequiresPermissions("zhgd:sbUnloaderRegistration:view")
	@GetMapping()
	public String sbUnloaderRegistration()
	{
	    return prefix + "/sbUnloaderRegistration";
	}
	
	/**
	 * 查询卸料注册列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderRegistration:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbUnloaderRegistration sbUnloaderRegistration)
	{
		startPage();
        List<SbUnloaderRegistration> list = sbUnloaderRegistrationService.selectSbUnloaderRegistrationList(sbUnloaderRegistration);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出卸料注册列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderRegistration:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbUnloaderRegistration sbUnloaderRegistration)
    {
    	List<SbUnloaderRegistration> list = sbUnloaderRegistrationService.selectSbUnloaderRegistrationList(sbUnloaderRegistration);
        ExcelUtil<SbUnloaderRegistration> util = new ExcelUtil<SbUnloaderRegistration>(SbUnloaderRegistration.class);
        return util.exportExcel(list, "sbUnloaderRegistration");
    }
	
	/**
	 * 新增卸料注册
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存卸料注册
	 */
	@RequiresPermissions("zhgd:sbUnloaderRegistration:add")
	@Log(title = "卸料注册", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbUnloaderRegistration sbUnloaderRegistration)
	{		
		return toAjax(sbUnloaderRegistrationService.insertSbUnloaderRegistration(sbUnloaderRegistration));
	}

	/**
	 * 修改卸料注册
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbUnloaderRegistration sbUnloaderRegistration = sbUnloaderRegistrationService.selectSbUnloaderRegistrationById(id);
		mmap.put("sbUnloaderRegistration", sbUnloaderRegistration);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存卸料注册
	 */
	@RequiresPermissions("zhgd:sbUnloaderRegistration:edit")
	@Log(title = "卸料注册", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbUnloaderRegistration sbUnloaderRegistration)
	{		
		return toAjax(sbUnloaderRegistrationService.updateSbUnloaderRegistration(sbUnloaderRegistration));
	}
	
	/**
	 * 删除卸料注册
	 */
	@RequiresPermissions("zhgd:sbUnloaderRegistration:remove")
	@Log(title = "卸料注册", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbUnloaderRegistrationService.deleteSbUnloaderRegistrationByIds(ids));
	}
	
}
