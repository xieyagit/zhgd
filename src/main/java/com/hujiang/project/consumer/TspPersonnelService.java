package com.hujiang.project.consumer;


import com.hujiang.common.utils.HttpUtils;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.common.utils.Md5Utils;
import com.hujiang.framework.config.JmsConfig;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
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

@Component
public class TspPersonnelService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    public final static String PLATFORMNAME = "PERSONNEL";
    public final static String APITYPE = "keytype2";
    public final static String HOST = "http://218.17.11.171:7010";

    //log日志
    private static Logger logger = LoggerFactory.getLogger(TspPersonnelService.class);

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = JmsConfig.TSP_PERSONNEL_QUEUE)
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo(JmsConfig.TSP_PERSONNEL_QUEUE + "_OUT") //双向队列
    public String handleMessage(String message) throws IOException {

        JmsMessageInfo info = JsonUtils.parse(message, JmsMessageInfo.class);
        if(JmsMessageType.Machine.equals(info.getType())) {
            return uploadMachine(info);
        }

        if(JmsMessageType.Data.equals(info.getType())) {
            return uploadData(info);
        }

        return null;
    }

    /**批量上传扬尘数据**/
    private String uploadData(JmsMessageInfo info) throws IOException {

        List<Map> postData = new ArrayList<Map>();
        ArrayList list = JsonUtils.convert(info.getBody(), ArrayList.class);
        String apiKey = getApikey(info.getProjectId());

        if(apiKey == null || apiKey.isEmpty()) {
            return "密钥不存在！！！";
        }

        for(Object item : list) {

            SbDustEmission emission = JsonUtils.convert(item, SbDustEmission.class);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("Jdbh", emission.getJdbh());
            emission.setSn( Md5Utils.hash(emission.getSn()) );
            data.put("HJ_DGUID", emission.getSn());
            data.put("HJ_ZS", emission.getNoise()); //噪声
            data.put("HJ_PM10", emission.getPm10());
            data.put("HJ_PM25", emission.getPm25());
            data.put("HJ_FS", emission.getWindSpeed()); //风速
            data.put("HJ_FX", emission.getWinddirection()); //风向
            data.put("HJ_WD", emission.getTemperature()); //温度
            data.put("HJ_SD", emission.getHumidity()); //湿度
            data.put("HJ_QY", emission.getAirPressure()); //气压
            data.put("HJ_DY", 220); //电压
            data.put("HJ_BZJD", ""); //板载经度
            data.put("HJ_BZWD", ""); //板载纬度
            data.put("HJ_BZWD_X", ""); //板载温度
            data.put("HJ_BZSD", ""); //板载湿度
            data.put("YXSK", emission.getDate()); //运行时刻
            data.put("tsp", emission.getTsp());
            data.put("sub_id", Md5Utils.hash(info.getProjectId().toString())); //项目id

            postData.add(data);
        }


        Map<String, String> headers = new HashMap<String, String>();
        headers.put("AppKey", apiKey);
        headers.put("Content-Type", "application/json; charset=utf-8");

        HttpResponse response = HttpUtils.doPost(HOST,
                "/api/MisInter/PostReal",
                "POST", headers, null,
                JsonUtils.toJson(postData));

        return handleResponse(apiKey, postData, response);
    }

    /**上报设备**/
    private String uploadMachine(JmsMessageInfo info) throws IOException {
        SbProjectDustEmission body = JsonUtils.convert(info.getBody(), SbProjectDustEmission.class);

        body.setSn( Md5Utils.hash(body.getSn()) );

        String apiKey = getApikey(body.getProjectId());

        if(apiKey == null || apiKey.isEmpty()) {
            return "密钥不存在！！！";
        }

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("AppKey", apiKey);
        headers.put("Content-Type", "application/json; charset=utf-8");

        List<Map> postData = new ArrayList<Map>();
        Map<String, String> data = new HashMap<String, String>();
        data.put("Jdbh", body.getJdbh());
        data.put("DEV_GUID", body.getSn());
        data.put("MD_NAME", body.getComments());
        data.put("MD_TYPE", "环境");
        data.put("MD_MODEL", "hj8190");
        data.put("MD_OPTION", body.getMeOption());
        data.put("INSTALL_DATE", body.getDeviceInstallationDate());
        data.put("RUNNING_STATE", null);
        data.put("INSTALL_LOCATION", body.getInstallAddress());
        data.put("INSTALL_COMPANY", "虎匠科技");
        data.put("sub_id", Md5Utils.hash(body.getProjectId().toString()));

        postData.add(data);

        HttpResponse response = HttpUtils.doPost(HOST,
                "/api/MisInter/Post",
                "POST", headers, null,
                JsonUtils.toJson(postData));

        return handleResponse(apiKey, postData, response);
    }

    /**响应接口请求**/
    private String handleResponse(String apiKey, List<Map> postData, HttpResponse response) throws IOException {

        String errorMessage = null;
        if(response.getStatusLine().getStatusCode() != 200) {
            errorMessage = String.format("人才安居接口上传设备失败！appkey:%s，参数：%s", apiKey, postData);
            logger.error(errorMessage);
            throw new IOException(errorMessage);
        }

        String result= EntityUtils.toString(response.getEntity());
        Map<String, Object> resultMap = JsonUtils.parse(result, HashMap.class);
        if(resultMap != null && resultMap.size() > 0) {
            if(resultMap.containsKey("success") && resultMap.get("success").equals(true)) {
                return result;
            }
        }

        errorMessage = resultMap.get("message").toString();
        logger.error(errorMessage);
        throw new IOException(errorMessage);
    }

    /**获取apikey**/
    public String getApikey(Long projectId) {
        //根据info.getProjectId()在hj_synchronization_information表中取出密钥
        HjSynchronizationInformation synchronizationInfo = hjSynchronizationInformationService
                .getProjectSynchronizationInfoByPlatformname(Integer.valueOf(projectId.intValue()),
                        PLATFORMNAME, APITYPE);

        if(synchronizationInfo == null) {
            return null;
        }

        return synchronizationInfo.getApiKey();
    }
}
