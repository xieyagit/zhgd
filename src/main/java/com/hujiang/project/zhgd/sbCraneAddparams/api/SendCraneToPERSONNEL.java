package com.hujiang.project.zhgd.sbCraneAddparams.api;

import com.hujiang.common.utils.JsonUtils;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.project.consumer.CranePersonnelService;
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

    @Autowired
    private Queue craneCayQueue;

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

    public void cayDate(SbCraneAddrecord sbCraneAddrecord){
        ArrayList<SbCraneAddrecord> list = null;
        int count = 0;
        //根据设备的编号和上传平台查询项目ID
        SbCraneBinding sbCraneBinding = new SbCraneBinding();
        sbCraneBinding.setHxzid(sbCraneAddrecord.getHxzid());
        sbCraneBinding.setScznl("CAY");
        List<SbCraneBinding> sbCraneBindingList = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
        for(SbCraneBinding craneBinding : sbCraneBindingList){
            sbCraneAddrecord.setJdbh(craneBinding.getJdbh());
            sbCraneAddrecord.setProjectId(craneBinding.getPid());
            sbCraneAddrecord.setXmid(craneBinding.getXmid());
            sbCraneAddrecord.setSubId(craneBinding.getSubId());
            count++; //用于消息队列计数

            if(list == null){
                list = new ArrayList<SbCraneAddrecord>();
            }
            /**添加塔吊数据到列表，待发送到消息队列**/
            if (craneBinding.getHxzid() != "CAY" && craneBinding.getJdbh() != null && craneBinding.getXmid()!=null) {
                list.add(sbCraneAddrecord);
            }
            if(list.size() > 0 ) {
                JmsMessageInfo<List<SbCraneAddrecord>> messageInfo = new JmsMessageInfo<List<SbCraneAddrecord>>();
                messageInfo.setBody(list);
                messageInfo.setType(JmsMessageType.Data);
                jmsMessagingTemplate.convertAndSend(craneCayQueue, JsonUtils.toJson(messageInfo));
                list = null;
            }
        }
    }
    public void cayWarning(SbCraneAddrecord sbCraneAddrecord){
        ArrayList<SbCraneAddrecord> list = null;
        int count = 0;
        //根据设备的编号和上传平台查询项目ID
        SbCraneBinding sbCraneBinding = new SbCraneBinding();
        sbCraneBinding.setHxzid(sbCraneAddrecord.getHxzid());
        sbCraneBinding.setScznl("CAY");
        List<SbCraneBinding> sbCraneBindingList = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
        for(SbCraneBinding craneBinding : sbCraneBindingList){
            sbCraneAddrecord.setJdbh(craneBinding.getJdbh());
            sbCraneAddrecord.setProjectId(craneBinding.getPid());
            sbCraneAddrecord.setXmid(craneBinding.getXmid());
            sbCraneAddrecord.setSubId(craneBinding.getSubId());
            count++; //用于消息队列计数

            if(list == null){
                list = new ArrayList<SbCraneAddrecord>();
            }
            /**添加塔吊数据到列表，待发送到消息队列**/
            if (sbCraneAddrecord.getHxzid() != "CAY" && sbCraneAddrecord.getJdbh() != null && sbCraneAddrecord.getXmid()!=null) {
                list.add(sbCraneAddrecord);
            }
            if(list.size() > 0 ) {
                JmsMessageInfo<List<SbCraneAddrecord>> messageInfo = new JmsMessageInfo<List<SbCraneAddrecord>>();
                messageInfo.setBody(list);
                messageInfo.setType(JmsMessageType.WARNING);
                jmsMessagingTemplate.convertAndSend(craneCayQueue, JsonUtils.toJson(messageInfo));
                list = null;
            }
        }
    }
    public void cayElectrify(SbCraneAddrecord sbCraneAddrecord){
        ArrayList<SbCraneAddrecord> list = null;
        int count = 0;
        //根据设备的编号和上传平台查询项目ID
        SbCraneBinding sbCraneBinding = new SbCraneBinding();
        sbCraneBinding.setHxzid(sbCraneAddrecord.getHxzid());
        sbCraneBinding.setScznl("CAY");
        List<SbCraneBinding> sbCraneBindingList = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
        for(SbCraneBinding craneBinding : sbCraneBindingList){
            sbCraneAddrecord.setJdbh(craneBinding.getJdbh());
            sbCraneAddrecord.setProjectId(craneBinding.getPid());
            sbCraneAddrecord.setXmid(craneBinding.getXmid());
            sbCraneAddrecord.setSubId(craneBinding.getSubId());
            count++; //用于消息队列计数

            if(list == null){
                list = new ArrayList<>();
            }
            /**添加塔吊运行时长数据到列表，待发送到消息队列**/
            if (craneBinding.getHxzid() != "CAY" && craneBinding.getJdbh() != null && craneBinding.getXmid()!=null) {
                list.add(sbCraneAddrecord);
            }
            if(list.size() > 0 ) {
                JmsMessageInfo<ArrayList<SbCraneAddrecord>> messageInfo = new JmsMessageInfo<>();
                messageInfo.setBody(list);
                messageInfo.setType(JmsMessageType.Electrify);
                jmsMessagingTemplate.convertAndSend(craneCayQueue, JsonUtils.toJson(messageInfo));
                list = null;
            }
        }
    }

    public void cayWorkCycle(SbCraneAddrecord sbCraneAddrecord){
        ArrayList<SbCraneAddrecord> list = null;
        int count = 0;
        //根据设备的编号和上传平台查询项目ID
        SbCraneBinding sbCraneBinding = new SbCraneBinding();
        sbCraneBinding.setHxzid(sbCraneAddrecord.getHxzid());
        sbCraneBinding.setScznl("CAY");
        List<SbCraneBinding> sbCraneBindingList = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
        for(SbCraneBinding craneBinding : sbCraneBindingList){
            sbCraneAddrecord.setJdbh(craneBinding.getJdbh());
            sbCraneAddrecord.setProjectId(craneBinding.getPid());
            sbCraneAddrecord.setXmid(craneBinding.getXmid());
            sbCraneAddrecord.setSubId(craneBinding.getSubId());
            count++; //用于消息队列计数

            if(list == null){
                list = new ArrayList<>();
            }
            /**添加塔吊运行时长数据到列表，待发送到消息队列**/
            if (craneBinding.getHxzid() != "CAY" && craneBinding.getJdbh() != null && craneBinding.getXmid()!=null) {
                list.add(sbCraneAddrecord);
            }
            if(list.size() > 0 ) {
                JmsMessageInfo<ArrayList<SbCraneAddrecord>> messageInfo = new JmsMessageInfo<>();
                messageInfo.setBody(list);
                messageInfo.setType(JmsMessageType.WorkCycle);
                jmsMessagingTemplate.convertAndSend(craneCayQueue, JsonUtils.toJson(messageInfo));
                list = null;
            }
        }
    }

    public void cayPunchTheClock(SbCraneAddrecord sbCraneAddrecord){
        ArrayList<SbCraneAddrecord> list = null;
        int count = 0;
        //根据设备的编号和上传平台查询项目ID
        SbCraneBinding sbCraneBinding = new SbCraneBinding();
        sbCraneBinding.setHxzid(sbCraneAddrecord.getHxzid());
        sbCraneBinding.setScznl("CAY");
        List<SbCraneBinding> sbCraneBindingList = iSbCraneBindingService.selectSbCraneBindingList(sbCraneBinding);
        for(SbCraneBinding craneBinding : sbCraneBindingList){
            sbCraneAddrecord.setJdbh(craneBinding.getJdbh());
            sbCraneAddrecord.setProjectId(craneBinding.getPid());
            sbCraneAddrecord.setXmid(craneBinding.getXmid());
            sbCraneAddrecord.setSubId(craneBinding.getSubId());
            count++; //用于消息队列计数

            if(list == null){
                list = new ArrayList<>();
            }
            /**添加塔吊运行时长数据到列表，待发送到消息队列**/
            if (craneBinding.getHxzid() != "CAY" && craneBinding.getJdbh() != null && craneBinding.getXmid()!=null) {
                list.add(sbCraneAddrecord);
            }
            if(list.size() > 0 ) {
                JmsMessageInfo<ArrayList<SbCraneAddrecord>> messageInfo = new JmsMessageInfo<>();
                messageInfo.setBody(list);
                messageInfo.setType(JmsMessageType.PunchTheClock);
                jmsMessagingTemplate.convertAndSend(craneCayQueue, JsonUtils.toJson(messageInfo));
                list = null;
            }
        }
    }
}
