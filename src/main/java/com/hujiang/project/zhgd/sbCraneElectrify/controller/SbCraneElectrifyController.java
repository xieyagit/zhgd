package com.hujiang.project.zhgd.sbCraneElectrify.controller;

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
import com.hujiang.project.zhgd.sbCraneElectrify.domain.SbCraneElectrify;
import com.hujiang.project.zhgd.sbCraneElectrify.service.ISbCraneElectrifyService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 塔机通电时间 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-24
 */
@Controller
@RequestMapping("/zhgd/sbCraneElectrify")
public class SbCraneElectrifyController extends BaseController
{
    private String prefix = "zhgd/sbCraneElectrify";
	
	@Autowired
	private ISbCraneElectrifyService sbCraneElectrifyService;
	
	@RequiresPermissions("zhgd:sbCraneElectrify:view")
	@GetMapping()
	public String sbCraneElectrify()
	{
	    return prefix + "/sbCraneElectrify";
	}
	
	/**
	 * 查询塔机通电时间列表
	 */
	@RequiresPermissions("zhgd:sbCraneElectrify:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbCraneElectrify sbCraneElectrify)
	{
		startPage();
        List<SbCraneElectrify> list = sbCraneElectrifyService.selectSbCraneElectrifyList(sbCraneElectrify);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出塔机通电时间列表
	 */
	@RequiresPermissions("zhgd:sbCraneElectrify:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbCraneElectrify sbCraneElectrify)
    {
    	List<SbCraneElectrify> list = sbCraneElectrifyService.selectSbCraneElectrifyList(sbCraneElectrify);
        ExcelUtil<SbCraneElectrify> util = new ExcelUtil<SbCraneElectrify>(SbCraneElectrify.class);
        return util.exportExcel(list, "sbCraneElectrify");
    }
	
	/**
	 * 新增塔机通电时间
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存塔机通电时间
	 */
	@RequiresPermissions("zhgd:sbCraneElectrify:add")
	@Log(title = "塔机通电时间", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbCraneElectrify sbCraneElectrify)
	{		
		return toAjax(sbCraneElectrifyService.insertSbCraneElectrify(sbCraneElectrify));
	}

	/**
	 * 修改塔机通电时间
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SbCraneElectrify sbCraneElectrify = sbCraneElectrifyService.selectSbCraneElectrifyById(id);
		mmap.put("sbCraneElectrify", sbCraneElectrify);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存塔机通电时间
	 */
	@RequiresPermissions("zhgd:sbCraneElectrify:edit")
	@Log(title = "塔机通电时间", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbCraneElectrify sbCraneElectrify)
	{		
		return toAjax(sbCraneElectrifyService.updateSbCraneElectrify(sbCraneElectrify));
	}
	
	/**
	 * 删除塔机通电时间
	 */
	@RequiresPermissions("zhgd:sbCraneElectrify:remove")
	@Log(title = "塔机通电时间", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbCraneElectrifyService.deleteSbCraneElectrifyByIds(ids));
	}
	
}
