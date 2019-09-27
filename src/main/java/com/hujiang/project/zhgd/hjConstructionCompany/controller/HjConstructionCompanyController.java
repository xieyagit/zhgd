package com.hujiang.project.zhgd.hjConstructionCompany.controller;

import com.hujiang.project.zhgd.hjConstructionProject.domain.HjConstructionProject;
import com.hujiang.project.zhgd.hjConstructionProject.service.IHjConstructionProjectService;
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.utils.CurrentTime;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
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
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 参建单位 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjConstructionCompany")
public class HjConstructionCompanyController extends BaseController
{
    private String prefix = "zhgd/hjConstructionCompany";
	
	@Autowired
	private IHjConstructionCompanyService hjConstructionCompanyService;
	@Autowired
	private IHjDictionariesService hjDictionariesService;
	@Autowired
	private IHjConstructionProjectService hjConstructionProjectService;//参建单位项目

	@Autowired
	private IHjProjectService hjProjectService;			//项目

	
	@RequiresPermissions("zhgd:hjConstructionCompany:view")
	@GetMapping()
	public String hjConstructionCompany(){
	    return prefix + "/hjConstructionCompany";
	}
	
	/**
	 * 查询参建单位列表
	 */
	@RequiresPermissions("zhgd:hjConstructionCompany:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjConstructionCompany hjConstructionCompany)
	{
		startPage();
        List<HjConstructionCompany> list = hjConstructionCompanyService.selectHjConstructionCompanyList(hjConstructionCompany);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出参建单位列表
	 */
	@RequiresPermissions("zhgd:hjConstructionCompany:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjConstructionCompany hjConstructionCompany)
    {
    	List<HjConstructionCompany> list = hjConstructionCompanyService.selectHjConstructionCompanyList(hjConstructionCompany);
        ExcelUtil<HjConstructionCompany> util = new ExcelUtil<HjConstructionCompany>(HjConstructionCompany.class);
        return util.exportExcel(list, "hjConstructionCompany");
    }
	
	/**
	 * 新增参建单位
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		HjDictionaries hjDictionaries = new HjDictionaries();
		hjDictionaries.setCategory("UNIT_TYPE");
		mmap.put("nuit",hjDictionariesService.selectHjDictionariesList(hjDictionaries));
		mmap.put("project",hjProjectService.selectHjProjectList(null));
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存参建单位
	 */
	@RequiresPermissions("zhgd:hjConstructionCompany:add")
	@Log(title = "参建单位", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjConstructionCompany hjConstructionCompany,Integer pid)
	{
		//添加参建单位
		int i = hjConstructionCompanyService.insertHjConstructionCompany(hjConstructionCompany);
		if(i>0){//添加参建单位成功
			//参建单位项目关系
			HjConstructionProject hjConstructionProject = new HjConstructionProject();
			hjConstructionProject.setProjectId(pid);
			hjConstructionProject.setConstructionId(hjConstructionCompany.getId());
			return toAjax(hjConstructionProjectService.insertHjConstructionProject(hjConstructionProject));
		}
		return toAjax(0);
	}

	/**
	 * 修改参建单位
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjConstructionCompany hjConstructionCompany = hjConstructionCompanyService.selectHjConstructionCompanyById(id);
		mmap.put("hjConstructionCompany", hjConstructionCompany);
		HjDictionaries hjDictionaries = new HjDictionaries();
		hjDictionaries.setCategory("UNIT_TYPE");
		mmap.put("nuit",hjDictionariesService.selectHjDictionariesList(hjDictionaries));
		//参建单位项目关系
		HjConstructionProject hjConstructionProject = new HjConstructionProject();
		hjConstructionProject.setConstructionId(id);
		Integer projectId = hjConstructionProjectService.selectHjConstructionProjectList(hjConstructionProject).get(0).getProjectId();

		mmap.put("project",hjProjectService.selectHjProjectById(projectId));
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存参建单位
	 */
	@RequiresPermissions("zhgd:hjConstructionCompany:edit")
	@Log(title = "参建单位", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjConstructionCompany hjConstructionCompany)
	{
		hjConstructionCompany.setUpdateDate(CurrentTime.getCurrentTime());
		return toAjax(hjConstructionCompanyService.updateHjConstructionCompany(hjConstructionCompany));
	}
	
	/**
	 * 删除参建单位
	 */
	@RequiresPermissions("zhgd:hjConstructionCompany:remove")
	@Log(title = "参建单位", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjConstructionCompanyService.deleteHjConstructionCompanyByIds(ids));
	}
	
}
