package com.hujiang.project.zhgd.hjDeeppit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 深基坑数据记录表 hj_deeppit_data
 *
 * @author hujiang
 * @date 2019-09-04
 */
public class HjDeeppitData
{
	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;
	/** 结构件id */
	private Integer structuresId;
	/** 监测因素id */
	private Integer displayId;
	/** 测点id */
	private Integer factorId;
	/** 深层水平位移X方向（mm） */
	private String levelX;
	/** 深层水平位移Y方向（mm） */
	private String levelY;
	/** 深层水平累积位移X方向（mm） */
	private String levelAccumulateX;
	/** 深层水平累积位移Y方向（mm） */
	private String levelAccumulateY;
	/** 地下水位（m） */
	private String waterLevel;
	/** 应变频率（HZ） */
	private String strainFrequency;
	/** 应变温度（℃） */
	private String strainTemperature;
	/** 沉降（mm） */
	private String subside;
	/** 建筑物倾斜X方向(°) */
	private String tiltX;
	/** 建筑物倾斜Y方向(°) */
	private String tiltY;
	/** 预留1 */
	private String reservedO;
	/** 预留2 */
	private String reservedT;
	/** 创建时间 */
	private String creation;
	private String endTime;

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}
	public void setStructuresId(Integer structuresId)
	{
		this.structuresId = structuresId;
	}

	public Integer getStructuresId()
	{
		return structuresId;
	}
	public void setDisplayId(Integer displayId)
	{
		this.displayId = displayId;
	}

	public Integer getDisplayId()
	{
		return displayId;
	}
	public void setFactorId(Integer factorId)
	{
		this.factorId = factorId;
	}

	public Integer getFactorId()
	{
		return factorId;
	}
	public void setLevelX(String levelX)
	{
		this.levelX = levelX;
	}

	public String getLevelX()
	{
		return levelX;
	}
	public void setLevelY(String levelY)
	{
		this.levelY = levelY;
	}

	public String getLevelY()
	{
		return levelY;
	}
	public void setLevelAccumulateX(String levelAccumulateX)
	{
		this.levelAccumulateX = levelAccumulateX;
	}

	public String getLevelAccumulateX()
	{
		return levelAccumulateX;
	}
	public void setLevelAccumulateY(String levelAccumulateY)
	{
		this.levelAccumulateY = levelAccumulateY;
	}

	public String getLevelAccumulateY()
	{
		return levelAccumulateY;
	}
	public void setWaterLevel(String waterLevel)
	{
		this.waterLevel = waterLevel;
	}

	public String getWaterLevel()
	{
		return waterLevel;
	}
	public void setStrainFrequency(String strainFrequency)
	{
		this.strainFrequency = strainFrequency;
	}

	public String getStrainFrequency()
	{
		return strainFrequency;
	}
	public void setStrainTemperature(String strainTemperature)
	{
		this.strainTemperature = strainTemperature;
	}

	public String getStrainTemperature()
	{
		return strainTemperature;
	}
	public void setSubside(String subside)
	{
		this.subside = subside;
	}

	public String getSubside()
	{
		return subside;
	}
	public void setTiltX(String tiltX)
	{
		this.tiltX = tiltX;
	}

	public String getTiltX()
	{
		return tiltX;
	}
	public void setTiltY(String tiltY)
	{
		this.tiltY = tiltY;
	}

	public String getTiltY()
	{
		return tiltY;
	}
	public void setReservedO(String reservedO)
	{
		this.reservedO = reservedO;
	}

	public String getReservedO()
	{
		return reservedO;
	}
	public void setReservedT(String reservedT)
	{
		this.reservedT = reservedT;
	}

	public String getReservedT()
	{
		return reservedT;
	}
	public void setCreation(String creation)
	{
		this.creation = creation;
	}

	public String getCreation()
	{
		return creation;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("structuresId", getStructuresId())
				.append("displayId", getDisplayId())
				.append("factorId", getFactorId())
				.append("levelX", getLevelX())
				.append("levelY", getLevelY())
				.append("levelAccumulateX", getLevelAccumulateX())
				.append("levelAccumulateY", getLevelAccumulateY())
				.append("waterLevel", getWaterLevel())
				.append("strainFrequency", getStrainFrequency())
				.append("strainTemperature", getStrainTemperature())
				.append("subside", getSubside())
				.append("tiltX", getTiltX())
				.append("tiltY", getTiltY())
				.append("reservedO", getReservedO())
				.append("reservedT", getReservedT())
				.append("creation", getCreation())
				.toString();
	}
}
