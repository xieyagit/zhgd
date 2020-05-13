package com.hujiang.project.zhgd.hjNanhuId.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 南湖项目id-我系统项目id表 hj_nanhu_id
 * 
 * @author hujiang
 * @date 2020-04-27
 */
public class HjNanhuId
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer id;
	/** 南湖项目ID */
	private String nanhuId;
	/** 对应我系统项目ID */
	private Integer projectId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setNanhuId(String nanhuId) 
	{
		this.nanhuId = nanhuId;
	}

	public String getNanhuId() 
	{
		return nanhuId;
	}
	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("nanhuId", getNanhuId())
            .append("projectId", getProjectId())
            .toString();
    }
}
