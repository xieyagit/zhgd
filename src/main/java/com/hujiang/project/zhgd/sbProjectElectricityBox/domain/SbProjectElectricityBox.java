package com.hujiang.project.zhgd.sbProjectElectricityBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 项目电箱表 sb_project_electricity_box
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public class SbProjectElectricityBox
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 项目id */
	private Integer projectId;
	/** 项目电箱备注 */
	private String comments;
	/** 电箱编号 */
	private String electricityBoxId;
	/**设备名称 例如：3#配电箱*/
	private String electricityBoxName;
	/**电缆温度限值(℃)*/
	private Double tempLimit;
	/**漏电流限值(mA)*/
	private Integer elecLimit;
	/**周围环境温度限值(℃)*/
	private Double aroundTemp;
	/** 电箱类型：0.智能电箱 1.改造电箱 */
	private Integer type;
	/** 项目编号*/
	private String project;
	/** 项目监督编号*/
	private String jdbh;
	/** 设备安装单位*/
	private String companyName;
	/** 安装地址*/
	private String installAddress;
	/** 字典：生活区、施工现场、配电房*/
	private String installAddrtype;
	/** DTU 标识*/
	private String dtuId;
	/** 工程 id*/
	private String subId;

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getJdbh() {
		return jdbh;
	}

	public void setJdbh(String jdbh) {
		this.jdbh = jdbh;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getInstallAddress() {
		return installAddress;
	}

	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
	}

	public String getInstallAddrtype() {
		return installAddrtype;
	}

	public void setInstallAddrtype(String installAddrtype) {
		this.installAddrtype = installAddrtype;
	}

	public String getDtuId() {
		return dtuId;
	}

	public void setDtuId(String dtuId) {
		this.dtuId = dtuId;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getElectricityBoxId() {
		return electricityBoxId;
	}

	public void setElectricityBoxId(String electricityBoxId) {
		this.electricityBoxId = electricityBoxId;
	}

	public String getElectricityBoxName() {
		return electricityBoxName;
	}

	public void setElectricityBoxName(String electricityBoxName) {
		this.electricityBoxName = electricityBoxName;
	}

	public Double getTempLimit() {
		return tempLimit;
	}

	public void setTempLimit(Double tempLimit) {
		this.tempLimit = tempLimit;
	}

	public Integer getElecLimit() {
		return elecLimit;
	}

	public void setElecLimit(Integer elecLimit) {
		this.elecLimit = elecLimit;
	}

	public Double getAroundTemp() {
		return aroundTemp;
	}

	public void setAroundTemp(Double aroundTemp) {
		this.aroundTemp = aroundTemp;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
