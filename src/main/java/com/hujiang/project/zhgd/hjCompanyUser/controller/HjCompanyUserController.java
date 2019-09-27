package com.hujiang.project.zhgd.hjCompanyUser.controller;

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
import com.hujiang.project.zhgd.hjCompanyUser.domain.HjCompanyUser;
import com.hujiang.project.zhgd.hjCompanyUser.service.IHjCompanyUserService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 公司用户 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjCompanyUser")
public class HjCompanyUserController extends BaseController
{
    private String prefix = "zhgd/hjCompanyUser";
	
	@Autowired
	private IHjCompanyUserService hjCompanyUserService;
	
	@RequiresPermissions("zhgd:hjCompanyUser:view")
	@GetMapping()
	public String hjCompanyUser()
	{
	    return prefix + "/hjCompanyUser";
	}
	
	/**
	 * 查询公司用户列表
	 */
	@RequiresPermissions("zhgd:hjCompanyUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjCompanyUser hjCompanyUser)
	{
		startPage();
        List<HjCompanyUser> list = hjCompanyUserService.selectHjCompanyUserList(hjCompanyUser);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出公司用户列表
	 */
	@RequiresPermissions("zhgd:hjCompanyUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjCompanyUser hjCompanyUser)
    {
    	List<HjCompanyUser> list = hjCompanyUserService.selectHjCompanyUserList(hjCompanyUser);
        ExcelUtil<HjCompanyUser> util = new ExcelUtil<HjCompanyUser>(HjCompanyUser.class);
        return util.exportExcel(list, "hjCompanyUser");
    }
	
	/**
	 * 新增公司用户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存公司用户
	 */
	@RequiresPermissions("zhgd:hjCompanyUser:add")
	@Log(title = "公司用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjCompanyUser hjCompanyUser)
	{		
		return toAjax(hjCompanyUserService.insertHjCompanyUser(hjCompanyUser));
	}

	/**
	 * 修改公司用户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjCompanyUser hjCompanyUser = hjCompanyUserService.selectHjCompanyUserById(id);
		mmap.put("hjCompanyUser", hjCompanyUser);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存公司用户
	 */
	@RequiresPermissions("zhgd:hjCompanyUser:edit")
	@Log(title = "公司用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjCompanyUser hjCompanyUser)
	{		
		return toAjax(hjCompanyUserService.updateHjCompanyUser(hjCompanyUser));
	}
	
	/**
	 * 删除公司用户
	 */
	@RequiresPermissions("zhgd:hjCompanyUser:remove")
	@Log(title = "公司用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjCompanyUserService.deleteHjCompanyUserByIds(ids));
	}
	
}
