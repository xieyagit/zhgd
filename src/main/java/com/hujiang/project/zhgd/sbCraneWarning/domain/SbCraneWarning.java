package com.hujiang.project.zhgd.sbCraneWarning.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 塔机预警数据表 sb_crane_warning
 * 
 * @author hujiang
 * @date 2019-06-21
 */
public class SbCraneWarning
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 设备编号--32位 */
	private String deviceNo;
	/** 运行时,格式为yyyy-MM-dd hh:mm:ss， */
	private String runtime;
	/** 载重（动臂式塔 机无载重，0） */
	private Double load;
	/** 载重比 */
	private Double loadRatio;
	/** 力矩 */
	private Double moment;
	/** 力矩比 */
	private Double momentRatio;
	/** 回转角度 */
	private Double slewingSpeed;
	/** 高度(动臂式塔 机无高度,填0） */
	private Double height;
	/** 幅度 */
	private Double range;
	/** 倍率 */
	private Integer magnification;
	/** 传感器状态（1-正常，2-停机） */
	private Integer sensorStatus;
	/** 正常工作预警状态（1-正常，2-预警） */
	private Integer normalWorkEarlyWarnState;
	/** 正常工作报警状态（1-正常，2-预警） */
	private Integer normalWorkWarnStatus;
	/** 区域限制预警状态（1-无，2-预警（塔吊 进入限制区域）） */
	private Integer regionLimitEarlyWarnStatus;
	/** 区域限制报警状态（1-无，2-预警（塔吊 进入限制区域）） */
	private Integer regionLimitWarnStatus;
	/** 群塔预警状态（1-无，2-预警（交叉群塔 同时作业中）） */
	private Integer groupTowerEarlyWarnStatus;
	/** 群塔报警状态（1-无，2-预警（交叉群塔 同时作业中）） */
	private Integer groupTowerWarnStatus;
	/** 风速 m/s */
	private Double windSpeed;
	/** 设备编号 */
	private String hxzid;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
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
	public void setRuntime(String runtime) 
	{
		this.runtime = runtime;
	}

	public String getRuntime() 
	{
		return runtime;
	}
	public void setLoad(Double load) 
	{
		this.load = load;
	}

	public Double getLoad() 
	{
		return load;
	}
	public void setLoadRatio(Double loadRatio) 
	{
		this.loadRatio = loadRatio;
	}

	public Double getLoadRatio() 
	{
		return loadRatio;
	}
	public void setMoment(Double moment) 
	{
		this.moment = moment;
	}

	public Double getMoment() 
	{
		return moment;
	}
	public void setMomentRatio(Double momentRatio) 
	{
		this.momentRatio = momentRatio;
	}

	public Double getMomentRatio() 
	{
		return momentRatio;
	}
	public void setSlewingSpeed(Double slewingSpeed) 
	{
		this.slewingSpeed = slewingSpeed;
	}

	public Double getSlewingSpeed() 
	{
		return slewingSpeed;
	}
	public void setHeight(Double height) 
	{
		this.height = height;
	}

	public Double getHeight() 
	{
		return height;
	}
	public void setRange(Double range) 
	{
		this.range = range;
	}

	public Double getRange() 
	{
		return range;
	}
	public void setMagnification(Integer magnification) 
	{
		this.magnification = magnification;
	}

	public Integer getMagnification() 
	{
		return magnification;
	}
	public void setSensorStatus(Integer sensorStatus) 
	{
		this.sensorStatus = sensorStatus;
	}

	public Integer getSensorStatus() 
	{
		return sensorStatus;
	}
	public void setNormalWorkEarlyWarnState(Integer normalWorkEarlyWarnState) 
	{
		this.normalWorkEarlyWarnState = normalWorkEarlyWarnState;
	}

	public Integer getNormalWorkEarlyWarnState() 
	{
		return normalWorkEarlyWarnState;
	}
	public void setNormalWorkWarnStatus(Integer normalWorkWarnStatus) 
	{
		this.normalWorkWarnStatus = normalWorkWarnStatus;
	}

	public Integer getNormalWorkWarnStatus() 
	{
		return normalWorkWarnStatus;
	}
	public void setRegionLimitEarlyWarnStatus(Integer regionLimitEarlyWarnStatus) 
	{
		this.regionLimitEarlyWarnStatus = regionLimitEarlyWarnStatus;
	}

	public Integer getRegionLimitEarlyWarnStatus() 
	{
		return regionLimitEarlyWarnStatus;
	}
	public void setRegionLimitWarnStatus(Integer regionLimitWarnStatus) 
	{
		this.regionLimitWarnStatus = regionLimitWarnStatus;
	}

	public Integer getRegionLimitWarnStatus() 
	{
		return regionLimitWarnStatus;
	}
	public void setGroupTowerEarlyWarnStatus(Integer groupTowerEarlyWarnStatus) 
	{
		this.groupTowerEarlyWarnStatus = groupTowerEarlyWarnStatus;
	}

	public Integer getGroupTowerEarlyWarnStatus() 
	{
		return groupTowerEarlyWarnStatus;
	}
	public void setGroupTowerWarnStatus(Integer groupTowerWarnStatus) 
	{
		this.groupTowerWarnStatus = groupTowerWarnStatus;
	}

	public Integer getGroupTowerWarnStatus() 
	{
		return groupTowerWarnStatus;
	}
	public void setWindSpeed(Double windSpeed) 
	{
		this.windSpeed = windSpeed;
	}

	public Double getWindSpeed() 
	{
		return windSpeed;
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
            .append("runtime", getRuntime())
            .append("load", getLoad())
            .append("loadRatio", getLoadRatio())
            .append("moment", getMoment())
            .append("momentRatio", getMomentRatio())
            .append("slewingSpeed", getSlewingSpeed())
            .append("height", getHeight())
            .append("range", getRange())
            .append("magnification", getMagnification())
            .append("sensorStatus", getSensorStatus())
            .append("normalWorkEarlyWarnState", getNormalWorkEarlyWarnState())
            .append("normalWorkWarnStatus", getNormalWorkWarnStatus())
            .append("regionLimitEarlyWarnStatus", getRegionLimitEarlyWarnStatus())
            .append("regionLimitWarnStatus", getRegionLimitWarnStatus())
            .append("groupTowerEarlyWarnStatus", getGroupTowerEarlyWarnStatus())
            .append("groupTowerWarnStatus", getGroupTowerWarnStatus())
            .append("windSpeed", getWindSpeed())
            .append("hxzid", getHxzid())
            .toString();
    }
}
