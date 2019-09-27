//package com.hujiang.project.zhgd.shortCreedNumber.controller;
//
//import java.util.List;
//import org.springframework.stereotype.Controller;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import com.hujiang.framework.aspectj.lang.annotation.Log;
//import com.hujiang.framework.aspectj.lang.enums.BusinessType;
//import com.hujiang.project.zhgd.shortCreedNumber.domain.ShortCreedNumber;
//import com.hujiang.project.zhgd.shortCreedNumber.service.IShortCreedNumberService;
//import com.hujiang.framework.web.controller.BaseController;
//import com.hujiang.framework.web.page.TableDataInfo;
//import com.hujiang.framework.web.domain.AjaxResult;
//import com.hujiang.common.utils.poi.ExcelUtil;
//
///**
// * 项目短信条数 信息操作处理
// *
// * @author hujiang
// * @date 2019-08-01
// */
//@Controller
//@RequestMapping("/zhgd/shortCreedNumber")
//public class ShortCreedNumberController extends BaseController
//{
//    private String prefix = "zhgd/shortCreedNumber";
//
//	@Autowired
//	private IShortCreedNumberService shortCreedNumberService;
//
//	@RequiresPermissions("zhgd:shortCreedNumber:view")
//	@GetMapping()
//	public String shortCreedNumber()
//	{
//	    return prefix + "/shortCreedNumber";
//	}
//
//	/**
//	 * 查询项目短信条数列表
//	 */
//	@RequiresPermissions("zhgd:shortCreedNumber:list")
//	@PostMapping("/list")
//	@ResponseBody
//	public TableDataInfo list(ShortCreedNumber shortCreedNumber)
//	{
//		startPage();
//        ShortCreedNumber list = shortCreedNumberService.selectShortCreedNumberList(shortCreedNumber);
//		return getDataTable(list);
//	}
//
//
//	/**
//	 * 导出项目短信条数列表
//	 */
//	@RequiresPermissions("zhgd:shortCreedNumber:export")
//    @PostMapping("/export")
//    @ResponseBody
//    public AjaxResult export(ShortCreedNumber shortCreedNumber)
//    {
//    	ShortCreedNumber list = shortCreedNumberService.selectShortCreedNumberList(shortCreedNumber);
//        ExcelUtil<ShortCreedNumber> util = new ExcelUtil<ShortCreedNumber>(ShortCreedNumber.class);
//        return util.exportExcel(list, "shortCreedNumber");
//    }
//
//	/**
//	 * 新增项目短信条数
//	 */
//	@GetMapping("/add")
//	public String add()
//	{
//	    return prefix + "/add";
//	}
//
//	/**
//	 * 新增保存项目短信条数
//	 */
//	@RequiresPermissions("zhgd:shortCreedNumber:add")
//	@Log(title = "项目短信条数", businessType = BusinessType.INSERT)
//	@PostMapping("/add")
//	@ResponseBody
//	public AjaxResult addSave(ShortCreedNumber shortCreedNumber)
//	{
//		return toAjax(shortCreedNumberService.insertShortCreedNumber(shortCreedNumber));
//	}
//
//	/**
//	 * 修改项目短信条数
//	 */
//	@GetMapping("/edit/{id}")
//	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
//	{
//		ShortCreedNumber shortCreedNumber = shortCreedNumberService.selectShortCreedNumberById(id);
//		mmap.put("shortCreedNumber", shortCreedNumber);
//	    return prefix + "/edit";
//	}
//
//	/**
//	 * 修改保存项目短信条数
//	 */
//	@RequiresPermissions("zhgd:shortCreedNumber:edit")
//	@Log(title = "项目短信条数", businessType = BusinessType.UPDATE)
//	@PostMapping("/edit")
//	@ResponseBody
//	public AjaxResult editSave(ShortCreedNumber shortCreedNumber)
//	{
//		return toAjax(shortCreedNumberService.updateShortCreedNumber(shortCreedNumber));
//	}
//
//	/**
//	 * 删除项目短信条数
//	 */
//	@RequiresPermissions("zhgd:shortCreedNumber:remove")
//	@Log(title = "项目短信条数", businessType = BusinessType.DELETE)
//	@PostMapping( "/remove")
//	@ResponseBody
//	public AjaxResult remove(String ids)
//	{
//		return toAjax(shortCreedNumberService.deleteShortCreedNumberByIds(ids));
//	}
//
//}
