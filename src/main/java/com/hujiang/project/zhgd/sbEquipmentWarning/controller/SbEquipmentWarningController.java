package com.hujiang.project.zhgd.sbEquipmentWarning.controller;

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
import com.hujiang.project.zhgd.sbEquipmentWarning.domain.SbEquipmentWarning;
import com.hujiang.project.zhgd.sbEquipmentWarning.service.ISbEquipmentWarningService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 定位报警 信息操作处理
 * 
 * @author hujiang
 * @date 2019-10-19
 */
@Controller
@RequestMapping("/zhgd/sbEquipmentWarning")
public class SbEquipmentWarningController extends BaseController
{
    private String prefix = "zhgd/sbEquipmentWarning";
	
	@Autowired
	private ISbEquipmentWarningService sbEquipmentWarningService;
	
	@RequiresPermissions("zhgd:sbEquipmentWarning:view")
	@GetMapping()
	public String sbEquipmentWarning()
	{
	    return prefix + "/sbEquipmentWarning";
	}
	
	/**
	 * 查询定位报警列表
	 */
	@RequiresPermissions("zhgd:sbEquipmentWarning:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbEquipmentWarning sbEquipmentWarning)
	{
		startPage();
        List<SbEquipmentWarning> list = sbEquipmentWarningService.selectSbEquipmentWarningList(sbEquipmentWarning);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出定位报警列表
	 */
	@RequiresPermissions("zhgd:sbEquipmentWarning:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbEquipmentWarning sbEquipmentWarning)
    {
    	List<SbEquipmentWarning> list = sbEquipmentWarningService.selectSbEquipmentWarningList(sbEquipmentWarning);
        ExcelUtil<SbEquipmentWarning> util = new ExcelUtil<SbEquipmentWarning>(SbEquipmentWarning.class);
        return util.exportExcel(list, "sbEquipmentWarning");
    }
	
	/**
	 * 新增定位报警
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存定位报警
	 */
	@RequiresPermissions("zhgd:sbEquipmentWarning:add")
	@Log(title = "定位报警", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(List<SbEquipmentWarning> sbEquipmentWarning)
	{		
		return toAjax(sbEquipmentWarningService.insertSbEquipmentWarning(sbEquipmentWarning));
	}

	/**
	 * 修改定位报警
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbEquipmentWarning sbEquipmentWarning = sbEquipmentWarningService.selectSbEquipmentWarningById(id);
		mmap.put("sbEquipmentWarning", sbEquipmentWarning);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存定位报警
	 */
	@RequiresPermissions("zhgd:sbEquipmentWarning:edit")
	@Log(title = "定位报警", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbEquipmentWarning sbEquipmentWarning)
	{		
		return toAjax(sbEquipmentWarningService.updateSbEquipmentWarning(sbEquipmentWarning));
	}
	
	/**
	 * 删除定位报警
	 */
	@RequiresPermissions("zhgd:sbEquipmentWarning:remove")
	@Log(title = "定位报警", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbEquipmentWarningService.deleteSbEquipmentWarningByIds(ids));
	}
	
}
