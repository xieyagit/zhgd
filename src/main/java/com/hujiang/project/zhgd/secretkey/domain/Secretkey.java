package com.hujiang.project.zhgd.secretkey.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 智慧工地对接秘钥表 zh_secretkey
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class Secretkey
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long id;
	/** 模块：1：实名制，2：扬尘噪音，3：视频监控，4：用电检测，5，车辆识别，6，塔吊监控，7，升降机监控 */
	private String type;
	/** 项目 */
	private Long pid;
	/** 授权账号 */
	private String apiKey;
	/** 设备号 */
	private String clientSerail;
	/** 项目编码 */
	private String projectCode;
	/** 工程编码 */
	private String engCode;
	/** 接口版本 */
	private String apiVersion;
	/** 授权秘钥 */
	private String apiSecret;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setPid(Long pid) 
	{
		this.pid = pid;
	}

	public Long getPid() 
	{
		return pid;
	}
	public void setApiKey(String apiKey) 
	{
		this.apiKey = apiKey;
	}

	public String getApiKey() 
	{
		return apiKey;
	}
	public void setClientSerail(String clientSerail) 
	{
		this.clientSerail = clientSerail;
	}

	public String getClientSerail() 
	{
		return clientSerail;
	}
	public void setProjectCode(String projectCode) 
	{
		this.projectCode = projectCode;
	}

	public String getProjectCode() 
	{
		return projectCode;
	}
	public void setEngCode(String engCode) 
	{
		this.engCode = engCode;
	}

	public String getEngCode() 
	{
		return engCode;
	}
	public void setApiVersion(String apiVersion) 
	{
		this.apiVersion = apiVersion;
	}

	public String getApiVersion() 
	{
		return apiVersion;
	}
	public void setApiSecret(String apiSecret) 
	{
		this.apiSecret = apiSecret;
	}

	public String getApiSecret() 
	{
		return apiSecret;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("pid", getPid())
            .append("apiKey", getApiKey())
            .append("clientSerail", getClientSerail())
            .append("projectCode", getProjectCode())
            .append("engCode", getEngCode())
            .append("apiVersion", getApiVersion())
            .append("apiSecret", getApiSecret())
            .toString();
    }
}
