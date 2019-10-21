package com.hujiang.project.zhgd.sbEquipmentWarning.domain;

import com.hujiang.project.zhgd.jpush.api.examples.PushExample;
import com.hujiang.project.zhgd.jpush.api.push.model.PushPayload;
import com.hujiang.project.zhgd.sbDustEmission.domain.BaseSmsMessage;
import com.hujiang.project.zhgd.sbProjectDustEmission.task.SmsMessageInfo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * 定位报警表 sb_equipment_warning
 * 
 * @author hujiang
 * @date 2019-10-19
 */
@Component
public class SbEquipmentWarning extends BaseSmsMessage<SbEquipmentWarning>
{
	/** 人员定位模块权限 */
	private static final Integer PRIVILEGESID=3;
	/** 推送弹窗标题 */
    private static final String ALTER="您有一条新的报警记录";
    /** 短信推送标题 */
    private static final  String TITLE = "定位器报警检测";
    /** 定位短信模板ID */
    private static final Integer TEMPID = 171285;

	/** 自增ID */
	private Integer id;
	/** 设备imei：关联设备表id */
	private String imei;
	/** 报警类型 0 sos  1 摔跌  2 长时间未移动  3 低电量 */
	private Integer warningType;
	/** 报警时间 */
	private String warningTime;
	/** 項目ID*/
	private Integer projectId;
    private String startTime;
    private String endTime;

	/** 工业区ID*/
	private Integer areaId;
	/** 工业区名称*/
	private String areaName;
	/** 项目人员ID*/
	private Integer userId;
	/** 项目人员名称*/
	private String userName;
	/** 项目负责人电话*/
	private String userPhone;

	private Integer adminId;

    public SbEquipmentWarning() {
        super();
        setPrivilegesId(PRIVILEGESID);
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setImei(String imei) 
	{
		this.imei = imei;
	}

	public String getImei() 
	{
		return imei;
	}
	public void setWarningType(Integer warningType)
	{
		this.warningType = warningType;
	}

	public Integer getWarningType()
	{
		return warningType;
	}
	public void setWarningTime(String warningTime)
	{
		this.warningTime = warningTime;
	}

	public String getWarningTime()
	{
		return warningTime;
	}

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "SbEquipmentWarning{" +
                "id=" + id +
                ", imei='" + imei + '\'' +
                ", warningType=" + warningType +
                ", warningTime='" + warningTime + '\'' +
                ", projectId=" + projectId +
                ", areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", adminId=" + adminId +
                '}';
    }

    @Override
	public void push(SmsMessageInfo smsMessageInfo) {
      //  Integer userId, Integer projectId, String alias, boolean isApnsProduction, String userPhone
        //推送标题
        String saveTitle = "";
        String saveAlert = "定位器设备";
        Map<String, String> tempPara = null;
        //短信发送内容
        String alert = "";
        Map<String,String> extras = new HashMap<>();
        extras.put("skip", String.valueOf(PRIVILEGESID));
        extras.put("append","");
        PushExample pushExample = new PushExample();
        if (this.getWarningType()==0 && smsMessageInfo.getOnOff()==1) {
            saveTitle = "SOS报警";
            alert=this.getUserName()+"在"+this.getWarningTime()+",在"+this.getAreaName()+"工业区设备发出SOS报警";
            //推送
            PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
                    (ALTER, smsMessageInfo.getAlias(), TITLE, false,extras);
            pushExample.testSendPush(payload);
            //将推送消息保存到数据库
            this.saveExcessive(saveTitle, this.getImei(), smsMessageInfo.getProjectId(), smsMessageInfo.getUserId(), saveAlert, PRIVILEGESID);
        }
        if (this.getWarningType()==1 && smsMessageInfo.getFall()==1) {
            saveTitle = "摔跌报警";
            alert=this.getUserName()+"在"+this.getWarningTime()+",在"+this.getAreaName()+"工业区设备发出摔跌报警";
            //推送
            PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
                    (ALTER, smsMessageInfo.getAlias(), TITLE, false,extras);
            pushExample.testSendPush(payload);
            //将推送消息保存到数据库
            this.saveExcessive(saveTitle, this.getImei(),  smsMessageInfo.getProjectId(), smsMessageInfo.getUserId(), saveAlert, PRIVILEGESID);
        }
        if (this.getWarningType()==2 && smsMessageInfo.getMove()==1) {
            saveTitle = "长时间未移动报警";
            alert=this.getUserName()+"在"+this.getWarningTime()+",在"+this.getAreaName()+"工业区设备发出长时间未移动报警";
            //推送
            PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
                    (ALTER, smsMessageInfo.getAlias(), TITLE, false,extras);
            pushExample.testSendPush(payload);
            //将推送消息保存到数据库
            this.saveExcessive(saveTitle, this.getImei(),  smsMessageInfo.getProjectId(), smsMessageInfo.getUserId(), saveAlert, PRIVILEGESID);
        }
        if (this.getWarningType()==3 && smsMessageInfo.getBat()==1) {
            saveTitle = "低电量报警";
            alert=this.getUserName()+"在"+this.getWarningTime()+",在"+this.getAreaName()+"工业区设备低电量报警";
            //推送
            PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
                    (ALTER, smsMessageInfo.getAlias(), TITLE, false,extras);
            pushExample.testSendPush(payload);
            //将推送消息保存到数据库
            this.saveExcessive(saveTitle, this.getImei(),  smsMessageInfo.getProjectId(), smsMessageInfo.getUserId(), saveAlert, PRIVILEGESID);
        }

            tempPara = new HashMap<>();
            tempPara.put("title", TITLE);
            tempPara.put("name", this.getUserName());  //站点
            tempPara.put("time",this.getWarningTime());
            tempPara.put("address",this.getAreaName());
            tempPara.put("accident", saveTitle);   //内容
            if(alert !=null && !("").equals(alert) && smsMessageInfo.getUserId() == this.getAdminId()){
                this.shortCreedNumber(this.getUserPhone(), smsMessageInfo.getProjectId(), smsMessageInfo.getUserId(), tempPara, TEMPID, "定位器设备报警");
            }

	}
}
