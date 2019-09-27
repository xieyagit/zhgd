package com.hujiang.project.zhgd.hjCompanyUser.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 公司用户表 hj_company_user
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class HjCompanyUser
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 公司id */
	private Integer companyId;
	/** 用户id */
	private Integer userId;

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
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("userId", getUserId())
            .toString();
    }
}
