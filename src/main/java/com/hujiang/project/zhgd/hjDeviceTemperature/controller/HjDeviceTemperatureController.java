package com.hujiang.project.zhgd.hjDeviceTemperature.controller;

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
import com.hujiang.project.zhgd.hjDeviceTemperature.domain.HjDeviceTemperature;
import com.hujiang.project.zhgd.hjDeviceTemperature.service.IHjDeviceTemperatureService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 人脸设备和测温设备绑定 信息操作处理
 * 
 * @author hujiang
 * @date 2020-03-05
 */
@Controller
@RequestMapping("/zhgd/hjDeviceTemperature")
public class HjDeviceTemperatureController extends BaseController
{
    private String prefix = "zhgd/hjDeviceTemperature";
	
	@Autowired
	private IHjDeviceTemperatureService hjDeviceTemperatureService;
	
	@RequiresPermissions("zhgd:hjDeviceTemperature:view")
	@GetMapping()
	public String hjDeviceTemperature()
	{
	    return prefix + "/hjDeviceTemperature";
	}
	
	/**
	 * 查询人脸设备和测温设备绑定列表
	 */
	@RequiresPermissions("zhgd:hjDeviceTemperature:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjDeviceTemperature hjDeviceTemperature)
	{
		startPage();
        List<HjDeviceTemperature> list = hjDeviceTemperatureService.selectHjDeviceTemperatureList(hjDeviceTemperature);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出人脸设备和测温设备绑定列表
	 */
	@RequiresPermissions("zhgd:hjDeviceTemperature:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjDeviceTemperature hjDeviceTemperature)
    {
    	List<HjDeviceTemperature> list = hjDeviceTemperatureService.selectHjDeviceTemperatureList(hjDeviceTemperature);
        ExcelUtil<HjDeviceTemperature> util = new ExcelUtil<HjDeviceTemperature>(HjDeviceTemperature.class);
        return util.exportExcel(list, "hjDeviceTemperature");
    }
	
	/**
	 * 新增人脸设备和测温设备绑定
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存人脸设备和测温设备绑定
	 */
	@RequiresPermissions("zhgd:hjDeviceTemperature:add")
	@Log(title = "人脸设备和测温设备绑定", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjDeviceTemperature hjDeviceTemperature)
	{		
		return toAjax(hjDeviceTemperatureService.insertHjDeviceTemperature(hjDeviceTemperature));
	}

	/**
	 * 修改人脸设备和测温设备绑定
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjDeviceTemperature hjDeviceTemperature = hjDeviceTemperatureService.selectHjDeviceTemperatureById(id);
		mmap.put("hjDeviceTemperature", hjDeviceTemperature);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存人脸设备和测温设备绑定
	 */
	@RequiresPermissions("zhgd:hjDeviceTemperature:edit")
	@Log(title = "人脸设备和测温设备绑定", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjDeviceTemperature hjDeviceTemperature)
	{		
		return toAjax(hjDeviceTemperatureService.updateHjDeviceTemperature(hjDeviceTemperature));
	}
	
	/**
	 * 删除人脸设备和测温设备绑定
	 */
	@RequiresPermissions("zhgd:hjDeviceTemperature:remove")
	@Log(title = "人脸设备和测温设备绑定", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjDeviceTemperatureService.deleteHjDeviceTemperatureByIds(ids));
	}
	
}
