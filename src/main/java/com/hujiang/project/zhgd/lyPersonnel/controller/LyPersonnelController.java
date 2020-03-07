package com.hujiang.project.zhgd.lyPersonnel.controller;

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
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.service.ILyPersonnelService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 楼宇人员 信息操作处理
 * 
 * @author hujiang
 * @date 2020-03-06
 */
@Controller
@RequestMapping("/zhgd/lyPersonnel")
public class LyPersonnelController extends BaseController
{
    private String prefix = "zhgd/lyPersonnel";
	
	@Autowired
	private ILyPersonnelService lyPersonnelService;
	
	@RequiresPermissions("zhgd:lyPersonnel:view")
	@GetMapping()
	public String lyPersonnel()
	{
	    return prefix + "/lyPersonnel";
	}
	
	/**
	 * 查询楼宇人员列表
	 */
	@RequiresPermissions("zhgd:lyPersonnel:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(LyPersonnel lyPersonnel)
	{
		startPage();
        List<LyPersonnel> list = lyPersonnelService.selectLyPersonnelList(lyPersonnel);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出楼宇人员列表
	 */
	@RequiresPermissions("zhgd:lyPersonnel:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LyPersonnel lyPersonnel)
    {
    	List<LyPersonnel> list = lyPersonnelService.selectLyPersonnelList(lyPersonnel);
        ExcelUtil<LyPersonnel> util = new ExcelUtil<LyPersonnel>(LyPersonnel.class);
        return util.exportExcel(list, "lyPersonnel");
    }
	
	/**
	 * 新增楼宇人员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存楼宇人员
	 */
	@RequiresPermissions("zhgd:lyPersonnel:add")
	@Log(title = "楼宇人员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(LyPersonnel lyPersonnel)
	{		
		return toAjax(lyPersonnelService.insertLyPersonnel(lyPersonnel));
	}

	/**
	 * 修改楼宇人员
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		LyPersonnel lyPersonnel = lyPersonnelService.selectLyPersonnelById(id);
		mmap.put("lyPersonnel", lyPersonnel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存楼宇人员
	 */
	@RequiresPermissions("zhgd:lyPersonnel:edit")
	@Log(title = "楼宇人员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(LyPersonnel lyPersonnel)
	{		
		return toAjax(lyPersonnelService.updateLyPersonnel(lyPersonnel));
	}
	
	/**
	 * 删除楼宇人员
	 */
	@RequiresPermissions("zhgd:lyPersonnel:remove")
	@Log(title = "楼宇人员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(lyPersonnelService.deleteLyPersonnelByIds(ids));
	}
	
}
