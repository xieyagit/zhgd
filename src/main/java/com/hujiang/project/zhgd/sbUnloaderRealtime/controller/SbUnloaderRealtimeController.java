package com.hujiang.project.zhgd.sbUnloaderRealtime.controller;

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
import com.hujiang.project.zhgd.sbUnloaderRealtime.domain.SbUnloaderRealtime;
import com.hujiang.project.zhgd.sbUnloaderRealtime.service.ISbUnloaderRealtimeService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 卸料实时数据 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-11
 */
@Controller
@RequestMapping("/zhgd/sbUnloaderRealtime")
public class SbUnloaderRealtimeController extends BaseController
{
    private String prefix = "zhgd/sbUnloaderRealtime";
	
	@Autowired
	private ISbUnloaderRealtimeService sbUnloaderRealtimeService;
	
	@RequiresPermissions("zhgd:sbUnloaderRealtime:view")
	@GetMapping()
	public String sbUnloaderRealtime()
	{
	    return prefix + "/sbUnloaderRealtime";
	}
	
	/**
	 * 查询卸料实时数据列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderRealtime:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbUnloaderRealtime sbUnloaderRealtime)
	{
		startPage();
        List<SbUnloaderRealtime> list = sbUnloaderRealtimeService.selectSbUnloaderRealtimeList(sbUnloaderRealtime);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出卸料实时数据列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderRealtime:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbUnloaderRealtime sbUnloaderRealtime)
    {
    	List<SbUnloaderRealtime> list = sbUnloaderRealtimeService.selectSbUnloaderRealtimeList(sbUnloaderRealtime);
        ExcelUtil<SbUnloaderRealtime> util = new ExcelUtil<SbUnloaderRealtime>(SbUnloaderRealtime.class);
        return util.exportExcel(list, "sbUnloaderRealtime");
    }
	
	/**
	 * 新增卸料实时数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存卸料实时数据
	 */
	@RequiresPermissions("zhgd:sbUnloaderRealtime:add")
	@Log(title = "卸料实时数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbUnloaderRealtime sbUnloaderRealtime)
	{		
		return toAjax(sbUnloaderRealtimeService.insertSbUnloaderRealtime(sbUnloaderRealtime));
	}

	/**
	 * 修改卸料实时数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbUnloaderRealtime sbUnloaderRealtime = sbUnloaderRealtimeService.selectSbUnloaderRealtimeById(id);
		mmap.put("sbUnloaderRealtime", sbUnloaderRealtime);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存卸料实时数据
	 */
	@RequiresPermissions("zhgd:sbUnloaderRealtime:edit")
	@Log(title = "卸料实时数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbUnloaderRealtime sbUnloaderRealtime)
	{		
		return toAjax(sbUnloaderRealtimeService.updateSbUnloaderRealtime(sbUnloaderRealtime));
	}
	
	/**
	 * 删除卸料实时数据
	 */
	@RequiresPermissions("zhgd:sbUnloaderRealtime:remove")
	@Log(title = "卸料实时数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbUnloaderRealtimeService.deleteSbUnloaderRealtimeByIds(ids));
	}
	
}
