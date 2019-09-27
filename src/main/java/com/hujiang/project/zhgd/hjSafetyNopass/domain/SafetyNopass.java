package com.hujiang.project.zhgd.hjSafetyNopass.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 整改未通过表 hj_safety_nopass
 * 
 * @author hujiang
 * @date 2019-07-18
 */
public class SafetyNopass
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 整改ID */
	private Integer safetyId;
	/** 整改几次 */
	private String safetyName;
	/** 创建时间 */
	private String safetyCreateTime;
	/** 整改结果 */
	private String rectifyResult;
	/** 整改后现场照片 */
	private String rectifyPhotos;
	/** 整改后时间 */
	private String rectifyTime;
	/** 复查意见 */
	private String reviewOpinions;
	/** 复查结果 */
	private String reviewResult;
	/** 复查后照片 */
	private String reviewPath;
	/** 复查时间 */
	private String reviewTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setSafetyId(Integer safetyId) 
	{
		this.safetyId = safetyId;
	}

	public Integer getSafetyId() 
	{
		return safetyId;
	}
	public void setSafetyName(String safetyName) 
	{
		this.safetyName = safetyName;
	}

	public String getSafetyName() 
	{
		return safetyName;
	}
	public void setSafetyCreateTime(String safetyCreateTime) 
	{
		this.safetyCreateTime = safetyCreateTime;
	}

	public String getSafetyCreateTime() 
	{
		return safetyCreateTime;
	}
	public void setRectifyResult(String rectifyResult) 
	{
		this.rectifyResult = rectifyResult;
	}

	public String getRectifyResult() 
	{
		return rectifyResult;
	}
	public void setRectifyPhotos(String rectifyPhotos) 
	{
		this.rectifyPhotos = rectifyPhotos;
	}

	public String getRectifyPhotos() 
	{
		return rectifyPhotos;
	}
	public void setRectifyTime(String rectifyTime) 
	{
		this.rectifyTime = rectifyTime;
	}

	public String getRectifyTime() 
	{
		return rectifyTime;
	}
	public void setReviewOpinions(String reviewOpinions) 
	{
		this.reviewOpinions = reviewOpinions;
	}

	public String getReviewOpinions() 
	{
		return reviewOpinions;
	}
	public void setReviewResult(String reviewResult) 
	{
		this.reviewResult = reviewResult;
	}

	public String getReviewResult() 
	{
		return reviewResult;
	}
	public void setReviewPath(String reviewPath) 
	{
		this.reviewPath = reviewPath;
	}

	public String getReviewPath() 
	{
		return reviewPath;
	}
	public void setReviewTime(String reviewTime) 
	{
		this.reviewTime = reviewTime;
	}

	public String getReviewTime() 
	{
		return reviewTime;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("safetyId", getSafetyId())
            .append("safetyName", getSafetyName())
            .append("safetyCreateTime", getSafetyCreateTime())
            .append("rectifyResult", getRectifyResult())
            .append("rectifyPhotos", getRectifyPhotos())
            .append("rectifyTime", getRectifyTime())
            .append("reviewOpinions", getReviewOpinions())
            .append("reviewResult", getReviewResult())
            .append("reviewPath", getReviewPath())
            .append("reviewTime", getReviewTime())
            .toString();
    }
}
