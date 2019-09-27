package com.hujiang.project.zhgd.hjDeeppit.controller;

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
import com.hujiang.project.zhgd.hjDeeppit.domain.HjDeeppitData;
import com.hujiang.project.zhgd.hjDeeppit.service.IHjDeeppitDataService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 深基坑数据记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-04
 */
@Controller
@RequestMapping("/zhgd/hjDeeppitData")
public class HjDeeppitDataController extends BaseController
{
    private String prefix = "zhgd/hjDeeppitData";
	
	@Autowired
	private IHjDeeppitDataService hjDeeppitDataService;
	
	@RequiresPermissions("zhgd:hjDeeppitData:view")
	@GetMapping()
	public String hjDeeppitData()
	{
	    return prefix + "/hjDeeppitData";
	}
	
	/**
	 * 查询深基坑数据记录列表
	 */
	@RequiresPermissions("zhgd:hjDeeppitData:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjDeeppitData hjDeeppitData)
	{
		startPage();
        List<HjDeeppitData> list = hjDeeppitDataService.selectHjDeeppitDataList(hjDeeppitData);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出深基坑数据记录列表
	 */
	@RequiresPermissions("zhgd:hjDeeppitData:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjDeeppitData hjDeeppitData)
    {
    	List<HjDeeppitData> list = hjDeeppitDataService.selectHjDeeppitDataList(hjDeeppitData);
        ExcelUtil<HjDeeppitData> util = new ExcelUtil<HjDeeppitData>(HjDeeppitData.class);
        return util.exportExcel(list, "hjDeeppitData");
    }
	
	/**
	 * 新增深基坑数据记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存深基坑数据记录
	 */
	@RequiresPermissions("zhgd:hjDeeppitData:add")
	@Log(title = "深基坑数据记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjDeeppitData hjDeeppitData)
	{		
		return toAjax(hjDeeppitDataService.insertHjDeeppitData(hjDeeppitData));
	}

	/**
	 * 修改深基坑数据记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjDeeppitData hjDeeppitData = hjDeeppitDataService.selectHjDeeppitDataById(id);
		mmap.put("hjDeeppitData", hjDeeppitData);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存深基坑数据记录
	 */
	@RequiresPermissions("zhgd:hjDeeppitData:edit")
	@Log(title = "深基坑数据记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjDeeppitData hjDeeppitData)
	{		
		return toAjax(hjDeeppitDataService.updateHjDeeppitData(hjDeeppitData));
	}
	
	/**
	 * 删除深基坑数据记录
	 */
	@RequiresPermissions("zhgd:hjDeeppitData:remove")
	@Log(title = "深基坑数据记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjDeeppitDataService.deleteHjDeeppitDataByIds(ids));
	}
	
}
