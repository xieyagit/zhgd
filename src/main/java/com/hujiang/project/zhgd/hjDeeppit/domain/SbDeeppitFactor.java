package com.hujiang.project.zhgd.hjDeeppit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 深基坑测点表 sb_deeppit_factor
 * 
 * @author hujiang
 * @date 2019-09-02
 */
public class SbDeeppitFactor
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 测点名称 */
	private String name;
	/** 测点图片 */
	private String portrait;
	/** 测点标签 */
	private String labels;
	/** 测点的参数值 */
	private String distance;
	/** 分组id */
	private String reserved;
	/** 供应商字段 */
	private int supplier;
	/** 因素id+供应商加密生成 */
	private String factorKey;

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
	public void setPortrait(String portrait) 
	{
		this.portrait = portrait;
	}

	public String getPortrait() 
	{
		return portrait;
	}
	public void setLabels(String labels) 
	{
		this.labels = labels;
	}

	public String getLabels() 
	{
		return labels;
	}
	public void setDistance(String distance) 
	{
		this.distance = distance;
	}

	public String getDistance() 
	{
		return distance;
	}
	public void setReserved(String reserved) 
	{
		this.reserved = reserved;
	}

	public String getReserved() 
	{
		return reserved;
	}

	public int getSupplier() {
		return supplier;
	}

	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}

	public String getFactorKey() {
		return factorKey;
	}

	public void setFactorKey(String factorKey) {
		this.factorKey = factorKey;
	}

	@Override
	public String toString() {
		return "SbDeeppitFactor{" +
				"id=" + id +
				", name='" + name + '\'' +
				", portrait='" + portrait + '\'' +
				", labels='" + labels + '\'' +
				", distance='" + distance + '\'' +
				", reserved='" + reserved + '\'' +
				", supplier=" + supplier +
				", factorKey='" + factorKey + '\'' +
				'}';
	}
}
