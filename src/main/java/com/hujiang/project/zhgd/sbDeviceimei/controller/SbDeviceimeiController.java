package com.hujiang.project.zhgd.sbDeviceimei.controller;

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
import com.hujiang.project.zhgd.sbDeviceimei.domain.SbDeviceimei;
import com.hujiang.project.zhgd.sbDeviceimei.service.ISbDeviceimeiService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 设备编号
 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-29
 */
@Controller
@RequestMapping("/zhgd/sbDeviceimei")
public class SbDeviceimeiController extends BaseController
{
    private String prefix = "zhgd/sbDeviceimei";
	
	@Autowired
	private ISbDeviceimeiService sbDeviceimeiService;
	
	@RequiresPermissions("zhgd:sbDeviceimei:view")
	@GetMapping()
	public String sbDeviceimei()
	{
	    return prefix + "/sbDeviceimei";
	}
	
	/**
	 * 查询设备编号
列表
	 */
	@RequiresPermissions("zhgd:sbDeviceimei:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbDeviceimei sbDeviceimei)
	{
		startPage();
        List<SbDeviceimei> list = sbDeviceimeiService.selectSbDeviceimeiList(sbDeviceimei);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出设备编号
列表
	 */
	@RequiresPermissions("zhgd:sbDeviceimei:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbDeviceimei sbDeviceimei)
    {
    	List<SbDeviceimei> list = sbDeviceimeiService.selectSbDeviceimeiList(sbDeviceimei);
        ExcelUtil<SbDeviceimei> util = new ExcelUtil<SbDeviceimei>(SbDeviceimei.class);
        return util.exportExcel(list, "sbDeviceimei");
    }
	
	/**
	 * 新增设备编号

	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存设备编号

	 */
	@RequiresPermissions("zhgd:sbDeviceimei:add")
	@Log(title = "设备编号", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbDeviceimei sbDeviceimei)
	{		
		return toAjax(sbDeviceimeiService.insertSbDeviceimei(sbDeviceimei));
	}

	/**
	 * 修改设备编号

	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbDeviceimei sbDeviceimei = sbDeviceimeiService.selectSbDeviceimeiById(id);
		mmap.put("sbDeviceimei", sbDeviceimei);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存设备编号

	 */
	@RequiresPermissions("zhgd:sbDeviceimei:edit")
	@Log(title = "设备编号", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbDeviceimei sbDeviceimei)
	{		
		return toAjax(sbDeviceimeiService.updateSbDeviceimei(sbDeviceimei));
	}
	
	/**
	 * 删除设备编号

	 */
	@RequiresPermissions("zhgd:sbDeviceimei:remove")
	@Log(title = "设备编号", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbDeviceimeiService.deleteSbDeviceimeiByIds(ids));
	}
	
}
