package com.hujiang.project.zhgd.hjSynchronizationInformation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

    
/**
 * 项目两制同步表 hj_synchronization_information
 * 
 * @author hujiang
 * @date 2019-06-03
 */
public class HjSynchronizationInformation
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 项目同步编号 */
	private String projectNumber;
	/** 授权账号 */
	private String apiKey;
	/** 授权密钥 */
	private String apiSecret;
	/** 设备序号 */
	private String clientSerial;
	/** hj_project 外键 id */
	private Integer projectId;
	/** 工程编码 */
	private String engineeringCode;
	/** 对接平台（从字典获取相应数据） */
	private String platformName;
	/** 状态（0审核中，1运行，2停止） */
	private Integer state;
	/** 创建时间 */
	private String createDate;
	/** 修改时间 */
	private String updateDate;
	/** 密钥类型（字典tag） */
	private String apiType;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setProjectNumber(String projectNumber) 
	{
		this.projectNumber = projectNumber;
	}

	public String getProjectNumber() 
	{
		return projectNumber;
	}
	public void setApiKey(String apiKey) 
	{
		this.apiKey = apiKey;
	}

	public String getApiKey() 
	{
		return apiKey;
	}
	public void setApiSecret(String apiSecret) 
	{
		this.apiSecret = apiSecret;
	}

	public String getApiSecret() 
	{
		return apiSecret;
	}
	public void setClientSerial(String clientSerial) 
	{
		this.clientSerial = clientSerial;
	}

	public String getClientSerial() 
	{
		return clientSerial;
	}
	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	public void setEngineeringCode(String engineeringCode) 
	{
		this.engineeringCode = engineeringCode;
	}

	public String getEngineeringCode() 
	{
		return engineeringCode;
	}
	public void setPlatformName(String platformName) 
	{
		this.platformName = platformName;
	}

	public String getPlatformName() 
	{
		return platformName;
	}
	public void setState(Integer state) 
	{
		this.state = state;
	}

	public Integer getState() 
	{
		return state;
	}
	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getCreateDate()
	{
		return createDate;
	}
	public void setUpdateDate(String updateDate) 
	{
		this.updateDate = updateDate;
	}

	public String getUpdateDate() 
	{
		return updateDate;
	}
	public void setApiType(String apiType) 
	{
		this.apiType = apiType;
	}

	public String getApiType() 
	{
		return apiType;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectNumber", getProjectNumber())
            .append("apiKey", getApiKey())
            .append("apiSecret", getApiSecret())
            .append("clientSerial", getClientSerial())
            .append("projectId", getProjectId())
            .append("engineeringCode", getEngineeringCode())
            .append("platformName", getPlatformName())
            .append("state", getState())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("apiType", getApiType())
            .toString();
    }
}
