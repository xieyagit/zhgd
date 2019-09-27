package com.hujiang.project.zhgd.deye;

import com.hujiang.common.utils.StringUtils;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.project.zhgd.sbCraneAddrecord.service.ISbCraneAddrecordService;
import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import com.hujiang.project.zhgd.sbCraneBinding.service.ISbCraneBindingService;
import com.hujiang.project.zhgd.sbCraneWarning.service.ISbCraneWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/provider/craneApi",method = RequestMethod.POST)
public class PcApi  extends BaseController {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private ISbCraneBindingService sbCraneBindingService;
    @Autowired
    private ISbCraneAddrecordService sbCraneAddrecordService;
    @Autowired
    private ISbCraneWarningService sbCraneWarningService;
    @Autowired
    private IHjProjectWorkersService projectWorkersService;
    @Autowired
    private IHjAttendanceRecordService hjAttendanceRecordService;

    /**
     * 塔吊界面接口
     * @param pid
     * @param hxzid
     * @return
     */
    @RequestMapping("/selectIndex")
    @ResponseBody
    public AjaxResult selectIndex(Integer pid , String hxzid){

        Map<String,Object> paramMap=new HashMap<String,Object>();
        Map<String,Object> map=new HashMap<String,Object>();
        paramMap.put("pid",pid);
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -60);// 60分钟之前的时间
        String startTime=dateFormat.format(beforeTime.getTime());
        paramMap.put("time",startTime);
//        System.out.println("=============="+startTime);
        List<SbCraneAddrecord> scaList= sbCraneAddrecordService.selectCraneCount(paramMap);
        //在线塔吊
        int zxCrane=scaList.size();
//        System.out.println(zxCrane);
        //查询所有塔吊设备
        SbCraneBinding scb=new SbCraneBinding();
        scb.setPid(pid);
        List<SbCraneBinding> scbList=sbCraneBindingService.selectSbCraneBindingList(scb);
        if(scbList.size()>0) {
            //塔吊设备总数量
            int number = scbList.size();
            //离线塔吊设备
            int lxCrane = number - zxCrane;
            map.put("zxCrane", zxCrane);
            map.put("lxCrane", lxCrane);
            //限位报警次数
            int a = sbCraneAddrecordService.selectCraneCountTwo(paramMap);
            //载重报警次数
            int b = sbCraneAddrecordService.selectCraneCountThree(paramMap);
            //碰撞报警
            int c = sbCraneWarningService.selectCraneCountOne(paramMap);
            //传感器报警
            int d = sbCraneWarningService.selectCraneCountTwo(paramMap);
            //禁入区报警
            int e = sbCraneWarningService.selectCraneCountThree(paramMap);
            //报警统计
            map.put("bjtj", a + b + c + d + e);
            if (StringUtils.isBlank(hxzid)) {
                hxzid = scbList.get(0).getHxzid();
            }
            paramMap.remove("pid");
            paramMap.put("hxzid", hxzid);

            //限位报警次数
            int a1 = sbCraneAddrecordService.selectCraneCountTwo(paramMap);
            //载重报警次数
            int b1 = sbCraneAddrecordService.selectCraneCountThree(paramMap);
            //碰撞报警
            int c1 = sbCraneWarningService.selectCraneCountOne(paramMap);
            //传感器报警
            int d1 = sbCraneWarningService.selectCraneCountTwo(paramMap);
            //禁入区报警
            int e1 = sbCraneWarningService.selectCraneCountThree(paramMap);
            map.put("xwbj", a1);
            map.put("zzbj", b1);
            map.put("pzbj", c1);
            map.put("cgqbj", d1);
            map.put("jrqbj", e1);

            scb.setHxzid(hxzid);
            List<SbCraneBinding> scb2List = sbCraneBindingService.selectSbCraneBindingList(scb);
            SbCraneBinding scb2 = scb2List.get(0);
            map.put("dname", scb2.getDname());

            HjProjectWorkers hjProjectWorkers = new HjProjectWorkers();
            hjProjectWorkers.setId(scb2.getUserid());
            List<HjProjectWorkers> wList = projectWorkersService.selectHjProjectWorkersList(hjProjectWorkers);
            map.put("rl", wList.get(0).getFaceUrl());
            map.put("name", wList.get(0).getEmpName());
            HjAttendanceRecord hr = new HjAttendanceRecord();
            hr.setEmployeeId(scb2.getUserid());
            hr.setDirection("in");
            hr.setPassedTime(startTime);
            List<HjAttendanceRecord> hrList = hjAttendanceRecordService.selectHjAttendanceRecordList(hr);
            if (hrList.size() > 0) {
                map.put("sbTime", hrList.get(0).getPassedTime());
            } else {
                map.put("sbTime", "");
            }
            SbCraneAddrecord sbCraneAddrecord = new SbCraneAddrecord();
            sbCraneAddrecord.setHxzid(hxzid);
            sbCraneAddrecord.setRuntime(startTime);
            List<SbCraneAddrecord> sList = sbCraneAddrecordService.selectSbCraneAddrecordListTwo(sbCraneAddrecord);
            if (sList.size() > 0) {
                map.put("record", sList.get(0));
            } else {
                map.put("record", "");
            }
            map.put("hxzid", hxzid);
            //本月操作人员考勤情况
            List<HjAttendanceRecord> harList=hjAttendanceRecordService.selectAttendanceRecordListThree(scb2.getUserid());
            map.put("recordList",harList);
        }else{
            return  AjaxResult.error("当前无设备");
        }
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
    public AjaxResult historyRecord(String time, String hxzid, String status){
        startPage();
        Map<String,Object> paramMap=new HashMap<String,Object>();
        if(StringUtils.isBlank(time)){
            time=dateFormat.format(new Date());
        }
        paramMap.put("time",time);
        paramMap.put("hxzid",hxzid);
        if(StringUtils.isNotBlank(status)){
            if("0".equals(status)){
                status=">1";
            }else{
                status="<=1";
            }
            paramMap.put("status",status);
        }
        List<SbCraneAddrecord> sList=sbCraneAddrecordService.selectSbCraneAddrecordListThree(paramMap);

        return AjaxResult.success(getDataTable(sList));
    }

    @RequestMapping("/historyRecordExcel")
    @ResponseBody
    public  List<SbCraneAddrecord> historyRecordExcel(String time, String hxzid){

        Map<String,Object> paramMap=new HashMap<String,Object>();
        if(StringUtils.isBlank(time)){
            time=dateFormat.format(new Date());
        }
        paramMap.put("time",time);
        paramMap.put("hxzid",hxzid);

        return sbCraneAddrecordService.selectSbCraneAddrecordListThree(paramMap);
    }

    /**
     * 切换设备
     * @param scb
     * @return
     */
    @RequestMapping("/switchDevice")
    @ResponseBody
    public AjaxResult switchDevice(@RequestBody SbCraneBinding scb){

     return AjaxResult.success(sbCraneBindingService.selectSbCraneBindingList(scb));
    }
}
