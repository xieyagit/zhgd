package com.hujiang.project.zhgd.lyStatistics.controller;

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
import com.hujiang.project.zhgd.lyStatistics.domain.LyStatistics;
import com.hujiang.project.zhgd.lyStatistics.service.ILyStatisticsService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 楼宇人员进出每日统计 信息操作处理
 * 
 * @author hujiang
 * @date 2020-03-13
 */
@Controller
@RequestMapping("/zhgd/lyStatistics")
public class LyStatisticsController extends BaseController
{
    private String prefix = "zhgd/lyStatistics";
	
	@Autowired
	private ILyStatisticsService lyStatisticsService;
	
	@RequiresPermissions("zhgd:lyStatistics:view")
	@GetMapping()
	public String lyStatistics()
	{
	    return prefix + "/lyStatistics";
	}
	
	/**
	 * 查询楼宇人员进出每日统计列表
	 */
	@RequiresPermissions("zhgd:lyStatistics:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(LyStatistics lyStatistics)
	{
		startPage();
        List<LyStatistics> list = lyStatisticsService.selectLyStatisticsList(lyStatistics);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出楼宇人员进出每日统计列表
	 */
	@RequiresPermissions("zhgd:lyStatistics:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LyStatistics lyStatistics)
    {
    	List<LyStatistics> list = lyStatisticsService.selectLyStatisticsList(lyStatistics);
        ExcelUtil<LyStatistics> util = new ExcelUtil<LyStatistics>(LyStatistics.class);
        return util.exportExcel(list, "lyStatistics");
    }
	
	/**
	 * 新增楼宇人员进出每日统计
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存楼宇人员进出每日统计
	 */
	@RequiresPermissions("zhgd:lyStatistics:add")
	@Log(title = "楼宇人员进出每日统计", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(LyStatistics lyStatistics)
	{		
		return toAjax(lyStatisticsService.insertLyStatistics(lyStatistics));
	}

	/**
	 * 修改楼宇人员进出每日统计
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		LyStatistics lyStatistics = lyStatisticsService.selectLyStatisticsById(id);
		mmap.put("lyStatistics", lyStatistics);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存楼宇人员进出每日统计
	 */
	@RequiresPermissions("zhgd:lyStatistics:edit")
	@Log(title = "楼宇人员进出每日统计", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(LyStatistics lyStatistics)
	{		
		return toAjax(lyStatisticsService.updateLyStatistics(lyStatistics));
	}
	
	/**
	 * 删除楼宇人员进出每日统计
	 */
	@RequiresPermissions("zhgd:lyStatistics:remove")
	@Log(title = "楼宇人员进出每日统计", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(lyStatisticsService.deleteLyStatisticsByIds(ids));
	}
	
}
