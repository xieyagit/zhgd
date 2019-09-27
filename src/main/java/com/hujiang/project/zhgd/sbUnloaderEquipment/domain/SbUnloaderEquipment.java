package com.hujiang.project.zhgd.sbUnloaderEquipment.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 卸料设备运行时长表 sb_unloader_equipment
 * 
 * @author hujiang
 * @date 2019-09-12
 */
public class SbUnloaderEquipment
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 设备编号 */
	private String deviceNo;
	/** 运行时刻 */
	private String runtime;
	/** 事件类型（0 断电，1 通电） */
	private Integer operation;
	/** 设备编号 */
	private String hxzid;

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
