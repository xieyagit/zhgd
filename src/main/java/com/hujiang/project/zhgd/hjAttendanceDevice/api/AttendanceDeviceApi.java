package com.hujiang.project.zhgd.hjAttendanceDevice.api;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/provider/attendanceDeviceApi")
public class AttendanceDeviceApi {

    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;

    @PostMapping(value = "/insertAttendanceDevice")
    public AjaxResult insertAttendanceDevice(@RequestBody HjAttendanceDevice hjAttendanceDevice){
        int i=hjAttendanceDeviceService.insertHjAttendanceDevice(hjAttendanceDevice);
        if(i!=1){
            return AjaxResult.error("新增失败");
        }

        return AjaxResult.success("新增成功");
    }
     @PostMapping(value = "/updateAttendanceDevice")
    public AjaxResult updateAttendanceDevice(@RequestBody HjAttendanceDevice hjAttendanceDevice){
        int i=hjAttendanceDeviceService.updateHjAttendanceDevice(hjAttendanceDevice);
        if(i!=1){
            return AjaxResult.error("修改失败");
        }

        return AjaxResult.success("修改成功");
    }

}
