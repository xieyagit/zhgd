package com.hujiang.project.zhgd.sbUnloaderLocatordata.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 卸料GPS定位数据
表 sb_unloader_locatordata
 * 
 * @author hujiang
 * @date 2019-09-11
 */
public class SbUnloaderLocatordata
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 黑匣子编号 */
	private String hxzId;
	/** 黑匣子编号32位 */
	private String deviceNo;
	/** 纬度 */
	private String latitude;
	/** 经度 */
	private String longitude;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setHxzId(String hxzId) 
	{
		this.hxzId = hxzId;
	}

	public String getHxzId() 
	{
		return hxzId;
	}
	public void setDeviceNo(String deviceNo) 
	{
		this.deviceNo = deviceNo;
	}

	public String getDeviceNo() 
	{
		return deviceNo;
	}
	public void setLatitude(String latitude) 
	{
		this.latitude = latitude;
	}

	public String getLatitude() 
	{
		return latitude;
	}
	public void setLongitude(String longitude) 
	{
		this.longitude = longitude;
	}

	public String getLongitude() 
	{
		return longitude;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("hxzId", getHxzId())
            .append("deviceNo", getDeviceNo())
            .append("latitude", getLatitude())
            .append("longitude", getLongitude())
            .toString();
    }
}
