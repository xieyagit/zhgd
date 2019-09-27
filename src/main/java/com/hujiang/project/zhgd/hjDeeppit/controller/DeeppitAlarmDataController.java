package com.hujiang.project.zhgd.hjDeeppit.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.project.zhgd.hjDeeppit.domain.DeeppitAlarmData;
import com.hujiang.project.zhgd.hjDeeppit.service.IDeeppitAlarmDataService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 深基坑报警记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-06
 */
@Controller
@RequestMapping("/xianxin/deeppitAlarmData")
public class DeeppitAlarmDataController extends BaseController
{
    private String prefix = "xianxin/deeppitAlarmData";
	
	@Autowired
	private IDeeppitAlarmDataService deeppitAlarmDataService;
	
	@RequiresPermissions("xianxin:deeppitAlarmData:view")
	@GetMapping()
	public String deeppitAlarmData()
	{
	    return prefix + "/deeppitAlarmData";
	}
	
	/**
	 * 查询深基坑报警记录列表
	 */
	@RequiresPermissions("xianxin:deeppitAlarmData:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DeeppitAlarmData deeppitAlarmData)
	{
		startPage();
        List<DeeppitAlarmData> list = deeppitAlarmDataService.selectDeeppitAlarmDataList(deeppitAlarmData);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出深基坑报警记录列表
	 */
	@RequiresPermissions("xianxin:deeppitAlarmData:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DeeppitAlarmData deeppitAlarmData)
    {
    	List<DeeppitAlarmData> list = deeppitAlarmDataService.selectDeeppitAlarmDataList(deeppitAlarmData);
        ExcelUtil<DeeppitAlarmData> util = new ExcelUtil<DeeppitAlarmData>(DeeppitAlarmData.class);
        return util.exportExcel(list, "deeppitAlarmData");
    }
	
	/**
	 * 新增深基坑报警记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存深基坑报警记录
	 */
	@RequiresPermissions("xianxin:deeppitAlarmData:add")
	@Log(title = "深基坑报警记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DeeppitAlarmData deeppitAlarmData)
	{		
		return toAjax(deeppitAlarmDataService.insertDeeppitAlarmData(deeppitAlarmData));
	}

	/**
	 * 修改深基坑报警记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		DeeppitAlarmData deeppitAlarmData = deeppitAlarmDataService.selectDeeppitAlarmDataById(id);
		mmap.put("deeppitAlarmData", deeppitAlarmData);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存深基坑报警记录
	 */
	@RequiresPermissions("xianxin:deeppitAlarmData:edit")
	@Log(title = "深基坑报警记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DeeppitAlarmData deeppitAlarmData)
	{		
		return toAjax(deeppitAlarmDataService.updateDeeppitAlarmData(deeppitAlarmData));
	}
	
	/**
	 * 删除深基坑报警记录
	 */
	@RequiresPermissions("xianxin:deeppitAlarmData:remove")
	@Log(title = "深基坑报警记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(deeppitAlarmDataService.deleteDeeppitAlarmDataByIds(ids));
	}
	
}
