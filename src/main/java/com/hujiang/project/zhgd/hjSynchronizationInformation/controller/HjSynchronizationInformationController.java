package com.hujiang.project.zhgd.hjSynchronizationInformation.controller;

import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import com.hujiang.project.zhgd.hjDictionaries.service.IHjDictionariesService;
import com.hujiang.project.zhgd.utils.CurrentTime;
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
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 项目两制同步 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjSynchronizationInformation")
public class HjSynchronizationInformationController extends BaseController
{
    private String prefix = "zhgd/hjSynchronizationInformation";
	
	@Autowired
	private IHjSynchronizationInformationService hjSynchronizationInformationService;
	@Autowired
	private IHjDictionariesService hjDictionariesService;
	
	@RequiresPermissions("zhgd:hjSynchronizationInformation:view")
	@GetMapping()
	public String hjSynchronizationInformation()
	{
	    return prefix + "/hjSynchronizationInformation";
	}
	
	/**
	 * 查询项目两制同步列表
	 */
	@RequiresPermissions("zhgd:hjSynchronizationInformation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjSynchronizationInformation hjSynchronizationInformation)
	{
		startPage();
        List<HjSynchronizationInformation> list = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hjSynchronizationInformation);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出项目两制同步列表
	 */
	@RequiresPermissions("zhgd:hjSynchronizationInformation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjSynchronizationInformation hjSynchronizationInformation)
    {
    	List<HjSynchronizationInformation> list = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hjSynchronizationInformation);
        ExcelUtil<HjSynchronizationInformation> util = new ExcelUtil<HjSynchronizationInformation>(HjSynchronizationInformation.class);
        return util.exportExcel(list, "hjSynchronizationInformation");
    }
	
	/**
	 * 新增项目两制同步
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		HjDictionaries hjDictionaries = new HjDictionaries();
		hjDictionaries.setCategory("PLATFORM");//对接平台
		mmap.put("platform", hjDictionariesService.selectHjDictionariesList(hjDictionaries));
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存项目两制同步
	 */
	@RequiresPermissions("zhgd:hjSynchronizationInformation:add")
	@Log(title = "项目两制同步", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjSynchronizationInformation hjSynchronizationInformation)
	{		
		return toAjax(hjSynchronizationInformationService.insertHjSynchronizationInformation(hjSynchronizationInformation));
	}

	/**
	 * 修改项目两制同步
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjSynchronizationInformation hjSynchronizationInformation = hjSynchronizationInformationService.selectHjSynchronizationInformationById(id);
		mmap.put("hjSynchronizationInformation", hjSynchronizationInformation);
		HjDictionaries hjDictionaries = new HjDictionaries();
		hjDictionaries.setCategory("PLATFORM");//对接平台
		mmap.put("platform", hjDictionariesService.selectHjDictionariesList(hjDictionaries));
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存项目两制同步
	 */
	@RequiresPermissions("zhgd:hjSynchronizationInformation:edit")
	@Log(title = "项目两制同步", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjSynchronizationInformation hjSynchronizationInformation)
	{
		hjSynchronizationInformation.setUpdateDate(CurrentTime.getCurrentTime());
		return toAjax(hjSynchronizationInformationService.updateHjSynchronizationInformation(hjSynchronizationInformation));
	}
	
	/**
	 * 删除项目两制同步
	 */
	@RequiresPermissions("zhgd:hjSynchronizationInformation:remove")
	@Log(title = "项目两制同步", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjSynchronizationInformationService.deleteHjSynchronizationInformationByIds(ids));
	}
	
}
