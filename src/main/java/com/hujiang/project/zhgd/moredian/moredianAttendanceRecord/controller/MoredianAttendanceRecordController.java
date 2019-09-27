package com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.controller;

import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.domain.MoredianAttendanceRecord;
import com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.service.IMoredianAttendanceRecordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 考勤记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-14
 */
@Controller
@RequestMapping("/moredian/moredianAttendanceRecord")
public class MoredianAttendanceRecordController extends BaseController
{
    private String prefix = "moredian/moredianAttendanceRecord";
	
	@Autowired
	private IMoredianAttendanceRecordService moredianAttendanceRecordService;
	
	@RequiresPermissions("moredian:moredianAttendanceRecord:view")
	@GetMapping()
	public String moredianAttendanceRecord()
	{
	    return prefix + "/moredianAttendanceRecord";
	}
	
	/**
	 * 查询考勤记录列表
	 */
	@RequiresPermissions("moredian:moredianAttendanceRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MoredianAttendanceRecord moredianAttendanceRecord)
	{
		startPage();
        List<MoredianAttendanceRecord> list = moredianAttendanceRecordService.selectMoredianAttendanceRecordList(moredianAttendanceRecord);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出考勤记录列表
	 */
	@RequiresPermissions("moredian:moredianAttendanceRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MoredianAttendanceRecord moredianAttendanceRecord)
    {
    	List<MoredianAttendanceRecord> list = moredianAttendanceRecordService.selectMoredianAttendanceRecordList(moredianAttendanceRecord);
        ExcelUtil<MoredianAttendanceRecord> util = new ExcelUtil<MoredianAttendanceRecord>(MoredianAttendanceRecord.class);
        return util.exportExcel(list, "moredianAttendanceRecord");
    }
	
	/**
	 * 新增考勤记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存考勤记录
	 */
	@RequiresPermissions("moredian:moredianAttendanceRecord:add")
	@Log(title = "考勤记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MoredianAttendanceRecord moredianAttendanceRecord)
	{		
		return toAjax(moredianAttendanceRecordService.insertMoredianAttendanceRecord(moredianAttendanceRecord));
	}

	/**
	 * 修改考勤记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MoredianAttendanceRecord moredianAttendanceRecord = moredianAttendanceRecordService.selectMoredianAttendanceRecordById(id);
		mmap.put("moredianAttendanceRecord", moredianAttendanceRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存考勤记录
	 */
	@RequiresPermissions("moredian:moredianAttendanceRecord:edit")
	@Log(title = "考勤记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MoredianAttendanceRecord moredianAttendanceRecord)
	{		
		return toAjax(moredianAttendanceRecordService.updateMoredianAttendanceRecord(moredianAttendanceRecord));
	}
	
	/**
	 * 删除考勤记录
	 */
	@RequiresPermissions("moredian:moredianAttendanceRecord:remove")
	@Log(title = "考勤记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(moredianAttendanceRecordService.deleteMoredianAttendanceRecordByIds(ids));
	}
	
}
