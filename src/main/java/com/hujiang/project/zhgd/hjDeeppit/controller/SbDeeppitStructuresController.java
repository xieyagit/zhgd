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
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitStructures;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitStructuresService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 深基坑结构物 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-02
 */
@Controller
@RequestMapping("/zhgd/sbDeeppitStructures")
public class SbDeeppitStructuresController extends BaseController
{
    private String prefix = "zhgd/sbDeeppitStructures";
	
	@Autowired
	private ISbDeeppitStructuresService sbDeeppitStructuresService;
	
	@RequiresPermissions("zhgd:sbDeeppitStructures:view")
	@GetMapping()
	public String sbDeeppitStructures()
	{
	    return prefix + "/sbDeeppitStructures";
	}
	
	/**
	 * 查询深基坑结构物列表
	 */
	@RequiresPermissions("zhgd:sbDeeppitStructures:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbDeeppitStructures sbDeeppitStructures)
	{
		startPage();
        List<SbDeeppitStructures> list = sbDeeppitStructuresService.selectSbDeeppitStructuresList(sbDeeppitStructures);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出深基坑结构物列表
	 */
	@RequiresPermissions("zhgd:sbDeeppitStructures:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbDeeppitStructures sbDeeppitStructures)
    {
    	List<SbDeeppitStructures> list = sbDeeppitStructuresService.selectSbDeeppitStructuresList(sbDeeppitStructures);
        ExcelUtil<SbDeeppitStructures> util = new ExcelUtil<SbDeeppitStructures>(SbDeeppitStructures.class);
        return util.exportExcel(list, "sbDeeppitStructures");
    }
	
	/**
	 * 新增深基坑结构物
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存深基坑结构物
	 */
	@RequiresPermissions("zhgd:sbDeeppitStructures:add")
	@Log(title = "深基坑结构物", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbDeeppitStructures sbDeeppitStructures)
	{		
		return toAjax(sbDeeppitStructuresService.insertSbDeeppitStructures(sbDeeppitStructures));
	}

	/**
	 * 修改深基坑结构物
	 */
	@GetMapping("/edit/{masterKey}")
	public String edit(@PathVariable("masterKey") String masterKey, ModelMap mmap)
	{
		SbDeeppitStructures sbDeeppitStructures = sbDeeppitStructuresService.selectSbDeeppitStructuresByMasterKey(masterKey);
		mmap.put("sbDeeppitStructures", sbDeeppitStructures);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存深基坑结构物
	 */
	@RequiresPermissions("zhgd:sbDeeppitStructures:edit")
	@Log(title = "深基坑结构物", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbDeeppitStructures sbDeeppitStructures)
	{		
		return toAjax(sbDeeppitStructuresService.updateSbDeeppitStructures(sbDeeppitStructures));
	}
	
	/**
	 * 删除深基坑结构物
	 */
	@RequiresPermissions("zhgd:sbDeeppitStructures:remove")
	@Log(title = "深基坑结构物", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbDeeppitStructuresService.deleteSbDeeppitStructuresByIds(ids));
	}
	
}
