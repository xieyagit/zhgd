package com.hujiang.project.zhgd.sbUnloaderAlarmtime.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 卸料报警时刻数据表 sb_unloader_alarmtime
 * 
 * @author hujiang
 * @date 2019-09-11
 */
public class SbUnloaderAlarmtime
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 黑匣子编号32位 */
	private String deviceNo;
	/** 黑匣子编号 */
	private String hxzId;
	/** 报警开始时间 */
	private String startTime;
	/** 报警类型1：载重2：倾角3：电池电量报警 */
	private Integer alarmType;
	/** 报警值：载重：0.00~2.00t，倾角X：-9.99°~9.99°，倾角Y：-9.99°~9.99°，电池电量：0~100 */
	private Float alarmValue;

	private Integer projectId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setDeviceNo(String deviceNo) 
	{
		this.deviceNo = deviceNo;
	}

	public String getDeviceNo() 
	{
		return deviceNo;
	}
	public void setHxzId(String hxzId) 
	{
		this.hxzId = hxzId;
	}

	public String getHxzId() 
	{
		return hxzId;
	}
	public void setStartTime(String startTime) 
	{
		this.startTime = startTime;
	}

	public String getStartTime() 
	{
		return startTime;
	}
	public void setAlarmType(Integer alarmType) 
	{
		this.alarmType = alarmType;
	}

	public Integer getAlarmType()
	{
		return alarmType;
	}
	public void setAlarmValue(Float alarmValue)
	{
		this.alarmValue = alarmValue;
	}

	public Float getAlarmValue()
	{
		return alarmValue;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceNo", getDeviceNo())
            .append("hxzId", getHxzId())
            .append("startTime", getStartTime())
            .append("alarmType", getAlarmType())
            .append("alarmValue", getAlarmValue())
            .toString();
    }
}
