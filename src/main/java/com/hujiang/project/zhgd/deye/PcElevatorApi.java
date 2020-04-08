package com.hujiang.project.zhgd.deye;

import com.hujiang.common.utils.StringUtils;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbElevatorAddrecord.service.ISbElevatorAddrecordService;
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import com.hujiang.project.zhgd.sbElevatorBinding.service.ISbElevatorBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/provider/elevatorApi", method = RequestMethod.POST)
public class PcElevatorApi extends BaseController {
    private Logger logger = Logger.getLogger(PcElevatorApi.class.getName());
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private IHjProjectWorkersService projectWorkersService;
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;
    @Autowired
    private ISbElevatorBindingService sbElevatorBindingService;
    @Autowired
    private ISbElevatorAddrecordService sbElevatorAddrecordService;

    /**
     * 升降机界面接口
     * @param pid
     * @param hxzid
     * @return
     */
    @RequestMapping("/selectIndex")
    @ResponseBody
    public AjaxResult selectIndex(Integer pid, String hxzid) {
        logger.info("升降机界面接口执行开始");
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        paramMap.put("pid", pid);
        Calendar beforeTime = Calendar.getInstance();
        // 60分钟之前的时间
        beforeTime.add(Calendar.MINUTE, -60);
        String startTime = dateFormat.format(beforeTime.getTime());
        paramMap.put("time", startTime);

        //查询所有升降机
        List<SbElevatorAddrecord> seaList = sbElevatorAddrecordService.selectElevatorCount(paramMap);
        //在线升降机
        int zxElevator = seaList.size();

        //查询项目下所有升降机设备
        List<SbElevatorBinding> sebList = sbElevatorBindingService.selectSbElevatorBindingByPid(pid);
        if (sebList.size() > 0) {
            //升降机设备总数
            int number = sebList.size();
            //离线塔吊设备
            int lxElevator = number - zxElevator;
            map.put("zxElevator", zxElevator);
            map.put("lxElevator", lxElevator);
            //整个项目下的所有升降机
            //载重报警
            int a = sbElevatorAddrecordService.selectElevatorCountTwo(paramMap);
            //人数报警
            int b = sbElevatorAddrecordService.selectElevatorCountThree(paramMap);
            //速度报警
            int c = sbElevatorAddrecordService.selectElevatorCountFour(paramMap);
            //倾斜报警
            int d = sbElevatorAddrecordService.selectElevatorCountFive(paramMap);
            //上限位报警
            int e = sbElevatorAddrecordService.selectElevatorCountSix(paramMap);
            //下限位报警
            int f = sbElevatorAddrecordService.selectElevatorCountSeven(paramMap);
            //报警统计
            map.put("bjtj", a + b + c + d + e + f);
//            List list = new ArrayList();
//            for(SbElevatorBinding seb: sebList){
//                hxzid = seb.getHxzId();
//                paramMap.put("hxzid", hxzid);
//                //单个升降机
//                //指定设备载重报警
//                int a1 = sbElevatorAddrecordService.selectElevatorCountTwo(paramMap);
//                //指定设备人数报警
//                int b1 = sbElevatorAddrecordService.selectElevatorCountThree(paramMap);
//                //指定设备速度报警
//                int c1 = sbElevatorAddrecordService.selectElevatorCountFour(paramMap);
//                //指定设备倾斜报警
//                int d1 = sbElevatorAddrecordService.selectElevatorCountFive(paramMap);
//                //指定设备上限位报警
//                int e1 = sbElevatorAddrecordService.selectElevatorCountSix(paramMap);
//                //指定设备下限位报警
//                int f1 = sbElevatorAddrecordService.selectElevatorCountSeven(paramMap);
//                Map<String, Object> map1 = new HashMap<>();
//                map1.put("zzbj", a1);
//                map1.put("rsbj", b1);
//                map1.put("sdbj", c1);
//                map1.put("qxbj", d1);
//                map1.put("sxwbj", e1);
//                map1.put("xxwbj", f1);
//                map1.put("cdbj", e1);
//                list.add(map1);
//            }
//            map.put("seb", list);

            if (StringUtils.isBlank(hxzid)) {
                hxzid = sebList.get(0).getHxzId();
            }
            paramMap.remove("pid");
            paramMap.put("hxzid", hxzid);
            //单个升降机
            //指定设备载重报警
            int a1 = sbElevatorAddrecordService.selectElevatorCountTwo(paramMap);
            //指定设备人数报警
            int b1 = sbElevatorAddrecordService.selectElevatorCountThree(paramMap);
            //指定设备速度报警
            int c1 = sbElevatorAddrecordService.selectElevatorCountFour(paramMap);
            //指定设备倾斜报警
            int d1 = sbElevatorAddrecordService.selectElevatorCountFive(paramMap);
            //指定设备上限位报警
            int e1 = sbElevatorAddrecordService.selectElevatorCountSix(paramMap);
            //指定设备下限位报警
            int f1 = sbElevatorAddrecordService.selectElevatorCountSeven(paramMap);
            map.put("zzbj", a1);
            map.put("rsbj", b1);
            map.put("sdbj", c1);
            map.put("qxbj", d1);
            map.put("sxwbj", e1);
            map.put("xxwbj", f1);
            map.put("cdbj", e1);

            //查询项目下的一个升降机 根据pid和hxzid 查询名称
            SbElevatorBinding sbElevatorBinding = sbElevatorBindingService.selectOneSbElevatorBinding(pid, hxzid);
            if(sbElevatorBinding != null){
                map.put("dname", sbElevatorBinding.getDname());
                Integer userId = sbElevatorBinding.getUserid();
                if(userId != null){
                    HjProjectWorkers hjProjectWorkers = projectWorkersService.getById(userId);
                    map.put("rl", hjProjectWorkers.getFaceUrl());
                    map.put("name", hjProjectWorkers.getEmpName());

                    HjAttendanceRecord hr = new HjAttendanceRecord();
                    hr.setEmployeeId(userId);
                    hr.setDirection("in");
                    hr.setPassedTime(startTime);
                    List<HjAttendanceRecord> hrList = hjAttendanceRecordService.selectHjAttendanceRecordList(hr);
                    if (hrList.size() > 0) {
                        map.put("sbTime", hrList.get(0).getPassedTime());
                    } else {
                        map.put("sbTime", "");
                    }

                    List<HjAttendanceRecord> harList = hjAttendanceRecordService.selectAttendanceRecordListThree(userId);
                    map.put("recordList", harList);
                }else{
                    map.put("rl", null);
                    map.put("name", null);
                }
            }

            SbElevatorAddrecord sbElevatorAddrecord = new SbElevatorAddrecord();
            sbElevatorAddrecord.setHxzid(hxzid);
            sbElevatorAddrecord.setRuntime(startTime);
            List<SbElevatorAddrecord> eList = sbElevatorAddrecordService.selectSbElevatorAddrecordListTwo(sbElevatorAddrecord);
            if (eList.size() > 0) {
                map.put("record", eList.get(0));
            } else {
                map.put("record", "");
            }
            map.put("hxzid", hxzid);


        } else {
            return AjaxResult.error("当前无设备");
        }
        logger.info("升降机界面接口执行结束");
        return AjaxResult.success(map);
    }

    /**
     * 历史记录
     * @param time
     * @param hxzid
     * @param status 0表示不合格
     * @return
     */
    @RequestMapping("/historyRecord")
    @ResponseBody
    public AjaxResult historyRecord(String time, String endTime, String hxzid, String status) {
        startPage();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isBlank(time)) {
            time = dateFormat.format(new Date());
        }
        paramMap.put("time", time);
        paramMap.put("hxzid", hxzid);
        paramMap.put("endTime", endTime);
        if (StringUtils.isNotBlank(status)) {
            paramMap.put("status", status);
        }
        List<SbElevatorAddrecord> sList = sbElevatorAddrecordService.selectSbElevatorAddrecordListThree(paramMap);

        return AjaxResult.success(getDataTable(sList));
    }

    @RequestMapping("/historyRecordExcel")
    @ResponseBody
    public List<SbElevatorAddrecord> historyRecordExcel(String time, String endTime, String hxzid, String status) {
        startPage();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isBlank(time)) {
            time = dateFormat.format(new Date());
        }
        paramMap.put("time", time);
        paramMap.put("hxzid", hxzid);
        if (StringUtils.isNotBlank(status)) {
            paramMap.put("status", status);
        }

        return sbElevatorAddrecordService.selectSbElevatorAddrecordListThree(paramMap);
    }

    /**
     * 切换设备
     * @param seb
     * @return
     */
    @RequestMapping("/switchDevice")
    @ResponseBody
    public AjaxResult switchDevice(SbElevatorBinding seb) {

        return AjaxResult.success(sbElevatorBindingService.selectHxzId(seb.getPid()));
    }

}
