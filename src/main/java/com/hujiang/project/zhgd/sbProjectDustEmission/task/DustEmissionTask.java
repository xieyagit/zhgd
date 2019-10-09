package com.hujiang.project.zhgd.sbProjectDustEmission.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.common.utils.ThreadUtils;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.consumer.TspPersonnelService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.service.ISbDustEmissionService;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.service.ISbProjectDustEmissionService;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.Util;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 扬尘检测定时任务
 */
//@Component("dustEmissionTask")
@RestController
@RequestMapping(value = "/provider/tasks",method = RequestMethod.POST)
public class DustEmissionTask {

    private final Logger logger = LoggerFactory.getLogger(ZCAPIClient.class);
    @Autowired
    private ISbProjectDustEmissionService projectDustEmissionService;
    @Autowired
    private ISbDustEmissionService dustEmissionService;

    @Autowired
    private Queue tspPersonnelQueue;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private SbDustEmission sbDustEmission;

    @Autowired
    TspPersonnelService tspPersonnelService;

    @Resource
    private JPushSMS jPushSMS;


//    public static void main(String age[])throws  Exception{
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.fluentPut("SN", "MjAxOTAzMjIwMTEwMDAwMQ==");
//        jsonObject.fluentPut("CMD", "GetData");
//        //请求url 获取新的扬尘监测数据
//        String s = Util.httpPostWithJSON(Constants.DUSTEMISSION, jsonObject);
//        //获取的原始数据
//        JSONObject  originalData = JSONObject.parseObject(s);
//        JSONObject digest = JSONObject.parseObject(originalData.getString("Data"));
//        System.out.println( new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()-1000*60*60*24*7)));
//
//    }

    /**
     * 5分钟执行一次扬尘数据获取
     * @throws Exception
     */
//    @Scheduled(cron="0 0/5 * * * ? ")
    @PostMapping(value = "insert")
    public void add()throws Exception {

        System.out.println("定时任务dustEmissionTask  add");

        int count = 0;
        ArrayList<SbDustEmission> list = null;
        List<SbProjectDustEmission> projectDustEmissions = projectDustEmissionService.selectSbProjectDustEmissionList(null);
        String apiKey = null;
        if(projectDustEmissions.size() > 0) {
            apiKey = tspPersonnelService.getApikey(projectDustEmissions.get(0).getProjectId());
        }

        for (SbProjectDustEmission p : projectDustEmissions) {

            count++; //用于消息队列计数
            if(list == null) {
                list = new ArrayList<SbDustEmission>();
            }

            //请求接口传输的json数据
            JSONObject jsonObject = new JSONObject();
            jsonObject.fluentPut("SN", p.getSn());
            jsonObject.fluentPut("CMD", "GetData");
            System.out.println(p.getProjectId());
            //请求url 获取新的扬尘监测数据
            String s = Util.httpPostWithJSON(Constants.DUSTEMISSION, jsonObject);
            System.out.println("获取的扬尘数据：" + s);
            //获取的原始数据
            JSONObject originalData = JSONObject.parseObject(s);
            JSONObject digest = JSONObject.parseObject(originalData.getString("Data"));
            if (digest.size()>0){
                SbProjectDustEmission emission = new SbProjectDustEmission();
                emission.setSn(p.getSn());
                emission.setScznl("CAY");
                List<SbProjectDustEmission> list1 = projectDustEmissionService.selectSbProjectDustEmissionList(emission);
                if (list1.size()>0) {
                    ThreadUtils.async(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                cayTsp(sbDustEmission);
                            } catch (IOException e) {
                                logger.error("城安院错误(insert): " + e.getMessage() + ", 参数错误：" + sbDustEmission);
                            } catch (URISyntaxException e) {
                                logger.error("城安院错误(insert): " + e.getMessage() + ", 参数错误：" + sbDustEmission);
                            }
                        }
                    });
                }
                //保存新的扬尘记录
                SbDustEmission dustEmission = JSONObject.parseObject(digest.toJSONString(), SbDustEmission.class);
                dustEmission.setJdbh(p.getJdbh());
                dustEmission.setSn(p.getSn());

                List<SbDustEmission> dustEmissions = dustEmissionService.selectSbDustEmissionList(dustEmission);
                this.tspsb(dustEmission);
//                cayTsp(dustEmission);
                ThreadUtils.async(new Runnable() {
                    @Override
                    public void run() {
                        //根据项目ID查询扬尘信息推送消息
                        String site = p.getComments();
                        String sn=p.getSn();

                        sbDustEmission.setPm10(dustEmission.getPm10());
                        sbDustEmission.setPm25(dustEmission.getPm25());
                        sbDustEmission.setJdbh(dustEmission.getJdbh());
                        sbDustEmission.setSn(dustEmission.getSn());
                        sbDustEmission.setTsp(dustEmission.getTsp());
                        sbDustEmission.setNoise(dustEmission.getNoise());
                        sbDustEmission.setTemperature(dustEmission.getTemperature());
                        sbDustEmission.setHumidity(dustEmission.getHumidity());
                        sbDustEmission.setWindSpeed(dustEmission.getWindSpeed());
                        sbDustEmission.setSn(sn);
                        sbDustEmission.setSite(site);

                        jPushSMS.JPushAndJSMS(sbDustEmission,p.getProjectId().intValue());
                    }
                });

                if (dustEmissions.size() == 0) {
                    dustEmissionService.insertSbDustEmission(dustEmission);
                }

                /**添加扬尘数据到列表，待发送到消息队列**/
                if(apiKey != null && !apiKey.isEmpty()) {
                    list.add(dustEmission);
                }
            }

            if(list.size() > 0 && (list.size() == 50 || count == projectDustEmissions.size())) { //最多每50条发送一次消息
                JmsMessageInfo<List<SbDustEmission>> messageInfo = new JmsMessageInfo<List<SbDustEmission>>();
                messageInfo.setBody(list);
                messageInfo.setType(JmsMessageType.Data);
                messageInfo.setProjectId(p.getProjectId());
                jmsMessagingTemplate.convertAndSend(tspPersonnelQueue, JsonUtils.toJson(messageInfo));
                list = null;
            }
        }
    }


    /** 上报城安院环境监测记录*/
    public String cayTsp(SbDustEmission sbDustEmission) throws IOException, URISyntaxException {
        String f = null;
        JSONObject js1 = new JSONObject();
        JSONArray body = new JSONArray();
        SbProjectDustEmission sbProjectDustEmission = new SbProjectDustEmission();
        sbProjectDustEmission.setSn(sbDustEmission.getSn());
        List<SbProjectDustEmission> sbProjectDustEmissions = projectDustEmissionService.selectProjectDustEmissionListData(sbProjectDustEmission);
        /** 市管项目*/
        if (sbProjectDustEmissions.get(0).getSubId() != null) {
            js1.put("HJ_PGUID", sbProjectDustEmissions.get(0).getXmid());//所属项目编号
            js1.put("Jdbh", sbProjectDustEmissions.get(0).getJdbh());//项目监督编号
            js1.put("HJ_DGUID",sbDustEmission.getSn());
            js1.put("HJ_ZS",sbDustEmission.getNoise());
            js1.put("HJ_PM10",sbDustEmission.getPm10());
            js1.put("HJ_PM25",sbDustEmission.getPm25());
            js1.put("HJ_FS",sbDustEmission.getWindSpeed());
            js1.put("HJ_FX",sbDustEmission.getWinddirection());
            js1.put("HJ_WD",sbDustEmission.getTemperature());
            js1.put("HJ_SD",sbDustEmission.getHumidity());
            js1.put("tsp",sbDustEmission.getTsp());
            js1.put("sub_id",sbProjectDustEmissions.get(0).getSubId());
            body.add(js1);
            JSONObject object = new JSONObject();
            object.put("PList",body);
            f = ZCAPIClient.QGXMCAY("hj/sync_hj_data",object);
        }else{
            /** 区管项目*/
            JSONObject object1 = new JSONObject();
            JSONArray array = new JSONArray();
            JSONObject j = new JSONObject();
            j.put("pguid", sbProjectDustEmissions.get(0).getXmid());
            JSONObject object = ZCAPIClient.reportedCay("authorize/getGcbyProj", j);
            JSONArray data = object.getJSONArray("res");
            JSONObject datas = data.getJSONObject(0);
            object1.put("HJ_PGUID", sbProjectDustEmissions.get(0).getXmid());//所属项目编号
            object1.put("Jdbh", sbProjectDustEmissions.get(0).getJdbh());//项目监督编号
            object1.put("HJ_DGUID",sbDustEmission.getSn());
            object1.put("HJ_ZS",sbDustEmission.getNoise());
            object1.put("HJ_PM10",sbDustEmission.getPm10());
            object1.put("HJ_PM25",sbDustEmission.getPm25());
            object1.put("HJ_FS",sbDustEmission.getWindSpeed());
            object1.put("HJ_FX",sbDustEmission.getWinddirection());
            object1.put("HJ_WD",sbDustEmission.getTemperature());
            object1.put("HJ_SD",sbDustEmission.getHumidity());
            array.add(object1);
            JSONObject object2 = new JSONObject();
            object2.put("PList",array);
            f = ZCAPIClient.SGXMCAY("hj/sync_hj_data",object2);
        }
        return f;
    }


    public AjaxResult tspsb(SbDustEmission sbDustEmission) throws IOException, URISyntaxException {
        AjaxResult a = new AjaxResult();
            if(sbDustEmission != null) {
                HjSynchronizationInformation hsi = new HjSynchronizationInformation();
                hsi.setPlatformName("WORKSBUREAU");
                hsi.setState(1);
                hsi.setApiType("keytype5");
                JSONObject json1 = new JSONObject();
                //project.getApiKey()
//                json1.put("api_key", CommonChars.DustEApiKey);
                json1.put("api_key", "8ed13a2c3ebd45b090ce2534b18a5df9");
                json1.put("api_version", "1.0");
                //项目编码
                json1.put("project_code", "XM20160484");
                //工程编码
                json1.put("eng_code", "XM2016048402");

                JSONObject body = new JSONObject();
                //设备编号
                body.put("device_no", sbDustEmission.getSn());
                //时间
//                body.put("runtime", sbDustEmission.getDate());
                body.put("runtime", "2019-08-05 11:46:14");
                //噪声(db)
                body.put("noise", sbDustEmission.getNoise());
                //PM10 单位：ug/m3
                body.put("pm10", sbDustEmission.getPm10());
                //pm25 单位：ug/m3
                body.put("pm25", sbDustEmission.getPm25());
                //风向
                body.put("wind_speed", sbDustEmission.getWindSpeed());
                //风向(必须是一下的值): 北，西北，西，西南，南，东南，东,东北
                body.put("wind_direction", "西北");
//                body.put("wind_direction", sbDustEmission.getWinddirection());
                //温度(℃)
                body.put("temperature", sbDustEmission.getTemperature());
                //湿度(%)
                body.put("humidity", sbDustEmission.getHumidity());
                //tsp 单位：ug/m3
                body.put("tsp",sbDustEmission.getTsp());
                json1.put("body", body);
                String str = ZCAPIClient.reportedData2019("envMonitoring/addRecord", json1);//ZCAPIClient.reportedData("/crane/addBasicInfo", json1);
                System.out.println(str);
                JSONObject status1 = JSONObject.parseObject(str);
                if (status1 != null) {
                    a.put("result", status1.getString("result"));
                    a.put("code", status1.getString("code"));
                    a.put("message", status1.getString("message"));
                    return a;
                }

            }

           return AjaxResult.error();
    }

}
