package com.hujiang.project.zhgd.hjAttendanceDevice.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.ys.util.YsUtil;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.utils.Constants;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
    @RequestMapping(value = "/provider/attendanceDeviceApi")
    public class AttendanceDeviceApi extends BaseController {

    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @Resource
    private YsUtil ysUtil;

    @PostMapping(value = "/insertAttendanceDevice")
    public AjaxResult insertAttendanceDevice(@RequestBody HjAttendanceDevice hjAttendanceDevice) throws Exception {
        if ("ys".equals(hjAttendanceDevice.getDeviceFactory())) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("accessToken", ysUtil.getAccessToken()));
            params.add(new BasicNameValuePair("deviceSerial", hjAttendanceDevice.getDeviceNo()));
            params.add(new BasicNameValuePair("validateCode", hjAttendanceDevice.getRemark()));
            String result = ysUtil.httpPostWithJSON(Constants.OPEN_YS + "res/device/add", params);
            JSONObject resultObject = JSONObject.parseObject(result);
            if (!"200".equals(resultObject.getString("code"))) {
                return AjaxResult.error(resultObject.getString("msg"));
            }
        }
        int i = hjAttendanceDeviceService.insertHjAttendanceDevice(hjAttendanceDevice);
        if (i != 1) {
            return AjaxResult.error("新增失败");
        }

        return AjaxResult.success("新增成功");
    }

    @PostMapping(value = "/updateAttendanceDevice")
    public AjaxResult updateAttendanceDevice(@RequestBody HjAttendanceDevice hjAttendanceDevice) {
        int i = hjAttendanceDeviceService.updateHjAttendanceDevice(hjAttendanceDevice);
        if (i != 1) {
            return AjaxResult.error("修改失败");
        }

        return AjaxResult.success("修改成功");
    }

    @PostMapping(value = "/selectAttendanceDevice")
    public TableDataInfo selectAttendanceDevice(@RequestBody HjAttendanceDevice hjAttendanceDevice) throws Exception {
        startPage();
        List<HjAttendanceDevice> list = hjAttendanceDeviceService.selectHjAttendanceDeviceList(hjAttendanceDevice);
        HjAttendanceDevice had;
        List<HjAttendanceDevice> hadList = new ArrayList<HjAttendanceDevice>();
        for (int i = 0; i < list.size(); i++) {
            had = list.get(i);
            had.setIsConnect(comparisonDate(had.getConnectTime()));
        }
        return getDataTable(list);
    }

    /**
     * 判断设备是否断线
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public String comparisonDate(String time) throws ParseException {
        if (!"".equals(time) && time != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date kqTime = sdf.parse(time);//考勤时间
            Calendar beforeTime = Calendar.getInstance();
            beforeTime.add(Calendar.MINUTE, -15);//
            Date dqTime = sdf.parse(sdf.format(beforeTime.getTime()));//当前时间
            if (dqTime.before(kqTime)) {
                return "1";
            } else {
                return "0";
            }

        }else{
            return "0";
        }
    }

    /**
     * 删除人脸机设备
     * @param ids
     * @return
     */
    @PostMapping(value = "remove")
    public AjaxResult remove(@RequestParam("ids") String ids)
    {
        return toAjax(hjAttendanceDeviceService.deleteHjAttendanceDeviceByIds(ids));
    }

}