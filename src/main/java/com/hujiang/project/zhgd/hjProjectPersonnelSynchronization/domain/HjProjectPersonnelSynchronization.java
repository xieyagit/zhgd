package com.hujiang.project.zhgd.hjProjectPersonnelSynchronization.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 项目人员同步表 hj_project_personnel_synchronization
 * 
 * @author hujiang
 * @date 2019-05-16
 */
public class HjProjectPersonnelSynchronization
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** hj_project 外键 id */
	private Integer projectId;
	/** 项目同步信息id */
	private Integer synchronizationInformationId;
	/** 项目工人id */
	private Integer projectWorkerId;

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
	public void setSynchronizationInformationId(Integer synchronizationInformationId) 
	{
		this.synchronizationInformationId = synchronizationInformationId;
	}

	public Integer getSynchronizationInformationId() 
	{
		return synchronizationInformationId;
	}
	public void setProjectWorkerId(Integer projectWorkerId) 
	{
		this.projectWorkerId = projectWorkerId;
	}

	public Integer getProjectWorkerId() 
	{
		return projectWorkerId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("synchronizationInformationId", getSynchronizationInformationId())
            .append("projectWorkerId", getProjectWorkerId())
            .toString();
    }
}
