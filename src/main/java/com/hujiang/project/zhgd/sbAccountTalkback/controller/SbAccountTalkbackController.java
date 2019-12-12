package com.hujiang.project.zhgd.sbAccountTalkback.controller;

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
import com.hujiang.project.zhgd.sbAccountTalkback.domain.SbAccountTalkback;
import com.hujiang.project.zhgd.sbAccountTalkback.service.ISbAccountTalkbackService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 集团对讲机列 信息操作处理
 * 
 * @author hujiang
 * @date 2019-12-05
 */
@Controller
@RequestMapping("/zhgd/sbAccountTalkback")
public class SbAccountTalkbackController extends BaseController
{
    private String prefix = "zhgd/sbAccountTalkback";
	
	@Autowired
	private ISbAccountTalkbackService sbAccountTalkbackService;
	
	@RequiresPermissions("zhgd:sbAccountTalkback:view")
	@GetMapping()
	public String sbAccountTalkback()
	{
	    return prefix + "/sbAccountTalkback";
	}
	
	/**
	 * 查询集团对讲机列列表
	 */
	@RequiresPermissions("zhgd:sbAccountTalkback:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbAccountTalkback sbAccountTalkback)
	{
		startPage();
        List<SbAccountTalkback> list = sbAccountTalkbackService.selectSbAccountTalkbackList(sbAccountTalkback);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出集团对讲机列列表
	 */
	@RequiresPermissions("zhgd:sbAccountTalkback:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbAccountTalkback sbAccountTalkback)
    {
    	List<SbAccountTalkback> list = sbAccountTalkbackService.selectSbAccountTalkbackList(sbAccountTalkback);
        ExcelUtil<SbAccountTalkback> util = new ExcelUtil<SbAccountTalkback>(SbAccountTalkback.class);
        return util.exportExcel(list, "sbAccountTalkback");
    }
	
	/**
	 * 新增集团对讲机列
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存集团对讲机列
	 */
	@RequiresPermissions("zhgd:sbAccountTalkback:add")
	@Log(title = "集团对讲机列", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbAccountTalkback sbAccountTalkback)
	{		
		return toAjax(sbAccountTalkbackService.insertSbAccountTalkback(sbAccountTalkback));
	}

	/**
	 * 修改集团对讲机列
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbAccountTalkback sbAccountTalkback = sbAccountTalkbackService.selectSbAccountTalkbackById(id);
		mmap.put("sbAccountTalkback", sbAccountTalkback);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存集团对讲机列
	 */
	@RequiresPermissions("zhgd:sbAccountTalkback:edit")
	@Log(title = "集团对讲机列", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbAccountTalkback sbAccountTalkback)
	{		
		return toAjax(sbAccountTalkbackService.updateSbAccountTalkback(sbAccountTalkback));
	}
	
	/**
	 * 删除集团对讲机列
	 */
	@RequiresPermissions("zhgd:sbAccountTalkback:remove")
	@Log(title = "集团对讲机列", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbAccountTalkbackService.deleteSbAccountTalkbackByIds(ids));
	}
	
}
