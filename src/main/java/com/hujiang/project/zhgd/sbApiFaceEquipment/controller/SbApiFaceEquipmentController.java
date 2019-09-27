package com.hujiang.project.zhgd.sbApiFaceEquipment.controller;

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
import com.hujiang.project.zhgd.sbApiFaceEquipment.domain.SbApiFaceEquipment;
import com.hujiang.project.zhgd.sbApiFaceEquipment.service.ISbApiFaceEquipmentService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 考勤人脸 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/zhgd/sbApiFaceEquipment")
public class SbApiFaceEquipmentController extends BaseController
{
    private String prefix = "zhgd/sbApiFaceEquipment";
	
	@Autowired
	private ISbApiFaceEquipmentService sbApiFaceEquipmentService;
	
	@RequiresPermissions("zhgd:sbApiFaceEquipment:view")
	@GetMapping()
	public String sbApiFaceEquipment()
	{
	    return prefix + "/sbApiFaceEquipment";
	}
	
	/**
	 * 查询考勤人脸列表
	 */
	@RequiresPermissions("zhgd:sbApiFaceEquipment:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbApiFaceEquipment sbApiFaceEquipment)
	{
		startPage();
        List<SbApiFaceEquipment> list = sbApiFaceEquipmentService.selectSbApiFaceEquipmentList(sbApiFaceEquipment);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出考勤人脸列表
	 */
	@RequiresPermissions("zhgd:sbApiFaceEquipment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbApiFaceEquipment sbApiFaceEquipment)
    {
    	List<SbApiFaceEquipment> list = sbApiFaceEquipmentService.selectSbApiFaceEquipmentList(sbApiFaceEquipment);
        ExcelUtil<SbApiFaceEquipment> util = new ExcelUtil<SbApiFaceEquipment>(SbApiFaceEquipment.class);
        return util.exportExcel(list, "sbApiFaceEquipment");
    }
	
	/**
	 * 新增考勤人脸
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存考勤人脸
	 */
	@RequiresPermissions("zhgd:sbApiFaceEquipment:add")
	@Log(title = "考勤人脸", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbApiFaceEquipment sbApiFaceEquipment)
	{		
		return toAjax(sbApiFaceEquipmentService.insertSbApiFaceEquipment(sbApiFaceEquipment));
	}

	/**
	 * 修改考勤人脸
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbApiFaceEquipment sbApiFaceEquipment = sbApiFaceEquipmentService.selectSbApiFaceEquipmentById(id);
		mmap.put("sbApiFaceEquipment", sbApiFaceEquipment);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存考勤人脸
	 */
	@RequiresPermissions("zhgd:sbApiFaceEquipment:edit")
	@Log(title = "考勤人脸", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbApiFaceEquipment sbApiFaceEquipment)
	{		
		return toAjax(sbApiFaceEquipmentService.updateSbApiFaceEquipment(sbApiFaceEquipment));
	}
	
	/**
	 * 删除考勤人脸
	 */
	@RequiresPermissions("zhgd:sbApiFaceEquipment:remove")
	@Log(title = "考勤人脸", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbApiFaceEquipmentService.deleteSbApiFaceEquipmentByIds(ids));
	}
	
}
