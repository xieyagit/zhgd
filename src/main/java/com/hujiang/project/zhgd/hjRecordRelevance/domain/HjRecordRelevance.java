package com.hujiang.project.zhgd.hjRecordRelevance.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 记录关联表 hj_record_relevance
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public class HjRecordRelevance
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 整改名称 */
	private String rectifyName;
	/** 整改期限 */
	private Date rectifyDate;
	/** 流程状态 */
	private Integer typeId;
	/** 问题严重外键 */
	private Integer ponderanceId;
	/** 安全隐患外键 */
	private Integer hiddenId;
	/** 检查区域 */
	private Integer checkArea;
	/** 所属巡检（0、安全巡检，1、质量巡检） */
	private Integer polling;
	/** 抄送人 */
	private String people;

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
	public void setTypeId(Integer typeId) 
	{
		this.typeId = typeId;
	}

	public Integer getTypeId() 
	{
		return typeId;
	}
	public void setPonderanceId(Integer ponderanceId) 
	{
		this.ponderanceId = ponderanceId;
	}

	public Integer getPonderanceId() 
	{
		return ponderanceId;
	}
	public void setHiddenId(Integer hiddenId) 
	{
		this.hiddenId = hiddenId;
	}

	public Integer getHiddenId() 
	{
		return hiddenId;
	}
	public void setCheckArea(Integer checkArea) 
	{
		this.checkArea = checkArea;
	}

	public Integer getCheckArea() 
	{
		return checkArea;
	}
	public void setPolling(Integer polling) 
	{
		this.polling = polling;
	}

	public Integer getPolling() 
	{
		return polling;
	}
	public void setPeople(String people) 
	{
		this.people = people;
	}

	public String getPeople() 
	{
		return people;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("rectifyName", getRectifyName())
            .append("rectifyDate", getRectifyDate())
            .append("typeId", getTypeId())
            .append("ponderanceId", getPonderanceId())
            .append("hiddenId", getHiddenId())
            .append("checkArea", getCheckArea())
            .append("polling", getPolling())
            .append("people", getPeople())
            .toString();
    }
}
