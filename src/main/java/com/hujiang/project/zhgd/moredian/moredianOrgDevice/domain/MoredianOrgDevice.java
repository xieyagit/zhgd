package com.hujiang.project.zhgd.moredian.moredianOrgDevice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 机构设备表 sb_moredian_org_device
 * 
 * @author hujiang
 * @date 2019-05-09
 */
public class MoredianOrgDevice
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 机构序号id */
	private Integer orgId;
	/** 设备sn */
	private String deviceSn;
	/** 设备id */
	private String deviceId;
	/** 设备密码 */
	private String deviceRemark;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setOrgId(Integer orgId)
	{
		this.orgId = orgId;
	}

	public Integer getOrgId()
	{
		return orgId;
	}
	public void setDeviceSn(String deviceSn) 
	{
		this.deviceSn = deviceSn;
	}

	public String getDeviceSn() 
	{
		return deviceSn;
	}
	public void setDeviceId(String deviceId) 
	{
		this.deviceId = deviceId;
	}

	public String getDeviceId() 
	{
		return deviceId;
	}
	public void setdeviceRemark(String deviceRemark)
	{
		this.deviceRemark = deviceRemark;
	}

	public String getdeviceRemark()
	{
		return deviceRemark;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orgId", getOrgId())
            .append("deviceSn", getDeviceSn())
            .append("deviceId", getDeviceId())
            .append("deviceRemark", getdeviceRemark())
            .toString();
    }
}
