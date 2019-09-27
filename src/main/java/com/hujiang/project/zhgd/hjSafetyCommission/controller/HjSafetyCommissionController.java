package com.hujiang.project.zhgd.hjSafetyCommission.controller;

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
import com.hujiang.project.zhgd.hjSafetyCommission.domain.HjSafetyCommission;
import com.hujiang.project.zhgd.hjSafetyCommission.service.IHjSafetyCommissionService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 巡检通知页面数据 信息操作处理
 * 
 * @author hujiang
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/zhgd/hjSafetyCommission")
public class HjSafetyCommissionController extends BaseController
{
    private String prefix = "zhgd/hjSafetyCommission";
	
	@Autowired
	private IHjSafetyCommissionService hjSafetyCommissionService;
	
	@RequiresPermissions("zhgd:hjSafetyCommission:view")
	@GetMapping()
	public String hjSafetyCommission()
	{
	    return prefix + "/hjSafetyCommission";
	}
	
	/**
	 * 查询巡检通知页面数据列表
	 */
	@RequiresPermissions("zhgd:hjSafetyCommission:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjSafetyCommission hjSafetyCommission)
	{
		startPage();
        List<HjSafetyCommission> list = hjSafetyCommissionService.selectHjSafetyCommissionList(hjSafetyCommission);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检通知页面数据列表
	 */
	@RequiresPermissions("zhgd:hjSafetyCommission:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjSafetyCommission hjSafetyCommission)
    {
    	List<HjSafetyCommission> list = hjSafetyCommissionService.selectHjSafetyCommissionList(hjSafetyCommission);
        ExcelUtil<HjSafetyCommission> util = new ExcelUtil<HjSafetyCommission>(HjSafetyCommission.class);
        return util.exportExcel(list, "hjSafetyCommission");
    }
	
	/**
	 * 新增巡检通知页面数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检通知页面数据
	 */
	@RequiresPermissions("zhgd:hjSafetyCommission:add")
	@Log(title = "巡检通知页面数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjSafetyCommission hjSafetyCommission)
	{		
		return toAjax(hjSafetyCommissionService.insertHjSafetyCommission(hjSafetyCommission));
	}

	/**
	 * 修改巡检通知页面数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjSafetyCommission hjSafetyCommission = hjSafetyCommissionService.selectHjSafetyCommissionById(id);
		mmap.put("hjSafetyCommission", hjSafetyCommission);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存巡检通知页面数据
	 */
	@RequiresPermissions("zhgd:hjSafetyCommission:edit")
	@Log(title = "巡检通知页面数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjSafetyCommission hjSafetyCommission)
	{		
		return toAjax(hjSafetyCommissionService.updateHjSafetyCommission(hjSafetyCommission));
	}
	
	/**
	 * 删除巡检通知页面数据
	 */
	@RequiresPermissions("zhgd:hjSafetyCommission:remove")
	@Log(title = "巡检通知页面数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjSafetyCommissionService.deleteHjSafetyCommissionByIds(ids));
	}
	
}
