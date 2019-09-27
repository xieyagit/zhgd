package com.hujiang.project.zhgd.hjInspector.controller;

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
import com.hujiang.project.zhgd.hjInspector.domain.HjInspector;
import com.hujiang.project.zhgd.hjInspector.service.IHjInspectorService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 检查记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-06-28
 */
@Controller
@RequestMapping("/zhgd/hjInspector")
public class HjInspectorController extends BaseController
{
    private String prefix = "zhgd/hjInspector";
	
	@Autowired
	private IHjInspectorService hjInspectorService;
	
	@RequiresPermissions("zhgd:hjInspector:view")
	@GetMapping()
	public String hjInspector()
	{
	    return prefix + "/hjInspector";
	}
	
	/**
	 * 查询检查记录列表
	 */
	@RequiresPermissions("zhgd:hjInspector:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjInspector hjInspector)
	{
		startPage();
        List<HjInspector> list = hjInspectorService.selectHjInspectorList(hjInspector);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出检查记录列表
	 */
	@RequiresPermissions("zhgd:hjInspector:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjInspector hjInspector)
    {
    	List<HjInspector> list = hjInspectorService.selectHjInspectorList(hjInspector);
        ExcelUtil<HjInspector> util = new ExcelUtil<HjInspector>(HjInspector.class);
        return util.exportExcel(list, "hjInspector");
    }
	
	/**
	 * 新增检查记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存检查记录
	 */
	@RequiresPermissions("zhgd:hjInspector:add")
	@Log(title = "检查记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjInspector hjInspector)
	{		
		return toAjax(hjInspectorService.insertHjInspector(hjInspector));
	}

	/**
	 * 修改检查记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjInspector hjInspector = hjInspectorService.selectHjInspectorById(id);
		mmap.put("hjInspector", hjInspector);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存检查记录
	 */
	@RequiresPermissions("zhgd:hjInspector:edit")
	@Log(title = "检查记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjInspector hjInspector)
	{		
		return toAjax(hjInspectorService.updateHjInspector(hjInspector));
	}
	
	/**
	 * 删除检查记录
	 */
	@RequiresPermissions("zhgd:hjInspector:remove")
	@Log(title = "检查记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjInspectorService.deleteHjInspectorByIds(ids));
	}
	
}
