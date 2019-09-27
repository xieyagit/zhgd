package com.hujiang.project.zhgd.zhNode.controller;

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
import com.hujiang.project.zhgd.zhNode.domain.ZhPrepose;
import com.hujiang.project.zhgd.zhNode.service.IZhPreposeService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 节点关联 信息操作处理
 * 
 * @author hujiang
 * @date 2019-08-05
 */
@Controller
@RequestMapping("/zhgd/zhPrepose")
public class ZhPreposeController extends BaseController
{
    private String prefix = "zhgd/zhPrepose";
	
	@Autowired
	private IZhPreposeService zhPreposeService;
	
	@RequiresPermissions("zhgd:zhPrepose:view")
	@GetMapping()
	public String zhPrepose()
	{
	    return prefix + "/zhPrepose";
	}
	
	/**
	 * 查询节点关联列表
	 */
	@RequiresPermissions("zhgd:zhPrepose:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ZhPrepose zhPrepose)
	{
		startPage();
        List<ZhPrepose> list = zhPreposeService.selectZhPreposeList(zhPrepose);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出节点关联列表
	 */
	@RequiresPermissions("zhgd:zhPrepose:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ZhPrepose zhPrepose)
    {
    	List<ZhPrepose> list = zhPreposeService.selectZhPreposeList(zhPrepose);
        ExcelUtil<ZhPrepose> util = new ExcelUtil<ZhPrepose>(ZhPrepose.class);
        return util.exportExcel(list, "zhPrepose");
    }
	
	/**
	 * 新增节点关联
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存节点关联
	 */
	@RequiresPermissions("zhgd:zhPrepose:add")
	@Log(title = "节点关联", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ZhPrepose zhPrepose)
	{		
		return toAjax(zhPreposeService.insertZhPrepose(zhPrepose));
	}

	/**
	 * 修改节点关联
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ZhPrepose zhPrepose = zhPreposeService.selectZhPreposeById(id);
		mmap.put("zhPrepose", zhPrepose);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存节点关联
	 */
	@RequiresPermissions("zhgd:zhPrepose:edit")
	@Log(title = "节点关联", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ZhPrepose zhPrepose)
	{		
		return toAjax(zhPreposeService.updateZhPrepose(zhPrepose));
	}
	
	/**
	 * 删除节点关联
	 */
	@RequiresPermissions("zhgd:zhPrepose:remove")
	@Log(title = "节点关联", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(int id)
	{		
		return toAjax(zhPreposeService.deleteZhPreposeById(id));
	}
	
}
