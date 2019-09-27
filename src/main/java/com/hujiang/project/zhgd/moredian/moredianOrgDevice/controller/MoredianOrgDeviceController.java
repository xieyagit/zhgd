package com.hujiang.project.zhgd.moredian.moredianOrgDevice.controller;

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
import com.hujiang.project.zhgd.moredian.moredianOrgDevice.domain.MoredianOrgDevice;
import com.hujiang.project.zhgd.moredian.moredianOrgDevice.service.IMoredianOrgDeviceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机构设备 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Controller
@RequestMapping("/moredian/moredianOrgDevice")
public class MoredianOrgDeviceController extends BaseController
{
    private String prefix = "moredian/moredianOrgDevice";
	
	@Autowired
	private IMoredianOrgDeviceService moredianOrgDeviceService;
	@Autowired
	private IMoredianOrgService iMoredianOrgService;
	
	@RequiresPermissions("moredian:moredianOrgDevice:view")
	@GetMapping()
	public String moredianOrgDevice()
	{
	    return prefix + "/moredianOrgDevice";
	}
	
	/**
	 * 查询机构设备列表
	 */
	@RequiresPermissions("moredian:moredianOrgDevice:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MoredianOrgDevice moredianOrgDevice)
	{
		startPage();
        List<MoredianOrgDevice> list = moredianOrgDeviceService.selectMoredianOrgDeviceList(moredianOrgDevice);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出机构设备列表
	 */
	@RequiresPermissions("moredian:moredianOrgDevice:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MoredianOrgDevice moredianOrgDevice)
    {
    	List<MoredianOrgDevice> list = moredianOrgDeviceService.selectMoredianOrgDeviceList(moredianOrgDevice);
        ExcelUtil<MoredianOrgDevice> util = new ExcelUtil<MoredianOrgDevice>(MoredianOrgDevice.class);
        return util.exportExcel(list, "moredianOrgDevice");
    }
	
	/**
	 * 新增机构设备
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		mmap.put("org",iMoredianOrgService.selectMoredianOrgList(null));
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存机构设备
	 */
	@RequiresPermissions("moredian:moredianOrgDevice:add")
	@Log(title = "机构设备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MoredianOrgDevice moredianOrgDevice, String qrcode)
	{
		JSONObject object = new JSONObject();
		//获取机构
		MoredianOrg moredianOrg = iMoredianOrgService.selectMoredianOrgById(moredianOrgDevice.getOrgId());
		System.out.println(moredianOrg);
		//获取机构accessToken
		String orgAccessToken = MoredianMethod.getOrgAccessToken(moredianOrg.getOrgAuthKey(), moredianOrg.getOrgId());
		System.out.println("获取机构accessToken:"+orgAccessToken);
		//设备激活
		object.put("qrCode",qrcode);
		JSONObject equipmentActivation = MoredianMethod.equipmentActivation(orgAccessToken, object);
		System.out.println("设备激活:"+equipmentActivation);
		JSONObject data = JSONObject.parseObject(equipmentActivation.getString("data"));
		System.out.println(data);
		System.out.println(data.getString("sn"));
		//获取设备SN
		moredianOrgDevice.setDeviceSn(data.getString("sn"));

		//根据设备SN获取设备魔点id
		String deviceId = MoredianMethod.getDeviceId(orgAccessToken, data.getString("sn"));
		System.out.println("根据设备SN获取设备魔点id:"+deviceId);
		moredianOrgDevice.setDeviceId(deviceId);

		//获取设备后台管理密码   设备密码先扫描设备二维码获取timestamp，再去获取设备密码
//		object.clear();
//		object.put("deviceId",deviceId);
//		object.put("timestamp",System.currentTimeMillis());
//		JSONObject dynamicPwd = MoredianMethod.getDynamicPwd(orgAccessToken, object);
//		System.out.println("获取设备后台管理密码:"+dynamicPwd);
//		JSONObject pwd = JSONObject.parseObject(dynamicPwd.getString("data"));
//		moredianOrgDevice.setdeviceRemark(pwd.getString("password"));
//		return toAjax(0);
		return toAjax(moredianOrgDeviceService.insertMoredianOrgDevice(moredianOrgDevice));
	}


	/**
	 * 修改机构设备
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MoredianOrgDevice moredianOrgDevice = moredianOrgDeviceService.selectMoredianOrgDeviceById(id);
		mmap.put("org",iMoredianOrgService.selectMoredianOrgList(null));
		mmap.put("moredianOrgDevice", moredianOrgDevice);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存机构设备
	 */
	@RequiresPermissions("moredian:moredianOrgDevice:edit")
	@Log(title = "机构设备", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MoredianOrgDevice moredianOrgDevice)
	{		
		return toAjax(moredianOrgDeviceService.updateMoredianOrgDevice(moredianOrgDevice));
	}
	
	/**
	 * 删除机构设备
	 */
	@RequiresPermissions("moredian:moredianOrgDevice:remove")
	@Log(title = "机构设备", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(moredianOrgDeviceService.deleteMoredianOrgDeviceByIds(ids));
	}
	
}
