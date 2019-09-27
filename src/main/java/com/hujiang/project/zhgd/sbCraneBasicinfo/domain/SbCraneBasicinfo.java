package com.hujiang.project.zhgd.sbCraneBasicinfo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 塔式起重机设备基本表 sb_crane_basicinfo
 * 
 * @author hujiang
 * @date 2019-06-20
 */
public class SbCraneBasicinfo
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 设备编号--32位 */
	private String deviceNo;
	/** 设备型号 */
	private String model;
	/** 设备名称 */
	private String name;
	/** 设备用户名称(如:2号塔机) */
	private String uid;
	/** 设备证书日期 */
	private String license;
	/** 设备发证日期 */
	private String licenseStartDate;
	/** 设备有效日期 */
	private String licenseEndDate;
	/** 设备照片 */
	private String photos;
	/** 设备产权单位 */
	private String propertyUnit;
	/** 设备制造商 */
	private String mnufacturerBusiness;
	/** 设备维保商 */
	private String maiWarBus;
	/** 设备检测商 */
	private String testingBusiness;
	/** 设备进场时间 */
	private String inDate;
	/** 设备出场时间 */
	private String outDate;
	/** 监测设备厂商 */
	private String monDeviceMan;
	/** 监测设备安装日期 */
	private String deviceInstallationDate;
	/** 设备编号 */
	private String hxzid;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setDeviceNo(String deviceNo) 
	{
		this.deviceNo = deviceNo;
	}

	public String getDeviceNo() 
	{
		return deviceNo;
	}
	public void setModel(String model) 
	{
		this.model = model;
	}

	public String getModel() 
	{
		return model;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setUid(String uid) 
	{
		this.uid = uid;
	}

	public String getUid() 
	{
		return uid;
	}
	public void setLicense(String license) 
	{
		this.license = license;
	}

	public String getLicense() 
	{
		return license;
	}
	public void setLicenseStartDate(String licenseStartDate) 
	{
		this.licenseStartDate = licenseStartDate;
	}

	public String getLicenseStartDate() 
	{
		return licenseStartDate;
	}
	public void setLicenseEndDate(String licenseEndDate) 
	{
		this.licenseEndDate = licenseEndDate;
	}

	public String getLicenseEndDate() 
	{
		return licenseEndDate;
	}
	public void setPhotos(String photos) 
	{
		this.photos = photos;
	}

	public String getPhotos() 
	{
		return photos;
	}
	public void setPropertyUnit(String propertyUnit) 
	{
		this.propertyUnit = propertyUnit;
	}

	public String getPropertyUnit() 
	{
		return propertyUnit;
	}
	public void setMnufacturerBusiness(String mnufacturerBusiness) 
	{
		this.mnufacturerBusiness = mnufacturerBusiness;
	}

	public String getMnufacturerBusiness() 
	{
		return mnufacturerBusiness;
	}
	public void setMaiWarBus(String maiWarBus) 
	{
		this.maiWarBus = maiWarBus;
	}

	public String getMaiWarBus() 
	{
		return maiWarBus;
	}
	public void setTestingBusiness(String testingBusiness) 
	{
		this.testingBusiness = testingBusiness;
	}

	public String getTestingBusiness() 
	{
		return testingBusiness;
	}
	public void setInDate(String inDate) 
	{
		this.inDate = inDate;
	}

	public String getInDate() 
	{
		return inDate;
	}
	public void setOutDate(String outDate) 
	{
		this.outDate = outDate;
	}

	public String getOutDate() 
	{
		return outDate;
	}
	public void setMonDeviceMan(String monDeviceMan) 
	{
		this.monDeviceMan = monDeviceMan;
	}

	public String getMonDeviceMan() 
	{
		return monDeviceMan;
	}
	public void setDeviceInstallationDate(String deviceInstallationDate) 
	{
		this.deviceInstallationDate = deviceInstallationDate;
	}

	public String getDeviceInstallationDate() 
	{
		return deviceInstallationDate;
	}
	public void setHxzid(String hxzid) 
	{
		this.hxzid = hxzid;
	}

	public String getHxzid() 
	{
		return hxzid;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceNo", getDeviceNo())
            .append("model", getModel())
            .append("name", getName())
            .append("uid", getUid())
            .append("license", getLicense())
            .append("licenseStartDate", getLicenseStartDate())
            .append("licenseEndDate", getLicenseEndDate())
            .append("photos", getPhotos())
            .append("propertyUnit", getPropertyUnit())
            .append("mnufacturerBusiness", getMnufacturerBusiness())
            .append("maiWarBus", getMaiWarBus())
            .append("testingBusiness", getTestingBusiness())
            .append("inDate", getInDate())
            .append("outDate", getOutDate())
            .append("monDeviceMan", getMonDeviceMan())
            .append("deviceInstallationDate", getDeviceInstallationDate())
            .append("hxzid", getHxzid())
            .toString();
    }
}
