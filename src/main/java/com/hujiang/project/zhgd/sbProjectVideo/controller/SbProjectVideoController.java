package com.hujiang.project.zhgd.sbProjectVideo.controller;

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
import com.hujiang.project.zhgd.sbProjectVideo.domain.SbProjectVideo;
import com.hujiang.project.zhgd.sbProjectVideo.service.ISbProjectVideoService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 项目视频 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-23
 */
@Controller
@RequestMapping("/zhgd/sbProjectVideo")
public class SbProjectVideoController extends BaseController
{
    private String prefix = "zhgd/sbProjectVideo";
	
	@Autowired
	private ISbProjectVideoService sbProjectVideoService;
	
	@RequiresPermissions("zhgd:sbProjectVideo:view")
	@GetMapping()
	public String sbProjectVideo()
	{
	    return prefix + "/sbProjectVideo";
	}
	
	/**
	 * 查询项目视频列表
	 */
	@RequiresPermissions("zhgd:sbProjectVideo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SbProjectVideo sbProjectVideo)
	{
		startPage();
        List<SbProjectVideo> list = sbProjectVideoService.selectSbProjectVideoList(sbProjectVideo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出项目视频列表
	 */
	@RequiresPermissions("zhgd:sbProjectVideo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SbProjectVideo sbProjectVideo)
    {
    	List<SbProjectVideo> list = sbProjectVideoService.selectSbProjectVideoList(sbProjectVideo);
        ExcelUtil<SbProjectVideo> util = new ExcelUtil<SbProjectVideo>(SbProjectVideo.class);
        return util.exportExcel(list, "sbProjectVideo");
    }
	
	/**
	 * 新增项目视频
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目视频
	 */
	@RequiresPermissions("zhgd:sbProjectVideo:add")
	@Log(title = "项目视频", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SbProjectVideo sbProjectVideo)
	{		
		return toAjax(sbProjectVideoService.insertSbProjectVideo(sbProjectVideo));
	}

	/**
	 * 修改项目视频
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		SbProjectVideo sbProjectVideo = sbProjectVideoService.selectSbProjectVideoById(id);
		mmap.put("sbProjectVideo", sbProjectVideo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目视频
	 */
	@RequiresPermissions("zhgd:sbProjectVideo:edit")
	@Log(title = "项目视频", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SbProjectVideo sbProjectVideo)
	{		
		return toAjax(sbProjectVideoService.updateSbProjectVideo(sbProjectVideo));
	}
	
	/**
	 * 删除项目视频
	 */
	@RequiresPermissions("zhgd:sbProjectVideo:remove")
	@Log(title = "项目视频", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sbProjectVideoService.deleteSbProjectVideoByIds(ids));
	}
	
}
