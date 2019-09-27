package com.hujiang.project.zhgd.sbUnloaderAlarmtime.controller;

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
import com.hujiang.project.zhgd.sbUnloaderAlarmtime.domain.SbUnloaderAlarmtime;
import com.hujiang.project.zhgd.sbUnloaderAlarmtime.service.ISbUnloaderAlarmtimeService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 卸料报警时刻数据 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-11
 */
@Controller
@RequestMapping("/zhgd/sbUnloaderAlarmtime")
public class SbUnloaderAlarmtimeController extends BaseController
{
    private String prefix = "zhgd/sbUnloaderAlarmtime";
	
	@Autowired
	private ISbUnloaderAlarmtimeService sbUnloaderAlarmtimeService;
	
	@RequiresPermissions("zhgd:sbUnloaderAlarmtime:view")
	@GetMapping()
	public String sbUnloaderAlarmtime()
	{
	    return prefix + "/sbUnloaderAlarmtime";
	}
	
	/**
	 * 查询卸料报警时刻数据列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderAlarmtime:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbUnloaderAlarmtime sbUnloaderAlarmtime)
	{
		startPage();
        List<SbUnloaderAlarmtime> list = sbUnloaderAlarmtimeService.selectSbUnloaderAlarmtimeList(sbUnloaderAlarmtime);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出卸料报警时刻数据列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderAlarmtime:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbUnloaderAlarmtime sbUnloaderAlarmtime)
    {
    	List<SbUnloaderAlarmtime> list = sbUnloaderAlarmtimeService.selectSbUnloaderAlarmtimeList(sbUnloaderAlarmtime);
        ExcelUtil<SbUnloaderAlarmtime> util = new ExcelUtil<SbUnloaderAlarmtime>(SbUnloaderAlarmtime.class);
        return util.exportExcel(list, "sbUnloaderAlarmtime");
    }
	
	/**
	 * 新增卸料报警时刻数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存卸料报警时刻数据
	 */
	@RequiresPermissions("zhgd:sbUnloaderAlarmtime:add")
	@Log(title = "卸料报警时刻数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbUnloaderAlarmtime sbUnloaderAlarmtime)
	{		
		return toAjax(sbUnloaderAlarmtimeService.insertSbUnloaderAlarmtime(sbUnloaderAlarmtime));
	}

	/**
	 * 修改卸料报警时刻数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbUnloaderAlarmtime sbUnloaderAlarmtime = sbUnloaderAlarmtimeService.selectSbUnloaderAlarmtimeById(id);
		mmap.put("sbUnloaderAlarmtime", sbUnloaderAlarmtime);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存卸料报警时刻数据
	 */
	@RequiresPermissions("zhgd:sbUnloaderAlarmtime:edit")
	@Log(title = "卸料报警时刻数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbUnloaderAlarmtime sbUnloaderAlarmtime)
	{		
		return toAjax(sbUnloaderAlarmtimeService.updateSbUnloaderAlarmtime(sbUnloaderAlarmtime));
	}
	
	/**
	 * 删除卸料报警时刻数据
	 */
	@RequiresPermissions("zhgd:sbUnloaderAlarmtime:remove")
	@Log(title = "卸料报警时刻数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbUnloaderAlarmtimeService.deleteSbUnloaderAlarmtimeByIds(ids));
	}
	
}
