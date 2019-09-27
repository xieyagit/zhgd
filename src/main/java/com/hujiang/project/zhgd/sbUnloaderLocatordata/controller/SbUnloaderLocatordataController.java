package com.hujiang.project.zhgd.sbUnloaderLocatordata.controller;

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
import com.hujiang.project.zhgd.sbUnloaderLocatordata.domain.SbUnloaderLocatordata;
import com.hujiang.project.zhgd.sbUnloaderLocatordata.service.ISbUnloaderLocatordataService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 卸料GPS定位数据
 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-11
 */
@Controller
@RequestMapping("/zhgd/sbUnloaderLocatordata")
public class SbUnloaderLocatordataController extends BaseController
{
    private String prefix = "zhgd/sbUnloaderLocatordata";
	
	@Autowired
	private ISbUnloaderLocatordataService sbUnloaderLocatordataService;
	
	@RequiresPermissions("zhgd:sbUnloaderLocatordata:view")
	@GetMapping()
	public String sbUnloaderLocatordata()
	{
	    return prefix + "/sbUnloaderLocatordata";
	}
	
	/**
	 * 查询卸料GPS定位数据
列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderLocatordata:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbUnloaderLocatordata sbUnloaderLocatordata)
	{
		startPage();
        List<SbUnloaderLocatordata> list = sbUnloaderLocatordataService.selectSbUnloaderLocatordataList(sbUnloaderLocatordata);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出卸料GPS定位数据
列表
	 */
	@RequiresPermissions("zhgd:sbUnloaderLocatordata:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbUnloaderLocatordata sbUnloaderLocatordata)
    {
    	List<SbUnloaderLocatordata> list = sbUnloaderLocatordataService.selectSbUnloaderLocatordataList(sbUnloaderLocatordata);
        ExcelUtil<SbUnloaderLocatordata> util = new ExcelUtil<SbUnloaderLocatordata>(SbUnloaderLocatordata.class);
        return util.exportExcel(list, "sbUnloaderLocatordata");
    }
	
	/**
	 * 新增卸料GPS定位数据

	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存卸料GPS定位数据

	 */
	@RequiresPermissions("zhgd:sbUnloaderLocatordata:add")
	@Log(title = "卸料GPS定位数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbUnloaderLocatordata sbUnloaderLocatordata)
	{		
		return toAjax(sbUnloaderLocatordataService.insertSbUnloaderLocatordata(sbUnloaderLocatordata));
	}

	/**
	 * 修改卸料GPS定位数据

	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbUnloaderLocatordata sbUnloaderLocatordata = sbUnloaderLocatordataService.selectSbUnloaderLocatordataById(id);
		mmap.put("sbUnloaderLocatordata", sbUnloaderLocatordata);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存卸料GPS定位数据

	 */
	@RequiresPermissions("zhgd:sbUnloaderLocatordata:edit")
	@Log(title = "卸料GPS定位数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbUnloaderLocatordata sbUnloaderLocatordata)
	{		
		return toAjax(sbUnloaderLocatordataService.updateSbUnloaderLocatordata(sbUnloaderLocatordata));
	}
	
	/**
	 * 删除卸料GPS定位数据

	 */
	@RequiresPermissions("zhgd:sbUnloaderLocatordata:remove")
	@Log(title = "卸料GPS定位数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbUnloaderLocatordataService.deleteSbUnloaderLocatordataByIds(ids));
	}
	
}
