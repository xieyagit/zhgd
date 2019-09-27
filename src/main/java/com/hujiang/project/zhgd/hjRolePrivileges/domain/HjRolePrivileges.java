package com.hujiang.project.zhgd.hjRolePrivileges.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 角色-权限表 hj_role_privileges
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class HjRolePrivileges
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 角色ID */
	private Integer roleId;
	/** 权限ID */
	private Integer privilegesId;
	/** 默认拥有（0.默认拥有） */
	private Integer whetherTheDefault;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setRoleId(Integer roleId) 
	{
		this.roleId = roleId;
	}

	public Integer getRoleId() 
	{
		return roleId;
	}
	public void setPrivilegesId(Integer privilegesId) 
	{
		this.privilegesId = privilegesId;
	}

	public Integer getPrivilegesId() 
	{
		return privilegesId;
	}
	public void setWhetherTheDefault(Integer whetherTheDefault) 
	{
		this.whetherTheDefault = whetherTheDefault;
	}

	public Integer getWhetherTheDefault() 
	{
		return whetherTheDefault;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleId", getRoleId())
            .append("privilegesId", getPrivilegesId())
            .append("whetherTheDefault", getWhetherTheDefault())
            .toString();
    }
}
