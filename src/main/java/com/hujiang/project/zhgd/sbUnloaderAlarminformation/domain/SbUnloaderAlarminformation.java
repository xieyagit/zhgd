package com.hujiang.project.zhgd.sbUnloaderAlarminformation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 卸料报警时段数据表 sb_unloader_alarminformation
 * 
 * @author hujiang
 * @date 2019-09-11
 */
public class SbUnloaderAlarminformation
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
	/** 报警结束时间 */
	private String endTime;
	/** 报警类型1：载重 */
	private Integer alarmType;
	/** 最大值0.00~2.00t */
	private Float maxValue;
	/** 最大值百分比0.00~120.00% */
	private Float maxValuePercent;

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
	public void setEndTime(String endTime) 
	{
		this.endTime = endTime;
	}

	public String getEndTime() 
	{
		return endTime;
	}
	public void setAlarmType(Integer alarmType) 
	{
		this.alarmType = alarmType;
	}

	public Integer getAlarmType() 
	{
		return alarmType;
	}
	public void setMaxValue(Float maxValue)
	{
		this.maxValue = maxValue;
	}

	public Float getMaxValue()
	{
		return maxValue;
	}
	public void setMaxValuePercent(Float maxValuePercent)
	{
		this.maxValuePercent = maxValuePercent;
	}

	public Float getMaxValuePercent()
	{
		return maxValuePercent;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceNo", getDeviceNo())
            .append("hxzId", getHxzId())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("alarmType", getAlarmType())
            .append("maxValue", getMaxValue())
            .append("maxValuePercent", getMaxValuePercent())
            .toString();
    }
}
