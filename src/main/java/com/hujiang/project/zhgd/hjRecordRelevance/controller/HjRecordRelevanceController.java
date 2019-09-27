package com.hujiang.project.zhgd.hjRecordRelevance.controller;

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
import com.hujiang.project.zhgd.hjRecordRelevance.domain.HjRecordRelevance;
import com.hujiang.project.zhgd.hjRecordRelevance.service.IHjRecordRelevanceService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 记录关联 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/zhgd/hjRecordRelevance")
public class HjRecordRelevanceController extends BaseController
{
    private String prefix = "zhgd/hjRecordRelevance";
	
	@Autowired
	private IHjRecordRelevanceService hjRecordRelevanceService;
	
	@RequiresPermissions("zhgd:hjRecordRelevance:view")
	@GetMapping()
	public String hjRecordRelevance()
	{
	    return prefix + "/hjRecordRelevance";
	}
	
	/**
	 * 查询记录关联列表
	 */
	@RequiresPermissions("zhgd:hjRecordRelevance:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjRecordRelevance hjRecordRelevance)
	{
		startPage();
        List<HjRecordRelevance> list = hjRecordRelevanceService.selectHjRecordRelevanceList(hjRecordRelevance);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出记录关联列表
	 */
	@RequiresPermissions("zhgd:hjRecordRelevance:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjRecordRelevance hjRecordRelevance)
    {
    	List<HjRecordRelevance> list = hjRecordRelevanceService.selectHjRecordRelevanceList(hjRecordRelevance);
        ExcelUtil<HjRecordRelevance> util = new ExcelUtil<HjRecordRelevance>(HjRecordRelevance.class);
        return util.exportExcel(list, "hjRecordRelevance");
    }
	
	/**
	 * 新增记录关联
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存记录关联
	 */
	@RequiresPermissions("zhgd:hjRecordRelevance:add")
	@Log(title = "记录关联", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjRecordRelevance hjRecordRelevance)
	{		
		return toAjax(hjRecordRelevanceService.insertHjRecordRelevance(hjRecordRelevance));
	}

	/**
	 * 修改记录关联
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjRecordRelevance hjRecordRelevance = hjRecordRelevanceService.selectHjRecordRelevanceById(id);
		mmap.put("hjRecordRelevance", hjRecordRelevance);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存记录关联
	 */
	@RequiresPermissions("zhgd:hjRecordRelevance:edit")
	@Log(title = "记录关联", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjRecordRelevance hjRecordRelevance)
	{		
		return toAjax(hjRecordRelevanceService.updateHjRecordRelevance(hjRecordRelevance));
	}
	
	/**
	 * 删除记录关联
	 */
	@RequiresPermissions("zhgd:hjRecordRelevance:remove")
	@Log(title = "记录关联", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjRecordRelevanceService.deleteHjRecordRelevanceByIds(ids));
	}
	
}
