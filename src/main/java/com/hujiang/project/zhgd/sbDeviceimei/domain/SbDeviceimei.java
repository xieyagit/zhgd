package com.hujiang.project.zhgd.sbDeviceimei.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备编号
表 sb_deviceimei
 * 
 * @author hujiang
 * @date 2019-06-29
 */
public class SbDeviceimei
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 设备编号 */
	private String imei;

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
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("imei", getImei())
            .toString();
    }
}
