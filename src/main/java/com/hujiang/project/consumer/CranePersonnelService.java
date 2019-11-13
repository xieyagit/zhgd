package com.hujiang.project.consumer;


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
public class CranePersonnelService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;

    public final static String PLATFORMNAME = "PERSONNEL";
    public final static String APITYPE = "keytype2";
    public final static String HOST = "http://218.17.11.171:7010";

    //log日志
    private static Logger logger = LoggerFactory.getLogger(CranePersonnelService.class);

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = JmsConfig.CRANE_PERSONNEL_QUEUE)
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo(JmsConfig.CRANE_PERSONNEL_QUEUE + "_OUT") //双向队列
    public String handleMessage(String message){
        try {
            JmsMessageInfo info = JsonUtils.parse(message, JmsMessageInfo.class);
            if(JmsMessageType.Machine.equals(info.getType())) {
                uploadMachine(info);
            }

            if(JmsMessageType.Data.equals(info.getType())) {
                uploadData(info);
            }
        }
        catch (IOException e) {
            return message;
        }
        return null;
    }

    /**批量上传塔吊实时数据**/
    private void uploadData(JmsMessageInfo info) throws IOException {

        List<Map> postData = new ArrayList<Map>();
        ArrayList list = JsonUtils.convert(info.getBody(), ArrayList.class);


        for(Object item : list) {

            SbCraneAddrecord sbCraneAddrecord = JsonUtils.convert(item, SbCraneAddrecord.class);
            String apiKey = getApikey(sbCraneAddrecord.getProjectId().longValue());

            if(apiKey == null || apiKey.isEmpty()) {
                logger.error("密钥不存在！！！");
                return;
            }
            sbCraneAddrecord.setHxzid( Md5Utils.hash(sbCraneAddrecord.getHxzid()));
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("Jdbh", sbCraneAddrecord.getJdbh());
            data.put("GUID", sbCraneAddrecord.getHxzid());
            data.put("yxsk", sbCraneAddrecord.getRuntime());
            data.put("zz",sbCraneAddrecord.getLoad().intValue());
            data.put("zzb", sbCraneAddrecord.getLoadRatio());
            data.put("lj", sbCraneAddrecord.getMoment());
            data.put("ljb", sbCraneAddrecord.getMomentRatio());
            data.put("jd", sbCraneAddrecord.getSlewingSpeed());
            data.put("zxw", sbCraneAddrecord.getIsLeftWarning());
            data.put("yxw", sbCraneAddrecord.getIsRightWarning());
            data.put("fd", sbCraneAddrecord.getRange());
            data.put("qxw", sbCraneAddrecord.getIsForwardWarning());
            data.put("hxw", sbCraneAddrecord.getIsBackwardWarning());
            data.put("gd",sbCraneAddrecord.getHeight());
            data.put("sxw",sbCraneAddrecord.getIsUpWarning());
            data.put("bl",sbCraneAddrecord.getMagnification());
            data.put("hj",1);
            data.put("ssbj",sbCraneAddrecord.getRealTimeFlag());
            data.put("azfs",sbCraneAddrecord.getInstallationMethod());
            data.put("fs",sbCraneAddrecord.getWindSpeed());
            data.put("kh",sbCraneAddrecord.getCardNum());
            data.put("xm",sbCraneAddrecord.getOperatorName());
            data.put("hzxw",0);
            data.put("fpz",sbCraneAddrecord.getMultiAlarmAll());
            data.put("cfszy",sbCraneAddrecord.getWindSpeed().intValue());
            data.put("sub_id",Md5Utils.hash(sbCraneAddrecord.getProjectId().toString()));

            postData.add(data);
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("AppKey", apiKey);
            headers.put("Content-Type", "application/json; charset=utf-8");

            HttpResponse response = HttpUtils.doPost(HOST,
                    "/api/Tower/PostReal",
                    "POST", headers, null,
                    JsonUtils.toJson(postData));

            handleResponse(apiKey, postData, response);


        }


    }

    /**上报设备**/
    private void uploadMachine(JmsMessageInfo info) throws IOException {
        SbCraneBinding body = JsonUtils.convert(info.getBody(), SbCraneBinding.class);
        body.setHxzid( Md5Utils.hash(body.getHxzid()));
        String apiKey = getApikey(body.getPid().longValue());

        if(apiKey == null || apiKey.isEmpty()) {
            logger.error("密钥不存在！！！");
            return;
        }

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("AppKey", apiKey);
        headers.put("Content-Type", "application/json; charset=utf-8");




        //塔机设备基本信息
        Map<String, Object> dataTwo = new HashMap<String, Object>();
        dataTwo.put("Jdbh", body.getJdbh());
        dataTwo.put("Dev_GUID", body.getHxzid());
        dataTwo.put("Dev_UID", "4018");
        dataTwo.put("Dev_Name", body.getDname());
        dataTwo.put("Dev_Model", null);
        dataTwo.put("License", null);
        dataTwo.put("License_Date", null);
        dataTwo.put("License_EDate", null);
        dataTwo.put("Dev_Photo", null);
        dataTwo.put("Com_cqdw", null);
        dataTwo.put("Com_zzs", null);
        dataTwo.put("Com_wds", null);
        dataTwo.put("Com_jcs", null);
        dataTwo.put("Dev_InDate", null);
        dataTwo.put("Dev_outDate", null);

        dataTwo.put("Jc_dev_company", "德业电子");
        dataTwo.put("Jc_dev_install_date", null);
        dataTwo.put("Serial_Num", body.getSerialNum());

        dataTwo.put("sub_id", Md5Utils.hash(body.getPid().toString()));
        List<Map> postMachine = new ArrayList<Map>();
        postMachine.add(dataTwo);


       //塔机参数
        Map<String, Object> data = new HashMap<String,Object >();
        data.put("Jdbh", body.getJdbh());
        data.put("TC_GUID", body.getHxzid());
        data.put("TC_MaxScope", body.getTcMaxScope().intValue());
        data.put("TC_SB_SpeedCut", 3);
        data.put("TC_SA_SpeedCut", 63);  //设备型号
        data.put("TC_MaxHeight", body.getTcMaxHeight());
        data.put("TC_MAX_XB_HEIGHT", 12);
        data.put("TC_MinHeight", 99.5);
        data.put("TC_HU_SpeedCut", 0);
        data.put("TC_MinScope", 97);
        data.put("TC_HB_SpeedCut", 2.5);

        data.put("TC_LoadCapacity", body.getTcLoadCapacity());
        data.put("TC_MS_LoadCapacity", 1300);
        data.put("TC_ML_MaxScope", 27);
        data.put("TC_Multiple",53);
        data.put("Tower_type",2);
        data.put("tc_load_moment", body.getTcLoadMoment());
        data.put("sub_id", Md5Utils.hash(body.getPid().toString()));
        List<Map> postParameters = new ArrayList<Map>();
        postParameters.add(data);


        HttpResponse responseMachine = HttpUtils.doPost(HOST,
                "/api/Tower/post",
                "POST", headers, null,
                JsonUtils.toJson(postMachine));
        handleResponse(apiKey, postMachine, responseMachine);

        HttpResponse responseParameters = HttpUtils.doPost(HOST,
                "/api/Tower/PostParams",
                "POST", headers, null,
                JsonUtils.toJson(postParameters));

        handleResponse(apiKey, postParameters, responseParameters);
    }



    /**响应接口请求**/
    private String handleResponse(String apiKey, List<Map> postData, HttpResponse response) throws IOException {

        String errorMessage = null;
        if(response.getStatusLine().getStatusCode() != 200) {
            errorMessage = String.format("人才安居接口上传设备失败！appkey:%s，参数：%s", apiKey, JsonUtils.toJson(postData));
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
