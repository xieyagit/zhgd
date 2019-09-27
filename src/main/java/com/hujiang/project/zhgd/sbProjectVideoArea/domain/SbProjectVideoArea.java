package com.hujiang.project.zhgd.sbProjectVideoArea.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 项目视频区表 sb_project_video_area
 * 
 * @author hujiang
 * @date 2019-07-08
 */
public class SbProjectVideoArea
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 项目id */
	private Integer projectid;
	/** 视频区 */
	private String areaName;
	/** 地址 */
	private String address;
	/** 所属单位 */
	private String affiliatedUnit;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setProjectid(Integer projectid) 
	{
		this.projectid = projectid;
	}

	public Integer getProjectid() 
	{
		return projectid;
	}
	public void setAreaName(String areaName) 
	{
		this.areaName = areaName;
	}

	public String getAreaName() 
	{
		return areaName;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setAffiliatedUnit(String affiliatedUnit) 
	{
		this.affiliatedUnit = affiliatedUnit;
	}

	public String getAffiliatedUnit() 
	{
		return affiliatedUnit;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectid", getProjectid())
            .append("areaName", getAreaName())
            .append("address", getAddress())
            .append("affiliatedUnit", getAffiliatedUnit())
            .toString();
    }
}
