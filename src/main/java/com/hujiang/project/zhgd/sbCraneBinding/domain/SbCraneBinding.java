package com.hujiang.project.zhgd.sbCraneBinding.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 塔吊设备绑定表 sb_crane_binding
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public class SbCraneBinding
{
	private static final long serialVersionUID = 1L;

	/** id */
	private Integer id;
	/** 设备编号 */
	private String hxzid;
	/** 项目编号 */
	private Integer pid;
	/** 操作人员id */
	private Integer userid;
	/** 设备名称 */
	private String dname;
	/** 是否同步1:已同步0未同步 */
	private String isSynchronization;
	/** 设备编号32位 */
	private String deviceNo;
	/** 广东省统一安装告 知编号（产权备案 编号） */
	private String serialNum;
	/** 最大幅度（M） */
	private Double tcMaxScope;
	/** 最大高度（M) */
	private Double tcMaxHeight;
	/** 最大载重（kg） */
	private Double tcLoadCapacity;
	/** 额定起重力矩（N·m） */
	private Double tcLoadMoment;
	/** 项目监督编号 */
	private String jdbh;
	/** 项目ID（城安院） */
	private String xmid;
	/** 工程id */
	private String subId;
	/** 对接平台 */
	private String scznl;
	/** 设备厂商ID */
	private Integer manufacturerId;
	/** 设备安装单位 */
	private String installCompany;


	private String craneName;

	public String getCraneName() {
		return craneName;
	}

	public void setCraneName(String craneName) {
		this.craneName = craneName;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}
	public void setHxzid(String hxzid)
	{
		this.hxzid = hxzid;
	}

	public String getHxzid()
	{
		return hxzid;
	}
	public void setPid(Integer pid)
	{
		this.pid = pid;
	}

	public Integer getPid()
	{
		return pid;
	}
	public void setUserid(Integer userid)
	{
		this.userid = userid;
	}

	public Integer getUserid()
	{
		return userid;
	}
	public void setDname(String dname)
	{
		this.dname = dname;
	}

	public String getDname()
	{
		return dname;
	}
	public void setIsSynchronization(String isSynchronization)
	{
		this.isSynchronization = isSynchronization;
	}

	public String getIsSynchronization()
	{
		return isSynchronization;
	}
	public void setDeviceNo(String deviceNo)
	{
		this.deviceNo = deviceNo;
	}

	public String getDeviceNo()
	{
		return deviceNo;
	}
	public void setSerialNum(String serialNum)
	{
		this.serialNum = serialNum;
	}

	public String getSerialNum()
	{
		return serialNum;
	}
	public void setTcMaxScope(Double tcMaxScope)
	{
		this.tcMaxScope = tcMaxScope;
	}

	public Double getTcMaxScope()
	{
		return tcMaxScope;
	}
	public void setTcMaxHeight(Double tcMaxHeight)
	{
		this.tcMaxHeight = tcMaxHeight;
	}

	public Double getTcMaxHeight()
	{
		return tcMaxHeight;
	}
	public void setTcLoadCapacity(Double tcLoadCapacity)
	{
		this.tcLoadCapacity = tcLoadCapacity;
	}

	public Double getTcLoadCapacity()
	{
		return tcLoadCapacity;
	}
	public void setTcLoadMoment(Double tcLoadMoment)
	{
		this.tcLoadMoment = tcLoadMoment;
	}

	public Double getTcLoadMoment()
	{
		return tcLoadMoment;
	}
	public void setJdbh(String jdbh)
	{
		this.jdbh = jdbh;
	}

	public String getJdbh()
	{
		return jdbh;
	}
	public void setXmid(String xmid)
	{
		this.xmid = xmid;
	}

	public String getXmid()
	{
		return xmid;
	}
	public void setSubId(String subId)
	{
		this.subId = subId;
	}

	public String getSubId()
	{
		return subId;
	}
	public void setScznl(String scznl)
	{
		this.scznl = scznl;
	}

	public String getScznl()
	{
		return scznl;
	}
	public void setManufacturerId(Integer manufacturerId)
	{
		this.manufacturerId = manufacturerId;
	}

	public Integer getManufacturerId()
	{
		return manufacturerId;
	}
	public void setInstallCompany(String installCompany)
	{
		this.installCompany = installCompany;
	}

	public String getInstallCompany()
	{
		return installCompany;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("hxzid", getHxzid())
				.append("pid", getPid())
				.append("userid", getUserid())
				.append("dname", getDname())
				.append("isSynchronization", getIsSynchronization())
				.append("deviceNo", getDeviceNo())
				.append("serialNum", getSerialNum())
				.append("tcMaxScope", getTcMaxScope())
				.append("tcMaxHeight", getTcMaxHeight())
				.append("tcLoadCapacity", getTcLoadCapacity())
				.append("tcLoadMoment", getTcLoadMoment())
				.append("jdbh", getJdbh())
				.append("xmid", getXmid())
				.append("subId", getSubId())
				.append("scznl", getScznl())
				.append("manufacturerId", getManufacturerId())
				.append("installCompany", getInstallCompany())
				.toString();
	}

}
