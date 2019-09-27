package com.hujiang.project.zhgd.moredian.moredianGroupPerson.controller;

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
import com.hujiang.project.zhgd.moredian.moredianGroupPerson.domain.MoredianGroupPerson;
import com.hujiang.project.zhgd.moredian.moredianGroupPerson.service.IMoredianGroupPersonService;
import com.hujiang.project.zhgd.moredian.moredianOrg.domain.MoredianOrg;
import com.hujiang.project.zhgd.moredian.moredianOrg.service.IMoredianOrgService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 群组人员 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-09
 */
@Controller
@RequestMapping("/moredian/moredianGroupPerson")
public class MoredianGroupPersonController extends BaseController
{
    private String prefix = "moredian/moredianGroupPerson";
	
	@Autowired
	private IMoredianGroupPersonService moredianGroupPersonService;
	@Autowired
	private IMoredianGroupService moredianGroupService;
	@Autowired
	private IMoredianOrgService iMoredianOrgService;

	
	@RequiresPermissions("moredian:moredianGroupPerson:view")
	@GetMapping()
	public String moredianGroupPerson()
	{
	    return prefix + "/moredianGroupPerson";
	}
	
	/**
	 * 查询群组人员列表
	 */
	@RequiresPermissions("moredian:moredianGroupPerson:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MoredianGroupPerson moredianGroupPerson)
	{
		startPage();
        List<MoredianGroupPerson> list = moredianGroupPersonService.selectMoredianGroupPersonList(moredianGroupPerson);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出群组人员列表
	 */
	@RequiresPermissions("moredian:moredianGroupPerson:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MoredianGroupPerson moredianGroupPerson)
    {
    	List<MoredianGroupPerson> list = moredianGroupPersonService.selectMoredianGroupPersonList(moredianGroupPerson);
        ExcelUtil<MoredianGroupPerson> util = new ExcelUtil<MoredianGroupPerson>(MoredianGroupPerson.class);
        return util.exportExcel(list, "moredianGroupPerson");
    }
	
	/**
	 * 新增群组人员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存群组人员
	 */
	@RequiresPermissions("moredian:moredianGroupPerson:add")
	@Log(title = "群组人员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MoredianGroupPerson moredianGroupPerson)
	{
		System.out.println(moredianGroupPerson);
		//根据群组id获取项目群组
		MoredianGroup mg = new MoredianGroup();
		mg.setGroupId(moredianGroupPerson.getGroupId());
		List<MoredianGroup> moredianGroups = moredianGroupService.selectMoredianGroupList(mg);
		if(moredianGroups!=null && moredianGroups.size()>0){
			MoredianOrg m = new MoredianOrg();
			m.setOrgId(moredianGroups.get(0).getOrgId());
			//获取机构
			MoredianOrg moredianOrg = iMoredianOrgService.selectMoredianOrgList(m).get(0);
			System.out.println(moredianOrg);
			//获取机构accessToken
			String orgAccessToken = MoredianMethod.getOrgAccessToken(moredianOrg.getOrgAuthKey(), moredianOrg.getOrgId());
			System.out.println("获取机构accessToken:"+orgAccessToken);
			//增加人员群组
			JSONObject o = new JSONObject();
			o.put("groupId",moredianGroupPerson.getGroupId());
			o.put("memberId",moredianGroupPerson.getMemberId());
			JSONObject object = MoredianMethod.bindGroup(orgAccessToken, o);
			System.out.println("添加群组人员 ："+object);
			return toAjax(moredianGroupPersonService.insertMoredianGroupPerson(moredianGroupPerson));

		}
		return toAjax(0);
	}

	/**
	 * 修改群组人员
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MoredianGroupPerson moredianGroupPerson = moredianGroupPersonService.selectMoredianGroupPersonById(id);
		mmap.put("moredianGroupPerson", moredianGroupPerson);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存群组人员
	 */
	@RequiresPermissions("moredian:moredianGroupPerson:edit")
	@Log(title = "群组人员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MoredianGroupPerson moredianGroupPerson)
	{		
		return toAjax(moredianGroupPersonService.updateMoredianGroupPerson(moredianGroupPerson));
	}
	
	/**
	 * 删除群组人员
	 */
	@RequiresPermissions("moredian:moredianGroupPerson:remove")
	@Log(title = "群组人员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		int result=0;
		String[] split = ids.split(",");
		for (int i = 0; i < split.length; i++) {//分割获取人员
			MoredianGroupPerson moredianGroupPerson = moredianGroupPersonService.selectMoredianGroupPersonById(Integer.parseInt(split[i]));
			//根据群组id获取项目群组
			MoredianGroup mg = new MoredianGroup();
			mg.setGroupId(moredianGroupPerson.getGroupId());
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
				o.put("groupId",moredianGroupPerson.getGroupId());
				o.put("memberId",moredianGroupPerson.getMemberId());
				JSONObject object = MoredianMethod.unbindGroup(orgAccessToken, o);
				if(object.getString("result")!=null && object.getString("result").equals("0")){
					result+=moredianGroupPersonService.deleteMoredianGroupPersonByIds(split[i]);
				}

			}
		}
		return toAjax(result);
	}
	
}
