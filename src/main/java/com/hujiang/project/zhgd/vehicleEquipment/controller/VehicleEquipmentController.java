package com.hujiang.project.zhgd.vehicleEquipment.controller;

import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.vehicleEquipment.domain.VehicleEquipment;
import com.hujiang.project.zhgd.vehicleEquipment.service.IVehicleEquipmentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 车牌设备 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-07
 */
@Controller
@RequestMapping("/zhgd/vehicleEquipment")
public class VehicleEquipmentController extends BaseController
{
    private String prefix = "zhgd/vehicleEquipment";
	
	@Autowired
	private IVehicleEquipmentService vehicleEquipmentService;
	
	@RequiresPermissions("zhgd:vehicleEquipment:view")
	@GetMapping()
	public String vehicleEquipment()
	{
	    return prefix + "/vehicleEquipment";
	}
	
	/**
	 * 查询车牌设备列表
	 */
	@RequiresPermissions("zhgd:vehicleEquipment:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VehicleEquipment vehicleEquipment)
	{
		startPage();
        List<VehicleEquipment> list = vehicleEquipmentService.selectVehicleEquipmentList(vehicleEquipment);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出车牌设备列表
	 */
	@RequiresPermissions("zhgd:vehicleEquipment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(VehicleEquipment vehicleEquipment)
    {
    	List<VehicleEquipment> list = vehicleEquipmentService.selectVehicleEquipmentList(vehicleEquipment);
        ExcelUtil<VehicleEquipment> util = new ExcelUtil<VehicleEquipment>(VehicleEquipment.class);
        return util.exportExcel(list, "vehicleEquipment");
    }
	
	/**
	 * 新增车牌设备
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存车牌设备
	 */
	@RequiresPermissions("zhgd:vehicleEquipment:add")
	@Log(title = "车牌设备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VehicleEquipment vehicleEquipment)
	{		
		return toAjax(vehicleEquipmentService.insertVehicleEquipment(vehicleEquipment));
	}

	/**
	 * 修改车牌设备
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		VehicleEquipment vehicleEquipment = vehicleEquipmentService.selectVehicleEquipmentById(id);
		mmap.put("vehicleEquipment", vehicleEquipment);
	    return prefix + "/edit";
	}

	/**
	 *
	 * @GetMapping()
	 * public void test(@PathVariable("") Object obj
	 * {
	 * 		VehicleEquipment v =     vehicleEquipmentService.alllist(obj);
	 * 		mao.put("", )
	 * }
	 *
	 * */

	/**
	 * 修改保存车牌设备
	 */
	@RequiresPermissions("zhgd:vehicleEquipment:edit")
	@Log(title = "车牌设备", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VehicleEquipment vehicleEquipment)
	{		
		return toAjax(vehicleEquipmentService.updateVehicleEquipment(vehicleEquipment));
	}
	
	/**
	 * 删除车牌设备
	 */
	@RequiresPermissions("zhgd:vehicleEquipment:remove")
	@Log(title = "车牌设备", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(vehicleEquipmentService.deleteVehicleEquipmentByIds(ids));
	}
	
}
