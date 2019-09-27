package com.hujiang.project.zhgd.hjBlacklist.controller;

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
import com.hujiang.project.zhgd.hjBlacklist.domain.HjBlacklist;
import com.hujiang.project.zhgd.hjBlacklist.service.IHjBlacklistService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 黑名单 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/zhgd/hjBlacklist")
public class HjBlacklistController extends BaseController
{
    private String prefix = "zhgd/hjBlacklist";
	
	@Autowired
	private IHjBlacklistService hjBlacklistService;
	
	@RequiresPermissions("zhgd:hjBlacklist:view")
	@GetMapping()
	public String hjBlacklist()
	{
	    return prefix + "/hjBlacklist";
	}
	
	/**
	 * 查询黑名单列表
	 */
	@RequiresPermissions("zhgd:hjBlacklist:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjBlacklist hjBlacklist)
	{
		startPage();
        List<HjBlacklist> list = hjBlacklistService.selectHjBlacklistList(hjBlacklist);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出黑名单列表
	 */
	@RequiresPermissions("zhgd:hjBlacklist:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjBlacklist hjBlacklist)
    {
    	List<HjBlacklist> list = hjBlacklistService.selectHjBlacklistList(hjBlacklist);
        ExcelUtil<HjBlacklist> util = new ExcelUtil<HjBlacklist>(HjBlacklist.class);
        return util.exportExcel(list, "hjBlacklist");
    }
	
	/**
	 * 新增黑名单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存黑名单
	 */
	@RequiresPermissions("zhgd:hjBlacklist:add")
	@Log(title = "黑名单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjBlacklist hjBlacklist)
	{		
		return toAjax(hjBlacklistService.insertHjBlacklist(hjBlacklist));
	}

	/**
	 * 修改黑名单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjBlacklist hjBlacklist = hjBlacklistService.selectHjBlacklistById(id);
		mmap.put("hjBlacklist", hjBlacklist);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存黑名单
	 */
	@RequiresPermissions("zhgd:hjBlacklist:edit")
	@Log(title = "黑名单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjBlacklist hjBlacklist)
	{		
		return toAjax(hjBlacklistService.updateHjBlacklist(hjBlacklist));
	}
	
	/**
	 * 删除黑名单
	 */
	@RequiresPermissions("zhgd:hjBlacklist:remove")
	@Log(title = "黑名单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjBlacklistService.deleteHjBlacklistByIds(ids));
	}
	
}
