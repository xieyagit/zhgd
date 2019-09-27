package com.hujiang.project.zhgd.hjStatistics.controller;

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
import com.hujiang.project.zhgd.hjStatistics.domain.HjStatistics;
import com.hujiang.project.zhgd.hjStatistics.service.IHjStatisticsService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 每日考勤统计 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-30
 */
@Controller
@RequestMapping("/zhgd/hjStatistics")
public class HjStatisticsController extends BaseController
{
    private String prefix = "zhgd/hjStatistics";
	
	@Autowired
	private IHjStatisticsService hjStatisticsService;
	
	@RequiresPermissions("zhgd:hjStatistics:view")
	@GetMapping()
	public String hjStatistics()
	{
	    return prefix + "/hjStatistics";
	}
	
	/**
	 * 查询每日考勤统计列表
	 */
	@RequiresPermissions("zhgd:hjStatistics:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjStatistics hjStatistics)
	{
		startPage();
        List<HjStatistics> list = hjStatisticsService.selectHjStatisticsList(hjStatistics);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出每日考勤统计列表
	 */
	@RequiresPermissions("zhgd:hjStatistics:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjStatistics hjStatistics)
    {
    	List<HjStatistics> list = hjStatisticsService.selectHjStatisticsList(hjStatistics);
        ExcelUtil<HjStatistics> util = new ExcelUtil<HjStatistics>(HjStatistics.class);
        return util.exportExcel(list, "hjStatistics");
    }
	
	/**
	 * 新增每日考勤统计
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存每日考勤统计
	 */
	@RequiresPermissions("zhgd:hjStatistics:add")
	@Log(title = "每日考勤统计", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjStatistics hjStatistics)
	{		
		return toAjax(hjStatisticsService.insertHjStatistics(hjStatistics));
	}

	/**
	 * 修改每日考勤统计
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjStatistics hjStatistics = hjStatisticsService.selectHjStatisticsById(id);
		mmap.put("hjStatistics", hjStatistics);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存每日考勤统计
	 */
	@RequiresPermissions("zhgd:hjStatistics:edit")
	@Log(title = "每日考勤统计", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjStatistics hjStatistics)
	{		
		return toAjax(hjStatisticsService.updateHjStatistics(hjStatistics));
	}
	
	/**
	 * 删除每日考勤统计
	 */
	@RequiresPermissions("zhgd:hjStatistics:remove")
	@Log(title = "每日考勤统计", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjStatisticsService.deleteHjStatisticsByIds(ids));
	}
	
}
