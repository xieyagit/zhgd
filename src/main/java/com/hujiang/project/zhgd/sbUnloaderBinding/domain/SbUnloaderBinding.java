package com.hujiang.project.zhgd.sbUnloaderBinding.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 卸料设备绑定表 sb_unloader_binding
 * 
 * @author hujiang
 * @date 2019-09-15
 */
public class SbUnloaderBinding
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 黑匣子编号32位 */
	private String deviceNo;
	/** 黑匣子编号 */
	private String hxzId;
	/** 项目ID */
	private Integer pid;
	/** 设备名称 */
	private String dName;
	/** 是否同步1:已同步0未同步 */
	private String isSynchronization;

	private String serialNum;//广东省统一安装告 知编号（产权备案 编号）
	private String tcMaxScope;//最大幅度（M）
	private String tcMaxHeight;//最大高度（M)
	private String tcLoadCapacity;//最大载重（kg）
	private String tcLoadMoment;//额定起重力矩（N·m）
	private String jdbh;//项目监督编号
	private String xmid;//项目ID
	private String subId;//工程ID
	private String scznl;//对接平台
	private String manufacturerId;//设备厂商id

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getTcMaxScope() {
		return tcMaxScope;
	}

	public void setTcMaxScope(String tcMaxScope) {
		this.tcMaxScope = tcMaxScope;
	}

	public String getTcMaxHeight() {
		return tcMaxHeight;
	}

	public void setTcMaxHeight(String tcMaxHeight) {
		this.tcMaxHeight = tcMaxHeight;
	}

	public String getTcLoadCapacity() {
		return tcLoadCapacity;
	}

	public void setTcLoadCapacity(String tcLoadCapacity) {
		this.tcLoadCapacity = tcLoadCapacity;
	}

	public String getTcLoadMoment() {
		return tcLoadMoment;
	}

	public void setTcLoadMoment(String tcLoadMoment) {
		this.tcLoadMoment = tcLoadMoment;
	}

	public String getJdbh() {
		return jdbh;
	}

	public void setJdbh(String jdbh) {
		this.jdbh = jdbh;
	}

	public String getXmid() {
		return xmid;
	}

	public void setXmid(String xmid) {
		this.xmid = xmid;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getScznl() {
		return scznl;
	}

	public void setScznl(String scznl) {
		this.scznl = scznl;
	}

	public String getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
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
	public void setHxzId(String hxzId)
	{
		this.hxzId = hxzId;
	}

	public String getHxzId()
	{
		return hxzId;
	}
	public void setPid(Integer pid) 
	{
		this.pid = pid;
	}

	public Integer getPid() 
	{
		return pid;
	}
	public void setDName(String dName) 
	{
		this.dName = dName;
	}

	public String getDName() 
	{
		return dName;
	}
	public void setIsSynchronization(String isSynchronization) 
	{
		this.isSynchronization = isSynchronization;
	}

	public String getIsSynchronization() 
	{
		return isSynchronization;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceNo", getDeviceNo())
            .append("hxzId", getHxzId())
            .append("pid", getPid())
            .append("dName", getDName())
            .append("isSynchronization", getIsSynchronization())
            .toString();
    }
}
