package com.hujiang.project.zhgd.hjUserRole.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户-角色表 hj_user_role
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class HjUserRole
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 用户ID */
	private Integer userId;
	/** 角色ID */
	private Integer roleId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setRoleId(Integer roleId) 
	{
		this.roleId = roleId;
	}

	public Integer getRoleId() 
	{
		return roleId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("roleId", getRoleId())
            .toString();
    }
}
