package com.hujiang.project.zhgd.hjExcessiveSafety.controller;

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
import com.hujiang.project.zhgd.hjExcessiveSafety.domain.HjExcessiveSafety;
import com.hujiang.project.zhgd.hjExcessiveSafety.service.IHjExcessiveSafetyService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 巡检短息记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-29
 */
@Controller
@RequestMapping("/zhgd/hjExcessiveSafety")
public class HjExcessiveSafetyController extends BaseController
{
    private String prefix = "zhgd/hjExcessiveSafety";
	
	@Autowired
	private IHjExcessiveSafetyService hjExcessiveSafetyService;
	
	@RequiresPermissions("zhgd:hjExcessiveSafety:view")
	@GetMapping()
	public String hjExcessiveSafety()
	{
	    return prefix + "/hjExcessiveSafety";
	}
	
	/**
	 * 查询巡检短息记录列表
	 */
	@RequiresPermissions("zhgd:hjExcessiveSafety:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjExcessiveSafety hjExcessiveSafety)
	{
		startPage();
        List<HjExcessiveSafety> list = hjExcessiveSafetyService.selectHjExcessiveSafetyList(hjExcessiveSafety);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检短息记录列表
	 */
	@RequiresPermissions("zhgd:hjExcessiveSafety:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjExcessiveSafety hjExcessiveSafety)
    {
    	List<HjExcessiveSafety> list = hjExcessiveSafetyService.selectHjExcessiveSafetyList(hjExcessiveSafety);
        ExcelUtil<HjExcessiveSafety> util = new ExcelUtil<HjExcessiveSafety>(HjExcessiveSafety.class);
        return util.exportExcel(list, "hjExcessiveSafety");
    }
	
	/**
	 * 新增巡检短息记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检短息记录
	 */
	@RequiresPermissions("zhgd:hjExcessiveSafety:add")
	@Log(title = "巡检短息记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjExcessiveSafety hjExcessiveSafety)
	{		
		return toAjax(hjExcessiveSafetyService.insertHjExcessiveSafety(hjExcessiveSafety));
	}

	/**
	 * 修改巡检短息记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjExcessiveSafety hjExcessiveSafety = hjExcessiveSafetyService.selectHjExcessiveSafetyById(id);
		mmap.put("hjExcessiveSafety", hjExcessiveSafety);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存巡检短息记录
	 */
	@RequiresPermissions("zhgd:hjExcessiveSafety:edit")
	@Log(title = "巡检短息记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjExcessiveSafety hjExcessiveSafety)
	{		
		return toAjax(hjExcessiveSafetyService.updateHjExcessiveSafety(hjExcessiveSafety));
	}
	
	/**
	 * 删除巡检短息记录
	 */
	@RequiresPermissions("zhgd:hjExcessiveSafety:remove")
	@Log(title = "巡检短息记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjExcessiveSafetyService.deleteHjExcessiveSafetyByIds(ids));
	}
	
}
