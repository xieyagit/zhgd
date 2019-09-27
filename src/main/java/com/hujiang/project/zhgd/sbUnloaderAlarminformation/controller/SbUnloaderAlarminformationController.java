package com.hujiang.project.zhgd.sbUnloaderAlarminformation.controller;

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
import com.hujiang.project.zhgd.sbUnloaderAlarminformation.domain.SbUnloaderAlarminformation;
import com.hujiang.project.zhgd.sbUnloaderAlarminformation.service.ISbUnloaderAlarminformationService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 卸料报警时段数据 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-11
 */
@Controller
@RequestMapping("/zhgd/sbUnloaderAlarminformation")
public class SbUnloaderAlarminformationController extends BaseController
{
    private String prefix = "zhgd/sbUnloaderAlarminformation";
	
	@Autowired
	private ISbUnloaderAlarminformationService sbUnloaderAlarminformationService;
	
	@RequiresPermissions("zhgd:sbUnloaderAlarminformation:view")
	@GetMapping()
	public String sbUnloaderAlarminformation()
	{
	    return prefix + "/sbUnloaderAlarminformation";
	}
	
	/**
	 * 查询卸料报警时段数据列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderAlarminformation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbUnloaderAlarminformation sbUnloaderAlarminformation)
	{
		startPage();
        List<SbUnloaderAlarminformation> list = sbUnloaderAlarminformationService.selectSbUnloaderAlarminformationList(sbUnloaderAlarminformation);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出卸料报警时段数据列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderAlarminformation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbUnloaderAlarminformation sbUnloaderAlarminformation)
    {
    	List<SbUnloaderAlarminformation> list = sbUnloaderAlarminformationService.selectSbUnloaderAlarminformationList(sbUnloaderAlarminformation);
        ExcelUtil<SbUnloaderAlarminformation> util = new ExcelUtil<SbUnloaderAlarminformation>(SbUnloaderAlarminformation.class);
        return util.exportExcel(list, "sbUnloaderAlarminformation");
    }
	
	/**
	 * 新增卸料报警时段数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存卸料报警时段数据
	 */
	@RequiresPermissions("zhgd:sbUnloaderAlarminformation:add")
	@Log(title = "卸料报警时段数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbUnloaderAlarminformation sbUnloaderAlarminformation)
	{		
		return toAjax(sbUnloaderAlarminformationService.insertSbUnloaderAlarminformation(sbUnloaderAlarminformation));
	}

	/**
	 * 修改卸料报警时段数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbUnloaderAlarminformation sbUnloaderAlarminformation = sbUnloaderAlarminformationService.selectSbUnloaderAlarminformationById(id);
		mmap.put("sbUnloaderAlarminformation", sbUnloaderAlarminformation);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存卸料报警时段数据
	 */
	@RequiresPermissions("zhgd:sbUnloaderAlarminformation:edit")
	@Log(title = "卸料报警时段数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbUnloaderAlarminformation sbUnloaderAlarminformation)
	{		
		return toAjax(sbUnloaderAlarminformationService.updateSbUnloaderAlarminformation(sbUnloaderAlarminformation));
	}
	
	/**
	 * 删除卸料报警时段数据
	 */
	@RequiresPermissions("zhgd:sbUnloaderAlarminformation:remove")
	@Log(title = "卸料报警时段数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbUnloaderAlarminformationService.deleteSbUnloaderAlarminformationByIds(ids));
	}
	
}
