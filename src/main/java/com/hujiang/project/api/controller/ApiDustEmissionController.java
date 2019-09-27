package com.hujiang.project.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.service.ISbDustEmissionService;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.service.ISbProjectDustEmissionService;
import com.hujiang.project.zhgd.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 扬尘接口
 */
@RestController
@RequestMapping(value = "/provider/dustEmission/get",method = RequestMethod.GET)
public class ApiDustEmissionController {

    @Autowired
    private ISbDustEmissionService dustEmissionService;
    @Autowired
    private ISbProjectDustEmissionService projectDustEmissionService;

    private Logger log = Logger.getLogger(ApiDustEmissionController.class.getName());



    /**
     * 根据项目id获取扬尘设备列表
     * @param pid
     * @return
     */
    @RequestMapping("/getDustEmissionList")
    public JSONObject getDustEmissionList(String pid){
        long l = 0;
        try{
            l = Long.parseLong(pid);
        }catch (Exception e){
            return Util.ErrorReturnedValue("没有此类型的项目id(pid为整数)");
        }
        JSONObject object = new JSONObject();
        SbProjectDustEmission projectDustEmission = new SbProjectDustEmission();
        projectDustEmission.setProjectId(l);
        //根据项目id获取项目所有扬尘设备SN
        List<SbProjectDustEmission> projectDustEmissions = projectDustEmissionService.selectSbProjectDustEmissionList(projectDustEmission);
        if(projectDustEmissions.size()>0){
            JSONArray jsonArray = new JSONArray();
            for(SbProjectDustEmission p:projectDustEmissions){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("comments",p.getComments());
                jsonObject.put("id",p.getId());
                jsonArray.add(jsonObject);
            }
            object.put("dustEmissionList",jsonArray);
            return object;
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("comments","无扬尘设备");
            jsonObject.put("id",0);
            return jsonObject;
        }
    }

    /**
     * 根据编号获取设备记录
     * @param sid
     * @return
     */
    @RequestMapping("/DustEmissionDatas")
    public JSONObject getDustEmissionData(String sid)throws Exception{
        long snid = 0;
        try{
            snid = Long.parseLong(sid);
        }catch (Exception e){
            return Util.ErrorReturnedValue("没有此类型的项目id(pid为整数)");
        }
        JSONObject object = new JSONObject();
        SbProjectDustEmission projectDustEmission = new SbProjectDustEmission();
        projectDustEmission.setId(snid);
        List<SbProjectDustEmission> projectDustEmissions = projectDustEmissionService.selectProjectDustEmissionListData(projectDustEmission);

        if(projectDustEmissions.size()>0){
            //保存新的扬尘记录
            JSONArray newarray = new JSONArray();
//            //请求接口传输的json数据
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.fluentPut("SN", projectDustEmissions.get(0).getSn());
//            jsonObject.fluentPut("CMD", "GetData");
//            //请求url 获取新的扬尘监测数据
//            String s = Util.httpPostWithJSON(Constants.DUSTEMISSION, jsonObject);
//            //获取的原始数据
//            JSONObject  originalData = JSONObject.parseObject(s);
//            JSONObject digest = JSONObject.parseObject(originalData.getString("Data"));
//            digest.put("comments",projectDustEmissions.get(0).getComments());


            JSONObject digest = new JSONObject();
            for(SbProjectDustEmission p:projectDustEmissions){ //获取所有扬尘设备
                System.out.println(projectDustEmissions.get(0).getSn()+"*******");
                //根据sn获取数据
                List<SbDustEmission> dustEmissions = dustEmissionService.selectDustEmissionListBySn(projectDustEmissions.get(0).getSn());
                //有数据
                if(dustEmissions!=null && dustEmissions.size()>0){
                    SbDustEmission dustEmission = dustEmissions.get(0);
                    digest.put("comments",projectDustEmissions.get(0).getComments());
                    digest.put("Tsp",dustEmission.getTsp());
                    digest.put("Temperature",dustEmission.getTemperature());
                    digest.put("PM25",dustEmission.getPm25());
                    digest.put("Noise",dustEmission.getNoise());
                    digest.put("Humidity",dustEmission.getHumidity());
                    digest.put("PM10",dustEmission.getPm10());
                    digest.put("Date",dustEmission.getDate());
                    digest.put("Winddirection",dustEmission.getWinddirection());
                    digest.put("WindSpeed",dustEmission.getWindSpeed());
                    break;
                }
            }
            //保存新的扬尘记录
//            DustEmission dustEmission1 = JSONObject.parseObject(digest.toJSONString(), DustEmission.class);
//            dustEmission1.setSn(projectDustEmissions.get(0).getSn());
//            dustEmissionService.insertDustEmission(dustEmission1);
            newarray.add(digest);//保存到返回的json
            object.put("newData",newarray);
            //保存查询的扬尘记录
            JSONArray jsonArray = new JSONArray();
            for(SbProjectDustEmission p:projectDustEmissions){
                if(p.getDustEmissionList().size()>0){//设备数据记录
                    for(SbDustEmission d:p.getDustEmissionList()){
                        JSONObject dustEmission = new JSONObject();
                        dustEmission.put("id",d.getId());
                        dustEmission.put("date",d.getDate());
                        dustEmission.put("pm25",d.getPm25());
                        dustEmission.put("pm10",d.getPm10());
                        dustEmission.put("tsp",d.getTsp());
                        dustEmission.put("noise",d.getNoise());
                        dustEmission.put("temperature",d.getTemperature());
                        dustEmission.put("humidity",d.getHumidity());
                        dustEmission.put("windSpeed",d.getWindSpeed());
                        dustEmission.put("winddirection",d.getWinddirection());
                        log.info(d.getDate());
                        jsonArray.add(dustEmission);
                    }
                }
            }
            for(int i = 0;i<jsonArray.size();i++){
                JSONObject o = (JSONObject)jsonArray.get(i);
                log.info(o.getString("date"));
            }

            object.put("dustEmissionListDatas",jsonArray);
            object.put("PM",this.getSIX(snid).get("PM"));
            return object;
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("comments","无扬尘设备");
            jsonObject.put("id",0);
            return jsonObject;
        }
    }

    /**
     *绿色施工界面中心数据
     * @param pid
     * @return
     * @throws Exception
     */
    @RequestMapping("HomeDustEmissionCentre")
    public JSONObject getHomeDustEmission(String pid)throws Exception{
        long pid1 = 0;
        try{
            pid1 = Long.parseLong(pid);
        }catch (Exception e){
            return Util.ErrorReturnedValue("没有此类型的项目id(pid为整数)");
        }
        JSONObject object = new JSONObject();
        SbProjectDustEmission projectDustEmission = new SbProjectDustEmission();
        projectDustEmission.setProjectId(pid1);
        List<SbProjectDustEmission> projectDustEmissions = projectDustEmissionService.selectProjectDustEmissionListData(projectDustEmission);
        if(projectDustEmissions.size()>0){
            //保存新的扬尘记录
            JSONArray newarray = new JSONArray();
            //请求接口传输的json数据
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.fluentPut("SN", projectDustEmissions.get(0).getSn());
//            jsonObject.fluentPut("CMD", "GetData");
//            //请求url 获取新的扬尘监测数据
//            String s = Util.httpPostWithJSON(Constants.DUSTEMISSION, jsonObject);
//            //获取的原始数据
//            JSONObject  originalData = JSONObject.parseObject(s);
//            JSONObject digest = JSONObject.parseObject(originalData.getString("Data"));

            JSONObject digest = new JSONObject();
            for(SbProjectDustEmission p:projectDustEmissions){ //获取所有扬尘设备
                //根据sn获取数据
                List<SbDustEmission> dustEmissions = dustEmissionService.selectDustEmissionListBySn(projectDustEmissions.get(0).getSn());
                //有数据
                if(dustEmissions!=null && dustEmissions.size()>0){

                    SbDustEmission dustEmission = dustEmissions.get(0);
                    System.out.println(dustEmission);
                    digest.put("comments",projectDustEmissions.get(0).getComments());
                    digest.put("Tsp",dustEmission.getTsp());
                    digest.put("Temperature",dustEmission.getTemperature());
                    digest.put("PM25",dustEmission.getPm25());
                    digest.put("Noise",dustEmission.getNoise());
                    digest.put("Humidity",dustEmission.getHumidity());
                    digest.put("PM10",dustEmission.getPm10());
                    digest.put("Date",dustEmission.getDate());
                    digest.put("Winddirection",dustEmission.getWinddirection());
                    digest.put("WindSpeed",dustEmission.getWindSpeed());
                    System.out.println("123123123121231213213");
                    break;
                }
            }


            //保存新的扬尘记录
//            DustEmission dustEmission1 = JSONObject.parseObject(digest.toJSONString(), DustEmission.class);
//            dustEmission1.setSn(projectDustEmissions.get(0).getSn());
//            dustEmissionService.insertDustEmission(dustEmission1);
            newarray.add(digest);//保存到返回的json
            //界面中心数据
            object.put("newData",newarray);
            //近六小时PM数据
            object.put("PM",this.getSIX(projectDustEmissions.get(0).getId()).get("PM"));
            //近12小时噪音
            object.put("ZY",this.getTWELVE(projectDustEmissions.get(0).getId()).get("ZY"));
            //近6小时气温
            object.put("QW",this.getSIX(projectDustEmissions.get(0).getId()).get("QW"));
            //PM当天平均值
            SimpleDateFormat sp1=new SimpleDateFormat("yyyy-MM-dd");
            Map<String, Float> dtavg = dustEmissionService.getPMAVG(projectDustEmissions.get(0).getProjectId(), sp1.format(new Date()));
            System.out.println(dtavg);
            //PM当月平均值
            SimpleDateFormat sp2=new SimpleDateFormat("yyyy-MM");
            Map<String, Float> dyavg = dustEmissionService.getPMAVG(projectDustEmissions.get(0).getProjectId(), sp2.format(new Date()));
            System.out.println(dyavg);
            //pm25当月和当天平均值
            JSONObject pm25avg = new JSONObject();
            JSONObject pm10avg = new JSONObject();
            DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
            if(dtavg!=null){
                pm25avg.put("day",decimalFormat.format(Float.parseFloat(dtavg.get("pm25")+"")));
                pm10avg.put("day",decimalFormat.format(Float.parseFloat(dtavg.get("pm10")+"")));

            }else {
                pm25avg.put("day",0);
                pm10avg.put("day",0);

            }

            object.put("pm25avg",pm25avg);
            //pm10当月和当天平均值

            if(dyavg!=null){
                pm25avg.put("month",decimalFormat.format(Float.parseFloat(dyavg.get("pm25")+"")));
                pm10avg.put("month",decimalFormat.format(Float.parseFloat(dyavg.get("pm10")+"")));
            }else {
                pm25avg.put("month",0);
                pm10avg.put("month",0);
            }

            object.put("pm10avg",pm10avg);
            return object;
        }else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("comments","无扬尘设备");
            jsonObject.put("id",0);
            return jsonObject;
        }
    }

    /**
     * 根据设备id获取近6小时数据
     * @param snid
     * @return
     */
    public JSONObject getSIX(Long snid){
        //近6小时污染数据统计
        SimpleDateFormat sp1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sp=new SimpleDateFormat("HH");
        //当前小时
        int h = Integer.parseInt(sp.format(new Date()));
        JSONArray jsonArrayPM = new JSONArray();
        JSONArray jsonArrayQW = new JSONArray();
        for (int i = 1;i<=6;i++){
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
            //获取近6小时的扬尘监测数据
            System.out.println(snid);
            System.out.println(dq1);
            SbDustEmission d1 = dustEmissionService.getDustEmissionByDateAndID(snid, dq1);
            if(d1!=null){
                JSONObject pm = new JSONObject();
                pm.put("PM25",d1.getPm25());
                pm.put("PM10",d1.getPm10());
                pm.put("time",s1);
                jsonArrayPM.add(pm);
                JSONObject qw = new JSONObject();//气温
                qw.put("time",s1);
                qw.put("temperature",d1.getTemperature());
                jsonArrayQW.add(qw);
            }else{
                JSONObject pm = new JSONObject();
                pm.put("PM25",0);
                pm.put("PM10",0);
                pm.put("time",s1);
                jsonArrayPM.add(pm);
                JSONObject qw = new JSONObject();//气温
                qw.put("time",s1);
                qw.put("temperature",0);
                jsonArrayQW.add(qw);
            }
        }
        JSONObject j = new JSONObject();
        j.put("PM",jsonArrayPM);//PM数据
        j.put("QW",jsonArrayQW);//QW数据
        return j;
    }




    /**
     * 根据设备id获取近12小时数据
     * @param snid
     * @return
     */
    public JSONObject getTWELVE(Long snid){
        //近6小时污染数据统计
        SimpleDateFormat sp1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sp=new SimpleDateFormat("HH");
        //当前小时
        int h = Integer.parseInt(sp.format(new Date()));
        JSONArray jsonArrayZY = new JSONArray();
        for (int i = 1;i<=12;i++){
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
            //获取近12小时的扬尘监测数据
            SbDustEmission d1 = dustEmissionService.getDustEmissionByDateAndID(snid, dq1);

            if(d1!=null){
                JSONObject zy = new JSONObject();//Noise噪音
                zy.put("time",s1);
                zy.put("Noise",d1.getNoise());
                jsonArrayZY.add(zy);
            }else{
                JSONObject zy = new JSONObject();//Noise噪音
                zy.put("Noise",0);
                zy.put("time",s1);
                jsonArrayZY.add(zy);
            }
        }
        JSONObject j = new JSONObject();
        j.put("ZY",jsonArrayZY);//Noise噪音
        return j;
    }















}
