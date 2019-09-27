package com.hujiang.project.zhgd.hjConstructionProject.controller;

import org.springframework.stereotype.Controller;
import java.util.List;
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
import com.hujiang.project.zhgd.hjConstructionProject.domain.HjConstructionProject;
import com.hujiang.project.zhgd.hjConstructionProject.service.IHjConstructionProjectService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 参建单位项目 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjConstructionProject")
public class HjConstructionProjectController extends BaseController
{
    private String prefix = "zhgd/hjConstructionProject";
	
	@Autowired
	private IHjConstructionProjectService hjConstructionProjectService;
	
	@RequiresPermissions("zhgd:hjConstructionProject:view")
	@GetMapping()
	public String hjConstructionProject()
	{
	    return prefix + "/hjConstructionProject";
	}
	
	/**
	 * 查询参建单位项目列表
	 */
	@RequiresPermissions("zhgd:hjConstructionProject:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjConstructionProject hjConstructionProject)
	{
		startPage();
        List<HjConstructionProject> list = hjConstructionProjectService.selectHjConstructionProjectList(hjConstructionProject);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出参建单位项目列表
	 */
	@RequiresPermissions("zhgd:hjConstructionProject:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjConstructionProject hjConstructionProject)
    {
    	List<HjConstructionProject> list = hjConstructionProjectService.selectHjConstructionProjectList(hjConstructionProject);
        ExcelUtil<HjConstructionProject> util = new ExcelUtil<HjConstructionProject>(HjConstructionProject.class);
        return util.exportExcel(list, "hjConstructionProject");
    }
	
	/**
	 * 新增参建单位项目
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存参建单位项目
	 */
	@RequiresPermissions("zhgd:hjConstructionProject:add")
	@Log(title = "参建单位项目", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjConstructionProject hjConstructionProject)
	{		
		return toAjax(hjConstructionProjectService.insertHjConstructionProject(hjConstructionProject));
	}

	/**
	 * 修改参建单位项目
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjConstructionProject hjConstructionProject = hjConstructionProjectService.selectHjConstructionProjectById(id);
		mmap.put("hjConstructionProject", hjConstructionProject);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存参建单位项目
	 */
	@RequiresPermissions("zhgd:hjConstructionProject:edit")
	@Log(title = "参建单位项目", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjConstructionProject hjConstructionProject)
	{		
		return toAjax(hjConstructionProjectService.updateHjConstructionProject(hjConstructionProject));
	}
	
	/**
	 * 删除参建单位项目
	 */
	@RequiresPermissions("zhgd:hjConstructionProject:remove")
	@Log(title = "参建单位项目", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjConstructionProjectService.deleteHjConstructionProjectByIds(ids));
	}
	
}
