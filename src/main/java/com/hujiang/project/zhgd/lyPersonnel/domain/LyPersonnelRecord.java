package com.hujiang.project.zhgd.lyPersonnel.domain;

import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;

import java.util.List;

/**
 * 人员通行记录
 * 
 * @author hujiang
 * @date 2020-03-06
 */
public class LyPersonnelRecord
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 名字 */
	private String empName;
	private String faceUrl;
	private List<LyAttendanceRecord> larList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getFaceUrl() {
		return faceUrl;
	}

	public void setFaceUrl(String faceUrl) {
		this.faceUrl = faceUrl;
	}

	public List<LyAttendanceRecord> getLarList() {
		return larList;
	}

	public void setLarList(List<LyAttendanceRecord> larList) {
		this.larList = larList;
	}
}
