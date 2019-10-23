package com.hujiang.project.zhgd.sbCamera.controller;

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
import com.hujiang.project.zhgd.sbCamera.domain.SbCamera;
import com.hujiang.project.zhgd.sbCamera.service.ISbCameraService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * AI摄像头客户 信息操作处理
 * 
 * @author hujiang
 * @date 2019-10-15
 */
@Controller
@RequestMapping("/zhgd/sbCamera")
public class SbCameraController extends BaseController
{
    private String prefix = "zhgd/sbCamera";
	
	@Autowired
	private ISbCameraService sbCameraService;
	
	@RequiresPermissions("zhgd:sbCamera:view")
	@GetMapping()
	public String sbCamera()
	{
	    return prefix + "/sbCamera";
	}
	
	/**
	 * 查询AI摄像头客户列表
	 */
	@RequiresPermissions("zhgd:sbCamera:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbCamera sbCamera)
	{
		startPage();
        List<SbCamera> list = sbCameraService.selectSbCameraList(sbCamera);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出AI摄像头客户列表
	 */
	@RequiresPermissions("zhgd:sbCamera:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbCamera sbCamera)
    {
    	List<SbCamera> list = sbCameraService.selectSbCameraList(sbCamera);
        ExcelUtil<SbCamera> util = new ExcelUtil<SbCamera>(SbCamera.class);
        return util.exportExcel(list, "sbCamera");
    }
	
	/**
	 * 新增AI摄像头客户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存AI摄像头客户
	 */
	@RequiresPermissions("zhgd:sbCamera:add")
	@Log(title = "AI摄像头客户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbCamera sbCamera)
	{		
		return toAjax(sbCameraService.insertSbCamera(sbCamera));
	}

	/**
	 * 修改AI摄像头客户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbCamera sbCamera = sbCameraService.selectSbCameraById(id);
		mmap.put("sbCamera", sbCamera);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存AI摄像头客户
	 */
	@RequiresPermissions("zhgd:sbCamera:edit")
	@Log(title = "AI摄像头客户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbCamera sbCamera)
	{		
		return toAjax(sbCameraService.updateSbCamera(sbCamera));
	}
	
	/**
	 * 删除AI摄像头客户
	 */
	@RequiresPermissions("zhgd:sbCamera:remove")
	@Log(title = "AI摄像头客户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbCameraService.deleteSbCameraByIds(ids));
	}
	
}
