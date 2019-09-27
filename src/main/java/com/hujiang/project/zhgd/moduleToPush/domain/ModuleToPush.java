package com.hujiang.project.zhgd.moduleToPush.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户推送开关表 module_to_push
 * 
 * @author hujiang
 * @date 2019-09-05
 */
public class ModuleToPush
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Integer privilegesId;
	/**  */
	private Integer userId;
	/**  */
	private Integer onOff;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setPrivilegesId(Integer privilegesId) 
	{
		this.privilegesId = privilegesId;
	}

	public Integer getPrivilegesId() 
	{
		return privilegesId;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setOnOff(Integer onOff) 
	{
		this.onOff = onOff;
	}

	public Integer getOnOff() 
	{
		return onOff;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("privilegesId", getPrivilegesId())
            .append("userId", getUserId())
            .append("onOff", getOnOff())
            .toString();
    }
}
