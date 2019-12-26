package com.hujiang.project.zhgd.sbGroupTalkback.controller;

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
import com.hujiang.project.zhgd.sbGroupTalkback.domain.SbGroupTalkback;
import com.hujiang.project.zhgd.sbGroupTalkback.service.ISbGroupTalkbackService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 集团对讲账号 信息操作处理
 * 
 * @author hujiang
 * @date 2019-12-05
 */
@Controller
@RequestMapping("/zhgd/sbGroupTalkback")
public class SbGroupTalkbackController extends BaseController
{
    private String prefix = "zhgd/sbGroupTalkback";
	
	@Autowired
	private ISbGroupTalkbackService sbGroupTalkbackService;
	
	@RequiresPermissions("zhgd:sbGroupTalkback:view")
	@GetMapping()
	public String sbGroupTalkback()
	{
	    return prefix + "/sbGroupTalkback";
	}
	
	/**
	 * 查询集团对讲账号列表
	 */
	@RequiresPermissions("zhgd:sbGroupTalkback:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbGroupTalkback sbGroupTalkback)
	{
		startPage();
        List<SbGroupTalkback> list = sbGroupTalkbackService.selectSbGroupTalkbackList(sbGroupTalkback);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出集团对讲账号列表
	 */
	@RequiresPermissions("zhgd:sbGroupTalkback:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbGroupTalkback sbGroupTalkback)
    {
    	List<SbGroupTalkback> list = sbGroupTalkbackService.selectSbGroupTalkbackList(sbGroupTalkback);
        ExcelUtil<SbGroupTalkback> util = new ExcelUtil<SbGroupTalkback>(SbGroupTalkback.class);
        return util.exportExcel(list, "sbGroupTalkback");
    }
	
	/**
	 * 新增集团对讲账号
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存集团对讲账号
	 */
	@RequiresPermissions("zhgd:sbGroupTalkback:add")
	@Log(title = "集团对讲账号", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbGroupTalkback sbGroupTalkback)
	{		
		return toAjax(sbGroupTalkbackService.insertSbGroupTalkback(sbGroupTalkback));
	}

	/**
	 * 修改集团对讲账号
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbGroupTalkback sbGroupTalkback = sbGroupTalkbackService.selectSbGroupTalkbackById(id);
		mmap.put("sbGroupTalkback", sbGroupTalkback);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存集团对讲账号
	 */
	@RequiresPermissions("zhgd:sbGroupTalkback:edit")
	@Log(title = "集团对讲账号", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbGroupTalkback sbGroupTalkback)
	{		
		return toAjax(sbGroupTalkbackService.updateSbGroupTalkback(sbGroupTalkback));
	}
	
	/**
	 * 删除集团对讲账号
	 */
	@RequiresPermissions("zhgd:sbGroupTalkback:remove")
	@Log(title = "集团对讲账号", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbGroupTalkbackService.deleteSbGroupTalkbackByIds(ids));
	}
	
}
