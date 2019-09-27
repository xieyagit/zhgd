package com.hujiang.project.zhgd.moredian.moredianOrgCallback.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 回调表 sb_moredian_org_callback
 * 
 * @author hujiang
 * @date 2019-05-13
 */
public class MoredianOrgCallback
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 机构url */
	private String callbackUrl;
	/** 回调标志 */
	private String callbackTag;
	/** 机构id */
	private String orgId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCallbackUrl(String callbackUrl) 
	{
		this.callbackUrl = callbackUrl;
	}

	public String getCallbackUrl() 
	{
		return callbackUrl;
	}
	public void setCallbackTag(String callbackTag) 
	{
		this.callbackTag = callbackTag;
	}

	public String getCallbackTag() 
	{
		return callbackTag;
	}
	public void setOrgId(String orgId) 
	{
		this.orgId = orgId;
	}

	public String getOrgId() 
	{
		return orgId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("callbackUrl", getCallbackUrl())
            .append("callbackTag", getCallbackTag())
            .append("orgId", getOrgId())
            .toString();
    }
}
