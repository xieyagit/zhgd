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
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import com.hujiang.project.zhgd.utils.Tools;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**人才安居上报升降机*/
@Component
public class ElevatorCayService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    public final static String PLATFORMNAME = "PERSONNEL";
    public final static String APITYPE = "keytype2";
    public final static String HOST = "http://218.17.11.171:7010";

    //log日志
    private static Logger logger = LoggerFactory.getLogger(ElevatorCayService.class);

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = JmsConfig.ELEVATOR_CAY_QUEUE)
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo(JmsConfig.ELEVATOR_CAY_QUEUE + "_OUT") //双向队列
    public String handleMessage(String message) {
        try {
            JmsMessageInfo info = JsonUtils.parse(message, JmsMessageInfo.class);
            if (JmsMessageType.Machine.equals(info.getType())) {
                uploadMachine(info);
            }

            if (JmsMessageType.Data.equals(info.getType())) {
                uploadData(info);
            }

            if (JmsMessageType.Electrify.equals(info.getType())) {
                uploadElectrify(info);
            }

            if (JmsMessageType.PunchTheClock.equals(info.getType())) {
                uploadPunchTheClock(info);
            }

            if (JmsMessageType.WorkCycle.equals(info.getType())) {
                uploadWorkCycle(info);
            }
        }
        catch (IOException e) {
            return message;
        }

        return null;
    }

    /**批量上传升降机实时数据**/
    private void uploadData(JmsMessageInfo info) throws IOException {
        ArrayList list = JsonUtils.convert(info.getBody(), ArrayList.class);

        for(Object item : list) {
            SbElevatorAddrecord emission = JsonUtils.convert(item, SbElevatorAddrecord.class);
            if(emission.getJdbh() == null || emission.getXmid() == null) {
                logger.error("缺失必要参数！！！");
                return;
            }
            emission.setHxzid( Md5Utils.hash(emission.getHxzid()));
            JSONObject data = new JSONObject();
            JSONArray jsonArray = new JSONArray();

            data.put("pguid",emission.getXmid());//所属项目编号
            data.put("Jdbh",emission.getJdbh());//项目监督编号
            data.put("guid",Tools.encodeToMD5s(emission.getHxzid()));//设备ID
            data.put("zz",emission.getLaod()*1000);//载重（kg）
            data.put("yxsk",emission.getRuntime());//运行时刻
            data.put("gd",emission.getHeight());//高度
            data.put("zt",0);//状态(0 正常状态/1 IC 卡无效/2 安全器故障/4 上高度预警/8 上高度报警/16 非本人操作/32 监理授权/64 加节模式/128 下限位报警)
            data.put("sxw",emission.getIsUpWarning());//是否上限位报警（0-否，1-是，2-无上限位）
            data.put("xxw",emission.getDownlineTime());//是否下限位报警（0-否，1-是，2-无下限位）
            data.put("Aqq_alarm",emission.getIsSafetyDeviceWarn());//是否安全器（防坠器）报警
            data.put("zz_alarm",emission.getIsOverWarning());//是否超重报警
            data.put("qxw",emission.getIsForwardWarning());//是否前（外）限位报警
            data.put("hxw",emission.getIsBackwardWarning());//是否后（内）限位报警
            if (emission.getSubId() != null){
                data.put("sub_id",emission.getSubId());//工程ID
                jsonArray.add(data);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter/",
                        "lifter/ele_t_data?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }else {
                jsonArray.add(data);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.197.174/misInter/",
                        "lifter/ele_t_data?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }
        }
    }

    /**上报设备**/
    private void uploadMachine(JmsMessageInfo info) throws IOException {
        ArrayList list = JsonUtils.convert(info.getBody(), ArrayList.class);
        for(Object item : list) {
            SbElevatorAddrecord emission = JsonUtils.convert(item, SbElevatorAddrecord.class);
            if(emission.getJdbh() == null || emission.getXmid() == null) {
                logger.error("缺失必要参数！！！");
                return;
            }
            emission.setHxzid( Md5Utils.hash(emission.getHxzid()));
            JSONObject data = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            JSONArray array1 = new JSONArray();
            data.put("Jdbh", emission.getJdbh());
            data.put("ProjectID", emission.getXmid());
            data.put("Dev_GUID", emission.getHxzid());
            data.put("Dev_UID", emission.getElevatorName());
            data.put("Jc_dev_company",emission.getInstallCompany());
            data.put("Serial_Num", emission.getSerialNum());
            data.put("Dev_Name", emission.getDname());
            //上报城安院升降机参数信息 （开始）
            JSONObject object2 = new JSONObject();
            object2.put("L_PGUID", emission.getXmid());// 所属项目
            object2.put("Jdbh", emission.getJdbh());//项目监督编号
            object2.put("L_DGUID", emission.getHxzid());//设备编号
            object2.put("L_Load_Capacity", emission.getCapacity());//最大载重（KG)
            object2.put("L_Height", emission.getHeight());//最大高度(M)
            if (emission.getSubId() != null){
                data.put("sub_id",emission.getSubId());//工程ID
                jsonArray.add(data);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter/",
                        "lifter/ele_info?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
                object2.put("sub_id", emission.getSubId());//工程ID
                array1.add(object2);
                JSONObject jsonObject4 = new JSONObject();
                jsonObject4.put("PList", array1);
                HttpResponse responses = HttpUtils.doPost("http://139.159.186.240/misInter/",
                        "lifter/ele_par?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,responses);
            } else {
                jsonArray.add(data);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter/",
                        "lifter/ele_info?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
                array1.add(object2);
                JSONObject jsonObject4 = new JSONObject();
                jsonObject4.put("PList", array1);
                HttpResponse responses = HttpUtils.doPost("http://139.159.186.240/misInter/",
                        "lifter/ele_par?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,responses);
            }
        }
    }

    private void uploadElectrify(JmsMessageInfo info) throws IOException{
        ArrayList arrayList = JsonUtils.convert(info.getBody(), ArrayList.class);
        JSONArray jsonArray = new JSONArray();
        for(Object item : arrayList) {
            SbElevatorAddrecord sbCraneAddrecord = JsonUtils.convert(item, SbElevatorAddrecord.class);
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
                        "lifter/ele_t_state?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }else {
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.197.174/misInter/",
                        "lifter/ele_t_state?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
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
            SbElevatorAddrecord sbElevatorAddrecord = JsonUtils.convert(item, SbElevatorAddrecord.class);
            if (sbElevatorAddrecord.getXmid() == null || sbElevatorAddrecord.getJdbh() == null){
                logger.error("缺失jdbh或者城安院项目id");
                return;
            }
            sbElevatorAddrecord.setHxzid( Md5Utils.hash(sbElevatorAddrecord.getHxzid()));
            JSONObject object1 = new JSONObject();
            object1.put("devType", "升降机");
            object1.put("projectId", sbElevatorAddrecord.getXmid());
            object1.put("Jdbh", sbElevatorAddrecord.getJdbh());
            object1.put("devGuid",sbElevatorAddrecord.getHxzid());
            object1.put("workerName",sbElevatorAddrecord.getOperatorName());
            object1.put("punchTime",sbElevatorAddrecord.getPunchTime());
            object1.put("closingTime",sbElevatorAddrecord.getClosingTime());
            if (sbElevatorAddrecord.getSubId() != null){
                object1.put("sub_id",sbElevatorAddrecord.getSubId());//工程ID
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

    private void uploadWorkCycle(JmsMessageInfo info) throws IOException{
        ArrayList arrayList = JsonUtils.convert(info.getBody(), ArrayList.class);
        JSONArray jsonArray = new JSONArray();
        for(Object item : arrayList) {
            SbElevatorAddrecord sbElevatorAddrecord = JsonUtils.convert(item, SbElevatorAddrecord.class);
            if (sbElevatorAddrecord.getXmid() == null || sbElevatorAddrecord.getJdbh() == null){
                logger.error("缺失jdbh或者城安院项目id");
                return;
            }
            sbElevatorAddrecord.setHxzid( Md5Utils.hash(sbElevatorAddrecord.getHxzid()));
            JSONObject object1 = new JSONObject();
            object1.put("PGUID",sbElevatorAddrecord.getXmid());//所属项目编号
            object1.put("Jdbh",sbElevatorAddrecord.getJdbh());//项目监督编号
//            object1.put("sub_id",datas.getString("gcid"));//工程ID
            object1.put("GUID", Tools.encodeToMD5s(sbElevatorAddrecord.getHxzid()));//设备编号
            object1.put("Yxsk_S", sbElevatorAddrecord.getDownlineTime());//操作开始时间
            object1.put("Yxsk_E", sbElevatorAddrecord.getClosingTime());//操作结束时间
            object1.put("zz", sbElevatorAddrecord.getLoadRatio());//工作最大承重值
            object1.put("cz", sbElevatorAddrecord.getIsOverWarning());//是否发生超载
            object1.put("Operator_A larm", "0");//是否非持卡人操作
            if (sbElevatorAddrecord.getSubId() != null){
                object1.put("sub_id",sbElevatorAddrecord.getSubId());//工程ID
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter/",
                        "lifter/ele_t_run?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }else {
                jsonArray.add(object1);
                JSONObject object = new JSONObject();
                object.put("PList",jsonArray);
                HttpResponse response = HttpUtils.doPost("http://139.159.197.174/misInter/",
                        "lifter/ele_t_run?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object));
                handleResponse(object,response);
            }
        }
    }

    /**响应接口请求**/
    private String handleResponse( JSONObject postData, HttpResponse response) throws IOException {

        String errorMessage = null;
        if(response.getStatusLine().getStatusCode() != 200) {
            errorMessage = String.format("城安院接口上传设备失败！参数：%s", postData);
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
            }
        }

        errorMessage = resultMap.get("message").toString();
        logger.error(errorMessage);
        throw new IOException(errorMessage);
    }

}
