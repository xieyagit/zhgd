package com.hujiang.project.zhgd.sbProjectElectricityBox.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.lang.reflect.Array;

/**
 * 上报电箱参数 sb_project_electricity_box
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public class SbPowerBoxAdd
{
	private static final long serialVersionUID = 1L;

	/** 项目id */
	private Integer projectId;
	/** 电箱编号 */
	private String electricityBoxId;
	/**设备名称 例如：3#配电箱*/
	private String electricityBoxName;
	/**设备类型*/
	private Integer type;
	/**设备名称 例如：3#配电箱*/
	private String name;
	/**设备类型名称*/
	private String typeNsame;
	/**安装商*/
	private String installCompany;
	/**区域类型*/
	private Integer installaddType;
	/**安装地址*/
	private String installAddress;
	/**经度*/
	private String longitude;
	/**纬度*/
	private String latitude;
	/**设备照片*/
	private Array photos;
	/**电缆温度限值(℃)*/
	private Double tempLimit;
	/**漏电流限值(mA)*/
	private Integer elecLimit;
	/**周围环境温度限值(℃)*/
	private Double aroundTemp;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeNsame() {
		return typeNsame;
	}

	public void setTypeNsame(String typeNsame) {
		this.typeNsame = typeNsame;
	}

	public String getInstallCompany() {
		return installCompany;
	}

	public void setInstallCompany(String installCompany) {
		this.installCompany = installCompany;
	}

	public Integer getInstalladdType() {
		return installaddType;
	}

	public void setInstalladdType(Integer installaddType) {
		this.installaddType = installaddType;
	}

	public String getInstallAddress() {
		return installAddress;
	}

	public void setInstallAddress(String installAddress) {
		this.installAddress = installAddress;
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

	public Array getPhotos() {
		return photos;
	}

	public void setPhotos(Array photos) {
		this.photos = photos;
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
}
