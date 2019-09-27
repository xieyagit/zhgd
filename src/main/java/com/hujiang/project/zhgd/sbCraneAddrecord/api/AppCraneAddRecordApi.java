package com.hujiang.project.zhgd.sbCraneAddrecord.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Vehicle;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.CraneAddRecord;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.project.zhgd.sbCraneAddrecord.service.ISbCraneAddrecordService;
import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import com.hujiang.project.zhgd.sbCraneBinding.service.ISbCraneBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 塔吊
 */
@RestController
@RequestMapping(value = "/provider/appCraneAddRecord",method = RequestMethod.POST)
public class AppCraneAddRecordApi extends BaseController {
    @Autowired
    private ISbCraneAddrecordService craneAddrecordService;
    @Autowired
    private ISbCraneBindingService craneBindingService;
    @Autowired
    private IHjProjectWorkersService iHjProjectWorkersService;

    /**
     * 塔吊切换设备
     * @param project
     * @return
     */
    @PostMapping(value = "/getCraneHxzId")
    public JSONObject getCraneHxzId(@RequestParam(value = "projectId")int project){
        JSONObject jsonObject = new JSONObject();
        List<SbCraneBinding> craneBindings = craneBindingService.selectByHxzId(project);
        JSONArray array = new JSONArray();
        if(craneBindings != null && craneBindings.size()>0){
            for (SbCraneBinding craneBinding:craneBindings) {
                JSONObject temp = new JSONObject();
                temp.put("projectId", craneBinding.getPid());
                temp.put("deviceId", craneBinding.getHxzid());
                temp.put("deviceName",craneBinding.getDname());
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
     * 塔吊界面
     * @return
     */
    @PostMapping(value = "/getCraneAddRecord")
    public JSONObject getCraneAddRecord(@RequestParam(value = "deviceId")String deviceId,
                                        @RequestParam(value = "projectId")Integer projectId){
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date());
        SbCraneAddrecord craneAddrecord = craneAddrecordService.selectSbCraneAddRecord(deviceId,date);
        CraneAddRecord record = craneAddrecordService.selectCraneAddRecordName(deviceId,date,projectId);
        JSONObject object =new JSONObject();
        try {

            if(craneAddrecord!=null ){
                jsonObject.put("msg","查询成功");
                jsonObject.put("code",0);
                if (record != null){
                    object.put("userName",record.getEmp_name());
                    object.put("time",record.getRuntime());
                    object.put("photo",record.getFace_url());
                }else {
                    object.put("userName",null);
                    object.put("time",null);
                    object.put("photo",null);
                }
                //object.put("id",craneAddrecord.getId());
                //object.put("device_no",craneAddrecord.getDeviceNo());
                //object.put("runtime",craneAddrecord.getRuntime());

                object.put("load",craneAddrecord.getLoad());
                object.put("ratedWeight",craneAddrecord.getRatedWeight());
                object.put("moment",craneAddrecord.getMoment());
                object.put("range",craneAddrecord.getRange());
                object.put("height",craneAddrecord.getHeight());
                if(craneAddrecord.getMagnification() != null){
                    object.put("magnification",craneAddrecord.getMagnification());
                }else {
                    object.put("magnification",0);
                }
                object.put("wind_speed",craneAddrecord.getWindSpeed());
                object.put("slewing_speed",craneAddrecord.getSlewingSpeed());

                //时间转毫秒
                long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(craneAddrecord.getRuntime()).getTime();
                //系统毫秒
                long l = System.currentTimeMillis();
                //监测时间加一个小时大于当前时间
                if((time+3600000)>l){
                    object.put("runningStatus",true);//塔吊正常
                }else{
                    object.put("runningStatus",false);//塔吊异常
                }
                jsonObject.put("data",object);
            }else{
                jsonObject.put("msg","查询失败");
                jsonObject.put("code",0);
                jsonObject.put("data",object);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        return jsonObject;
    }

    @PostMapping(value = "/getCraneAddRecordHistory")
    public JSONObject getCraneAddRecordHistory(@RequestParam(value = "deviceId")String deviceId,
                                               @RequestParam(value = "dateTime",required = false)String dateTime){
        startPage();
        JSONObject jsonObject = new JSONObject();
        JSONArray array = new JSONArray();
        List<SbCraneAddrecord> craneAddrecords = craneAddrecordService.selectSbCraneAddRecordHistory(deviceId,dateTime);
        if(craneAddrecords!=null && craneAddrecords.size()>0){
            for (SbCraneAddrecord craneAddrecord:craneAddrecords){
                JSONObject temp = new JSONObject();
                temp.put("id",craneAddrecord.getId());
                temp.put("device_no",craneAddrecord.getDeviceNo());
                temp.put("runtime",craneAddrecord.getRuntime());
                temp.put("load",craneAddrecord.getLoad());
                temp.put("moment",craneAddrecord.getMoment());
                temp.put("range",craneAddrecord.getRange());
                temp.put("height",craneAddrecord.getHeight());
                if(craneAddrecord.getMagnification() != null){
                    temp.put("magnification",craneAddrecord.getMagnification());
                }else {
                    temp.put("magnification",0);
                }

                temp.put("wind_speed",craneAddrecord.getWindSpeed());
                temp.put("slewing_speed",craneAddrecord.getSlewingSpeed());
                array.add(temp);
            }
            jsonObject.put("msg","塔吊记录查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",array);
        }else{
            jsonObject.put("msg","塔吊记录查询失败");
            jsonObject.put("code",-1);
            jsonObject.put("data",null);
        }
        return jsonObject;
    }

    /**
     * 智慧工地1.0看板 塔吊
     * */
    @PostMapping(value = "/kanban")
    public JSONObject kanban(@RequestParam("pid") Integer pid){
        JSONArray jsonArray = new JSONArray();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<SbCraneAddrecord> t = craneAddrecordService.list(pid);
        for (SbCraneAddrecord sbCraneAddrecord : t) {
            HjProjectWorkers projectWorkers = iHjProjectWorkersService.seleimg(sbCraneAddrecord.getUserid());
            SbCraneAddrecord addrecord = craneAddrecordService.kanban(sbCraneAddrecord.getHxzid());
            try {
                JSONObject object1 = new JSONObject();
                object1.put("runtime", addrecord.getRuntime());
                object1.put("load", addrecord.getLoad());
                object1.put("moment", addrecord.getMoment());
                object1.put("slewingSpeed", addrecord.getSlewingSpeed());
                object1.put("range", addrecord.getRange());
                object1.put("height", addrecord.getHeight());
                object1.put("isUpWarning", addrecord.getIsUpWarning());
                object1.put("magnification", addrecord.getMagnification());
                object1.put("ratedWeight", addrecord.getRatedWeight());
                object1.put("dname", addrecord.getDname());
                object1.put("windSpeed", addrecord.getWindSpeed());
                object1.put("obliguity", addrecord.getObliguity());//倾角
                object1.put("size", t.size());              //塔吊数量
                object1.put("img", projectWorkers.getFaceUrl());
                object1.put("name", projectWorkers.getEmpName());
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                Date parse = df.parse(addrecord.getRuntime());
                Timestamp timestamp1 = new Timestamp(parse.getTime());
                Date d1 = df.parse(String.valueOf(timestamp));
                Date d2 = df.parse(String.valueOf(timestamp1));
                long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
                Long a = diff / (1000 * 60 * 60 * 24);
                Long b = (diff - a * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                Integer days = Integer.parseInt(String.valueOf(a));
                Integer hours = Integer.parseInt(String.valueOf(b));
                int hour = days * 24 + hours;
                String sb = "正常";
                if (days >= 1 && hour > 58) {
                    sb = "异常";
                }
                object1.put("sb", sb);
                //缺计算最早时间
                SbCraneAddrecord min = craneAddrecordService.lists(sbCraneAddrecord.getHxzid());
                Date parse1 = df.parse(min.getRuntime());
                Timestamp timestamp2 = new Timestamp(parse1.getTime());
                Date dd = df.parse(String.valueOf(timestamp));
                Date de = df.parse(String.valueOf(timestamp2));
                long dif = dd.getTime() - de.getTime();//这样得到的差值是微秒级别
                Long a1 = dif / (1000 * 60 * 60 * 24);
                Integer day = Integer.parseInt(String.valueOf(a1));
                int h = 90 -day;
                object1.put("residue",h);//剩余检修时长
                jsonArray.add(object1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if(t.size()==0){
            JSONObject object1 = new JSONObject();
            object1.put("runtime", "");
            object1.put("load", "");
            object1.put("moment", "");
            object1.put("slewingSpeed", "");
            object1.put("range", "");
            object1.put("height", "");
            object1.put("isUpWarning", "");
            object1.put("magnification", "");
            object1.put("ratedWeight", "");
            object1.put("dname", "");
            object1.put("angle", "");
            object1.put("windSpeed","");
            object1.put("sb", "");
            object1.put("size", "");              //塔吊数量
            object1.put("img", "");
            object1.put("name","");
            object1.put("residue","");//剩余检修时长
            jsonArray.add(object1);
        }
        JSONObject object = new JSONObject();
        object.put("sjjlist",jsonArray);
        return object;
    }

}
