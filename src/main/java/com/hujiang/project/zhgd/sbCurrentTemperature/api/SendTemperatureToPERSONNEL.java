package com.hujiang.project.zhgd.sbCurrentTemperature.api;

import com.hujiang.common.utils.JsonUtils;
import com.hujiang.common.utils.Md5Utils;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.project.consumer.TemperaturePersonnelService;
import com.hujiang.project.consumer.TspPersonnelService;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import com.hujiang.project.zhgd.sbElevatorBinding.service.ISbElevatorBindingService;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import com.hujiang.project.zhgd.sbProjectElectricityBox.service.ISbProjectElectricityBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Repository;

import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * 电箱上传到人才安居
 */
@Repository
public class SendTemperatureToPERSONNEL {
    @Autowired
    TemperaturePersonnelService temperaturePersonnelService;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private ISbProjectElectricityBoxService iSbProjectElectricityBoxService;

    @Autowired
    private Queue temperaturePersonnelQueue;
    @Autowired
    private Queue temperatureCayQueue;

    public void rcajMachine(SbProjectElectricityBox sbProjectElectricityBox){
        String apiKey = temperaturePersonnelService.getApikey(sbProjectElectricityBox.getProjectId().longValue());
        if(apiKey == null || "".equals(apiKey)) {
            return;
        }
        JmsMessageInfo<SbProjectElectricityBox> messageMachine = new JmsMessageInfo<SbProjectElectricityBox>();
        messageMachine.setBody(sbProjectElectricityBox);
        messageMachine.setType(JmsMessageType.Machine);
        jmsMessagingTemplate.convertAndSend(temperaturePersonnelQueue, JsonUtils.toJson(messageMachine));
    }

    public void rcajDate(SbCurrentTemperature sbCurrentTemperature){
        ArrayList<SbCurrentTemperature> list = null;
        String apiKey = null;
        int count = 0;
        //根据设备的编号和上传平台查询项目ID
        SbProjectElectricityBox sbProjectElectricityBox = new SbProjectElectricityBox();
        sbProjectElectricityBox.setElectricityBoxId(sbCurrentTemperature.getElectricityBoxId());
        List<SbProjectElectricityBox> sbProjectElectricityBoxList = iSbProjectElectricityBoxService.selectSbProjectElectricityBoxList(sbProjectElectricityBox);

        for(SbProjectElectricityBox projectElectricityBox : sbProjectElectricityBoxList){
            sbCurrentTemperature.setJdbh(projectElectricityBox.getJdbh());
            sbCurrentTemperature.setProjectId(projectElectricityBox.getProjectId().intValue());
            sbCurrentTemperature.setElectricityBoxId(sbCurrentTemperature.getElectricityBoxId());
            count++; //用于消息队列计数
            apiKey = temperaturePersonnelService.getApikey(projectElectricityBox.getProjectId().longValue());

            if(list == null){
                list = new ArrayList<SbCurrentTemperature>();
            }
            /**添加扬尘数据到列表，待发送到消息队列**/
            if(apiKey != null && !apiKey.isEmpty()) {
                list.add(sbCurrentTemperature);
            }

            if(list.size() > 0 ) {
                JmsMessageInfo<List<SbCurrentTemperature>> messageInfo = new JmsMessageInfo<List<SbCurrentTemperature>>();
                messageInfo.setBody(list);
                messageInfo.setType(JmsMessageType.Data);
                jmsMessagingTemplate.convertAndSend(temperaturePersonnelQueue, JsonUtils.toJson(messageInfo));
                list = null;
            }
        }
    }
    public void cayMachine(SbProjectElectricityBox sbProjectElectricityBox){
        if(sbProjectElectricityBox.getJdbh() == null || sbProjectElectricityBox.getXmid() == null) {
            return;
        }
        JmsMessageInfo<SbProjectElectricityBox> messageMachine = new JmsMessageInfo<SbProjectElectricityBox>();
        messageMachine.setBody(sbProjectElectricityBox);
        messageMachine.setType(JmsMessageType.Machine);
        jmsMessagingTemplate.convertAndSend(temperatureCayQueue, JsonUtils.toJson(messageMachine));
    }
    public void cayDate(SbCurrentTemperature sbCurrentTemperature){
        ArrayList<SbCurrentTemperature> list = null;
        int count = 0;
        //根据设备的编号和上传平台查询项目ID
        SbProjectElectricityBox sbProjectElectricityBox = new SbProjectElectricityBox();
        sbProjectElectricityBox.setElectricityBoxId(sbCurrentTemperature.getElectricityBoxId());
        List<SbProjectElectricityBox> sbProjectElectricityBoxList = iSbProjectElectricityBoxService.selectSbProjectElectricityBoxList(sbProjectElectricityBox);

        for(SbProjectElectricityBox projectElectricityBox : sbProjectElectricityBoxList){
            sbCurrentTemperature.setJdbh(projectElectricityBox.getJdbh());
            sbCurrentTemperature.setProjectId(projectElectricityBox.getProjectId().intValue());
            sbCurrentTemperature.setElectricityBoxId(sbCurrentTemperature.getElectricityBoxId());
            count++; //用于消息队列计数

            if(list == null){
                list = new ArrayList<SbCurrentTemperature>();
            }
            /**添加扬尘数据到列表，待发送到消息队列**/
            if(projectElectricityBox.getJdbh() != null && projectElectricityBox.getXmid() != null) {
                list.add(sbCurrentTemperature);
            }

            if(list.size() > 0 ) {
                JmsMessageInfo<List<SbCurrentTemperature>> messageInfo = new JmsMessageInfo<List<SbCurrentTemperature>>();
                messageInfo.setBody(list);
                messageInfo.setType(JmsMessageType.Data);
                jmsMessagingTemplate.convertAndSend(temperatureCayQueue, JsonUtils.toJson(messageInfo));
                list = null;
            }
        }
    }
}