package com.hujiang.project.zhgd.sbProjectDustEmission.controller;

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
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.service.ISbProjectDustEmissionService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 项目对应的扬尘设备SN 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-18
 */
@Controller
@RequestMapping("/zhgd/sbProjectDustEmission")
public class SbProjectDustEmissionController extends BaseController
{
    private String prefix = "zhgd/sbProjectDustEmission";
	
	@Autowired
	private ISbProjectDustEmissionService sbProjectDustEmissionService;
	
	@RequiresPermissions("zhgd:sbProjectDustEmission:view")
	@GetMapping()
	public String sbProjectDustEmission()
	{
	    return prefix + "/sbProjectDustEmission";
	}
	
	/**
	 * 查询项目对应的扬尘设备SN列表
	 */
	@RequiresPermissions("zhgd:sbProjectDustEmission:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbProjectDustEmission sbProjectDustEmission)
	{
		startPage();
        List<SbProjectDustEmission> list = sbProjectDustEmissionService.selectSbProjectDustEmissionList(sbProjectDustEmission);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出项目对应的扬尘设备SN列表
	 */
	@RequiresPermissions("zhgd:sbProjectDustEmission:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbProjectDustEmission sbProjectDustEmission)
    {
    	List<SbProjectDustEmission> list = sbProjectDustEmissionService.selectSbProjectDustEmissionList(sbProjectDustEmission);
        ExcelUtil<SbProjectDustEmission> util = new ExcelUtil<SbProjectDustEmission>(SbProjectDustEmission.class);
        return util.exportExcel(list, "sbProjectDustEmission");
    }
	
	/**
	 * 新增项目对应的扬尘设备SN
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目对应的扬尘设备SN
	 */
	@RequiresPermissions("zhgd:sbProjectDustEmission:add")
	@Log(title = "项目对应的扬尘设备SN", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbProjectDustEmission sbProjectDustEmission)
	{		
		return toAjax(sbProjectDustEmissionService.insertSbProjectDustEmission(sbProjectDustEmission));
	}

	/**
	 * 修改项目对应的扬尘设备SN
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SbProjectDustEmission sbProjectDustEmission = sbProjectDustEmissionService.selectSbProjectDustEmissionById(id);
		mmap.put("sbProjectDustEmission", sbProjectDustEmission);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目对应的扬尘设备SN
	 */
	@RequiresPermissions("zhgd:sbProjectDustEmission:edit")
	@Log(title = "项目对应的扬尘设备SN", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbProjectDustEmission sbProjectDustEmission)
	{		
		return toAjax(sbProjectDustEmissionService.updateSbProjectDustEmission(sbProjectDustEmission));
	}
	
	/**
	 * 删除项目对应的扬尘设备SN
	 */
	@RequiresPermissions("zhgd:sbProjectDustEmission:remove")
	@Log(title = "项目对应的扬尘设备SN", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(Integer id)
	{
		return toAjax(sbProjectDustEmissionService.deleteSbProjectDustEmissionByIds(id));
	}
	
}
