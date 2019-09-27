package com.hujiang.project.zhgd.hjDeeppit.controller;

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
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitDisplay;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitDisplayService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 深基坑结构物监测因素 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-04
 */
@Controller
@RequestMapping("/zhgd/sbDeeppitDisplay")
public class SbDeeppitDisplayController extends BaseController
{
    private String prefix = "zhgd/sbDeeppitDisplay";
	
	@Autowired
	private ISbDeeppitDisplayService sbDeeppitDisplayService;
	
	@RequiresPermissions("zhgd:sbDeeppitDisplay:view")
	@GetMapping()
	public String sbDeeppitDisplay()
	{
	    return prefix + "/sbDeeppitDisplay";
	}
	
	/**
	 * 查询深基坑结构物监测因素列表
	 */
	@RequiresPermissions("zhgd:sbDeeppitDisplay:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbDeeppitDisplay sbDeeppitDisplay)
	{
		startPage();
        List<SbDeeppitDisplay> list = sbDeeppitDisplayService.selectSbDeeppitDisplayList(sbDeeppitDisplay);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出深基坑结构物监测因素列表
	 */
	@RequiresPermissions("zhgd:sbDeeppitDisplay:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbDeeppitDisplay sbDeeppitDisplay)
    {
    	List<SbDeeppitDisplay> list = sbDeeppitDisplayService.selectSbDeeppitDisplayList(sbDeeppitDisplay);
        ExcelUtil<SbDeeppitDisplay> util = new ExcelUtil<SbDeeppitDisplay>(SbDeeppitDisplay.class);
        return util.exportExcel(list, "sbDeeppitDisplay");
    }
	
	/**
	 * 新增深基坑结构物监测因素
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存深基坑结构物监测因素
	 */
	@RequiresPermissions("zhgd:sbDeeppitDisplay:add")
	@Log(title = "深基坑结构物监测因素", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbDeeppitDisplay sbDeeppitDisplay)
	{		
		return toAjax(sbDeeppitDisplayService.insertSbDeeppitDisplay(sbDeeppitDisplay));
	}

	/**
	 * 修改深基坑结构物监测因素
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbDeeppitDisplay sbDeeppitDisplay = sbDeeppitDisplayService.selectSbDeeppitDisplayById(id);
		mmap.put("sbDeeppitDisplay", sbDeeppitDisplay);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存深基坑结构物监测因素
	 */
	@RequiresPermissions("zhgd:sbDeeppitDisplay:edit")
	@Log(title = "深基坑结构物监测因素", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbDeeppitDisplay sbDeeppitDisplay)
	{		
		return toAjax(sbDeeppitDisplayService.updateSbDeeppitDisplay(sbDeeppitDisplay));
	}
	
	/**
	 * 删除深基坑结构物监测因素
	 */
	@RequiresPermissions("zhgd:sbDeeppitDisplay:remove")
	@Log(title = "深基坑结构物监测因素", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbDeeppitDisplayService.deleteSbDeeppitDisplayByIds(ids));
	}
	
}
