package com.hujiang.project.zhgd.hjSafetyArea.controller;

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
import com.hujiang.project.zhgd.hjSafetyArea.domain.HjSafetyArea;
import com.hujiang.project.zhgd.hjSafetyArea.service.IHjSafetyAreaService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 巡检工区 信息操作处理
 * 
 * @author hujiang
 * @date 2019-07-23
 */
@Controller
@RequestMapping("/zhgd/hjSafetyArea")
public class HjSafetyAreaController extends BaseController
{
    private String prefix = "zhgd/hjSafetyArea";
	
	@Autowired
	private IHjSafetyAreaService hjSafetyAreaService;
	
	@RequiresPermissions("zhgd:hjSafetyArea:view")
	@GetMapping()
	public String hjSafetyArea()
	{
	    return prefix + "/hjSafetyArea";
	}
	
	/**
	 * 查询巡检工区列表
	 */
	@RequiresPermissions("zhgd:hjSafetyArea:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjSafetyArea hjSafetyArea)
	{
		startPage();
        List<HjSafetyArea> list = hjSafetyAreaService.selectHjSafetyAreaList(hjSafetyArea);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出巡检工区列表
	 */
	@RequiresPermissions("zhgd:hjSafetyArea:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjSafetyArea hjSafetyArea)
    {
    	List<HjSafetyArea> list = hjSafetyAreaService.selectHjSafetyAreaList(hjSafetyArea);
        ExcelUtil<HjSafetyArea> util = new ExcelUtil<HjSafetyArea>(HjSafetyArea.class);
        return util.exportExcel(list, "hjSafetyArea");
    }
	
	/**
	 * 新增巡检工区
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存巡检工区
	 */
	@RequiresPermissions("zhgd:hjSafetyArea:add")
	@Log(title = "巡检工区", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjSafetyArea hjSafetyArea)
	{		
		return toAjax(hjSafetyAreaService.insertHjSafetyArea(hjSafetyArea));
	}

	/**
	 * 修改巡检工区
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjSafetyArea hjSafetyArea = hjSafetyAreaService.selectHjSafetyAreaById(id);
		mmap.put("hjSafetyArea", hjSafetyArea);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存巡检工区
	 */
	@RequiresPermissions("zhgd:hjSafetyArea:edit")
	@Log(title = "巡检工区", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjSafetyArea hjSafetyArea)
	{		
		return toAjax(hjSafetyAreaService.updateHjSafetyArea(hjSafetyArea));
	}
	
	/**
	 * 删除巡检工区
	 */
	@RequiresPermissions("zhgd:hjSafetyArea:remove")
	@Log(title = "巡检工区", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjSafetyAreaService.deleteHjSafetyAreaByIds(ids));
	}
	
}
