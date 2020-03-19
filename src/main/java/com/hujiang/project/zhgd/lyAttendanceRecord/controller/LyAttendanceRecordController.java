package com.hujiang.project.zhgd.lyAttendanceRecord.controller;

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
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyAttendanceRecord.service.ILyAttendanceRecordService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 考勤记录 信息操作处理
 * 
 * @author hujiang
 * @date 2020-03-08
 */
@Controller
@RequestMapping("/zhgd/lyAttendanceRecord")
public class LyAttendanceRecordController extends BaseController
{
    private String prefix = "zhgd/lyAttendanceRecord";
	
	@Autowired
	private ILyAttendanceRecordService lyAttendanceRecordService;
	
	@RequiresPermissions("zhgd:lyAttendanceRecord:view")
	@GetMapping()
	public String lyAttendanceRecord()
	{
	    return prefix + "/lyAttendanceRecord";
	}
	
	/**
	 * 查询考勤记录列表
	 */
	@RequiresPermissions("zhgd:lyAttendanceRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(LyAttendanceRecord lyAttendanceRecord)
	{
		startPage();
        List<LyAttendanceRecord> list = lyAttendanceRecordService.selectLyAttendanceRecordList(lyAttendanceRecord);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出考勤记录列表
	 */
	@RequiresPermissions("zhgd:lyAttendanceRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LyAttendanceRecord lyAttendanceRecord)
    {
    	List<LyAttendanceRecord> list = lyAttendanceRecordService.selectLyAttendanceRecordList(lyAttendanceRecord);
        ExcelUtil<LyAttendanceRecord> util = new ExcelUtil<LyAttendanceRecord>(LyAttendanceRecord.class);
        return util.exportExcel(list, "lyAttendanceRecord");
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
	@RequiresPermissions("zhgd:lyAttendanceRecord:add")
	@Log(title = "考勤记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(LyAttendanceRecord lyAttendanceRecord)
	{		
		return toAjax(lyAttendanceRecordService.insertLyAttendanceRecord(lyAttendanceRecord));
	}

	/**
	 * 修改考勤记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		LyAttendanceRecord lyAttendanceRecord = lyAttendanceRecordService.selectLyAttendanceRecordById(id);
		mmap.put("lyAttendanceRecord", lyAttendanceRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存考勤记录
	 */
	@RequiresPermissions("zhgd:lyAttendanceRecord:edit")
	@Log(title = "考勤记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(LyAttendanceRecord lyAttendanceRecord)
	{		
		return toAjax(lyAttendanceRecordService.updateLyAttendanceRecord(lyAttendanceRecord));
	}
	
	/**
	 * 删除考勤记录
	 */
	@RequiresPermissions("zhgd:lyAttendanceRecord:remove")
	@Log(title = "考勤记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(lyAttendanceRecordService.deleteLyAttendanceRecordByIds(ids));
	}
	
}
