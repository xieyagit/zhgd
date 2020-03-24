package com.hujiang.project.zhgd.lyAttendanceRecord.api;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecordPersonnel;
import com.hujiang.project.zhgd.lyAttendanceRecord.service.ILyAttendanceRecordService;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyCompanyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.service.ILyPersonnelService;
import com.hujiang.project.zhgd.lyRegistrationRecord.service.ILyRegistrationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/provider/lyRecord",method = RequestMethod.POST)
public class LyRecordApi extends BaseController {
    @Autowired
    private ILyAttendanceRecordService lyAttendanceRecordService;


    /**
     * 信息查询人员资料
     */
    @PostMapping("/selectPersonnelRecord")
    public AjaxResult selectPersonnelRecord(@RequestBody LyAttendanceRecord lyAttendanceRecord){
            List<LyAttendanceRecord> lar=lyAttendanceRecordService.selectLyAttendanceRecordListTwo(lyAttendanceRecord);
            return AjaxResult.success(lar);
    }
    /**
     * pc通行记录
     */
    @PostMapping("/selectPersonnelRecordPageList")
    public AjaxResult selectPersonnelRecordPageList(@RequestBody LyAttendanceRecordPersonnel lyAttendanceRecordPersonnel){
        startPage();
        List<LyAttendanceRecordPersonnel> larpList=lyAttendanceRecordService.selectPersonnelRecordPageList(lyAttendanceRecordPersonnel);
        return AjaxResult.success(getDataTable(larpList));
    }
}
