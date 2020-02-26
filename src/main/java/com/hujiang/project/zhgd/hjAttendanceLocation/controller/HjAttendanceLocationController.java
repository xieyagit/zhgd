package com.hujiang.project.zhgd.hjAttendanceLocation.controller;

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
import com.hujiang.project.zhgd.hjAttendanceLocation.domain.HjAttendanceLocation;
import com.hujiang.project.zhgd.hjAttendanceLocation.service.IHjAttendanceLocationService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 打卡定位 信息操作处理
 * 
 * @author hujiang
 * @date 2020-02-26
 */
@Controller
@RequestMapping("/zhgd/hjAttendanceLocation")
public class HjAttendanceLocationController extends BaseController
{
    private String prefix = "zhgd/hjAttendanceLocation";
	
	@Autowired
	private IHjAttendanceLocationService hjAttendanceLocationService;
	
	@RequiresPermissions("zhgd:hjAttendanceLocation:view")
	@GetMapping()
	public String hjAttendanceLocation()
	{
	    return prefix + "/hjAttendanceLocation";
	}
	
	/**
	 * 查询打卡定位列表
	 */
	@RequiresPermissions("zhgd:hjAttendanceLocation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjAttendanceLocation hjAttendanceLocation)
	{
		startPage();
        List<HjAttendanceLocation> list = hjAttendanceLocationService.selectHjAttendanceLocationList(hjAttendanceLocation);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出打卡定位列表
	 */
	@RequiresPermissions("zhgd:hjAttendanceLocation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjAttendanceLocation hjAttendanceLocation)
    {
    	List<HjAttendanceLocation> list = hjAttendanceLocationService.selectHjAttendanceLocationList(hjAttendanceLocation);
        ExcelUtil<HjAttendanceLocation> util = new ExcelUtil<HjAttendanceLocation>(HjAttendanceLocation.class);
        return util.exportExcel(list, "hjAttendanceLocation");
    }
	
	/**
	 * 新增打卡定位
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存打卡定位
	 */
	@RequiresPermissions("zhgd:hjAttendanceLocation:add")
	@Log(title = "打卡定位", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjAttendanceLocation hjAttendanceLocation)
	{		
		return toAjax(hjAttendanceLocationService.insertHjAttendanceLocation(hjAttendanceLocation));
	}

	/**
	 * 修改打卡定位
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjAttendanceLocation hjAttendanceLocation = hjAttendanceLocationService.selectHjAttendanceLocationById(id);
		mmap.put("hjAttendanceLocation", hjAttendanceLocation);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存打卡定位
	 */
	@RequiresPermissions("zhgd:hjAttendanceLocation:edit")
	@Log(title = "打卡定位", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjAttendanceLocation hjAttendanceLocation)
	{		
		return toAjax(hjAttendanceLocationService.updateHjAttendanceLocation(hjAttendanceLocation));
	}
	
	/**
	 * 删除打卡定位
	 */
	@RequiresPermissions("zhgd:hjAttendanceLocation:remove")
	@Log(title = "打卡定位", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjAttendanceLocationService.deleteHjAttendanceLocationByIds(ids));
	}
	
}
