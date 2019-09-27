package com.hujiang.project.zhgd.sbElevatorOperator.controller;

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
import com.hujiang.project.zhgd.sbElevatorOperator.domain.SbElevatorOperator;
import com.hujiang.project.zhgd.sbElevatorOperator.service.ISbElevatorOperatorService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 升降机操作记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-27
 */
@Controller
@RequestMapping("/zhgd/sbElevatorOperator")
public class SbElevatorOperatorController extends BaseController
{
    private String prefix = "zhgd/sbElevatorOperator";
	
	@Autowired
	private ISbElevatorOperatorService sbElevatorOperatorService;
	
	@RequiresPermissions("zhgd:sbElevatorOperator:view")
	@GetMapping()
	public String sbElevatorOperator()
	{
	    return prefix + "/sbElevatorOperator";
	}
	
	/**
	 * 查询升降机操作记录列表
	 */
	@RequiresPermissions("zhgd:sbElevatorOperator:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbElevatorOperator sbElevatorOperator)
	{
		startPage();
        List<SbElevatorOperator> list = sbElevatorOperatorService.selectSbElevatorOperatorList(sbElevatorOperator);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出升降机操作记录列表
	 */
	@RequiresPermissions("zhgd:sbElevatorOperator:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbElevatorOperator sbElevatorOperator)
    {
    	List<SbElevatorOperator> list = sbElevatorOperatorService.selectSbElevatorOperatorList(sbElevatorOperator);
        ExcelUtil<SbElevatorOperator> util = new ExcelUtil<SbElevatorOperator>(SbElevatorOperator.class);
        return util.exportExcel(list, "sbElevatorOperator");
    }
	
	/**
	 * 新增升降机操作记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存升降机操作记录
	 */
	@RequiresPermissions("zhgd:sbElevatorOperator:add")
	@Log(title = "升降机操作记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbElevatorOperator sbElevatorOperator)
	{		
		return toAjax(sbElevatorOperatorService.insertSbElevatorOperator(sbElevatorOperator));
	}

	/**
	 * 修改升降机操作记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbElevatorOperator sbElevatorOperator = sbElevatorOperatorService.selectSbElevatorOperatorById(id);
		mmap.put("sbElevatorOperator", sbElevatorOperator);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存升降机操作记录
	 */
	@RequiresPermissions("zhgd:sbElevatorOperator:edit")
	@Log(title = "升降机操作记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbElevatorOperator sbElevatorOperator)
	{		
		return toAjax(sbElevatorOperatorService.updateSbElevatorOperator(sbElevatorOperator));
	}
	
	/**
	 * 删除升降机操作记录
	 */
	@RequiresPermissions("zhgd:sbElevatorOperator:remove")
	@Log(title = "升降机操作记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbElevatorOperatorService.deleteSbElevatorOperatorByIds(ids));
	}
	
}
