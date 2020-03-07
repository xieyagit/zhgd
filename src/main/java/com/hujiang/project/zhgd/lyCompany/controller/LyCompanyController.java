package com.hujiang.project.zhgd.lyCompany.controller;

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
import com.hujiang.project.zhgd.lyCompany.domain.LyCompany;
import com.hujiang.project.zhgd.lyCompany.service.ILyCompanyService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 楼宇公司 信息操作处理
 * 
 * @author hujiang
 * @date 2020-03-05
 */
@Controller
@RequestMapping("/zhgd/lyCompany")
public class LyCompanyController extends BaseController
{
    private String prefix = "zhgd/lyCompany";
	
	@Autowired
	private ILyCompanyService lyCompanyService;
	
	@RequiresPermissions("zhgd:lyCompany:view")
	@GetMapping()
	public String lyCompany()
	{
	    return prefix + "/lyCompany";
	}
	
	/**
	 * 查询楼宇公司列表
	 */
	@RequiresPermissions("zhgd:lyCompany:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(LyCompany lyCompany)
	{
		startPage();
        List<LyCompany> list = lyCompanyService.selectLyCompanyList(lyCompany);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出楼宇公司列表
	 */
	@RequiresPermissions("zhgd:lyCompany:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LyCompany lyCompany)
    {
    	List<LyCompany> list = lyCompanyService.selectLyCompanyList(lyCompany);
        ExcelUtil<LyCompany> util = new ExcelUtil<LyCompany>(LyCompany.class);
        return util.exportExcel(list, "lyCompany");
    }
	
	/**
	 * 新增楼宇公司
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存楼宇公司
	 */
	@RequiresPermissions("zhgd:lyCompany:add")
	@Log(title = "楼宇公司", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(LyCompany lyCompany)
	{		
		return toAjax(lyCompanyService.insertLyCompany(lyCompany));
	}

	/**
	 * 修改楼宇公司
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		LyCompany lyCompany = lyCompanyService.selectLyCompanyById(id);
		mmap.put("lyCompany", lyCompany);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存楼宇公司
	 */
	@RequiresPermissions("zhgd:lyCompany:edit")
	@Log(title = "楼宇公司", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(LyCompany lyCompany)
	{		
		return toAjax(lyCompanyService.updateLyCompany(lyCompany));
	}
	
	/**
	 * 删除楼宇公司
	 */
	@RequiresPermissions("zhgd:lyCompany:remove")
	@Log(title = "楼宇公司", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(lyCompanyService.deleteLyCompanyByIds(ids));
	}
	
}
