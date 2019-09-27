package com.hujiang.project.zhgd.hjCompanyHierarchy.controller;

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
import com.hujiang.project.zhgd.hjCompanyHierarchy.domain.HjCompanyHierarchy;
import com.hujiang.project.zhgd.hjCompanyHierarchy.service.IHjCompanyHierarchyService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 公司层级 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjCompanyHierarchy")
public class HjCompanyHierarchyController extends BaseController
{
    private String prefix = "zhgd/hjCompanyHierarchy";
	
	@Autowired
	private IHjCompanyHierarchyService hjCompanyHierarchyService;
	
	@RequiresPermissions("zhgd:hjCompanyHierarchy:view")
	@GetMapping()
	public String hjCompanyHierarchy()
	{
	    return prefix + "/hjCompanyHierarchy";
	}


	/**
	 * 查询公司层级列表
	 */
	@RequiresPermissions("zhgd:hjCompanyHierarchy:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjCompanyHierarchy hjCompanyHierarchy)
	{
		startPage();
		List<HjCompanyHierarchy> list = hjCompanyHierarchyService.selectHjCompanyHierarchyList(hjCompanyHierarchy);
		return getDataTable(list);
	}
	
	 /**
	 * 导出公司层级列表
	 */
	@RequiresPermissions("zhgd:hjCompanyHierarchy:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjCompanyHierarchy hjCompanyHierarchy)
    {
    	List<HjCompanyHierarchy> list = hjCompanyHierarchyService.selectHjCompanyHierarchyList(hjCompanyHierarchy);
        ExcelUtil<HjCompanyHierarchy> util = new ExcelUtil<HjCompanyHierarchy>(HjCompanyHierarchy.class);
        return util.exportExcel(list, "hjCompanyHierarchy");
    }
	
	/**
	 * 新增公司层级
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存公司层级
	 */
	@RequiresPermissions("zhgd:hjCompanyHierarchy:add")
	@Log(title = "公司层级", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjCompanyHierarchy hjCompanyHierarchy)
	{		
		return toAjax(hjCompanyHierarchyService.insertHjCompanyHierarchy(hjCompanyHierarchy));
	}

	/**
	 * 修改公司层级
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjCompanyHierarchy hjCompanyHierarchy = hjCompanyHierarchyService.selectHjCompanyHierarchyById(id);
		mmap.put("hjCompanyHierarchy", hjCompanyHierarchy);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存公司层级
	 */
	@RequiresPermissions("zhgd:hjCompanyHierarchy:edit")
	@Log(title = "公司层级", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjCompanyHierarchy hjCompanyHierarchy)
	{		
		return toAjax(hjCompanyHierarchyService.updateHjCompanyHierarchy(hjCompanyHierarchy));
	}
	
	/**
	 * 删除公司层级
	 */
	@RequiresPermissions("zhgd:hjCompanyHierarchy:remove")
	@Log(title = "公司层级", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjCompanyHierarchyService.deleteHjCompanyHierarchyByIds(ids));
	}
	
}
