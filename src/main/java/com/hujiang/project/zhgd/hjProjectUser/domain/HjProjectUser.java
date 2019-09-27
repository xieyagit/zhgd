package com.hujiang.project.zhgd.hjProjectUser.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 项目用户表 hj_project_user
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class HjProjectUser
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 项目id */
	private Integer projectId;
	/** 用户id */
	private Integer userId;

	private String userPhone;
	private String alias;

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
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("userId", getUserId())
				.append("alias", getAlias())
				.append("userPhone", getUserPhone())
            .toString();
    }
}
