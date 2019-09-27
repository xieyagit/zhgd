package com.hujiang.project.zhgd.hjFile.controller;

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
import com.hujiang.project.zhgd.hjFile.domain.HjFile;
import com.hujiang.project.zhgd.hjFile.service.IHjFileService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 文件路径 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/zhgd/hjFile")
public class HjFileController extends BaseController
{
    private String prefix = "zhgd/hjFile";
	
	@Autowired
	private IHjFileService hjFileService;
	
	@RequiresPermissions("zhgd:hjFile:view")
	@GetMapping()
	public String hjFile()
	{
	    return prefix + "/hjFile";
	}
	
	/**
	 * 查询文件路径列表
	 */
	@RequiresPermissions("zhgd:hjFile:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjFile hjFile)
	{
		startPage();
        List<HjFile> list = hjFileService.selectHjFileList(hjFile);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出文件路径列表
	 */
	@RequiresPermissions("zhgd:hjFile:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjFile hjFile)
    {
    	List<HjFile> list = hjFileService.selectHjFileList(hjFile);
        ExcelUtil<HjFile> util = new ExcelUtil<HjFile>(HjFile.class);
        return util.exportExcel(list, "hjFile");
    }
	
	/**
	 * 新增文件路径
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存文件路径
	 */
	@RequiresPermissions("zhgd:hjFile:add")
	@Log(title = "文件路径", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjFile hjFile)
	{		
		return toAjax(hjFileService.insertHjFile(hjFile));
	}

	/**
	 * 修改文件路径
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjFile hjFile = hjFileService.selectHjFileById(id);
		mmap.put("hjFile", hjFile);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存文件路径
	 */
	@RequiresPermissions("zhgd:hjFile:edit")
	@Log(title = "文件路径", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjFile hjFile)
	{		
		return toAjax(hjFileService.updateHjFile(hjFile));
	}
	
	/**
	 * 删除文件路径
	 */
	@RequiresPermissions("zhgd:hjFile:remove")
	@Log(title = "文件路径", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjFileService.deleteHjFileByIds(ids));
	}
	
}
