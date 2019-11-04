package com.hujiang.project.zhgd.hjCompanyProject.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 公司项目表 hj_company_project
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class HjCompanyProject
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 公司ID */
	private Integer companyId;
	/** 项目ID */
	private Integer projectId;
	/** 所属公司层级 */
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
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
            .append("companyId", getCompanyId())
            .append("projectId", getProjectId())
            .append("path", getPath())
            .toString();
    }
}
