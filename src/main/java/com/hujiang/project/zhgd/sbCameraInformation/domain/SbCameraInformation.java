package com.hujiang.project.zhgd.sbCameraInformation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 海康摄像头报警记录表 sb_camera_information
 * 
 * @author hujiang
 * @date 2019-10-16
 */
public class SbCameraInformation
{
	private static final long serialVersionUID = 1L;
	/** 消息链路id */
	private String traceId;
	/** 任务id */
	private String taskId;
	/** 任务名称 */
	private String taskName;
	/** 任务类型,CLOUD，IPCH7，NVR	 */
	private String taskType;
	/** 训练版本id */
	private String trainId;
	/** 通道id */
	private String channelId;
	/** 设备Id */
	private String deviceId;
	/** 设备序列号 */
	private String deviceSerial;
	/** 设备名称 */
	private String deviceName;
	/** 通道号 */
	private Integer channelNo;
	/** 通道名称 */
	private String channelName;
	/** 分组id */
	private String groupId;
	/** 分组名称 */
	private String groupName;
	/** 抓图时间(时间戳) */
	private Integer captureTime;
	/** 图片报警地址 */
	private String resultUrl;
	/** 图片宽 */
	private String width;
	/** 图片高 */
	private String height;
	/** 模型id  */
	private String modelID;
	/** 目标id(id) */
	private Integer targetId;
	/** 对应labelInfo中的type */
	private Integer type;
	/** 检测结果置信度range=[0,1000] */
	private Integer confidence;
	/** 检测结果的名称，对应描述中labelInfo中的名称 */
	private String name;
	/** 目标框，左上点坐标和宽高 */
	private String rect;
	/** 分类结果 */
	private String properties;
	/** 分类结果详情 */
	private String classify;
	/**  */
	private String attrType;
	/** 对应类别名称 */
	private String typeName;
	/** 分类属性，对应labelInfo中的属性id */
	private Integer attrValue;
	/** 分类属性的名称，对应labelInfo中的属性名称 */
	private String valueName;
	/** 分类结果置信度range=[0,1000] */
	private String attrConf;
	/** 规则id */
	private String ruleId;
	/** 规则名称 */
	private String ruleName;
	/** 唯一标识*/
	private String msgId;

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public void setTraceId(String traceId) 
	{
		this.traceId = traceId;
	}

	public String getTraceId() 
	{
		return traceId;
	}
	public void setTaskId(String taskId) 
	{
		this.taskId = taskId;
	}

	public String getTaskId() 
	{
		return taskId;
	}
	public void setTaskName(String taskName) 
	{
		this.taskName = taskName;
	}

	public String getTaskName() 
	{
		return taskName;
	}
	public void setTaskType(String taskType) 
	{
		this.taskType = taskType;
	}

	public String getTaskType() 
	{
		return taskType;
	}
	public void setTrainId(String trainId) 
	{
		this.trainId = trainId;
	}

	public String getTrainId() 
	{
		return trainId;
	}
	public void setChannelId(String channelId) 
	{
		this.channelId = channelId;
	}

	public String getChannelId() 
	{
		return channelId;
	}
	public void setDeviceId(String deviceId) 
	{
		this.deviceId = deviceId;
	}

	public String getDeviceId() 
	{
		return deviceId;
	}
	public void setDeviceSerial(String deviceSerial) 
	{
		this.deviceSerial = deviceSerial;
	}

	public String getDeviceSerial() 
	{
		return deviceSerial;
	}
	public void setDeviceName(String deviceName) 
	{
		this.deviceName = deviceName;
	}

	public String getDeviceName() 
	{
		return deviceName;
	}
	public void setChannelNo(Integer channelNo) 
	{
		this.channelNo = channelNo;
	}

	public Integer getChannelNo() 
	{
		return channelNo;
	}
	public void setChannelName(String channelName) 
	{
		this.channelName = channelName;
	}

	public String getChannelName() 
	{
		return channelName;
	}
	public void setGroupId(String groupId) 
	{
		this.groupId = groupId;
	}

	public String getGroupId() 
	{
		return groupId;
	}
	public void setGroupName(String groupName) 
	{
		this.groupName = groupName;
	}

	public String getGroupName() 
	{
		return groupName;
	}
	public void setCaptureTime(Integer captureTime) 
	{
		this.captureTime = captureTime;
	}

	public Integer getCaptureTime() 
	{
		return captureTime;
	}
	public void setResultUrl(String resultUrl) 
	{
		this.resultUrl = resultUrl;
	}

	public String getResultUrl() 
	{
		return resultUrl;
	}
	public void setWidth(String width) 
	{
		this.width = width;
	}

	public String getWidth() 
	{
		return width;
	}
	public void setHeight(String height) 
	{
		this.height = height;
	}

	public String getHeight() 
	{
		return height;
	}
	public void setModelID(String modelID) 
	{
		this.modelID = modelID;
	}

	public String getModelID() 
	{
		return modelID;
	}
	public void setTargetId(Integer targetId) 
	{
		this.targetId = targetId;
	}

	public Integer getTargetId() 
	{
		return targetId;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}
	public void setConfidence(Integer confidence) 
	{
		this.confidence = confidence;
	}

	public Integer getConfidence() 
	{
		return confidence;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setRect(String rect) 
	{
		this.rect = rect;
	}

	public String getRect() 
	{
		return rect;
	}
	public void setProperties(String properties) 
	{
		this.properties = properties;
	}

	public String getProperties() 
	{
		return properties;
	}
	public void setClassify(String classify) 
	{
		this.classify = classify;
	}

	public String getClassify() 
	{
		return classify;
	}
	public void setAttrType(String attrType) 
	{
		this.attrType = attrType;
	}

	public String getAttrType() 
	{
		return attrType;
	}
	public void setTypeName(String typeName) 
	{
		this.typeName = typeName;
	}

	public String getTypeName() 
	{
		return typeName;
	}
	public void setAttrValue(Integer attrValue) 
	{
		this.attrValue = attrValue;
	}

	public Integer getAttrValue() 
	{
		return attrValue;
	}
	public void setValueName(String valueName) 
	{
		this.valueName = valueName;
	}

	public String getValueName() 
	{
		return valueName;
	}
	public void setAttrConf(String attrConf) 
	{
		this.attrConf = attrConf;
	}

	public String getAttrConf() 
	{
		return attrConf;
	}
	public void setRuleId(String ruleId)
	{
		this.ruleId = ruleId;
	}

	public String getRuleId()
	{
		return ruleId;
	}
	public void setRuleName(String ruleName) 
	{
		this.ruleName = ruleName;
	}

	public String getRuleName() 
	{
		return ruleName;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("traceId", getTraceId())
            .append("taskId", getTaskId())
            .append("taskName", getTaskName())
            .append("taskType", getTaskType())
            .append("trainId", getTrainId())
            .append("channelId", getChannelId())
            .append("deviceId", getDeviceId())
            .append("deviceSerial", getDeviceSerial())
            .append("deviceName", getDeviceName())
            .append("channelNo", getChannelNo())
            .append("channelName", getChannelName())
            .append("groupId", getGroupId())
            .append("groupName", getGroupName())
            .append("captureTime", getCaptureTime())
            .append("resultUrl", getResultUrl())
            .append("width", getWidth())
            .append("height", getHeight())
            .append("modelID", getModelID())
            .append("targetId", getTargetId())
            .append("type", getType())
            .append("confidence", getConfidence())
            .append("name", getName())
            .append("rect", getRect())
            .append("properties", getProperties())
            .append("classify", getClassify())
            .append("attrType", getAttrType())
            .append("typeName", getTypeName())
            .append("attrValue", getAttrValue())
            .append("valueName", getValueName())
            .append("attrConf", getAttrConf())
            .append("ruleId", getRuleId())
            .append("ruleName", getRuleName())
            .toString();
    }
}
