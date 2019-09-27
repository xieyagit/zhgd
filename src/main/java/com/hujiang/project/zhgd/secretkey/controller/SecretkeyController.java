package com.hujiang.project.zhgd.secretkey.controller;

import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.secretkey.domain.Secretkey;
import com.hujiang.project.zhgd.secretkey.service.ISecretkeyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 智慧工地对接秘钥 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/secretkey")
public class SecretkeyController extends BaseController
{
    private String prefix;
	
	@Autowired
	private ISecretkeyService secretkeyService;

	public SecretkeyController() {
		prefix = "zhgd/secretkey";
	}

	@RequiresPermissions("zhgd:secretkey:view")
	@GetMapping()
	public String secretkey()
	{
	    return prefix + "/secretkey";
	}
	
	/**
	 * 查询智慧工地对接秘钥列表
	 */
	@RequiresPermissions("zhgd:secretkey:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Secretkey secretkey)
	{
		startPage();
        List<Secretkey> list = secretkeyService.selectSecretkeyList(secretkey);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出智慧工地对接秘钥列表
	 */
	@RequiresPermissions("zhgd:secretkey:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Secretkey secretkey)
    {
    	List<Secretkey> list = secretkeyService.selectSecretkeyList(secretkey);
        ExcelUtil<Secretkey> util = new ExcelUtil<Secretkey>(Secretkey.class);
        return util.exportExcel(list, "secretkey");
    }
	
	/**
	 * 新增智慧工地对接秘钥
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存智慧工地对接秘钥
	 */
	@RequiresPermissions("zhgd:secretkey:add")
	@Log(title = "智慧工地对接秘钥", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Secretkey secretkey)
	{		
		return toAjax(secretkeyService.insertSecretkey(secretkey));
	}

	/**
	 * 修改智慧工地对接秘钥
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Secretkey secretkey = secretkeyService.selectSecretkeyById(id);
		mmap.put("secretkey", secretkey);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存智慧工地对接秘钥
	 */
	@RequiresPermissions("zhgd:secretkey:edit")
	@Log(title = "智慧工地对接秘钥", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Secretkey secretkey)
	{		
		return toAjax(secretkeyService.updateSecretkey(secretkey));
	}
	
	/**
	 * 删除智慧工地对接秘钥
	 */
	@RequiresPermissions("zhgd:secretkey:remove")
	@Log(title = "智慧工地对接秘钥", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(secretkeyService.deleteSecretkeyByIds(ids));
	}
	
}
