package com.hujiang.project.zhgd.hjBlacklist.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 黑名单表 hj_blacklist
 * 
 * @author hujiang
 * @date 2019-06-25
 */
public class HjBlacklist
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Integer id;
	/** 姓名 */
	private String empName;
	/** 身份证号码 */
	private String empCode;
	/** 原因 */
	private String cause;
	/** 附件地址 */
	private String url;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setEmpName(String empName) 
	{
		this.empName = empName;
	}

	public String getEmpName() 
	{
		return empName;
	}
	public void setEmpCode(String empCode) 
	{
		this.empCode = empCode;
	}

	public String getEmpCode() 
	{
		return empCode;
	}
	public void setCause(String cause) 
	{
		this.cause = cause;
	}

	public String getCause() 
	{
		return cause;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getUrl() 
	{
		return url;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("empName", getEmpName())
            .append("empCode", getEmpCode())
            .append("cause", getCause())
            .append("url", getUrl())
            .toString();
    }
}
