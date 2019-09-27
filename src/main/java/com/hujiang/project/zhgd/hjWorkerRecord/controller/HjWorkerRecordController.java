package com.hujiang.project.zhgd.hjWorkerRecord.controller;

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
import com.hujiang.project.zhgd.hjWorkerRecord.domain.HjWorkerRecord;
import com.hujiang.project.zhgd.hjWorkerRecord.service.IHjWorkerRecordService;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.common.utils.poi.ExcelUtil;

/**
 * 工人从业记录 信息操作处理
 * 
 * @author hujiang
 * @date 2019-05-17
 */
@Controller
@RequestMapping("/zhgd/hjWorkerRecord")
public class HjWorkerRecordController extends BaseController
{
    private String prefix = "zhgd/hjWorkerRecord";
	
	@Autowired
	private IHjWorkerRecordService hjWorkerRecordService;
	
	@RequiresPermissions("zhgd:hjWorkerRecord:view")
	@GetMapping()
	public String hjWorkerRecord()
	{
	    return prefix + "/hjWorkerRecord";
	}
	
	/**
	 * 查询工人从业记录列表
	 */
	@RequiresPermissions("zhgd:hjWorkerRecord:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(HjWorkerRecord hjWorkerRecord)
	{
		startPage();
        List<HjWorkerRecord> list = hjWorkerRecordService.selectHjWorkerRecordList(hjWorkerRecord);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出工人从业记录列表
	 */
	@RequiresPermissions("zhgd:hjWorkerRecord:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HjWorkerRecord hjWorkerRecord)
    {
    	List<HjWorkerRecord> list = hjWorkerRecordService.selectHjWorkerRecordList(hjWorkerRecord);
        ExcelUtil<HjWorkerRecord> util = new ExcelUtil<HjWorkerRecord>(HjWorkerRecord.class);
        return util.exportExcel(list, "hjWorkerRecord");
    }
	
	/**
	 * 新增工人从业记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存工人从业记录
	 */
	@RequiresPermissions("zhgd:hjWorkerRecord:add")
	@Log(title = "工人从业记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(HjWorkerRecord hjWorkerRecord)
	{		
		return toAjax(hjWorkerRecordService.insertHjWorkerRecord(hjWorkerRecord));
	}

	/**
	 * 修改工人从业记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		HjWorkerRecord hjWorkerRecord = hjWorkerRecordService.selectHjWorkerRecordById(id);
		mmap.put("hjWorkerRecord", hjWorkerRecord);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存工人从业记录
	 */
	@RequiresPermissions("zhgd:hjWorkerRecord:edit")
	@Log(title = "工人从业记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(HjWorkerRecord hjWorkerRecord)
	{		
		return toAjax(hjWorkerRecordService.updateHjWorkerRecord(hjWorkerRecord));
	}
	
	/**
	 * 删除工人从业记录
	 */
	@RequiresPermissions("zhgd:hjWorkerRecord:remove")
	@Log(title = "工人从业记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hjWorkerRecordService.deleteHjWorkerRecordByIds(ids));
	}
	
}
