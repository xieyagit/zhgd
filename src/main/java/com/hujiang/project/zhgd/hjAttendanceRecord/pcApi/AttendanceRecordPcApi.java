package com.hujiang.project.zhgd.hjAttendanceRecord.pcApi;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.Param;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.utils.AliyunOSSClientUtil;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * pc两制考勤记录
 *
 * @author hujiang
 * @date 2019-05-19
 */
@RestController
@RequestMapping(value = "/provider/attendanceRecordPcApi",method = RequestMethod.POST)
public class AttendanceRecordPcApi extends BaseController {
    private Logger logger = Logger.getLogger(AttendanceRecordPcApi.class.getName());

    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;
    @Autowired
    private IHjProjectWorkersService workersService;



    /**
     * pc考勤列表
     * @param
     * @return
     */
    @RequestMapping("/selectAttendanceRecordList")
    @ResponseBody
    public AjaxResult selectAttendanceRecordListTwo(@RequestBody Param param, PageDomain pageDomain )
    {
        logger.info("pc查询考勤记录列表开始---selectAttendanceRecordList---start");
        startPage();
        List<Param> paramList=hjAttendanceRecordService.selectAttendanceRecordListTwo(param);
        logger.info("pc查询考勤记录列表结束---selectAttendanceRecordList---end");
        return AjaxResult.success(getDataTable(paramList));
    }



    /**
     * 导出考勤数据源
     * @param param
     * @return
     */
    @RequestMapping("/export")
    @ResponseBody
    public List<Param> export(@RequestBody Param param ){
        return hjAttendanceRecordService.selectAttendanceRecordListTwo(param);
    }


    /**
     * 报表数据
     * @return
     */
    @RequestMapping("/getBB")
    @ResponseBody
    public Map<String,Object> getBB(HjProjectWorkers workers){

        List<HjProjectWorkers> workers1 = workersService.selectHjProjectWorkersList(workers);

//        List<HjAttendanceRecord> hjAttendanceRecords = hjAttendanceRecordService.selectHjAttendanceRecordInAndOut(projectId, time);
        Map<String,Object> result = new HashMap<>();
//        String f = "0";
//        for(HjAttendanceRecord attendanceRecord:hjAttendanceRecords){
//            attendanceRecord.setDirection("in");
//            HjProjectWorkers projectWorkers = workersService.selectHjProjectWorkersById(attendanceRecord.getEmployeeId());
//            HjAttendanceRecord in = hjAttendanceRecordService.selectHjAttendanceRecordListIn(attendanceRecord);
//                if(attendanceRecord!=null){
//                    attendanceRecord.setDirection("out");
//                    HjAttendanceRecord attendanceRecord1 = hjAttendanceRecordService.selectHjAttendanceRecordListOut(attendanceRecord);
//                    if(attendanceRecord1!=null){
//                       f=Util.getTime(attendanceRecord.getPassedTime(),attendanceRecord1.getPassedTime()).toString();
//                    }
//
//                }
//        }
        HjAttendanceRecord hjAttendanceRecord = new HjAttendanceRecord();
        for(HjProjectWorkers projectWorkers:workers1){//获取项目人员
            JSONObject object = new JSONObject();
            JSONArray array = new JSONArray();
            long millis = System.currentTimeMillis();//系统毫秒数

            hjAttendanceRecordService.addTime(projectWorkers.getId(),projectWorkers.getEmpName(),projectWorkers.getProjectId());
//            for(int i = 0; i<30;i++){
//                JSONObject jsonObject = new JSONObject();
//                millis=millis - (1000 * 60 * 60 * 24);
//                Date d = new Date(millis);
//                SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd");
//                String rq=sp.format(d);//获取日期
//                hjAttendanceRecord.setEmployeeId(projectWorkers.getId());
//                hjAttendanceRecord.setPassedTime(rq);
//                String integer = hjAttendanceRecordService.selectTime(hjAttendanceRecord);
//                DecimalFormat df=new DecimalFormat("0.0");
//
////                List<HjAttendanceRecord> hjAttendanceRecords = hjAttendanceRecordService.selectHjAttendanceRecordInAndOut(projectWorkers.getProjectId(), rq);
////                Float f = null;
////                if(hjAttendanceRecords!=null && hjAttendanceRecords.size()>0){
////
////                    HjAttendanceRecord record = new HjAttendanceRecord();
////                    record.setDirection("in");
////                    record.setPassedTime(rq);
////                    record.setEmployeeId(projectWorkers.getId());
////                    HjAttendanceRecord attendanceRecord = hjAttendanceRecordService.selectHjAttendanceRecordListIn(record);//上班考勤记录
////                    record.setDirection("out");
////                    HjAttendanceRecord attendanceRecord1 = hjAttendanceRecordService.selectHjAttendanceRecordListOut(record);//下班考勤记录
////                    f= Util.getTime(attendanceRecord.getPassedTime(),attendanceRecord1.getPassedTime());//工时
////
////
////                }
////                jsonObject.put(rq,f==null?0:f);
////                array.add(jsonObject);
//            }
//            object.put("gs",array);
//            result.put(projectWorkers.getEmpName(),object);
        }
        return result;
    }





}
