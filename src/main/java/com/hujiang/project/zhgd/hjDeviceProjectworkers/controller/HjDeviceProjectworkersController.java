package com.hujiang.project.zhgd.hjDeviceProjectworkers.controller;

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
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 考勤设备人员 信息操作处理
 * 
 * @author hujiang
 * @date 2019-08-08
 */
@Controller
@RequestMapping("/zhgd/hjDeviceProjectworkers")
public class HjDeviceProjectworkersController extends BaseController
{
    private String prefix = "zhgd/hjDeviceProjectworkers";
	
	@Autowired
	private IHjDeviceProjectworkersService hjDeviceProjectworkersService;
	
	@RequiresPermissions("zhgd:hjDeviceProjectworkers:view")
	@GetMapping()
	public String hjDeviceProjectworkers()
	{
	    return prefix + "/hjDeviceProjectworkers";
	}
	
	/**
	 * 查询考勤设备人员列表
	 */
	@RequiresPermissions("zhgd:hjDeviceProjectworkers:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjDeviceProjectworkers hjDeviceProjectworkers)
	{
		startPage();
        List<HjDeviceProjectworkers> list = hjDeviceProjectworkersService.selectHjDeviceProjectworkersList(hjDeviceProjectworkers);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出考勤设备人员列表
	 */
	@RequiresPermissions("zhgd:hjDeviceProjectworkers:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjDeviceProjectworkers hjDeviceProjectworkers)
    {
    	List<HjDeviceProjectworkers> list = hjDeviceProjectworkersService.selectHjDeviceProjectworkersList(hjDeviceProjectworkers);
        ExcelUtil<HjDeviceProjectworkers> util = new ExcelUtil<HjDeviceProjectworkers>(HjDeviceProjectworkers.class);
        return util.exportExcel(list, "hjDeviceProjectworkers");
    }
	
	/**
	 * 新增考勤设备人员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存考勤设备人员
	 */
	@RequiresPermissions("zhgd:hjDeviceProjectworkers:add")
	@Log(title = "考勤设备人员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjDeviceProjectworkers hjDeviceProjectworkers)
	{		
		return toAjax(hjDeviceProjectworkersService.insertHjDeviceProjectworkers(hjDeviceProjectworkers));
	}

	/**
	 * 修改考勤设备人员
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjDeviceProjectworkers hjDeviceProjectworkers = hjDeviceProjectworkersService.selectHjDeviceProjectworkersById(id);
		mmap.put("hjDeviceProjectworkers", hjDeviceProjectworkers);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存考勤设备人员
	 */
	@RequiresPermissions("zhgd:hjDeviceProjectworkers:edit")
	@Log(title = "考勤设备人员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjDeviceProjectworkers hjDeviceProjectworkers)
	{		
		return toAjax(hjDeviceProjectworkersService.updateHjDeviceProjectworkers(hjDeviceProjectworkers));
	}
	
	/**
	 * 删除考勤设备人员
	 */
	@RequiresPermissions("zhgd:hjDeviceProjectworkers:remove")
	@Log(title = "考勤设备人员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjDeviceProjectworkersService.deleteHjDeviceProjectworkersByIds(ids));
	}
	
}
