package com.hujiang.project.zhgd.hjAttendanceLocation.api;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.ys.util.YsUtil;
import com.hujiang.project.zhgd.hjAttendanceLocation.domain.HjAttendanceLocation;
import com.hujiang.project.zhgd.hjAttendanceLocation.service.IHjAttendanceLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/location")
public class HjAttendanceLocationApi extends BaseController {

    @Autowired
    private IHjAttendanceLocationService hjAttendanceLocationService;

    @PostMapping(value = "/insert")
    public AjaxResult insertHjAttendanceLocation(@RequestBody HjAttendanceLocation hjAttendanceLocation) {
        int i = hjAttendanceLocationService.insertHjAttendanceLocation(hjAttendanceLocation);
        if (i != 1) {
            return AjaxResult.error("新增失败");
        }
        return AjaxResult.success("新增成功");
    }

    @PostMapping(value = "/modify")
    public AjaxResult updateHjAttendanceLocation(@RequestBody HjAttendanceLocation hjAttendanceLocation) {
        int i = hjAttendanceLocationService.updateHjAttendanceLocation(hjAttendanceLocation);
        if (i != 1) {
            return AjaxResult.error("修改失败");
        }
        return AjaxResult.success("修改成功");
    }

    @RequestMapping(value = "/query")
    @ResponseBody
    public AjaxResult selectHjAttendanceLocationList(@RequestParam(value = "projectId") Integer projectId) {
        HjAttendanceLocation hjAttendanceLocation = new HjAttendanceLocation();
        hjAttendanceLocation.setProjectId(projectId);
        List<HjAttendanceLocation> list = hjAttendanceLocationService.selectHjAttendanceLocationList(hjAttendanceLocation);
        return AjaxResult.success(list);
    }

    /**
     * @param id
     * @return
     */
    @PostMapping(value = "remove")
    public AjaxResult deleteHjAttendanceLocationByIds(@RequestParam("id") String id) {
        int i = hjAttendanceLocationService.deleteHjAttendanceLocationByIds(id);
        if (i != 1) {
            return AjaxResult.error("删除失败");
        }
        return AjaxResult.success("删除成功");
    }

}