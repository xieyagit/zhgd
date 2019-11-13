package com.hujiang.project.consumer;


import com.hujiang.common.utils.HttpUtils;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.common.utils.Md5Utils;
import com.hujiang.framework.config.JmsConfig;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
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
public class ElevatorPersonnelService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    public final static String PLATFORMNAME = "PERSONNEL";
    public final static String APITYPE = "keytype2";
    public final static String HOST = "http://218.17.11.171:7010";

    //log日志
    private static Logger logger = LoggerFactory.getLogger(ElevatorPersonnelService.class);

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = JmsConfig.ELEVATOR_PERSONNEL_QUEUE)
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo(JmsConfig.ELEVATOR_PERSONNEL_QUEUE + "_OUT") //双向队列
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

    /**批量上传升降机实时数据**/
    private void uploadData(JmsMessageInfo info) throws IOException {

        List<Map> postData = new ArrayList<Map>();
        ArrayList list = JsonUtils.convert(info.getBody(), ArrayList.class);


        for(Object item : list) {

            SbElevatorAddrecord emission = JsonUtils.convert(item, SbElevatorAddrecord.class);
            String apiKey = getApikey(emission.getProjectId().longValue());

            if(apiKey == null || apiKey.isEmpty()) {
                logger.error("密钥不存在！！！");
                return;
            }
            emission.setHxzid( Md5Utils.hash(emission.getHxzid()));
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("Jdbh", emission.getJdbh());
            data.put("guid", emission.getHxzid());
            data.put("lr", 1);
            data.put("zzrs",Integer.parseInt(emission.getPeopleCnt()));
            data.put("zz", emission.getLaod().intValue());
            data.put("zzb", emission.getLoadRatio());
            data.put("xm", emission.getOperatorName());
            data.put("kh", null);
            data.put("yxsk", emission.getRuntime());
            data.put("fx", 1);
            data.put("gd", emission.getHeight());
            data.put("ssbj", 1);
            data.put("zt", 0);
            data.put("zdjl", 1);
            data.put("xxw",emission.getIsUpWarning());
            data.put("sxw",emission.getIsDownWarning());
            data.put("zz_alarm",emission.getIsOverWarning());
            data.put("qxw",emission.getIsForwardWarning());
            data.put("hxw",emission.getIsBackwardWarning());
            data.put("cxw",emission.getIsLimitWarning());
            data.put("sub_id", Md5Utils.hash(emission.getProjectId().toString()));


            postData.add(data);
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("AppKey", apiKey);
            headers.put("Content-Type", "application/json; charset=utf-8");

            HttpResponse response = HttpUtils.doPost(HOST,
                    "/api/Elevator/PostReal",
                    "POST", headers, null,
                    JsonUtils.toJson(postData));

            handleResponse(apiKey, postData, response);


        }
    }

    /**上报设备**/
    private void uploadMachine(JmsMessageInfo info) throws IOException {
        SbElevatorBinding body = JsonUtils.convert(info.getBody(), SbElevatorBinding.class);
        body.setHxzId(Md5Utils.hash(body.getHxzId()));
        String apiKey = getApikey(body.getProjectId().longValue());

        if(apiKey == null || apiKey.isEmpty()) {
            logger.error("密钥不存在！！！");
            return;
        }

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("AppKey", apiKey);
        headers.put("Content-Type", "application/json; charset=utf-8");

        List<Map> postMachine = new ArrayList<Map>();
        Map<String, String> data = new HashMap<String, String>();
        data.put("Jdbh", body.getJdbh());
        data.put("Dev_GUID", body.getHxzId());
        data.put("Dev_UID", "4018");
        data.put("Dev_Name", body.getDname());
        data.put("Dev_Model", null);  //设备型号
        data.put("License", null);
        data.put("License_SDate", null);
        data.put("License_EDate", null);
        data.put("Com_cqdw", null);
        data.put("Com_zzs", "德业电子");
        data.put("Com_wds", "德业电子");

        data.put("Com_jcs", "德业电子");
        data.put("Dev_InDate", null);
        data.put("Dev_outDate", null);
        data.put("Aqq_ValidDate",null);
        data.put("Jc_dev_company", body.getInstallCompany());
        data.put("Jc_dev_install_date", null);
        data.put("Serial_Num", body.getSerialNum());
        data.put("sub_id", Md5Utils.hash(body.getProjectId().toString()));
        postMachine.add(data);

        List<Map> postParameters = new ArrayList<Map>();
        Map<String, Object> dataTwo = new HashMap<String, Object>();
        dataTwo.put("Jdbh", body.getJdbh());
        dataTwo.put("L_DGUID", body.getHxzId());
        dataTwo.put("L_Load_Capacity", body.getCapacity().toString());
        dataTwo.put("L_Height", body.getHeight().toString());
        dataTwo.put("L_Height2", null);
        dataTwo.put("L_UpCollect", null);
        dataTwo.put("L_DownCollection", null);
        dataTwo.put("L_GearModules", 12);
        dataTwo.put("L_LowLimit", null);
        dataTwo.put("L_M_Contract", null);
        dataTwo.put("L_S_Contract", null);
        dataTwo.put("L_MonitorStyle", null);
        dataTwo.put("L_Sections", 25);
        dataTwo.put("L_ControlStyle", 1);
        dataTwo.put("L_Limit_Capacity", 35);
        dataTwo.put("sub_id", Md5Utils.hash(body.getProjectId().toString()));

        postParameters.add(dataTwo);

        HttpResponse responseMachine = HttpUtils.doPost(HOST,
                "/api/Elevator/post",
                "POST", headers, null,
                JsonUtils.toJson(postMachine));
        handleResponse(apiKey, postMachine, responseMachine);

        HttpResponse responseParameters = HttpUtils.doPost(HOST,
                "/api/Elevator/PostParams",
                "POST", headers, null,
                JsonUtils.toJson(postParameters));

        handleResponse(apiKey, postParameters, responseParameters);
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
