package com.hujiang.project.zhgd.hjReport.controller;

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
import com.hujiang.project.zhgd.hjReport.domain.HjReport;
import com.hujiang.project.zhgd.hjReport.service.IHjReportService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 工作汇报 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-03
 */
@Controller
@RequestMapping("/zhgd/hjReport")
public class HjReportController extends BaseController
{
    private String prefix = "zhgd/hjReport";
	
	@Autowired
	private IHjReportService hjReportService;
	
	@RequiresPermissions("zhgd:hjReport:view")
	@GetMapping()
	public String hjReport()
	{
	    return prefix + "/hjReport";
	}
	
	/**
	 * 查询工作汇报列表
	 */
	@RequiresPermissions("zhgd:hjReport:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjReport hjReport)
	{
		startPage();
        List<HjReport> list = hjReportService.selectHjReportList(hjReport);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出工作汇报列表
	 */
	@RequiresPermissions("zhgd:hjReport:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjReport hjReport)
    {
    	List<HjReport> list = hjReportService.selectHjReportList(hjReport);
        ExcelUtil<HjReport> util = new ExcelUtil<HjReport>(HjReport.class);
        return util.exportExcel(list, "hjReport");
    }
	
	/**
	 * 新增工作汇报
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存工作汇报
	 */
	@RequiresPermissions("zhgd:hjReport:add")
	@Log(title = "工作汇报", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjReport hjReport)
	{		
		return toAjax(hjReportService.insertHjReport(hjReport));
	}

	/**
	 * 修改工作汇报
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjReport hjReport = hjReportService.selectHjReportById(id);
		mmap.put("hjReport", hjReport);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存工作汇报
	 */
	@RequiresPermissions("zhgd:hjReport:edit")
	@Log(title = "工作汇报", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjReport hjReport)
	{		
		return toAjax(hjReportService.updateHjReport(hjReport));
	}
	
	/**
	 * 删除工作汇报
	 */
	@RequiresPermissions("zhgd:hjReport:remove")
	@Log(title = "工作汇报", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjReportService.deleteHjReportByIds(ids));
	}
	
}
