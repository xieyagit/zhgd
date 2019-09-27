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
