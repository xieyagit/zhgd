package com.hujiang.project.zhgd.moredian.moredianPerson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 人员表 sb_moredian_person
 * 
 * @author hujiang
 * @date 2019-05-11
 */
public class MoredianPerson
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 人员id */
	private Integer tpUserId;
	/** 人员名称 */
	private String memberName;
	/** 识别图片url */
	private String verifyFace;
	/** 手机号 */
	private String mobile;
	/** 魔点人员Id */
	private String memberId;
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
	public void setTpUserId(Integer tpUserId) 
	{
		this.tpUserId = tpUserId;
	}

	public Integer getTpUserId() 
	{
		return tpUserId;
	}
	public void setMemberName(String memberName) 
	{
		this.memberName = memberName;
	}

	public String getMemberName() 
	{
		return memberName;
	}
	public void setVerifyFace(String verifyFace) 
	{
		this.verifyFace = verifyFace;
	}

	public String getVerifyFace() 
	{
		return verifyFace;
	}
	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}

	public String getMobile() 
	{
		return mobile;
	}
	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
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
            .append("tpUserId", getTpUserId())
            .append("memberName", getMemberName())
            .append("verifyFace", getVerifyFace())
            .append("mobile", getMobile())
            .append("memberId", getMemberId())
            .append("orgId", getOrgId())
            .toString();
    }
}
