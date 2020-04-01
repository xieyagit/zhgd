package com.hujiang.project.zhgd.lyAttendanceRecord.domain;

import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;

import java.util.List;

/**
 * 人员通行记录
 * 
 * @author hujiang
 * @date 2020-03-06
 */
public class LyRecordExport
{
	private static final long serialVersionUID = 1L;
	
	private String empNumber;
	private String empName;
	private String subordinate;
	private String passedTime;
	private String maxTime;
	private String minTime;
	private String temperature;
	private Integer projectId;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getSubordinate() {
		return subordinate;
	}

	public void setSubordinate(String subordinate) {
		this.subordinate = subordinate;
	}

	public String getPassedTime() {
		return passedTime;
	}

	public void setPassedTime(String passedTime) {
		this.passedTime = passedTime;
	}

	public String getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}

	public String getMinTime() {
		return minTime;
	}

	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
}
