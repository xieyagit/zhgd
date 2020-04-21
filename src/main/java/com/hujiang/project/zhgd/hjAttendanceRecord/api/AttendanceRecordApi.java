package com.hujiang.project.zhgd.hjAttendanceRecord.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.DateUtils;
import com.hujiang.common.utils.http.HttpUtils;
import com.hujiang.project.common.AesUtils;
import com.hujiang.project.common.CommonUtils;
import com.hujiang.project.common.FuJianUtils;
import com.hujiang.project.common.LoggerUitls;
import com.hujiang.project.common.Result;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.AttendanceRecordDto;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjConstructionCompany.service.IHjConstructionCompanyService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.hjTeam.domain.HjTeam;
import com.hujiang.project.zhgd.hjTeam.service.IHjTeamService;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * App两制考勤记录
 *
 * @author hujiang
 * @date 2019-05-19
 */
@RestController
@RequestMapping(value = "/provider/attendanceRecordApi", method = RequestMethod.POST)
public class AttendanceRecordApi {


    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;

    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    @Autowired
    private IHjProjectService projectService;

    @Autowired
    private IHjTeamService hjTeamService;

    @Autowired
    private IHjConstructionCompanyService hjConstructionCompanyService;
//    @Autowired
//    private DateUtils dateUtils;


    /**
     * 劳务工人考勤情况
     * @param hjAttendanceRecord 考勤记录表
     * @return
     */
    @RequestMapping("/selectWorkerAttendance")
    @ResponseBody
    public Map<String, Object> selectWorkerAttendance(@RequestBody HjAttendanceRecord hjAttendanceRecord) {
        Map<String, Object> result = hjAttendanceRecordService.selectWorkerAttendance(hjAttendanceRecord.getProjectId());
        return result;
    }

    /**
     * 管理人员考勤情况
     * @param hjAttendanceRecord 考勤记录表
     * @return
     */
    @RequestMapping("/selectAdministration")
    @ResponseBody
    public Map<String, Object> selectAdministration(@RequestBody HjAttendanceRecord hjAttendanceRecord) {
        Map<String, Object> result = hjAttendanceRecordService.selectAdministration(hjAttendanceRecord.getProjectId());
        return result;
    }

    /**
     * 人脸考勤
     * @param
     * @return
     */
    @RequestMapping("/insertAdministration")
    @ResponseBody
    public Map<String, Object> insertAdministration(HjAttendanceRecord hjAttendanceRecord, MultipartFile file) {

        //福建两制上传工人考勤信息
        workerAttendance(hjAttendanceRecord);

        return hjAttendanceRecordService.insertAdministrationNew(hjAttendanceRecord, file);
    }

    private void workerAttendance(HjAttendanceRecord hjAttendanceRecord){
        HjProjectWorkers projectWorkers = hjProjectWorkersService.getById(hjAttendanceRecord.getEmployeeId());

        Integer projectId = hjAttendanceRecord.getProjectId();
        HjSynchronizationInformation hs = new HjSynchronizationInformation();
        hs.setProjectId(projectId);
        hs.setState(1);
        hs.setApiType("keytype1");
        List<HjSynchronizationInformation> hList = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);

        Map<String, Object> projectMap = projectService.getProject(projectId);
        HjProject project = JSONObject.parseObject(JSONObject.toJSONString(projectMap), HjProject.class);

        HjTeam hjTeam = new HjTeam();
        hjTeam.setProjectId(projectId);
        HjTeam hjTeam1 = hjTeamService.getHjTeam(hjTeam);

        String apiKey = null;
        String apiSecret = null;
        if (hList != null && hList.size() > 0) {
            apiKey = hList.get(0).getApiKey();
            apiSecret = hList.get(0).getApiSecret();
        }

        String paramStr = setParam(apiKey, apiSecret, hjAttendanceRecord, project, hjTeam1, projectWorkers);
        String msg = "上传工人考勤信息";
        try {
            Result result = HttpUtils.httpPostWithjson(Constants.HUJIAN_TWO_SYSTEMS, paramStr, msg);
            if ("0".equals(result.getCode()) && result.getData() != null) {
                LoggerUitls.logInfo("福建两制上传工人考勤信息成功", result.getCode(), result.getMessage(), result.getData());
            } else {
                LoggerUitls.logInfo("福建两制上传工人考勤信息失败", result.getCode(), result.getMessage(), result.getData());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String setParam(String apiKey, String apiSecret, HjAttendanceRecord hjAttendanceRecord, HjProject project, HjTeam hjTeam1, HjProjectWorkers projectWorkers) {
        Map apiParam = FuJianUtils.setHeader(FuJianUtils.WORKERATTENDANCE_UPLOAD);

        apiParam.put("appid", apiKey);

        Map param = setParam(apiSecret, hjAttendanceRecord, project, hjTeam1, projectWorkers);

        String data = JSON.toJSONString(param);
        apiParam.put("data", data);

        String sign = CommonUtils.getSign(apiParam, apiSecret);
        apiParam.put("sign", sign);
        return JSON.toJSONString(apiParam);
    }

    private Map<String, Object> setParam(String apiSecret, HjAttendanceRecord hjAttendanceRecord, HjProject project, HjTeam hjTeam1, HjProjectWorkers projectWorkers){
        Map<String, Object> map = new HashMap<>();
        map.put("projectCode", project.getProjectCode());
        map.put("teamSysNo", hjTeam1.getTeamSysno());
        List<AttendanceRecordDto> list = new ArrayList();
        AttendanceRecordDto attendanceRecord = new AttendanceRecordDto();
        if("in".equals(hjAttendanceRecord.getDirection())){
            attendanceRecord.setDirection("01");
        }
        if("out".equals(hjAttendanceRecord.getDirection())){
            attendanceRecord.setDirection("02");
        }

        attendanceRecord.setIdCardNumber(AesUtils.encrypt(projectWorkers.getIdCode(), apiSecret));
        attendanceRecord.setIdCardType("01");
        //判断一个人是进场还是退场   退场时间为空    或者退场时间大于当前时间    进场状态
        if(projectWorkers.getEndTime() == null || DateUtils.compareDate(DateUtils.stringToDate(projectWorkers.getEndTime()), new Date()) == 1){
            attendanceRecord.setDate(projectWorkers.getStartTime());
        }else{
            attendanceRecord.setDate(projectWorkers.getEndTime());
        }

        list.add(attendanceRecord);
        map.put("dataList", list);
        return map;
    }

    /**
     * 智慧工地 现场工种
     * */
    @PostMapping(value = "/getWorkType")
    public JSONObject list(Integer projectId) {
        JSONArray jsonArray = new JSONArray();
        JSONArray array = new JSONArray();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        String passedTime = dateNowStr;
        List<HjAttendanceRecord> list = hjAttendanceRecordService.list(projectId, passedTime);
        List lists = new ArrayList();
        for (HjAttendanceRecord attendanceRecord : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", attendanceRecord.getTitle());
            jsonObject.put("count", attendanceRecord.getCount());
            jsonArray.add(jsonObject);
        }
        if (list.size() == 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", "");
            jsonObject.put("count", "");
            jsonArray.add(jsonObject);
        }
        JSONObject object = new JSONObject();
        jsonArray.sort(Comparator.comparing(obj -> {
            Integer value = ((JSONObject) obj).getIntValue("count");
            return value;
        }).reversed());
        System.out.println("排序后(降序) :  " + jsonArray.toJSONString());
        Integer k = 0;
        if (jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                JSONObject job = jsonArray.getJSONObject(i);
                if (job != null) {
                    if (i < 5) {
                        jsonObject.put("title", job.get("title"));
                        jsonObject.put("count", job.get("count"));
                        array.add(jsonObject);
                    }
                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    if (i >= 5) {
                        k = k + job.getInteger("count");
                    }
                }
                if (k > 0) {
                    if (i == jsonArray.size() - 1) {
                        jsonObject.put("title", "其他");
                        jsonObject.put("count", k);
                        array.add(jsonObject);
                    }
                }
            }
        }
        object.put("xcgz", array);
        return object;
    }

    /**
     * 智慧工地 人员动态+班组动态
     * */
    @PostMapping(value = "/getTeamCount")
    public JSONObject turnover(Integer projectId) {
        /**人员动态*/
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);
        String passedTime = dateNowStr;
        List<HjAttendanceRecord> list = hjAttendanceRecordService.turnover(projectId, passedTime);
        JSONArray jsonArray = new JSONArray();
        for (HjAttendanceRecord attendanceRecord : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", attendanceRecord.getName());      //姓名
            jsonObject.put("createTime", attendanceRecord.getDirection());        //进出方向
            jsonObject.put("inOut_id", attendanceRecord.getPassedTime());    //进出时间
            jsonArray.add(jsonObject);
        }
        if (list.size() == 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", "");      //姓名
            jsonObject.put("createTime", "");        //进出方向
            jsonObject.put("inOut_id", "");    //进出时间
            jsonArray.add(jsonObject);
        }
        /** 班组动态*/
        JSONArray array = new JSONArray();
        int enter = 0;
        List<HjAttendanceRecord> lists = hjAttendanceRecordService.turnovers(projectId);
        HjAttendanceRecord attendanceRecords;
        HjAttendanceRecord aecords;
        for (HjAttendanceRecord record : lists) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("title", record.getName());
            jsonObject1.put("sum", record.getCount2());
            jsonObject1.put("kq", record.getCount1());
            array.add(jsonObject1);
        }
        if (lists.size() == 0) {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("kq", "");
            jsonObject1.put("sum", "");
            jsonObject1.put("title", "");
            jsonArray.add(jsonObject1);
        }
        JSONObject object = new JSONObject();
        object.put("list", jsonArray);
        object.put("team", array);
        return object;
    }

    /**
     * 智慧看板 分包单位考勤情况
     * */
    @PostMapping(value = "/getBuildcompanyData")
    public JSONObject item(Integer projectId) {
        JSONObject object = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        List<HjAttendanceRecord> itme = hjAttendanceRecordService.item(projectId);
        for (HjAttendanceRecord hjAttendanceRecord : itme) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", hjAttendanceRecord.getTitle());
            jsonObject.put("kq", hjAttendanceRecord.getCount2());
            jsonObject.put("zc", hjAttendanceRecord.getCount1());
            jsonArray.add(jsonObject);
        }
        if (itme.size() == 0) {
            object.put("name", "");
            object.put("kq", "");
            object.put("zc", "");
        } else {
            object.put("buildcompany", jsonArray);
        }
        return object;
    }

    /**今日劳动曲线*/
    @PostMapping(value = "/getXS")
    public JSONObject jrld(Integer projectId) throws ParseException {
        JSONArray jsonArray = new JSONArray();
        SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
        String passedTimes = sdfs.format(new Date());
        List<HjAttendanceRecord> hjAttendanceRecord = hjAttendanceRecordService.labour(projectId, passedTimes);
        for (HjAttendanceRecord attendanceRecord : hjAttendanceRecord) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(String.valueOf(sdf.format(sdf.parse(attendanceRecord.getPassedTime()))), attendanceRecord.getCount());
            jsonArray.add(jsonObject);
        }
        JSONObject object = new JSONObject();
        object.put("hour", jsonArray);
        return object;
    }

    /**项目出勤统计*/
    @PostMapping(value = "/getNearlyEightDays")
    public JSONObject attendance(Integer projectId) {
        JSONArray jsonArray = new JSONArray();
        JSONObject object = new JSONObject();
        List<HjAttendanceRecord> hjAttendanceRecord = hjAttendanceRecordService.attendance(projectId);
        List<HjAttendanceRecord> hjAttendanceRecord2 = hjAttendanceRecordService.attendances(projectId);
        for (int i = 0; i <= 8; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("date", hjAttendanceRecord.get(i).getPassedTime());
            jsonObject.put("workerCheck", hjAttendanceRecord2.get(i).getCount());
            jsonObject.put("managerCheck", hjAttendanceRecord.get(i).getCount() - hjAttendanceRecord2.get(i).getCount());
            jsonObject.put("count", hjAttendanceRecord.get(i).getCount());
            jsonArray.add(jsonObject);
        }
        object.put("list", jsonArray);
        return object;
    }

    /** 工人上工情况*/
    @PostMapping(value = "/TheWorkersWork")
    public JSONObject TheWorkersWork(Integer projectId) {
        JSONObject jsonObject = new JSONObject();
        Calendar cal = Calendar.getInstance();
        //获取今天的日期
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println(year + "-" + month + "-" + day);
        Date date = cal.getTime();
        //本月
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
        String dateStringYYYYMM = format1.format(date);
        System.out.println(dateStringYYYYMM);
        List<HjAttendanceRecord> record = hjAttendanceRecordService.items(projectId, dateStringYYYYMM);
        for (HjAttendanceRecord attendanceRecord : record) {
            if (record.size() <= 0) {
                jsonObject.put("thisMonth", "0");
            }
            jsonObject.put("thisMonth", attendanceRecord.getCount());
        }
        if (record.size() == 0) {
            jsonObject.put("thisMonth", "0");
        }
        SimpleDateFormat format11 = new SimpleDateFormat("yyyy-MM-dd");
        String dateStringYYYYMMDD = format11.format(date);
        //今日
        System.out.println(dateStringYYYYMMDD);
        List<HjAttendanceRecord> a = hjAttendanceRecordService.ite(projectId, dateStringYYYYMMDD);

        if (a.size() <= 0) {
            jsonObject.put("today", "0");
        } else {
            jsonObject.put("today", a.size());
            Integer contract = null;
            HjProjectWorkers projectWorkers = hjProjectWorkersService.easyContract(projectId);
            String tatol = Util.accuracy(a.size(), (projectWorkers.getCount()), 0);
            jsonObject.put("bfb", tatol + "% ");
        }
        //获取昨天的日期
        cal.setTime(new Date());
        //cal2.set(2018, 2, 1);
        cal.add(Calendar.DATE, -1);
        int year2 = cal.get(Calendar.YEAR);
        int month2 = cal.get(Calendar.MONTH) + 1;
        int day2 = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println(year2 + "-" + month2 + "-" + day2);
        Date date2 = cal.getTime();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String dateStringYYYYMMDD2 = format2.format(date2);
        System.out.println(dateStringYYYYMMDD2);
        List<HjAttendanceRecord> recorda = hjAttendanceRecordService.ite(projectId, dateStringYYYYMMDD2);
        if (recorda.size() == 0) {
            jsonObject.put("yesterday", "0");
        } else {
            jsonObject.put("yesterday", recorda.size());
        }
        //获取上个月的日期（上个月的今天）
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        int year3 = cal.get(Calendar.YEAR);
        int month3 = cal.get(Calendar.MONTH) + 1;
        int day3 = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println(year3 + "-" + month3 + "-" + day3);
        Date date3 = cal.getTime();
        SimpleDateFormat format3 = new SimpleDateFormat("yyyy-MM");
        String dateStringYYYYMMDD3 = format3.format(date3);
        System.out.println(dateStringYYYYMMDD3);
        List<HjAttendanceRecord> recors = hjAttendanceRecordService.items(projectId, dateStringYYYYMMDD3);
        for (HjAttendanceRecord attendanceRecord : recors) {
            jsonObject.put("ultimo", attendanceRecord.getCount());
        }
        if (recors.size() == 0) {
            jsonObject.put("ultimo", "0");
        }
        if (jsonObject.size() == 0) {
            jsonObject.put("ultimo", "0");
            jsonObject.put("yesterday", "0");
            jsonObject.put("today", "0");
            jsonObject.put("thisMonth", "0");
            jsonObject.put("bfb", "0");
        }
        return jsonObject;
    }

}
