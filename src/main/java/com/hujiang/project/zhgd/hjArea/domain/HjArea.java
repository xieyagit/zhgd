package com.hujiang.project.zhgd.hjArea.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 城市表 hj_area
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class HjArea
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Long id;
	/** 名称 */
	private String title;
	/** 类型 */
	private String type;
	/** 上级id */
	private Long parentId;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setParentId(Long parentId) 
	{
		this.parentId = parentId;
	}

	public Long getParentId() 
	{
		return parentId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("type", getType())
            .append("parentId", getParentId())
            .toString();
    }
}
