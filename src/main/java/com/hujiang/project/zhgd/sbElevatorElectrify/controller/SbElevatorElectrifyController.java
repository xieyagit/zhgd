package com.hujiang.project.zhgd.sbElevatorElectrify.controller;

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
import com.hujiang.project.zhgd.sbElevatorElectrify.domain.SbElevatorElectrify;
import com.hujiang.project.zhgd.sbElevatorElectrify.service.ISbElevatorElectrifyService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 升降机通电时间接口 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-27
 */
@Controller
@RequestMapping("/zhgd/sbElevatorElectrify")
public class SbElevatorElectrifyController extends BaseController
{
    private String prefix = "zhgd/sbElevatorElectrify";
	
	@Autowired
	private ISbElevatorElectrifyService sbElevatorElectrifyService;
	
	@RequiresPermissions("zhgd:sbElevatorElectrify:view")
	@GetMapping()
	public String sbElevatorElectrify()
	{
	    return prefix + "/sbElevatorElectrify";
	}
	
	/**
	 * 查询升降机通电时间接口列表
	 */
	@RequiresPermissions("zhgd:sbElevatorElectrify:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbElevatorElectrify sbElevatorElectrify)
	{
		startPage();
        List<SbElevatorElectrify> list = sbElevatorElectrifyService.selectSbElevatorElectrifyList(sbElevatorElectrify);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出升降机通电时间接口列表
	 */
	@RequiresPermissions("zhgd:sbElevatorElectrify:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbElevatorElectrify sbElevatorElectrify)
    {
    	List<SbElevatorElectrify> list = sbElevatorElectrifyService.selectSbElevatorElectrifyList(sbElevatorElectrify);
        ExcelUtil<SbElevatorElectrify> util = new ExcelUtil<SbElevatorElectrify>(SbElevatorElectrify.class);
        return util.exportExcel(list, "sbElevatorElectrify");
    }
	
	/**
	 * 新增升降机通电时间接口
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存升降机通电时间接口
	 */
	@RequiresPermissions("zhgd:sbElevatorElectrify:add")
	@Log(title = "升降机通电时间接口", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbElevatorElectrify sbElevatorElectrify)
	{		
		return toAjax(sbElevatorElectrifyService.insertSbElevatorElectrify(sbElevatorElectrify));
	}

	/**
	 * 修改升降机通电时间接口
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbElevatorElectrify sbElevatorElectrify = sbElevatorElectrifyService.selectSbElevatorElectrifyById(id);
		mmap.put("sbElevatorElectrify", sbElevatorElectrify);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存升降机通电时间接口
	 */
	@RequiresPermissions("zhgd:sbElevatorElectrify:edit")
	@Log(title = "升降机通电时间接口", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbElevatorElectrify sbElevatorElectrify)
	{		
		return toAjax(sbElevatorElectrifyService.updateSbElevatorElectrify(sbElevatorElectrify));
	}
	
	/**
	 * 删除升降机通电时间接口
	 */
	@RequiresPermissions("zhgd:sbElevatorElectrify:remove")
	@Log(title = "升降机通电时间接口", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbElevatorElectrifyService.deleteSbElevatorElectrifyByIds(ids));
	}
	
}
