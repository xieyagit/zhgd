package com.hujiang.project.zhgd.sbUnloaderRealtime.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 卸料实时数据表 sb_unloader_realtime
 * 
 * @author hujiang
 * @date 2019-09-11
 */
public class SbUnloaderRealtime
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 黑匣子编号32位 */
	private String deviceNo;
	/** 黑匣子编号 */
	private String hxzId;
	/** 采集时间 */
	private String rTime;
	/** 载重0.00~2.00t */
	private Float weight;
	/** 载重百分比0.00~120.00% */
	private Float weightPercent;
	/** 倾角X-9.99°~9.99° */
	private Float obliguityX;
	/** 倾角Y-9.99°~9.99° */
	private Float obliguityY;
	/** 合成倾角0.00~14.99° */
	private Float obliguity;
	/** 电量百分比0~100 */
	private Float batteryPercent;
	/** 载重状态0：正常1：预警2：报警3：故障 */
	private Integer weightStatus;
	/** 倾角X状态0：正常1：预警2：报警3：故障 */
	private Integer obliguityXStatus;
	/** 倾角Y状态0：正常1：预警2：报警3：故障 */
	private Integer obliguityYStatus;
	/** 倾角Y状态0：正常1：预警2：报警3：故障 */
	private Integer batteryStatus;

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
	public void setRTime(String rTime) 
	{
		this.rTime = rTime;
	}

	public String getRTime() 
	{
		return rTime;
	}
	public void setWeight(Float weight) 
	{
		this.weight = weight;
	}

	public Float getWeight() 
	{
		return weight;
	}
	public void setWeightPercent(Float weightPercent) 
	{
		this.weightPercent = weightPercent;
	}

	public Float getWeightPercent() 
	{
		return weightPercent;
	}
	public void setObliguityX(Float obliguityX) 
	{
		this.obliguityX = obliguityX;
	}

	public Float getObliguityX() 
	{
		return obliguityX;
	}
	public void setObliguityY(Float obliguityY) 
	{
		this.obliguityY = obliguityY;
	}

	public Float getObliguityY() 
	{
		return obliguityY;
	}
	public void setObliguity(Float obliguity) 
	{
		this.obliguity = obliguity;
	}

	public Float getObliguity() 
	{
		return obliguity;
	}
	public void setBatteryPercent(Float batteryPercent) 
	{
		this.batteryPercent = batteryPercent;
	}

	public Float getBatteryPercent() 
	{
		return batteryPercent;
	}
	public void setWeightStatus(Integer weightStatus) 
	{
		this.weightStatus = weightStatus;
	}

	public Integer getWeightStatus() 
	{
		return weightStatus;
	}
	public void setObliguityXStatus(Integer obliguityXStatus) 
	{
		this.obliguityXStatus = obliguityXStatus;
	}

	public Integer getObliguityXStatus() 
	{
		return obliguityXStatus;
	}
	public void setObliguityYStatus(Integer obliguityYStatus) 
	{
		this.obliguityYStatus = obliguityYStatus;
	}

	public Integer getObliguityYStatus() 
	{
		return obliguityYStatus;
	}
	public void setBatteryStatus(Integer batteryStatus) 
	{
		this.batteryStatus = batteryStatus;
	}

	public Integer getBatteryStatus() 
	{
		return batteryStatus;
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
            .append("rTime", getRTime())
            .append("weight", getWeight())
            .append("weightPercent", getWeightPercent())
            .append("obliguityX", getObliguityX())
            .append("obliguityY", getObliguityY())
            .append("obliguity", getObliguity())
            .append("batteryPercent", getBatteryPercent())
            .append("weightStatus", getWeightStatus())
            .append("obliguityXStatus", getObliguityXStatus())
            .append("obliguityYStatus", getObliguityYStatus())
            .append("batteryStatus", getBatteryStatus())
            .toString();
    }
}
