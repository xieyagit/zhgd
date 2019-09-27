package com.hujiang.project.zhgd.zhNode.controller;

import java.util.List;

import com.hujiang.project.zhgd.zhNode.domain.ZhProgressPlan;
import com.hujiang.project.zhgd.zhNode.service.IZhProgressPlanService;
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
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 进度计划 信息操作处理
 * 
 * @author hujiang
 * @date 2019-08-02
 */
@Controller
@RequestMapping("/zhgd/zhProgressPlan")
public class ZhProgressPlanController extends BaseController
{
    private String prefix = "zhgd/zhProgressPlan";
	
	@Autowired
	private IZhProgressPlanService zhProgressPlanService;
	
	@RequiresPermissions("zhgd:zhProgressPlan:view")
	@GetMapping()
	public String zhProgressPlan()
	{
	    return prefix + "/zhProgressPlan";
	}
	
	/**
	 * 查询进度计划列表
	 */
	@RequiresPermissions("zhgd:zhProgressPlan:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ZhProgressPlan zhProgressPlan)
	{
		startPage();
        List<ZhProgressPlan> list = zhProgressPlanService.selectZhProgressPlanList(zhProgressPlan);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出进度计划列表
	 */
	@RequiresPermissions("zhgd:zhProgressPlan:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ZhProgressPlan zhProgressPlan)
    {
    	List<ZhProgressPlan> list = zhProgressPlanService.selectZhProgressPlanList(zhProgressPlan);
        ExcelUtil<ZhProgressPlan> util = new ExcelUtil<ZhProgressPlan>(ZhProgressPlan.class);
        return util.exportExcel(list, "zhProgressPlan");
    }
	
	/**
	 * 新增进度计划
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存进度计划
	 */
	@RequiresPermissions("zhgd:zhProgressPlan:add")
	@Log(title = "进度计划", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ZhProgressPlan zhProgressPlan)
	{		
		return toAjax(zhProgressPlanService.insertZhProgressPlan(zhProgressPlan));
	}

	/**
	 * 修改进度计划
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ZhProgressPlan zhProgressPlan = zhProgressPlanService.selectZhProgressPlanById(id);
		mmap.put("zhProgressPlan", zhProgressPlan);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存进度计划
	 */
	@RequiresPermissions("zhgd:zhProgressPlan:edit")
	@Log(title = "进度计划", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ZhProgressPlan zhProgressPlan)
	{		
		return toAjax(zhProgressPlanService.updateZhProgressPlan(zhProgressPlan));
	}
	
	/**
	 * 删除进度计划
	 */
	@RequiresPermissions("zhgd:zhProgressPlan:remove")
	@Log(title = "进度计划", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(zhProgressPlanService.deleteZhProgressPlanByIds(ids));
	}



}
