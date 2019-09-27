package com.hujiang.project.zhgd.hjTeam.controller;

import com.hujiang.project.zhgd.utils.CurrentTime;
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
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 班组 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjTeam")
public class HjTeamController extends BaseController
{
    private String prefix = "zhgd/hjTeam";
	
	@Autowired
	private IHjTeamService hjTeamService;
	
	@RequiresPermissions("zhgd:hjTeam:view")
	@GetMapping()
	public String hjTeam()
	{
	    return prefix + "/hjTeam";
	}
	
	/**
	 * 查询班组列表
	 */
	@RequiresPermissions("zhgd:hjTeam:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjTeam hjTeam)
	{
		startPage();
        List<HjTeam> list = hjTeamService.selectHjTeamList(hjTeam);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出班组列表
	 */
	@RequiresPermissions("zhgd:hjTeam:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjTeam hjTeam)
    {
    	List<HjTeam> list = hjTeamService.selectHjTeamList(hjTeam);
        ExcelUtil<HjTeam> util = new ExcelUtil<HjTeam>(HjTeam.class);
        return util.exportExcel(list, "hjTeam");
    }
	
	/**
	 * 新增班组
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存班组
	 */
	@RequiresPermissions("zhgd:hjTeam:add")
	@Log(title = "班组", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjTeam hjTeam)
	{		
		return toAjax(hjTeamService.insertHjTeam(hjTeam));
	}

	/**
	 * 修改班组
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjTeam hjTeam = hjTeamService.selectHjTeamById(id);
		mmap.put("hjTeam", hjTeam);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存班组
	 */
	@RequiresPermissions("zhgd:hjTeam:edit")
	@Log(title = "班组", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjTeam hjTeam)
	{
		hjTeam.setUpdateDate(CurrentTime.getCurrentTime());
		return toAjax(hjTeamService.updateHjTeam(hjTeam));
	}
	
	/**
	 * 删除班组
	 */
	@RequiresPermissions("zhgd:hjTeam:remove")
	@Log(title = "班组", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjTeamService.deleteHjTeamByIds(ids));
	}
	
}
