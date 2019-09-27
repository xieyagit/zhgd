package com.hujiang.project.zhgd.hjCompanyHierarchy.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 公司层级表 hj_company_hierarchy
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class HjCompanyHierarchy
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 公司ID */
	private Integer companyId;
	/** 父级ID（1级为0） */
	private Integer parentId;

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
	public void setParentId(Integer parentId) 
	{
		this.parentId = parentId;
	}

	public Integer getParentId() 
	{
		return parentId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyId", getCompanyId())
            .append("parentId", getParentId())
            .toString();
    }
}
