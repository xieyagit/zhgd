package com.hujiang.project.zhgd.lyRegistrationRecord.controller;

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
import com.hujiang.project.zhgd.lyRegistrationRecord.domain.LyRegistrationRecord;
import com.hujiang.project.zhgd.lyRegistrationRecord.service.ILyRegistrationRecordService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 登记记录 信息操作处理
 * 
 * @author hujiang
 * @date 2020-03-06
 */
@Controller
@RequestMapping("/zhgd/lyRegistrationRecord")
public class LyRegistrationRecordController extends BaseController
{
    private String prefix = "zhgd/lyRegistrationRecord";
	
	@Autowired
	private ILyRegistrationRecordService lyRegistrationRecordService;
	
	@RequiresPermissions("zhgd:lyRegistrationRecord:view")
	@GetMapping()
	public String lyRegistrationRecord()
	{
	    return prefix + "/lyRegistrationRecord";
	}
	
	/**
	 * 查询登记记录列表
	 */
	@RequiresPermissions("zhgd:lyRegistrationRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(LyRegistrationRecord lyRegistrationRecord)
	{
		startPage();
        List<LyRegistrationRecord> list = lyRegistrationRecordService.selectLyRegistrationRecordList(lyRegistrationRecord);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出登记记录列表
	 */
	@RequiresPermissions("zhgd:lyRegistrationRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LyRegistrationRecord lyRegistrationRecord)
    {
    	List<LyRegistrationRecord> list = lyRegistrationRecordService.selectLyRegistrationRecordList(lyRegistrationRecord);
        ExcelUtil<LyRegistrationRecord> util = new ExcelUtil<LyRegistrationRecord>(LyRegistrationRecord.class);
        return util.exportExcel(list, "lyRegistrationRecord");
    }
	
	/**
	 * 新增登记记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存登记记录
	 */
	@RequiresPermissions("zhgd:lyRegistrationRecord:add")
	@Log(title = "登记记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(LyRegistrationRecord lyRegistrationRecord)
	{		
		return toAjax(lyRegistrationRecordService.insertLyRegistrationRecord(lyRegistrationRecord));
	}

	/**
	 * 修改登记记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		LyRegistrationRecord lyRegistrationRecord = lyRegistrationRecordService.selectLyRegistrationRecordById(id);
		mmap.put("lyRegistrationRecord", lyRegistrationRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存登记记录
	 */
	@RequiresPermissions("zhgd:lyRegistrationRecord:edit")
	@Log(title = "登记记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(LyRegistrationRecord lyRegistrationRecord)
	{		
		return toAjax(lyRegistrationRecordService.updateLyRegistrationRecord(lyRegistrationRecord));
	}
	
	/**
	 * 删除登记记录
	 */
	@RequiresPermissions("zhgd:lyRegistrationRecord:remove")
	@Log(title = "登记记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(lyRegistrationRecordService.deleteLyRegistrationRecordByIds(ids));
	}
	
}
