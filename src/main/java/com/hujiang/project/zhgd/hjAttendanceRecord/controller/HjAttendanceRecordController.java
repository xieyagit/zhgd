package com.hujiang.project.zhgd.hjAttendanceRecord.controller;

import org.springframework.stereotype.Controller;
import java.util.List;
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
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 考勤记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjAttendanceRecord")
public class HjAttendanceRecordController extends BaseController
{
    private String prefix = "zhgd/hjAttendanceRecord";
	
	@Autowired
	private IHjAttendanceRecordService hjAttendanceRecordService;
	
	@RequiresPermissions("zhgd:hjAttendanceRecord:view")
	@GetMapping()
	public String hjAttendanceRecord()
	{
	    return prefix + "/hjAttendanceRecord";
	}
	
	/**
	 * 查询考勤记录列表
	 */
	@RequiresPermissions("zhgd:hjAttendanceRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjAttendanceRecord hjAttendanceRecord)
	{
		startPage();
        List<HjAttendanceRecord> list = hjAttendanceRecordService.selectHjAttendanceRecordList(hjAttendanceRecord);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出考勤记录列表
	 */
	@RequiresPermissions("zhgd:hjAttendanceRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjAttendanceRecord hjAttendanceRecord)
    {
    	List<HjAttendanceRecord> list = hjAttendanceRecordService.selectHjAttendanceRecordList(hjAttendanceRecord);
        ExcelUtil<HjAttendanceRecord> util = new ExcelUtil<HjAttendanceRecord>(HjAttendanceRecord.class);
        return util.exportExcel(list, "hjAttendanceRecord");
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
	@RequiresPermissions("zhgd:hjAttendanceRecord:add")
	@Log(title = "考勤记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjAttendanceRecord hjAttendanceRecord)
	{		
		return toAjax(hjAttendanceRecordService.insertHjAttendanceRecord(hjAttendanceRecord));
	}

	/**
	 * 修改考勤记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjAttendanceRecord hjAttendanceRecord = hjAttendanceRecordService.selectHjAttendanceRecordById(id);
		mmap.put("hjAttendanceRecord", hjAttendanceRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存考勤记录
	 */
	@RequiresPermissions("zhgd:hjAttendanceRecord:edit")
	@Log(title = "考勤记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjAttendanceRecord hjAttendanceRecord)
	{		
		return toAjax(hjAttendanceRecordService.updateHjAttendanceRecord(hjAttendanceRecord));
	}
	
	/**
	 * 删除考勤记录
	 */
	@RequiresPermissions("zhgd:hjAttendanceRecord:remove")
	@Log(title = "考勤记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjAttendanceRecordService.deleteHjAttendanceRecordByIds(ids));
	}
	
}
