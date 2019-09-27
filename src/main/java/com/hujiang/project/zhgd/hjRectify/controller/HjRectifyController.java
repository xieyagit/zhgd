package com.hujiang.project.zhgd.hjRectify.controller;

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
import com.hujiang.project.zhgd.hjRectify.domain.HjRectify;
import com.hujiang.project.zhgd.hjRectify.service.IHjRectifyService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 整改记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/zhgd/hjRectify")
public class HjRectifyController extends BaseController
{
    private String prefix = "zhgd/hjRectify";
	
	@Autowired
	private IHjRectifyService hjRectifyService;
	
	@RequiresPermissions("zhgd:hjRectify:view")
	@GetMapping()
	public String hjRectify()
	{
	    return prefix + "/hjRectify";
	}
	
	/**
	 * 查询整改记录列表
	 */
	@RequiresPermissions("zhgd:hjRectify:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjRectify hjRectify)
	{
		startPage();
        List<HjRectify> list = hjRectifyService.selectHjRectifyList(hjRectify);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出整改记录列表
	 */
	@RequiresPermissions("zhgd:hjRectify:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjRectify hjRectify)
    {
    	List<HjRectify> list = hjRectifyService.selectHjRectifyList(hjRectify);
        ExcelUtil<HjRectify> util = new ExcelUtil<HjRectify>(HjRectify.class);
        return util.exportExcel(list, "hjRectify");
    }
	
	/**
	 * 新增整改记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存整改记录
	 */
	@RequiresPermissions("zhgd:hjRectify:add")
	@Log(title = "整改记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjRectify hjRectify)
	{		
		return toAjax(hjRectifyService.insertHjRectify(hjRectify));
	}

	/**
	 * 修改整改记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjRectify hjRectify = hjRectifyService.selectHjRectifyById(id);
		mmap.put("hjRectify", hjRectify);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存整改记录
	 */
	@RequiresPermissions("zhgd:hjRectify:edit")
	@Log(title = "整改记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjRectify hjRectify)
	{		
		return toAjax(hjRectifyService.updateHjRectify(hjRectify));
	}
	
	/**
	 * 删除整改记录
	 */
	@RequiresPermissions("zhgd:hjRectify:remove")
	@Log(title = "整改记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjRectifyService.deleteHjRectifyByIds(ids));
	}
	
}
