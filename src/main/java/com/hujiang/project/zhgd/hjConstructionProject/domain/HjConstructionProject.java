package com.hujiang.project.zhgd.hjConstructionProject.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 参建单位项目表 hj_construction_project
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class HjConstructionProject
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 参建单位id */
	private Integer constructionId;
	/** 项目ID */
	private Integer projectId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setConstructionId(Integer constructionId) 
	{
		this.constructionId = constructionId;
	}

	public Integer getConstructionId() 
	{
		return constructionId;
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
            .append("constructionId", getConstructionId())
            .append("projectId", getProjectId())
            .toString();
    }
}
