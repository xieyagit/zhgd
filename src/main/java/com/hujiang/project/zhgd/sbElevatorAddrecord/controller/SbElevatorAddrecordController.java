package com.hujiang.project.zhgd.sbElevatorAddrecord.controller;

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
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbElevatorAddrecord.service.ISbElevatorAddrecordService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 2.5.3升降机实时数据 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-27
 */
@Controller
@RequestMapping("/zhgd/sbElevatorAddrecord")
public class SbElevatorAddrecordController extends BaseController
{
    private String prefix = "zhgd/sbElevatorAddrecord";
	
	@Autowired
	private ISbElevatorAddrecordService sbElevatorAddrecordService;
	
	@RequiresPermissions("zhgd:sbElevatorAddrecord:view")
	@GetMapping()
	public String sbElevatorAddrecord()
	{
	    return prefix + "/sbElevatorAddrecord";
	}
	
	/**
	 * 查询2.5.3升降机实时数据列表
	 */
	@RequiresPermissions("zhgd:sbElevatorAddrecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbElevatorAddrecord sbElevatorAddrecord)
	{
		startPage();
        List<SbElevatorAddrecord> list = sbElevatorAddrecordService.selectSbElevatorAddrecordList(sbElevatorAddrecord);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出2.5.3升降机实时数据列表
	 */
	@RequiresPermissions("zhgd:sbElevatorAddrecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbElevatorAddrecord sbElevatorAddrecord)
    {
    	List<SbElevatorAddrecord> list = sbElevatorAddrecordService.selectSbElevatorAddrecordList(sbElevatorAddrecord);
        ExcelUtil<SbElevatorAddrecord> util = new ExcelUtil<SbElevatorAddrecord>(SbElevatorAddrecord.class);
        return util.exportExcel(list, "sbElevatorAddrecord");
    }
	
	/**
	 * 新增2.5.3升降机实时数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存2.5.3升降机实时数据
	 */
	@RequiresPermissions("zhgd:sbElevatorAddrecord:add")
	@Log(title = "2.5.3升降机实时数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbElevatorAddrecord sbElevatorAddrecord)
	{		
		return toAjax(sbElevatorAddrecordService.insertSbElevatorAddrecord(sbElevatorAddrecord));
	}

	/**
	 * 修改2.5.3升降机实时数据
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbElevatorAddrecord sbElevatorAddrecord = sbElevatorAddrecordService.selectSbElevatorAddrecordById(id);
		mmap.put("sbElevatorAddrecord", sbElevatorAddrecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存2.5.3升降机实时数据
	 */
	@RequiresPermissions("zhgd:sbElevatorAddrecord:edit")
	@Log(title = "2.5.3升降机实时数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbElevatorAddrecord sbElevatorAddrecord)
	{		
		return toAjax(sbElevatorAddrecordService.updateSbElevatorAddrecord(sbElevatorAddrecord));
	}
	
	/**
	 * 删除2.5.3升降机实时数据
	 */
	@RequiresPermissions("zhgd:sbElevatorAddrecord:remove")
	@Log(title = "2.5.3升降机实时数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbElevatorAddrecordService.deleteSbElevatorAddrecordByIds(ids));
	}
	
}
