package com.hujiang.project.zhgd.sbCameraBinding.controller;

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
import com.hujiang.project.zhgd.sbCameraBinding.domain.SbCameraBinding;
import com.hujiang.project.zhgd.sbCameraBinding.service.ISbCameraBindingService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 摄像头设备 信息操作处理
 * 
 * @author hujiang
 * @date 2019-10-15
 */
@Controller
@RequestMapping("/zhgd/sbCameraBinding")
public class SbCameraBindingController extends BaseController
{
    private String prefix = "zhgd/sbCameraBinding";
	
	@Autowired
	private ISbCameraBindingService sbCameraBindingService;
	
	@RequiresPermissions("zhgd:sbCameraBinding:view")
	@GetMapping()
	public String sbCameraBinding()
	{
	    return prefix + "/sbCameraBinding";
	}
	
	/**
	 * 查询摄像头设备列表
	 */
	@RequiresPermissions("zhgd:sbCameraBinding:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbCameraBinding sbCameraBinding)
	{
		startPage();
        List<SbCameraBinding> list = sbCameraBindingService.selectSbCameraBindingList(sbCameraBinding);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出摄像头设备列表
	 */
	@RequiresPermissions("zhgd:sbCameraBinding:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbCameraBinding sbCameraBinding)
    {
    	List<SbCameraBinding> list = sbCameraBindingService.selectSbCameraBindingList(sbCameraBinding);
        ExcelUtil<SbCameraBinding> util = new ExcelUtil<SbCameraBinding>(SbCameraBinding.class);
        return util.exportExcel(list, "sbCameraBinding");
    }
	
	/**
	 * 新增摄像头设备
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存摄像头设备
	 */
	@RequiresPermissions("zhgd:sbCameraBinding:add")
	@Log(title = "摄像头设备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbCameraBinding sbCameraBinding)
	{		
		return toAjax(sbCameraBindingService.insertSbCameraBinding(sbCameraBinding));
	}

	/**
	 * 修改摄像头设备
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbCameraBinding sbCameraBinding = sbCameraBindingService.selectSbCameraBindingById(id);
		mmap.put("sbCameraBinding", sbCameraBinding);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存摄像头设备
	 */
	@RequiresPermissions("zhgd:sbCameraBinding:edit")
	@Log(title = "摄像头设备", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbCameraBinding sbCameraBinding)
	{		
		return toAjax(sbCameraBindingService.updateSbCameraBinding(sbCameraBinding));
	}
	
	/**
	 * 删除摄像头设备
	 */
	@RequiresPermissions("zhgd:sbCameraBinding:remove")
	@Log(title = "摄像头设备", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbCameraBindingService.deleteSbCameraBindingByIds(ids));
	}
	
}
