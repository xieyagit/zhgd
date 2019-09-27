package com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.domain.HjProjectPersonnelSynchronization;
import com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.service.IHjProjectPersonnelSynchronizationService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 项目人员同步 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/zhgd/hjProjectPersonnelSynchronization")
public class HjProjectPersonnelSynchronizationController extends BaseController
{
    private String prefix = "zhgd/hjProjectPersonnelSynchronization";
	
	@Autowired
	private IHjProjectPersonnelSynchronizationService hjProjectPersonnelSynchronizationService;
	
	@RequiresPermissions("zhgd:hjProjectPersonnelSynchronization:view")
	@GetMapping()
	public String hjProjectPersonnelSynchronization()
	{
	    return prefix + "/hjProjectPersonnelSynchronization";
	}
	
	/**
	 * 查询项目人员同步列表
	 */
	@RequiresPermissions("zhgd:hjProjectPersonnelSynchronization:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization)
	{
		startPage();
        List<HjProjectPersonnelSynchronization> list = hjProjectPersonnelSynchronizationService.selectHjProjectPersonnelSynchronizationList(hjProjectPersonnelSynchronization);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出项目人员同步列表
	 */
	@RequiresPermissions("zhgd:hjProjectPersonnelSynchronization:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization)
    {
    	List<HjProjectPersonnelSynchronization> list = hjProjectPersonnelSynchronizationService.selectHjProjectPersonnelSynchronizationList(hjProjectPersonnelSynchronization);
        ExcelUtil<HjProjectPersonnelSynchronization> util = new ExcelUtil<HjProjectPersonnelSynchronization>(HjProjectPersonnelSynchronization.class);
        return util.exportExcel(list, "hjProjectPersonnelSynchronization");
    }
	
	/**
	 * 新增项目人员同步
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目人员同步
	 */
	@RequiresPermissions("zhgd:hjProjectPersonnelSynchronization:add")
	@Log(title = "项目人员同步", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization)
	{		
		return toAjax(hjProjectPersonnelSynchronizationService.insertHjProjectPersonnelSynchronization(hjProjectPersonnelSynchronization));
	}

	/**
	 * 修改项目人员同步
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization = hjProjectPersonnelSynchronizationService.selectHjProjectPersonnelSynchronizationById(id);
		mmap.put("hjProjectPersonnelSynchronization", hjProjectPersonnelSynchronization);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目人员同步
	 */
	@RequiresPermissions("zhgd:hjProjectPersonnelSynchronization:edit")
	@Log(title = "项目人员同步", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjProjectPersonnelSynchronization hjProjectPersonnelSynchronization)
	{		
		return toAjax(hjProjectPersonnelSynchronizationService.updateHjProjectPersonnelSynchronization(hjProjectPersonnelSynchronization));
	}
	
	/**
	 * 删除项目人员同步
	 */
	@RequiresPermissions("zhgd:hjProjectPersonnelSynchronization:remove")
	@Log(title = "项目人员同步", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjProjectPersonnelSynchronizationService.deleteHjProjectPersonnelSynchronizationByIds(ids));
	}
	
}
