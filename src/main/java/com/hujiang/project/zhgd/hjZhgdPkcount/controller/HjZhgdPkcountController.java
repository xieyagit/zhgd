package com.hujiang.project.zhgd.hjZhgdPkcount.controller;

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
import com.hujiang.project.zhgd.hjZhgdPkcount.domain.HjZhgdPkcount;
import com.hujiang.project.zhgd.hjZhgdPkcount.service.IHjZhgdPkcountService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 车位剩余 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-09
 */
@Controller
@RequestMapping("/zhgd/hjZhgdPkcount")
public class HjZhgdPkcountController extends BaseController
{
    private String prefix = "zhgd/hjZhgdPkcount";
	
	@Autowired
	private IHjZhgdPkcountService hjZhgdPkcountService;
	
	@RequiresPermissions("zhgd:hjZhgdPkcount:view")
	@GetMapping()
	public String hjZhgdPkcount()
	{
	    return prefix + "/hjZhgdPkcount";
	}
	
	/**
	 * 查询车位剩余列表
	 */
	@RequiresPermissions("zhgd:hjZhgdPkcount:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjZhgdPkcount hjZhgdPkcount)
	{
		startPage();
        List<HjZhgdPkcount> list = hjZhgdPkcountService.selectHjZhgdPkcountList(hjZhgdPkcount);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出车位剩余列表
	 */
	@RequiresPermissions("zhgd:hjZhgdPkcount:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjZhgdPkcount hjZhgdPkcount)
    {
    	List<HjZhgdPkcount> list = hjZhgdPkcountService.selectHjZhgdPkcountList(hjZhgdPkcount);
        ExcelUtil<HjZhgdPkcount> util = new ExcelUtil<HjZhgdPkcount>(HjZhgdPkcount.class);
        return util.exportExcel(list, "hjZhgdPkcount");
    }
	
	/**
	 * 新增车位剩余
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存车位剩余
	 */
	@RequiresPermissions("zhgd:hjZhgdPkcount:add")
	@Log(title = "车位剩余", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjZhgdPkcount hjZhgdPkcount)
	{		
		return toAjax(hjZhgdPkcountService.insertHjZhgdPkcount(hjZhgdPkcount));
	}

	/**
	 * 修改车位剩余
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjZhgdPkcount hjZhgdPkcount = hjZhgdPkcountService.selectHjZhgdPkcountById(id);
		mmap.put("hjZhgdPkcount", hjZhgdPkcount);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存车位剩余
	 */
	@RequiresPermissions("zhgd:hjZhgdPkcount:edit")
	@Log(title = "车位剩余", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjZhgdPkcount hjZhgdPkcount)
	{		
		return toAjax(hjZhgdPkcountService.updateHjZhgdPkcount(hjZhgdPkcount));
	}

	
}
