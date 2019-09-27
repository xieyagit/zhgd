package com.hujiang.project.zhgd.sbUnloaderParameter.controller;

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
import com.hujiang.project.zhgd.sbUnloaderParameter.domain.SbUnloaderParameter;
import com.hujiang.project.zhgd.sbUnloaderParameter.service.ISbUnloaderParameterService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 卸料基础参数 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-10
 */
@Controller
@RequestMapping("/zhgd/sbUnloaderParameter")
public class SbUnloaderParameterController extends BaseController
{
    private String prefix = "zhgd/sbUnloaderParameter";
	
	@Autowired
	private ISbUnloaderParameterService sbUnloaderParameterService;
	
	@RequiresPermissions("zhgd:sbUnloaderParameter:view")
	@GetMapping()
	public String sbUnloaderParameter()
	{
	    return prefix + "/sbUnloaderParameter";
	}
	
	/**
	 * 查询卸料基础参数列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderParameter:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbUnloaderParameter sbUnloaderParameter)
	{
		startPage();
        List<SbUnloaderParameter> list = sbUnloaderParameterService.selectSbUnloaderParameterList(sbUnloaderParameter);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出卸料基础参数列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderParameter:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbUnloaderParameter sbUnloaderParameter)
    {
    	List<SbUnloaderParameter> list = sbUnloaderParameterService.selectSbUnloaderParameterList(sbUnloaderParameter);
        ExcelUtil<SbUnloaderParameter> util = new ExcelUtil<SbUnloaderParameter>(SbUnloaderParameter.class);
        return util.exportExcel(list, "sbUnloaderParameter");
    }
	
	/**
	 * 新增卸料基础参数
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存卸料基础参数
	 */
	@RequiresPermissions("zhgd:sbUnloaderParameter:add")
	@Log(title = "卸料基础参数", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbUnloaderParameter sbUnloaderParameter)
	{		
		return toAjax(sbUnloaderParameterService.insertSbUnloaderParameter(sbUnloaderParameter));
	}

	/**
	 * 修改卸料基础参数
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbUnloaderParameter sbUnloaderParameter = sbUnloaderParameterService.selectSbUnloaderParameterById(id);
		mmap.put("sbUnloaderParameter", sbUnloaderParameter);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存卸料基础参数
	 */
	@RequiresPermissions("zhgd:sbUnloaderParameter:edit")
	@Log(title = "卸料基础参数", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbUnloaderParameter sbUnloaderParameter)
	{		
		return toAjax(sbUnloaderParameterService.updateSbUnloaderParameter(sbUnloaderParameter));
	}
	
	/**
	 * 删除卸料基础参数
	 */
	@RequiresPermissions("zhgd:sbUnloaderParameter:remove")
	@Log(title = "卸料基础参数", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbUnloaderParameterService.deleteSbUnloaderParameterByIds(ids));
	}
	
}
