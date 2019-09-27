package com.hujiang.project.zhgd.sbCraneWorkloop.controller;

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
import com.hujiang.project.zhgd.sbCraneWorkloop.domain.SbCraneWorkloop;
import com.hujiang.project.zhgd.sbCraneWorkloop.service.ISbCraneWorkloopService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 塔机工作循环数据 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-24
 */
@Controller
@RequestMapping("/zhgd/sbCraneWorkloop")
public class SbCraneWorkloopController extends BaseController
{
    private String prefix = "zhgd/sbCraneWorkloop";
	
	@Autowired
	private ISbCraneWorkloopService sbCraneWorkloopService;
	
	@RequiresPermissions("zhgd:sbCraneWorkloop:view")
	@GetMapping()
	public String sbCraneWorkloop()
	{
	    return prefix + "/sbCraneWorkloop";
	}
	
	/**
	 * 查询塔机工作循环数据列表
	 */
	@RequiresPermissions("zhgd:sbCraneWorkloop:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbCraneWorkloop sbCraneWorkloop)
	{
		startPage();
        List<SbCraneWorkloop> list = sbCraneWorkloopService.selectSbCraneWorkloopList(sbCraneWorkloop);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出塔机工作循环数据列表
	 */
	@RequiresPermissions("zhgd:sbCraneWorkloop:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbCraneWorkloop sbCraneWorkloop)
    {
    	List<SbCraneWorkloop> list = sbCraneWorkloopService.selectSbCraneWorkloopList(sbCraneWorkloop);
        ExcelUtil<SbCraneWorkloop> util = new ExcelUtil<SbCraneWorkloop>(SbCraneWorkloop.class);
        return util.exportExcel(list, "sbCraneWorkloop");
    }
	
	/**
	 * 新增塔机工作循环数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存塔机工作循环数据
	 */
	@RequiresPermissions("zhgd:sbCraneWorkloop:add")
	@Log(title = "塔机工作循环数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbCraneWorkloop sbCraneWorkloop)
	{		
		return toAjax(sbCraneWorkloopService.insertSbCraneWorkloop(sbCraneWorkloop));
	}

	/**
	 * 修改塔机工作循环数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbCraneWorkloop sbCraneWorkloop = sbCraneWorkloopService.selectSbCraneWorkloopById(id);
		mmap.put("sbCraneWorkloop", sbCraneWorkloop);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存塔机工作循环数据
	 */
	@RequiresPermissions("zhgd:sbCraneWorkloop:edit")
	@Log(title = "塔机工作循环数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbCraneWorkloop sbCraneWorkloop)
	{		
		return toAjax(sbCraneWorkloopService.updateSbCraneWorkloop(sbCraneWorkloop));
	}
	
	/**
	 * 删除塔机工作循环数据
	 */
	@RequiresPermissions("zhgd:sbCraneWorkloop:remove")
	@Log(title = "塔机工作循环数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbCraneWorkloopService.deleteSbCraneWorkloopByIds(ids));
	}
	
}
