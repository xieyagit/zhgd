package com.hujiang.project.zhgd.sbProjectElectricityBox.task;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.ThreadUtils;
import com.hujiang.project.api.controller.ApiElectricityBoxController;
import com.hujiang.project.zhgd.sbCurrentTemperature.api.SendTemperatureToPERSONNEL;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbCurrentTemperature.service.ISbCurrentTemperatureService;
import com.hujiang.project.zhgd.sbDoorLock.domain.SbDoorLock;
import com.hujiang.project.zhgd.sbDoorLock.service.ISbDoorLockService;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import com.hujiang.project.zhgd.sbProjectElectricityBox.service.ISbProjectElectricityBoxService;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.Util;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 电箱定时任务
 */
//@Component("electricityBoxTask")
    @RestController
    @RequestMapping(value = "/provider/b")
public class ElectricityBoxTask {
    private final Logger logger = LoggerFactory.getLogger(ZCAPIClient.class);
    @Autowired
    private ISbProjectElectricityBoxService iProjectElectricityBoxService;
    @Autowired
    private ISbDoorLockService iDoorLockService;
    @Autowired
    private ISbCurrentTemperatureService iCurrentTemperatureService;
    @Autowired
    private ApiElectricityBoxController apiElectricityBoxController;
    @Autowired
    private SendTemperatureToPERSONNEL sendTemperatureToPERSONNEL;

    /**
     * 60秒执行一次电箱获取
     * 获取智能电箱的数据
     * @throws Exception
     */
//    @Scheduled(cron="0/60 * * * * ?")
    @PostMapping(value = "/c")
    public void add()throws Exception{
        SbProjectElectricityBox sbox = new SbProjectElectricityBox();
        sbox.setType(0);
        //查询所有项目所有电箱
        List<SbProjectElectricityBox> projectElectricityBoxes = iProjectElectricityBoxService.selectSbProjectElectricityBoxList(sbox);
        for(SbProjectElectricityBox peb:projectElectricityBoxes){
            //电箱编号
            String electricityBoxId = peb.getElectricityBoxId();
            //截取后八位
            String substring = electricityBoxId.substring(electricityBoxId.length() - 7, electricityBoxId.length());
            SimpleDateFormat sp1 = new SimpleDateFormat("yyyy-MM-dd");
            //获取今天日期
            String jr = sp1.format(new Date());
            //明日日期
            Date d1 = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 );
            String mr = sp1.format(d1);//获取考勤日期
            //请求url 获取电箱温度数据
            String s = Util.httpPostWithJSON(Constants.TempEelcHistory+"?id="+substring+"&startTime="+jr+" 00:00:00&endTime="+mr, new JSONObject());
            System.out.println("获取的电箱温度数据："+s);
            //获取的原始数据
            JSONObject  originalData = JSONObject.parseObject(s);
            JSONArray data = (JSONArray)originalData.get("data");
            if(data.size()!=0){
                JSONObject o = (JSONObject)data.get(data.size() - 1);
                o.remove("id");
                o.put("electricityBoxId",electricityBoxId);
                SbCurrentTemperature currentTemperature = o.toJavaObject(SbCurrentTemperature.class);
                List<SbCurrentTemperature> currentTemperatures = iCurrentTemperatureService.selectSbCurrentTemperatureList(null);
                Long id = new Long(4);
                String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                if(currentTemperatures.size()>0){
                    if(!currentTemperatures.get(0).getTm().equals(currentTemperature.getTm())) {//数据更新时间不等于查询出来的最新时间
                        //添加新的记录
                        iCurrentTemperatureService.insertSbCurrentTemperature(currentTemperature);
                        System.out.println("添加温度记录");
                    }
                }else {
                        //添加新的记录
                        iCurrentTemperatureService.insertSbCurrentTemperature(currentTemperature);
                       // sendTemperatureToPERSONNEL.rcajDate(currentTemperature);

                }
            }
            //请求url 获取电箱门开关数据
//            String s1 = Util.httpPostWithJSON(Constants.LockDoorHistoryInfo+"?id_card="+electricityBoxId+"&startTime=2018-03-21 00:00:00&endTime=2019-03-24", new JSONObject());
            String s1 = Util.httpPostWithJSON(Constants.LockDoorHistoryInfo+"?id_card="+electricityBoxId+"&startTime="+jr+" 00:00:00&endTime="+mr, new JSONObject());
            System.out.println("获取的电箱门开关数据："+s1);
            //获取的原始数据
            JSONObject  originalData1 = JSONObject.parseObject(s1);
            JSONArray data1 = (JSONArray)originalData1.get("data");
            if(data1.size()!=0){
                JSONObject o = (JSONObject)data1.get(data1.size() - 1);
                o.remove("id");
                o.put("electricityBoxId",electricityBoxId);
                SbDoorLock doorLock = o.toJavaObject(SbDoorLock.class);
                List<SbDoorLock> doorLocks = iDoorLockService.selectSbDoorLockList(null);
                if(doorLocks.size()>0){
                    if(!doorLock.getHandleTime().equals(doorLocks.get(0).getHandleTime())){
                        iDoorLockService.insertSbDoorLock(doorLock);
                        System.out.println("添加门开关记录");
                    }
                }else {
                    iDoorLockService.insertSbDoorLock(doorLock);
                    System.out.println("添加门开关记录");
                }
            }
        }

        //上报配电箱状态


    }

    /**
     * 60秒执行一次电箱获取
     * 获取改造电箱的数据
     * @throws Exception
     */
//    @Scheduled(cron="0 0/10 * * * ?")
    @PostMapping(value = "/cr")
    public void addRemould()throws Exception{
        SbProjectElectricityBox sbox = new SbProjectElectricityBox();
        sbox.setType(1);
        //查询所有项目所有电箱
        List<SbProjectElectricityBox> projectElectricityBoxes =
                iProjectElectricityBoxService.selectSbProjectElectricityBoxList(sbox);
        for(SbProjectElectricityBox peb:projectElectricityBoxes){
            //电箱编号
            String electricityBoxId = peb.getElectricityBoxId();
            SimpleDateFormat sp1 = new SimpleDateFormat("yyyy-MM-dd");
            //获取今天日期
            String jr = sp1.format(new Date());
            //明日日期
            Date d1 = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 );
            String mr = sp1.format(d1);//获取考勤日期
            //请求url 获取电箱温度数据
            String s = Util.httpPostWithJSON(Constants.TempEelcHistoryRemould+"?dev_guid="+electricityBoxId+"&page=0", new JSONObject());
            System.out.println("获取的电箱温度数据："+s);
            //获取的原始数据
            JSONObject  originalData = JSONObject.parseObject(s);
            JSONArray data = (JSONArray)originalData.get("data");
            SbCurrentTemperature sc;
            JSONObject j;
            for(Object o:data){
                j =JSONObject.parseObject(o.toString());
                sc=new SbCurrentTemperature();
                sc.setElectricityBoxId(j.getString("dev_guid"));
                sc.setCurrent(j.getBigDecimal("elec"));
                sc.setEnvirwarm(j.getBigDecimal("temp_hj"));
                sc.setAwarm(j.getBigDecimal("temp_xl_a"));
                sc.setBwarm(j.getBigDecimal("temp_xl_b"));
                sc.setCwarm(j.getBigDecimal("temp_xl_c"));
                sc.setNwarm(j.getBigDecimal("temp_xl_n"));
                sc.setTm(j.getString("create_time"));
                iCurrentTemperatureService.insertSbCurrentTemperature(sc);
                sc.setWranType(j.getInteger("wran_type"));
                //人才安居
               sendTemperatureToPERSONNEL.rcajDate(sc);
                //城安院
                apiElectricityBoxController.reportElectricBoxState(sc,j.getInteger("wran_type"));


            }

        }




    }


}
