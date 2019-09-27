package com.hujiang.project.zhgd.sbEquipmentRecord.controller;

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
import com.hujiang.project.zhgd.sbEquipmentRecord.domain.SbEquipmentRecord;
import com.hujiang.project.zhgd.sbEquipmentRecord.service.ISbEquipmentRecordService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 定位记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-29
 */
@Controller
@RequestMapping("/zhgd/sbEquipmentRecord")
public class SbEquipmentRecordController extends BaseController
{
    private String prefix = "zhgd/sbEquipmentRecord";
	
	@Autowired
	private ISbEquipmentRecordService sbEquipmentRecordService;
	
	@RequiresPermissions("zhgd:sbEquipmentRecord:view")
	@GetMapping()
	public String sbEquipmentRecord()
	{
	    return prefix + "/sbEquipmentRecord";
	}
	
	/**
	 * 查询定位记录列表
	 */
	@RequiresPermissions("zhgd:sbEquipmentRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbEquipmentRecord sbEquipmentRecord)
	{
		startPage();
        List<SbEquipmentRecord> list = sbEquipmentRecordService.selectSbEquipmentRecordList(sbEquipmentRecord);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出定位记录列表
	 */
	@RequiresPermissions("zhgd:sbEquipmentRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbEquipmentRecord sbEquipmentRecord)
    {
    	List<SbEquipmentRecord> list = sbEquipmentRecordService.selectSbEquipmentRecordList(sbEquipmentRecord);
        ExcelUtil<SbEquipmentRecord> util = new ExcelUtil<SbEquipmentRecord>(SbEquipmentRecord.class);
        return util.exportExcel(list, "sbEquipmentRecord");
    }
	
	/**
	 * 新增定位记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存定位记录
	 */
	@RequiresPermissions("zhgd:sbEquipmentRecord:add")
	@Log(title = "定位记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbEquipmentRecord sbEquipmentRecord)
	{		
		return toAjax(sbEquipmentRecordService.insertSbEquipmentRecord(sbEquipmentRecord));
	}

	/**
	 * 修改定位记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbEquipmentRecord sbEquipmentRecord = sbEquipmentRecordService.selectSbEquipmentRecordById(id);
		mmap.put("sbEquipmentRecord", sbEquipmentRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存定位记录
	 */
	@RequiresPermissions("zhgd:sbEquipmentRecord:edit")
	@Log(title = "定位记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbEquipmentRecord sbEquipmentRecord)
	{		
		return toAjax(sbEquipmentRecordService.updateSbEquipmentRecord(sbEquipmentRecord));
	}
	
	/**
	 * 删除定位记录
	 */
	@RequiresPermissions("zhgd:sbEquipmentRecord:remove")
	@Log(title = "定位记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbEquipmentRecordService.deleteSbEquipmentRecordByIds(ids));
	}
	
}
