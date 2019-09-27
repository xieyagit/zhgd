package com.hujiang.project.zhgd.moredian.moredianGroupPerson.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 群组人员表 sb_moredian_group_person
 * 
 * @author hujiang
 * @date 2019-05-09
 */
public class MoredianGroupPerson
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 魔点人员Id */
	private String memberId;
	/** 魔点群组ID */
	private String groupId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
	}
	public void setGroupId(String groupId) 
	{
		this.groupId = groupId;
	}

	public String getGroupId() 
	{
		return groupId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("groupId", getGroupId())
            .toString();
    }
}
