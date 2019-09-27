package com.hujiang.project.zhgd.hjArea.controller;

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
import com.hujiang.project.zhgd.hjArea.domain.HjArea;
import com.hujiang.project.zhgd.hjArea.service.IHjAreaService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 城市 信息操作处理
 * 
 * @author hujiang
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/zhgd/hjArea")
public class HjAreaController extends BaseController
{
    private String prefix = "zhgd/hjArea";
	
	@Autowired
	private IHjAreaService hjAreaService;
	
	@RequiresPermissions("zhgd:hjArea:view")
	@GetMapping()
	public String hjArea()
	{
	    return prefix + "/hjArea";
	}
	
	/**
	 * 查询城市列表
	 */
	@RequiresPermissions("zhgd:hjArea:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjArea hjArea)
	{
		startPage();
        List<HjArea> list = hjAreaService.selectHjAreaList(hjArea);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出城市列表
	 */
	@RequiresPermissions("zhgd:hjArea:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjArea hjArea)
    {
    	List<HjArea> list = hjAreaService.selectHjAreaList(hjArea);
        ExcelUtil<HjArea> util = new ExcelUtil<HjArea>(HjArea.class);
        return util.exportExcel(list, "hjArea");
    }
	
	/**
	 * 新增城市
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存城市
	 */
	@RequiresPermissions("zhgd:hjArea:add")
	@Log(title = "城市", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjArea hjArea)
	{		
		return toAjax(hjAreaService.insertHjArea(hjArea));
	}

	/**
	 * 修改城市
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		HjArea hjArea = hjAreaService.selectHjAreaById(id);
		mmap.put("hjArea", hjArea);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存城市
	 */
	@RequiresPermissions("zhgd:hjArea:edit")
	@Log(title = "城市", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjArea hjArea)
	{		
		return toAjax(hjAreaService.updateHjArea(hjArea));
	}
	
	/**
	 * 删除城市
	 */
	@RequiresPermissions("zhgd:hjArea:remove")
	@Log(title = "城市", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjAreaService.deleteHjAreaByIds(ids));
	}
	
}
