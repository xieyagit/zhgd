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
import com.hujiang.project.zhgd.zhNode.domain.ZhNode;
import com.hujiang.project.zhgd.zhNode.service.IZhNodeService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 节点计划详情 信息操作处理
 * 
 * @author hujiang
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/zhgd/zhNode")
public class ZhNodeController extends BaseController
{
    private String prefix = "zhgd/zhNode";
	
	@Autowired
	private IZhNodeService zhNodeService;
	
	@RequiresPermissions("zhgd:zhNode:view")
	@GetMapping()
	public String zhNode()
	{
	    return prefix + "/zhNode";
	}
	
	/**
	 * 查询节点计划详情列表
	 */
	@RequiresPermissions("zhgd:zhNode:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ZhNode zhNode)
	{
		startPage();
        List<ZhNode> list = zhNodeService.selectZhNodeList(zhNode);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出节点计划详情列表
	 */
	@RequiresPermissions("zhgd:zhNode:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ZhNode zhNode)
    {
    	List<ZhNode> list = zhNodeService.selectZhNodeList(zhNode);
        ExcelUtil<ZhNode> util = new ExcelUtil<ZhNode>(ZhNode.class);
        return util.exportExcel(list, "zhNode");
    }
	
	/**
	 * 新增节点计划详情
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存节点计划详情
	 */
	@RequiresPermissions("zhgd:zhNode:add")
	@Log(title = "节点计划详情", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ZhNode zhNode)
	{
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.put("date",zhNodeService.insertZhNode(zhNode));
		return ajaxResult;
	}

	/**
	 * 修改节点计划详情
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ZhNode zhNode = zhNodeService.selectZhNodeById(id);
		mmap.put("zhNode", zhNode);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存节点计划详情
	 */
	@RequiresPermissions("zhgd:zhNode:edit")
	@Log(title = "节点计划详情", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ZhNode zhNode)
	{		
		return toAjax(zhNodeService.updateZhNode(zhNode));
	}
	
/*	*//**
	 * 删除节点计划详情
	 *//*
	@RequiresPermissions("zhgd:zhNode:remove")
	@Log(title = "节点计划详情", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(zhNodeService.deleteZhNodeByIds(ids));
	}*/
	
}
