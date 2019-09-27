package com.hujiang.project.zhgd.hjCompanyProject.controller;

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
import com.hujiang.project.zhgd.hjCompanyProject.domain.HjCompanyProject;
import com.hujiang.project.zhgd.hjCompanyProject.service.IHjCompanyProjectService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 公司项目 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjCompanyProject")
public class HjCompanyProjectController extends BaseController
{
    private String prefix = "zhgd/hjCompanyProject";
	
	@Autowired
	private IHjCompanyProjectService hjCompanyProjectService;
	
	@RequiresPermissions("zhgd:hjCompanyProject:view")
	@GetMapping()
	public String hjCompanyProject()
	{
	    return prefix + "/hjCompanyProject";
	}
	
	/**
	 * 查询公司项目列表
	 */
	@RequiresPermissions("zhgd:hjCompanyProject:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjCompanyProject hjCompanyProject)
	{
		startPage();
        List<HjCompanyProject> list = hjCompanyProjectService.selectHjCompanyProjectList(hjCompanyProject);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出公司项目列表
	 */
	@RequiresPermissions("zhgd:hjCompanyProject:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjCompanyProject hjCompanyProject)
    {
    	List<HjCompanyProject> list = hjCompanyProjectService.selectHjCompanyProjectList(hjCompanyProject);
        ExcelUtil<HjCompanyProject> util = new ExcelUtil<HjCompanyProject>(HjCompanyProject.class);
        return util.exportExcel(list, "hjCompanyProject");
    }
	
	/**
	 * 新增公司项目
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存公司项目
	 */
	@RequiresPermissions("zhgd:hjCompanyProject:add")
	@Log(title = "公司项目", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjCompanyProject hjCompanyProject)
	{		
		return toAjax(hjCompanyProjectService.insertHjCompanyProject(hjCompanyProject));
	}

	/**
	 * 修改公司项目
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjCompanyProject hjCompanyProject = hjCompanyProjectService.selectHjCompanyProjectById(id);
		mmap.put("hjCompanyProject", hjCompanyProject);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存公司项目
	 */
	@RequiresPermissions("zhgd:hjCompanyProject:edit")
	@Log(title = "公司项目", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjCompanyProject hjCompanyProject)
	{		
		return toAjax(hjCompanyProjectService.updateHjCompanyProject(hjCompanyProject));
	}
	
	/**
	 * 删除公司项目
	 */
	@RequiresPermissions("zhgd:hjCompanyProject:remove")
	@Log(title = "公司项目", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjCompanyProjectService.deleteHjCompanyProjectByIds(ids));
	}
	
}
