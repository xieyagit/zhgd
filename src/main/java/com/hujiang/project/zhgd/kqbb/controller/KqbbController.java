package com.hujiang.project.zhgd.kqbb.controller;

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
import com.hujiang.project.zhgd.kqbb.domain.Kqbb;
import com.hujiang.project.zhgd.kqbb.service.IKqbbService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 考勤报 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-05
 */
@Controller
@RequestMapping("/zhgd/kqbb")
public class KqbbController extends BaseController
{
    private String prefix = "zhgd/kqbb";
	
	@Autowired
	private IKqbbService kqbbService;
	
	@RequiresPermissions("zhgd:kqbb:view")
	@GetMapping()
	public String kqbb()
	{
	    return prefix + "/kqbb";
	}
	
	/**
	 * 查询考勤报列表
	 */
	@RequiresPermissions("zhgd:kqbb:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Kqbb kqbb)
	{
		startPage();
        List<Kqbb> list = kqbbService.selectKqbbList(kqbb);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出考勤报列表
	 */
	@RequiresPermissions("zhgd:kqbb:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Kqbb kqbb)
    {
    	List<Kqbb> list = kqbbService.selectKqbbList(kqbb);
        ExcelUtil<Kqbb> util = new ExcelUtil<Kqbb>(Kqbb.class);
        return util.exportExcel(list, "kqbb");
    }
	
	/**
	 * 新增考勤报
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存考勤报
	 */
	@RequiresPermissions("zhgd:kqbb:add")
	@Log(title = "考勤报", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Kqbb kqbb)
	{		
		return toAjax(kqbbService.insertKqbb(kqbb));
	}

	/**
	 * 修改考勤报
	 */
	@GetMapping("/edit/{projectId}")
	public String edit(@PathVariable("projectId") Integer projectId, ModelMap mmap)
	{
		Kqbb kqbb = kqbbService.selectKqbbById(projectId);
		mmap.put("kqbb", kqbb);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存考勤报
	 */
	@RequiresPermissions("zhgd:kqbb:edit")
	@Log(title = "考勤报", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Kqbb kqbb)
	{		
		return toAjax(kqbbService.updateKqbb(kqbb));
	}
	
	/**
	 * 删除考勤报
	 */
	@RequiresPermissions("zhgd:kqbb:remove")
	@Log(title = "考勤报", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(kqbbService.deleteKqbbByIds(ids));
	}
	
}
