package com.hujiang.project.zhgd.sbCamera.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * AI摄像头客户表 sb_camera
 * 
 * @author hujiang
 * @date 2019-10-15
 */
public class SbCamera
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 客户ID */
	private String clientId;
	/** 客户秘钥 */
	private String clientSecret;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setClientId(String clientId) 
	{
		this.clientId = clientId;
	}

	public String getClientId() 
	{
		return clientId;
	}
	public void setClientSecret(String clientSecret) 
	{
		this.clientSecret = clientSecret;
	}

	public String getClientSecret() 
	{
		return clientSecret;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("clientId", getClientId())
            .append("clientSecret", getClientSecret())
            .toString();
    }
}
