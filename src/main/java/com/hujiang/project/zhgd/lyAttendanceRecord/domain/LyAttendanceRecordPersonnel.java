package com.hujiang.project.zhgd.lyAttendanceRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * pc通行记录
 * 
 * @author hujiang
 * @date 2020-03-08
 */
public class LyAttendanceRecordPersonnel
{
	private static final long serialVersionUID = 1L;
	
	/** 人员ID */
	private Integer id;
	/** 项目编号 */
	private Integer projectId;
	/** 员工编号 */
	private Integer employeeId;
	/** 通过时间 ”yyyy-MM-dd hh:mm:ss” */
	private String passedTime;
	/** 通行方向  in—进，out—出 */
	private String direction;

	/** 工地现场采集的人脸照片，url */
	private String sitePhoto;


	/** 体温 */
	private String temperature;

	private String empName;//人员名字
	private String companyName;//公司名字
	private String type;//在职人员或访客

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

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
	public void setEmployeeId(Integer employeeId) 
	{
		this.employeeId = employeeId;
	}

	public Integer getEmployeeId() 
	{
		return employeeId;
	}
	public void setPassedTime(String passedTime) 
	{
		this.passedTime = passedTime;
	}

	public String getPassedTime() 
	{
		return passedTime;
	}
	public void setDirection(String direction) 
	{
		this.direction = direction;
	}

	public String getDirection() 
	{
		return direction;
	}

	public void setSitePhoto(String sitePhoto) 
	{
		this.sitePhoto = sitePhoto;
	}

	public String getSitePhoto() 
	{
		return sitePhoto;
	}

	public void setTemperature(String temperature) 
	{
		this.temperature = temperature;
	}

	public String getTemperature() 
	{
		return temperature;
	}


}
