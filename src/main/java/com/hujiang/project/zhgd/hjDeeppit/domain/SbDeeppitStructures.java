package com.hujiang.project.zhgd.hjDeeppit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 深基坑结构物表 sb_deeppit_structures
 * 
 * @author hujiang
 * @date 2019-09-02
 */
public class SbDeeppitStructures
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 结构物名称 */
	private String name;
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
	/** 供应商id */
	private int supplier;
	/** 主键：id+供应商 */
	private String masterKey;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
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

	public int getSupplier() {
		return supplier;
	}

	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	@Override
	public String toString() {
		return "SbDeeppitStructures{" +
				"id=" + id +
				", name='" + name + '\'' +
				", typeId=" + typeId +
				", typeName='" + typeName + '\'' +
				", latitude='" + latitude + '\'' +
				", longitude='" + longitude + '\'' +
				", portrait='" + portrait + '\'' +
				", reservedO='" + reservedO + '\'' +
				", reserved2T='" + reserved2T + '\'' +
				", supplier='" + supplier + '\'' +
				", masterKey='" + masterKey + '\'' +
				'}';
	}
}
