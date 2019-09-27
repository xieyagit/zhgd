package com.hujiang.project.zhgd.sbDustEmission.controller;

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
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.service.ISbDustEmissionService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 扬尘数据 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-18
 */
@Controller
@RequestMapping("/zhgd/sbDustEmission")
public class SbDustEmissionController extends BaseController
{
    private String prefix = "zhgd/sbDustEmission";
	
	@Autowired
	private ISbDustEmissionService sbDustEmissionService;
	
	@RequiresPermissions("zhgd:sbDustEmission:view")
	@GetMapping()
	public String sbDustEmission()
	{
	    return prefix + "/sbDustEmission";
	}
	
	/**
	 * 查询扬尘数据列表
	 */
	@RequiresPermissions("zhgd:sbDustEmission:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbDustEmission sbDustEmission)
	{
		startPage();
        List<SbDustEmission> list = sbDustEmissionService.selectSbDustEmissionList(sbDustEmission);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出扬尘数据列表
	 */
	@RequiresPermissions("zhgd:sbDustEmission:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbDustEmission sbDustEmission)
    {
    	List<SbDustEmission> list = sbDustEmissionService.selectSbDustEmissionList(sbDustEmission);
        ExcelUtil<SbDustEmission> util = new ExcelUtil<SbDustEmission>(SbDustEmission.class);
        return util.exportExcel(list, "sbDustEmission");
    }
	
	/**
	 * 新增扬尘数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存扬尘数据
	 */
	@RequiresPermissions("zhgd:sbDustEmission:add")
	@Log(title = "扬尘数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbDustEmission sbDustEmission)
	{		
		return toAjax(sbDustEmissionService.insertSbDustEmission(sbDustEmission));
	}

	/**
	 * 修改扬尘数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SbDustEmission sbDustEmission = sbDustEmissionService.selectSbDustEmissionById(id);
		mmap.put("sbDustEmission", sbDustEmission);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存扬尘数据
	 */
	@RequiresPermissions("zhgd:sbDustEmission:edit")
	@Log(title = "扬尘数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbDustEmission sbDustEmission)
	{		
		return toAjax(sbDustEmissionService.updateSbDustEmission(sbDustEmission));
	}
	
	/**
	 * 删除扬尘数据
	 */
	@RequiresPermissions("zhgd:sbDustEmission:remove")
	@Log(title = "扬尘数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbDustEmissionService.deleteSbDustEmissionByIds(ids));
	}
	
}
