package com.hujiang.project.zhgd.hjInspector.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 检查记录表 hj_inspector
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public class HjInspector
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 问题ID| */
	private Integer relevanceId;
	/** 检查人姓名 */
	private String inspectorName;
	/** 检查时间 */
	private Date inspectorDate;
	/** 检查描述 */
	private String inspectorProblem;
	/** 检查图片 */
	private String inspectorImg;
	/** 检查备注 */
	private String inspectorRemarks;
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
	public void setRelevanceId(Integer relevanceId) 
	{
		this.relevanceId = relevanceId;
	}

	public Integer getRelevanceId() 
	{
		return relevanceId;
	}
	public void setInspectorName(String inspectorName) 
	{
		this.inspectorName = inspectorName;
	}

	public String getInspectorName() 
	{
		return inspectorName;
	}
	public void setInspectorDate(Date inspectorDate) 
	{
		this.inspectorDate = inspectorDate;
	}

	public Date getInspectorDate() 
	{
		return inspectorDate;
	}
	public void setInspectorProblem(String inspectorProblem) 
	{
		this.inspectorProblem = inspectorProblem;
	}

	public String getInspectorProblem() 
	{
		return inspectorProblem;
	}
	public void setInspectorImg(String inspectorImg) 
	{
		this.inspectorImg = inspectorImg;
	}

	public String getInspectorImg() 
	{
		return inspectorImg;
	}
	public void setInspectorRemarks(String inspectorRemarks) 
	{
		this.inspectorRemarks = inspectorRemarks;
	}

	public String getInspectorRemarks() 
	{
		return inspectorRemarks;
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
            .append("relevanceId", getRelevanceId())
            .append("inspectorName", getInspectorName())
            .append("inspectorDate", getInspectorDate())
            .append("inspectorProblem", getInspectorProblem())
            .append("inspectorImg", getInspectorImg())
            .append("inspectorRemarks", getInspectorRemarks())
            .append("people", getPeople())
            .toString();
    }
}
