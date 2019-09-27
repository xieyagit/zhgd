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
import com.hujiang.project.zhgd.zhNode.domain.ZhProgressNode;
import com.hujiang.project.zhgd.zhNode.service.IZhProgressNodeService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 进度节点 信息操作处理
 * 
 * @author hujiang
 * @date 2019-08-05
 */
@Controller
@RequestMapping("/zhgd/zhProgressNode")
public class ZhProgressNodeController extends BaseController
{
    private String prefix = "zhgd/zhProgressNode";
	
	@Autowired
	private IZhProgressNodeService zhProgressNodeService;
	
	@RequiresPermissions("zhgd:zhProgressNode:view")
	@GetMapping()
	public String zhProgressNode()
	{
	    return prefix + "/zhProgressNode";
	}
	
	/**
	 * 查询进度节点列表
	 */
	@RequiresPermissions("zhgd:zhProgressNode:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ZhProgressNode zhProgressNode)
	{
		startPage();
        List<ZhProgressNode> list = zhProgressNodeService.selectZhProgressNodeList(zhProgressNode);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出进度节点列表
	 */
	@RequiresPermissions("zhgd:zhProgressNode:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ZhProgressNode zhProgressNode)
    {
    	List<ZhProgressNode> list = zhProgressNodeService.selectZhProgressNodeList(zhProgressNode);
        ExcelUtil<ZhProgressNode> util = new ExcelUtil<ZhProgressNode>(ZhProgressNode.class);
        return util.exportExcel(list, "zhProgressNode");
    }
	
	/**
	 * 新增进度节点
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存进度节点
	 */
	@RequiresPermissions("zhgd:zhProgressNode:add")
	@Log(title = "进度节点", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ZhProgressNode zhProgressNode)
	{		
		return toAjax(zhProgressNodeService.insertZhProgressNode(zhProgressNode));
	}

	/**
	 * 修改进度节点
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ZhProgressNode zhProgressNode = zhProgressNodeService.selectZhProgressNodeById(id);
		mmap.put("zhProgressNode", zhProgressNode);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存进度节点
	 */
	@RequiresPermissions("zhgd:zhProgressNode:edit")
	@Log(title = "进度节点", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ZhProgressNode zhProgressNode)
	{		
		return toAjax(zhProgressNodeService.updateZhProgressNode(zhProgressNode));
	}
	
	/**
	 * 删除进度节点
	 */
	@RequiresPermissions("zhgd:zhProgressNode:remove")
	@Log(title = "进度节点", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(zhProgressNodeService.deleteZhProgressNodeByIds(ids));
	}
	
}
