package com.hujiang.project.zhgd.sbDoorLock.controller;

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
import com.hujiang.project.zhgd.sbDoorLock.domain.SbDoorLock;
import com.hujiang.project.zhgd.sbDoorLock.service.ISbDoorLockService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 门锁记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-24
 */
@Controller
@RequestMapping("/zhgd/sbDoorLock")
public class SbDoorLockController extends BaseController
{
    private String prefix = "zhgd/sbDoorLock";
	
	@Autowired
	private ISbDoorLockService sbDoorLockService;
	
	@RequiresPermissions("zhgd:sbDoorLock:view")
	@GetMapping()
	public String sbDoorLock()
	{
	    return prefix + "/sbDoorLock";
	}
	
	/**
	 * 查询门锁记录列表
	 */
	@RequiresPermissions("zhgd:sbDoorLock:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbDoorLock sbDoorLock)
	{
		startPage();
        List<SbDoorLock> list = sbDoorLockService.selectSbDoorLockList(sbDoorLock);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出门锁记录列表
	 */
	@RequiresPermissions("zhgd:sbDoorLock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbDoorLock sbDoorLock)
    {
    	List<SbDoorLock> list = sbDoorLockService.selectSbDoorLockList(sbDoorLock);
        ExcelUtil<SbDoorLock> util = new ExcelUtil<SbDoorLock>(SbDoorLock.class);
        return util.exportExcel(list, "sbDoorLock");
    }
	
	/**
	 * 新增门锁记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存门锁记录
	 */
	@RequiresPermissions("zhgd:sbDoorLock:add")
	@Log(title = "门锁记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbDoorLock sbDoorLock)
	{		
		return toAjax(sbDoorLockService.insertSbDoorLock(sbDoorLock));
	}

	/**
	 * 修改门锁记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbDoorLock sbDoorLock = sbDoorLockService.selectSbDoorLockById(id);
		mmap.put("sbDoorLock", sbDoorLock);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存门锁记录
	 */
	@RequiresPermissions("zhgd:sbDoorLock:edit")
	@Log(title = "门锁记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbDoorLock sbDoorLock)
	{		
		return toAjax(sbDoorLockService.updateSbDoorLock(sbDoorLock));
	}
	
	/**
	 * 删除门锁记录
	 */
	@RequiresPermissions("zhgd:sbDoorLock:remove")
	@Log(title = "门锁记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbDoorLockService.deleteSbDoorLockByIds(ids));
	}
	
}
