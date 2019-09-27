package com.hujiang.project.zhgd.sbEquipmentRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 定位记录表 sb_equipment_record
 * 
 * @author hujiang
 * @date 2019-06-29
 */
public class SbEquipmentRecord
{
	private static final long serialVersionUID = 1L;
	
	/** 数据id */
	private Integer id;
	/** 设备imei：关联设备表id */
	private String imei;
	/** xloc */
	private Double xloc;
	/**  */
	private Double yloc;
	/** 电量 */
	private Integer bat;
	/** 信号强度 */
	private String rssi;
	/** 定位时间 */
	private Date watchDate;
	/** 创建时间 */
	private Date createDate;
	/** 定位方式 */
	private String isPosition;
	/** 位置定位 */
	private String address;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setImei(String imei) 
	{
		this.imei = imei;
	}

	public String getImei() 
	{
		return imei;
	}
	public void setXloc(Double xloc) 
	{
		this.xloc = xloc;
	}

	public Double getXloc() 
	{
		return xloc;
	}
	public void setYloc(Double yloc) 
	{
		this.yloc = yloc;
	}

	public Double getYloc() 
	{
		return yloc;
	}
	public void setBat(Integer bat) 
	{
		this.bat = bat;
	}

	public Integer getBat() 
	{
		return bat;
	}
	public void setRssi(String rssi) 
	{
		this.rssi = rssi;
	}

	public String getRssi() 
	{
		return rssi;
	}
	public void setWatchDate(Date watchDate) 
	{
		this.watchDate = watchDate;
	}

	public Date getWatchDate() 
	{
		return watchDate;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setIsPosition(String isPosition) 
	{
		this.isPosition = isPosition;
	}

	public String getIsPosition() 
	{
		return isPosition;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("imei", getImei())
            .append("xloc", getXloc())
            .append("yloc", getYloc())
            .append("bat", getBat())
            .append("rssi", getRssi())
            .append("watchDate", getWatchDate())
            .append("createDate", getCreateDate())
            .append("isPosition", getIsPosition())
            .append("address", getAddress())
            .toString();
    }
}
