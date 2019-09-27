package com.hujiang.project.zhgd.hjSystemUser.controller;

import com.hujiang.project.zhgd.hjCompanyLibrary.domain.HjCompanyLibrary;
import com.hujiang.project.zhgd.hjCompanyLibrary.service.IHjCompanyLibraryService;
import com.hujiang.project.zhgd.hjCompanyUser.domain.HjCompanyUser;
import com.hujiang.project.zhgd.hjCompanyUser.service.IHjCompanyUserService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectUser.domain.HjProjectUser;
import com.hujiang.project.zhgd.hjProjectUser.service.IHjProjectUserService;
import com.hujiang.project.zhgd.hjSystemRole.domain.HjSystemRole;
import com.hujiang.project.zhgd.hjSystemRole.service.IHjSystemRoleService;
import com.hujiang.project.zhgd.hjUserRole.domain.HjUserRole;
import com.hujiang.project.zhgd.hjUserRole.service.IHjUserRoleService;
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
import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;
import com.hujiang.project.zhgd.hjSystemUser.service.IHjSystemUserService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 系统用户 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjSystemUser")
public class HjSystemUserController extends BaseController
{
    private String prefix = "zhgd/hjSystemUser";
	
	@Autowired
	private IHjSystemUserService hjSystemUserService;
	@Autowired
	private IHjCompanyLibraryService hjCompanyLibraryService;
	@Autowired
	private IHjCompanyUserService hjCompanyUserService;
	@Autowired
	private IHjProjectService hjProjectService;			//项目
	@Autowired
	private IHjProjectUserService hjProjectUserService;
	@Autowired
	private IHjSystemRoleService hjSystemRoleService;
	@Autowired
	private IHjUserRoleService hjUserRoleService;

	
	@RequiresPermissions("zhgd:hjSystemUser:view")
	@GetMapping()
	public String hjSystemUser()
	{
	    return prefix + "/hjSystemUser";
	}
	
	/**
	 * 查询系统用户列表
	 */
	@RequiresPermissions("zhgd:hjSystemUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjSystemUser hjSystemUser)
	{
		startPage();
        List<HjSystemUser> list = hjSystemUserService.selectHjSystemUserList(hjSystemUser);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出系统用户列表
	 */
	@RequiresPermissions("zhgd:hjSystemUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjSystemUser hjSystemUser)
    {
    	List<HjSystemUser> list = hjSystemUserService.selectHjSystemUserList(hjSystemUser);
        ExcelUtil<HjSystemUser> util = new ExcelUtil<HjSystemUser>(HjSystemUser.class);
        return util.exportExcel(list, "hjSystemUser");
    }
	
	/**
	 * 新增系统用户
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		mmap.put("company",hjCompanyLibraryService.selectHjCompanyLibraryList(null));
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存系统用户
	 */
	@RequiresPermissions("zhgd:hjSystemUser:add")
	@Log(title = "集团（公司）用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjSystemUser hjSystemUser, Integer cid,Integer rid)
	{	//添加用户返回主键
		int uid = hjSystemUserService.insertHjSystemUser(hjSystemUser);
		if(hjSystemUser.getUserType() == 2){
			HjProjectUser hjProjectUser = new HjProjectUser();
			hjProjectUser.setProjectId(cid);
			hjProjectUser.setUserId(hjSystemUser.getId());
			hjProjectUserService.insertHjProjectUser(hjProjectUser);
		}else {
			if(uid>0){
				HjCompanyUser hjCompanyUser = new HjCompanyUser();
				hjCompanyUser.setCompanyId(cid);
				hjCompanyUser.setUserId(hjSystemUser.getId());
				//添加关系表
				return toAjax(hjCompanyUserService.insertHjCompanyUser(hjCompanyUser));
			}
		}
		HjUserRole hjUserRole = new HjUserRole();
		hjUserRole.setUserId(hjSystemUser.getId());
		hjUserRole.setRoleId(rid);
		hjUserRoleService.insertHjUserRole(hjUserRole);

		return toAjax(uid);
	}

	/**
	 * 修改系统用户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjSystemUser hjSystemUser = hjSystemUserService.selectHjSystemUserById(id);
		if(hjSystemUser.getUserType() == 0){
			mmap.put("hjSystemUser", hjSystemUser);
			mmap.put("company",hjCompanyLibraryService.selectHjCompanyLibraryList(null));
			HjCompanyUser hjCompanyUser = new HjCompanyUser();
			hjCompanyUser.setUserId(id);
			mmap.put("cid",hjCompanyUserService.selectHjCompanyUserList(hjCompanyUser).get(0).getCompanyId());
		}else if(hjSystemUser.getUserType() == 1){
			mmap.put("hjSystemUser", hjSystemUser);
			mmap.put("company",hjCompanyLibraryService.selectHjCompanyLibraryList(null));
			HjCompanyUser hjCompanyUser = new HjCompanyUser();
			hjCompanyUser.setUserId(id);
			mmap.put("cid",hjCompanyUserService.selectHjCompanyUserList(hjCompanyUser).get(0).getCompanyId());
		}else {

			List<HjCompanyLibrary> company = new ArrayList<>();
			List<HjProject> list = hjProjectService.selectHjProjectList(null);

			for (int i = 0; i < list.size(); i++){
				HjCompanyLibrary hjCompanyLibrary = new HjCompanyLibrary();
				hjCompanyLibrary.setId(list.get(i).getId());
				hjCompanyLibrary.setCompanyName(list.get(i).getProjectName());
				company.add(hjCompanyLibrary);
			}
			mmap.put("hjSystemUser", hjSystemUser);
			mmap.put("company",company);
			HjProjectUser hjProjectUser = new HjProjectUser();
			hjProjectUser.setUserId(id);
			mmap.put("cid",hjProjectUserService.selectHjProjectUserList(hjProjectUser).get(0).getProjectId());
		}

	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存系统用户
	 */
	@RequiresPermissions("zhgd:hjSystemUser:edit")
	@Log(title = "集团（公司）用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjSystemUser hjSystemUser)
	{
		hjSystemUser.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return toAjax(hjSystemUserService.updateHjSystemUser(hjSystemUser));
	}
	
	/**
	 * 删除系统用户
	 */
	@RequiresPermissions("zhgd:hjSystemUser:remove")
	@Log(title = "集团（公司）用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{

		HjSystemUser hjSystemUser = hjSystemUserService.selectHjSystemUserById(Integer.parseInt(ids));

		if(hjSystemUser.getUserType() == 2){ // 项目
			Integer id = Integer.parseInt(ids);
			hjProjectUserService.deleteHjProjectUserIds(id);
		}else{
			Integer id = Integer.parseInt(ids);
			hjCompanyUserService.deleteHjCompanyUserId(id);
		}


		return toAjax(hjSystemUserService.deleteHjSystemUserByIds(ids));
	}



	/**
	 * 新增是选择项目，公司或者集团
	 */
	@GetMapping("/selectCompany")
	@ResponseBody
	public List<HjCompanyLibrary> selectCompany(Integer type)
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


	/**
	 * 新增是选择项目，公司或者集团
	 */
	@GetMapping("/selectRoleName")
	@ResponseBody
	public List<HjSystemRole> selectRoleName(Integer type, Integer cid)
	{
		HjSystemRole hjSystemRole = new HjSystemRole();
		hjSystemRole.setBelong(cid);
		hjSystemRole.setHierarchy(type);
		List<HjSystemRole> systemRoleList = hjSystemRoleService.selectHjSystemRoleList(hjSystemRole);
		return systemRoleList;
	}











}
