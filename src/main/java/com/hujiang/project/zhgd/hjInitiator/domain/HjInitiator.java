package com.hujiang.project.zhgd.hjInitiator.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 发起问题记录表 hj_initiator
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public class HjInitiator
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 问题ID */
	private Integer relevanceId;
	/** 发起人问题描述 */
	private String sponsorProblem;
	/** 发起人姓名 */
	private String initiatorName;
	/** 整改发起时间 */
	private Date startingDate;
	/** 问题照片 */
	private String problemImg;
	/** 问题备注 */
	private String problemRemarks;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setRelevanceId(Integer relevanceId) 
	{
		this.relevanceId = relevanceId;
	}

	public Integer getRelevanceId() 
	{
		return relevanceId;
	}
	public void setSponsorProblem(String sponsorProblem) 
	{
		this.sponsorProblem = sponsorProblem;
	}

	public String getSponsorProblem() 
	{
		return sponsorProblem;
	}
	public void setInitiatorName(String initiatorName) 
	{
		this.initiatorName = initiatorName;
	}

	public String getInitiatorName() 
	{
		return initiatorName;
	}
	public void setStartingDate(Date startingDate) 
	{
		this.startingDate = startingDate;
	}

	public Date getStartingDate() 
	{
		return startingDate;
	}
	public void setProblemImg(String problemImg) 
	{
		this.problemImg = problemImg;
	}

	public String getProblemImg() 
	{
		return problemImg;
	}
	public void setProblemRemarks(String problemRemarks) 
	{
		this.problemRemarks = problemRemarks;
	}

	public String getProblemRemarks() 
	{
		return problemRemarks;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("relevanceId", getRelevanceId())
            .append("sponsorProblem", getSponsorProblem())
            .append("initiatorName", getInitiatorName())
            .append("startingDate", getStartingDate())
            .append("problemImg", getProblemImg())
            .append("problemRemarks", getProblemRemarks())
            .toString();
    }
}
