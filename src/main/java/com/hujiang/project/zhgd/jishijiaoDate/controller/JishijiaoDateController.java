package com.hujiang.project.zhgd.jishijiaoDate.controller;

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
import com.hujiang.project.zhgd.jishijiaoDate.domain.JishijiaoDate;
import com.hujiang.project.zhgd.jishijiaoDate.service.IJishijiaoDateService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 极视角异常数据 信息操作处理
 * 
 * @author hujiang
 * @date 2019-08-15
 */
@Controller
@RequestMapping("/zhgd/jishijiaoDate")
public class JishijiaoDateController extends BaseController
{
    private String prefix = "zhgd/jishijiaoDate";
	
	@Autowired
	private IJishijiaoDateService jishijiaoDateService;
	
	@RequiresPermissions("zhgd:jishijiaoDate:view")
	@GetMapping()
	public String jishijiaoDate()
	{
	    return prefix + "/jishijiaoDate";
	}
	
	/**
	 * 查询极视角异常数据列表
	 */
	@RequiresPermissions("zhgd:jishijiaoDate:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(JishijiaoDate jishijiaoDate)
	{
		startPage();
        List<JishijiaoDate> list = jishijiaoDateService.selectJishijiaoDateList(jishijiaoDate);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出极视角异常数据列表
	 */
	@RequiresPermissions("zhgd:jishijiaoDate:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(JishijiaoDate jishijiaoDate)
    {
    	List<JishijiaoDate> list = jishijiaoDateService.selectJishijiaoDateList(jishijiaoDate);
        ExcelUtil<JishijiaoDate> util = new ExcelUtil<JishijiaoDate>(JishijiaoDate.class);
        return util.exportExcel(list, "jishijiaoDate");
    }
	
	/**
	 * 新增极视角异常数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存极视角异常数据
	 */
	@RequiresPermissions("zhgd:jishijiaoDate:add")
	@Log(title = "极视角异常数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(JishijiaoDate jishijiaoDate)
	{		
		return toAjax(jishijiaoDateService.insertJishijiaoDate(jishijiaoDate));
	}

	/**
	 * 修改极视角异常数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		JishijiaoDate jishijiaoDate = jishijiaoDateService.selectJishijiaoDateById(id);
		mmap.put("jishijiaoDate", jishijiaoDate);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存极视角异常数据
	 */
	@RequiresPermissions("zhgd:jishijiaoDate:edit")
	@Log(title = "极视角异常数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(JishijiaoDate jishijiaoDate)
	{		
		return toAjax(jishijiaoDateService.updateJishijiaoDate(jishijiaoDate));
	}
	
	/**
	 * 删除极视角异常数据
	 */
	@RequiresPermissions("zhgd:jishijiaoDate:remove")
	@Log(title = "极视角异常数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(jishijiaoDateService.deleteJishijiaoDateByIds(ids));
	}
	
}
