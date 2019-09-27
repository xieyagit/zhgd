package com.hujiang.project.zhgd.hjDeeppit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 深基坑结构物表 sb_deeppit_structures
 * 
 * @author hujiang
 * @date 2019-09-02
 */
public class SbDeeppitStructuresItem
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer deviceId;
	/** 结构物名称 */
	private String deviceName;
	/** 结构物类型id */
	private Integer typeId;
	/** 结构物类型名称 */
	private String typeName;
	/** 纬度 */
	private String latitude;
	/** 经度 */
	private String longitude;
	/** 结构物图片 */
	private String portrait;
	/** 预留1 */
	private String reservedO;
	/** 预留2 */
	private String reserved2T;


	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public void setTypeId(Integer typeId)
	{
		this.typeId = typeId;
	}

	public Integer getTypeId() 
	{
		return typeId;
	}
	public void setTypeName(String typeName) 
	{
		this.typeName = typeName;
	}

	public String getTypeName() 
	{
		return typeName;
	}
	public void setLatitude(String latitude) 
	{
		this.latitude = latitude;
	}

	public String getLatitude() 
	{
		return latitude;
	}
	public void setLongitude(String longitude) 
	{
		this.longitude = longitude;
	}

	public String getLongitude() 
	{
		return longitude;
	}
	public void setPortrait(String portrait) 
	{
		this.portrait = portrait;
	}

	public String getPortrait() 
	{
		return portrait;
	}
	public void setReservedO(String reservedO) 
	{
		this.reservedO = reservedO;
	}

	public String getReservedO() 
	{
		return reservedO;
	}
	public void setReserved2T(String reserved2T) 
	{
		this.reserved2T = reserved2T;
	}

	public String getReserved2T() 
	{
		return reserved2T;
	}

	@Override
	public String toString() {
		return "SbDeeppitStructuresItem{" +
				"deviceId=" + deviceId +
				", deviceName='" + deviceName + '\'' +
				", typeId=" + typeId +
				", typeName='" + typeName + '\'' +
				", latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", portrait='" + portrait + '\'' +
				", reservedO='" + reservedO + '\'' +
				", reserved2T='" + reserved2T + '\'' +
				'}';
	}
}
