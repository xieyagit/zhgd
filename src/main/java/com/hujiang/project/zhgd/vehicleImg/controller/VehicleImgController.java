package com.hujiang.project.zhgd.vehicleImg.controller;

import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.vehicleImg.domain.VehicleImg;
import com.hujiang.project.zhgd.vehicleImg.service.IVehicleImgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车牌照片 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-19
 */
@Controller
@RequestMapping("/moredian/vehicleImg")
public class VehicleImgController extends BaseController
{
    private String prefix = "moredian/vehicleImg";
	
	@Autowired
	private IVehicleImgService vehicleImgService;
	
	@RequiresPermissions("moredian:vehicleImg:view")
	@GetMapping()
	public String vehicleImg()
	{
	    return prefix + "/vehicleImg";
	}
	
	/**
	 * 查询车牌照片列表
	 */
	@RequiresPermissions("moredian:vehicleImg:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VehicleImg vehicleImg)
	{
		startPage();
        List<VehicleImg> list = vehicleImgService.selectVehicleImgList(vehicleImg);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出车牌照片列表
	 */
	@RequiresPermissions("moredian:vehicleImg:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(VehicleImg vehicleImg)
    {
    	List<VehicleImg> list = vehicleImgService.selectVehicleImgList(vehicleImg);
        ExcelUtil<VehicleImg> util = new ExcelUtil<VehicleImg>(VehicleImg.class);
        return util.exportExcel(list, "vehicleImg");
    }
	
	/**
	 * 新增车牌照片
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存车牌照片
	 */
	@RequiresPermissions("moredian:vehicleImg:add")
	@Log(title = "车牌照片", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VehicleImg vehicleImg)
	{		
		return toAjax(vehicleImgService.insertVehicleImg(vehicleImg));
	}

	/**
	 * 修改车牌照片
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		VehicleImg vehicleImg = vehicleImgService.selectVehicleImgById(id);
		mmap.put("vehicleImg", vehicleImg);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存车牌照片
	 */
	@RequiresPermissions("moredian:vehicleImg:edit")
	@Log(title = "车牌照片", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VehicleImg vehicleImg)
	{		
		return toAjax(vehicleImgService.updateVehicleImg(vehicleImg));
	}
	
	/**
	 * 删除车牌照片
	 */
	@RequiresPermissions("moredian:vehicleImg:remove")
	@Log(title = "车牌照片", businessType = BusinessType.DELETE)
	@PostMapping( "/rmoeve")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(vehicleImgService.deleteVehicleImgByIds(ids));
	}
	
}
