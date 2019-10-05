package com.hujiang.project.zhgd.sbProjectElectricityBox.controller;

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
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import com.hujiang.project.zhgd.sbProjectElectricityBox.service.ISbProjectElectricityBoxService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 项目电箱 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-24
 */
@Controller
@RequestMapping("/zhgd/sbProjectElectricityBox")
public class SbProjectElectricityBoxController extends BaseController
{
    private String prefix = "zhgd/sbProjectElectricityBox";
	
	@Autowired
	private ISbProjectElectricityBoxService sbProjectElectricityBoxService;
	
	@RequiresPermissions("zhgd:sbProjectElectricityBox:view")
	@GetMapping()
	public String sbProjectElectricityBox()
	{
	    return prefix + "/sbProjectElectricityBox";
	}
	
	/**
	 * 查询项目电箱列表
	 */
	@RequiresPermissions("zhgd:sbProjectElectricityBox:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbProjectElectricityBox sbProjectElectricityBox)
	{
		startPage();
        List<SbProjectElectricityBox> list = sbProjectElectricityBoxService.selectSbProjectElectricityBoxList(sbProjectElectricityBox);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出项目电箱列表
	 */
	@RequiresPermissions("zhgd:sbProjectElectricityBox:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbProjectElectricityBox sbProjectElectricityBox)
    {
    	List<SbProjectElectricityBox> list = sbProjectElectricityBoxService.selectSbProjectElectricityBoxList(sbProjectElectricityBox);
        ExcelUtil<SbProjectElectricityBox> util = new ExcelUtil<SbProjectElectricityBox>(SbProjectElectricityBox.class);
        return util.exportExcel(list, "sbProjectElectricityBox");
    }
	
	/**
	 * 新增项目电箱
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目电箱
	 */
	@RequiresPermissions("zhgd:sbProjectElectricityBox:add")
	@Log(title = "项目电箱", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbProjectElectricityBox sbProjectElectricityBox)
	{		
		return toAjax(sbProjectElectricityBoxService.insertSbProjectElectricityBox(sbProjectElectricityBox));
	}

	/**
	 * 修改项目电箱
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbProjectElectricityBox sbProjectElectricityBox = sbProjectElectricityBoxService.selectSbProjectElectricityBoxById(id);
		mmap.put("sbProjectElectricityBox", sbProjectElectricityBox);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目电箱
	 */
	@RequiresPermissions("zhgd:sbProjectElectricityBox:edit")
	@Log(title = "项目电箱", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbProjectElectricityBox sbProjectElectricityBox)
	{		
		return toAjax(sbProjectElectricityBoxService.updateSbProjectElectricityBox(sbProjectElectricityBox));
	}
	
	/**
	 * 删除项目电箱
	 */
	@RequiresPermissions("zhgd:sbProjectElectricityBox:remove")
	@Log(title = "项目电箱", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(Integer id)
	{		
		return toAjax(sbProjectElectricityBoxService.deleteSbProjectElectricityBoxByIds(id));
	}
	
}
