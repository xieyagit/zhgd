package com.hujiang.project.zhgd.sbCraneAddparams.controller;

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
import com.hujiang.project.zhgd.sbCraneAddparams.domain.SbCraneAddparams;
import com.hujiang.project.zhgd.sbCraneAddparams.service.ISbCraneAddparamsService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 塔式起重机参数 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-20
 */
@Controller
@RequestMapping("/zhgd/sbCraneAddparams")
public class SbCraneAddparamsController extends BaseController
{
    private String prefix = "zhgd/sbCraneAddparams";
	
	@Autowired
	private ISbCraneAddparamsService sbCraneAddparamsService;
	
	@RequiresPermissions("zhgd:sbCraneAddparams:view")
	@GetMapping()
	public String sbCraneAddparams()
	{
	    return prefix + "/sbCraneAddparams";
	}
	
	/**
	 * 查询塔式起重机参数列表
	 */
	@RequiresPermissions("zhgd:sbCraneAddparams:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbCraneAddparams sbCraneAddparams)
	{
		startPage();
        List<SbCraneAddparams> list = sbCraneAddparamsService.selectSbCraneAddparamsList(sbCraneAddparams);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出塔式起重机参数列表
	 */
	@RequiresPermissions("zhgd:sbCraneAddparams:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbCraneAddparams sbCraneAddparams)
    {
    	List<SbCraneAddparams> list = sbCraneAddparamsService.selectSbCraneAddparamsList(sbCraneAddparams);
        ExcelUtil<SbCraneAddparams> util = new ExcelUtil<SbCraneAddparams>(SbCraneAddparams.class);
        return util.exportExcel(list, "sbCraneAddparams");
    }
	
	/**
	 * 新增塔式起重机参数
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存塔式起重机参数
	 */
	@RequiresPermissions("zhgd:sbCraneAddparams:add")
	@Log(title = "塔式起重机参数", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbCraneAddparams sbCraneAddparams)
	{		
		return toAjax(sbCraneAddparamsService.insertSbCraneAddparams(sbCraneAddparams));
	}

	/**
	 * 修改塔式起重机参数
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SbCraneAddparams sbCraneAddparams = sbCraneAddparamsService.selectSbCraneAddparamsById(id);
		mmap.put("sbCraneAddparams", sbCraneAddparams);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存塔式起重机参数
	 */
	@RequiresPermissions("zhgd:sbCraneAddparams:edit")
	@Log(title = "塔式起重机参数", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbCraneAddparams sbCraneAddparams)
	{		
		return toAjax(sbCraneAddparamsService.updateSbCraneAddparams(sbCraneAddparams));
	}
	
	/**
	 * 删除塔式起重机参数
	 */
	@RequiresPermissions("zhgd:sbCraneAddparams:remove")
	@Log(title = "塔式起重机参数", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbCraneAddparamsService.deleteSbCraneAddparamsByIds(ids));
	}
	
}
