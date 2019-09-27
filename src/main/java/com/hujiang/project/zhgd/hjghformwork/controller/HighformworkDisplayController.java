package com.hujiang.project.zhgd.hjghformwork.controller;

import java.util.List;

import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkDisplay;
import com.hujiang.project.zhgd.hjghformwork.service.IHighformworkDisplayService;
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
 * 高支模监测因素 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-09
 */
@Controller
@RequestMapping("/xianxin/highformworkDisplay")
public class HighformworkDisplayController extends BaseController
{
    private String prefix = "xianxin/highformworkDisplay";
	
	@Autowired
	private IHighformworkDisplayService highformworkDisplayService;
	
	@RequiresPermissions("xianxin:highformworkDisplay:view")
	@GetMapping()
	public String highformworkDisplay()
	{
	    return prefix + "/highformworkDisplay";
	}
	
	/**
	 * 查询高支模监测因素列表
	 */
	@RequiresPermissions("xianxin:highformworkDisplay:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HighformworkDisplay highformworkDisplay)
	{
		startPage();
        List<HighformworkDisplay> list = highformworkDisplayService.selectHighformworkDisplayList(highformworkDisplay);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出高支模监测因素列表
	 */
	@RequiresPermissions("xianxin:highformworkDisplay:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HighformworkDisplay highformworkDisplay)
    {
    	List<HighformworkDisplay> list = highformworkDisplayService.selectHighformworkDisplayList(highformworkDisplay);
        ExcelUtil<HighformworkDisplay> util = new ExcelUtil<HighformworkDisplay>(HighformworkDisplay.class);
        return util.exportExcel(list, "highformworkDisplay");
    }
	
	/**
	 * 新增高支模监测因素
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存高支模监测因素
	 */
	@RequiresPermissions("xianxin:highformworkDisplay:add")
	@Log(title = "高支模监测因素", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HighformworkDisplay highformworkDisplay)
	{		
		return toAjax(highformworkDisplayService.insertHighformworkDisplay(highformworkDisplay));
	}

	/**
	 * 修改高支模监测因素
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HighformworkDisplay highformworkDisplay = highformworkDisplayService.selectHighformworkDisplayById(id);
		mmap.put("highformworkDisplay", highformworkDisplay);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存高支模监测因素
	 */
	@RequiresPermissions("xianxin:highformworkDisplay:edit")
	@Log(title = "高支模监测因素", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HighformworkDisplay highformworkDisplay)
	{		
		return toAjax(highformworkDisplayService.updateHighformworkDisplay(highformworkDisplay));
	}
	
	/**
	 * 删除高支模监测因素
	 */
	@RequiresPermissions("xianxin:highformworkDisplay:remove")
	@Log(title = "高支模监测因素", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(highformworkDisplayService.deleteHighformworkDisplayByIds(ids));
	}
	
}
