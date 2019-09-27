package com.hujiang.project.zhgd.hjDeeppit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 深基坑token表 sb_deeppit_token
 * 
 * @author hujiang
 * @date 2019-09-03
 */
public class SbDeeppitToken
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** token */
	private String token;
	/** 过期时间 */
	private String expiration;
	/** appId */
	private String appId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setToken(String token) 
	{
		this.token = token;
	}

	public String getToken() 
	{
		return token;
	}
	public void setExpiration(String expiration) 
	{
		this.expiration = expiration;
	}

	public String getExpiration() {
		return expiration;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Override
	public String toString() {
		return "SbDeeppitToken{" +
				"id=" + id +
				", token='" + token + '\'' +
				", expiration='" + expiration + '\'' +
				", appId='" + appId + '\'' +
				'}';
	}
}
