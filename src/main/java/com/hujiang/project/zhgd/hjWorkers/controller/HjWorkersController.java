package com.hujiang.project.zhgd.hjWorkers.controller;

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
import com.hujiang.project.zhgd.hjWorkers.domain.HjWorkers;
import com.hujiang.project.zhgd.hjWorkers.service.IHjWorkersService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 工人库 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-16
 */
@Controller
@RequestMapping("/zhgd/hjWorkers")
public class HjWorkersController extends BaseController
{
    private String prefix = "zhgd/hjWorkers";
	
	@Autowired
	private IHjWorkersService hjWorkersService;
	
	@RequiresPermissions("zhgd:hjWorkers:view")
	@GetMapping()
	public String hjWorkers()
	{
	    return prefix + "/hjWorkers";
	}
	
	/**
	 * 查询工人库列表
	 */
	@RequiresPermissions("zhgd:hjWorkers:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjWorkers hjWorkers)
	{
		startPage();
        List<HjWorkers> list = hjWorkersService.selectHjWorkersList(hjWorkers);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出工人库列表
	 */
	@RequiresPermissions("zhgd:hjWorkers:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjWorkers hjWorkers)
    {
    	List<HjWorkers> list = hjWorkersService.selectHjWorkersList(hjWorkers);
        ExcelUtil<HjWorkers> util = new ExcelUtil<HjWorkers>(HjWorkers.class);
        return util.exportExcel(list, "hjWorkers");
    }
	
	/**
	 * 新增工人库
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存工人库
	 */
	@RequiresPermissions("zhgd:hjWorkers:add")
	@Log(title = "工人库", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjWorkers hjWorkers)
	{		
		return toAjax(hjWorkersService.insertHjWorkers(hjWorkers));
	}

	/**
	 * 修改工人库
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjWorkers hjWorkers = hjWorkersService.selectHjWorkersById(id);
		mmap.put("hjWorkers", hjWorkers);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存工人库
	 */
	@RequiresPermissions("zhgd:hjWorkers:edit")
	@Log(title = "工人库", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjWorkers hjWorkers)
	{		
		return toAjax(hjWorkersService.updateHjWorkers(hjWorkers));
	}
	
	/**
	 * 删除工人库
	 */
	@RequiresPermissions("zhgd:hjWorkers:remove")
	@Log(title = "工人库", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjWorkersService.deleteHjWorkersByIds(ids));
	}
	
}
