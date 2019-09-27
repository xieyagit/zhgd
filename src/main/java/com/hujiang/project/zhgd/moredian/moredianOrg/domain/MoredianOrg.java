package com.hujiang.project.zhgd.moredian.moredianOrg.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 机构表 sb_moredian_org
 * 
 * @author hujiang
 * @date 2019-05-09
 */
public class MoredianOrg
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 机构名 */
	private String orgName;
	/** 地址 */
	private String address;
	/** 联系人 */
	private String contact;
	/** 电话 */
	private String phone;
	/** orgId */
	private String orgId;
	/** orgAuthKey */
	private String orgAuthKey;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setOrgName(String orgName) 
	{
		this.orgName = orgName;
	}

	public String getOrgName() 
	{
		return orgName;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setContact(String contact) 
	{
		this.contact = contact;
	}

	public String getContact() 
	{
		return contact;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setOrgId(String orgId) 
	{
		this.orgId = orgId;
	}

	public String getOrgId() 
	{
		return orgId;
	}
	public void setOrgAuthKey(String orgAuthKey) 
	{
		this.orgAuthKey = orgAuthKey;
	}

	public String getOrgAuthKey() 
	{
		return orgAuthKey;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orgName", getOrgName())
            .append("address", getAddress())
            .append("contact", getContact())
            .append("phone", getPhone())
            .append("orgId", getOrgId())
            .append("orgAuthKey", getOrgAuthKey())
            .toString();
    }
}
