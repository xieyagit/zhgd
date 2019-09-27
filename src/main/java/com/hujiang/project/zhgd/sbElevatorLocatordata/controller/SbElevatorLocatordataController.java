package com.hujiang.project.zhgd.sbElevatorLocatordata.controller;

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
import com.hujiang.project.zhgd.sbElevatorLocatordata.domain.SbElevatorLocatordata;
import com.hujiang.project.zhgd.sbElevatorLocatordata.service.ISbElevatorLocatordataService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 升降机GPS定位数据
 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-11
 */
@Controller
@RequestMapping("/zhgd/sbElevatorLocatordata")
public class SbElevatorLocatordataController extends BaseController
{
    private String prefix = "zhgd/sbElevatorLocatordata";
	
	@Autowired
	private ISbElevatorLocatordataService sbElevatorLocatordataService;
	
	@RequiresPermissions("zhgd:sbElevatorLocatordata:view")
	@GetMapping()
	public String sbElevatorLocatordata()
	{
	    return prefix + "/sbElevatorLocatordata";
	}
	
	/**
	 * 查询升降机GPS定位数据
列表
	 */
	@RequiresPermissions("zhgd:sbElevatorLocatordata:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbElevatorLocatordata sbElevatorLocatordata)
	{
		startPage();
        List<SbElevatorLocatordata> list = sbElevatorLocatordataService.selectSbElevatorLocatordataList(sbElevatorLocatordata);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出升降机GPS定位数据
列表
	 */
	@RequiresPermissions("zhgd:sbElevatorLocatordata:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbElevatorLocatordata sbElevatorLocatordata)
    {
    	List<SbElevatorLocatordata> list = sbElevatorLocatordataService.selectSbElevatorLocatordataList(sbElevatorLocatordata);
        ExcelUtil<SbElevatorLocatordata> util = new ExcelUtil<SbElevatorLocatordata>(SbElevatorLocatordata.class);
        return util.exportExcel(list, "sbElevatorLocatordata");
    }
	
	/**
	 * 新增升降机GPS定位数据

	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存升降机GPS定位数据

	 */
	@RequiresPermissions("zhgd:sbElevatorLocatordata:add")
	@Log(title = "升降机GPS定位数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbElevatorLocatordata sbElevatorLocatordata)
	{		
		return toAjax(sbElevatorLocatordataService.insertSbElevatorLocatordata(sbElevatorLocatordata));
	}

	/**
	 * 修改升降机GPS定位数据

	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbElevatorLocatordata sbElevatorLocatordata = sbElevatorLocatordataService.selectSbElevatorLocatordataById(id);
		mmap.put("sbElevatorLocatordata", sbElevatorLocatordata);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存升降机GPS定位数据

	 */
	@RequiresPermissions("zhgd:sbElevatorLocatordata:edit")
	@Log(title = "升降机GPS定位数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbElevatorLocatordata sbElevatorLocatordata)
	{		
		return toAjax(sbElevatorLocatordataService.updateSbElevatorLocatordata(sbElevatorLocatordata));
	}
	
	/**
	 * 删除升降机GPS定位数据

	 */
	@RequiresPermissions("zhgd:sbElevatorLocatordata:remove")
	@Log(title = "升降机GPS定位数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbElevatorLocatordataService.deleteSbElevatorLocatordataByIds(ids));
	}
	
}
