package com.hujiang.project.consumer;


import com.hujiang.common.utils.HttpUtils;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.common.utils.Md5Utils;
import com.hujiang.framework.config.JmsConfig;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
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
public class TemperaturePersonnelService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    public final static String PLATFORMNAME = "PERSONNEL";
    public final static String APITYPE = "keytype2";
    public final static String HOST = "http://218.17.11.171:7010";

    //log日志
    private static Logger logger = LoggerFactory.getLogger(TemperaturePersonnelService.class);

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = JmsConfig.TEMPERATURE_PERSONNEL_QUEUE)
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo(JmsConfig.TEMPERATURE_PERSONNEL_QUEUE + "_OUT") //双向队列
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
        String apiKey = getApikey(body.getProjectId().longValue());

        if(apiKey == null || apiKey.isEmpty()) {
            logger.error("密钥不存在！！！");
            return;
        }

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("AppKey", apiKey);
        headers.put("Content-Type", "application/json; charset=utf-8");

        List<Map> postMachine = new ArrayList<Map>();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("Jdbh", body.getJdbh());
        data.put("DEV_TYPE", body.getDevType());// 设备类型
        data.put("DEV_GUID",Md5Utils.hash(body.getElectricityBoxId()));
        data.put("DEV_TYPE_NAME", body.getComments());// 设备类型名称
        data.put("COMPANY_NAME", body.getCompanyName());// 设备安装单位
        data.put("COMPANY_ADDRESS", "");// 公司地址
        data.put("INSTALL_ADDRESS", body.getInstallAddress());// 安装地址
        data.put("INSTALL_ADDRTYPE", body.getInstallAddrtype());// 字典：生活区、施工现场、配电房
        data.put("DTU_ID", 1);// DTU 标识
        data.put("LONGTITUDE", "");// 经度
        data.put("LATITUDE", "");// 纬度
        data.put("CREATE_DATE", "");// 创建时间
        data.put("PHOTO_PATH", "");// 设备照片路径（多张照片以“,”分隔)
        data.put("TEMP_LIMIT", body.getTempLimit());// 电缆温度限值
        data.put("ELEC_LIMIT", body.getElecLimit());// 漏电流限值
        data.put("AROUND_TEMP_LIMIT", body.getAroundTemp());// 周围环境温度限值
        data.put("sub_id", Md5Utils.hash(body.getProjectId().toString()));
        postMachine.add(data);


        HttpResponse responseMachine = HttpUtils.doPost(HOST,
                "/api/DistBox/Post",
                "POST", headers, null,
                JsonUtils.toJson(postMachine));
        handleResponse(apiKey, postMachine, responseMachine);

    }

    /**批量上传电箱数据**/
    private void uploadData(JmsMessageInfo info) throws IOException {

        List<Map> postData = new ArrayList<Map>();
        ArrayList list = JsonUtils.convert(info.getBody(), ArrayList.class);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for(Object item : list) {

            SbCurrentTemperature emission = JsonUtils.convert(item, SbCurrentTemperature.class);
            String apiKey = getApikey(emission.getProjectId().longValue());

            if(apiKey == null || apiKey.isEmpty()) {
                logger.error("密钥不存在！！！");
                return;
            }
            List channelDatas = new ArrayList();
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("Jdbh", emission.getJdbh());
            data.put("DEV_GUID", Md5Utils.hash(emission.getElectricityBoxId()));
            data.put("DEV_OPERATE_TIME", df.format(new Date()));
            data.put("DEV_STATUS",0);
            data.put("WRAN_TYPE", 0);
            data.put("WARN_ID", 0);

            data.put("TEMP_XL_a", emission.getAwarm());  // 线缆 a 温度
            data.put("TEMP_XL_b", emission.getBwarm());  // 线缆 b 温度
            data.put("TEMP_XL_c", emission.getCwarm());  // 线缆 c 温度
            data.put("TEMP_XL_n", emission.getNwarm()); // 线缆 n 温度
            data.put("ELEC_LIMIT", emission.getCurrent());    // 漏电流
            data.put("TEMP_HJ", emission.getEnvirwarm());     // 环境温度
            data.put("sub_id", Md5Utils.hash(emission.getProjectId().toString()));
            data.put("ChannelDatas",channelDatas);

            postData.add(data);
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("AppKey", apiKey);
            headers.put("Content-Type", "application/json; charset=utf-8");

            HttpResponse response = HttpUtils.doPost(HOST,
                    "/api/DistBox/PostReal",
                    "POST", headers, null,
                    JsonUtils.toJson(postData));

            handleResponse(apiKey, postData, response);


        }
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
