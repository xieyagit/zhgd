package com.hujiang.project.zhgd.sbApiFaceInformation.controller;

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
import com.hujiang.project.zhgd.sbApiFaceInformation.domain.SbApiFaceInformation;
import com.hujiang.project.zhgd.sbApiFaceInformation.service.ISbApiFaceInformationService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 考勤人脸 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/zhgd/sbApiFaceInformation")
public class SbApiFaceInformationController extends BaseController
{
    private String prefix = "zhgd/sbApiFaceInformation";
	
	@Autowired
	private ISbApiFaceInformationService sbApiFaceInformationService;
	
	@RequiresPermissions("zhgd:sbApiFaceInformation:view")
	@GetMapping()
	public String sbApiFaceInformation()
	{
	    return prefix + "/sbApiFaceInformation";
	}
	
	/**
	 * 查询考勤人脸列表
	 */
	@RequiresPermissions("zhgd:sbApiFaceInformation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbApiFaceInformation sbApiFaceInformation)
	{
		startPage();
        List<SbApiFaceInformation> list = sbApiFaceInformationService.selectSbApiFaceInformationList(sbApiFaceInformation);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出考勤人脸列表
	 */
	@RequiresPermissions("zhgd:sbApiFaceInformation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbApiFaceInformation sbApiFaceInformation)
    {
    	List<SbApiFaceInformation> list = sbApiFaceInformationService.selectSbApiFaceInformationList(sbApiFaceInformation);
        ExcelUtil<SbApiFaceInformation> util = new ExcelUtil<SbApiFaceInformation>(SbApiFaceInformation.class);
        return util.exportExcel(list, "sbApiFaceInformation");
    }
	
	/**
	 * 新增考勤人脸
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存考勤人脸
	 */
	@RequiresPermissions("zhgd:sbApiFaceInformation:add")
	@Log(title = "考勤人脸", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbApiFaceInformation sbApiFaceInformation)
	{		
		return toAjax(sbApiFaceInformationService.insertSbApiFaceInformation(sbApiFaceInformation));
	}

	/**
	 * 修改考勤人脸
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbApiFaceInformation sbApiFaceInformation = sbApiFaceInformationService.selectSbApiFaceInformationById(id);
		mmap.put("sbApiFaceInformation", sbApiFaceInformation);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存考勤人脸
	 */
	@RequiresPermissions("zhgd:sbApiFaceInformation:edit")
	@Log(title = "考勤人脸", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbApiFaceInformation sbApiFaceInformation)
	{		
		return toAjax(sbApiFaceInformationService.updateSbApiFaceInformation(sbApiFaceInformation));
	}
	
	/**
	 * 删除考勤人脸
	 */
	@RequiresPermissions("zhgd:sbApiFaceInformation:remove")
	@Log(title = "考勤人脸", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbApiFaceInformationService.deleteSbApiFaceInformationByIds(ids));
	}
	
}
