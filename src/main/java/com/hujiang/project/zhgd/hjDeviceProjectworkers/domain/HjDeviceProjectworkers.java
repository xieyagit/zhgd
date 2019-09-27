package com.hujiang.project.zhgd.hjDeviceProjectworkers.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 考勤设备人员表 hj_device_projectworkers
 * 
 * @author hujiang
 * @date 2019-08-08
 */
public class HjDeviceProjectworkers
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 设备编号 */
	private String deviceNo;
	/** 人员ID */
	private Integer projectWorkersId;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	public void setProjectWorkersId(Integer projectWorkersId) 
	{
		this.projectWorkersId = projectWorkersId;
	}

	public Integer getProjectWorkersId() 
	{
		return projectWorkersId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceNo", getDeviceNo())
            .append("projectWorkersId", getProjectWorkersId())
				.append("status", getStatus())
            .toString();
    }
}
