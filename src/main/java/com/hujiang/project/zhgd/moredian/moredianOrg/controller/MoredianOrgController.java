package com.hujiang.project.zhgd.moredian.moredianOrg.controller;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.moredian.MoredianMethod;
import com.hujiang.project.zhgd.moredian.moredianOrg.domain.MoredianOrg;
import com.hujiang.project.zhgd.moredian.moredianOrg.service.IMoredianOrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Controller
@RequestMapping("/moredian/moredianOrg")
public class MoredianOrgController extends BaseController
{
    private String prefix = "moredian/moredianOrg";
	
	@Autowired
	private IMoredianOrgService moredianOrgService;
	
	@RequiresPermissions("moredian:moredianOrg:view")
	@GetMapping()
	public String moredianOrg()
	{
	    return prefix + "/moredianOrg";
	}
	
	/**
	 * 查询机构列表
	 */
	@RequiresPermissions("moredian:moredianOrg:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MoredianOrg moredianOrg)
	{
		startPage();
        List<MoredianOrg> list = moredianOrgService.selectMoredianOrgList(moredianOrg);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出机构列表
	 */
	@RequiresPermissions("moredian:moredianOrg:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MoredianOrg moredianOrg)
    {
    	List<MoredianOrg> list = moredianOrgService.selectMoredianOrgList(moredianOrg);
        ExcelUtil<MoredianOrg> util = new ExcelUtil<MoredianOrg>(MoredianOrg.class);
        return util.exportExcel(list, "moredianOrg");
    }
	
	/**
	 * 新增机构
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存机构
	 */
	@RequiresPermissions("moredian:moredianOrg:add")
	@Log(title = "机构", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MoredianOrg moredianOrg)
	{
		JSONObject o = (JSONObject)JSONObject.toJSON(moredianOrg);
		o.remove("orgAuthKey");
		o.remove("orgId");
		//添加机构
		JSONObject org = MoredianMethod.createOrg(o);
		if(org.getString("result").equals("0")){
			JSONObject data = JSONObject.parseObject(org.getString("data"));
			moredianOrg.setOrgId(data.getString("orgId"));
			moredianOrg.setOrgAuthKey(data.getString("orgAuthKey"));
			return toAjax(moredianOrgService.insertMoredianOrg(moredianOrg));
		}
		return toAjax(0);
	}

	/**
	 * 修改机构
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MoredianOrg moredianOrg = moredianOrgService.selectMoredianOrgById(id);
		mmap.put("moredianOrg", moredianOrg);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存机构
	 */
	@RequiresPermissions("moredian:moredianOrg:edit")
	@Log(title = "机构", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MoredianOrg moredianOrg)
	{		
		return toAjax(moredianOrgService.updateMoredianOrg(moredianOrg));
	}
	
	/**
	 * 删除机构
	 */
	@RequiresPermissions("moredian:moredianOrg:remove")
	@Log(title = "机构", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(moredianOrgService.deleteMoredianOrgByIds(ids));
	}
	
}
