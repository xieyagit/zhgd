package com.hujiang.project.zhgd.hjRectify.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 整改记录表 hj_rectify
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public class HjRectify
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 整改人 */
	private String rectifyName;
	/** 整改时间 */
	private Date rectifyDate;
	/** 整改人所属公司 */
	private String rectifyCompany;
	/** 整改描述 */
	private String rectifyProblem;
	/** 整改照片 */
	private String rectifyImg;
	/** 整改备注 */
	private String rectifgRemarks;
	/**  */
	private Integer relevanceId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setRectifyName(String rectifyName) 
	{
		this.rectifyName = rectifyName;
	}

	public String getRectifyName() 
	{
		return rectifyName;
	}
	public void setRectifyDate(Date rectifyDate) 
	{
		this.rectifyDate = rectifyDate;
	}

	public Date getRectifyDate() 
	{
		return rectifyDate;
	}
	public void setRectifyCompany(String rectifyCompany) 
	{
		this.rectifyCompany = rectifyCompany;
	}

	public String getRectifyCompany() 
	{
		return rectifyCompany;
	}
	public void setRectifyProblem(String rectifyProblem) 
	{
		this.rectifyProblem = rectifyProblem;
	}

	public String getRectifyProblem() 
	{
		return rectifyProblem;
	}
	public void setRectifyImg(String rectifyImg) 
	{
		this.rectifyImg = rectifyImg;
	}

	public String getRectifyImg() 
	{
		return rectifyImg;
	}
	public void setRectifgRemarks(String rectifgRemarks) 
	{
		this.rectifgRemarks = rectifgRemarks;
	}

	public String getRectifgRemarks() 
	{
		return rectifgRemarks;
	}
	public void setRelevanceId(Integer relevanceId) 
	{
		this.relevanceId = relevanceId;
	}

	public Integer getRelevanceId() 
	{
		return relevanceId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rectifyName", getRectifyName())
            .append("rectifyDate", getRectifyDate())
            .append("rectifyCompany", getRectifyCompany())
            .append("rectifyProblem", getRectifyProblem())
            .append("rectifyImg", getRectifyImg())
            .append("rectifgRemarks", getRectifgRemarks())
            .append("relevanceId", getRelevanceId())
            .toString();
    }
}
