package com.hujiang.project.zhgd.hjProject.controller;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjArea.domain.HjArea;
import com.hujiang.project.zhgd.hjArea.service.IHjAreaService;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjCompanyLibrary.domain.HjCompanyLibrary;
import com.hujiang.project.zhgd.hjCompanyLibrary.service.IHjCompanyLibraryService;
import com.hujiang.project.zhgd.hjCompanyProject.domain.HjCompanyProject;
import com.hujiang.project.zhgd.hjCompanyProject.service.IHjCompanyProjectService;
import com.hujiang.project.zhgd.hjConstructionProject.service.IHjConstructionProjectService;
import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.project.zhgd.hjProject.domain.HjCompanyProjectTemp;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import com.hujiang.project.zhgd.utils.CurrentTime;
import com.jhlabs.image.MedianFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

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
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjProject")
public class HjProjectController extends BaseController
{
    private String prefix = "zhgd/hjProject";
	
	@Autowired
	private IHjProjectService hjProjectService;			//项目
	@Autowired
	private IHjCompanyLibraryService hjCompanyLibraryService;	//公司
	@Autowired
	private IHjCompanyProjectService hjCompanyProjectService;//公司项目
	@Autowired
	private IHjDictionariesService hjDictionariesService;	//字典
	@Autowired
	private IHjAreaService hjAreaService;	//城市



	
	@RequiresPermissions("zhgd:hjProject:view")
	@GetMapping()
	public String hjProject()
	{
	    return prefix + "/hjProject";
	}
	
	/**
	 * 查询项目列表
	 */
	@RequiresPermissions("zhgd:hjProject:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjProject hjProject) {
		startPage();
        List<HjProject> list = hjProjectService.selectHjProjectList(hjProject);
		return getDataTable(list);
	}

	/**
	 * 导出项目列表
	 */
	@RequiresPermissions("zhgd:hjProject:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjProject hjProject)
    {
    	List<HjProject> list = hjProjectService.selectHjProjectList(hjProject);
        ExcelUtil<HjProject> util = new ExcelUtil<HjProject>(HjProject.class);
        return util.exportExcel(list, "hjProject");
    }
	
	/**
	 * 新增项目
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		//字典
		HjDictionaries hjDictionaries = new HjDictionaries();
		//项目状态
		hjDictionaries.setCategory("PROJECT_STATE");
		mmap.put("state",hjDictionariesService.selectHjDictionariesList(hjDictionaries));

		//项目类型
		hjDictionaries.setCategory("PROJECT_TYPE");
		mmap.put("type",hjDictionariesService.selectHjDictionariesList(hjDictionaries));

		//结构类型
		hjDictionaries.setCategory("STRUCTURE_TYPE");
		mmap.put("structure",hjDictionariesService.selectHjDictionariesList(hjDictionaries));

		//公司列表
		mmap.put("company",hjCompanyLibraryService.selectHjCompanyLibraryList(null));

		//监理企业
		HjCompanyLibrary companyLibrary = new HjCompanyLibrary();
		companyLibrary.setCompanyType(2);//监理ID
		mmap.put("supervisor",hjCompanyLibraryService.selectHjCompanyLibraryList(companyLibrary));

		//总包单位
		companyLibrary.setCompanyType(4);//总包ID
		mmap.put("main",hjCompanyLibraryService.selectHjCompanyLibraryList(companyLibrary));

		//城市
		HjArea area = new HjArea();
		area.setType("PROVINCE");//省份
		mmap.put("area",hjAreaService.selectHjAreaList(area));

	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目
	 */
	@RequiresPermissions("zhgd:hjProject:add")
	@Log(title = "项目", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjProject hjProject, Integer cid, MultipartFile file)throws Exception
	{
		System.out.println(hjProject);

		if (file != null&&!file.isEmpty()) {
			//上传图片到oss服务器
			String url = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", hjProject.getShortName() + System.currentTimeMillis() + ".jpg");
			hjProject.setProjectImage(url.substring(0,url.indexOf("?")));
		}


		int i = hjProjectService.insertHjProject(hjProject);
		if(i>0){
			HjCompanyProject hjCompanyProject = new HjCompanyProject();
			hjCompanyProject.setCompanyId(cid);
			hjCompanyProject.setProjectId(hjProject.getId());
			return toAjax(hjCompanyProjectService.insertHjCompanyProject(hjCompanyProject));
		}
		return toAjax(i);
	}

	/**
	 * 修改项目
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjProject hjProject = hjProjectService.selectHjProjectById(id);
		mmap.put("hjProject", hjProject);

		HjCompanyProject hjCompanyProject = new HjCompanyProject();
		hjCompanyProject.setProjectId(id);
		mmap.put("cid",hjCompanyProjectService.selectHjCompanyProjectList(hjCompanyProject).get(0).getCompanyId());

		//字典
		HjDictionaries hjDictionaries = new HjDictionaries();
		//项目状态
		hjDictionaries.setCategory("PROJECT_STATE");
		mmap.put("state",hjDictionariesService.selectHjDictionariesList(hjDictionaries));

		//项目类型
		hjDictionaries.setCategory("PROJECT_TYPE");
		mmap.put("type",hjDictionariesService.selectHjDictionariesList(hjDictionaries));

		//结构类型
		hjDictionaries.setCategory("STRUCTURE_TYPE");
		mmap.put("structure",hjDictionariesService.selectHjDictionariesList(hjDictionaries));

		//公司列表
		mmap.put("company",hjCompanyLibraryService.selectHjCompanyLibraryList(null));

		//监理企业
		HjCompanyLibrary companyLibrary = new HjCompanyLibrary();
		companyLibrary.setCompanyType(2);//监理ID
		mmap.put("supervisor",hjCompanyLibraryService.selectHjCompanyLibraryList(companyLibrary));

		//总包单位
		companyLibrary.setCompanyType(4);//总包ID
		mmap.put("main",hjCompanyLibraryService.selectHjCompanyLibraryList(companyLibrary));

		//城市
		HjArea area = new HjArea();
		String[] split = hjProject.getProjectRegion().split(",");
		mmap.put("areaid",Integer.parseInt(split[0]));
		mmap.put("cityid",Integer.parseInt(split[1]));
		mmap.put("countyid",Integer.parseInt(split[2]));

		area.setType("PROVINCE");//省份
		mmap.put("area",hjAreaService.selectHjAreaList(area));

		area.setType("CITY");//城市
		area.setParentId(Long.parseLong(split[0]));
		mmap.put("city",hjAreaService.selectHjAreaList(area));//获取省份城市

		area.setType("DISTRICT");//县
		area.setParentId(Long.parseLong(split[1]));//获取城市县
		mmap.put("county",hjAreaService.selectHjAreaList(area));
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目
	 */
	@RequiresPermissions("zhgd:hjProject:edit")
	@Log(title = "项目", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjProject hjProject, MultipartFile file)throws Exception
	{
		System.out.println(hjProject);
		if (file != null&&!file.isEmpty()) {
			//删除原图片
			AliyunOSSClientUtil.deleteFile(AliyunOSSClientUtil.getOSSClient(), "hujiang", hjProject.getProjectImage().substring(hjProject.getProjectImage().lastIndexOf("/") + 1, hjProject.getProjectImage().length()));

			//上传图片到oss服务器
			String url = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", hjProject.getShortName() + System.currentTimeMillis() + ".jpg");
			hjProject.setProjectImage(url);
		}
		hjProject.setUpdateDate(CurrentTime.getCurrentTime());
		return toAjax(hjProjectService.updateHjProject(hjProject));
	}

	/**
	 * 删除项目
	 */
	@RequiresPermissions("zhgd:hjProject:remove")
	@Log(title = "项目", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjProjectService.deleteHjProjectByIds(ids));
	}
	
}
