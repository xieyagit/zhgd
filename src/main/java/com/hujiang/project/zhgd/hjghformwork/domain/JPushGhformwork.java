package com.hujiang.project.zhgd.hjghformwork.domain;


import com.hujiang.project.zhgd.jpush.api.examples.PushExample;
import com.hujiang.project.zhgd.jpush.api.push.model.PushPayload;
import com.hujiang.project.zhgd.sbDustEmission.domain.BaseSmsMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JPushGhformwork extends BaseSmsMessage<JPushGhformwork> {

    //高支模模块权限
    private static final  int HGPRIVILEGE=19;
    //短信模板ID
    private static final int TEMPID = 169708;

    private String title;
    private String works;
    private String time;
    private String alarmType;
    private String alarmLevel;
    private String alarmDetails;
    private String sn;

    public JPushGhformwork(){
        super();
        setPrivilegesId(HGPRIVILEGE);
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorks() {
        return works;
    }

    public void setWorks(String works) {
        this.works = works;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmDetails() {
        return alarmDetails;
    }

    public void setAlarmDetails(String alarmDetails) {
        this.alarmDetails = alarmDetails;
    }

    @Override
    public void push(Integer userId, Integer projectId, String alias, boolean isApnsProduction, String userPhone) {
        Map<String,String> extras = new HashMap<>();
        extras.put("skip", String.valueOf(HGPRIVILEGE));
        extras.put("append","");
        Map<String, String> tempPara = null;
        tempPara = new HashMap<>();
        tempPara.put("title",this.title);//title
        tempPara.put("works",this.works);  //结构物名称
        tempPara.put("time", this.time);  //时间
        tempPara.put("alarmType", this.alarmType);  //测点
        tempPara.put("alarmLevel", this.alarmLevel);  //报警等级
        tempPara.put("alarmDetails", this.alarmDetails);  //报警等级
        tempPara.put("sn", this.sn);  //报警等级

        //APP推送消息
        PushExample pushExample = new PushExample();
        PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
                ("您有一条新的建筑物异常记录", alias, "高支模监测", false,extras);
        pushExample.testSendPush(payload);
        //将推送消息保存到数据库
        this.saveExcessive(this.title,this.sn, projectId, userId, "高支模设备", HGPRIVILEGE);

        //发送短信
        this.shortCreedNumber(userPhone, projectId, userId, tempPara,TEMPID,"高支模超标");
    }
}
