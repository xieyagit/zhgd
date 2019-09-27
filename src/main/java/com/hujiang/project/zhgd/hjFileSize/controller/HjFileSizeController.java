package com.hujiang.project.zhgd.hjFileSize.controller;

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
import com.hujiang.project.zhgd.hjFileSize.domain.HjFileSize;
import com.hujiang.project.zhgd.hjFileSize.service.IHjFileSizeService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 项目存储空间大小 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/zhgd/hjFileSize")
public class HjFileSizeController extends BaseController
{
    private String prefix = "zhgd/hjFileSize";
	
	@Autowired
	private IHjFileSizeService hjFileSizeService;
	
	@RequiresPermissions("zhgd:hjFileSize:view")
	@GetMapping()
	public String hjFileSize()
	{
	    return prefix + "/hjFileSize";
	}
	
	/**
	 * 查询项目存储空间大小列表
	 */
	@RequiresPermissions("zhgd:hjFileSize:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjFileSize hjFileSize)
	{
		startPage();
        List<HjFileSize> list = hjFileSizeService.selectHjFileSizeList(hjFileSize);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出项目存储空间大小列表
	 */
	@RequiresPermissions("zhgd:hjFileSize:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjFileSize hjFileSize)
    {
    	List<HjFileSize> list = hjFileSizeService.selectHjFileSizeList(hjFileSize);
        ExcelUtil<HjFileSize> util = new ExcelUtil<HjFileSize>(HjFileSize.class);
        return util.exportExcel(list, "hjFileSize");
    }
	
	/**
	 * 新增项目存储空间大小
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目存储空间大小
	 */
	@RequiresPermissions("zhgd:hjFileSize:add")
	@Log(title = "项目存储空间大小", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjFileSize hjFileSize)
	{		
		return toAjax(hjFileSizeService.insertHjFileSize(hjFileSize));
	}

	/**
	 * 修改项目存储空间大小
	 */
	@GetMapping("/edit/{projectId}")
	public String edit(@PathVariable("projectId") Integer projectId, ModelMap mmap)
	{
		HjFileSize hjFileSize = hjFileSizeService.selectHjFileSizeById(projectId);
		mmap.put("hjFileSize", hjFileSize);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目存储空间大小
	 */
	@RequiresPermissions("zhgd:hjFileSize:edit")
	@Log(title = "项目存储空间大小", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjFileSize hjFileSize)
	{		
		return toAjax(hjFileSizeService.updateHjFileSize(hjFileSize));
	}
	
	/**
	 * 删除项目存储空间大小
	 */
	@RequiresPermissions("zhgd:hjFileSize:remove")
	@Log(title = "项目存储空间大小", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjFileSizeService.deleteHjFileSizeByIds(ids));
	}
	
}
