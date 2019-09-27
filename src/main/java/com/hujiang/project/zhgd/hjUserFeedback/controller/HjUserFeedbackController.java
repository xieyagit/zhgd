package com.hujiang.project.zhgd.hjUserFeedback.controller;

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
import com.hujiang.project.zhgd.hjUserFeedback.domain.HjUserFeedback;
import com.hujiang.project.zhgd.hjUserFeedback.service.IHjUserFeedbackService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 用户反馈 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-03
 */
@Controller
@RequestMapping("/zhgd/hjUserFeedback")
public class HjUserFeedbackController extends BaseController
{
    private String prefix = "zhgd/hjUserFeedback";
	
	@Autowired
	private IHjUserFeedbackService hjUserFeedbackService;
	
	@RequiresPermissions("zhgd:hjUserFeedback:view")
	@GetMapping()
	public String hjUserFeedback()
	{
	    return prefix + "/hjUserFeedback";
	}
	
	/**
	 * 查询用户反馈列表
	 */
	@RequiresPermissions("zhgd:hjUserFeedback:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjUserFeedback hjUserFeedback)
	{
		startPage();
        List<HjUserFeedback> list = hjUserFeedbackService.selectHjUserFeedbackList(hjUserFeedback);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户反馈列表
	 */
	@RequiresPermissions("zhgd:hjUserFeedback:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjUserFeedback hjUserFeedback)
    {
    	List<HjUserFeedback> list = hjUserFeedbackService.selectHjUserFeedbackList(hjUserFeedback);
        ExcelUtil<HjUserFeedback> util = new ExcelUtil<HjUserFeedback>(HjUserFeedback.class);
        return util.exportExcel(list, "hjUserFeedback");
    }
	
	/**
	 * 新增用户反馈
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户反馈
	 */
	@RequiresPermissions("zhgd:hjUserFeedback:add")
	@Log(title = "用户反馈", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjUserFeedback hjUserFeedback)
	{		
		return toAjax(hjUserFeedbackService.insertHjUserFeedback(hjUserFeedback));
	}

	/**
	 * 修改用户反馈
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjUserFeedback hjUserFeedback = hjUserFeedbackService.selectHjUserFeedbackById(id);
		mmap.put("hjUserFeedback", hjUserFeedback);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户反馈
	 */
	@RequiresPermissions("zhgd:hjUserFeedback:edit")
	@Log(title = "用户反馈", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjUserFeedback hjUserFeedback)
	{		
		return toAjax(hjUserFeedbackService.updateHjUserFeedback(hjUserFeedback));
	}
	
	/**
	 * 删除用户反馈
	 */
	@RequiresPermissions("zhgd:hjUserFeedback:remove")
	@Log(title = "用户反馈", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjUserFeedbackService.deleteHjUserFeedbackByIds(ids));
	}
	
}
