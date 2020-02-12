package com.hujiang.project.zhgd.sbGroupTitle.controller;

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
import com.hujiang.project.zhgd.sbGroupTitle.domain.SbGroupTitle;
import com.hujiang.project.zhgd.sbGroupTitle.service.ISbGroupTitleService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 项目标题 信息操作处理
 * 
 * @author hujiang
 * @date 2020-01-03
 */
@Controller
@RequestMapping("/zhgd/sbGroupTitle")
public class SbGroupTitleController extends BaseController
{
    private String prefix = "zhgd/sbGroupTitle";
	
	@Autowired
	private ISbGroupTitleService sbGroupTitleService;
	
	@RequiresPermissions("zhgd:sbGroupTitle:view")
	@GetMapping()
	public String sbGroupTitle()
	{
	    return prefix + "/sbGroupTitle";
	}
	
	/**
	 * 查询项目标题列表
	 */
	@RequiresPermissions("zhgd:sbGroupTitle:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbGroupTitle sbGroupTitle)
	{
		startPage();
        List<SbGroupTitle> list = sbGroupTitleService.selectSbGroupTitleList(sbGroupTitle);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出项目标题列表
	 */
	@RequiresPermissions("zhgd:sbGroupTitle:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbGroupTitle sbGroupTitle)
    {
    	List<SbGroupTitle> list = sbGroupTitleService.selectSbGroupTitleList(sbGroupTitle);
        ExcelUtil<SbGroupTitle> util = new ExcelUtil<SbGroupTitle>(SbGroupTitle.class);
        return util.exportExcel(list, "sbGroupTitle");
    }
	
	/**
	 * 新增项目标题
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目标题
	 */
	@RequiresPermissions("zhgd:sbGroupTitle:add")
	@Log(title = "项目标题", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbGroupTitle sbGroupTitle)
	{		
		return toAjax(sbGroupTitleService.insertSbGroupTitle(sbGroupTitle));
	}

	/**
	 * 修改项目标题
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbGroupTitle sbGroupTitle = sbGroupTitleService.selectSbGroupTitleById(id);
		mmap.put("sbGroupTitle", sbGroupTitle);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目标题
	 */
	@RequiresPermissions("zhgd:sbGroupTitle:edit")
	@Log(title = "项目标题", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbGroupTitle sbGroupTitle)
	{		
		return toAjax(sbGroupTitleService.updateSbGroupTitle(sbGroupTitle));
	}
	
	/**
	 * 删除项目标题
	 */
	@RequiresPermissions("zhgd:sbGroupTitle:remove")
	@Log(title = "项目标题", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbGroupTitleService.deleteSbGroupTitleByIds(ids));
	}
	
}
