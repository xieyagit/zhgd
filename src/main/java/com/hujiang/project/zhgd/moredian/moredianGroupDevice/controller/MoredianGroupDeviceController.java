package com.hujiang.project.zhgd.moredian.moredianGroupDevice.controller;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.poi.ExcelUtil;
import com.hujiang.framework.aspectj.lang.annotation.Log;
import com.hujiang.framework.aspectj.lang.enums.BusinessType;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.moredian.MoredianMethod;
import com.hujiang.project.zhgd.moredian.moredianGroup.domain.MoredianGroup;
import com.hujiang.project.zhgd.moredian.moredianGroup.service.IMoredianGroupService;
import com.hujiang.project.zhgd.moredian.moredianGroupDevice.domain.MoredianGroupDevice;
import com.hujiang.project.zhgd.moredian.moredianGroupDevice.service.IMoredianGroupDeviceService;
import com.hujiang.project.zhgd.moredian.moredianOrg.domain.MoredianOrg;
import com.hujiang.project.zhgd.moredian.moredianOrg.service.IMoredianOrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 群组设备 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Controller
@RequestMapping("/moredian/moredianGroupDevice")
public class MoredianGroupDeviceController extends BaseController
{
    private String prefix = "moredian/moredianGroupDevice";
	
	@Autowired
	private IMoredianGroupDeviceService moredianGroupDeviceService;
	@Autowired
	private IMoredianGroupService moredianGroupService;
	@Autowired
	private IMoredianOrgService iMoredianOrgService;


	@RequiresPermissions("moredian:moredianGroupDevice:view")
	@GetMapping()
	public String moredianGroupDevice()
	{
	    return prefix + "/moredianGroupDevice";
	}
	
	/**
	 * 查询群组设备列表
	 */
	@RequiresPermissions("moredian:moredianGroupDevice:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MoredianGroupDevice moredianGroupDevice)
	{
		startPage();
        List<MoredianGroupDevice> list = moredianGroupDeviceService.selectMoredianGroupDeviceList(moredianGroupDevice);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出群组设备列表
	 */
	@RequiresPermissions("moredian:moredianGroupDevice:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MoredianGroupDevice moredianGroupDevice)
    {
    	List<MoredianGroupDevice> list = moredianGroupDeviceService.selectMoredianGroupDeviceList(moredianGroupDevice);
        ExcelUtil<MoredianGroupDevice> util = new ExcelUtil<MoredianGroupDevice>(MoredianGroupDevice.class);
        return util.exportExcel(list, "moredianGroupDevice");
    }
	
	/**
	 * 新增群组设备
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存群组设备
	 */
	@RequiresPermissions("moredian:moredianGroupDevice:add")
	@Log(title = "群组设备", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MoredianGroupDevice moredianGroupDevice)
	{
		//根据群组id获取项目群组
		MoredianGroup mg = new MoredianGroup();
		mg.setGroupId(moredianGroupDevice.getGroupId());
		List<MoredianGroup> moredianGroups = moredianGroupService.selectMoredianGroupList(mg);
		if(moredianGroups!=null && moredianGroups.size()>0) {
			MoredianOrg m = new MoredianOrg();
			m.setOrgId(moredianGroups.get(0).getOrgId());
			//获取机构
			MoredianOrg moredianOrg = iMoredianOrgService.selectMoredianOrgList(m).get(0);
			System.out.println(moredianOrg);
			//获取机构accessToken
			String orgAccessToken = MoredianMethod.getOrgAccessToken(moredianOrg.getOrgAuthKey(), moredianOrg.getOrgId());
			System.out.println("获取机构accessToken:" + orgAccessToken);
			//增加人员群组
			JSONObject o = new JSONObject();
			String[] arr={moredianGroupDevice.getGroupId()};
			System.out.println(arr);
			o.put("groupIdList", arr);
			o.put("deviceId", moredianGroupDevice.getDeviceId());
			JSONObject object = MoredianMethod.deviceGroupUnbind(orgAccessToken, o);
			System.out.println("设备与群组绑定 ：" + object);
		}
		return toAjax(moredianGroupDeviceService.insertMoredianGroupDevice(moredianGroupDevice));
	}

	/**
	 * 修改群组设备
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MoredianGroupDevice moredianGroupDevice = moredianGroupDeviceService.selectMoredianGroupDeviceById(id);
		mmap.put("moredianGroupDevice", moredianGroupDevice);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存群组设备
	 */
	@RequiresPermissions("moredian:moredianGroupDevice:edit")
	@Log(title = "群组设备", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MoredianGroupDevice moredianGroupDevice)
	{		
		return toAjax(moredianGroupDeviceService.updateMoredianGroupDevice(moredianGroupDevice));
	}
	
	/**
	 * 删除群组设备
	 */
	@RequiresPermissions("moredian:moredianGroupDevice:remove")
	@Log(title = "群组设备", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(moredianGroupDeviceService.deleteMoredianGroupDeviceByIds(ids));
	}
	
}
