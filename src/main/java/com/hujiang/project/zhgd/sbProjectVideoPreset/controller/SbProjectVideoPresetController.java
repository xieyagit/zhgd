package com.hujiang.project.zhgd.sbProjectVideoPreset.controller;

import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.sbProjectVideoPreset.domain.SbProjectVideoPreset;
import com.hujiang.project.zhgd.sbProjectVideoPreset.service.ISbProjectVideoPresetService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 预置点 信息操作处理
 * 
 * @author hujiang
 * @date 2019-12-11
 */
@Controller
@RequestMapping("/zhgd/sbProjectVideoPreset")
public class SbProjectVideoPresetController extends BaseController
{
    private String prefix = "zhgd/sbProjectVideoPreset";
	
	@Autowired
	private ISbProjectVideoPresetService sbProjectVideoPresetService;
	
	@RequiresPermissions("zhgd:sbProjectVideoPreset:view")
	@GetMapping()
	public String sbProjectVideoPreset()
	{
	    return prefix + "/sbProjectVideoPreset";
	}
	
	/**
	 * 查询预置点列表
	 */
	@RequiresPermissions("zhgd:sbProjectVideoPreset:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbProjectVideoPreset sbProjectVideoPreset)
	{
		startPage();
        List<SbProjectVideoPreset> list = sbProjectVideoPresetService.selectSbProjectVideoPresetList(sbProjectVideoPreset);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出预置点列表
	 */
	@RequiresPermissions("zhgd:sbProjectVideoPreset:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbProjectVideoPreset sbProjectVideoPreset)
    {
    	List<SbProjectVideoPreset> list = sbProjectVideoPresetService.selectSbProjectVideoPresetList(sbProjectVideoPreset);
        ExcelUtil<SbProjectVideoPreset> util = new ExcelUtil<SbProjectVideoPreset>(SbProjectVideoPreset.class);
        return util.exportExcel(list, "sbProjectVideoPreset");
    }
	
	/**
	 * 新增预置点
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存预置点
	 */
	@RequiresPermissions("zhgd:sbProjectVideoPreset:add")
	@Log(title = "预置点", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbProjectVideoPreset sbProjectVideoPreset)
	{		
		return toAjax(sbProjectVideoPresetService.insertSbProjectVideoPreset(sbProjectVideoPreset));
	}

	/**
	 * 修改预置点
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbProjectVideoPreset sbProjectVideoPreset = sbProjectVideoPresetService.selectSbProjectVideoPresetById(id);
		mmap.put("sbProjectVideoPreset", sbProjectVideoPreset);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存预置点
	 */
	@RequiresPermissions("zhgd:sbProjectVideoPreset:edit")
	@Log(title = "预置点", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbProjectVideoPreset sbProjectVideoPreset)
	{		
		return toAjax(sbProjectVideoPresetService.updateSbProjectVideoPreset(sbProjectVideoPreset));
	}
	
	/**
	 * 删除预置点
	 */
	@RequiresPermissions("zhgd:sbProjectVideoPreset:remove")
	@Log(title = "预置点", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbProjectVideoPresetService.deleteSbProjectVideoPresetByIds(ids));
	}
	
}
