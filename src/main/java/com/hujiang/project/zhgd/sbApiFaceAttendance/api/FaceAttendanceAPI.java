package com.hujiang.project.zhgd.sbApiFaceAttendance.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbApiFaceAttendance.domain.SbApiFaceAttendance;
import com.hujiang.project.zhgd.sbApiFaceAttendance.service.ISbApiFaceAttendanceService;
import com.hujiang.project.zhgd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-07-01 11:07
 **/
@RestController
@RequestMapping(value = "/provider/FaceAttendanceAPI",method = RequestMethod.POST)
public class FaceAttendanceAPI {
    private Logger logger = Logger.getLogger(FaceAttendanceAPI.class.getName());
    @Autowired
    private ISbApiFaceAttendanceService attendanceService;

    @PostMapping("getAttendance")
    public Map<String,Object> getAttendance(@RequestBody SbApiFaceAttendance apiFaceAttendance){
        logger.info("\r com.hujiang.project.zhgd.sbApiFaceAttendance.api.FaceAttendanceAPI.getAttendance" +
                "获取数据："+apiFaceAttendance.toString()
        );
        return attendanceService.insertSbApiFaceAttendanceResult(apiFaceAttendance);
    }

}
