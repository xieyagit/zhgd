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
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitDisplayItems;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitDisplayItemsService;
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
@RequestMapping("/zhgd/sbDeeppitDisplayItems")
public class SbDeeppitDisplayItemsController extends BaseController
{
    private String prefix = "zhgd/sbDeeppitDisplayItems";
	
	@Autowired
	private ISbDeeppitDisplayItemsService sbDeeppitDisplayItemsService;
	
	@RequiresPermissions("zhgd:sbDeeppitDisplayItems:view")
	@GetMapping()
	public String sbDeeppitDisplayItems()
	{
	    return prefix + "/sbDeeppitDisplayItems";
	}
	
	/**
	 * 查询深基坑结构物监测因素列表
	 */
	@RequiresPermissions("zhgd:sbDeeppitDisplayItems:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbDeeppitDisplayItems sbDeeppitDisplayItems)
	{
		startPage();
        List<SbDeeppitDisplayItems> list = sbDeeppitDisplayItemsService.selectSbDeeppitDisplayItemsList(sbDeeppitDisplayItems);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出深基坑结构物监测因素列表
	 */
	@RequiresPermissions("zhgd:sbDeeppitDisplayItems:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbDeeppitDisplayItems sbDeeppitDisplayItems)
    {
    	List<SbDeeppitDisplayItems> list = sbDeeppitDisplayItemsService.selectSbDeeppitDisplayItemsList(sbDeeppitDisplayItems);
        ExcelUtil<SbDeeppitDisplayItems> util = new ExcelUtil<SbDeeppitDisplayItems>(SbDeeppitDisplayItems.class);
        return util.exportExcel(list, "sbDeeppitDisplayItems");
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
	@RequiresPermissions("zhgd:sbDeeppitDisplayItems:add")
	@Log(title = "深基坑结构物监测因素", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbDeeppitDisplayItems sbDeeppitDisplayItems)
	{		
		return toAjax(sbDeeppitDisplayItemsService.insertSbDeeppitDisplayItems(sbDeeppitDisplayItems));
	}

	/**
	 * 修改深基坑结构物监测因素
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbDeeppitDisplayItems sbDeeppitDisplayItems = sbDeeppitDisplayItemsService.selectSbDeeppitDisplayItemsById(id);
		mmap.put("sbDeeppitDisplayItems", sbDeeppitDisplayItems);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存深基坑结构物监测因素
	 */
	@RequiresPermissions("zhgd:sbDeeppitDisplayItems:edit")
	@Log(title = "深基坑结构物监测因素", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbDeeppitDisplayItems sbDeeppitDisplayItems)
	{		
		return toAjax(sbDeeppitDisplayItemsService.updateSbDeeppitDisplayItems(sbDeeppitDisplayItems));
	}
	
	/**
	 * 删除深基坑结构物监测因素
	 */
	@RequiresPermissions("zhgd:sbDeeppitDisplayItems:remove")
	@Log(title = "深基坑结构物监测因素", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbDeeppitDisplayItemsService.deleteSbDeeppitDisplayItemsByIds(ids));
	}
	
}
