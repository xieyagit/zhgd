package com.hujiang.project.zhgd.sbUnloaderBinding.controller;

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
import com.hujiang.project.zhgd.sbUnloaderBinding.domain.SbUnloaderBinding;
import com.hujiang.project.zhgd.sbUnloaderBinding.service.ISbUnloaderBindingService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 卸料设备绑定 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-15
 */
@Controller
@RequestMapping("/zhgd/sbUnloaderBinding")
public class SbUnloaderBindingController extends BaseController
{
    private String prefix = "zhgd/sbUnloaderBinding";
	
	@Autowired
	private ISbUnloaderBindingService sbUnloaderBindingService;
	
	@RequiresPermissions("zhgd:sbUnloaderBinding:view")
	@GetMapping()
	public String sbUnloaderBinding()
	{
	    return prefix + "/sbUnloaderBinding";
	}
	
	/**
	 * 查询卸料设备绑定列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderBinding:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbUnloaderBinding sbUnloaderBinding)
	{
		startPage();
        List<SbUnloaderBinding> list = sbUnloaderBindingService.selectSbUnloaderBindingList(sbUnloaderBinding);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出卸料设备绑定列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderBinding:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbUnloaderBinding sbUnloaderBinding)
    {
    	List<SbUnloaderBinding> list = sbUnloaderBindingService.selectSbUnloaderBindingList(sbUnloaderBinding);
        ExcelUtil<SbUnloaderBinding> util = new ExcelUtil<SbUnloaderBinding>(SbUnloaderBinding.class);
        return util.exportExcel(list, "sbUnloaderBinding");
    }
	
	/**
	 * 新增卸料设备绑定
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存卸料设备绑定
	 */
	@RequiresPermissions("zhgd:sbUnloaderBinding:add")
	@Log(title = "卸料设备绑定", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbUnloaderBinding sbUnloaderBinding)
	{		
		return toAjax(sbUnloaderBindingService.insertSbUnloaderBinding(sbUnloaderBinding));
	}

	/**
	 * 修改卸料设备绑定
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbUnloaderBinding sbUnloaderBinding = sbUnloaderBindingService.selectSbUnloaderBindingById(id);
		mmap.put("sbUnloaderBinding", sbUnloaderBinding);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存卸料设备绑定
	 */
	@RequiresPermissions("zhgd:sbUnloaderBinding:edit")
	@Log(title = "卸料设备绑定", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbUnloaderBinding sbUnloaderBinding)
	{		
		return toAjax(sbUnloaderBindingService.updateSbUnloaderBinding(sbUnloaderBinding));
	}
	
	/**
	 * 删除卸料设备绑定
	 */
	@RequiresPermissions("zhgd:sbUnloaderBinding:remove")
	@Log(title = "卸料设备绑定", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbUnloaderBindingService.deleteSbUnloaderBindingByIds(ids));
	}
	
}
