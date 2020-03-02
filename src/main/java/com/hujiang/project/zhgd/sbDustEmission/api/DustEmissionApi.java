package com.hujiang.project.zhgd.sbDustEmission.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.DateUtils;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.dustEmissionThresholdValue.domain.DustEmissionThresholdValue;
import com.hujiang.project.zhgd.dustEmissionThresholdValue.service.IDustEmissionThresholdValueService;
import com.hujiang.project.zhgd.hjArea.domain.HjArea;
import com.hujiang.project.zhgd.hjArea.service.IHjAreaService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectUser.domain.HjProjectUser;
import com.hujiang.project.zhgd.hjProjectUser.service.IHjProjectUserService;
import com.hujiang.project.zhgd.hjRolePrivileges.domain.HjRolePrivileges;
import com.hujiang.project.zhgd.hjRolePrivileges.service.IHjRolePrivilegesService;
import com.hujiang.project.zhgd.hjSystemUser.domain.HjSystemUser;
import com.hujiang.project.zhgd.hjSystemUser.service.IHjSystemUserService;
import com.hujiang.project.zhgd.hjUserRole.domain.HjUserRole;
import com.hujiang.project.zhgd.hjUserRole.service.IHjUserRoleService;
import com.hujiang.project.zhgd.moduleToPush.domain.ModuleToPush;
import com.hujiang.project.zhgd.moduleToPush.service.IModuleToPushService;
import com.hujiang.project.zhgd.sbDustEmission.domain.Pm25_Pm10;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.service.ISbDustEmissionService;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.service.ISbProjectDustEmissionService;
import com.hujiang.project.zhgd.utils.MyThread4;
import com.hujiang.project.zhgd.utils.Util;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.Thread.currentThread;


@RestController
@RequestMapping(value = "/provider/DustEmission",method = RequestMethod.POST)
public class DustEmissionApi extends BaseController {
    private Logger logger = Logger.getLogger(DustEmissionApi.class.getName());
    @Autowired
    private ISbDustEmissionService dustEmissionService;
    @Autowired
    private ISbProjectDustEmissionService projectDustEmissionService;
    @Autowired
    private IHjAreaService hjAreaService;
    @Autowired
    private IHjProjectService projectService;
    @Autowired
    IDustEmissionThresholdValueService thresholdValueService;
    @Autowired
    ISbProjectDustEmissionService iSbProjectDustEmissionService;
    @Autowired
    private IHjProjectUserService userService;
    @Autowired
    private IHjUserRoleService userRoleService;
    @Autowired
    private IHjSystemUserService systemUserService;
    @Autowired
    private IHjRolePrivilegesService rolePrivilegesService;
    @Autowired
    private IModuleToPushService moduleToPushService;
    //tsp模块权限
    private static final  int PRIVILEGESID=26;

    /**
     * Excel数据源
     * @param sn
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping("getExcel")
    public List<SbDustEmission> getExcel(@RequestParam(value = "sn")String sn,
                                         @RequestParam(value = "startTime",required = false)String startTime,
                                         @RequestParam(value = "endTime",required = false)String endTime){
        Map<String,Object> map = new HashMap<>();
        map.put("sn",sn);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        SbProjectDustEmission projectDustEmission = new SbProjectDustEmission();
        projectDustEmission.setSn(sn);
        List<SbProjectDustEmission> sbProjectDustEmissions = projectDustEmissionService.selectSbProjectDustEmissionList(projectDustEmission);
        if(sbProjectDustEmissions!=null && sbProjectDustEmissions.size()>0){
            DustEmissionThresholdValue thresholdValue = new DustEmissionThresholdValue();
            thresholdValue.setProjectId(sbProjectDustEmissions.get(0).getProjectId().intValue());
            //根据项目id查询是否设置阈值
            List<DustEmissionThresholdValue> dustEmissionThresholdValues = thresholdValueService.selectDustEmissionThresholdValueList(thresholdValue);
            if(dustEmissionThresholdValues==null || dustEmissionThresholdValues.size()==0){
                //没有添加则设置默认值
                int i = thresholdValueService.insertDustEmissionThresholdValue(thresholdValue);
                if(i>0){
                    DustEmissionThresholdValue thresholdValue1 = thresholdValueService.selectDustEmissionThresholdValueList(thresholdValue).get(0);
                    map.put("pm25",thresholdValue1.getPm25());
                    map.put("pm10",thresholdValue1.getPm10());
                    map.put("tsp",thresholdValue1.getPm10());
                    map.put("noise",thresholdValue1.getNoise());
                    map.put("temperature",thresholdValue1.getTemperature());
                    map.put("windSpeed",thresholdValue1.getWindSpeed());
                }
            }else{
                DustEmissionThresholdValue thresholdValue1 = dustEmissionThresholdValues.get(0);
                map.put("pm25",thresholdValue1.getPm25());
                map.put("pm10",thresholdValue1.getPm10());
                map.put("tsp",thresholdValue1.getPm10());
                map.put("noise",thresholdValue1.getNoise());
                map.put("temperature",thresholdValue1.getTemperature());
                map.put("windSpeed",thresholdValue1.getWindSpeed());
            }
        }

        logger.info(" com.hujiang.project.zhgd.sbDustEmission.api.DustEmissionApi.getExcel:Excel数据源");
        return dustEmissionService.selectSbDustEmissionByTime(map);
    }

    /**
     * 查询扬尘记录
     * @param sn sn编号
     * @param tag 0 不合格   1合格
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @PostMapping("getDustEmission")
    public JSONObject getDustEmission(@RequestParam(value = "sn")String sn,
                                      @RequestParam(value = "tag",required = false)Integer tag,
                                      @RequestParam(value = "startTime",required = false)String startTime,
                                      @RequestParam(value = "endTime",required = false)String endTime,
                                      PageDomain pageDomain
                                      ){
        logger.info("com.hujiang.project.zhgd.sbDustEmission.api.DustEmissionApi.getDustEmission开始");

        JSONObject result = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("sn",sn);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("tag",tag);
        DustEmissionThresholdValue thresholdValue1 = null;
        SbProjectDustEmission projectDustEmission = new SbProjectDustEmission();
        projectDustEmission.setSn(sn);
        List<SbProjectDustEmission> sbProjectDustEmissions = projectDustEmissionService.selectSbProjectDustEmissionList(projectDustEmission);
        if(sbProjectDustEmissions!=null && sbProjectDustEmissions.size()>0){
            DustEmissionThresholdValue thresholdValue = new DustEmissionThresholdValue();
            thresholdValue.setProjectId(sbProjectDustEmissions.get(0).getProjectId().intValue());
            //根据项目id查询是否设置阈值
            List<DustEmissionThresholdValue> dustEmissionThresholdValues = thresholdValueService.selectDustEmissionThresholdValueList(thresholdValue);
            if(dustEmissionThresholdValues==null || dustEmissionThresholdValues.size()==0){
                //没有添加则设置默认值
                int i = thresholdValueService.insertDustEmissionThresholdValue(thresholdValue);
                if(i>0){
                    thresholdValue1 = thresholdValueService.selectDustEmissionThresholdValueList(thresholdValue).get(0);
                    map.put("pm25",thresholdValue1.getPm25());
                    map.put("pm10",thresholdValue1.getPm10());
                    map.put("tsp",thresholdValue1.getPm10());
                    map.put("noise",thresholdValue1.getNoise());
                    map.put("temperature",thresholdValue1.getTemperature());
                    map.put("windSpeed",thresholdValue1.getWindSpeed());
                }
            }else{
                thresholdValue1 = dustEmissionThresholdValues.get(0);
                map.put("pm25",thresholdValue1.getPm25());
                map.put("pm10",thresholdValue1.getPm10());
                map.put("tsp",thresholdValue1.getPm10());
                map.put("noise",thresholdValue1.getNoise());
                map.put("temperature",thresholdValue1.getTemperature());
                map.put("windSpeed",thresholdValue1.getWindSpeed());
            }
        }
        //获取分页数据
        startPage();
        List<SbDustEmission> sbDustEmissions = dustEmissionService.selectSbDustEmissionByTime(map);
        TableDataInfo dataTable = getDataTable(sbDustEmissions);
        //处理分页数据
        List<SbDustEmission> rows = (List<SbDustEmission>)dataTable.getRows();
        JSONArray array = new JSONArray();
        if(rows!=null && rows.size()>0){
            for(SbDustEmission dustEmission:rows){
                //pm2.5 大于100超标 //pm10 大于150超标  //tsp 大于120超标  //噪音 大于65超标  温度大于30度  风速大于13km/h
                JSONObject o = (JSONObject)JSONObject.toJSON(dustEmission);
                if((dustEmission.getPm25()!=null && dustEmission.getPm25().doubleValue()>=thresholdValue1.getPm25()) || (dustEmission.getPm10()!=null && dustEmission.getPm10().doubleValue()>=thresholdValue1.getPm10()) ||
                        (dustEmission.getTsp()!=null && dustEmission.getTsp().doubleValue()>=thresholdValue1.getTsp() )||(dustEmission.getNoise()!=null && dustEmission.getNoise().doubleValue()>=thresholdValue1.getNoise())||
                        (dustEmission.getTemperature()!=null && dustEmission.getTemperature().doubleValue()>=thresholdValue1.getTemperature() )||(dustEmission.getWindSpeed()!=null && dustEmission.getWindSpeed().doubleValue()>=thresholdValue1.getWindSpeed())){
                    o.put("ifoverproof","NO"); //超标 不合格
                }else{
                    o.put("ifoverproof","YES"); //合格
                }
                if (tag != null) {
                    if (tag == 0) {
                        if (o.get("ifoverproof").equals("NO")) {
                            array.add(o);
                        }
                    } else if (tag == 1) {
                        if (o.get("ifoverproof").equals("YES")) {
                            array.add(o);
                        }
                    } else {
                        array.add(o);
                    }
                }else {
                    array.add(o);
                }
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("total",dataTable.getTotal());//总记录数
            jsonObject.put("rows",array);//数据
            result.put("msg","扬尘记录查询成功");
            result.put("code",0);
            result.put("data",jsonObject);

        }else{
            result.put("msg","扬尘记录查询失败");
            result.put("code",-1);
            result.put("data",null);
        }
        logger.info("com.hujiang.project.zhgd.sbDustEmission.api.DustEmissionApi.getDustEmission结束");
        return result;
    }

    /**
     * 获取TSP界面数据
     * @param sn
     * @return
     */
    @PostMapping("getPM25AndPN10")
    public JSONObject getPM25AndPN10(@RequestParam(value = "sn") String sn,@RequestParam(value = "projectId")Integer projectId) throws  Exception{
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //加上时间
        logger.info("com.hujiang.project.zhgd.sbDustEmission.api.DustEmissionApi.getPM25AndPN10获取TSP界面数据开始");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("msg","查询成功");
        jsonObject.put("code",0);
        //============================================================
        //获取近八小时平均数据和当前最新数据
        JSONObject result = getEight(sn);
        //最新扬尘监测数据
        SbDustEmission dustEmission1 = dustEmissionService.selectSbDustEmissionToOne(sn);
        try{
            if(dustEmission1!=null){
                Date date=sDateFormat.parse(dustEmission1.getDate());
                if( (date.getTime()+3600000)<System.currentTimeMillis()){
                    dustEmission1.setAirPressure(BigDecimal.valueOf(0));
                    dustEmission1.setHumidity(BigDecimal.valueOf(0));
                    dustEmission1.setPm10(BigDecimal.valueOf(0));
                    dustEmission1.setPm25(BigDecimal.valueOf(0));
                    dustEmission1.setNoise(BigDecimal.valueOf(0));
                    dustEmission1.setTemperature(BigDecimal.valueOf(0));
                    dustEmission1.setTsp(BigDecimal.valueOf(0));
                    dustEmission1.setWinddirection(Long.parseLong("0"));
                    dustEmission1.setWindSpeed(BigDecimal.valueOf(0));
                    dustEmission1.setWindPower(BigDecimal.valueOf(0));
                    result.put("new",dustEmission1);
                }else{
                    result.put("new",dustEmission1);
                }
            }else{
                dustEmission1 = new SbDustEmission();
                dustEmission1.setAirPressure(BigDecimal.valueOf(0));
                dustEmission1.setHumidity(BigDecimal.valueOf(0));
                dustEmission1.setPm10(BigDecimal.valueOf(0));
                dustEmission1.setPm25(BigDecimal.valueOf(0));
                dustEmission1.setNoise(BigDecimal.valueOf(0));
                dustEmission1.setTemperature(BigDecimal.valueOf(0));
                dustEmission1.setTsp(BigDecimal.valueOf(0));
                dustEmission1.setWinddirection(Long.parseLong("0"));
                dustEmission1.setWindSpeed(BigDecimal.valueOf(0));
                dustEmission1.setWindPower(BigDecimal.valueOf(0));
                System.out.println(dustEmission1);
                result.put("new",dustEmission1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        //============================================================
        //根据项目id查询设备数
        SbProjectDustEmission projectDustEmission = new SbProjectDustEmission();
        projectDustEmission.setProjectId(projectId.longValue());
        //获取项目设备数
        List<SbProjectDustEmission> sbProjectDustEmissions = projectDustEmissionService.selectSbProjectDustEmissionList(projectDustEmission);
        int sum_yc=0;//异常数
        JSONObject sum =new JSONObject();
        sum.put("sum",sbProjectDustEmissions.size());//设备总数
        //统计不正常设备数
        for(SbProjectDustEmission sp:sbProjectDustEmissions){
            SbDustEmission dustEmission = dustEmissionService.selectSbDustEmissionToOne(sp.getSn());
            if(dustEmission!=null){

                //必须捕获异常
                try {
                    Date date=sDateFormat.parse(dustEmission.getDate());
                    //获取数据监测毫秒数
                    //加上一个小时的毫秒数，是否大于当前系统毫秒数，大于则设备正常,小于则异常
                    if((date.getTime()+3600000)<System.currentTimeMillis()){
                        sum_yc+=1;
                    }

                } catch(ParseException px) {
                    px.printStackTrace();
                }
            }else{
                sum_yc+=1;
            }
        }
        sum.put("sum_yc",sum_yc);//设备异常数
        result.put("count",sum);
        //============================================================
        //获取项目信息
        HjProject hjProject = projectService.selectHjProjectById(projectId);
        //获取项目地址
        String[] split = hjProject.getProjectRegion().split(",");
        HjArea area = hjAreaService.selectHjAreaById(Long.parseLong(split[2]));
        HjArea shi = hjAreaService.selectHjAreaById(Long.parseLong(split[1]));
        //天气
        String weather;
        String weatherShi;
        if(!"1".equals(area.getIsid())) {
            weather = Util.httpGet("https://www.tianqiapi.com/api?version=v1&appsecret=PDsIqZt7&appid=74243526&city=" + area.getTitle().replaceAll("新区", "").replaceAll("区", "").replaceAll("市", ""));
            weatherShi = Util.httpGet("https://www.tianqiapi.com/api?version=v6&appsecret=PDsIqZt7&appid=74243526&city=" + area.getTitle().replaceAll("新区", "").replaceAll("区", "").replaceAll("市", ""));
        }else{
            weather = Util.httpGet("https://www.tianqiapi.com/api?version=v1&appsecret=PDsIqZt7&appid=74243526&cityid=" + area.getId());
            weatherShi = Util.httpGet("https://www.tianqiapi.com/api?version=v6&appsecret=PDsIqZt7&appid=74243526&cityid=" + area.getId());

        }
        jsonObject.put("weather", JSONObject.parse(StringEscapeUtils.unescapeJava(weather) ));//天气
        JSONObject parse = (JSONObject)JSONObject.parse(StringEscapeUtils.unescapeJava(weatherShi));
        jsonObject.put("air_pm25", parse.getFloat("air_pm25"));//天气
        //============================================================
        jsonObject.put("get30", get30(sn));//获取近30天pm2.5合格数和噪音合格数
        //============================================================

        jsonObject.put("data",result);
        logger.info("com.hujiang.project.zhgd.sbDustEmission.api.DustEmissionApi.getPM25AndPN10获取TSP界面数据结束");
        return jsonObject;
    }



    /**
     * 近八小时平均数据和当前最新数据
     * @param sn
     * @return
     */
    private JSONObject getEight(String sn){
        JSONObject result = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        //近8小时污染数据统计
        SimpleDateFormat sp1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sp=new SimpleDateFormat("HH");

        //今日平均值
        String format = sp1.format(new Date());
        Pm25_Pm10 pm25_pm10 = dustEmissionService.getPm25_Pm10BySnAndTime(sn, format);
        JSONObject object = new JSONObject();
        if(pm25_pm10==null){
            object.put("PM25",0);
            object.put("PM10",0);
            object.put("time",format);
        }else{
            object.put("PM25",pm25_pm10.getPm25()==null?0:pm25_pm10.getPm25());
            object.put("PM10",pm25_pm10.getPm10()==null?0:pm25_pm10.getPm10());
            object.put("time",format);
        }
        result.put("today",object);

        //当前小时
        int h = Integer.parseInt(sp.format(new Date()));
        for (int i = 1;i<=8;i++){
            String s1="";
            if(h>0){
                if(h<10){
                    s1="0"+h;
                    h--;
                }else{
                    s1=h+"";
                    h--;
                }
            }else{
                s1="00";
                h=23;
            }
            //当前日期小时
            String dq1 =sp1.format(new Date())+" "+s1;
            //获取近8小时的扬尘监测数据
            Pm25_Pm10 pm25_pm10BySnAndTime = dustEmissionService.getPm25_Pm10BySnAndTime(sn, dq1);
            JSONObject pm = new JSONObject();

            if(pm25_pm10BySnAndTime==null){
                pm.put("PM25",0);
                pm.put("PM10",0);
                pm.put("time",s1);
            }else{
                pm.put("PM25",pm25_pm10BySnAndTime.getPm25()==null?0:pm25_pm10BySnAndTime.getPm25());
                pm.put("PM10",pm25_pm10BySnAndTime.getPm10()==null?0:pm25_pm10BySnAndTime.getPm10());
                pm.put("time",s1);
            }
            jsonArray.add(pm);

        }

        result.put("eight",jsonArray);//近八小时平均数据
        return result;
    }

    /**
     * 获取近30天pm2.5合格数和噪音合格数
     * @param sn
     * @return
     */
    private JSONObject get30(String sn){
        JSONObject result = new JSONObject();
        int pm25_yx=0;//优秀
        int pm25_lh=0;//良好
        int pm25_qd=0;//轻度
        int pm25_zd=0;//中度
        int pm25_zd1=0;//重度
        int pm25_yz=0;//严重
        int pm25_yc=0;//异常

        int zy_hg=0;//合格
        int zy_bhg=0;//不合格
        int zy_yc=0;//异常


        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
        String maxDateStr = sdf.format(new Date());
        String minDateStr = "";
        Calendar calc =Calendar.getInstance();
        try {
            for(int i=0;i<30;i++){ //前30天
                calc.setTime(sdf.parse(maxDateStr));
                calc.add(5, -i);
                Date minDate = calc.getTime();
                minDateStr = sdf.format(minDate);
               //获取当前日期数据的平均值
                Pm25_Pm10 pm25_pm10BySnAndTime = dustEmissionService.getPm25_Pm10BySnAndTime(sn, minDateStr);
                JSONObject pm = new JSONObject();
                if(pm25_pm10BySnAndTime!=null){//对象不为空
                    if(pm25_pm10BySnAndTime.getPm25()!=null){//属性不为空
                        if(pm25_pm10BySnAndTime.getPm25().doubleValue()>=0 && pm25_pm10BySnAndTime.getPm25().doubleValue()<=50){//优秀
                            pm25_yx+=1;
                        }
                        if(pm25_pm10BySnAndTime.getPm25().doubleValue()>50 && pm25_pm10BySnAndTime.getPm25().doubleValue()<=100){//良好
                            pm25_lh+=1;
                        }
                        if(pm25_pm10BySnAndTime.getPm25().doubleValue()>100 && pm25_pm10BySnAndTime.getPm25().doubleValue()<=150){//轻度
                            pm25_qd+=1;
                        }
                        if(pm25_pm10BySnAndTime.getPm25().doubleValue()>150 && pm25_pm10BySnAndTime.getPm25().doubleValue()<=200){//中度
                            pm25_zd+=1;
                        }
                        if(pm25_pm10BySnAndTime.getPm25().doubleValue()>200 && pm25_pm10BySnAndTime.getPm25().doubleValue()<=300){//重度
                            pm25_zd1+=1;
                        }
                        if(pm25_pm10BySnAndTime.getPm25().doubleValue()>300){//严重
                            pm25_yz+=1;
                        }
                    }else{//属性为空
                        if(pm25_pm10BySnAndTime.getPm25().doubleValue()>=0 && pm25_pm10BySnAndTime.getPm25().doubleValue()<=50){//异常
                            pm25_yc+=1;//异常
                        }
                    }
                    if(pm25_pm10BySnAndTime.getNoise()!=null){
                        if(pm25_pm10BySnAndTime.getNoise().doubleValue()<=65){//合格
                            zy_hg+=1;
                        }else if(pm25_pm10BySnAndTime.getNoise().doubleValue()>65){//不合格
                            zy_bhg+=1;
                        }
                    }else{
                        zy_yc+=1;//异常
                    }
                }else{
                    pm25_yc+=1;//异常
                    zy_yc+=1;//异常
                }

            }


        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        result.put("pm25_yx",pm25_yx);
        result.put("pm25_lh",pm25_lh);
        result.put("pm25_qd",pm25_qd);
        result.put("pm25_zd",pm25_zd);
        result.put("pm25_zd1",pm25_zd1);
        result.put("pm25_yz",pm25_yz);
        result.put("pm25_yc",pm25_yc);
        result.put("zy_hg",zy_hg);
        result.put("zy_bhg",zy_bhg);
        result.put("zy_yc",zy_yc);
        return result;
    }

    /** 看板1.0TSP数据*/
    @PostMapping("/Tsp")
    public JSONObject raise(long projectId){

        JSONObject ject = new JSONObject();
        List<SbProjectDustEmission> dustEmission = iSbProjectDustEmissionService.selectSn(projectId);
        if (dustEmission.size()>0) {
            /** 扬尘*/
            JSONArray array = new JSONArray();
            JSONArray jsonArray = new JSONArray();
            List<SbDustEmission> emissions = dustEmissionService.selectTsp(dustEmission.get(0).getSn());
            List<SbDustEmission> result = new ArrayList<SbDustEmission>();
            Date currentDate = new Date();
            Date currentDate1 = new Date();
            SbDustEmission item = null;
            for (int i = 1; i <= 8; i++) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String finalCurrentDate = DateUtils.getDateTime(currentDate);
                List<SbDustEmission> list = emissions.stream().filter(a -> DateUtils.getDateTime(DateUtils.parseDate(a.getDate())).equals(finalCurrentDate)).collect(Collectors.toList());

                JSONObject jsonObject = new JSONObject();
                if (list.size() > 0) {
                    if (dustEmission.get(0).getSn().equals(list.get(0).getSn())) {
                        jsonObject.put(sdf.format(currentDate), list.get(0).getTsp());
                        array.add(jsonObject);
                    } else {
                        jsonObject.put(sdf.format(currentDate), "");
                        array.add(jsonObject);
                    }
                } else {
                    jsonObject.put(sdf.format(currentDate), "");
                    array.add(jsonObject);
                }
                ject.put("tsp", array);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                currentDate = calendar.getTime();
            }

            /** 温度*/
            List<SbDustEmission> emissions1 = dustEmissionService.selectTsp1(dustEmission.get(0).getSn());
            List<SbDustEmission> result1 = new ArrayList<SbDustEmission>();
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(currentDate1);
            calendar2.add(Calendar.HOUR_OF_DAY, -11);
            currentDate1 = calendar2.getTime();
            for (int i = 1; i <= 12; i++) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
                String finalCurrentDate = DateUtils.getDateTimes(currentDate1);
                List<SbDustEmission> list = emissions1.stream().filter(a -> DateUtils.getDateTimes(DateUtils.parseDate(a.getDate())).equals(finalCurrentDate)).collect(Collectors.toList());
                JSONObject object = new JSONObject();
                if (list.size() > 0) {
                    object.put(sdf.format(currentDate1), list.get(0).getTemperature());
                    jsonArray.add(object);
                } else {
                    object.put(sdf.format(currentDate1), "");
                    jsonArray.add(object);
                }
                ject.put("temperature", jsonArray);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate1);
                calendar.add(Calendar.HOUR_OF_DAY, +1);
                currentDate1 = calendar.getTime();
            }
            if (ject == null) {
                ject.put("tsp", "");
                ject.put("temperature", "");
            }
        }
        return ject;
    }

    @PostMapping("/getTSP")
    public JSONObject getDustUserList(@RequestParam("projectId")Integer projectId,
                                       @RequestParam(value = "filed",required = false)String filed){
        JSONObject jsonObject = new JSONObject();
        JSONArray userArray = new JSONArray();
        HjProjectUser projectUser = new HjProjectUser();
        projectUser.setProjectId(projectId);//p.getProjectId().intValue()
        List<HjProjectUser> projectUserList = userService.selectHjProjectUserList(projectUser);
        List<HjUserRole> userRoleList = userRoleService.selectHjUserRoleList(null);
        List<HjRolePrivileges> rolePrivilegesList = rolePrivilegesService.selectHjRolePrivilegesList(null);
        List<HjSystemUser> systemUserList = systemUserService.getCraneUserList(null,filed);
        ModuleToPush moduleToPush = new ModuleToPush();
        moduleToPush.setPrivilegesId(PRIVILEGESID);
        List<ModuleToPush> moduleToPushesList = moduleToPushService.selectModuleToPushList(moduleToPush);

        if (projectUserList == null || projectUserList.size() < 1) {
            jsonObject.put("msg","查询成功");
            jsonObject.put("code",0);
            jsonObject.put("data",userArray);
            return jsonObject;
        }
        for (HjProjectUser hjProjectUser : projectUserList) {
            HjUserRole userRole = new HjUserRole();
            userRole.setUserId(hjProjectUser.getUserId());
            List<HjUserRole> myUserRoleList = userRoleList.stream()
                    .filter(a->a.getUserId().equals(hjProjectUser.getUserId()))
                    .collect(Collectors.toList());
            if (userRoleList == null || userRoleList.size() < 1) {
                continue;
            }
            List<HjRolePrivileges> myRolePrivilegesList = rolePrivilegesList.stream()
                    .filter(
                            a-> a.getPrivilegesId().equals(PRIVILEGESID) &&
                                    myUserRoleList.stream().map(b->b.getRoleId()).collect(Collectors.toList()).contains(a.getRoleId())
                    )
                    .collect(Collectors.toList());
            if(myRolePrivilegesList == null || myRolePrivilegesList.size() < 1) {
                continue;
            }
            List<HjSystemUser> mySystemUserList = systemUserList.stream()
                    .filter(a->a.getId().equals(hjProjectUser.getUserId()))
                    .collect(Collectors.toList());
            if(mySystemUserList == null || mySystemUserList.size() < 1){
                continue;
            }
            for (HjSystemUser systemUser:mySystemUserList){
                JSONObject userMap = new JSONObject();
                userMap.put("id",systemUser.getId());
                userMap.put("userName",systemUser.getUserName());
                userMap.put("userPhone",systemUser.getUserPhone());
                userMap.put("userAccount",systemUser.getUserAccount());
                if(!moduleToPushesList.stream().filter(a->a.getUserId().equals(systemUser.getId())).findAny().isPresent()) {
                    userMap.put("onOff",0);
                }
                else {
                    ModuleToPush moduleToPushs = moduleToPushesList.stream().filter(a->a.getUserId().equals(systemUser.getId())).findAny().get();
                    userMap.put("onOff",moduleToPushs.getOnOff());
                }
                userArray.add(userMap);
            }
        }
        jsonObject.put("msg","查询成功");
        jsonObject.put("code",0);
        jsonObject.put("data",userArray);

        //projectUserList
        return jsonObject;
    }


}
