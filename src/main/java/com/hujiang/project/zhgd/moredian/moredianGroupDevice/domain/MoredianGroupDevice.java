package com.hujiang.project.zhgd.moredian.moredianGroupDevice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 群组设备表 sb_moredian_group_device
 * 
 * @author hujiang
 * @date 2019-05-09
 */
public class MoredianGroupDevice
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 设备id */
	private String deviceId;
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
	public void setDeviceId(String deviceId) 
	{
		this.deviceId = deviceId;
	}

	public String getDeviceId() 
	{
		return deviceId;
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
            .append("deviceId", getDeviceId())
            .append("groupId", getGroupId())
            .toString();
    }
}
