package com.hujiang.project.zhgd.sbVersion.controller;

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
import com.hujiang.project.zhgd.sbVersion.domain.SbVersion;
import com.hujiang.project.zhgd.sbVersion.service.ISbVersionService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 版本 信息操作处理
 * 
 * @author hujiang
 * @date 2019-08-26
 */
@Controller
@RequestMapping("/zhgd/sbVersion")
public class SbVersionController extends BaseController
{
    private String prefix = "zhgd/sbVersion";
	
	@Autowired
	private ISbVersionService sbVersionService;
	
	@RequiresPermissions("zhgd:sbVersion:view")
	@GetMapping()
	public String sbVersion()
	{
	    return prefix + "/sbVersion";
	}
	
	/**
	 * 查询版本列表
	 */
	@RequiresPermissions("zhgd:sbVersion:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbVersion sbVersion)
	{
		startPage();
        List<SbVersion> list = sbVersionService.selectSbVersionList(sbVersion);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出版本列表
	 */
	@RequiresPermissions("zhgd:sbVersion:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbVersion sbVersion)
    {
    	List<SbVersion> list = sbVersionService.selectSbVersionList(sbVersion);
        ExcelUtil<SbVersion> util = new ExcelUtil<SbVersion>(SbVersion.class);
        return util.exportExcel(list, "sbVersion");
    }
	
	/**
	 * 新增版本
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存版本
	 */
	@RequiresPermissions("zhgd:sbVersion:add")
	@Log(title = "版本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbVersion sbVersion)
	{		
		return toAjax(sbVersionService.insertSbVersion(sbVersion));
	}

	/**
	 * 修改版本
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbVersion sbVersion = sbVersionService.selectSbVersionById(id);
		mmap.put("sbVersion", sbVersion);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存版本
	 */
	@RequiresPermissions("zhgd:sbVersion:edit")
	@Log(title = "版本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbVersion sbVersion)
	{		
		return toAjax(sbVersionService.updateSbVersion(sbVersion));
	}
	
	/**
	 * 删除版本
	 */
	@RequiresPermissions("zhgd:sbVersion:remove")
	@Log(title = "版本", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbVersionService.deleteSbVersionByIds(ids));
	}
	
}
