package com.hujiang.project.zhgd.sbCraneWarning.controller;

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
import com.hujiang.project.zhgd.sbCraneWarning.domain.SbCraneWarning;
import com.hujiang.project.zhgd.sbCraneWarning.service.ISbCraneWarningService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 塔机预警数据 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-21
 */
@Controller
@RequestMapping("/zhgd/sbCraneWarning")
public class SbCraneWarningController extends BaseController
{
    private String prefix = "zhgd/sbCraneWarning";
	
	@Autowired
	private ISbCraneWarningService sbCraneWarningService;
	
	@RequiresPermissions("zhgd:sbCraneWarning:view")
	@GetMapping()
	public String sbCraneWarning()
	{
	    return prefix + "/sbCraneWarning";
	}
	
	/**
	 * 查询塔机预警数据列表
	 */
	@RequiresPermissions("zhgd:sbCraneWarning:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbCraneWarning sbCraneWarning)
	{
		startPage();
        List<SbCraneWarning> list = sbCraneWarningService.selectSbCraneWarningList(sbCraneWarning);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出塔机预警数据列表
	 */
	@RequiresPermissions("zhgd:sbCraneWarning:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbCraneWarning sbCraneWarning)
    {
    	List<SbCraneWarning> list = sbCraneWarningService.selectSbCraneWarningList(sbCraneWarning);
        ExcelUtil<SbCraneWarning> util = new ExcelUtil<SbCraneWarning>(SbCraneWarning.class);
        return util.exportExcel(list, "sbCraneWarning");
    }
	
	/**
	 * 新增塔机预警数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存塔机预警数据
	 */
	@RequiresPermissions("zhgd:sbCraneWarning:add")
	@Log(title = "塔机预警数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbCraneWarning sbCraneWarning)
	{		
		return toAjax(sbCraneWarningService.insertSbCraneWarning(sbCraneWarning));
	}

	/**
	 * 修改塔机预警数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SbCraneWarning sbCraneWarning = sbCraneWarningService.selectSbCraneWarningById(id);
		mmap.put("sbCraneWarning", sbCraneWarning);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存塔机预警数据
	 */
	@RequiresPermissions("zhgd:sbCraneWarning:edit")
	@Log(title = "塔机预警数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbCraneWarning sbCraneWarning)
	{		
		return toAjax(sbCraneWarningService.updateSbCraneWarning(sbCraneWarning));
	}
	
	/**
	 * 删除塔机预警数据
	 */
	@RequiresPermissions("zhgd:sbCraneWarning:remove")
	@Log(title = "塔机预警数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbCraneWarningService.deleteSbCraneWarningByIds(ids));
	}
	
}
