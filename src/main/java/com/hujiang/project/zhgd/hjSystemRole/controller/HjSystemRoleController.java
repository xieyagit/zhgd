package com.hujiang.project.zhgd.hjSystemRole.controller;

import com.hujiang.project.zhgd.hjCompanyLibrary.domain.HjCompanyLibrary;
import com.hujiang.project.zhgd.hjCompanyLibrary.service.IHjCompanyLibraryService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.hujiang.project.zhgd.hjSystemRole.domain.HjSystemRole;
import com.hujiang.project.zhgd.hjSystemRole.service.IHjSystemRoleService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 系统-角色 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjSystemRole")
public class HjSystemRoleController extends BaseController
{
    private String prefix = "zhgd/hjSystemRole";
	
	@Autowired
	private IHjSystemRoleService hjSystemRoleService;

	@Autowired
	private IHjProjectService hjProjectService;			//项目

	@Autowired
	private IHjCompanyLibraryService hjCompanyLibraryService;

	
	@RequiresPermissions("zhgd:hjSystemRole:view")
	@GetMapping()
	public String hjSystemRole()
	{
	    return prefix + "/hjSystemRole";
	}
	
	/**
	 * 查询系统-角色列表
	 */
	@RequiresPermissions("zhgd:hjSystemRole:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjSystemRole hjSystemRole)
	{
		startPage();
        List<HjSystemRole> list = hjSystemRoleService.selectHjSystemRoleList(hjSystemRole);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出系统-角色列表
	 */
	@RequiresPermissions("zhgd:hjSystemRole:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjSystemRole hjSystemRole)
    {
    	List<HjSystemRole> list = hjSystemRoleService.selectHjSystemRoleList(hjSystemRole);
        ExcelUtil<HjSystemRole> util = new ExcelUtil<HjSystemRole>(HjSystemRole.class);
        return util.exportExcel(list, "hjSystemRole");
    }
	
	/**
	 * 新增系统-角色
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		mmap.put("company",hjCompanyLibraryService.selectHjCompanyLibraryList(null));
		return prefix + "/add";
	}
	
	/**
	 * 新增保存系统-角色
	 */
	@RequiresPermissions("zhgd:hjSystemRole:add")
	@Log(title = "系统-角色", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjSystemRole hjSystemRole)
	{
		hjSystemRole.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return toAjax(hjSystemRoleService.insertHjSystemRole(hjSystemRole));
	}

	/**
	 * 修改系统-角色
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjSystemRole hjSystemRole = hjSystemRoleService.selectHjSystemRoleById(id);
		mmap.put("hjSystemRole", hjSystemRole);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存系统-角色
	 */
	@RequiresPermissions("zhgd:hjSystemRole:edit")
	@Log(title = "系统-角色", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjSystemRole hjSystemRole)
	{		hjSystemRole.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return toAjax(hjSystemRoleService.updateHjSystemRole(hjSystemRole));
	}
	
	/**
	 * 删除系统-角色
	 */
	@RequiresPermissions("zhgd:hjSystemRole:remove")
	@Log(title = "系统-角色", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjSystemRoleService.deleteHjSystemRoleByIds(ids));
	}



	/**
	 * 新增是选择项目，公司或者集团
	 */
	@GetMapping("/selectCompanyOnt")
	@ResponseBody
	public List<HjCompanyLibrary> selectCompanyOnt(Integer type)
	{
		List<HjCompanyLibrary> company = new ArrayList<>();
		if(type == 0){ // 集团
			company = hjCompanyLibraryService.selectHjCompanyLibraryList(null);
		}else if(type == 1){
			company =hjCompanyLibraryService.selectHjCompanyLibraryList(null);
		}else{
			List<HjProject> list = hjProjectService.selectHjProjectList(null);

			for (int i = 0; i < list.size(); i++){
				HjCompanyLibrary hjCompanyLibrary = new HjCompanyLibrary();
				hjCompanyLibrary.setId(list.get(i).getId());
				hjCompanyLibrary.setCompanyName(list.get(i).getProjectName());
				company.add(hjCompanyLibrary);
			}
		}

		return company;
	}
	
}
