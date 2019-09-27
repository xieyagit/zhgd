package com.hujiang.project.zhgd.sbCraneLocatordata.controller;

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
import com.hujiang.project.zhgd.sbCraneLocatordata.domain.SbCraneLocatordata;
import com.hujiang.project.zhgd.sbCraneLocatordata.service.ISbCraneLocatordataService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 塔吊GPS定位数据
 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-11
 */
@Controller
@RequestMapping("/zhgd/sbCraneLocatordata")
public class SbCraneLocatordataController extends BaseController
{
    private String prefix = "zhgd/sbCraneLocatordata";
	
	@Autowired
	private ISbCraneLocatordataService sbCraneLocatordataService;
	
	@RequiresPermissions("zhgd:sbCraneLocatordata:view")
	@GetMapping()
	public String sbCraneLocatordata()
	{
	    return prefix + "/sbCraneLocatordata";
	}
	
	/**
	 * 查询塔吊GPS定位数据
列表
	 */
	@RequiresPermissions("zhgd:sbCraneLocatordata:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbCraneLocatordata sbCraneLocatordata)
	{
		startPage();
        List<SbCraneLocatordata> list = sbCraneLocatordataService.selectSbCraneLocatordataList(sbCraneLocatordata);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出塔吊GPS定位数据
列表
	 */
	@RequiresPermissions("zhgd:sbCraneLocatordata:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbCraneLocatordata sbCraneLocatordata)
    {
    	List<SbCraneLocatordata> list = sbCraneLocatordataService.selectSbCraneLocatordataList(sbCraneLocatordata);
        ExcelUtil<SbCraneLocatordata> util = new ExcelUtil<SbCraneLocatordata>(SbCraneLocatordata.class);
        return util.exportExcel(list, "sbCraneLocatordata");
    }
	
	/**
	 * 新增塔吊GPS定位数据

	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存塔吊GPS定位数据

	 */
	@RequiresPermissions("zhgd:sbCraneLocatordata:add")
	@Log(title = "塔吊GPS定位数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbCraneLocatordata sbCraneLocatordata)
	{		
		return toAjax(sbCraneLocatordataService.insertSbCraneLocatordata(sbCraneLocatordata));
	}

	/**
	 * 修改塔吊GPS定位数据

	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbCraneLocatordata sbCraneLocatordata = sbCraneLocatordataService.selectSbCraneLocatordataById(id);
		mmap.put("sbCraneLocatordata", sbCraneLocatordata);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存塔吊GPS定位数据

	 */
	@RequiresPermissions("zhgd:sbCraneLocatordata:edit")
	@Log(title = "塔吊GPS定位数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbCraneLocatordata sbCraneLocatordata)
	{		
		return toAjax(sbCraneLocatordataService.updateSbCraneLocatordata(sbCraneLocatordata));
	}
	
	/**
	 * 删除塔吊GPS定位数据

	 */
	@RequiresPermissions("zhgd:sbCraneLocatordata:remove")
	@Log(title = "塔吊GPS定位数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbCraneLocatordataService.deleteSbCraneLocatordataByIds(ids));
	}
	
}
