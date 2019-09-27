package com.hujiang.project.zhgd.hjWorkersInformation.controller;

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
import com.hujiang.project.zhgd.hjWorkersInformation.domain.HjWorkersInformation;
import com.hujiang.project.zhgd.hjWorkersInformation.service.IHjWorkersInformationService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 工人 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-03
 */
@Controller
@RequestMapping("/zhgd/hjWorkersInformation")
public class HjWorkersInformationController extends BaseController
{
    private String prefix = "zhgd/hjWorkersInformation";
	
	@Autowired
	private IHjWorkersInformationService hjWorkersInformationService;
	
	@RequiresPermissions("zhgd:hjWorkersInformation:view")
	@GetMapping()
	public String hjWorkersInformation()
	{
	    return prefix + "/hjWorkersInformation";
	}
	
	/**
	 * 查询工人列表
	 */
	@RequiresPermissions("zhgd:hjWorkersInformation:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjWorkersInformation hjWorkersInformation)
	{
		startPage();
        List<HjWorkersInformation> list = hjWorkersInformationService.selectHjWorkersInformationList(hjWorkersInformation);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出工人列表
	 */
	@RequiresPermissions("zhgd:hjWorkersInformation:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjWorkersInformation hjWorkersInformation)
    {
    	List<HjWorkersInformation> list = hjWorkersInformationService.selectHjWorkersInformationList(hjWorkersInformation);
        ExcelUtil<HjWorkersInformation> util = new ExcelUtil<HjWorkersInformation>(HjWorkersInformation.class);
        return util.exportExcel(list, "hjWorkersInformation");
    }
	
	/**
	 * 新增工人
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存工人
	 */
	@RequiresPermissions("zhgd:hjWorkersInformation:add")
	@Log(title = "工人", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjWorkersInformation hjWorkersInformation)
	{		
		return toAjax(hjWorkersInformationService.insertHjWorkersInformation(hjWorkersInformation));
	}

	/**
	 * 修改工人
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjWorkersInformation hjWorkersInformation = hjWorkersInformationService.selectHjWorkersInformationById(id);
		mmap.put("hjWorkersInformation", hjWorkersInformation);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存工人
	 */
	@RequiresPermissions("zhgd:hjWorkersInformation:edit")
	@Log(title = "工人", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjWorkersInformation hjWorkersInformation)
	{		
		return toAjax(hjWorkersInformationService.updateHjWorkersInformation(hjWorkersInformation));
	}
	
	/**
	 * 删除工人
	 */
	@RequiresPermissions("zhgd:hjWorkersInformation:remove")
	@Log(title = "工人", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjWorkersInformationService.deleteHjWorkersInformationByIds(ids));
	}
	
}
