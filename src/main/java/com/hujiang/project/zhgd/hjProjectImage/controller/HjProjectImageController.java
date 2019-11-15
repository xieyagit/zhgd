package com.hujiang.project.zhgd.hjProjectImage.controller;

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
import com.hujiang.project.zhgd.hjProjectImage.domain.HjProjectImage;
import com.hujiang.project.zhgd.hjProjectImage.service.IHjProjectImageService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 项目效果图 信息操作处理
 * 
 * @author hujiang
 * @date 2019-11-15
 */
@Controller
@RequestMapping("/zhgd/hjProjectImage")
public class HjProjectImageController extends BaseController
{
    private String prefix = "zhgd/hjProjectImage";
	
	@Autowired
	private IHjProjectImageService hjProjectImageService;
	
	@RequiresPermissions("zhgd:hjProjectImage:view")
	@GetMapping()
	public String hjProjectImage()
	{
	    return prefix + "/hjProjectImage";
	}
	
	/**
	 * 查询项目效果图列表
	 */
	@RequiresPermissions("zhgd:hjProjectImage:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjProjectImage hjProjectImage)
	{
		startPage();
        List<HjProjectImage> list = hjProjectImageService.selectHjProjectImageList(hjProjectImage);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出项目效果图列表
	 */
	@RequiresPermissions("zhgd:hjProjectImage:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjProjectImage hjProjectImage)
    {
    	List<HjProjectImage> list = hjProjectImageService.selectHjProjectImageList(hjProjectImage);
        ExcelUtil<HjProjectImage> util = new ExcelUtil<HjProjectImage>(HjProjectImage.class);
        return util.exportExcel(list, "hjProjectImage");
    }
	
	/**
	 * 新增项目效果图
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目效果图
	 */
	@RequiresPermissions("zhgd:hjProjectImage:add")
	@Log(title = "项目效果图", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjProjectImage hjProjectImage)
	{		
		return toAjax(hjProjectImageService.insertHjProjectImage(hjProjectImage));
	}

	/**
	 * 修改项目效果图
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjProjectImage hjProjectImage = hjProjectImageService.selectHjProjectImageById(id);
		mmap.put("hjProjectImage", hjProjectImage);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目效果图
	 */
	@RequiresPermissions("zhgd:hjProjectImage:edit")
	@Log(title = "项目效果图", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjProjectImage hjProjectImage)
	{		
		return toAjax(hjProjectImageService.updateHjProjectImage(hjProjectImage));
	}
	
	/**
	 * 删除项目效果图
	 */
	@RequiresPermissions("zhgd:hjProjectImage:remove")
	@Log(title = "项目效果图", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjProjectImageService.deleteHjProjectImageByIds(ids));
	}
	
}
