package com.hujiang.project.zhgd.hjSafetyNopass.controller;

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
import com.hujiang.project.zhgd.hjSafetyNopass.domain.SafetyNopass;
import com.hujiang.project.zhgd.hjSafetyNopass.service.IHjSafetyNopassService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 整改未通过 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-18
 */
@Controller
@RequestMapping("/zhgd/hjSafetyNopass")
public class HjSafetyNopassController extends BaseController
{
    private String prefix = "zhgd/hjSafetyNopass";
	
	@Autowired
	private IHjSafetyNopassService hjSafetyNopassService;
	
	@RequiresPermissions("zhgd:hjSafetyNopass:view")
	@GetMapping()
	public String hjSafetyNopass()
	{
	    return prefix + "/hjSafetyNopass";
	}
	
	/**
	 * 查询整改未通过列表
	 */
	@RequiresPermissions("zhgd:hjSafetyNopass:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SafetyNopass hjSafetyNopass)
	{
		startPage();
        List<SafetyNopass> list = hjSafetyNopassService.selectHjSafetyNopassList(hjSafetyNopass);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出整改未通过列表
	 */
	@RequiresPermissions("zhgd:hjSafetyNopass:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SafetyNopass hjSafetyNopass)
    {
    	List<SafetyNopass> list = hjSafetyNopassService.selectHjSafetyNopassList(hjSafetyNopass);
        ExcelUtil<SafetyNopass> util = new ExcelUtil<SafetyNopass>(SafetyNopass.class);
        return util.exportExcel(list, "hjSafetyNopass");
    }
	
	/**
	 * 新增整改未通过
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存整改未通过
	 */
	@RequiresPermissions("zhgd:hjSafetyNopass:add")
	@Log(title = "整改未通过", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SafetyNopass hjSafetyNopass)
	{		
		return toAjax(hjSafetyNopassService.insertHjSafetyNopass(hjSafetyNopass));
	}

	/**
	 * 修改整改未通过
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SafetyNopass hjSafetyNopass = hjSafetyNopassService.selectHjSafetyNopassById(id);
		mmap.put("hjSafetyNopass", hjSafetyNopass);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存整改未通过
	 */
	@RequiresPermissions("zhgd:hjSafetyNopass:edit")
	@Log(title = "整改未通过", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SafetyNopass hjSafetyNopass)
	{		
		return toAjax(hjSafetyNopassService.updateHjSafetyNopass(hjSafetyNopass));
	}
	
	/**
	 * 删除整改未通过
	 */
	@RequiresPermissions("zhgd:hjSafetyNopass:remove")
	@Log(title = "整改未通过", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjSafetyNopassService.deleteHjSafetyNopassByIds(ids));
	}
	
}
