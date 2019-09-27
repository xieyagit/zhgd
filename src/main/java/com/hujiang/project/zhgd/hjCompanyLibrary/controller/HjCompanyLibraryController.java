package com.hujiang.project.zhgd.hjCompanyLibrary.controller;

import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.project.zhgd.utils.CurrentTime;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

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
import com.hujiang.project.zhgd.hjCompanyLibrary.domain.HjCompanyLibrary;
import com.hujiang.project.zhgd.hjCompanyLibrary.service.IHjCompanyLibraryService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 公司库 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjCompanyLibrary")
public class HjCompanyLibraryController extends BaseController
{
    private String prefix = "zhgd/hjCompanyLibrary";
    private Logger logger = Logger.getLogger(HjCompanyLibraryController.class.getName());
	
	@Autowired
	private IHjCompanyLibraryService hjCompanyLibraryService;
	@Autowired
	private IHjDictionariesService hjDictionariesService;

	
	@RequiresPermissions("zhgd:hjCompanyLibrary:view")
	@GetMapping()
	public String hjCompanyLibrary(ModelMap mmap)
	{
		HjDictionaries hjDictionaries = new HjDictionaries();
		hjDictionaries.setCategory("UNIT_TYPE");
		mmap.put("nuit",hjDictionariesService.selectHjDictionariesList(hjDictionaries));
	    return prefix + "/hjCompanyLibrary";
	}
	
	/**
	 * 查询公司库列表
	 */
	@RequiresPermissions("zhgd:hjCompanyLibrary:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjCompanyLibrary hjCompanyLibrary)
	{
		System.out.println(hjCompanyLibrary);
		startPage();
        List<HjCompanyLibrary> list = hjCompanyLibraryService.selectHjCompanyLibraryList(hjCompanyLibrary);
		logger.info(list.size()+"");
		return getDataTable(list);
	}

	/**
	 * 导出公司库列表
	 */
	@RequiresPermissions("zhgd:hjCompanyLibrary:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjCompanyLibrary hjCompanyLibrary)
    {
    	List<HjCompanyLibrary> list = hjCompanyLibraryService.selectHjCompanyLibraryList(hjCompanyLibrary);
        ExcelUtil<HjCompanyLibrary> util = new ExcelUtil<HjCompanyLibrary>(HjCompanyLibrary.class);
        return util.exportExcel(list, "hjCompanyLibrary");
    }
	
	/**
	 * 新增公司库
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		HjDictionaries hjDictionaries = new HjDictionaries();
		hjDictionaries.setCategory("UNIT_TYPE");
		mmap.put("nuit",hjDictionariesService.selectHjDictionariesList(hjDictionaries));
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存公司库
	 */
	@RequiresPermissions("zhgd:hjCompanyLibrary:add")
	@Log(title = "公司库", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjCompanyLibrary hjCompanyLibrary)
	{		
		return toAjax(hjCompanyLibraryService.insertHjCompanyLibrary(hjCompanyLibrary));
	}

	/**
	 * 修改公司库
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjCompanyLibrary hjCompanyLibrary = hjCompanyLibraryService.selectHjCompanyLibraryById(id);
		mmap.put("hjCompanyLibrary", hjCompanyLibrary);
		HjDictionaries hjDictionaries = new HjDictionaries();
		hjDictionaries.setCategory("UNIT_TYPE");
		mmap.put("nuit",hjDictionariesService.selectHjDictionariesList(hjDictionaries));
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存公司库
	 */
	@RequiresPermissions("zhgd:hjCompanyLibrary:edit")
	@Log(title = "公司库", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjCompanyLibrary hjCompanyLibrary)
	{
		hjCompanyLibrary.setUpdateDate(CurrentTime.getCurrentTime());
		return toAjax(hjCompanyLibraryService.updateHjCompanyLibrary(hjCompanyLibrary));
	}
	
	/**
	 * 删除公司库
	 */
	@RequiresPermissions("zhgd:hjCompanyLibrary:remove")
	@Log(title = "公司库", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjCompanyLibraryService.deleteHjCompanyLibraryByIds(ids));
	}
	
}
