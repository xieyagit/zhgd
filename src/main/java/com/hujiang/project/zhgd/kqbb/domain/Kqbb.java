package com.hujiang.project.zhgd.kqbb.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 考勤报表 kqbb
 * 
 * @author hujiang
 * @date 2019-07-05
 */
public class Kqbb
{
	private static final long serialVersionUID = 1L;
	
	/** 项目id */
	private Integer projectId;
	/** 员工id */
	private Integer empId;
	/** 员工姓名 */
	private String empName;
	/** 统计时间 */
	private String aTime;
	/** 工时 */
	private BigDecimal manHour;

	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	public void setEmpId(Integer empId) 
	{
		this.empId = empId;
	}

	public Integer getEmpId() 
	{
		return empId;
	}
	public void setEmpName(String empName) 
	{
		this.empName = empName;
	}

	public String getEmpName() 
	{
		return empName;
	}
	public void setATime(String aTime) 
	{
		this.aTime = aTime;
	}

	public String getATime() 
	{
		return aTime;
	}
	public void setManHour(BigDecimal manHour)
	{
		this.manHour = manHour;
	}

	public BigDecimal getManHour() 
	{
		return manHour;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("empId", getEmpId())
            .append("empName", getEmpName())
            .append("aTime", getATime())
            .append("manHour", getManHour())
            .toString();
    }
}
