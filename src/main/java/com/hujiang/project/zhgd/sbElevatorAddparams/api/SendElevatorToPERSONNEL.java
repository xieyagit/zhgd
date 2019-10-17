package com.hujiang.project.zhgd.sbElevatorAddparams.api;

import com.hujiang.common.utils.JsonUtils;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.project.consumer.ElevatorPersonnelService;
import com.hujiang.project.consumer.TspPersonnelService;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbElevatorAddrecord.domain.SbElevatorAddrecord;
import com.hujiang.project.zhgd.sbElevatorBinding.domain.SbElevatorBinding;
import com.hujiang.project.zhgd.sbElevatorBinding.service.ISbElevatorBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Repository;

import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * 升降机上传到人才安居
 */
@Repository
public class SendElevatorToPERSONNEL {
    @Autowired
    ElevatorPersonnelService elevatorPersonnelService;

    @Autowired
    private ISbElevatorBindingService iSbElevatorBindingService;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue elevatorPersonnelQueue;

    public void rcajMachine(SbElevatorBinding SbElevatorBinding){
        String apiKey = elevatorPersonnelService.getApikey(SbElevatorBinding.getProjectId().longValue());
        if(apiKey == null || "".equals(apiKey)) {
            return;
        }
        JmsMessageInfo<SbElevatorBinding> messageMachine = new JmsMessageInfo<SbElevatorBinding>();
        messageMachine.setBody(SbElevatorBinding);
        messageMachine.setType(JmsMessageType.Machine);
        jmsMessagingTemplate.convertAndSend(elevatorPersonnelQueue, JsonUtils.toJson(messageMachine));
    }

    public void rcajDate(SbElevatorAddrecord sbElevatorAddrecord){
        ArrayList<SbElevatorAddrecord> list = null;
        String apiKey = null;
        int count = 0;
        //根据设备的编号和上传平台查询项目ID
        SbElevatorBinding sbElevatorBinding = new SbElevatorBinding();
        sbElevatorBinding.setHxzid(sbElevatorAddrecord.getHxzid());
        List<SbElevatorBinding> sbElevatorBindingList = iSbElevatorBindingService.selectSbElevatorBindingList(sbElevatorBinding);
        for(SbElevatorBinding elevatorBinding : sbElevatorBindingList){
            sbElevatorAddrecord.setJdbh(elevatorBinding.getJdbh());
            sbElevatorAddrecord.setProjectId(elevatorBinding.getProjectId().intValue());
            count++; //用于消息队列计数
            apiKey = elevatorPersonnelService.getApikey(elevatorBinding.getProjectId().longValue());

            if(list == null){
                list = new ArrayList<SbElevatorAddrecord>();
            }
            /**添加扬尘数据到列表，待发送到消息队列**/
            if(apiKey != null && !apiKey.isEmpty()) {
                list.add(sbElevatorAddrecord);
            }

            if(list.size() > 0 ) {
                JmsMessageInfo<List<SbElevatorAddrecord>> messageInfo = new JmsMessageInfo<List<SbElevatorAddrecord>>();
                messageInfo.setBody(list);
                messageInfo.setType(JmsMessageType.Data);
                jmsMessagingTemplate.convertAndSend(elevatorPersonnelQueue, JsonUtils.toJson(messageInfo));
                list = null;
            }
        }

    }
}
