package com.hujiang.project.zhgd.hjMenu.controller;

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
import com.hujiang.project.zhgd.hjMenu.domain.HjMenu;
import com.hujiang.project.zhgd.hjMenu.service.IHjMenuService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 两制服务菜单 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/zhgd/hjMenu")
public class HjMenuController extends BaseController
{
    private String prefix = "zhgd/hjMenu";
	
	@Autowired
	private IHjMenuService hjMenuService;
	
	@RequiresPermissions("zhgd:hjMenu:view")
	@GetMapping()
	public String hjMenu()
	{
	    return prefix + "/hjMenu";
	}
	
	/**
	 * 查询两制服务菜单列表
	 */
	@RequiresPermissions("zhgd:hjMenu:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjMenu hjMenu)
	{
		startPage();
        List<HjMenu> list = hjMenuService.selectHjMenuList(hjMenu);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出两制服务菜单列表
	 */
	@RequiresPermissions("zhgd:hjMenu:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjMenu hjMenu)
    {
    	List<HjMenu> list = hjMenuService.selectHjMenuList(hjMenu);
        ExcelUtil<HjMenu> util = new ExcelUtil<HjMenu>(HjMenu.class);
        return util.exportExcel(list, "hjMenu");
    }
	
	/**
	 * 新增两制服务菜单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存两制服务菜单
	 */
	@RequiresPermissions("zhgd:hjMenu:add")
	@Log(title = "两制服务菜单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjMenu hjMenu)
	{		
		return toAjax(hjMenuService.insertHjMenu(hjMenu));
	}

	/**
	 * 修改两制服务菜单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjMenu hjMenu = hjMenuService.selectHjMenuById(id);
		mmap.put("hjMenu", hjMenu);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存两制服务菜单
	 */
	@RequiresPermissions("zhgd:hjMenu:edit")
	@Log(title = "两制服务菜单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjMenu hjMenu)
	{		
		return toAjax(hjMenuService.updateHjMenu(hjMenu));
	}
	
	/**
	 * 删除两制服务菜单
	 */
	@RequiresPermissions("zhgd:hjMenu:remove")
	@Log(title = "两制服务菜单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjMenuService.deleteHjMenuByIds(ids));
	}
	
}
