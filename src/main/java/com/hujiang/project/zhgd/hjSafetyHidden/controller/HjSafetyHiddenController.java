package com.hujiang.project.zhgd.hjSafetyHidden.controller;

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
import com.hujiang.project.zhgd.hjSafetyHidden.domain.HjSafetyHidden;
import com.hujiang.project.zhgd.hjSafetyHidden.service.IHjSafetyHiddenService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 隐患类型 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-10
 */
@Controller
@RequestMapping("/zhgd/hjSafetyHidden")
public class HjSafetyHiddenController extends BaseController
{
    private String prefix = "zhgd/hjSafetyHidden";
	
	@Autowired
	private IHjSafetyHiddenService hjSafetyHiddenService;
	
	@RequiresPermissions("zhgd:hjSafetyHidden:view")
	@GetMapping()
	public String hjSafetyHidden()
	{
	    return prefix + "/hjSafetyHidden";
	}
	
	/**
	 * 查询隐患类型列表
	 */
	@RequiresPermissions("zhgd:hjSafetyHidden:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjSafetyHidden hjSafetyHidden)
	{
		startPage();
        List<HjSafetyHidden> list = hjSafetyHiddenService.selectHjSafetyHiddenList(hjSafetyHidden);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出隐患类型列表
	 */
	@RequiresPermissions("zhgd:hjSafetyHidden:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjSafetyHidden hjSafetyHidden)
    {
    	List<HjSafetyHidden> list = hjSafetyHiddenService.selectHjSafetyHiddenList(hjSafetyHidden);
        ExcelUtil<HjSafetyHidden> util = new ExcelUtil<HjSafetyHidden>(HjSafetyHidden.class);
        return util.exportExcel(list, "hjSafetyHidden");
    }
	
	/**
	 * 新增隐患类型
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存隐患类型
	 */
	@RequiresPermissions("zhgd:hjSafetyHidden:add")
	@Log(title = "隐患类型", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjSafetyHidden hjSafetyHidden)
	{		
		return toAjax(hjSafetyHiddenService.insertHjSafetyHidden(hjSafetyHidden));
	}

	/**
	 * 修改隐患类型
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjSafetyHidden hjSafetyHidden = hjSafetyHiddenService.selectHjSafetyHiddenById(id);
		mmap.put("hjSafetyHidden", hjSafetyHidden);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存隐患类型
	 */
	@RequiresPermissions("zhgd:hjSafetyHidden:edit")
	@Log(title = "隐患类型", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjSafetyHidden hjSafetyHidden)
	{		
		return toAjax(hjSafetyHiddenService.updateHjSafetyHidden(hjSafetyHidden));
	}
	
	/**
	 * 删除隐患类型
	 */
	@RequiresPermissions("zhgd:hjSafetyHidden:remove")
	@Log(title = "隐患类型", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjSafetyHiddenService.deleteHjSafetyHiddenByIds(ids));
	}
	
}
