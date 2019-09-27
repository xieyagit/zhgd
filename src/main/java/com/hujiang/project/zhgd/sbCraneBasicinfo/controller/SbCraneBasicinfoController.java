package com.hujiang.project.zhgd.sbCraneBasicinfo.controller;

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
import com.hujiang.project.zhgd.sbCraneBasicinfo.domain.SbCraneBasicinfo;
import com.hujiang.project.zhgd.sbCraneBasicinfo.service.ISbCraneBasicinfoService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 塔式起重机设备基本 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-20
 */
@Controller
@RequestMapping("/zhgd/sbCraneBasicinfo")
public class SbCraneBasicinfoController extends BaseController
{
    private String prefix = "zhgd/sbCraneBasicinfo";
	
	@Autowired
	private ISbCraneBasicinfoService sbCraneBasicinfoService;
	
	@RequiresPermissions("zhgd:sbCraneBasicinfo:view")
	@GetMapping()
	public String sbCraneBasicinfo()
	{
	    return prefix + "/sbCraneBasicinfo";
	}
	
	/**
	 * 查询塔式起重机设备基本列表
	 */
	@RequiresPermissions("zhgd:sbCraneBasicinfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbCraneBasicinfo sbCraneBasicinfo)
	{
		startPage();
        List<SbCraneBasicinfo> list = sbCraneBasicinfoService.selectSbCraneBasicinfoList(sbCraneBasicinfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出塔式起重机设备基本列表
	 */
	@RequiresPermissions("zhgd:sbCraneBasicinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbCraneBasicinfo sbCraneBasicinfo)
    {
    	List<SbCraneBasicinfo> list = sbCraneBasicinfoService.selectSbCraneBasicinfoList(sbCraneBasicinfo);
        ExcelUtil<SbCraneBasicinfo> util = new ExcelUtil<SbCraneBasicinfo>(SbCraneBasicinfo.class);
        return util.exportExcel(list, "sbCraneBasicinfo");
    }
	
	/**
	 * 新增塔式起重机设备基本
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存塔式起重机设备基本
	 */
	@RequiresPermissions("zhgd:sbCraneBasicinfo:add")
	@Log(title = "塔式起重机设备基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbCraneBasicinfo sbCraneBasicinfo)
	{		
		return toAjax(sbCraneBasicinfoService.insertSbCraneBasicinfo(sbCraneBasicinfo));
	}

	/**
	 * 修改塔式起重机设备基本
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SbCraneBasicinfo sbCraneBasicinfo = sbCraneBasicinfoService.selectSbCraneBasicinfoById(id);
		mmap.put("sbCraneBasicinfo", sbCraneBasicinfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存塔式起重机设备基本
	 */
	@RequiresPermissions("zhgd:sbCraneBasicinfo:edit")
	@Log(title = "塔式起重机设备基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbCraneBasicinfo sbCraneBasicinfo)
	{		
		return toAjax(sbCraneBasicinfoService.updateSbCraneBasicinfo(sbCraneBasicinfo));
	}
	
	/**
	 * 删除塔式起重机设备基本
	 */
	@RequiresPermissions("zhgd:sbCraneBasicinfo:remove")
	@Log(title = "塔式起重机设备基本", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbCraneBasicinfoService.deleteSbCraneBasicinfoByIds(ids));
	}
	
}
