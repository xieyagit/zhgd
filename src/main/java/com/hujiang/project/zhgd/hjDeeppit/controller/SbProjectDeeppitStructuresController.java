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
import com.hujiang.project.zhgd.hjDeeppit.domain.SbProjectDeeppitStructures;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbProjectDeeppitStructuresService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 深基坑结构物-项目 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-02
 */
@Controller
@RequestMapping("/zhgd/sbProjectDeeppitStructures")
public class SbProjectDeeppitStructuresController extends BaseController
{
    private String prefix = "zhgd/sbProjectDeeppitStructures";
	
	@Autowired
	private ISbProjectDeeppitStructuresService sbProjectDeeppitStructuresService;
	
	@RequiresPermissions("zhgd:sbProjectDeeppitStructures:view")
	@GetMapping()
	public String sbProjectDeeppitStructures()
	{
	    return prefix + "/sbProjectDeeppitStructures";
	}
	
	/**
	 * 查询深基坑结构物-项目列表
	 */
	@RequiresPermissions("zhgd:sbProjectDeeppitStructures:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbProjectDeeppitStructures sbProjectDeeppitStructures)
	{
		startPage();
        List<SbProjectDeeppitStructures> list = sbProjectDeeppitStructuresService.selectSbProjectDeeppitStructuresList(sbProjectDeeppitStructures);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出深基坑结构物-项目列表
	 */
	@RequiresPermissions("zhgd:sbProjectDeeppitStructures:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbProjectDeeppitStructures sbProjectDeeppitStructures)
    {
    	List<SbProjectDeeppitStructures> list = sbProjectDeeppitStructuresService.selectSbProjectDeeppitStructuresList(sbProjectDeeppitStructures);
        ExcelUtil<SbProjectDeeppitStructures> util = new ExcelUtil<SbProjectDeeppitStructures>(SbProjectDeeppitStructures.class);
        return util.exportExcel(list, "sbProjectDeeppitStructures");
    }
	
	/**
	 * 新增深基坑结构物-项目
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存深基坑结构物-项目
	 */
	@RequiresPermissions("zhgd:sbProjectDeeppitStructures:add")
	@Log(title = "深基坑结构物-项目", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbProjectDeeppitStructures sbProjectDeeppitStructures)
	{		
		return toAjax(sbProjectDeeppitStructuresService.insertSbProjectDeeppitStructures(sbProjectDeeppitStructures));
	}

	/**
	 * 修改深基坑结构物-项目
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbProjectDeeppitStructures sbProjectDeeppitStructures = sbProjectDeeppitStructuresService.selectSbProjectDeeppitStructuresById(id);
		mmap.put("sbProjectDeeppitStructures", sbProjectDeeppitStructures);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存深基坑结构物-项目
	 */
	@RequiresPermissions("zhgd:sbProjectDeeppitStructures:edit")
	@Log(title = "深基坑结构物-项目", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbProjectDeeppitStructures sbProjectDeeppitStructures)
	{		
		return toAjax(sbProjectDeeppitStructuresService.updateSbProjectDeeppitStructures(sbProjectDeeppitStructures));
	}
	
	/**
	 * 删除深基坑结构物-项目
	 */
	@RequiresPermissions("zhgd:sbProjectDeeppitStructures:remove")
	@Log(title = "深基坑结构物-项目", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbProjectDeeppitStructuresService.deleteSbProjectDeeppitStructuresByIds(ids));
	}
	
}
