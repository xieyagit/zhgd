package com.hujiang.project.zhgd.hjghformwork.controller;

import java.util.List;

import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkAlarmData;
import com.hujiang.project.zhgd.hjghformwork.service.IHighformworkAlarmDataService;
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
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 高支模报警记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-09
 */
@Controller
@RequestMapping("/xianxin/highformworkAlarmData")
public class HighformworkAlarmDataController extends BaseController
{
    private String prefix = "xianxin/highformworkAlarmData";
	
	@Autowired
	private IHighformworkAlarmDataService highformworkAlarmDataService;
	
	@RequiresPermissions("xianxin:highformworkAlarmData:view")
	@GetMapping()
	public String highformworkAlarmData()
	{
	    return prefix + "/highformworkAlarmData";
	}
	
	/**
	 * 查询高支模报警记录列表
	 */
	@RequiresPermissions("xianxin:highformworkAlarmData:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HighformworkAlarmData highformworkAlarmData)
	{
		startPage();
        List<HighformworkAlarmData> list = highformworkAlarmDataService.selectHighformworkAlarmDataList(highformworkAlarmData);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出高支模报警记录列表
	 */
	@RequiresPermissions("xianxin:highformworkAlarmData:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HighformworkAlarmData highformworkAlarmData)
    {
    	List<HighformworkAlarmData> list = highformworkAlarmDataService.selectHighformworkAlarmDataList(highformworkAlarmData);
        ExcelUtil<HighformworkAlarmData> util = new ExcelUtil<HighformworkAlarmData>(HighformworkAlarmData.class);
        return util.exportExcel(list, "highformworkAlarmData");
    }
	
	/**
	 * 新增高支模报警记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存高支模报警记录
	 */
	@RequiresPermissions("xianxin:highformworkAlarmData:add")
	@Log(title = "高支模报警记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HighformworkAlarmData highformworkAlarmData)
	{		
		return toAjax(highformworkAlarmDataService.insertHighformworkAlarmData(highformworkAlarmData));
	}

	/**
	 * 修改高支模报警记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HighformworkAlarmData highformworkAlarmData = highformworkAlarmDataService.selectHighformworkAlarmDataById(id);
		mmap.put("highformworkAlarmData", highformworkAlarmData);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存高支模报警记录
	 */
	@RequiresPermissions("xianxin:highformworkAlarmData:edit")
	@Log(title = "高支模报警记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HighformworkAlarmData highformworkAlarmData)
	{		
		return toAjax(highformworkAlarmDataService.updateHighformworkAlarmData(highformworkAlarmData));
	}
	
	/**
	 * 删除高支模报警记录
	 */
	@RequiresPermissions("xianxin:highformworkAlarmData:remove")
	@Log(title = "高支模报警记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(highformworkAlarmDataService.deleteHighformworkAlarmDataByIds(ids));
	}
	
}
