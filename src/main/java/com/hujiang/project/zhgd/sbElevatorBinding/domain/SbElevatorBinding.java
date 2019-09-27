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
{
	private static final long serialVersionUID = 1L;
	
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
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("hxzid", getHxzid())
            .append("pid", getPid())
            .append("userid", getUserid())
            .append("dname", getDname())
            .append("isSynchronization", getIsSynchronization())
            .append("deviceNo", getDeviceNo())
            .toString();
    }
}
