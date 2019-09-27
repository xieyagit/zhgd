package com.hujiang.project.zhgd.sbCraneAddrecord.controller;

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
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.project.zhgd.sbCraneAddrecord.service.ISbCraneAddrecordService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 塔式起重机实时数据 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/zhgd/sbCraneAddrecord")
public class SbCraneAddrecordController extends BaseController
{
    private String prefix = "zhgd/sbCraneAddrecord";
	
	@Autowired
	private ISbCraneAddrecordService sbCraneAddrecordService;
	
	@RequiresPermissions("zhgd:sbCraneAddrecord:view")
	@GetMapping()
	public String sbCraneAddrecord()
	{
	    return prefix + "/sbCraneAddrecord";
	}
	
	/**
	 * 查询塔式起重机实时数据列表
	 */
	@RequiresPermissions("zhgd:sbCraneAddrecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbCraneAddrecord sbCraneAddrecord)
	{
		startPage();
        List<SbCraneAddrecord> list = sbCraneAddrecordService.selectSbCraneAddrecordList(sbCraneAddrecord);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出塔式起重机实时数据列表
	 */
	@RequiresPermissions("zhgd:sbCraneAddrecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbCraneAddrecord sbCraneAddrecord)
    {
    	List<SbCraneAddrecord> list = sbCraneAddrecordService.selectSbCraneAddrecordList(sbCraneAddrecord);
        ExcelUtil<SbCraneAddrecord> util = new ExcelUtil<SbCraneAddrecord>(SbCraneAddrecord.class);
        return util.exportExcel(list, "sbCraneAddrecord");
    }
	
	/**
	 * 新增塔式起重机实时数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存塔式起重机实时数据
	 */
	@RequiresPermissions("zhgd:sbCraneAddrecord:add")
	@Log(title = "塔式起重机实时数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbCraneAddrecord sbCraneAddrecord)
	{		
		return toAjax(sbCraneAddrecordService.insertSbCraneAddrecord(sbCraneAddrecord));
	}

	/**
	 * 修改塔式起重机实时数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SbCraneAddrecord sbCraneAddrecord = sbCraneAddrecordService.selectSbCraneAddrecordById(id);
		mmap.put("sbCraneAddrecord", sbCraneAddrecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存塔式起重机实时数据
	 */
	@RequiresPermissions("zhgd:sbCraneAddrecord:edit")
	@Log(title = "塔式起重机实时数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbCraneAddrecord sbCraneAddrecord)
	{		
		return toAjax(sbCraneAddrecordService.updateSbCraneAddrecord(sbCraneAddrecord));
	}
	
	/**
	 * 删除塔式起重机实时数据
	 */
	@RequiresPermissions("zhgd:sbCraneAddrecord:remove")
	@Log(title = "塔式起重机实时数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbCraneAddrecordService.deleteSbCraneAddrecordByIds(ids));
	}
	
}
