package com.hujiang.project.zhgd.hjSafetyAbarbeitung.controller;

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
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.HjSafetyAbarbeitung;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.service.IHjSafetyAbarbeitungService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 整改 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-10
 */
@Controller
@RequestMapping("/zhgd/hjSafetyAbarbeitung")
public class HjSafetyAbarbeitungController extends BaseController
{
    private String prefix = "zhgd/hjSafetyAbarbeitung";
	
	@Autowired
	private IHjSafetyAbarbeitungService hjSafetyAbarbeitungService;
	
	@RequiresPermissions("zhgd:hjSafetyAbarbeitung:view")
	@GetMapping()
	public String hjSafetyAbarbeitung()
	{
	    return prefix + "/hjSafetyAbarbeitung";
	}
	
	/**
	 * 查询整改列表
	 */
	@RequiresPermissions("zhgd:hjSafetyAbarbeitung:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjSafetyAbarbeitung hjSafetyAbarbeitung)
	{
		startPage();
        List<HjSafetyAbarbeitung> list = hjSafetyAbarbeitungService.selectHjSafetyAbarbeitungList(hjSafetyAbarbeitung);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出整改列表
	 */
	@RequiresPermissions("zhgd:hjSafetyAbarbeitung:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjSafetyAbarbeitung hjSafetyAbarbeitung)
    {
    	List<HjSafetyAbarbeitung> list = hjSafetyAbarbeitungService.selectHjSafetyAbarbeitungList(hjSafetyAbarbeitung);
        ExcelUtil<HjSafetyAbarbeitung> util = new ExcelUtil<HjSafetyAbarbeitung>(HjSafetyAbarbeitung.class);
        return util.exportExcel(list, "hjSafetyAbarbeitung");
    }
	
	/**
	 * 新增整改
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存整改
	 */
	@RequiresPermissions("zhgd:hjSafetyAbarbeitung:add")
	@Log(title = "整改", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjSafetyAbarbeitung hjSafetyAbarbeitung)
	{		
		return toAjax(hjSafetyAbarbeitungService.insertHjSafetyAbarbeitung(hjSafetyAbarbeitung));
	}

	/**
	 * 修改整改
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjSafetyAbarbeitung hjSafetyAbarbeitung = hjSafetyAbarbeitungService.selectHjSafetyAbarbeitungById(id);
		mmap.put("hjSafetyAbarbeitung", hjSafetyAbarbeitung);
	    return prefix + "/edit";
	}
	
//	/**
//	 * 修改保存整改
//	 */
//	@RequiresPermissions("zhgd:hjSafetyAbarbeitung:edit")
//	@Log(title = "整改", businessType = BusinessType.UPDATE)
//	@PostMapping("/edit")
//	@ResponseBody
//	public AjaxResult editSave(HjSafetyAbarbeitung hjSafetyAbarbeitung)
//	{
//		return toAjax(hjSafetyAbarbeitungService.updateHjSafetyAbarbeitung(hjSafetyAbarbeitung));
//	}
//
//	/**
//	 * 删除整改
//	 */
//	@RequiresPermissions("zhgd:hjSafetyAbarbeitung:remove")
//	@Log(title = "整改", businessType = BusinessType.DELETE)
//	@PostMapping( "/remove")
//	@ResponseBody
//	public AjaxResult remove(String ids)
//	{
//		return toAjax(hjSafetyAbarbeitungService.deleteHjSafetyAbarbeitungByIds(ids));
//	}
	
}
