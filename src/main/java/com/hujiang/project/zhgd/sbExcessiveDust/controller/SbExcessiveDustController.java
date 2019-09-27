package com.hujiang.project.zhgd.sbExcessiveDust.controller;

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
import com.hujiang.project.zhgd.sbExcessiveDust.domain.SbExcessiveDust;
import com.hujiang.project.zhgd.sbExcessiveDust.service.ISbExcessiveDustService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 扬尘超标记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-25
 */
@Controller
@RequestMapping("/zhgd/sbExcessiveDust")
public class SbExcessiveDustController extends BaseController
{
    private String prefix = "zhgd/sbExcessiveDust";
	
	@Autowired
	private ISbExcessiveDustService sbExcessiveDustService;
	
	@RequiresPermissions("zhgd:sbExcessiveDust:view")
	@GetMapping()
	public String sbExcessiveDust()
	{
	    return prefix + "/sbExcessiveDust";
	}
	
	/**
	 * 查询扬尘超标记录列表
	 */
	@RequiresPermissions("zhgd:sbExcessiveDust:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbExcessiveDust sbExcessiveDust)
	{
		startPage();
        List<SbExcessiveDust> list = sbExcessiveDustService.selectSbExcessiveDustList(sbExcessiveDust);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出扬尘超标记录列表
	 */
	@RequiresPermissions("zhgd:sbExcessiveDust:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbExcessiveDust sbExcessiveDust)
    {
    	List<SbExcessiveDust> list = sbExcessiveDustService.selectSbExcessiveDustList(sbExcessiveDust);
        ExcelUtil<SbExcessiveDust> util = new ExcelUtil<SbExcessiveDust>(SbExcessiveDust.class);
        return util.exportExcel(list, "sbExcessiveDust");
    }
	
	/**
	 * 新增扬尘超标记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存扬尘超标记录
	 */
	@RequiresPermissions("zhgd:sbExcessiveDust:add")
	@Log(title = "扬尘超标记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbExcessiveDust sbExcessiveDust)
	{		
		return toAjax(sbExcessiveDustService.insertSbExcessiveDust(sbExcessiveDust));
	}

	/**
	 * 修改扬尘超标记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbExcessiveDust sbExcessiveDust = sbExcessiveDustService.selectSbExcessiveDustById(id);
		mmap.put("sbExcessiveDust", sbExcessiveDust);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存扬尘超标记录
	 */
	@RequiresPermissions("zhgd:sbExcessiveDust:edit")
	@Log(title = "扬尘超标记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbExcessiveDust sbExcessiveDust)
	{		
		return toAjax(sbExcessiveDustService.updateSbExcessiveDust(sbExcessiveDust));
	}
	
	/**
	 * 删除扬尘超标记录
	 */
	@RequiresPermissions("zhgd:sbExcessiveDust:remove")
	@Log(title = "扬尘超标记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbExcessiveDustService.deleteSbExcessiveDustByIds(ids));
	}
	
}
