package com.hujiang.project.zhgd.hjDeeppit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 深基坑结构物监测因素表 sb_deeppit_display_items
 * 
 * @author hujiang
 * @date 2019-09-04
 */
public class SbDeeppitDisplayItems
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 因素id */
	private Integer displayId;
	/** item名称 */
	private String name;
	/**  */
	private String fieldName;
	/** 单位 */
	private String unit;
	/** 预留 */
	private String reserved;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setDisplayId(Integer displayId) 
	{
		this.displayId = displayId;
	}

	public Integer getDisplayId() 
	{
		return displayId;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setFieldName(String fieldName) 
	{
		this.fieldName = fieldName;
	}

	public String getFieldName() 
	{
		return fieldName;
	}
	public void setUnit(String unit) 
	{
		this.unit = unit;
	}

	public String getUnit() 
	{
		return unit;
	}
	public void setReserved(String reserved) 
	{
		this.reserved = reserved;
	}

	public String getReserved() 
	{
		return reserved;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("displayId", getDisplayId())
            .append("name", getName())
            .append("fieldName", getFieldName())
            .append("unit", getUnit())
            .append("reserved", getReserved())
            .toString();
    }
}
