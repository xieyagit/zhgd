package com.hujiang.project.zhgd.lyPersonYunmou.controller;

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
import com.hujiang.project.zhgd.lyPersonYunmou.domain.LyPersonYunmou;
import com.hujiang.project.zhgd.lyPersonYunmou.service.ILyPersonYunmouService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 云眸人员 信息操作处理
 * 
 * @author hujiang
 * @date 2020-04-14
 */
@Controller
@RequestMapping("/zhgd/lyPersonYunmou")
public class LyPersonYunmouController extends BaseController
{
    private String prefix = "zhgd/lyPersonYunmou";
	
	@Autowired
	private ILyPersonYunmouService lyPersonYunmouService;
	
	@RequiresPermissions("zhgd:lyPersonYunmou:view")
	@GetMapping()
	public String lyPersonYunmou()
	{
	    return prefix + "/lyPersonYunmou";
	}
	
	/**
	 * 查询云眸人员列表
	 */
	@RequiresPermissions("zhgd:lyPersonYunmou:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(LyPersonYunmou lyPersonYunmou)
	{
		startPage();
        List<LyPersonYunmou> list = lyPersonYunmouService.selectLyPersonYunmouList(lyPersonYunmou);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出云眸人员列表
	 */
	@RequiresPermissions("zhgd:lyPersonYunmou:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LyPersonYunmou lyPersonYunmou)
    {
    	List<LyPersonYunmou> list = lyPersonYunmouService.selectLyPersonYunmouList(lyPersonYunmou);
        ExcelUtil<LyPersonYunmou> util = new ExcelUtil<LyPersonYunmou>(LyPersonYunmou.class);
        return util.exportExcel(list, "lyPersonYunmou");
    }
	
	/**
	 * 新增云眸人员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存云眸人员
	 */
	@RequiresPermissions("zhgd:lyPersonYunmou:add")
	@Log(title = "云眸人员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(LyPersonYunmou lyPersonYunmou)
	{		
		return toAjax(lyPersonYunmouService.insertLyPersonYunmou(lyPersonYunmou));
	}

	/**
	 * 修改云眸人员
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		LyPersonYunmou lyPersonYunmou = lyPersonYunmouService.selectLyPersonYunmouById(id);
		mmap.put("lyPersonYunmou", lyPersonYunmou);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存云眸人员
	 */
	@RequiresPermissions("zhgd:lyPersonYunmou:edit")
	@Log(title = "云眸人员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(LyPersonYunmou lyPersonYunmou)
	{		
		return toAjax(lyPersonYunmouService.updateLyPersonYunmou(lyPersonYunmou));
	}
	
	/**
	 * 删除云眸人员
	 */
	@RequiresPermissions("zhgd:lyPersonYunmou:remove")
	@Log(title = "云眸人员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(lyPersonYunmouService.deleteLyPersonYunmouByIds(ids));
	}
	
}
