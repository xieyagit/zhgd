package com.hujiang.project.zhgd.hjDeviceHikvision.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 海康萤石用户表 hj_device_hikvision
 * 
 * @author hujiang
 * @date 2019-10-19
 */
public class HjDeviceHikvision
{
	private static final long serialVersionUID = 1L;
	
	/** id,采用海康用户id */
	private Integer id;
	/** 身份证号 */
	private String idCard;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setIdCard(String idCard) 
	{
		this.idCard = idCard;
	}

	public String getIdCard() 
	{
		return idCard;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("idCard", getIdCard())
            .toString();
    }
}
