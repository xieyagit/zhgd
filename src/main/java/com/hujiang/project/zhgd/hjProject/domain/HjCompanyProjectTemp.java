package com.hujiang.project.zhgd.hjProject.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 项目表 hj_project
 * 
 * @author hujiang
 * @date 2019-05-24
 */
public class HjCompanyProjectTemp
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 项目名 */
	private String projectName;
	/** 简称 */
	private String shortName;
	/** 项目负责人 */
	private String projectPrincipal;
	/** 联系方式 */
	private String phone;
	/** 项目类型（从字典获取相应数据） */
	private String projectType;
	/** 所属地区（三级联动） */
	private String projectRegion;
	/** 项目状态 */
	private String projectState;
	/** 项目管理人数 */
	private Integer projectNumber;
	/** 项目地址 */
	private String projectAddress;
	/** 起始时间 */
	private String startingTime;
	/** 结束时间 */
	private String finishTime;
	/** 施工企业（公司库获取） */
	private Integer constructionId;
	/** 监理企业（公司库获取） */
	private Integer supervisorId;
	/** 工程造价(万元) */
	private String projectCost;
	/** 经度 */
	private String longitude;
	/** 纬度 */
	private String latitude;
	//项目参建单位数量
	private int numProjectC;
	//项目在场工人数量
	private int numProjectW;
	//项目上工人数
	private int numProjectWorking;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectRegion() {
		return projectRegion;
	}

	public void setProjectRegion(String projectRegion) {
		this.projectRegion = projectRegion;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}



	public String getProjectPrincipal() {
		return projectPrincipal;
	}

	public void setProjectPrincipal(String projectPrincipal) {
		this.projectPrincipal = projectPrincipal;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getProjectState() {
		return projectState;
	}

	public void setProjectState(String projectState) {
		this.projectState = projectState;
	}

	public Integer getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(Integer projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(String startingTime) {
		this.startingTime = startingTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(Integer constructionId) {
		this.constructionId = constructionId;
	}

	public Integer getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(Integer supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getProjectCost() {
		return projectCost;
	}

	public void setProjectCost(String projectCost) {
		this.projectCost = projectCost;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getNumProjectC() {
		return numProjectC;
	}

	public void setNumProjectC(int numProjectC) {
		this.numProjectC = numProjectC;
	}

	public int getNumProjectW() {
		return numProjectW;
	}

	public void setNumProjectW(int numProjectW) {
		this.numProjectW = numProjectW;
	}

	public int getNumProjectWorking() {
		return numProjectWorking;
	}

	public void setNumProjectWorking(int numProjectWorking) {
		this.numProjectWorking = numProjectWorking;
	}


	@Override
	public String toString() {
		return "HjCompanyProjectTemp{" +
				"id=" + id +
				", projectName='" + projectName + '\'' +
				", shortName='" + shortName + '\'' +
				", projectPrincipal='" + projectPrincipal + '\'' +
				", phone='" + phone + '\'' +
				", projectType='" + projectType + '\'' +
				", projectState='" + projectState + '\'' +
				", projectNumber=" + projectNumber +
				", projectAddress='" + projectAddress + '\'' +
				", startingTime='" + startingTime + '\'' +
				", finishTime='" + finishTime + '\'' +
				", constructionId=" + constructionId +
				", supervisorId=" + supervisorId +
				", projectCost='" + projectCost + '\'' +
				", longitude='" + longitude + '\'' +
				", latitude='" + latitude + '\'' +
				", numProjectC=" + numProjectC +
				", numProjectW=" + numProjectW +
				", numProjectWorking=" + numProjectWorking +
				'}';
	}
}
