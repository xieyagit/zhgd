package com.hujiang.project.zhgd.hjAttendanceDevice.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.ys.util.YsUtil;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.utils.Constants;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
    @RequestMapping(value = "/provider/attendanceDeviceApi")
    public class AttendanceDeviceApi {

        @Autowired
        private IHjAttendanceDeviceService hjAttendanceDeviceService;
        @Resource
        private YsUtil ysUtil;
        @PostMapping(value = "/insertAttendanceDevice")
        public AjaxResult insertAttendanceDevice(@RequestBody HjAttendanceDevice hjAttendanceDevice)throws Exception{
            if("ys".equals(hjAttendanceDevice.getDeviceFactory())){
                List<NameValuePair> params=new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("accessToken", ysUtil.getAccessToken()));
                params.add(new BasicNameValuePair("deviceSerial",hjAttendanceDevice.getDeviceNo()));
                params.add(new BasicNameValuePair("validateCode", hjAttendanceDevice.getRemark()));
                String result= ysUtil.httpPostWithJSON(Constants.OPEN_YS+"res/device/add",params);
                JSONObject resultObject=JSONObject.parseObject(result);
                if(!"200".equals(resultObject.getString("code"))){
                    return AjaxResult.error(resultObject.getString("msg"));
                }
            }
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