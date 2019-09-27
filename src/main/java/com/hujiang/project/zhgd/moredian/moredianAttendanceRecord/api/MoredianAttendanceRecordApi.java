package com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.domain.MoredianAttendanceRecord;
import com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.service.IMoredianAttendanceRecordService;
import com.hujiang.project.zhgd.moredian.moredianGroup.service.IMoredianGroupService;
import com.hujiang.project.zhgd.moredian.moredianGroupPerson.service.IMoredianGroupPersonService;
import com.hujiang.project.zhgd.moredian.moredianOrg.service.IMoredianOrgService;
import com.hujiang.project.zhgd.moredian.moredianOrgDevice.domain.MoredianOrgDevice;
import com.hujiang.project.zhgd.moredian.moredianOrgDevice.service.IMoredianOrgDeviceService;
import com.hujiang.project.zhgd.moredian.moredianPerson.domain.MoredianPerson;
import com.hujiang.project.zhgd.moredian.moredianPerson.service.IMoredianPersonService;
import com.hujiang.project.zhgd.utils.APIClient;
import com.hujiang.project.zhgd.utils.MoreDianAliyunOSSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @program: LZ
 * @description: 魔点考勤记录Api
 * @author: Mr.LiuYong
 * @create: 2019-05-14 08:59
 **/
@RestController
@RequestMapping(value = "/api/moredian",method = RequestMethod.POST)
public class MoredianAttendanceRecordApi extends BaseController {

    @Autowired
    private IMoredianOrgDeviceService moredianOrgDeviceService;
    @Autowired
    private IMoredianAttendanceRecordService moredianAttendanceRecordService;
    private Logger logger = Logger.getLogger(MoredianAttendanceRecordApi.class.getName());
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;
    @Autowired
    private IMoredianPersonService moredianPersonService; //魔点人员
    @Autowired
    private IHjProjectWorkersService projectWorkersService;//项目人员
    @Resource
    private APIClient apiClient;
    @Resource
    private HttpServletRequest request;
    /**
     * 功能描述 :接收上传的考勤记录
     * @author Mr.LiuYong
     * @date  2019/5/14 9:02
     * @param  json
     * @return com.hujiang.framework.web.domain.AjaxResult
     */
    @RequestMapping(value = "record")
    public AjaxResult record( @RequestParam(value = "json") String json)throws Exception{
        logger.info("com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.api.MoredianAttendanceRecordApi.record:\n请求地址"+request.getRemoteAddr());
        logger.info("com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.api.MoredianAttendanceRecordApi.record:\n接收数据"+json);
        JSONObject object = JSONObject.parseObject(json);
        JSONObject data = JSONObject.parseObject(object.getString("data"));
        if(object.getString("callbackTag").equals("REC_SUCCESS")){
            MoredianAttendanceRecord moredianAttendanceRecord = data.toJavaObject(MoredianAttendanceRecord.class);
            moredianAttendanceRecord.setFileKey(moredianAttendanceRecord.getFileKey());
            //毫秒转时间
            moredianAttendanceRecord.setRecognizeTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong(data.getString("recognizeTime")))));
            //根据设备sn查询设备备注
            MoredianOrgDevice device = new MoredianOrgDevice();
            device.setDeviceSn(moredianAttendanceRecord.getDeviceSn());
            MoredianOrgDevice moredianOrgDevice = moredianOrgDeviceService.selectMoredianOrgDeviceList(device).get(0);
            //-----------------------------------------
            //根据考勤魔点人员id查询人员信息
            MoredianPerson moredianPerson = new MoredianPerson();
            moredianPerson.setMemberId(moredianAttendanceRecord.getMemberId());
            List<MoredianPerson> moredianPeople = moredianPersonService.selectMoredianPersonList(moredianPerson);
            for(MoredianPerson moredianPerson1:moredianPeople){
                //根据查询的人员id查询项目人员信息
                HjProjectWorkers hjProjectWorkers = projectWorkersService.selectHjProjectWorkersById(moredianPerson1.getTpUserId());
                if(hjProjectWorkers!=null){
                    //添加项目人员考勤记录
                    HjAttendanceRecord attendanceRecord = new HjAttendanceRecord();
                    //人臉照
                    attendanceRecord.setSitePhoto(MoreDianAliyunOSSClientUtil.urlPrefix+"/"+moredianAttendanceRecord.getFileKey());
                    //人員id
                    attendanceRecord.setEmployeeId(hjProjectWorkers.getId());
                    //项目id
                    attendanceRecord.setProjectId(hjProjectWorkers.getProjectId());
                    //考勤时间
                    attendanceRecord.setPassedTime(moredianAttendanceRecord.getRecognizeTime());
                    //通行方式 1—人脸识别，2—虹膜识别，3—指纹识别，4—掌形识别，5—身份证识别，6—实名卡，7—异常清退（适用于人员没有通过闸机系统出工地而导致人员状态不一致的情况），8—一键开闸(需要与闸机交互)， 9—应急通道（不需要与闸机交互），10—二维码识别，11-其他方式
                    attendanceRecord.setWay(1);
                    if(moredianOrgDevice.getdeviceRemark().equals("上下班考勤")){
                        Integer hh = Integer.parseInt(moredianAttendanceRecord.getRecognizeTime().substring(11,13));
                        if(hh>12){//考勤时间为12点之后
                            attendanceRecord.setDirection("out");//为下班考勤

                        }else{
                            attendanceRecord.setDirection("in");//为上班考勤
                        }
                    }else if(moredianOrgDevice.getdeviceRemark().equals("下班考勤")){
                        attendanceRecord.setDirection("out");
                    }else if(moredianOrgDevice.getdeviceRemark().equals("上班考勤")){
                        attendanceRecord.setDirection("in");
                    }
                    int i = hjAttendanceRecordService.insertHjAttendanceRecord(attendanceRecord);
                    logger.info("添加項目人員考勤信息"+i);
                    Boolean aBoolean = apiClient.uploadPassedLogTest(hjProjectWorkers, attendanceRecord.getDirection(), attendanceRecord.getPassedTime(),attendanceRecord.getSitePhoto());
                    logger.info("同步考勤记录"+aBoolean);

                }
            }

            return toAjax(moredianAttendanceRecordService.insertMoredianAttendanceRecord(moredianAttendanceRecord));
        }
        return error("数据接收失败："+object);
    }




}
