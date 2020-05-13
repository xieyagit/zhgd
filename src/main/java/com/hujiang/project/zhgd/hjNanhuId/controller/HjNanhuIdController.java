package com.hujiang.project.zhgd.hjNanhuId.controller;

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
import com.hujiang.project.zhgd.hjNanhuId.domain.HjNanhuId;
import com.hujiang.project.zhgd.hjNanhuId.service.IHjNanhuIdService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 南湖项目id-我系统项目id 信息操作处理
 * 
 * @author hujiang
 * @date 2020-04-27
 */
@Controller
@RequestMapping("/zhgd/hjNanhuId")
public class HjNanhuIdController extends BaseController
{
    private String prefix = "zhgd/hjNanhuId";
	
	@Autowired
	private IHjNanhuIdService hjNanhuIdService;
	
	@RequiresPermissions("zhgd:hjNanhuId:view")
	@GetMapping()
	public String hjNanhuId()
	{
	    return prefix + "/hjNanhuId";
	}
	
	/**
	 * 查询南湖项目id-我系统项目id列表
	 */
	@RequiresPermissions("zhgd:hjNanhuId:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjNanhuId hjNanhuId)
	{
		startPage();
        List<HjNanhuId> list = hjNanhuIdService.selectHjNanhuIdList(hjNanhuId);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出南湖项目id-我系统项目id列表
	 */
	@RequiresPermissions("zhgd:hjNanhuId:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjNanhuId hjNanhuId)
    {
    	List<HjNanhuId> list = hjNanhuIdService.selectHjNanhuIdList(hjNanhuId);
        ExcelUtil<HjNanhuId> util = new ExcelUtil<HjNanhuId>(HjNanhuId.class);
        return util.exportExcel(list, "hjNanhuId");
    }
	
	/**
	 * 新增南湖项目id-我系统项目id
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存南湖项目id-我系统项目id
	 */
	@RequiresPermissions("zhgd:hjNanhuId:add")
	@Log(title = "南湖项目id-我系统项目id", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjNanhuId hjNanhuId)
	{		
		return toAjax(hjNanhuIdService.insertHjNanhuId(hjNanhuId));
	}

	/**
	 * 修改南湖项目id-我系统项目id
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjNanhuId hjNanhuId = hjNanhuIdService.selectHjNanhuIdById(id);
		mmap.put("hjNanhuId", hjNanhuId);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存南湖项目id-我系统项目id
	 */
	@RequiresPermissions("zhgd:hjNanhuId:edit")
	@Log(title = "南湖项目id-我系统项目id", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjNanhuId hjNanhuId)
	{		
		return toAjax(hjNanhuIdService.updateHjNanhuId(hjNanhuId));
	}
	
	/**
	 * 删除南湖项目id-我系统项目id
	 */
	@RequiresPermissions("zhgd:hjNanhuId:remove")
	@Log(title = "南湖项目id-我系统项目id", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjNanhuIdService.deleteHjNanhuIdByIds(ids));
	}
	
}
