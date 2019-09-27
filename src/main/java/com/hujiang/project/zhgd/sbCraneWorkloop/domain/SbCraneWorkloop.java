package com.hujiang.project.zhgd.sbCraneWorkloop.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 塔机工作循环数据表 sb_crane_workloop
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public class SbCraneWorkloop
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 设备编号--32位 */
	private String deviceNo;
	/** 工作循环开始时刻 */
	private String startTime;
	/** 工作循环结束时刻 */
	private String endTime;
	/** 最高力矩比 */
	private Double maxMomentRatio;
	/** 超力矩发生次数 */
	private Double exceedingNum;
	/** 最大风速 m/s */
	private Double maxWindSpeed;
	/** 是否发生超风速(0.否1.是) */
	private Integer isOverWindSpeed;
	/** 最大幅度 */
	private Double maxRange;
	/** 最大幅度 */
	private Double maxHeight;
	/** 设备编号 */
	private String hxzid;

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
	public void setMaxMomentRatio(Double maxMomentRatio) 
	{
		this.maxMomentRatio = maxMomentRatio;
	}

	public Double getMaxMomentRatio() 
	{
		return maxMomentRatio;
	}
	public void setExceedingNum(Double exceedingNum) 
	{
		this.exceedingNum = exceedingNum;
	}

	public Double getExceedingNum() 
	{
		return exceedingNum;
	}
	public void setMaxWindSpeed(Double maxWindSpeed) 
	{
		this.maxWindSpeed = maxWindSpeed;
	}

	public Double getMaxWindSpeed() 
	{
		return maxWindSpeed;
	}
	public void setIsOverWindSpeed(Integer isOverWindSpeed) 
	{
		this.isOverWindSpeed = isOverWindSpeed;
	}

	public Integer getIsOverWindSpeed() 
	{
		return isOverWindSpeed;
	}
	public void setMaxRange(Double maxRange) 
	{
		this.maxRange = maxRange;
	}

	public Double getMaxRange() 
	{
		return maxRange;
	}
	public void setMaxHeight(Double maxHeight) 
	{
		this.maxHeight = maxHeight;
	}

	public Double getMaxHeight() 
	{
		return maxHeight;
	}
	public void setHxzid(String hxzid) 
	{
		this.hxzid = hxzid;
	}

	public String getHxzid() 
	{
		return hxzid;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceNo", getDeviceNo())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("maxMomentRatio", getMaxMomentRatio())
            .append("exceedingNum", getExceedingNum())
            .append("maxWindSpeed", getMaxWindSpeed())
            .append("isOverWindSpeed", getIsOverWindSpeed())
            .append("maxRange", getMaxRange())
            .append("maxHeight", getMaxHeight())
            .append("hxzid", getHxzid())
            .toString();
    }
}
