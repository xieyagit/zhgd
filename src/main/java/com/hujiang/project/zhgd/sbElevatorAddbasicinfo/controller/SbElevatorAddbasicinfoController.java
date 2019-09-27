package com.hujiang.project.zhgd.sbElevatorAddbasicinfo.controller;

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
import com.hujiang.project.zhgd.sbElevatorAddbasicinfo.domain.SbElevatorAddbasicinfo;
import com.hujiang.project.zhgd.sbElevatorAddbasicinfo.service.ISbElevatorAddbasicinfoService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 升降机基本 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-27
 */
@Controller
@RequestMapping("/zhgd/sbElevatorAddbasicinfo")
public class SbElevatorAddbasicinfoController extends BaseController
{
    private String prefix = "zhgd/sbElevatorAddbasicinfo";
	
	@Autowired
	private ISbElevatorAddbasicinfoService sbElevatorAddbasicinfoService;
	
	@RequiresPermissions("zhgd:sbElevatorAddbasicinfo:view")
	@GetMapping()
	public String sbElevatorAddbasicinfo()
	{
	    return prefix + "/sbElevatorAddbasicinfo";
	}
	
	/**
	 * 查询升降机基本列表
	 */
	@RequiresPermissions("zhgd:sbElevatorAddbasicinfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbElevatorAddbasicinfo sbElevatorAddbasicinfo)
	{
		startPage();
        List<SbElevatorAddbasicinfo> list = sbElevatorAddbasicinfoService.selectSbElevatorAddbasicinfoList(sbElevatorAddbasicinfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出升降机基本列表
	 */
	@RequiresPermissions("zhgd:sbElevatorAddbasicinfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbElevatorAddbasicinfo sbElevatorAddbasicinfo)
    {
    	List<SbElevatorAddbasicinfo> list = sbElevatorAddbasicinfoService.selectSbElevatorAddbasicinfoList(sbElevatorAddbasicinfo);
        ExcelUtil<SbElevatorAddbasicinfo> util = new ExcelUtil<SbElevatorAddbasicinfo>(SbElevatorAddbasicinfo.class);
        return util.exportExcel(list, "sbElevatorAddbasicinfo");
    }
	
	/**
	 * 新增升降机基本
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存升降机基本
	 */
	@RequiresPermissions("zhgd:sbElevatorAddbasicinfo:add")
	@Log(title = "升降机基本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbElevatorAddbasicinfo sbElevatorAddbasicinfo)
	{		
		return toAjax(sbElevatorAddbasicinfoService.insertSbElevatorAddbasicinfo(sbElevatorAddbasicinfo));
	}

	/**
	 * 修改升降机基本
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbElevatorAddbasicinfo sbElevatorAddbasicinfo = sbElevatorAddbasicinfoService.selectSbElevatorAddbasicinfoById(id);
		mmap.put("sbElevatorAddbasicinfo", sbElevatorAddbasicinfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存升降机基本
	 */
	@RequiresPermissions("zhgd:sbElevatorAddbasicinfo:edit")
	@Log(title = "升降机基本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbElevatorAddbasicinfo sbElevatorAddbasicinfo)
	{		
		return toAjax(sbElevatorAddbasicinfoService.updateSbElevatorAddbasicinfo(sbElevatorAddbasicinfo));
	}
	
	/**
	 * 删除升降机基本
	 */
	@RequiresPermissions("zhgd:sbElevatorAddbasicinfo:remove")
	@Log(title = "升降机基本", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbElevatorAddbasicinfoService.deleteSbElevatorAddbasicinfoByIds(ids));
	}
	
}
