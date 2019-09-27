package com.hujiang.project.zhgd.hjDeeppit.controller;

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
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitGroup;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitGroupService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 深基坑测点分组 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-02
 */
@Controller
@RequestMapping("/zhgd/sbDeeppitGroup")
public class SbDeeppitGroupController extends BaseController
{
    private String prefix = "zhgd/sbDeeppitGroup";
	
	@Autowired
	private ISbDeeppitGroupService sbDeeppitGroupService;
	
	@RequiresPermissions("zhgd:sbDeeppitGroup:view")
	@GetMapping()
	public String sbDeeppitGroup()
	{
	    return prefix + "/sbDeeppitGroup";
	}
	
	/**
	 * 查询深基坑测点分组列表
	 */
	@RequiresPermissions("zhgd:sbDeeppitGroup:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbDeeppitGroup sbDeeppitGroup)
	{
		startPage();
        List<SbDeeppitGroup> list = sbDeeppitGroupService.selectSbDeeppitGroupList(sbDeeppitGroup);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出深基坑测点分组列表
	 */
	@RequiresPermissions("zhgd:sbDeeppitGroup:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbDeeppitGroup sbDeeppitGroup)
    {
    	List<SbDeeppitGroup> list = sbDeeppitGroupService.selectSbDeeppitGroupList(sbDeeppitGroup);
        ExcelUtil<SbDeeppitGroup> util = new ExcelUtil<SbDeeppitGroup>(SbDeeppitGroup.class);
        return util.exportExcel(list, "sbDeeppitGroup");
    }
	
	/**
	 * 新增深基坑测点分组
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存深基坑测点分组
	 */
	@RequiresPermissions("zhgd:sbDeeppitGroup:add")
	@Log(title = "深基坑测点分组", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbDeeppitGroup sbDeeppitGroup)
	{		
		return toAjax(sbDeeppitGroupService.insertSbDeeppitGroup(sbDeeppitGroup));
	}

	/**
	 * 修改深基坑测点分组
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbDeeppitGroup sbDeeppitGroup = sbDeeppitGroupService.selectSbDeeppitGroupById(id);
		mmap.put("sbDeeppitGroup", sbDeeppitGroup);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存深基坑测点分组
	 */
	@RequiresPermissions("zhgd:sbDeeppitGroup:edit")
	@Log(title = "深基坑测点分组", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbDeeppitGroup sbDeeppitGroup)
	{		
		return toAjax(sbDeeppitGroupService.updateSbDeeppitGroup(sbDeeppitGroup));
	}
	
	/**
	 * 删除深基坑测点分组
	 */
	@RequiresPermissions("zhgd:sbDeeppitGroup:remove")
	@Log(title = "深基坑测点分组", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbDeeppitGroupService.deleteSbDeeppitGroupByIds(ids));
	}
	
}
