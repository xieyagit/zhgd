package com.hujiang.project.zhgd.hjZhgdDriver.controller;

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
import com.hujiang.project.zhgd.hjZhgdDriver.domain.HjZhgdDriver;
import com.hujiang.project.zhgd.hjZhgdDriver.service.IHjZhgdDriverService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 车牌绑定司机 信息操作处理
 * 
 * @author hujiang
 * @date 2019-08-30
 */
@Controller
@RequestMapping("/zhgd/hjZhgdDriver")
public class HjZhgdDriverController extends BaseController
{
    private String prefix = "zhgd/hjZhgdDriver";
	
	@Autowired
	private IHjZhgdDriverService hjZhgdDriverService;
	
	@RequiresPermissions("zhgd:hjZhgdDriver:view")
	@GetMapping()
	public String hjZhgdDriver()
	{
	    return prefix + "/hjZhgdDriver";
	}
	
	/**
	 * 查询车牌绑定司机列表
	 */
	@RequiresPermissions("zhgd:hjZhgdDriver:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjZhgdDriver hjZhgdDriver)
	{
		startPage();
        List<HjZhgdDriver> list = hjZhgdDriverService.selectHjZhgdDriverList(hjZhgdDriver);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出车牌绑定司机列表
	 */
	@RequiresPermissions("zhgd:hjZhgdDriver:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjZhgdDriver hjZhgdDriver)
    {
    	List<HjZhgdDriver> list = hjZhgdDriverService.selectHjZhgdDriverList(hjZhgdDriver);
        ExcelUtil<HjZhgdDriver> util = new ExcelUtil<HjZhgdDriver>(HjZhgdDriver.class);
        return util.exportExcel(list, "hjZhgdDriver");
    }
	
	/**
	 * 新增车牌绑定司机
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存车牌绑定司机
	 */
//	@RequiresPermissions("zhgd:hjZhgdDriver:add")
//	@Log(title = "车牌绑定司机", businessType = BusinessType.INSERT)
//	@PostMapping("/add")
//	@ResponseBody
//	public AjaxResult addSave(HjZhgdDriver hjZhgdDriver)
//	{
//		return toAjax(hjZhgdDriverService.insertHjZhgdDriver(hjZhgdDriver));
//	}

	/**
	 * 修改车牌绑定司机
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjZhgdDriver hjZhgdDriver = hjZhgdDriverService.selectHjZhgdDriverById(id);
		mmap.put("hjZhgdDriver", hjZhgdDriver);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存车牌绑定司机
	 */
	@RequiresPermissions("zhgd:hjZhgdDriver:edit")
	@Log(title = "车牌绑定司机", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjZhgdDriver hjZhgdDriver)
	{		
		return toAjax(hjZhgdDriverService.updateHjZhgdDriver(hjZhgdDriver));
	}
	
	/**
	 * 删除车牌绑定司机
	 */
//	@RequiresPermissions("zhgd:hjZhgdDriver:remove")
//	@Log(title = "车牌绑定司机", businessType = BusinessType.DELETE)
//	@PostMapping( "/remove")
//	@ResponseBody
//	public AjaxResult remove(String ids)
//	{
//		return toAjax(hjZhgdDriverService.deleteHjZhgdDriverByIds(ids));
//	}
	
}
