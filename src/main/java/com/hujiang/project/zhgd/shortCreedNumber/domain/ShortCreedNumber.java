package com.hujiang.project.zhgd.shortCreedNumber.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 项目短信条数表 short_creed_number
 * 
 * @author hujiang
 * @date 2019-08-01
 */
public class ShortCreedNumber
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Integer projectId;
	/** 剩余短信条数 */
	private Integer noteNumber;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	public void setNoteNumber(Integer noteNumber) 
	{
		this.noteNumber = noteNumber;
	}

	public Integer getNoteNumber() 
	{
		return noteNumber;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("noteNumber", getNoteNumber())
            .toString();
    }
}
