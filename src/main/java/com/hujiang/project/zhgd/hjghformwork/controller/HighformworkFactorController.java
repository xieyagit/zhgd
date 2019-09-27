package com.hujiang.project.zhgd.hjghformwork.controller;

import java.util.List;

import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkFactor;
import com.hujiang.project.zhgd.hjghformwork.service.IHighformworkFactorService;
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
 * 高支模传感器 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-09
 */
@Controller
@RequestMapping("/xianxin/highformworkFactor")
public class HighformworkFactorController extends BaseController
{
    private String prefix = "xianxin/highformworkFactor";
	
	@Autowired
	private IHighformworkFactorService highformworkFactorService;
	
	@RequiresPermissions("xianxin:highformworkFactor:view")
	@GetMapping()
	public String highformworkFactor()
	{
	    return prefix + "/highformworkFactor";
	}
	
	/**
	 * 查询高支模传感器列表
	 */
	@RequiresPermissions("xianxin:highformworkFactor:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HighformworkFactor highformworkFactor)
	{
		startPage();
        List<HighformworkFactor> list = highformworkFactorService.selectHighformworkFactorList(highformworkFactor);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出高支模传感器列表
	 */
	@RequiresPermissions("xianxin:highformworkFactor:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HighformworkFactor highformworkFactor)
    {
    	List<HighformworkFactor> list = highformworkFactorService.selectHighformworkFactorList(highformworkFactor);
        ExcelUtil<HighformworkFactor> util = new ExcelUtil<HighformworkFactor>(HighformworkFactor.class);
        return util.exportExcel(list, "highformworkFactor");
    }
	
	/**
	 * 新增高支模传感器
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存高支模传感器
	 */
	@RequiresPermissions("xianxin:highformworkFactor:add")
	@Log(title = "高支模传感器", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HighformworkFactor highformworkFactor)
	{		
		return toAjax(highformworkFactorService.insertHighformworkFactor(highformworkFactor));
	}

	/**
	 * 修改高支模传感器
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HighformworkFactor highformworkFactor = highformworkFactorService.selectHighformworkFactorById(id);
		mmap.put("highformworkFactor", highformworkFactor);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存高支模传感器
	 */
	@RequiresPermissions("xianxin:highformworkFactor:edit")
	@Log(title = "高支模传感器", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HighformworkFactor highformworkFactor)
	{		
		return toAjax(highformworkFactorService.updateHighformworkFactor(highformworkFactor));
	}
	
	/**
	 * 删除高支模传感器
	 */
	@RequiresPermissions("xianxin:highformworkFactor:remove")
	@Log(title = "高支模传感器", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(highformworkFactorService.deleteHighformworkFactorByIds(ids));
	}
	
}
