package com.hujiang.project.zhgd.hjDeviceTemperature.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 人脸设备和测温设备绑定表 hj_device_temperature
 * 
 * @author hujiang
 * @date 2020-03-05
 */
public class HjDeviceTemperature
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 人脸设备序列号 */
	private String deviceSn;
	/** 测温设备序列号 */
	private String temperatureSn;
private Integer pid;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setDeviceSn(String deviceSn) 
	{
		this.deviceSn = deviceSn;
	}

	public String getDeviceSn() 
	{
		return deviceSn;
	}
	public void setTemperatureSn(String temperatureSn) 
	{
		this.temperatureSn = temperatureSn;
	}

	public String getTemperatureSn() 
	{
		return temperatureSn;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceSn", getDeviceSn())
            .append("temperatureSn", getTemperatureSn())
            .toString();
    }
}
