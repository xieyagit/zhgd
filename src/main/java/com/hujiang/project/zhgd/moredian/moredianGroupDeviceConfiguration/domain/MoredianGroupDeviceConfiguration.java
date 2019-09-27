package com.hujiang.project.zhgd.moredian.moredianGroupDeviceConfiguration.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 群组设备配置表 sb_moredian_group_device_configuration
 * 
 * @author hujiang
 * @date 2019-05-09
 */
public class MoredianGroupDeviceConfiguration
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 门禁启用时间（hh:mm） */
	private String dayBeginTime;
	/** 门禁结束时间（hh:mm） */
	private String dayEndTime;
	/** 1,2,3,4,5,6,7 */
	private String weekdays;
	/** 魔点群组ID */
	private String groupId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setDayBeginTime(String dayBeginTime) 
	{
		this.dayBeginTime = dayBeginTime;
	}

	public String getDayBeginTime() 
	{
		return dayBeginTime;
	}
	public void setDayEndTime(String dayEndTime) 
	{
		this.dayEndTime = dayEndTime;
	}

	public String getDayEndTime() 
	{
		return dayEndTime;
	}
	public void setWeekdays(String weekdays) 
	{
		this.weekdays = weekdays;
	}

	public String getWeekdays() 
	{
		return weekdays;
	}
	public void setGroupId(String groupId) 
	{
		this.groupId = groupId;
	}

	public String getGroupId() 
	{
		return groupId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dayBeginTime", getDayBeginTime())
            .append("dayEndTime", getDayEndTime())
            .append("weekdays", getWeekdays())
            .append("groupId", getGroupId())
            .toString();
    }
}
