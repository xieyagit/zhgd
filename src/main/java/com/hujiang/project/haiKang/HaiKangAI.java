package com.hujiang.project.haiKang;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.building.cloud.Consumer;
import com.hikvision.building.cloud.http.Message;
import com.hikvision.building.cloud.service.IPushMessageService;
import com.hujiang.project.zhgd.sbCamera.service.ISbCameraService;
import com.hujiang.project.zhgd.sbCameraInformation.domain.SbCameraInformation;
import com.hujiang.project.zhgd.sbCameraInformation.service.ISbCameraInformationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class HaiKangAI implements IPushMessageService {

    @Autowired
    private ISbCameraInformationService iSbCameraInformationService;
    @Override
    public void pushMessage(List<Message> messageList) {
        // TODO Auto-generated method stub
        // 异步处理消费到的消息
        try{
            List<SbCameraInformation> informations = new ArrayList<>();
            for (int i = 0 ; i>messageList.size();i++){
                informations.get(i).setMsgId(messageList.get(i).getMsgId());
                JSONObject object = JSONObject.parseObject(messageList.get(i).getContent());
                informations.get(i).setTraceId(object.getString("traceId"));
                informations.get(i).setTaskId(object.getString("taskId"));
                informations.get(i).setTaskName(object.getString("taskName"));
                informations.get(i).setTaskType(object.getString("taskType"));
                informations.get(i).setTrainId(object.getString("trainId"));
                informations.get(i).setChannelId(object.getString("channelId"));
                informations.get(i).setDeviceId(object.getString("deviceId"));
                informations.get(i).setDeviceSerial(object.getString("deviceSerial"));
                informations.get(i).setDeviceName(object.getString("deviceName"));
                informations.get(i).setChannelNo(object.getInteger("channelNo"));
                informations.get(i).setChannelName(object.getString("channelName"));
                informations.get(i).setGroupId(object.getString("groupId"));
                informations.get(i).setGroupName(object.getString("groupName"));
                informations.get(i).setCaptureTime(object.getInteger("captureTime"));
                informations.get(i).setResultUrl(object.getString("resultUrl"));
                informations.get(i).setWidth(object.getString("width"));
                informations.get(i).setHeight(object.getString("height"));
                informations.get(i).setModelID(object.getString("modelID"));
                informations.get(i).setTargetId(object.getInteger("targetId"));
                informations.get(i).setType(object.getInteger("type"));
                informations.get(i).setConfidence(object.getInteger("confidence"));
                informations.get(i).setName(object.getString("name"));
                informations.get(i).setRect(object.getString("rect"));
                informations.get(i).setProperties(object.getString("properties"));
                informations.get(i).setClassify(object.getString("classify"));
                informations.get(i).setAttrType(object.getString("attrType"));
                informations.get(i).setTypeName(object.getString("typeName"));
                informations.get(i).setAttrValue(object.getInteger("attrValue"));
                informations.get(i).setValueName(object.getString("valueName"));
                informations.get(i).setAttrConf(object.getString("attrConf"));
                informations.get(i).setRuleId(object.getInteger("ruleId"));
                informations.get(i).setRuleName(object.getString("ruleName"));
            }
            int i = iSbCameraInformationService.insertInformation(informations);
            if (i>0){
                System.out.println("成功");
            } else {
                System.out.println("失败");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void a (){
        // 开发者账号信息，使用云眸管理员账号登录open2.hik-cloud.com，进入秘钥管理页面获取
        String clientId = "2d0974a7d6ef47d1b9e736053c721680";
        String clientSecret = "c656ccac5a53492eb20352d2fc308b33";
        Consumer consumer = new Consumer(clientId, clientSecret, new HaiKangAI());
        // 启动消费线程
        consumer.run();
    }
}
