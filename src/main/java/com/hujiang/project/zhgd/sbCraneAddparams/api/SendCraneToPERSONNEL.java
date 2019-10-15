package com.hujiang.project.zhgd.sbCraneAddparams.api;

import com.hujiang.common.utils.JsonUtils;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.project.consumer.CranePersonnelService;
import com.hujiang.project.consumer.TspPersonnelService;
import com.hujiang.project.zhgd.sbCraneAddrecord.domain.SbCraneAddrecord;
import com.hujiang.project.zhgd.sbCraneBinding.domain.SbCraneBinding;
import com.hujiang.project.zhgd.sbCraneBinding.service.ISbCraneBindingService;
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
public class SendCraneToPERSONNEL {
    @Autowired
    CranePersonnelService cranePersonnelService;

    @Autowired
    private ISbCraneBindingService iSbCraneBindingService;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue cranePersonnelQueue;

    public void rcajMachine(SbCraneBinding sbCraneBinding){
        String apiKey = cranePersonnelService.getApikey(sbCraneBinding.getPid().longValue());
        if(apiKey == null || "".equals(apiKey)) {
            return;
        }
        JmsMessageInfo<SbCraneBinding> messageMachine = new JmsMessageInfo<SbCraneBinding>();
        messageMachine.setBody(sbCraneBinding);
        messageMachine.setType(JmsMessageType.Machine);
        jmsMessagingTemplate.convertAndSend(cranePersonnelQueue, JsonUtils.toJson(messageMachine));
    }

    public void rcajDate(SbCraneAddrecord sbCraneAddrecord){
        ArrayList<SbCraneAddrecord> list = null;
        String apiKey = null;
        int count = 0;
        //根据设备的编号和上传平台查询项目ID
        SbCraneBinding sbCraneBinding = new SbCraneBinding();
        sbCraneBinding.setHxzid(sbCraneAddrecord.getHxzid());
        List<SbCraneBinding> sbCraneBindingList = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
        for(SbCraneBinding craneBinding : sbCraneBindingList){
            sbCraneAddrecord.setJdbh(craneBinding.getJdbh());
            sbCraneAddrecord.setProjectId(craneBinding.getPid());
            count++; //用于消息队列计数
            apiKey = cranePersonnelService.getApikey(craneBinding.getPid().longValue());

            if(list == null){
                list = new ArrayList<SbCraneAddrecord>();
            }
            /**添加塔吊数据到列表，待发送到消息队列**/
            if(apiKey != null && !apiKey.isEmpty()) {
                list.add(sbCraneAddrecord);
            }

            if(list.size() > 0 ) {
                JmsMessageInfo<List<SbCraneAddrecord>> messageInfo = new JmsMessageInfo<List<SbCraneAddrecord>>();
                messageInfo.setBody(list);
                messageInfo.setType(JmsMessageType.Data);
                jmsMessagingTemplate.convertAndSend(cranePersonnelQueue, JsonUtils.toJson(messageInfo));
                list = null;
            }
        }

    }
}
