//package com.hujiang.project.haiKang;
//
//import com.alibaba.fastjson.JSONObject;
//import com.hikvision.building.cloud.Consumer;
//import com.hikvision.building.cloud.http.Message;
//import com.hikvision.building.cloud.service.IPushMessageService;
//import com.hujiang.project.zhgd.sbCamera.service.ISbCameraService;
//import com.hujiang.project.zhgd.sbCameraInformation.domain.SbCameraInformation;
//import com.hujiang.project.zhgd.sbCameraInformation.service.ISbCameraInformationService;
//import com.hujiang.project.zhgd.utils.Tools;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class HaiKangAI implements IPushMessageService {
//
//    @Autowired
//    private ISbCameraInformationService iSbCameraInformationService;
//    @Override
//    public void pushMessage(List<Message> messageList) {
//        // 异步处理消费到的消息
//        try{
//            List<SbCameraInformation> informations = new ArrayList<SbCameraInformation>(messageList.size());
//            for (int i = 0 ; i<messageList.size();i++){
//                SbCameraInformation cameraInformation = new SbCameraInformation();
//                cameraInformation.setMsgId(messageList.get(i).getMsgId());
//                JSONObject object = JSONObject.parseObject(messageList.get(i).getContent());
//                cameraInformation.setTraceId(object.getString("traceId"));
//                JSONObject object1 = JSONObject.parseObject(object.getString("taskInfo"));
//                cameraInformation.setTaskId(object1.getString("taskId"));
//                cameraInformation.setTaskName(object1.getString("taskName"));
//                cameraInformation.setTaskType(object1.getString("taskType"));
//                cameraInformation.setTrainId(object1.getString("trainId"));
//                object1 = JSONObject.parseObject(object.getString("captureDestination"));
//                cameraInformation.setChannelId(object1.getString("channelId"));
//                cameraInformation.setDeviceId(object1.getString("deviceId"));
//                cameraInformation.setDeviceSerial(object1.getString("deviceSerial"));
//                cameraInformation.setDeviceName(object1.getString("deviceName"));
//                cameraInformation.setChannelNo(object1.getInteger("channelNo"));
//                cameraInformation.setChannelName(object1.getString("channelName"));
//                cameraInformation.setGroupId(object1.getString("groupId"));
//                cameraInformation.setGroupName(object1.getString("groupName"));
//                object1 = JSONObject.parseObject(object.getString("captureResult"));
//                String s = Tools.timeStamp2Date(object1.getInteger("captureTime"));
//                cameraInformation.setCaptureTime(object1.getString("captureTime"));
//                cameraInformation.setResultUrl(object1.getString("resultUrl"));
//                object1 = JSONObject.parseObject(object.getString("algorithmResult"));
//                cameraInformation.setWidth(object1.getString("width"));
//                cameraInformation.setHeight(object1.getString("height"));
//                object1 = JSONObject.parseObject(object.getString("ruleResult"));
//                cameraInformation.setRuleId(object1.getString("ruleId"));
//                cameraInformation.setRuleName(object1.getString("ruleName"));
//                informations.add(cameraInformation);
//            }
//            int i = iSbCameraInformationService.insertInformation(informations);
//            if (i>0){
//                System.out.println("成功");
//            } else {
//                System.out.println("失败");
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void a (){
//        // 开发者账号信息，使用云眸管理员账号登录open2.hik-cloud.com，进入秘钥管理页面获取
//        String clientId = "2d0974a7d6ef47d1b9e736053c721680";
//        String clientSecret = "e38877b0d87745e1b67271c739a2a64f";
//        Consumer consumer = new Consumer(clientId, clientSecret, this);
//        // 启动消费线程
//        consumer.run();
//    }
//}
