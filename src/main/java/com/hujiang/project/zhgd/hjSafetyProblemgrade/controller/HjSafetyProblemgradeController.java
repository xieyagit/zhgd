package com.hujiang.project.zhgd.hjSafetyProblemgrade.controller;

import org.springframework.stereotype.Controller;
import java.util.List;
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
import com.hujiang.project.zhgd.hjSafetyProblemgrade.domain.HjSafetyProblemgrade;
import com.hujiang.project.zhgd.hjSafetyProblemgrade.service.IHjSafetyProblemgradeService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 问题级别 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-10
 */
@Controller
@RequestMapping("/zhgd/hjSafetyProblemgrade")
public class HjSafetyProblemgradeController extends BaseController
{
    private String prefix = "zhgd/hjSafetyProblemgrade";
	
	@Autowired
	private IHjSafetyProblemgradeService hjSafetyProblemgradeService;
	
	@RequiresPermissions("zhgd:hjSafetyProblemgrade:view")
	@GetMapping()
	public String hjSafetyProblemgrade()
	{
	    return prefix + "/hjSafetyProblemgrade";
	}
	
	/**
	 * 查询问题级别列表
	 */
	@RequiresPermissions("zhgd:hjSafetyProblemgrade:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjSafetyProblemgrade hjSafetyProblemgrade)
	{
		startPage();
        List<HjSafetyProblemgrade> list = hjSafetyProblemgradeService.selectHjSafetyProblemgradeList(hjSafetyProblemgrade);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出问题级别列表
	 */
	@RequiresPermissions("zhgd:hjSafetyProblemgrade:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjSafetyProblemgrade hjSafetyProblemgrade)
    {
    	List<HjSafetyProblemgrade> list = hjSafetyProblemgradeService.selectHjSafetyProblemgradeList(hjSafetyProblemgrade);
        ExcelUtil<HjSafetyProblemgrade> util = new ExcelUtil<HjSafetyProblemgrade>(HjSafetyProblemgrade.class);
        return util.exportExcel(list, "hjSafetyProblemgrade");
    }
	
	/**
	 * 新增问题级别
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存问题级别
	 */
	@RequiresPermissions("zhgd:hjSafetyProblemgrade:add")
	@Log(title = "问题级别", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjSafetyProblemgrade hjSafetyProblemgrade)
	{		
		return toAjax(hjSafetyProblemgradeService.insertHjSafetyProblemgrade(hjSafetyProblemgrade));
	}

	/**
	 * 修改问题级别
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjSafetyProblemgrade hjSafetyProblemgrade = hjSafetyProblemgradeService.selectHjSafetyProblemgradeById(id);
		mmap.put("hjSafetyProblemgrade", hjSafetyProblemgrade);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存问题级别
	 */
	@RequiresPermissions("zhgd:hjSafetyProblemgrade:edit")
	@Log(title = "问题级别", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjSafetyProblemgrade hjSafetyProblemgrade)
	{		
		return toAjax(hjSafetyProblemgradeService.updateHjSafetyProblemgrade(hjSafetyProblemgrade));
	}
	
	/**
	 * 删除问题级别
	 */
	@RequiresPermissions("zhgd:hjSafetyProblemgrade:remove")
	@Log(title = "问题级别", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjSafetyProblemgradeService.deleteHjSafetyProblemgradeByIds(ids));
	}
	
}
