package com.hujiang.project.zhgd.hjProjectWorkers.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 项目工人 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-19
 */
@Controller
@RequestMapping("/zhgd/hjProjectWorkers")
public class HjProjectWorkersController extends BaseController
{
    private String prefix = "zhgd/hjProjectWorkers";
	
	@Autowired
	private IHjProjectWorkersService hjProjectWorkersService;
	
	@RequiresPermissions("zhgd:hjProjectWorkers:view")
	@GetMapping()
	public String hjProjectWorkers()
	{
	    return prefix + "/hjProjectWorkers";
	}
	
	/**
	 * 查询项目工人列表
	 */
	@RequiresPermissions("zhgd:hjProjectWorkers:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjProjectWorkers hjProjectWorkers)
	{
		startPage();
        List<HjProjectWorkers> list = hjProjectWorkersService.selectHjProjectWorkersList(hjProjectWorkers);
		return getDataTable(list);
	}

	
	/**
	 * 导出项目工人列表
	 */
	@RequiresPermissions("zhgd:hjProjectWorkers:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjProjectWorkers hjProjectWorkers)
    {
    	List<HjProjectWorkers> list = hjProjectWorkersService.selectHjProjectWorkersList(hjProjectWorkers);
        ExcelUtil<HjProjectWorkers> util = new ExcelUtil<HjProjectWorkers>(HjProjectWorkers.class);
        return util.exportExcel(list, "hjProjectWorkers");
    }
	
	/**
	 * 新增项目工人
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目工人
	 */
	@RequiresPermissions("zhgd:hjProjectWorkers:add")
	@Log(title = "项目工人", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjProjectWorkers hjProjectWorkers)
	{		
		return toAjax(hjProjectWorkersService.insertHjProjectWorkers(hjProjectWorkers));
	}

	/**
	 * 修改项目工人
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjProjectWorkers hjProjectWorkers = hjProjectWorkersService.selectHjProjectWorkersById(id);
		mmap.put("hjProjectWorkers", hjProjectWorkers);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目工人
	 */
	@RequiresPermissions("zhgd:hjProjectWorkers:edit")
	@Log(title = "项目工人", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjProjectWorkers hjProjectWorkers)
	{		
		return toAjax(hjProjectWorkersService.updateHjProjectWorkers(hjProjectWorkers));
	}
	
	/**
	 * 删除项目工人
	 */
	@RequiresPermissions("zhgd:hjProjectWorkers:remove")
	@Log(title = "项目工人", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjProjectWorkersService.deleteHjProjectWorkersByIds(ids));
	}
	
}
