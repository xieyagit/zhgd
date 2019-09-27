package com.hujiang.project.zhgd.moredian.moredianGroup.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 项目群组表 sb_moredian_group
 * 
 * @author hujiang
 * @date 2019-05-11
 */
public class MoredianGroup
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 项目id */
	private Integer projectId;
	/** 项目名称 */
	private String projectName;
	/** 魔点群组ID */
	private String groupId;
	/** 机构id */
	private String orgId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	public void setProjectName(String projectName) 
	{
		this.projectName = projectName;
	}

	public String getProjectName() 
	{
		return projectName;
	}
	public void setGroupId(String groupId) 
	{
		this.groupId = groupId;
	}

	public String getGroupId() 
	{
		return groupId;
	}
	public void setOrgId(String orgId) 
	{
		this.orgId = orgId;
	}

	public String getOrgId() 
	{
		return orgId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("projectName", getProjectName())
            .append("groupId", getGroupId())
            .append("orgId", getOrgId())
            .toString();
    }
}
