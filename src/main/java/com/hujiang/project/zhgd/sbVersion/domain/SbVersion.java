package com.hujiang.project.zhgd.sbVersion.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 版本表 sb_version
 * 
 * @author hujiang
 * @date 2019-08-26
 */
public class SbVersion
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 版本号 */
	private Integer versionNumber;
	/** 版本名    */
	private String versionName;
	/** 更新内容 */
	private String versionContent;
	/** 安装包地址 */
	private String url;
	/** 是否强制升级 */
	private Integer isMandatoryUpgrade;
	/** 时间 */
	private String createTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setVersionNumber(Integer versionNumber) 
	{
		this.versionNumber = versionNumber;
	}

	public Integer getVersionNumber() 
	{
		return versionNumber;
	}
	public void setVersionName(String versionName) 
	{
		this.versionName = versionName;
	}

	public String getVersionName() 
	{
		return versionName;
	}
	public void setVersionContent(String versionContent) 
	{
		this.versionContent = versionContent;
	}

	public String getVersionContent() 
	{
		return versionContent;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getUrl() 
	{
		return url;
	}
	public void setIsMandatoryUpgrade(Integer isMandatoryUpgrade) 
	{
		this.isMandatoryUpgrade = isMandatoryUpgrade;
	}

	public Integer getIsMandatoryUpgrade() 
	{
		return isMandatoryUpgrade;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("versionNumber", getVersionNumber())
            .append("versionName", getVersionName())
            .append("versionContent", getVersionContent())
            .append("url", getUrl())
            .append("isMandatoryUpgrade", getIsMandatoryUpgrade())
            .toString();
    }
}
