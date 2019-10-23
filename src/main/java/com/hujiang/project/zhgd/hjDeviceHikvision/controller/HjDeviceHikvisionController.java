package com.hujiang.project.zhgd.hjDeviceHikvision.controller;

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
import com.hujiang.project.zhgd.hjDeviceHikvision.domain.HjDeviceHikvision;
import com.hujiang.project.zhgd.hjDeviceHikvision.service.IHjDeviceHikvisionService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 海康萤石用户 信息操作处理
 * 
 * @author hujiang
 * @date 2019-10-19
 */
@Controller
@RequestMapping("/zhgd/hjDeviceHikvision")
public class HjDeviceHikvisionController extends BaseController
{
    private String prefix = "zhgd/hjDeviceHikvision";
	
	@Autowired
	private IHjDeviceHikvisionService hjDeviceHikvisionService;
	
	@RequiresPermissions("zhgd:hjDeviceHikvision:view")
	@GetMapping()
	public String hjDeviceHikvision()
	{
	    return prefix + "/hjDeviceHikvision";
	}
	
	/**
	 * 查询海康萤石用户列表
	 */
	@RequiresPermissions("zhgd:hjDeviceHikvision:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjDeviceHikvision hjDeviceHikvision)
	{
		startPage();
        List<HjDeviceHikvision> list = hjDeviceHikvisionService.selectHjDeviceHikvisionList(hjDeviceHikvision);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出海康萤石用户列表
	 */
	@RequiresPermissions("zhgd:hjDeviceHikvision:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjDeviceHikvision hjDeviceHikvision)
    {
    	List<HjDeviceHikvision> list = hjDeviceHikvisionService.selectHjDeviceHikvisionList(hjDeviceHikvision);
        ExcelUtil<HjDeviceHikvision> util = new ExcelUtil<HjDeviceHikvision>(HjDeviceHikvision.class);
        return util.exportExcel(list, "hjDeviceHikvision");
    }
	
	/**
	 * 新增海康萤石用户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存海康萤石用户
	 */
	@RequiresPermissions("zhgd:hjDeviceHikvision:add")
	@Log(title = "海康萤石用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjDeviceHikvision hjDeviceHikvision)
	{		
		return toAjax(hjDeviceHikvisionService.insertHjDeviceHikvision(hjDeviceHikvision));
	}

	/**
	 * 修改海康萤石用户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjDeviceHikvision hjDeviceHikvision = hjDeviceHikvisionService.selectHjDeviceHikvisionById(id);
		mmap.put("hjDeviceHikvision", hjDeviceHikvision);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存海康萤石用户
	 */
	@RequiresPermissions("zhgd:hjDeviceHikvision:edit")
	@Log(title = "海康萤石用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjDeviceHikvision hjDeviceHikvision)
	{		
		return toAjax(hjDeviceHikvisionService.updateHjDeviceHikvision(hjDeviceHikvision));
	}
	
	/**
	 * 删除海康萤石用户
	 */
	@RequiresPermissions("zhgd:hjDeviceHikvision:remove")
	@Log(title = "海康萤石用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjDeviceHikvisionService.deleteHjDeviceHikvisionByIds(ids));
	}
	
}
