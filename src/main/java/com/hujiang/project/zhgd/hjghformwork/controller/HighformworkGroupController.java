package com.hujiang.project.zhgd.hjghformwork.controller;

import java.util.List;

import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkGroup;
import com.hujiang.project.zhgd.hjghformwork.service.IHighformworkGroupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 高支模测点 信息操作处理
 * 
 * @author hujiang
 * @date 2019-09-09
 */
@Controller
@RequestMapping("/xianxin/highformworkGroup")
public class HighformworkGroupController extends BaseController
{
    private String prefix = "xianxin/highformworkGroup";
	
	@Autowired
	private IHighformworkGroupService highformworkGroupService;
	
	@RequiresPermissions("xianxin:highformworkGroup:view")
	@GetMapping()
	public String highformworkGroup()
	{
	    return prefix + "/highformworkGroup";
	}
	
	/**
	 * 查询高支模测点列表
	 */
	@RequiresPermissions("xianxin:highformworkGroup:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HighformworkGroup highformworkGroup)
	{
		startPage();
        List<HighformworkGroup> list = highformworkGroupService.selectHighformworkGroupList(highformworkGroup);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出高支模测点列表
	 */
	@RequiresPermissions("xianxin:highformworkGroup:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HighformworkGroup highformworkGroup)
    {
    	List<HighformworkGroup> list = highformworkGroupService.selectHighformworkGroupList(highformworkGroup);
        ExcelUtil<HighformworkGroup> util = new ExcelUtil<HighformworkGroup>(HighformworkGroup.class);
        return util.exportExcel(list, "highformworkGroup");
    }
	
	/**
	 * 新增高支模测点
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存高支模测点
	 */
	@RequiresPermissions("xianxin:highformworkGroup:add")
	@Log(title = "高支模测点", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HighformworkGroup highformworkGroup)
	{		
		return toAjax(highformworkGroupService.insertHighformworkGroup(highformworkGroup));
	}

	/**
	 * 修改高支模测点
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HighformworkGroup highformworkGroup = highformworkGroupService.selectHighformworkGroupById(id);
		mmap.put("highformworkGroup", highformworkGroup);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存高支模测点
	 */
	@RequiresPermissions("xianxin:highformworkGroup:edit")
	@Log(title = "高支模测点", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HighformworkGroup highformworkGroup)
	{		
		return toAjax(highformworkGroupService.updateHighformworkGroup(highformworkGroup));
	}
	
	/**
	 * 删除高支模测点
	 */
	@RequiresPermissions("xianxin:highformworkGroup:remove")
	@Log(title = "高支模测点", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(highformworkGroupService.deleteHighformworkGroupByIds(ids));
	}
	
}
