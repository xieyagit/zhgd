package com.hujiang.project.zhgd.dustEmissionThresholdValue.controller;

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
import com.hujiang.project.zhgd.dustEmissionThresholdValue.domain.DustEmissionThresholdValue;
import com.hujiang.project.zhgd.dustEmissionThresholdValue.service.IDustEmissionThresholdValueService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 项目扬尘设备阈值 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-08
 */
@Controller
@RequestMapping("/zhgd/dustEmissionThresholdValue")
public class DustEmissionThresholdValueController extends BaseController
{
    private String prefix = "zhgd/dustEmissionThresholdValue";
	
	@Autowired
	private IDustEmissionThresholdValueService dustEmissionThresholdValueService;
	
	@RequiresPermissions("zhgd:dustEmissionThresholdValue:view")
	@GetMapping()
	public String dustEmissionThresholdValue()
	{
	    return prefix + "/dustEmissionThresholdValue";
	}
	
	/**
	 * 查询项目扬尘设备阈值列表
	 */
	@RequiresPermissions("zhgd:dustEmissionThresholdValue:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DustEmissionThresholdValue dustEmissionThresholdValue)
	{
		startPage();
        List<DustEmissionThresholdValue> list = dustEmissionThresholdValueService.selectDustEmissionThresholdValueList(dustEmissionThresholdValue);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出项目扬尘设备阈值列表
	 */
	@RequiresPermissions("zhgd:dustEmissionThresholdValue:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DustEmissionThresholdValue dustEmissionThresholdValue)
    {
    	List<DustEmissionThresholdValue> list = dustEmissionThresholdValueService.selectDustEmissionThresholdValueList(dustEmissionThresholdValue);
        ExcelUtil<DustEmissionThresholdValue> util = new ExcelUtil<DustEmissionThresholdValue>(DustEmissionThresholdValue.class);
        return util.exportExcel(list, "dustEmissionThresholdValue");
    }
	
	/**
	 * 新增项目扬尘设备阈值
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目扬尘设备阈值
	 */
	@RequiresPermissions("zhgd:dustEmissionThresholdValue:add")
	@Log(title = "项目扬尘设备阈值", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DustEmissionThresholdValue dustEmissionThresholdValue)
	{		
		return toAjax(dustEmissionThresholdValueService.insertDustEmissionThresholdValue(dustEmissionThresholdValue));
	}

	/**
	 * 修改项目扬尘设备阈值
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		DustEmissionThresholdValue dustEmissionThresholdValue = dustEmissionThresholdValueService.selectDustEmissionThresholdValueById(id);
		mmap.put("dustEmissionThresholdValue", dustEmissionThresholdValue);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目扬尘设备阈值
	 */
	@RequiresPermissions("zhgd:dustEmissionThresholdValue:edit")
	@Log(title = "项目扬尘设备阈值", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DustEmissionThresholdValue dustEmissionThresholdValue)
	{		
		return toAjax(dustEmissionThresholdValueService.updateDustEmissionThresholdValue(dustEmissionThresholdValue));
	}
	
	/**
	 * 删除项目扬尘设备阈值
	 */
	@RequiresPermissions("zhgd:dustEmissionThresholdValue:remove")
	@Log(title = "项目扬尘设备阈值", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(dustEmissionThresholdValueService.deleteDustEmissionThresholdValueByIds(ids));
	}
	
}
