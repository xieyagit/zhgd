package com.hujiang.project.zhgd.sbCameraInformation.controller;

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
import com.hujiang.project.zhgd.sbCameraInformation.domain.SbCameraInformation;
import com.hujiang.project.zhgd.sbCameraInformation.service.ISbCameraInformationService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 海康摄像头报警记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-10-16
 */
@Controller
@RequestMapping("/zhgd/sbCameraInformation")
public class SbCameraInformationController extends BaseController
{
//    private String prefix = "zhgd/sbCameraInformation";
//
//	@Autowired
//	private ISbCameraInformationService sbCameraInformationService;
//
//	@RequiresPermissions("zhgd:sbCameraInformation:view")
//	@GetMapping()
//	public String sbCameraInformation()
//	{
//	    return prefix + "/sbCameraInformation";
//	}
//
//	/**
//	 * 查询海康摄像头报警记录列表
//	 */
//	@RequiresPermissions("zhgd:sbCameraInformation:list")
//	@PostMapping("/list")
//	@ResponseBody
//	public TableDataInfo list(SbCameraInformation sbCameraInformation)
//	{
//		startPage();
//        List<SbCameraInformation> list = sbCameraInformationService.selectSbCameraInformationList(sbCameraInformation);
//		return getDataTable(list);
//	}
//
//
//	/**
//	 * 导出海康摄像头报警记录列表
//	 */
//	@RequiresPermissions("zhgd:sbCameraInformation:export")
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(SbCameraInformation sbCameraInformation)
//    {
//    	List<SbCameraInformation> list = sbCameraInformationService.selectSbCameraInformationList(sbCameraInformation);
//        ExcelUtil<SbCameraInformation> util = new ExcelUtil<SbCameraInformation>(SbCameraInformation.class);
//        return util.exportExcel(list, "sbCameraInformation");
//    }
//
//	/**
//	 * 新增海康摄像头报警记录
//	 */
//	@GetMapping("/add")
//	public String add()
//	{
//	    return prefix + "/add";
//	}
//
//	/**
//	 * 新增保存海康摄像头报警记录
//	 */
//	@RequiresPermissions("zhgd:sbCameraInformation:add")
//	@Log(title = "海康摄像头报警记录", businessType = BusinessType.INSERT)
//	@PostMapping("/add")
//	@ResponseBody
//	public AjaxResult addSave(SbCameraInformation sbCameraInformation)
//	{
//		return toAjax(sbCameraInformationService.insertSbCameraInformation(sbCameraInformation));
//	}
//
//	/**
//	 * 修改海康摄像头报警记录
//	 */
//	@GetMapping("/edit/{id}")
//	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
//	{
//		SbCameraInformation sbCameraInformation = sbCameraInformationService.selectSbCameraInformationById(id);
//		mmap.put("sbCameraInformation", sbCameraInformation);
//	    return prefix + "/edit";
//	}
//
//	/**
//	 * 修改保存海康摄像头报警记录
//	 */
//	@RequiresPermissions("zhgd:sbCameraInformation:edit")
//	@Log(title = "海康摄像头报警记录", businessType = BusinessType.UPDATE)
//	@PostMapping("/edit")
//	@ResponseBody
//	public AjaxResult editSave(SbCameraInformation sbCameraInformation)
//	{
//		return toAjax(sbCameraInformationService.updateSbCameraInformation(sbCameraInformation));
//	}
//
//	/**
//	 * 删除海康摄像头报警记录
//	 */
//	@RequiresPermissions("zhgd:sbCameraInformation:remove")
//	@Log(title = "海康摄像头报警记录", businessType = BusinessType.DELETE)
//	@PostMapping( "/remove")
//	@ResponseBody
//	public AjaxResult remove(String ids)
//	{
//		return toAjax(sbCameraInformationService.deleteSbCameraInformationByIds(ids));
//	}
	
}
