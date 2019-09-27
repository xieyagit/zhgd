package com.hujiang.project.zhgd.hjConstructionUser.controller;

import org.springframework.stereotype.Controller;
import java.util.List;
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
import com.hujiang.project.zhgd.hjConstructionUser.domain.HjConstructionUser;
import com.hujiang.project.zhgd.hjConstructionUser.service.IHjConstructionUserService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 分包单位与用户关系 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-10
 */
@Controller
@RequestMapping("/zhgd/hjConstructionUser")
public class HjConstructionUserController extends BaseController
{
    private String prefix = "zhgd/hjConstructionUser";
	
	@Autowired
	private IHjConstructionUserService hjConstructionUserService;
	
	@RequiresPermissions("zhgd:hjConstructionUser:view")
	@GetMapping()
	public String hjConstructionUser()
	{
	    return prefix + "/hjConstructionUser";
	}
	
	/**
	 * 查询分包单位与用户关系列表
	 */
	@RequiresPermissions("zhgd:hjConstructionUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjConstructionUser hjConstructionUser)
	{
		startPage();
        List<HjConstructionUser> list = hjConstructionUserService.selectHjConstructionUserList(hjConstructionUser);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出分包单位与用户关系列表
	 */
	@RequiresPermissions("zhgd:hjConstructionUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjConstructionUser hjConstructionUser)
    {
    	List<HjConstructionUser> list = hjConstructionUserService.selectHjConstructionUserList(hjConstructionUser);
        ExcelUtil<HjConstructionUser> util = new ExcelUtil<HjConstructionUser>(HjConstructionUser.class);
        return util.exportExcel(list, "hjConstructionUser");
    }
	
	/**
	 * 新增分包单位与用户关系
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存分包单位与用户关系
	 */
	@RequiresPermissions("zhgd:hjConstructionUser:add")
	@Log(title = "分包单位与用户关系", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjConstructionUser hjConstructionUser)
	{		
		return toAjax(hjConstructionUserService.insertHjConstructionUser(hjConstructionUser));
	}

	/**
	 * 修改分包单位与用户关系
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjConstructionUser hjConstructionUser = hjConstructionUserService.selectHjConstructionUserById(id);
		mmap.put("hjConstructionUser", hjConstructionUser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存分包单位与用户关系
	 */
	@RequiresPermissions("zhgd:hjConstructionUser:edit")
	@Log(title = "分包单位与用户关系", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjConstructionUser hjConstructionUser)
	{		
		return toAjax(hjConstructionUserService.updateHjConstructionUser(hjConstructionUser));
	}
	
	/**
	 * 删除分包单位与用户关系
	 */
	@RequiresPermissions("zhgd:hjConstructionUser:remove")
	@Log(title = "分包单位与用户关系", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjConstructionUserService.deleteHjConstructionUserByIds(ids));
	}
	
}
