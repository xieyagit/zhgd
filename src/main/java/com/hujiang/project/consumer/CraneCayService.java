package com.hujiang.project.consumer;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.HttpUtils;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.common.utils.Md5Utils;
import com.hujiang.framework.config.JmsConfig;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import com.hujiang.project.zhgd.sbCraneBinding.service.ISbCraneBindingService;
import com.hujiang.project.zhgd.sbCraneWorkloop.domain.SbCraneWorkloop;
import com.hujiang.project.zhgd.sbCraneWorkloop.service.ISbCraneWorkloopService;
import com.hujiang.project.zhgd.utils.Tools;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**城安院上报升降机*/
@Component
public class CraneCayService {

    DecimalFormat df2=new DecimalFormat("0.00");

    @Autowired
    private ISbCraneWorkloopService iSbCraneWorkloopService;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private ISbCraneBindingService iSb;

    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    //log日志
    private static Logger logger = LoggerFactory.getLogger(CraneCayService.class);

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = JmsConfig.CRANE_CAY_QUEUE)
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo(JmsConfig.CRANE_CAY_QUEUE + "_OUT") //双向队列
    public String handleMessage(String message){
        try {
            JmsMessageInfo info = JsonUtils.parse(message, JmsMessageInfo.class);
            if(JmsMessageType.Machine.equals(info.getType())) {
                uploadMachine(info);
            }

            if(JmsMessageType.Data.equals(info.getType())) {
                uploadData(info);
            }

            if(JmsMessageType.WARNING.equals(info.getType())) {
                uploadWarmimg(info);
            }

            if(JmsMessageType.Electrify.equals(info.getType())) {
                uploadElectrify(info);
            }

            if(JmsMessageType.WorkCycle.equals(info.getType())) {
                uploadWorkCycle(info);
            }

            if(JmsMessageType.PunchTheClock.equals(info.getType())) {
                uploadPunchTheClock(info);
            }
        }
        catch (IOException e) {
            return message;
        }
        return null;
    }

    private void uploadData(JmsMessageInfo info) throws IOException {
        ArrayList list = JsonUtils.convert(info.getBody(), ArrayList.class);
        JSONArray jsonArray = new JSONArray();
        for(Object item : list) {
            SbCraneAddrecord sbCraneAddrecord = JsonUtils.convert(item, SbCraneAddrecord.class);
            if (sbCraneAddrecord.getXmid() == null || sbCraneAddrecord.getJdbh() == null){
                logger.error("缺失jdbh或者城安院项目id");
                return;
            }
            sbCraneAddrecord.setHxzid( Md5Utils.hash(sbCraneAddrecord.getHxzid()));
            JSONObject object1 = new JSONObject();
            object1.put("PGUID", sbCraneAddrecord.getXmid());//所属项目编号
            object1.put("Jdbh", sbCraneAddrecord.getJdbh());//项目监督编号
            object1.put("GUID", Tools.encodeToMD5s(sbCraneAddrecord.getHxzid()));//设备号
            object1.put("yxsk",sbCraneAddrecord.getRuntime());//运行时刻
            object1.put("zz",sbCraneAddrecord.getLoad().intValue()*1000);//载重（KG)
            object1.put("lj",sbCraneAddrecord.getMoment());//力矩
            object1.put("jd", sbCraneAddrecord.getSlewingSpeed());
            object1.put("zxw", sbCraneAddrecord.getIsLeftWarning());
            object1.put("yxw", sbCraneAddrecord.getIsRightWarning());
            object1.put("fd", sbCraneAddrecord.getRange());
            object1.put("qxw", sbCraneAddrecord.getIsForwardWarning());
            object1.put("hxw", sbCraneAddrecord.getIsBackwardWarning());
            object1.put("gd",sbCraneAddrecord.getHeight());
            object1.put("sxw",sbCraneAddrecord.getIsUpWarning());
            object1.put("fs",sbCraneAddrecord.getWindSpeed());
            object1.put("hzxw", "0");//是否回转限位报 警
            if (sbCraneAddrecord.getSubId() != null){
                object1.put("fpz",sbCraneAddrecord.getMultiAlarmAll());//防碰撞报警
                SbCraneWorkloop sbCraneWorkloop = iSbCraneWorkloopService.selectTD(sbCraneAddrecord.getHxzid());
                if (sbCraneAddrecord.getWindSpeed()>= 13.8 && sbCraneWorkloop.getMaxWindSpeed()>13.8) {
                    object1.put("cfszy", "1");//是否超风速作业 报警（风速大于6 级（大于13.8 m/s），在连续两个工作循环工作，发生报警）
                }else {
                    object1.put("cfszy", "0");//是否超风速作业 报警（风速大于6 级（大于13.8 m/s），在连续两个工作循环工作，发生报警）
                }
                object1.put("sub_id",sbCraneAddrecord.getSubId());//工程ID
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter/",
                        "lifter/ele_par?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }else {
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.197.174/misInter/",
                        "lifter/ele_par?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }
        }
    }

    private void uploadMachine(JmsMessageInfo info) throws IOException {
        SbCraneBinding body = JsonUtils.convert(info.getBody(), SbCraneBinding.class);
        if (body.getJdbh() == null || body.getXmid() == null)
        {
            logger.error("缺失必要参数或者您的项目不再深圳市城安院监管内！请核对");
            return;
        }
        body.setHxzid( Md5Utils.hash(body.getHxzid()));
        //塔机设备基本信息
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        JSONArray jsonArray = new JSONArray();
        json.put("ProjectID", body.getXmid());//所属项目编号
        json.put("Jdbh",body.getJdbh());//项目监督编号
        json.put("Dev_GUID", body.getHxzid());//设备编号
        json.put("Dev_UID", body.getCraneName());//设备用户编号
        json.put("Dev_Name", body.getDname());//设备名称（命名规 则：阿拉伯数字# 塔吊；示例：1#塔 吊 2#塔吊，同个 项目下数字不能 重复）
        json.put("Jc_dev_company", body.getInstallCompany());//监测设备厂商
        json.put("Serial_Num",body.getSerialNum());//广东省统一安装告 知编号（产权备案 编号）
        //参数信息
        JSONObject object2 = new JSONObject();
        object2.put("TC_PGUID", body.getXmid());//所属项目编号
        object2.put("Jdbh", body.getJdbh());//项目监督编号
        object2.put("TC_GUID", body.getDeviceNo());//设备编号
        object2.put("TC_MaxScope",body.getTcMaxScope());//最大幅度(M)
        object2.put("TC_MaxHeight",body.getTcMaxHeight());//最大高度（M）
        object2.put("TC_LoadCapacity",body.getTcLoadCapacity());//最大载重（KG）
        object2.put("Tower_type",2);//塔机类型（ 1-动臂塔式起重机， 2-其他, 3-塔头塔式起重机， 4-平头塔式起重机）
        object2.put("tc_load_moment",body.getTcLoadMoment());//额定起重力矩（N·m）
        if (body.getSubId() != null) {
            json.put("sub_id", body.getSubId());//工程 id
            array.add(json);
            JSONObject object1 = new JSONObject();
            object1.put("PList", array);
            System.out.println(object1);
            HttpResponse responseMachine = HttpUtils.doPost("http://139.159.186.240/misInter/",
                    "tower/towerInfo?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                    "POST", null, null,
                    JsonUtils.toJson(array));
            handleResponse(object1,responseMachine);
            object2.put("sub_id", body.getSubId());//工程 id
            jsonArray.add(object1);
            JSONObject object = new JSONObject();
            object.put("PList", array);
            HttpResponse responseMachines = HttpUtils.doPost("http://139.159.186.240/misInter/",
                    "tower/towerParams?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                    "POST", null, null,
                    JsonUtils.toJson(object));
            handleResponse(object,responseMachines);
        } else {
            array.add(json);
            JSONObject object1 = new JSONObject();
            object1.put("PList", array);
            HttpResponse responseMachine = HttpUtils.doPost("http://139.159.197.174/misInter/",
                    "tower/towerInfo?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                    "POST", null, null,
                    JsonUtils.toJson(object1));
            handleResponse(object1,responseMachine);
            jsonArray.add(object1);
            JSONObject object = new JSONObject();
            object.put("PList", array);
            HttpResponse responseMachines = HttpUtils.doPost("http://139.159.197.174/misInter/",
                    "tower/towerParams?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                    "POST", null, null,
                    JsonUtils.toJson(object));
            handleResponse(object,responseMachines);
        }
    }

    private void uploadWarmimg(JmsMessageInfo info) throws IOException{
        ArrayList arrayList = JsonUtils.convert(info.getBody(), ArrayList.class);
        JSONArray jsonArray = new JSONArray();
        for(Object item : arrayList) {
            SbCraneAddrecord sbCraneAddrecord = JsonUtils.convert(item, SbCraneAddrecord.class);
            if (sbCraneAddrecord.getXmid() == null || sbCraneAddrecord.getJdbh() == null){
                logger.error("缺失jdbh或者城安院项目id");
                return;
            }
            sbCraneAddrecord.setHxzid( Md5Utils.hash(sbCraneAddrecord.getHxzid()));
            JSONObject object1 = new JSONObject();
            object1.put("PGUID",sbCraneAddrecord.getXmid());//所属项目编号
            object1.put("Jdbh",sbCraneAddrecord.getJdbh());//项目监督编号
            object1.put("GUID",sbCraneAddrecord.getDeviceNo());//设备号
            object1.put("zz",sbCraneAddrecord.getLoad());//载重
            object1.put("lj",df2.format(Double.valueOf(sbCraneAddrecord.getLoad())*Double.valueOf(sbCraneAddrecord.getRange())));//力矩
            object1.put("jd",sbCraneAddrecord.getSlewingSpeed());//回转角度
            object1.put("fd",sbCraneAddrecord.getRange());//幅度
            object1.put("gd",sbCraneAddrecord.getHeight());//高度
            object1.put("fs",sbCraneAddrecord.getWindSpeed());//风速
            if (sbCraneAddrecord.getSubId() != null){
                object1.put("sub_id",sbCraneAddrecord.getSubId());//工程ID
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter/",
                        "tower/tow_Warning?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }else {
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.197.174/misInter/",
                        "tower/tow_Warning?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }
        }
    }

    private void uploadElectrify(JmsMessageInfo info) throws IOException{
        ArrayList arrayList = JsonUtils.convert(info.getBody(), ArrayList.class);
        JSONArray jsonArray = new JSONArray();
        for(Object item : arrayList) {
            SbCraneAddrecord sbCraneAddrecord = JsonUtils.convert(item, SbCraneAddrecord.class);
            if (sbCraneAddrecord.getXmid() == null || sbCraneAddrecord.getJdbh() == null){
                logger.error("缺失jdbh或者城安院项目id");
                return;
            }
            sbCraneAddrecord.setHxzid( Md5Utils.hash(sbCraneAddrecord.getHxzid()));
            JSONObject object1 = new JSONObject();

            object1.put("GUID",Tools.encodeToMD5s(sbCraneAddrecord.getHxzid()));//设备号
            object1.put("Yxsk",sbCraneAddrecord.getRuntime());//运行时刻
            object1.put("PGUID", sbCraneAddrecord.getXmid());//所属项目编号
            object1.put("Jdbh", sbCraneAddrecord.getJdbh());//项目监督编号
            if(StringUtils.isBlank(sbCraneAddrecord.getDownlineTime())){
                object1.put("operation","1");//事件类型（0 断电，1 通电）
            }else {
                object1.put("operation","0");//事件类型（0 断电，1 通电）
            }
            if (sbCraneAddrecord.getSubId() != null){
                object1.put("sub_id",sbCraneAddrecord.getSubId());//工程ID
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter/",
                        "tower_t_state?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }else {
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.197.174/misInter/",
                        "tower_t_state?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }
        }
    }

    private void uploadWorkCycle(JmsMessageInfo info) throws IOException{
        ArrayList arrayList = JsonUtils.convert(info.getBody(), ArrayList.class);
        JSONArray jsonArray = new JSONArray();
        for(Object item : arrayList) {
            SbCraneAddrecord sbCraneAddrecord = JsonUtils.convert(item, SbCraneAddrecord.class);
            if (sbCraneAddrecord.getXmid() == null || sbCraneAddrecord.getJdbh() == null){
                logger.error("缺失jdbh或者城安院项目id");
                return;
            }
            sbCraneAddrecord.setHxzid( Md5Utils.hash(sbCraneAddrecord.getHxzid()));
            JSONObject object1 = new JSONObject();
            object1.put("PGUID", sbCraneAddrecord.getXmid());//所属项目编号
            object1.put("Jdbh", sbCraneAddrecord.getJdbh());//项目监督编号
            object1.put("GUID", Tools.encodeToMD5s(sbCraneAddrecord.getHxzid()));
            object1.put("Yxsk_E",sbCraneAddrecord.getWorkEndTime());
            object1.put("ljb",sbCraneAddrecord.getWorkMaxHeight());
            if (sbCraneAddrecord.getSubId() != null){
                object1.put("sub_id",sbCraneAddrecord.getSubId());//工程ID
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter/",
                        "tower/tow_cycle?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }else {
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.197.174/misInter/",
                        "tower/tow_cycle?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }
        }
    }

    private void uploadPunchTheClock(JmsMessageInfo info) throws IOException{
        ArrayList arrayList = JsonUtils.convert(info.getBody(), ArrayList.class);
        JSONArray jsonArray = new JSONArray();
        for(Object item : arrayList) {
            SbCraneAddrecord sbCraneAddrecord = JsonUtils.convert(item, SbCraneAddrecord.class);
            if (sbCraneAddrecord.getXmid() == null || sbCraneAddrecord.getJdbh() == null){
                logger.error("缺失jdbh或者城安院项目id");
                return;
            }
            sbCraneAddrecord.setHxzid( Md5Utils.hash(sbCraneAddrecord.getHxzid()));
            JSONObject object1 = new JSONObject();
            object1.put("devType", "塔机");
            object1.put("projectId", sbCraneAddrecord.getXmid());
            object1.put("Jdbh", sbCraneAddrecord.getJdbh());
            object1.put("devGuid",sbCraneAddrecord.getHxzid());
            object1.put("workerName",sbCraneAddrecord.getOperatorName());
            object1.put("punchTime",sbCraneAddrecord.getPunchTime());
            object1.put("closingTime",sbCraneAddrecord.getClosingTime());
            if (sbCraneAddrecord.getSubId() != null){
                object1.put("sub_id",sbCraneAddrecord.getSubId());//工程ID
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter/",
                        "tower/oper_pec?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }else {
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.197.174/misInter/",
                        "tower/oper_pec?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }
        }
    }


    /**响应接口请求**/
    private String handleResponse(JSONObject postData, HttpResponse response) throws IOException {

        String errorMessage = null;
        if(response.getStatusLine().getStatusCode() != 200) {
            errorMessage = String.format("城安院接口上传设备失败！参数：%s", JsonUtils.toJson(postData));
            logger.error(errorMessage);
            throw new IOException(errorMessage);
        }

        String result= EntityUtils.toString(response.getEntity());
        Map<String, Object> resultMap = JsonUtils.parse(result, HashMap.class);
        if(resultMap != null && resultMap.size() > 0) {
            if(resultMap.containsKey("success")) {
                if(resultMap.get("success").equals(true)) {
                    return result;
                }

                if(resultMap.containsKey("message")) {
                    logger.error(resultMap.get("message").toString());
                }
            }
        }

        errorMessage = resultMap.get("message").toString();
        logger.error(errorMessage);
        throw new IOException(errorMessage);
    }
}
