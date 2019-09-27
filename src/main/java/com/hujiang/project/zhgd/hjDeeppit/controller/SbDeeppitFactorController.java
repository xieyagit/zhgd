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
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitFactor;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitFactorService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 深基坑测点 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-02
 */
@Controller
@RequestMapping("/zhgd/sbDeeppitFactor")
public class SbDeeppitFactorController extends BaseController
{
    private String prefix = "zhgd/sbDeeppitFactor";
	
	@Autowired
	private ISbDeeppitFactorService sbDeeppitFactorService;
	
	@RequiresPermissions("zhgd:sbDeeppitFactor:view")
	@GetMapping()
	public String sbDeeppitFactor()
	{
	    return prefix + "/sbDeeppitFactor";
	}
	
	/**
	 * 查询深基坑测点列表
	 */
	@RequiresPermissions("zhgd:sbDeeppitFactor:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbDeeppitFactor sbDeeppitFactor)
	{
		startPage();
        List<SbDeeppitFactor> list = sbDeeppitFactorService.selectSbDeeppitFactorList(sbDeeppitFactor);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出深基坑测点列表
	 */
	@RequiresPermissions("zhgd:sbDeeppitFactor:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbDeeppitFactor sbDeeppitFactor)
    {
    	List<SbDeeppitFactor> list = sbDeeppitFactorService.selectSbDeeppitFactorList(sbDeeppitFactor);
        ExcelUtil<SbDeeppitFactor> util = new ExcelUtil<SbDeeppitFactor>(SbDeeppitFactor.class);
        return util.exportExcel(list, "sbDeeppitFactor");
    }
	
	/**
	 * 新增深基坑测点
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存深基坑测点
	 */
	@RequiresPermissions("zhgd:sbDeeppitFactor:add")
	@Log(title = "深基坑测点", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbDeeppitFactor sbDeeppitFactor)
	{		
		return toAjax(sbDeeppitFactorService.insertSbDeeppitFactor(sbDeeppitFactor));
	}

	/**
	 * 修改深基坑测点
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbDeeppitFactor sbDeeppitFactor = sbDeeppitFactorService.selectSbDeeppitFactorById(id);
		mmap.put("sbDeeppitFactor", sbDeeppitFactor);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存深基坑测点
	 */
	@RequiresPermissions("zhgd:sbDeeppitFactor:edit")
	@Log(title = "深基坑测点", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbDeeppitFactor sbDeeppitFactor)
	{		
		return toAjax(sbDeeppitFactorService.updateSbDeeppitFactor(sbDeeppitFactor));
	}
	
	/**
	 * 删除深基坑测点
	 */
	@RequiresPermissions("zhgd:sbDeeppitFactor:remove")
	@Log(title = "深基坑测点", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbDeeppitFactorService.deleteSbDeeppitFactorByIds(ids));
	}
	
}
