package com.hujiang.project.zhgd.sbDustEmission.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain.SafetyRectification;
import com.hujiang.project.zhgd.sbDustEmission.domain.Pm25_Pm10;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.service.ISbDustEmissionService;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.service.ISbProjectDustEmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;


@RestController
@RequestMapping(value = "/provider/appDustEmission",method = RequestMethod.POST)
public class AppDustEmissionApi extends BaseController {
    private Logger logger = Logger.getLogger(AppDustEmissionApi.class.getName());
    @Autowired
    private ISbDustEmissionService dustEmissionService;
    @Autowired
    private ISbProjectDustEmissionService projectDustEmissionService;


    /**
     * 查询扬尘记录
     * @return
     */
    @PostMapping("/getDustEmission")
    public JSONObject getDustEmission(@RequestParam(value = "sn")String sn,
                                      @RequestParam(value = "dateTime",required = false)String dateTime,
                                      PageDomain pageDomain){
        startPage();
        JSONObject result = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("sn",sn);
        map.put("dateTime",dateTime);
        //获取分页数据
        List<SbDustEmission> sbDustEmissions = dustEmissionService.selectSbDustEmissionByTimes(map);
        TableDataInfo dataTable = getDataTable(sbDustEmissions);
        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            if (sbDustEmissions != null && sbDustEmissions.size() > 0) {
                result.put("msg", "扬尘记录查询成功");
                result.put("code", 0);
                result.put("data", sbDustEmissions);
            } else {
                result.put("msg", "扬尘记录查询失败");
                result.put("code", -1);
                result.put("data", Collections.emptyList());
            }
        }else {
            result.put("msg", "查询成功");
            result.put("code", 0);
            result.put("data", Collections.emptyList());
        }
        return result;
    }

    /**
     * 获取TSP界面数据
     * @param sn
     * @return
     */
    @PostMapping("getPM25AndPN10")
    public JSONObject getPM25AndPN10(@RequestParam(value = "sn") String sn,@RequestParam(value = "projectId")Integer projectId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","查询成功");
        jsonObject.put("code",0);
        JSONObject result = getEight(sn);
        //五天平均值
        result.put("getFive", get5(sn));

        jsonObject.put("data",result);
        return jsonObject;
    }

    /**
     * 当前最新数据
     * @param sn
     * @return
     */
    private JSONObject getEight(String sn){
        JSONObject result = new JSONObject();
        SimpleDateFormat sp1=new SimpleDateFormat("yyyy-MM-dd");

        //今日平均值
        String format = sp1.format(new Date());
        Pm25_Pm10 pm25_pm10 = dustEmissionService.getPm25_Pm10BySnAndTime(sn,format);
        SbDustEmission day = dustEmissionService.selectToday(sn,format);
        if(pm25_pm10!=null && day!=null){
            result.put("PM25",pm25_pm10.getPm25()==null?0:pm25_pm10.getPm25()); //pm25
            result.put("PM10",pm25_pm10.getPm10()==null?0:pm25_pm10.getPm10()); //pm10
            result.put("PM100",pm25_pm10.getPm100()==null?0:pm25_pm10.getPm100()); //pm100
            result.put("humidity",day.getHumidity()==null?0:day.getHumidity()); //湿度
            result.put("wind_speed",day.getWindSpeed()==null?0:day.getWindSpeed()); //温度
            result.put("temperature",day.getTemperature()==null?0:day.getTemperature());//风向
            result.put("noise",day.getNoise()==null?0:day.getNoise());
            result.put("time",format);  //当天时间
        }else{
            result.put("PM25",0);
            result.put("PM10",0);
            result.put("PM100",0);
            result.put("humidity",0);
            result.put("wind_speed",0);
            result.put("temperature",0);
            result.put("noise",0);
            result.put("time",format);
        }
        return result;
    }

    /**
     * 前5天pm2.5平均数
     */
    private JSONArray get5(String sn){
        //空气质量
        String excellent = "优";
        String well = "良";
        String moderate = "差";
        String abnormal = "0";
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2  = new SimpleDateFormat("MM-dd");
        String maxDateStr = sdf.format(new Date());
        String minDateStr = "";
        Calendar calc =Calendar.getInstance();
        JSONArray array = new JSONArray();
        try {
            for(int i=4;i>=0;i--) {//前5天
                JSONObject map = new JSONObject();
                calc.setTime(sdf.parse(maxDateStr));
                calc.add(5, -i);
                Date minDate = calc.getTime();
                minDateStr = sdf.format(minDate);
                String minDateStr2 = sdf2.format(minDate);
                //当天平均值
                Pm25_Pm10 pm25_pm10 = dustEmissionService.getPm25_Pm10BySnAndTime(sn,minDateStr);
                if(pm25_pm10!=null){
                    if (pm25_pm10.getPm25().doubleValue() >= 0 && pm25_pm10.getPm25().doubleValue() <= 50) {//优秀
                        map.put("day",excellent);
                        map.put("time",minDateStr2);
                    }
                    if (pm25_pm10.getPm25().doubleValue()>50 && pm25_pm10.getPm25().doubleValue() <= 100) {//良好
                        map.put("day", well);
                        map.put("time", minDateStr2);
                    }
//                    if (pm25_pm10.getPm25().doubleValue()>100 && pm25_pm10.getPm25().doubleValue() <= 150) {//轻度
//                        map.put("day", mild);
//                        map.put("time", minDateStr);
//                    }
                    if (pm25_pm10.getPm25().doubleValue()>100 && pm25_pm10.getPm25().doubleValue() <= 200) {//中度
                        map.put("day", moderate);
                        map.put("time", minDateStr2);
                    }
//                    if (pm25_pm10.getPm25().doubleValue()>200 && pm25_pm10.getPm25().doubleValue() <= 300) {//重度
//                        map.put("day", severe);
//                        map.put("time", minDateStr);
//                    }
//                    if (pm25_pm10.getPm25().doubleValue()>300) {//严重
//                        map.put("5day", seriousness);
//                        map.put("time", minDateStr);
//                    }
                }else {
                    //异常
                    map.put("day", abnormal);
                    map.put("time", minDateStr2);
                }
                array.add(map);
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
        return array;
    }
}
