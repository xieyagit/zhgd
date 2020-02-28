package com.hujiang.project.zhgd.hjAttendanceLocation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 打卡定位表 hj_attendance_location
 * 
 * @author hujiang
 * @date 2020-02-26
 */
public class HjAttendanceLocation
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 项目id */
	private Integer projectId;
	/** 考勤位置名称 */
	private String name;
	/** 经度 */
	private String longitude;
	/** 纬度 */
	private String latitude;
	/** 打卡半径 */
	private Integer radius;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setLongitude(String longitude) 
	{
		this.longitude = longitude;
	}

	public String getLongitude() 
	{
		return longitude;
	}
	public void setLatitude(String latitude) 
	{
		this.latitude = latitude;
	}

	public String getLatitude() 
	{
		return latitude;
	}
	public void setRadius(Integer radius) 
	{
		this.radius = radius;
	}

	public Integer getRadius() 
	{
		return radius;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("name", getName())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("radius", getRadius())
            .toString();
    }
}
