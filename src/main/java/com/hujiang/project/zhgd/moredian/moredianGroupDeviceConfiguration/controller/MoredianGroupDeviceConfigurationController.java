package com.hujiang.project.zhgd.moredian.moredianGroupDeviceConfiguration.controller;

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
import com.hujiang.project.zhgd.moredian.moredianGroupDeviceConfiguration.domain.MoredianGroupDeviceConfiguration;
import com.hujiang.project.zhgd.moredian.moredianGroupDeviceConfiguration.service.IMoredianGroupDeviceConfigurationService;
import com.hujiang.project.zhgd.moredian.moredianOrg.domain.MoredianOrg;
import com.hujiang.project.zhgd.moredian.moredianOrg.service.IMoredianOrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 群组设备配置 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Controller
@RequestMapping("/moredian/moredianGroupDeviceConfiguration")
public class MoredianGroupDeviceConfigurationController extends BaseController
{
    private String prefix = "moredian/moredianGroupDeviceConfiguration";
	
	@Autowired
	private IMoredianGroupDeviceConfigurationService moredianGroupDeviceConfigurationService;
	@Autowired
	private IMoredianGroupService moredianGroupService;
	@Autowired
	private IMoredianOrgService iMoredianOrgService;



	@RequiresPermissions("moredian:moredianGroupDeviceConfiguration:view")
	@GetMapping()
	public String moredianGroupDeviceConfiguration()
	{
	    return prefix + "/moredianGroupDeviceConfiguration";
	}
	
	/**
	 * 查询群组设备配置列表
	 */
	@RequiresPermissions("moredian:moredianGroupDeviceConfiguration:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MoredianGroupDeviceConfiguration moredianGroupDeviceConfiguration)
	{
		startPage();
        List<MoredianGroupDeviceConfiguration> list = moredianGroupDeviceConfigurationService.selectMoredianGroupDeviceConfigurationList(moredianGroupDeviceConfiguration);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出群组设备配置列表
	 */
	@RequiresPermissions("moredian:moredianGroupDeviceConfiguration:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MoredianGroupDeviceConfiguration moredianGroupDeviceConfiguration)
    {
    	List<MoredianGroupDeviceConfiguration> list = moredianGroupDeviceConfigurationService.selectMoredianGroupDeviceConfigurationList(moredianGroupDeviceConfiguration);
        ExcelUtil<MoredianGroupDeviceConfiguration> util = new ExcelUtil<MoredianGroupDeviceConfiguration>(MoredianGroupDeviceConfiguration.class);
        return util.exportExcel(list, "moredianGroupDeviceConfiguration");
    }
	
	/**
	 * 新增群组设备配置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存群组设备配置
	 */
	@RequiresPermissions("moredian:moredianGroupDeviceConfiguration:add")
	@Log(title = "群组设备配置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MoredianGroupDeviceConfiguration moredianGroupDeviceConfiguration)
	{
		//根据群组id获取项目群组
		MoredianGroup mg = new MoredianGroup();
		mg.setGroupId(moredianGroupDeviceConfiguration.getGroupId());
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

			JSONObject o = new JSONObject();
			o.put("dayBeginTime", moredianGroupDeviceConfiguration.getDayBeginTime());
			o.put("dayEndTime", moredianGroupDeviceConfiguration.getDayEndTime());
			o.put("weekdays", moredianGroupDeviceConfiguration.getWeekdays());
			o.put("groupId", moredianGroupDeviceConfiguration.getGroupId());
			JSONObject object = MoredianMethod.deviceGroupConfiguration(orgAccessToken, o);
			System.out.println("设备群组配置 ：" + object);
		}
		return toAjax(moredianGroupDeviceConfigurationService.insertMoredianGroupDeviceConfiguration(moredianGroupDeviceConfiguration));
	}

	/**
	 * 修改群组设备配置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MoredianGroupDeviceConfiguration moredianGroupDeviceConfiguration = moredianGroupDeviceConfigurationService.selectMoredianGroupDeviceConfigurationById(id);
		mmap.put("moredianGroupDeviceConfiguration", moredianGroupDeviceConfiguration);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存群组设备配置
	 */
	@RequiresPermissions("moredian:moredianGroupDeviceConfiguration:edit")
	@Log(title = "群组设备配置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MoredianGroupDeviceConfiguration moredianGroupDeviceConfiguration)
	{		
		return toAjax(moredianGroupDeviceConfigurationService.updateMoredianGroupDeviceConfiguration(moredianGroupDeviceConfiguration));
	}
	
	/**
	 * 删除群组设备配置
	 */
	@RequiresPermissions("moredian:moredianGroupDeviceConfiguration:remove")
	@Log(title = "群组设备配置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(moredianGroupDeviceConfigurationService.deleteMoredianGroupDeviceConfigurationByIds(ids));
	}
	
}
