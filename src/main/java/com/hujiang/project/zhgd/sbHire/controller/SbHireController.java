package com.hujiang.project.zhgd.sbHire.controller;

import org.springframework.stereotype.Controller;
import java.util.List;
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
import com.hujiang.project.zhgd.sbHire.domain.SbHire;
import com.hujiang.project.zhgd.sbHire.service.ISbHireService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 设备人员 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-04
 */
@Controller
@RequestMapping("/zhgd/sbHire")
public class SbHireController extends BaseController
{
    private String prefix = "zhgd/sbHire";
	
	@Autowired
	private ISbHireService sbHireService;
	
	@RequiresPermissions("zhgd:sbHire:view")
	@GetMapping()
	public String sbHire()
	{
	    return prefix + "/sbHire";
	}
	
	/**
	 * 查询设备人员列表
	 */
	@RequiresPermissions("zhgd:sbHire:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbHire sbHire)
	{
		startPage();
        List<SbHire> list = sbHireService.selectSbHireList(sbHire);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出设备人员列表
	 */
	@RequiresPermissions("zhgd:sbHire:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbHire sbHire)
    {
    	List<SbHire> list = sbHireService.selectSbHireList(sbHire);
        ExcelUtil<SbHire> util = new ExcelUtil<SbHire>(SbHire.class);
        return util.exportExcel(list, "sbHire");
    }
	
	/**
	 * 新增设备人员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备人员
	 */
	@RequiresPermissions("zhgd:sbHire:add")
	@Log(title = "设备人员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbHire sbHire)
	{		
		return toAjax(sbHireService.insertSbHire(sbHire));
	}

	/**
	 * 修改设备人员
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbHire sbHire = sbHireService.selectSbHireById(id);
		mmap.put("sbHire", sbHire);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备人员
	 */
	@RequiresPermissions("zhgd:sbHire:edit")
	@Log(title = "设备人员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbHire sbHire)
	{		
		return toAjax(sbHireService.updateSbHire(sbHire));
	}
	
	/**
	 * 删除设备人员
	 */
	@RequiresPermissions("zhgd:sbHire:remove")
	@Log(title = "设备人员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbHireService.deleteSbHireByIds(ids));
	}
	
}
