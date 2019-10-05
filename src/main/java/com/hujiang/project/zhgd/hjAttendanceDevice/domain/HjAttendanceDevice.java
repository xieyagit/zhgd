package com.hujiang.project.zhgd.hjAttendanceDevice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

    
/**
 * 考勤机设备表 hj_attendance_device
 * 
 * @author hujiang
 * @date 2019-08-06
 */
public class HjAttendanceDevice
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/**  */
	private String deviceNo;
	/** 设备名字 */
	private String deviceName;
	/** 设备厂家 */
	private String deviceFactory;
	/** 进出，in or out */
	private String direction;
	/** 创建时间 */
	private String createTime;
	/** 是否上传住建局 1正常，0不正常 */
	private String status;
	/** 项目ID */
	private Integer projectId;
	/** 设备IP */
	private String deviceIp;
	/** 上次连接时间 */
	private String connectTime;

	public String getConnectTime() {
		return connectTime;
	}

	public void setConnectTime(String connectTime) {
		this.connectTime = connectTime;
	}

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
	public void setDeviceName(String deviceName) 
	{
		this.deviceName = deviceName;
	}

	public String getDeviceName() 
	{
		return deviceName;
	}
	public void setDeviceFactory(String deviceFactory) 
	{
		this.deviceFactory = deviceFactory;
	}

	public String getDeviceFactory() 
	{
		return deviceFactory;
	}
	public void setDirection(String direction) 
	{
		this.direction = direction;
	}

	public String getDirection() 
	{
		return direction;
	}
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getCreateTime()
	{
		return createTime;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	public void setDeviceIp(String deviceIp) 
	{
		this.deviceIp = deviceIp;
	}

	public String getDeviceIp() 
	{
		return deviceIp;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceNo", getDeviceNo())
            .append("deviceName", getDeviceName())
            .append("deviceFactory", getDeviceFactory())
            .append("direction", getDirection())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .append("projectId", getProjectId())
            .append("deviceIp", getDeviceIp())
            .append("connectTime", getConnectTime())
            .toString();
    }
}
