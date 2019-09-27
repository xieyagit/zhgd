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
