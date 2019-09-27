package com.hujiang.project.zhgd.hjDictionaries.controller;

import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 字典 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjDictionaries")
public class HjDictionariesController extends BaseController
{
    private String prefix = "zhgd/hjDictionaries";
	
	@Autowired
	private IHjDictionariesService hjDictionariesService;
	
	@RequiresPermissions("zhgd:hjDictionaries:view")
	@GetMapping()
	public String hjDictionaries()
	{
	    return prefix + "/hjDictionaries";
	}
	
	/**
	 * 查询字典列表
	 */
	@RequiresPermissions("zhgd:hjDictionaries:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjDictionaries hjDictionaries)
	{
		startPage();
        List<HjDictionaries> list = hjDictionariesService.selectHjDictionariesList(hjDictionaries);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出字典列表
	 */
	@RequiresPermissions("zhgd:hjDictionaries:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjDictionaries hjDictionaries)
    {
    	List<HjDictionaries> list = hjDictionariesService.selectHjDictionariesList(hjDictionaries);
        ExcelUtil<HjDictionaries> util = new ExcelUtil<HjDictionaries>(HjDictionaries.class);
        return util.exportExcel(list, "hjDictionaries");
    }
	
	/**
	 * 新增字典
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存字典
	 */
	@RequiresPermissions("zhgd:hjDictionaries:add")
	@Log(title = "字典", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjDictionaries hjDictionaries)
	{		
		return toAjax(hjDictionariesService.insertHjDictionaries(hjDictionaries));
	}

	/**
	 * 修改字典
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjDictionaries hjDictionaries = hjDictionariesService.selectHjDictionariesById(id);
		mmap.put("hjDictionaries", hjDictionaries);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存字典
	 */
	@RequiresPermissions("zhgd:hjDictionaries:edit")
	@Log(title = "字典", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjDictionaries hjDictionaries)
	{
		hjDictionaries.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return toAjax(hjDictionariesService.updateHjDictionaries(hjDictionaries));
	}
	
	/**
	 * 删除字典
	 */
	@RequiresPermissions("zhgd:hjDictionaries:remove")
	@Log(title = "字典", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjDictionariesService.deleteHjDictionariesByIds(ids));
	}
	
}
