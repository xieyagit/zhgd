package com.hujiang.project.zhgd.sbCurrentTemperature.controller;

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
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbCurrentTemperature.service.ISbCurrentTemperatureService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 温度及漏电流记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-24
 */
@Controller
@RequestMapping("/zhgd/sbCurrentTemperature")
public class SbCurrentTemperatureController extends BaseController
{
    private String prefix = "zhgd/sbCurrentTemperature";
	
	@Autowired
	private ISbCurrentTemperatureService sbCurrentTemperatureService;
	
	@RequiresPermissions("zhgd:sbCurrentTemperature:view")
	@GetMapping()
	public String sbCurrentTemperature()
	{
	    return prefix + "/sbCurrentTemperature";
	}
	
	/**
	 * 查询温度及漏电流记录列表
	 */
	@RequiresPermissions("zhgd:sbCurrentTemperature:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbCurrentTemperature sbCurrentTemperature)
	{
		startPage();
        List<SbCurrentTemperature> list = sbCurrentTemperatureService.selectSbCurrentTemperatureList(sbCurrentTemperature);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出温度及漏电流记录列表
	 */
	@RequiresPermissions("zhgd:sbCurrentTemperature:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbCurrentTemperature sbCurrentTemperature)
    {
    	List<SbCurrentTemperature> list = sbCurrentTemperatureService.selectSbCurrentTemperatureList(sbCurrentTemperature);
        ExcelUtil<SbCurrentTemperature> util = new ExcelUtil<SbCurrentTemperature>(SbCurrentTemperature.class);
        return util.exportExcel(list, "sbCurrentTemperature");
    }
	
	/**
	 * 新增温度及漏电流记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存温度及漏电流记录
	 */
	@RequiresPermissions("zhgd:sbCurrentTemperature:add")
	@Log(title = "温度及漏电流记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbCurrentTemperature sbCurrentTemperature)
	{		
		return toAjax(sbCurrentTemperatureService.insertSbCurrentTemperature(sbCurrentTemperature));
	}

	/**
	 * 修改温度及漏电流记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbCurrentTemperature sbCurrentTemperature = sbCurrentTemperatureService.selectSbCurrentTemperatureById(id);
		mmap.put("sbCurrentTemperature", sbCurrentTemperature);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存温度及漏电流记录
	 */
	@RequiresPermissions("zhgd:sbCurrentTemperature:edit")
	@Log(title = "温度及漏电流记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbCurrentTemperature sbCurrentTemperature)
	{		
		return toAjax(sbCurrentTemperatureService.updateSbCurrentTemperature(sbCurrentTemperature));
	}
	
	/**
	 * 删除温度及漏电流记录
	 */
	@RequiresPermissions("zhgd:sbCurrentTemperature:remove")
	@Log(title = "温度及漏电流记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbCurrentTemperatureService.deleteSbCurrentTemperatureByIds(ids));
	}
	
}
