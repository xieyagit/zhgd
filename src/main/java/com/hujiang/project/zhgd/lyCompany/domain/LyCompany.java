package com.hujiang.project.zhgd.lyCompany.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 楼宇公司表 ly_company
 * 
 * @author hujiang
 * @date 2020-03-05
 */
public class LyCompany
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 公司id */
	private String companyName;
	/** 楼层 */
	private String floor;
	/** 入住时间 */
	private String checkIdTime;
	/** 负责人 */
	private String personCharge;
	/** 所属项目 */
	private Integer pid;
	/** 公司人数	 */
	private Integer size;

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}

	public String getCompanyName() 
	{
		return companyName;
	}
	public void setFloor(String floor) 
	{
		this.floor = floor;
	}

	public String getFloor() 
	{
		return floor;
	}
	public void setCheckIdTime(String checkIdTime) 
	{
		this.checkIdTime = checkIdTime;
	}

	public String getCheckIdTime() 
	{
		return checkIdTime;
	}
	public void setPersonCharge(String personCharge) 
	{
		this.personCharge = personCharge;
	}

	public String getPersonCharge() 
	{
		return personCharge;
	}
	public void setPid(Integer pid) 
	{
		this.pid = pid;
	}

	public Integer getPid() 
	{
		return pid;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("companyName", getCompanyName())
            .append("floor", getFloor())
            .append("checkIdTime", getCheckIdTime())
            .append("personCharge", getPersonCharge())
            .append("pid", getPid())
            .toString();
    }
}
