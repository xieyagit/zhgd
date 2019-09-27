package com.hujiang.project.zhgd.hjInformation.controller;

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
import com.hujiang.project.zhgd.hjInformation.domain.HjInformation;
import com.hujiang.project.zhgd.hjInformation.service.IHjInformationService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 资料 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-01
 */
@Controller
@RequestMapping("/zhgd/hjInformation")
public class HjInformationController extends BaseController
{
    private String prefix = "zhgd/hjInformation";
	
	@Autowired
	private IHjInformationService hjInformationService;
	
	@RequiresPermissions("zhgd:hjInformation:view")
	@GetMapping()
	public String hjInformation()
	{
	    return prefix + "/hjInformation";
	}
	
	/**
	 * 查询资料列表
	 */
	@RequiresPermissions("zhgd:hjInformation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjInformation hjInformation)
	{
		startPage();
        List<HjInformation> list = hjInformationService.selectHjInformationList(hjInformation);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资料列表
	 */
	@RequiresPermissions("zhgd:hjInformation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjInformation hjInformation)
    {
    	List<HjInformation> list = hjInformationService.selectHjInformationList(hjInformation);
        ExcelUtil<HjInformation> util = new ExcelUtil<HjInformation>(HjInformation.class);
        return util.exportExcel(list, "hjInformation");
    }
	
	/**
	 * 新增资料
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资料
	 */
	@RequiresPermissions("zhgd:hjInformation:add")
	@Log(title = "资料", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjInformation hjInformation)
	{		
		return toAjax(hjInformationService.insertHjInformation(hjInformation));
	}

	/**
	 * 修改资料
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjInformation hjInformation = hjInformationService.selectHjInformationById(id);
		mmap.put("hjInformation", hjInformation);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资料
	 */
	@RequiresPermissions("zhgd:hjInformation:edit")
	@Log(title = "资料", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjInformation hjInformation)
	{		
		return toAjax(hjInformationService.updateHjInformation(hjInformation));
	}
	
	/**
	 * 删除资料
	 */
	@RequiresPermissions("zhgd:hjInformation:remove")
	@Log(title = "资料", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String[] ids)
	{		
		return toAjax(hjInformationService.deleteHjInformationByIds(ids));
	}
	
}
