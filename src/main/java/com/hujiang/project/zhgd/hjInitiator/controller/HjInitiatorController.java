package com.hujiang.project.zhgd.hjInitiator.controller;

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
import com.hujiang.project.zhgd.hjInitiator.domain.HjInitiator;
import com.hujiang.project.zhgd.hjInitiator.service.IHjInitiatorService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 发起问题记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/zhgd/hjInitiator")
public class HjInitiatorController extends BaseController
{
    private String prefix = "zhgd/hjInitiator";
	
	@Autowired
	private IHjInitiatorService hjInitiatorService;
	
	@RequiresPermissions("zhgd:hjInitiator:view")
	@GetMapping()
	public String hjInitiator()
	{
	    return prefix + "/hjInitiator";
	}
	
	/**
	 * 查询发起问题记录列表
	 */
	@RequiresPermissions("zhgd:hjInitiator:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjInitiator hjInitiator)
	{
		startPage();
        List<HjInitiator> list = hjInitiatorService.selectHjInitiatorList(hjInitiator);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出发起问题记录列表
	 */
	@RequiresPermissions("zhgd:hjInitiator:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjInitiator hjInitiator)
    {
    	List<HjInitiator> list = hjInitiatorService.selectHjInitiatorList(hjInitiator);
        ExcelUtil<HjInitiator> util = new ExcelUtil<HjInitiator>(HjInitiator.class);
        return util.exportExcel(list, "hjInitiator");
    }
	
	/**
	 * 新增发起问题记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存发起问题记录
	 */
	@RequiresPermissions("zhgd:hjInitiator:add")
	@Log(title = "发起问题记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjInitiator hjInitiator)
	{		
		return toAjax(hjInitiatorService.insertHjInitiator(hjInitiator));
	}

	/**
	 * 修改发起问题记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjInitiator hjInitiator = hjInitiatorService.selectHjInitiatorById(id);
		mmap.put("hjInitiator", hjInitiator);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存发起问题记录
	 */
	@RequiresPermissions("zhgd:hjInitiator:edit")
	@Log(title = "发起问题记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjInitiator hjInitiator)
	{		
		return toAjax(hjInitiatorService.updateHjInitiator(hjInitiator));
	}
	
	/**
	 * 删除发起问题记录
	 */
	@RequiresPermissions("zhgd:hjInitiator:remove")
	@Log(title = "发起问题记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjInitiatorService.deleteHjInitiatorByIds(ids));
	}
	
}
