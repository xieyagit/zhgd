package com.hujiang.project.zhgd.hjghformwork.controller;

import java.util.List;

import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkData;
import com.hujiang.project.zhgd.hjghformwork.service.IHighformworkDataService;
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
 * 高支模数据记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-09
 */
@Controller
@RequestMapping("/xianxin/highformworkData")
public class HighformworkDataController extends BaseController
{
    private String prefix = "xianxin/highformworkData";
	
	@Autowired
	private IHighformworkDataService highformworkDataService;
	
	@RequiresPermissions("xianxin:highformworkData:view")
	@GetMapping()
	public String highformworkData()
	{
	    return prefix + "/highformworkData";
	}
	
	/**
	 * 查询高支模数据记录列表
	 */
	@RequiresPermissions("xianxin:highformworkData:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HighformworkData highformworkData)
	{
		startPage();
        List<HighformworkData> list = highformworkDataService.selectHighformworkDataList(highformworkData);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出高支模数据记录列表
	 */
	@RequiresPermissions("xianxin:highformworkData:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HighformworkData highformworkData)
    {
    	List<HighformworkData> list = highformworkDataService.selectHighformworkDataList(highformworkData);
        ExcelUtil<HighformworkData> util = new ExcelUtil<HighformworkData>(HighformworkData.class);
        return util.exportExcel(list, "highformworkData");
    }
	
	/**
	 * 新增高支模数据记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存高支模数据记录
	 */
	@RequiresPermissions("xianxin:highformworkData:add")
	@Log(title = "高支模数据记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HighformworkData highformworkData)
	{		
		return toAjax(highformworkDataService.insertHighformworkData(highformworkData));
	}

	/**
	 * 修改高支模数据记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HighformworkData highformworkData = highformworkDataService.selectHighformworkDataById(id);
		mmap.put("highformworkData", highformworkData);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存高支模数据记录
	 */
	@RequiresPermissions("xianxin:highformworkData:edit")
	@Log(title = "高支模数据记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HighformworkData highformworkData)
	{		
		return toAjax(highformworkDataService.updateHighformworkData(highformworkData));
	}
	
	/**
	 * 删除高支模数据记录
	 */
	@RequiresPermissions("xianxin:highformworkData:remove")
	@Log(title = "高支模数据记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(highformworkDataService.deleteHighformworkDataByIds(ids));
	}
	
}
