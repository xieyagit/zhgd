package com.hujiang.project.zhgd.hjAttendanceDevice.controller;

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
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 考勤机设备 信息操作处理
 * 
 * @author hujiang
 * @date 2019-08-06
 */
@Controller
@RequestMapping("/zhgd/hjAttendanceDevice")
public class HjAttendanceDeviceController extends BaseController
{
    private String prefix = "zhgd/hjAttendanceDevice";
	
	@Autowired
	private IHjAttendanceDeviceService hjAttendanceDeviceService;
	
	@RequiresPermissions("zhgd:hjAttendanceDevice:view")
	@GetMapping()
	public String hjAttendanceDevice()
	{
	    return prefix + "/hjAttendanceDevice";
	}
	
	/**
	 * 查询考勤机设备列表
	 */
	@RequiresPermissions("zhgd:hjAttendanceDevice:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjAttendanceDevice hjAttendanceDevice)
	{
		startPage();
        List<HjAttendanceDevice> list = hjAttendanceDeviceService.selectHjAttendanceDeviceList(hjAttendanceDevice);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出考勤机设备列表
	 */
	@RequiresPermissions("zhgd:hjAttendanceDevice:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjAttendanceDevice hjAttendanceDevice)
    {
    	List<HjAttendanceDevice> list = hjAttendanceDeviceService.selectHjAttendanceDeviceList(hjAttendanceDevice);
        ExcelUtil<HjAttendanceDevice> util = new ExcelUtil<HjAttendanceDevice>(HjAttendanceDevice.class);
        return util.exportExcel(list, "hjAttendanceDevice");
    }
	
	/**
	 * 新增考勤机设备
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存考勤机设备
	 */
	@RequiresPermissions("zhgd:hjAttendanceDevice:add")
	@Log(title = "考勤机设备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjAttendanceDevice hjAttendanceDevice)
	{		
		return toAjax(hjAttendanceDeviceService.insertHjAttendanceDevice(hjAttendanceDevice));
	}

	/**
	 * 修改考勤机设备
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjAttendanceDevice hjAttendanceDevice = hjAttendanceDeviceService.selectHjAttendanceDeviceById(id);
		mmap.put("hjAttendanceDevice", hjAttendanceDevice);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存考勤机设备
	 */
	@RequiresPermissions("zhgd:hjAttendanceDevice:edit")
	@Log(title = "考勤机设备", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjAttendanceDevice hjAttendanceDevice)
	{		
		return toAjax(hjAttendanceDeviceService.updateHjAttendanceDevice(hjAttendanceDevice));
	}
	
	/**
	 * 删除考勤机设备
	 */
	@RequiresPermissions("zhgd:hjAttendanceDevice:remove")
	@Log(title = "考勤机设备", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjAttendanceDeviceService.deleteHjAttendanceDeviceByIds(ids));
	}
	
}
