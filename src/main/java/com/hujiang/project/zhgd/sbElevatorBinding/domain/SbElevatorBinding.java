package com.hujiang.project.zhgd.sbElevatorBinding.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 升降机绑定表 sb_elevator_binding
 *
 * @author hujiang
 * @date 2019-06-29
 */
public class SbElevatorBinding
{private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;
	/** 设备编号 */
	private String hxzid;
	/** 项目id */
	private Integer pid;
	/** 人员id */
	private Integer userid;
	/** 设备名称 */
	private String dname;
	/** 是否同步 */
	private String isSynchronization;
	/** 设备编号32位 */
	private String deviceNo;
	/** 广东省统一安装告 知编号（产权备案 编号） */
	private String serialNum;
	/** 项目监督编号 */
	private String jdbh;
	/** 项目ID（城安院） */
	private String xmid;
	/** 工程ID */
	private String subId;
	/** 对接平台 */
	private String scznl;
	/** 设备厂商ID */
	private Integer manufacturerId;
	/** 设备安装单位 */
	private String installCompany;
	/** 最大载重(kg) */
	private Double capacity;
	/** 最大高度（m） */
	private Double height;

	private Integer projectId;
	private String hxzId;
	private String elevatorName;

	private Integer gctype;


	public String getElevatorName() {
		return elevatorName;
	}

	public void setElevatorName(String elevatorName) {
		this.elevatorName = elevatorName;
	}

	public String getHxzId() {
		return hxzId;
	}

	public void setHxzId(String hxzId) {
		this.hxzId = hxzId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
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
	public void setCapacity(Double capacity)
	{
		this.capacity = capacity;
	}

	public Double getCapacity()
	{
		return capacity;
	}
	public void setHeight(Double height)
	{
		this.height = height;
	}

	public Double getHeight()
	{
		return height;
	}

	public Integer getGctype() {
		return gctype;
	}

	public void setGctype(Integer gctype) {
		this.gctype = gctype;
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
				.append("jdbh", getJdbh())
				.append("xmid", getXmid())
				.append("subId", getSubId())
				.append("scznl", getScznl())
				.append("manufacturerId", getManufacturerId())
				.append("installCompany", getInstallCompany())
				.append("capacity", getCapacity())
				.append("height", getHeight())
				.toString();
	}
}
