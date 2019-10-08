package com.hujiang.project.zhgd.sbManufacturer.controller;

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
import com.hujiang.project.zhgd.sbManufacturer.domain.SbManufacturer;
import com.hujiang.project.zhgd.sbManufacturer.service.ISbManufacturerService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 设备厂商名称 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-24
 */
@Controller
@RequestMapping("/zhgd/sbManufacturer")
public class SbManufacturerController extends BaseController
{
    private String prefix = "zhgd/sbManufacturer";
	
	@Autowired
	private ISbManufacturerService sbManufacturerService;
	
	@RequiresPermissions("zhgd:sbManufacturer:view")
	@GetMapping()
	public String sbManufacturer()
	{
	    return prefix + "/sbManufacturer";
	}
	
	/**
	 * 查询设备厂商名称列表
	 */
	@RequiresPermissions("zhgd:sbManufacturer:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbManufacturer sbManufacturer)
	{
		startPage();
        List<SbManufacturer> list = sbManufacturerService.selectSbManufacturerList(sbManufacturer);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出设备厂商名称列表
	 */
	@RequiresPermissions("zhgd:sbManufacturer:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbManufacturer sbManufacturer)
    {
    	List<SbManufacturer> list = sbManufacturerService.selectSbManufacturerList(sbManufacturer);
        ExcelUtil<SbManufacturer> util = new ExcelUtil<SbManufacturer>(SbManufacturer.class);
        return util.exportExcel(list, "sbManufacturer");
    }
	
	/**
	 * 新增设备厂商名称
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备厂商名称
	 */
	@RequiresPermissions("zhgd:sbManufacturer:add")
	@Log(title = "设备厂商名称", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbManufacturer sbManufacturer)
	{		
		return toAjax(sbManufacturerService.insertSbManufacturer(sbManufacturer));
	}

	/**
	 * 修改设备厂商名称
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbManufacturer sbManufacturer = sbManufacturerService.selectSbManufacturerById(id);
		mmap.put("sbManufacturer", sbManufacturer);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备厂商名称
	 */
	@RequiresPermissions("zhgd:sbManufacturer:edit")
	@Log(title = "设备厂商名称", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbManufacturer sbManufacturer)
	{		
		return toAjax(sbManufacturerService.updateSbManufacturer(sbManufacturer));
	}
	
	/**
	 * 删除设备厂商名称
	 */
	@RequiresPermissions("zhgd:sbManufacturer:remove")
	@Log(title = "设备厂商名称", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbManufacturerService.deleteSbManufacturerByIds(ids));
	}
	
}
