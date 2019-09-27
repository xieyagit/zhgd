package com.hujiang.project.zhgd.sbUnloaderRegistration.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 卸料注册表 sb_unloader_registration
 * 
 * @author hujiang
 * @date 2019-09-09
 */
public class SbUnloaderRegistration
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 黑匣子厂家 */
	private String hxzFactory;
	/** 黑匣子编号 */
	private String hxzId;
	/** 黑匣子编号（32）位 */
	private String deviceNo;
	/** 硬件版本号 */
	private String hardwareVer;
	/** 软件版本号 */
	private String softwareVer;
	/** SIM卡号 */
	private String simCardNo;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setHxzFactory(String hxzFactory) 
	{
		this.hxzFactory = hxzFactory;
	}

	public String getHxzFactory() 
	{
		return hxzFactory;
	}
	public void setHxzId(String hxzId) 
	{
		this.hxzId = hxzId;
	}

	public String getHxzId() 
	{
		return hxzId;
	}
	public void setHardwareVer(String hardwareVer) 
	{
		this.hardwareVer = hardwareVer;
	}

	public String getHardwareVer() 
	{
		return hardwareVer;
	}
	public void setSoftwareVer(String softwareVer) 
	{
		this.softwareVer = softwareVer;
	}

	public String getSoftwareVer() 
	{
		return softwareVer;
	}
	public void setSimCardNo(String simCardNo) 
	{
		this.simCardNo = simCardNo;
	}

	public String getSimCardNo(){return simCardNo;}
	public void setDeviceNo(String deviceNo) {this.deviceNo = deviceNo;}

	public String getDeviceNo() {return deviceNo;}


	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("hxzFactory", getHxzFactory())
            .append("hxzId", getHxzId())
			.append("deviceNo", getDeviceNo())
            .append("hardwareVer", getHardwareVer())
            .append("softwareVer", getSoftwareVer())
            .append("simCardNo", getSimCardNo())
			.toString();
    }
}
