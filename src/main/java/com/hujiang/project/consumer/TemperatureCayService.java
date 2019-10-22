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
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import com.hujiang.project.zhgd.utils.Tools;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**电箱上报升降机*/
@Component
public class TemperatureCayService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    //log日志
    private static Logger logger = LoggerFactory.getLogger(TemperatureCayService.class);

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = JmsConfig.TEMPERATURE_CAY_QUEUE)
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo(JmsConfig.TEMPERATURE_CAY_QUEUE + "_OUT") //双向队列
    public String handleMessage(String message) {

        try {
            JmsMessageInfo info = JsonUtils.parse(message, JmsMessageInfo.class);
            if (JmsMessageType.Machine.equals(info.getType())) {
                uploadMachine(info);
            }
            if (JmsMessageType.Data.equals(info.getType())) {
                uploadData(info);
            }
        }
        catch (IOException e) {
            return message;
        }

        return null;
    }

    /**上报设备**/
    private void uploadMachine(JmsMessageInfo info) throws IOException {
        SbProjectElectricityBox body = JsonUtils.convert(info.getBody(), SbProjectElectricityBox.class);
        if(body.getXmid() == null || body.getJdbh() == null) {
            logger.error("缺失城安院必要参数！！！");
            return;
        }
        JSONArray array = new JSONArray();
        JSONObject data = new JSONObject();
        data.put("PROJECT_ID", body.getXmid());// 项目编号
        data.put("Jdbh", body.getJdbh());// 项目监督编号
        data.put("DEV_GUID", Tools.encodeToMD5s(body.getElectricityBoxId()));// 设备编码
        data.put("DEV_TYPE", body.getDevType());// 设备类型
        data.put("DEV_TYPE_NAME", body.getElectricityBoxName());// 设备类型名称
        data.put("COMPANY_NAME", body.getCompanyName());// 设备安装单位
        data.put("COMPANY_ADDRESS", "");// 公司地址
        data.put("INSTALL_ADDRESS", body.getInstallAddress());// 安装地址
        data.put("INSTALL_ADDRTYPE", body.getInstallAddrtype());// 字典：生活区、施工现场、配电房
        data.put("DTU_ID", Tools.encodeToMD5s(body.getElectricityBoxId()));// DTU 标识
        data.put("LONGTITUDE", "");// 经度
        data.put("LATITUDE", "");// 纬度
        data.put("CREATE_DATE", "");// 创建时间
        data.put("PHOTO_PATH", "");// 设备照片路径（多张照片以“,”分隔)
        data.put("TEMP_LIMIT", body.getTempLimit());// 电缆温度限值
        data.put("ELEC_LIMIT", body.getElecLimit());// 漏电流限值
        data.put("AROUND_TEMP_LIMIT", body.getAroundTemp());// 周围环境温度限值
        if (body.getSubId() != null){
            data.put("sub_id",body.getSubId());//工程ID
            array.add(data);
            JSONObject object = new JSONObject();
            object.put("PList",array);
            HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter/",
                    "/pdx/pdxParams?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                    "POST", null, null,
                    JsonUtils.toJson(object));
            handleResponse(object,response);
        }else {
            array.add(data);
            JSONObject object = new JSONObject();
            object.put("PList",array);
            HttpResponse response = HttpUtils.doPost("http://139.159.197.174/misInter/",
                    "/pdx/pdxParams?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                    "POST", null, null,
                    JsonUtils.toJson(object));
            handleResponse(object,response);
        }
    }

    /**批量上传电箱数据**/
    private void uploadData(JmsMessageInfo info) throws IOException {
        List<Map> postData = new ArrayList<Map>();
        ArrayList list = JsonUtils.convert(info.getBody(), ArrayList.class);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Object item : list) {
            SbCurrentTemperature emission = JsonUtils.convert(item, SbCurrentTemperature.class);
            if(emission.getXmid() == null || emission.getJdbh() == null) {
                logger.error("缺失城安院必要参数！！！");
                return;
            }
            List channelDatas = new ArrayList();
            String status = "正常";
            if (emission.getWranType() == 1) {
                status = "报警";
            }
            Integer i = 0;
            if (status.equals("正常")){
                i = 0;
            } else if (status.equals("报警")){
                i = 1;
            } else if (status.equals("未连接")){
                i = 2;
            } else if (status.equals("故障")) {
                i = 4;
            } else if (status.equals("屏蔽")) {
                i = 8;
            }
            String alert = "表示无报警";
            if (emission.getWranType() == 1) {
                alert = "表示漏电报警";
            }
            Integer b = 0;
            if (alert.equals("表示无报警")){
                b  = 0 ;
            } else if (alert.equals("表示漏电报警")){
                b = 1;
            } else if (alert.equals("表示温度报警")){
                b = 2;
            } else if (alert.equals("表示两者都报警")){
                b = 3;
            }
            JSONArray array = new JSONArray();
            JSONObject object = new JSONObject();
            object.put("PROJECT_ID", emission.getProjectId());// 项目ID;
            object.put("Jdbh", emission.getJdbh());// 监督号
            object.put("DEV_GUID", Tools.encodeToMD5s(emission.getElectricityBoxId()));// 设备ID
            object.put("DEV_OPERATE_TIME", df.format(new Date()));// 上传日期精确到时分秒
            object.put("DEV_STATUS", i);   // 0：正常1：报警 2：未连接 4：故障 8：屏蔽
            object.put("WRAN_TYPE", b);   // 0 表示无报警，1 表示漏电报警，2 表示温度报警，3 表示两者都报警
            if (emission.getWranType() != 0) {
                object.put("WARN_ID", alert);// WARN_TYPE 不等于 0 时此字段必填，唯一标识，同一次报警 ID 相同
            }
            object.put("TEMP_XL_a", emission.getAwarm());  // 线缆 a 温度
            object.put("TEMP_XL_b", emission.getBwarm());  // 线缆 b 温度
            object.put("TEMP_XL_c", emission.getCwarm());  // 线缆 c 温度
            object.put("TEMP_XL_n", emission.getNwarm()); // 线缆 n 温度
            object.put("ELEC_LIMIT", emission.getCurrent());    // 漏电流
            object.put("TEMP_HJ", emission.getEnvirwarm());     // 环境温度
            object.put("ChannelDatas", channelDatas);
            if (emission.getSubId() != null){
                object.put("sub_id", emission.getSubId());
                array.add(object);
                JSONObject object1 = new JSONObject();
                object1.put("PList",array);
                HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter/",
                        "/pdx/pdxState?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object1));
                handleResponse(object1,response);
            }else {
                array.add(object);
                JSONObject object1 = new JSONObject();
                object1.put("PList",array);
                HttpResponse response = HttpUtils.doPost("http://139.159.197.174/misInter/",
                        "/pdx/pdxState?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(object1));
                handleResponse(object1,response);
            }
        }
    }


    /**响应接口请求**/
    private String handleResponse(JSONObject postData, HttpResponse response) throws IOException {

        String errorMessage = null;
        if(response.getStatusLine().getStatusCode() != 200) {
            errorMessage = String.format("人才安居接口上传设备失败！参数：%s",  postData);
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
