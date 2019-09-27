package com.hujiang.project.zhgd.sbCraneElectrify.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 塔机通电时间表 sb_crane_electrify
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public class SbCraneElectrify
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 设备编号32 */
	private String deviceNo;
	/** 运行时刻 */
	private String runtime;
	/** 事件类型（0 断电，1 通电） */
	private Integer operation;
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
	public void setRuntime(String runtime) 
	{
		this.runtime = runtime;
	}

	public String getRuntime() 
	{
		return runtime;
	}
	public void setOperation(Integer operation) 
	{
		this.operation = operation;
	}

	public Integer getOperation() 
	{
		return operation;
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
            .append("runtime", getRuntime())
            .append("operation", getOperation())
            .append("hxzid", getHxzid())
            .toString();
    }
}
