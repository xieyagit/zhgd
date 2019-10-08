package com.hujiang.project.zhgd.sbElevatorAddrecord.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.ProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.CraneAddRecord;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import com.hujiang.project.zhgd.sbCraneBinding.service.ISbCraneBindingService;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.ElevatorAddRecord;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbElevatorAddrecord.service.ISbElevatorAddrecordService;
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import com.hujiang.project.zhgd.sbElevatorBinding.service.ISbElevatorBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 升降机
 */
@RestController
@RequestMapping(value = "/provider/appElevatorAddRecord",method = RequestMethod.POST)
public class AppElevatorAddRecordApi {
    @Autowired
    private ISbElevatorAddrecordService elevatorAddrecordService;
    @Autowired
    private ISbElevatorBindingService elevatorBindingService;
    @Autowired
    private IHjProjectWorkersService iHjProjectService;

    /**
     * 升降机切换设备
     * @param projectId
     * @return
     */
    @PostMapping(value = "/getElevatorHxzId")
    public JSONObject getElevatorHxzId(@RequestParam(value = "projectId")int projectId){
        JSONObject jsonObject = new JSONObject();
        List<SbElevatorBinding> elevatorBindingList = elevatorBindingService.selectHxzId(projectId);
        JSONArray array = new JSONArray();
        if(elevatorBindingList != null && elevatorBindingList.size()>0){
            for (SbElevatorBinding elevatorBinding:elevatorBindingList) {
                JSONObject temp = new JSONObject();
                temp.put("projectId", elevatorBinding.getPid());
                temp.put("deviceId", elevatorBinding.getHxzid());
                temp.put("deviceName",elevatorBinding.getDname());
                array.add(temp);
            }
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",array);
        }else {
            jsonObject.put("msg","暂无设备");
            jsonObject.put("code",-1);
            jsonObject.put("data",null);
        }
        return jsonObject;
    }

    /**
     * 升降机界面
     * @return
     */
    @PostMapping(value = "/getElevatorAddRecord")
    public JSONObject getElevatorAddRecord(@RequestParam(value = "deviceId")String deviceId,
                                           @RequestParam(value = "projectId")Integer projectId){
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        SbElevatorAddrecord elevatorAddrecord = elevatorAddrecordService.selectSbElevatorAddRecord(deviceId,date);
        ElevatorAddRecord elevator = elevatorAddrecordService.selectElevatorAddRecordName(deviceId,date,projectId);
        JSONObject object =new JSONObject();
        try {
            if(elevatorAddrecord!=null ){
                jsonObject.put("msg","查询成功");
                jsonObject.put("code",0);
                if (elevator != null){
                    object.put("userName",elevator.getEmp_name());
                    object.put("time",elevator.getRuntime());
                    object.put("photo",elevator.getFace_url());
                }
                //object.put("id",craneAddrecord.getId());
                //object.put("device_no",craneAddrecord.getDeviceNo());
                //object.put("runtime",craneAddrecord.getRuntime());

                object.put("load",elevatorAddrecord.getLaod()); //载重
                object.put("height",elevatorAddrecord.getHeight()); //高度
                int weightAlarm = elevatorAddrecord.getWeightAlarm();//载重报警
                int speedAlarm = elevatorAddrecord.getSpeedAlarm();//速度报警
                int obliguityXAlarm = elevatorAddrecord.getObliguityXAlarm();//倾角X报警
                int obliguityYAlarm = elevatorAddrecord.getObliguityYAlarm();//倾角Y报警
                if(weightAlarm==1 || speedAlarm == 1 || obliguityXAlarm == 1 || obliguityYAlarm == 1){
                    object.put("weightAlarmStatus",false);//防坠异常
                }else {
                    object.put("weightAlarmStatus",true);//防坠正常
                }
                int upWarning = elevatorAddrecord.getIsUpWarning();
                int downWarning = elevatorAddrecord.getIsDownWarning();
                if(upWarning==1 || downWarning==1){
                    object.put("upAndDown",false);//上下限位异常
                }else{
                    object.put("upAndDown",true);//上下限位正常
                }

                //时间转毫秒
                long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(elevatorAddrecord.getRuntime()).getTime();
                //系统毫秒
                long l = System.currentTimeMillis();
                //监测时间加一个小时大于当前时间
                if((time+3600000)>l){
                    object.put("runningStatus",true);//升降机正常
                }else{
                    object.put("runningStatus",false);//升降机异常
                }
                jsonObject.put("data",object);
            }else{
                jsonObject.put("msg","暂无数据");//升降机异常
                jsonObject.put("code",0);
                jsonObject.put("data",object);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /** 智慧工地1.0看板升降机*/
    @PostMapping(value = "/crane")
    public JSONObject crane(Integer pid) throws ParseException {
        JSONArray jsonArray = new JSONArray();
        List<SbElevatorAddrecord> result = elevatorAddrecordService.crane(pid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String runtime = simpleDateFormat.format(new Date());
        List<SbElevatorAddrecord> results = elevatorAddrecordService.cranes(pid,runtime);
        for (SbElevatorAddrecord addrecord :results) {
            JSONObject jsonObject = new JSONObject();
            HjProjectWorkers projectWorkers = iHjProjectService.seleimgs(addrecord.getUserid());
            if (projectWorkers != null) {
                jsonObject.put("img", projectWorkers.getFaceUrl());
                jsonObject.put("name", projectWorkers.getEmpName());
                jsonObject.put("count", result.size());//升降机总台数

                /**计算是否异常，超过一个小时属于异常*/
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                Date parses = null;
                parses = df.parse(result.get(0).getMaxRuntime());
                date = df.parse(result.get(0).getMinRuntime());

                System.out.println(addrecord.getSpeedAlarm());
                int i = addrecord.getSpeedAlarm();                //防坠
                if (i <1) {
                    jsonObject.put("falling", "正常");     //防坠
                } else {
                    jsonObject.put("falling", "异常");     //防坠
                }
                jsonObject.put("operator_name", addrecord.getOperatorName());  //驾驶员姓名
                jsonObject.put("time", addrecord.getRuntime());                //上班时间
                jsonObject.put("laod", addrecord.getLaod());                   //载重
                jsonObject.put("height", addrecord.getHeight());               //高度
                if (addrecord.getIsUpWarning() ==0  && addrecord.getIsDownWarning() == 0) {
                    jsonObject.put("isDown", "正常");                                      //上下限位检测状态
                } else {
                    jsonObject.put("isDown", "异常");                                      //上下限位检测状态
                }
                Timestamp timestamp = new Timestamp(parses.getTime());
                Timestamp timestamp2 = new Timestamp(date.getTime());
                Date d1 = df.parse(String.valueOf(timestamp));
                Date d2 = df.parse(String.valueOf(timestamp2));
                long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
                Long a = diff / (1000 * 60 * 60 * 24);
                Long b = (diff - a * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                Long c = (diff - a * (1000 * 60 * 60 * 24) - b * (1000 * 60 * 60)) / (1000 * 60);
                Integer days = Integer.parseInt(String.valueOf(a));
                Integer hours = Integer.parseInt(String.valueOf(b));
                int hourss = days;
                jsonObject.put("days", hourss);//升降机服务时长

                Date date1 = new Date();
                Timestamp timestamps = new Timestamp(date1.getTime());
                Timestamp timestamps1 = new Timestamp(parses.getTime());
                Date c1 = df.parse(String.valueOf(timestamps));
                Date c2 = df.parse(String.valueOf(timestamps1));
                long diff2 = c1.getTime() - c2.getTime();//这样得到的差值是微秒级别
                Long d = diff2 / (1000 * 60 * 60 * 24);
                Long e = (diff2 - d * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                Integer day = Integer.parseInt(String.valueOf(d));
                Integer hour = Integer.parseInt(String.valueOf(e));
                int h = 90 - (day / 24);
                jsonObject.put("residue", h);//剩余检修时长
                String sb = "正常";
                if (day >1 && hour < 60) {
                    sb = "异常";
                }
                jsonObject.put("sb", sb);//设备状态
                jsonArray.add(jsonObject);
            }
            if (results.size() == 0) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("img", "");
                jsonObject1.put("name", "");
                jsonObject1.put("count", "");
                jsonObject1.put("falling", "");
                jsonObject1.put("operator_name", "");  //驾驶员姓名
                jsonObject1.put("time", "");                //上班时间
                jsonObject1.put("laod", "");                   //载重
                jsonObject1.put("height", "");
                jsonObject1.put("isDown", ""); //高度
                jsonObject1.put("days", "");//升降机服务时长
                jsonObject1.put("residue", "");//剩余检修时长
                jsonObject1.put("sb", "");//设备状态
                jsonArray.add(jsonObject1);
            }
        }
        JSONObject object = new JSONObject();
        object.put("data", jsonArray);
        return object;
    }

}
