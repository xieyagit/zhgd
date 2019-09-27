package com.hujiang.project.zhgd.moredian.moredianOrgCallback.controller;

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
import com.hujiang.project.zhgd.moredian.moredianOrgCallback.domain.MoredianOrgCallback;
import com.hujiang.project.zhgd.moredian.moredianOrgCallback.service.IMoredianOrgCallbackService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 回调 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-13
 */
@Controller
@RequestMapping("/moredian/moredianOrgCallback")
public class MoredianOrgCallbackController extends BaseController
{
    private String prefix = "moredian/moredianOrgCallback";
	
	@Autowired
	private IMoredianOrgCallbackService moredianOrgCallbackService;
	@Autowired
	private IMoredianOrgService iMoredianOrgService;



	@RequiresPermissions("moredian:moredianOrgCallback:view")
	@GetMapping()
	public String moredianOrgCallback()
	{
	    return prefix + "/moredianOrgCallback";
	}
	
	/**
	 * 查询回调列表
	 */
	@RequiresPermissions("moredian:moredianOrgCallback:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MoredianOrgCallback moredianOrgCallback)
	{
		startPage();
        List<MoredianOrgCallback> list = moredianOrgCallbackService.selectMoredianOrgCallbackList(moredianOrgCallback);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出回调列表
	 */
	@RequiresPermissions("moredian:moredianOrgCallback:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MoredianOrgCallback moredianOrgCallback)
    {
    	List<MoredianOrgCallback> list = moredianOrgCallbackService.selectMoredianOrgCallbackList(moredianOrgCallback);
        ExcelUtil<MoredianOrgCallback> util = new ExcelUtil<MoredianOrgCallback>(MoredianOrgCallback.class);
        return util.exportExcel(list, "moredianOrgCallback");
    }
	
	/**
	 * 新增回调
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap) {
		mmap.put("org", iMoredianOrgService.selectMoredianOrgList(null));
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存回调
	 */
	@RequiresPermissions("moredian:moredianOrgCallback:add")
	@Log(title = "回调", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MoredianOrgCallback moredianOrgCallback)
	{
		System.out.println(moredianOrgCallback);
		//获取机构
		MoredianOrg moredianOrg = iMoredianOrgService.selectMoredianOrgById(Integer.parseInt(moredianOrgCallback.getOrgId()));
		moredianOrgCallback.setOrgId(moredianOrg.getOrgId());
		System.out.println(moredianOrg);
		//获取机构accessToken
		String orgAccessToken = MoredianMethod.getOrgAccessToken(moredianOrg.getOrgAuthKey(), moredianOrg.getOrgId());
		System.out.println("获取机构accessToken:" + orgAccessToken);
		JSONObject object = new JSONObject();
		object.put("callbackUrl",moredianOrgCallback.getCallbackUrl());
		object.put("callbackTag",moredianOrgCallback.getCallbackTag());
		JSONObject object1 = MoredianMethod.callbackAddOrgCallback(orgAccessToken, object);
		System.out.println("注册回调："+object1);
		return toAjax(moredianOrgCallbackService.insertMoredianOrgCallback(moredianOrgCallback));
	}

	/**
	 * 修改回调
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MoredianOrgCallback moredianOrgCallback = moredianOrgCallbackService.selectMoredianOrgCallbackById(id);
		mmap.put("moredianOrgCallback", moredianOrgCallback);
		mmap.put("org", iMoredianOrgService.selectMoredianOrgList(null));
	    return prefix + "/edit";
	}

	/**
	 * 修改保存回调
	 */
	@RequiresPermissions("moredian:moredianOrgCallback:edit")
	@Log(title = "回调", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MoredianOrgCallback moredianOrgCallback)
	{
		MoredianOrg moredianOrg = iMoredianOrgService.selectMoredianOrgById(Integer.parseInt(moredianOrgCallback.getOrgId()));
		moredianOrgCallback.setOrgId(moredianOrg.getOrgId());
		System.out.println(moredianOrg);
		//获取机构accessToken
		String orgAccessToken = MoredianMethod.getOrgAccessToken(moredianOrg.getOrgAuthKey(), moredianOrg.getOrgId());
		System.out.println("获取机构accessToken:" + orgAccessToken);
		JSONObject object = new JSONObject();
		object.put("callbackUrl",moredianOrgCallback.getCallbackUrl());
		object.put("callbackTag",moredianOrgCallback.getCallbackTag());
		JSONObject object1 = MoredianMethod.callbackUpdateOrgCallback(orgAccessToken, object);
		System.out.println("修改回调："+object1);
		return toAjax(moredianOrgCallbackService.updateMoredianOrgCallback(moredianOrgCallback));
	}
	
	/**
	 * 删除回调
	 */
	@RequiresPermissions("moredian:moredianOrgCallback:remove")
	@Log(title = "回调", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(moredianOrgCallbackService.deleteMoredianOrgCallbackByIds(ids));
	}
	
}
