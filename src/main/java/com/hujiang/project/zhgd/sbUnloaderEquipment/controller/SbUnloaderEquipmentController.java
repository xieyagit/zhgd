package com.hujiang.project.zhgd.sbUnloaderEquipment.controller;

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
import com.hujiang.project.zhgd.sbUnloaderEquipment.domain.SbUnloaderEquipment;
import com.hujiang.project.zhgd.sbUnloaderEquipment.service.ISbUnloaderEquipmentService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 卸料设备运行时长 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-12
 */
@Controller
@RequestMapping("/zhgd/sbUnloaderEquipment")
public class SbUnloaderEquipmentController extends BaseController
{
    private String prefix = "zhgd/sbUnloaderEquipment";
	
	@Autowired
	private ISbUnloaderEquipmentService sbUnloaderEquipmentService;
	
	@RequiresPermissions("zhgd:sbUnloaderEquipment:view")
	@GetMapping()
	public String sbUnloaderEquipment()
	{
	    return prefix + "/sbUnloaderEquipment";
	}
	
	/**
	 * 查询卸料设备运行时长列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderEquipment:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbUnloaderEquipment sbUnloaderEquipment)
	{
		startPage();
        List<SbUnloaderEquipment> list = sbUnloaderEquipmentService.selectSbUnloaderEquipmentList(sbUnloaderEquipment);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出卸料设备运行时长列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderEquipment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbUnloaderEquipment sbUnloaderEquipment)
    {
    	List<SbUnloaderEquipment> list = sbUnloaderEquipmentService.selectSbUnloaderEquipmentList(sbUnloaderEquipment);
        ExcelUtil<SbUnloaderEquipment> util = new ExcelUtil<SbUnloaderEquipment>(SbUnloaderEquipment.class);
        return util.exportExcel(list, "sbUnloaderEquipment");
    }
	
	/**
	 * 新增卸料设备运行时长
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存卸料设备运行时长
	 */
	@RequiresPermissions("zhgd:sbUnloaderEquipment:add")
	@Log(title = "卸料设备运行时长", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbUnloaderEquipment sbUnloaderEquipment)
	{		
		return toAjax(sbUnloaderEquipmentService.insertSbUnloaderEquipment(sbUnloaderEquipment));
	}

	/**
	 * 修改卸料设备运行时长
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbUnloaderEquipment sbUnloaderEquipment = sbUnloaderEquipmentService.selectSbUnloaderEquipmentById(id);
		mmap.put("sbUnloaderEquipment", sbUnloaderEquipment);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存卸料设备运行时长
	 */
	@RequiresPermissions("zhgd:sbUnloaderEquipment:edit")
	@Log(title = "卸料设备运行时长", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbUnloaderEquipment sbUnloaderEquipment)
	{		
		return toAjax(sbUnloaderEquipmentService.updateSbUnloaderEquipment(sbUnloaderEquipment));
	}
	
	/**
	 * 删除卸料设备运行时长
	 */
	@RequiresPermissions("zhgd:sbUnloaderEquipment:remove")
	@Log(title = "卸料设备运行时长", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbUnloaderEquipmentService.deleteSbUnloaderEquipmentByIds(ids));
	}
	
}
