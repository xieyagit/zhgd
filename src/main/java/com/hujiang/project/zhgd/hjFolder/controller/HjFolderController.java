package com.hujiang.project.zhgd.hjFolder.controller;

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
import com.hujiang.project.zhgd.hjFolder.domain.HjFolder;
import com.hujiang.project.zhgd.hjFolder.service.IHjFolderService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 文件夹 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/zhgd/hjFolder")
public class HjFolderController extends BaseController
{
    private String prefix = "zhgd/hjFolder";
	
	@Autowired
	private IHjFolderService hjFolderService;
	
	@RequiresPermissions("zhgd:hjFolder:view")
	@GetMapping()
	public String hjFolder()
	{
	    return prefix + "/hjFolder";
	}
	
	/**
	 * 查询文件夹列表
	 */
	@RequiresPermissions("zhgd:hjFolder:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjFolder hjFolder)
	{
		startPage();
        List<HjFolder> list = hjFolderService.selectHjFolderList(hjFolder);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出文件夹列表
	 */
	@RequiresPermissions("zhgd:hjFolder:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjFolder hjFolder)
    {
    	List<HjFolder> list = hjFolderService.selectHjFolderList(hjFolder);
        ExcelUtil<HjFolder> util = new ExcelUtil<HjFolder>(HjFolder.class);
        return util.exportExcel(list, "hjFolder");
    }
	
	/**
	 * 新增文件夹
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存文件夹
	 */
	@RequiresPermissions("zhgd:hjFolder:add")
	@Log(title = "文件夹", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjFolder hjFolder)
	{		
		return toAjax(hjFolderService.insertHjFolder(hjFolder));
	}

	/**
	 * 修改文件夹
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjFolder hjFolder = hjFolderService.selectHjFolderById(id);
		mmap.put("hjFolder", hjFolder);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存文件夹
	 */
	@RequiresPermissions("zhgd:hjFolder:edit")
	@Log(title = "文件夹", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjFolder hjFolder)
	{		
		return toAjax(hjFolderService.updateHjFolder(hjFolder));
	}
	
	/**
	 * 删除文件夹
	 */
	@RequiresPermissions("zhgd:hjFolder:remove")
	@Log(title = "文件夹", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjFolderService.deleteHjFolderByIds(ids));
	}
	
}
