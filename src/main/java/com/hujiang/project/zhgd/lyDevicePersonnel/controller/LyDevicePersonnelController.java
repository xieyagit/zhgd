package com.hujiang.project.zhgd.lyDevicePersonnel.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
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
import com.hujiang.project.zhgd.lyDevicePersonnel.domain.LyDevicePersonnel;
import com.hujiang.project.zhgd.lyDevicePersonnel.service.ILyDevicePersonnelService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 楼宇考勤设备人员 信息操作处理
 * 
 * @author hujiang
 * @date 2020-03-06
 */
@Controller
@RequestMapping("/zhgd/lyDevicePersonnel")
public class LyDevicePersonnelController extends BaseController
{
    private String prefix = "zhgd/lyDevicePersonnel";
	
	@Autowired
	private ILyDevicePersonnelService lyDevicePersonnelService;
	
	@RequiresPermissions("zhgd:lyDevicePersonnel:view")
	@GetMapping()
	public String lyDevicePersonnel()
	{
	    return prefix + "/lyDevicePersonnel";
	}
	
	/**
	 * 查询楼宇考勤设备人员列表
	 */
	@RequiresPermissions("zhgd:lyDevicePersonnel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(LyDevicePersonnel lyDevicePersonnel)
	{
		startPage();
        List<LyDevicePersonnel> list = lyDevicePersonnelService.selectLyDevicePersonnelList(lyDevicePersonnel);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出楼宇考勤设备人员列表
	 */
	@RequiresPermissions("zhgd:lyDevicePersonnel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LyDevicePersonnel lyDevicePersonnel)
    {
    	List<LyDevicePersonnel> list = lyDevicePersonnelService.selectLyDevicePersonnelList(lyDevicePersonnel);
        ExcelUtil<LyDevicePersonnel> util = new ExcelUtil<LyDevicePersonnel>(LyDevicePersonnel.class);
        return util.exportExcel(list, "lyDevicePersonnel");
    }
	
	/**
	 * 新增楼宇考勤设备人员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存楼宇考勤设备人员
	 */
	@RequiresPermissions("zhgd:lyDevicePersonnel:add")
	@Log(title = "楼宇考勤设备人员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(LyDevicePersonnel lyDevicePersonnel)
	{		
		return toAjax(lyDevicePersonnelService.insertLyDevicePersonnel(lyDevicePersonnel));
	}

	/**
	 * 修改楼宇考勤设备人员
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		LyDevicePersonnel lyDevicePersonnel = lyDevicePersonnelService.selectLyDevicePersonnelById(id);
		mmap.put("lyDevicePersonnel", lyDevicePersonnel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存楼宇考勤设备人员
	 */
	@RequiresPermissions("zhgd:lyDevicePersonnel:edit")
	@Log(title = "楼宇考勤设备人员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(LyDevicePersonnel lyDevicePersonnel)
	{		
		return toAjax(lyDevicePersonnelService.updateLyDevicePersonnel(lyDevicePersonnel));
	}
	
	/**
	 * 删除楼宇考勤设备人员
	 */
	@RequiresPermissions("zhgd:lyDevicePersonnel:remove")
	@Log(title = "楼宇考勤设备人员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(lyDevicePersonnelService.deleteLyDevicePersonnelByIds(ids));
	}
	
}
