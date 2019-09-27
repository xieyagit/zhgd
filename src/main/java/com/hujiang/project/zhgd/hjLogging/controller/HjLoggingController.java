package com.hujiang.project.zhgd.hjLogging.controller;

import java.util.List;

import com.hujiang.project.zhgd.hjLogging.domain.HjLogging;
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
import com.hujiang.project.zhgd.hjLogging.service.IHjLoggingService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 考勤，实名制日志 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-14
 */
@Controller
@RequestMapping("/zhgd/hjLogging")
public class HjLoggingController extends BaseController
{
    private String prefix = "zhgd/hjLogging";
	
	@Autowired
	private IHjLoggingService hjLoggingService;
	
	@RequiresPermissions("zhgd:hjLogging:view")
	@GetMapping()
	public String hjLogging()
	{
	    return prefix + "/hjLogging";
	}
	
	/**
	 * 查询考勤，实名制日志列表
	 */
	@RequiresPermissions("zhgd:hjLogging:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjLogging hjLogging)
	{
		startPage();
        List<HjLogging> list = hjLoggingService.selectHjLoggingList(hjLogging);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出考勤，实名制日志列表
	 */
	@RequiresPermissions("zhgd:hjLogging:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjLogging hjLogging)
    {
    	List<HjLogging> list = hjLoggingService.selectHjLoggingList(hjLogging);
        ExcelUtil<HjLogging> util = new ExcelUtil<HjLogging>(HjLogging.class);
        return util.exportExcel(list, "hjLogging");
    }
	
	/**
	 * 新增考勤，实名制日志
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存考勤，实名制日志
	 */
	@RequiresPermissions("zhgd:hjLogging:add")
	@Log(title = "考勤，实名制日志", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjLogging hjLogging)
	{		
		return toAjax(hjLoggingService.insertHjLogging(hjLogging));
	}

	/**
	 * 修改考勤，实名制日志
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjLogging hjLogging = hjLoggingService.selectHjLoggingById(id);
		mmap.put("hjLogging", hjLogging);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存考勤，实名制日志
	 */
	@RequiresPermissions("zhgd:hjLogging:edit")
	@Log(title = "考勤，实名制日志", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjLogging hjLogging)
	{		
		return toAjax(hjLoggingService.updateHjLogging(hjLogging));
	}
	
	/**
	 * 删除考勤，实名制日志
	 */
	@RequiresPermissions("zhgd:hjLogging:remove")
	@Log(title = "考勤，实名制日志", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjLoggingService.deleteHjLoggingByIds(ids));
	}
	
}
