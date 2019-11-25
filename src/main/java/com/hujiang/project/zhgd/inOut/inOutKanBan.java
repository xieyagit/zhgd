package com.hujiang.project.zhgd.inOut;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.DongTai;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.Cqgztj;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.InOROut;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.TCount;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/provider/inOutKanBan")
public class inOutKanBan {
    private Logger logger = Logger.getLogger(inOutKanBan.class.getName());
    @Autowired
    private IHjProjectService projectService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;

    /**
     * 实名制进出看板
     */
    @PostMapping("/selectIndex")
    public AjaxResult selectIndex(Integer pid) {
        logger.info("实名制进出看板接口开始----/provider/inOutKanBan/selectIndex");
        //时间
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        HjProject hjProject = projectService.selectHjProjectById(pid);
        Map<String, Object> map = new HashMap<String, Object>();
        if (hjProject != null) {
            map.put("projectName", hjProject.getProjectName());
            //项目在线人数
            Integer a = hjProjectWorkersService.selectOnLineCount(pid);
            map.put("xmzxrs", a);
            //今日出勤人数
            if (a > 0) {
                Integer b = hjAttendanceRecordService.selectJinRiChuQin(pid);

                map.put("jrcqrs", b);
                map.put("cql", new DecimalFormat("0.00").format(((float) b / a) * 100));
            } else {
                map.put("jrcqrs", 0);
                map.put("cql", 0);
            }
            //关键人员数
            Integer d = hjProjectWorkersService.selectOnLineCountGj(pid);
            //关键人员出勤数
            if (d > 0) {
                Integer d2 = hjAttendanceRecordService.selectGJGWChuQin(pid);

                map.put("gjgwcqrs", d2);
                //关键岗位出勤率
                map.put("gjgwcql", new DecimalFormat("0.00").format(((float) d2 / d) * 100));
            } else {
                map.put("gjgwcqrs", 0);
                //关键岗位出勤率
                map.put("gjgwcql", 0);
            }
            //工种出勤统计
            List<Cqgztj> cList = hjProjectWorkersService.selectCqgztj(pid);
            map.put("gzList", cList);
            //工人动态
            List<DongTai> grdtList = hjAttendanceRecordService.selectGRKQDongTai(pid);
            //管理人员动态
            List<DongTai> glrydtList = hjAttendanceRecordService.selectGLKQDongTai(pid);
            map.put("grdtList", grdtList);
            map.put("glrydtList", glrydtList);
            //施工企业考勤情况
            List<TCount> zcList = hjAttendanceRecordService.getZCCount(pid);
            List<TCount> kqList = hjAttendanceRecordService.getKQCount(pid);

            JSONArray jsonArray = new JSONArray();
            for (TCount zc : zcList) {
                System.out.println(zcList.get(0).getTitle());
                JSONObject object = new JSONObject();
                object.put("name", zc.getTitle());//参建单位名称
                object.put("zc", zc.getCount());//参建单位在场人数
                for (TCount kq : kqList) {
                    if (zc.getTitle().equals(kq.getTitle())) {
                        object.put("kq", kq.getCount());//参建单位考勤人数
                        object.put("bfb", Util.accuracy(kq.getCount(), zc.getCount(), 2));//考勤百分比
                    }
                }
                if (kqList.size() == 0) {
                    object.put("kq", 0);//参建单位考勤人数
                    object.put("bfb", 0);//考勤百分比
                }
                jsonArray.add(object);
            }
            map.put("sgqykq", jsonArray);
            //照片
            HjAttendanceRecord hdr = new HjAttendanceRecord();
            hdr.setProjectId(pid);
            HjAttendanceRecord adr = hjAttendanceRecordService.selectHjAttendanceRecordListOut(hdr);
            if (adr != null) {
                Integer workId = adr.getEmployeeId();
                map.put("har", adr);
                InOROut inOrOut = hjProjectWorkersService.selectInOrOutKB(workId);
                map.put("inOrOut", inOrOut);
            } else {
                map.put("har", "");
                map.put("inOrOut", "");
            }
            //在场实时人数
            List<HjAttendanceRecord> rList = hjAttendanceRecordService.selectZCSSRS(pid);
            map.put("zcssrs", rList.size());
        } else {
            return AjaxResult.error("请输入正确项目编号");
        }
        logger.info("实名制进出看板接口结束----/provider/inOutKanBan/selectIndex");
        return AjaxResult.success(map);
    }

    /**
     * 实名制电视看板
     */
    @PostMapping(value = "/selectTV", produces = "application/json;charset=UTF-8")
    public AjaxResult selectTV(Integer pid) {
        logger.info("实名制电视看板----/provider/inOutKanBan/selectTV");
        //时间
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        HjProject hjProject = projectService.selectHjProjectById(pid);
        Map<String, Object> map = new HashMap<String, Object>();
        if (hjProject != null) {
            Map<String, Object> worker = hjAttendanceRecordService.selectWorkerAttendance(pid);
            Map<String, Object> manager = hjAttendanceRecordService.selectAdministration(pid);
            //今日出勤人数
            Map<String, Object> workerData = (Map<String, Object>) worker.get("data");
            Map<String, Object> managerData = (Map<String, Object>) manager.get("data");
            Integer attendanceWorker = (Integer) workerData.get("attendanceNumber");
            Integer attendanceManager = (Integer) managerData.get("attendanceNumber");
            Integer sceneWorker = (Integer) workerData.get("sceneNumber");
            Integer sceneManager = (Integer) managerData.get("sceneNumber");
            Integer attendanceNumber = attendanceWorker + attendanceManager;
            Integer sceneNumber = sceneWorker + sceneManager;
            map.put("attendanceWorker", attendanceWorker);
            map.put("attendanceManager", attendanceManager);
            map.put("sceneWorker", sceneWorker);
            map.put("sceneManager", sceneManager);
            map.put("attendanceNumber", attendanceNumber);
            map.put("sceneNumber", sceneNumber);
            //工人动态
            List<DongTai> workerList = hjAttendanceRecordService.selectWorkerList(pid);
            //管理人员动态
            List<DongTai> managerList = hjAttendanceRecordService.selectManagerList(pid);
            map.put("workerList", workerList);
            map.put("managerList", managerList);

            //施工企业考勤情况
            List<TCount> sceneList = hjAttendanceRecordService.getZCCount(pid);
            List<TCount> attendanceList = hjAttendanceRecordService.getKQCount(pid);
            JSONArray jsonArray = new JSONArray();
            for (TCount scene : sceneList) {
                System.out.println(sceneList.get(0).getTitle());
                JSONObject object = new JSONObject();
                object.put("name", scene.getTitle());//参建单位名称
                object.put("sceneNumber", scene.getCount());//参建单位在场人数
                for (TCount attendance : attendanceList) {
                    if (scene.getTitle().equals(attendance.getTitle())) {
                        object.put("attendanceNumber", attendance.getCount());//参建单位考勤人数
                        object.put("percent", Util.accuracy(attendance.getCount(), scene.getCount(), 2));//考勤百分比
                    }
                }
                if (attendanceList.size() == 0) {
                    object.put("attendanceNumber", 0);//参建单位考勤人数
                    object.put("percent", 0);//考勤百分比
                }
                jsonArray.add(object);
            }
            map.put("company", jsonArray);
            //照片
            HjAttendanceRecord hdr = new HjAttendanceRecord();
            hdr.setProjectId(pid);
            HjAttendanceRecord adr = hjAttendanceRecordService.selectNewHjAttendanceRecord(hdr);
            if (adr != null) {
                Integer workId = adr.getEmployeeId();
                map.put("har", adr);
                InOROut inOrOut = hjProjectWorkersService.selectInOrOutKB(workId);
                map.put("inOrOut", inOrOut);
            } else {
                map.put("har", "");
                map.put("inOrOut", "");
            }
        } else {
            return AjaxResult.error("请输入正确项目编号");
        }
        logger.info("实名制电视看板----/provider/inOutKanBan/selectTV");
        return AjaxResult.success(map);
    }

}
