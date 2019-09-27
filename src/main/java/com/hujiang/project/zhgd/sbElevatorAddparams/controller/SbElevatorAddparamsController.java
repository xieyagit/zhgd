package com.hujiang.project.zhgd.sbElevatorAddparams.controller;

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
import com.hujiang.project.zhgd.sbElevatorAddparams.domain.SbElevatorAddparams;
import com.hujiang.project.zhgd.sbElevatorAddparams.service.ISbElevatorAddparamsService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 升降机参数   信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-27
 */
@Controller
@RequestMapping("/zhgd/sbElevatorAddparams")
public class SbElevatorAddparamsController extends BaseController
{
    private String prefix = "zhgd/sbElevatorAddparams";
	
	@Autowired
	private ISbElevatorAddparamsService sbElevatorAddparamsService;
	
	@RequiresPermissions("zhgd:sbElevatorAddparams:view")
	@GetMapping()
	public String sbElevatorAddparams()
	{
	    return prefix + "/sbElevatorAddparams";
	}
	
	/**
	 * 查询升降机参数  列表
	 */
	@RequiresPermissions("zhgd:sbElevatorAddparams:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbElevatorAddparams sbElevatorAddparams)
	{
		startPage();
        List<SbElevatorAddparams> list = sbElevatorAddparamsService.selectSbElevatorAddparamsList(sbElevatorAddparams);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出升降机参数  列表
	 */
	@RequiresPermissions("zhgd:sbElevatorAddparams:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbElevatorAddparams sbElevatorAddparams)
    {
    	List<SbElevatorAddparams> list = sbElevatorAddparamsService.selectSbElevatorAddparamsList(sbElevatorAddparams);
        ExcelUtil<SbElevatorAddparams> util = new ExcelUtil<SbElevatorAddparams>(SbElevatorAddparams.class);
        return util.exportExcel(list, "sbElevatorAddparams");
    }
	
	/**
	 * 新增升降机参数  
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存升降机参数  
	 */
	@RequiresPermissions("zhgd:sbElevatorAddparams:add")
	@Log(title = "升降机参数  ", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbElevatorAddparams sbElevatorAddparams)
	{		
		return toAjax(sbElevatorAddparamsService.insertSbElevatorAddparams(sbElevatorAddparams));
	}

	/**
	 * 修改升降机参数  
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbElevatorAddparams sbElevatorAddparams = sbElevatorAddparamsService.selectSbElevatorAddparamsById(id);
		mmap.put("sbElevatorAddparams", sbElevatorAddparams);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存升降机参数  
	 */
	@RequiresPermissions("zhgd:sbElevatorAddparams:edit")
	@Log(title = "升降机参数  ", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbElevatorAddparams sbElevatorAddparams)
	{		
		return toAjax(sbElevatorAddparamsService.updateSbElevatorAddparams(sbElevatorAddparams));
	}
	
	/**
	 * 删除升降机参数  
	 */
	@RequiresPermissions("zhgd:sbElevatorAddparams:remove")
	@Log(title = "升降机参数  ", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbElevatorAddparamsService.deleteSbElevatorAddparamsByIds(ids));
	}
	
}
