package com.hujiang.project.zhgd.sbApiFaceAttendance.controller;

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
import com.hujiang.project.zhgd.sbApiFaceAttendance.domain.SbApiFaceAttendance;
import com.hujiang.project.zhgd.sbApiFaceAttendance.service.ISbApiFaceAttendanceService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 人脸摄像机考勤记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/zhgd/sbApiFaceAttendance")
public class SbApiFaceAttendanceController extends BaseController
{
    private String prefix = "zhgd/sbApiFaceAttendance";
	
	@Autowired
	private ISbApiFaceAttendanceService sbApiFaceAttendanceService;
	
	@RequiresPermissions("zhgd:sbApiFaceAttendance:view")
	@GetMapping()
	public String sbApiFaceAttendance()
	{
	    return prefix + "/sbApiFaceAttendance";
	}
	
	/**
	 * 查询人脸摄像机考勤记录列表
	 */
	@RequiresPermissions("zhgd:sbApiFaceAttendance:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbApiFaceAttendance sbApiFaceAttendance)
	{
		startPage();
        List<SbApiFaceAttendance> list = sbApiFaceAttendanceService.selectSbApiFaceAttendanceList(sbApiFaceAttendance);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出人脸摄像机考勤记录列表
	 */
	@RequiresPermissions("zhgd:sbApiFaceAttendance:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbApiFaceAttendance sbApiFaceAttendance)
    {
    	List<SbApiFaceAttendance> list = sbApiFaceAttendanceService.selectSbApiFaceAttendanceList(sbApiFaceAttendance);
        ExcelUtil<SbApiFaceAttendance> util = new ExcelUtil<SbApiFaceAttendance>(SbApiFaceAttendance.class);
        return util.exportExcel(list, "sbApiFaceAttendance");
    }
	
	/**
	 * 新增人脸摄像机考勤记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存人脸摄像机考勤记录
	 */
	@RequiresPermissions("zhgd:sbApiFaceAttendance:add")
	@Log(title = "人脸摄像机考勤记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbApiFaceAttendance sbApiFaceAttendance)
	{		
		return toAjax(sbApiFaceAttendanceService.insertSbApiFaceAttendance(sbApiFaceAttendance));
	}

	/**
	 * 修改人脸摄像机考勤记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbApiFaceAttendance sbApiFaceAttendance = sbApiFaceAttendanceService.selectSbApiFaceAttendanceById(id);
		mmap.put("sbApiFaceAttendance", sbApiFaceAttendance);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存人脸摄像机考勤记录
	 */
	@RequiresPermissions("zhgd:sbApiFaceAttendance:edit")
	@Log(title = "人脸摄像机考勤记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbApiFaceAttendance sbApiFaceAttendance)
	{		
		return toAjax(sbApiFaceAttendanceService.updateSbApiFaceAttendance(sbApiFaceAttendance));
	}
	
	/**
	 * 删除人脸摄像机考勤记录
	 */
	@RequiresPermissions("zhgd:sbApiFaceAttendance:remove")
	@Log(title = "人脸摄像机考勤记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbApiFaceAttendanceService.deleteSbApiFaceAttendanceByIds(ids));
	}
	
}
