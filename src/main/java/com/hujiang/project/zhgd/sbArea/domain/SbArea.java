package com.hujiang.project.zhgd.sbArea.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 工区表 sb_area
 * 
 * @author hujiang
 * @date 2019-06-29
 */
public class SbArea
{
	private static final long serialVersionUID = 1L;
	
	/** 工区id */
	private Integer id;
	/** 工区名名称 */
	private String name;
	/** 项目id */
	private Integer pid;
	/** 劳务公司id */
	private Integer bid;
	/** 经度 */
	private Double xloc;
	/** 纬度 */
	private Double yloc;
	/** 半径 */
	private Double radius;

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
	public void setPid(Integer pid) 
	{
		this.pid = pid;
	}

	public Integer getPid() 
	{
		return pid;
	}
	public void setBid(Integer bid) 
	{
		this.bid = bid;
	}

	public Integer getBid() 
	{
		return bid;
	}
	public void setXloc(Double xloc) 
	{
		this.xloc = xloc;
	}

	public Double getXloc() 
	{
		return xloc;
	}
	public void setYloc(Double yloc) 
	{
		this.yloc = yloc;
	}

	public Double getYloc() 
	{
		return yloc;
	}
	public void setRadius(Double radius) 
	{
		this.radius = radius;
	}

	public Double getRadius() 
	{
		return radius;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("pid", getPid())
            .append("bid", getBid())
            .append("xloc", getXloc())
            .append("yloc", getYloc())
            .append("radius", getRadius())
            .toString();
    }
}
