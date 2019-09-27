package com.hujiang.project.zhgd.sbCraneBinding.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.hujiang.project.zhgd.deye.ZCDataService;
import com.hujiang.project.zhgd.utils.Tools;
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
import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import com.hujiang.project.zhgd.sbCraneBinding.service.ISbCraneBindingService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 塔吊设备绑定 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-24
 */
@Controller
@RequestMapping("/zhgd/sbCraneBinding")
public class SbCraneBindingController extends BaseController
{
    private String prefix = "zhgd/sbCraneBinding";

    @Autowired
	private ZCDataService zcDataService;

	@Autowired
	private ISbCraneBindingService sbCraneBindingService;
	
	@RequiresPermissions("zhgd:sbCraneBinding:view")
	@GetMapping()
	public String sbCraneBinding()
	{
	    return prefix + "/sbCraneBinding";
	}
	
	/**
	 * 查询塔吊设备绑定列表
	 */
	@RequiresPermissions("zhgd:sbCraneBinding:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbCraneBinding sbCraneBinding)
	{
		startPage();
        List<SbCraneBinding> list = sbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出塔吊设备绑定列表
	 */
	@RequiresPermissions("zhgd:sbCraneBinding:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbCraneBinding sbCraneBinding)
    {
    	List<SbCraneBinding> list = sbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
        ExcelUtil<SbCraneBinding> util = new ExcelUtil<SbCraneBinding>(SbCraneBinding.class);
        return util.exportExcel(list, "sbCraneBinding");
    }
	
	/**
	 * 新增塔吊设备绑定
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存塔吊设备绑定
	 */
	@RequiresPermissions("zhgd:sbCraneBinding:add")
	@Log(title = "塔吊设备绑定", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave (SbCraneBinding sbCraneBinding)throws IOException, URISyntaxException
	{
		int i=	zcDataService.setBaseCrane(sbCraneBinding);
		if(i==2){
			sbCraneBinding.setIsSynchronization("1");
		}else{
			sbCraneBinding.setIsSynchronization("0");
		}
		sbCraneBinding.setDeviceNo(Tools.encodeToMD5s(sbCraneBinding.getHxzid()));
		return toAjax(sbCraneBindingService.insertSbCraneBinding(sbCraneBinding));
	}

	/**
	 * 修改塔吊设备绑定
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbCraneBinding sbCraneBinding = sbCraneBindingService.selectSbCraneBindingById(id);
		mmap.put("sbCraneBinding", sbCraneBinding);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存塔吊设备绑定
	 */
	@RequiresPermissions("zhgd:sbCraneBinding:edit")
	@Log(title = "塔吊设备绑定", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbCraneBinding sbCraneBinding)
	{		
		return toAjax(sbCraneBindingService.updateSbCraneBinding(sbCraneBinding));
	}
	
	/**
	 * 删除塔吊设备绑定
	 */
	@RequiresPermissions("zhgd:sbCraneBinding:remove")
	@Log(title = "塔吊设备绑定", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbCraneBindingService.deleteSbCraneBindingByIds(ids));
	}
	
}
