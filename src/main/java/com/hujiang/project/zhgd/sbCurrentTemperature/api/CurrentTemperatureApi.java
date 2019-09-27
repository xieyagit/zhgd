package com.hujiang.project.zhgd.sbCurrentTemperature.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbCurrentTemperature.service.ISbCurrentTemperatureService;
import com.hujiang.project.zhgd.sbDoorLock.domain.SbDoorLock;
import com.hujiang.project.zhgd.sbDoorLock.service.ISbDoorLockService;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import com.hujiang.project.zhgd.sbProjectElectricityBox.service.ISbProjectElectricityBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-24 10:54
 **/
@RestController
@RequestMapping(value = "/provider/currentTemperatureApi",method = RequestMethod.POST)
public class CurrentTemperatureApi extends BaseController {
    Logger logger = Logger.getLogger(CurrentTemperatureApi.class.getName());

    @Autowired
    private ISbCurrentTemperatureService sbCurrentTemperatureService;
    @Autowired
    private ISbProjectElectricityBoxService boxService;
    @Autowired
    private ISbDoorLockService sbDoorLockService;


    /**
     * 电箱数据分页
     * @param electricityBoxId
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping("/list")
    public JSONObject list(@RequestParam(value = "electricityBoxId")String electricityBoxId,
                           @RequestParam(value = "startTime",required = false)String startTime,
                           @RequestParam(value = "endTime",required = false)String endTime)
    {
        logger.info("com.hujiang.project.zhgd.sbCurrentTemperature.api.CurrentTemperatureApi.list分页开始");
        JSONObject result = new JSONObject();
        result.put("msg","查询成功");
        result.put("code",0);
        startPage();
        Map<String,Object> map = new HashMap<>();
        map.put("electricityBoxId",electricityBoxId);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<SbCurrentTemperature> list = sbCurrentTemperatureService.selectSbCurrentTemperatureByTime(map);
        result.put("data",getDataTable(list));
        logger.info("com.hujiang.project.zhgd.sbCurrentTemperature.api.CurrentTemperatureApi.list分页结束");
        return result;
    }


    /**
     * 导出Excel数据源
     * @param electricityBoxId
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping(value = "getExcel")
    public List<SbCurrentTemperature> getExcel(@RequestParam(value = "electricityBoxId")String electricityBoxId,
                                                  @RequestParam(value = "startTime",required = false)String startTime,
                                                  @RequestParam(value = "endTime",required = false)String endTime){
        Map<String,Object> map = new HashMap<>();
        map.put("electricityBoxId",electricityBoxId);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        return sbCurrentTemperatureService.selectSbCurrentTemperatureByTime(map);
    }

    /**
     * 智能电箱界面数据
     * @param electricityBoxId
     * @param projectId
     * @return
     */
    @PostMapping(value = "getEquipmentInformation")
    public JSONObject getEquipmentInformation(@RequestParam(value = "electricityBoxId")String electricityBoxId,
                                              @RequestParam(value = "projectId")Integer projectId)throws Exception{

        JSONObject result =new JSONObject();
        JSONObject newData =new JSONObject();
        //获取设备最新数据
        SbCurrentTemperature sbCurrentTemperature = sbCurrentTemperatureService.selectSbCurrentTemperatureToOne(electricityBoxId);
        if(sbCurrentTemperature!=null){

            //时间转毫秒
            long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sbCurrentTemperature.getTm()).getTime();
            long l = System.currentTimeMillis();//系统毫秒
            if((time+3600000)>l){         //监测时间加一个小时大于当前时间
                newData.put("data",sbCurrentTemperature);
                newData.put("runningStatus","正常");//设备正常
            }else{
                newData.put("data",new SbCurrentTemperature());
                newData.put("runningStatus","异常");//设备异常

            }

        }else{
            newData.put("data",new SbCurrentTemperature());
            newData.put("runningStatus","异常");//设备异常
        }
        //电箱门开关记录
        SbDoorLock sbDoorLock = sbDoorLockService.selectSbDoorLockToOne(electricityBoxId);
        if(sbDoorLock!=null){
            newData.put("doorLock",Integer.parseInt(sbDoorLock.getDoorType())==0?"关":"开");//门锁状态
        }else{
            newData.put("doorLock","异常");//门锁状态异常
        }
        result.put("new",newData);
        //检测项目电箱
        SbProjectElectricityBox box = new SbProjectElectricityBox();
        box.setProjectId(projectId);
        List<SbProjectElectricityBox> sbProjectElectricityBoxes = boxService.selectSbProjectElectricityBoxList(box);
        JSONObject object = new JSONObject();

        int off_line =0;//离线设备数
        int number =0;//在线设备数
        if(sbProjectElectricityBoxes.size()>0){
            for(SbProjectElectricityBox b:sbProjectElectricityBoxes){
                SbCurrentTemperature currentTemperature = sbCurrentTemperatureService.selectSbCurrentTemperatureToOne(b.getElectricityBoxId());
                if(sbCurrentTemperature!=null){
                    //时间转毫秒
                    long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sbCurrentTemperature.getTm()).getTime();
                    long millis= System.currentTimeMillis();//系统毫秒
                    if((time+3600000)<millis){         //监测时间加一个小时大于当前时间
                        off_line+=1;
                    }else{
                        number+=1;
                    }

                }else{
                    off_line+=1;
                }
            }
        }
        object.put("off_line",off_line);//设备离线数
        object.put("number",number);//在设备数
        //报警数
        Map<String, Object> map = sbCurrentTemperatureService.selectSbCurrentTemperatureCount(electricityBoxId);
        object.put("count",map);

        result.put("countData",object);

        return result;
    }

    /**看板1.0，电箱数据*/
    @PostMapping(value = "/kanban")
    public JSONObject kanban(Integer projectId) throws ParseException {

        JSONObject object = new JSONObject();
        /**计算出电箱服务时间*/
        SbCurrentTemperature temperature = sbCurrentTemperatureService.kanban(projectId);    //计算开始使用时间
        if (temperature != null) {
            JSONObject jsonObject = new JSONObject();
            List<SbCurrentTemperature> currentTemperature = sbCurrentTemperatureService.selectkanban(projectId); //计算出最晚时间，当时的温度
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            Date parses = null;
            for (SbCurrentTemperature sbCurrentTemperature : currentTemperature) {
                date = df.parse(sbCurrentTemperature.getTm());
                parses = df.parse(sbCurrentTemperature.getTm());
            }
            Timestamp timestamp = new Timestamp(date.getTime());
            Date parse = df.parse(temperature.getTm());
            Timestamp timestamp1 = new Timestamp(parse.getTime());
            Date d1 = df.parse(String.valueOf(timestamp));
            Date d2 = df.parse(String.valueOf(timestamp1));
            long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
            Long a = diff / (1000 * 60 * 60 * 24);
            Long b = (diff - a * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            Long c = (diff - a * (1000 * 60 * 60 * 24) - b * (1000 * 60 * 60)) / (1000 * 60);
            Integer days = Integer.parseInt(String.valueOf(a));
            Integer hours = Integer.parseInt(String.valueOf(b));
            //Integer minutes = Integer.parseInt(String.valueOf(c));
            int hourss = days * 24 + hours;
            if (hourss < 1) {
                jsonObject.put("days", "");//电箱服务时间
            } else {
                jsonObject.put("days", hourss);//电箱服务时间
            }
            /**判断电箱是否异常*/
            Date date1 = new Date();
            Timestamp timestamps = new Timestamp(date.getTime());
            Timestamp timestamps1 = new Timestamp(parses.getTime());
            Date c1 = df.parse(String.valueOf(timestamps));
            Date c2 = df.parse(String.valueOf(timestamps1));
            long diffs = c1.getTime() - c2.getTime();//这样得到的差值是微秒级别
            Long d = diffs / (1000 * 60 * 60 * 24);
            Long e = (diffs - d * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            Integer day = Integer.parseInt(String.valueOf(d));
            Integer hour = Integer.parseInt(String.valueOf(e));
            String sb = "正常";
            if (day > 0 || hour > 0) {
                sb = "异常";
            }
            jsonObject.put("sb", sb);
            jsonObject.put("count", currentTemperature);

            object.put("count", currentTemperature.size());
            object.put("envirwarm", currentTemperature.get(0).getEnvirwarm());
            object.put("kgjl", jsonObject);
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sb", "");
            JSONObject ject = new JSONObject();
            ject.put("id","");
            ject.put("electricityBoxId","");
            ject.put("current","");
            ject.put("envirwarm","");
            ject.put("awarm","");
            ject.put("bwarm","");
            ject.put("cwarm","");
            ject.put("nwarm","");
            ject.put("tm","");
            jsonObject.put("count", ject);
            jsonObject.put("days", "");
            object.put("count", "");
            object.put("envirwarm", "");
            object.put("kgjl", jsonObject);
        }
        return object;
    }
}
