package com.hujiang.project.zhgd.lyDevicePersonnel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 楼宇考勤设备人员表 ly_device_personnel
 * 
 * @author hujiang
 * @date 2020-03-06
 */
public class LyDevicePersonnel
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 设备编号 */
	private String deviceNo;
	/** 人员ID */
	private Integer personnelId;
	/** 1:已添加，2待删除，0，待添加，4添加结果未返回，5删除结果未返回 */
	private String status;
	/** 人员类型， */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	public void setPersonnelId(Integer personnelId) 
	{
		this.personnelId = personnelId;
	}

	public Integer getPersonnelId() 
	{
		return personnelId;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceNo", getDeviceNo())
            .append("personnelId", getPersonnelId())
            .append("status", getStatus())
            .append("type", getType())
            .toString();
    }
}
