package com.hujiang.project.zhgd.sbProjectVideoArea.controller;

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
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.SbProjectVideoArea;
import com.hujiang.project.zhgd.sbProjectVideoArea.service.ISbProjectVideoAreaService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 项目视频区 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-23
 */
@Controller
@RequestMapping("/zhgd/sbProjectVideoArea")
public class SbProjectVideoAreaController extends BaseController
{
    private String prefix = "zhgd/sbProjectVideoArea";
	
	@Autowired
	private ISbProjectVideoAreaService sbProjectVideoAreaService;
	
	@RequiresPermissions("zhgd:sbProjectVideoArea:view")
	@GetMapping()
	public String sbProjectVideoArea()
	{
	    return prefix + "/sbProjectVideoArea";
	}
	
	/**
	 * 查询项目视频区列表
	 */
	@RequiresPermissions("zhgd:sbProjectVideoArea:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbProjectVideoArea sbProjectVideoArea)
	{
		startPage();
        List<SbProjectVideoArea> list = sbProjectVideoAreaService.selectSbProjectVideoAreaList(sbProjectVideoArea);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出项目视频区列表
	 */
	@RequiresPermissions("zhgd:sbProjectVideoArea:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbProjectVideoArea sbProjectVideoArea)
    {
    	List<SbProjectVideoArea> list = sbProjectVideoAreaService.selectSbProjectVideoAreaList(sbProjectVideoArea);
        ExcelUtil<SbProjectVideoArea> util = new ExcelUtil<SbProjectVideoArea>(SbProjectVideoArea.class);
        return util.exportExcel(list, "sbProjectVideoArea");
    }
	
	/**
	 * 新增项目视频区
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目视频区
	 */
	@RequiresPermissions("zhgd:sbProjectVideoArea:add")
	@Log(title = "项目视频区", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbProjectVideoArea sbProjectVideoArea)
	{		
		return toAjax(sbProjectVideoAreaService.insertSbProjectVideoArea(sbProjectVideoArea));
	}

	/**
	 * 修改项目视频区
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbProjectVideoArea sbProjectVideoArea = sbProjectVideoAreaService.selectSbProjectVideoAreaById(id);
		mmap.put("sbProjectVideoArea", sbProjectVideoArea);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目视频区
	 */
	@RequiresPermissions("zhgd:sbProjectVideoArea:edit")
	@Log(title = "项目视频区", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbProjectVideoArea sbProjectVideoArea)
	{		
		return toAjax(sbProjectVideoAreaService.updateSbProjectVideoArea(sbProjectVideoArea));
	}
	
	/**
	 * 删除项目视频区
	 */
	@RequiresPermissions("zhgd:sbProjectVideoArea:remove")
	@Log(title = "项目视频区", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbProjectVideoAreaService.deleteSbProjectVideoAreaByIds(ids));
	}
	
}
