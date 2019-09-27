package com.hujiang.project.zhgd.sbElevatorBinding.controller;

import java.util.List;

import com.hujiang.project.zhgd.deye.ZCDataService;
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
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
import com.hujiang.project.zhgd.sbElevatorBinding.service.ISbElevatorBindingService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 升降机绑定 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-29
 */
@Controller
@RequestMapping("/zhgd/sbElevatorBinding")
public class SbElevatorBindingController extends BaseController
{
    private String prefix = "zhgd/sbElevatorBinding";

	@Autowired
	private ZCDataService zcDataService;
	@Autowired
	private ISbElevatorBindingService sbElevatorBindingService;
	
	@RequiresPermissions("zhgd:sbElevatorBinding:view")
	@GetMapping()
	public String sbElevatorBinding()
	{
	    return prefix + "/sbElevatorBinding";
	}
	
	/**
	 * 查询升降机绑定列表
	 */
	@RequiresPermissions("zhgd:sbElevatorBinding:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbElevatorBinding sbElevatorBinding)
	{
		startPage();
        List<SbElevatorBinding> list = sbElevatorBindingService.selectSbElevatorBindingList(sbElevatorBinding);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出升降机绑定列表
	 */
	@RequiresPermissions("zhgd:sbElevatorBinding:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbElevatorBinding sbElevatorBinding)
    {
    	List<SbElevatorBinding> list = sbElevatorBindingService.selectSbElevatorBindingList(sbElevatorBinding);
        ExcelUtil<SbElevatorBinding> util = new ExcelUtil<SbElevatorBinding>(SbElevatorBinding.class);
        return util.exportExcel(list, "sbElevatorBinding");
    }
	
	/**
	 * 新增升降机绑定
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存升降机绑定
	 */
	@RequiresPermissions("zhgd:sbElevatorBinding:add")
	@Log(title = "升降机绑定", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbElevatorBinding sbElevatorBinding) throws Exception
	{
		int i=	zcDataService.setBaseElevator(sbElevatorBinding);
		if(i==2){
			sbElevatorBinding.setIsSynchronization("1");
		}else{
			sbElevatorBinding.setIsSynchronization("0");
		}
		sbElevatorBinding.setDeviceNo(Tools.encodeToMD5s(sbElevatorBinding.getHxzid()));
		return toAjax(sbElevatorBindingService.insertSbElevatorBinding(sbElevatorBinding));
	}

	/**
	 * 修改升降机绑定
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbElevatorBinding sbElevatorBinding = sbElevatorBindingService.selectSbElevatorBindingById(id);
		mmap.put("sbElevatorBinding", sbElevatorBinding);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存升降机绑定
	 */
	@RequiresPermissions("zhgd:sbElevatorBinding:edit")
	@Log(title = "升降机绑定", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbElevatorBinding sbElevatorBinding)
	{		
		return toAjax(sbElevatorBindingService.updateSbElevatorBinding(sbElevatorBinding));
	}
	
	/**
	 * 删除升降机绑定
	 */
	@RequiresPermissions("zhgd:sbElevatorBinding:remove")
	@Log(title = "升降机绑定", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbElevatorBindingService.deleteSbElevatorBindingByIds(ids));
	}
	
}
