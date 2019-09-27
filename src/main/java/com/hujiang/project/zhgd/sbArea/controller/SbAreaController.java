package com.hujiang.project.zhgd.sbArea.controller;

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
import com.hujiang.project.zhgd.sbArea.domain.SbArea;
import com.hujiang.project.zhgd.sbArea.service.ISbAreaService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 工区 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-29
 */
@Controller
@RequestMapping("/zhgd/sbArea")
public class SbAreaController extends BaseController
{
    private String prefix = "zhgd/sbArea";
	
	@Autowired
	private ISbAreaService sbAreaService;
	
	@RequiresPermissions("zhgd:sbArea:view")
	@GetMapping()
	public String sbArea()
	{
	    return prefix + "/sbArea";
	}
	
	/**
	 * 查询工区列表
	 */
	@RequiresPermissions("zhgd:sbArea:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbArea sbArea)
	{
		startPage();
        List<SbArea> list = sbAreaService.selectSbAreaList(sbArea);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出工区列表
	 */
	@RequiresPermissions("zhgd:sbArea:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbArea sbArea)
    {
    	List<SbArea> list = sbAreaService.selectSbAreaList(sbArea);
        ExcelUtil<SbArea> util = new ExcelUtil<SbArea>(SbArea.class);
        return util.exportExcel(list, "sbArea");
    }
	
	/**
	 * 新增工区
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存工区
	 */
	@RequiresPermissions("zhgd:sbArea:add")
	@Log(title = "工区", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbArea sbArea)
	{		
		return toAjax(sbAreaService.insertSbArea(sbArea));
	}

	/**
	 * 修改工区
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbArea sbArea = sbAreaService.selectSbAreaById(id);
		mmap.put("sbArea", sbArea);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存工区
	 */
	@RequiresPermissions("zhgd:sbArea:edit")
	@Log(title = "工区", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbArea sbArea)
	{		
		return toAjax(sbAreaService.updateSbArea(sbArea));
	}
	
	/**
	 * 删除工区
	 */
	@RequiresPermissions("zhgd:sbArea:remove")
	@Log(title = "工区", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbAreaService.deleteSbAreaByIds(ids));
	}
	
}
