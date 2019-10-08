package com.hujiang.project.zhgd.sbManufacturer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备厂商名称表 sb_manufacturer
 * 
 * @author hujiang
 * @date 2019-09-24
 */
public class SbManufacturer
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 设备厂商名 */
	private String manufacturerName;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setManufacturerName(String manufacturerName) 
	{
		this.manufacturerName = manufacturerName;
	}

	public String getManufacturerName() 
	{
		return manufacturerName;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("manufacturerName", getManufacturerName())
            .toString();
    }
}
