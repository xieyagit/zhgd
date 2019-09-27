package com.hujiang.project.zhgd.hjDictionaries.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 字典表 hj_dictionaries
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class HjDictionaries
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 标识（例：PROJECT_MUNICIPAL） */
	private String tag;
	/** 数据类型名称（例：项目类型） */
	private String groupTitle;
	/** 类别（例：PROJECT_TYPE） */
	private String category;
	/** 标题（例：市政、房建） */
	private String title;
	/** 创建时间 */
	private String createDate;
	/** 修改时间 */
	private String updateDate;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setTag(String tag) 
	{
		this.tag = tag;
	}

	public String getTag() 
	{
		return tag;
	}
	public void setGroupTitle(String groupTitle) 
	{
		this.groupTitle = groupTitle;
	}

	public String getGroupTitle() 
	{
		return groupTitle;
	}
	public void setCategory(String category) 
	{
		this.category = category;
	}

	public String getCategory() 
	{
		return category;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getCreateDate()
	{
		return createDate;
	}
	public void setUpdateDate(String updateDate)
	{
		this.updateDate = updateDate;
	}

	public String getUpdateDate()
	{
		return updateDate;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tag", getTag())
            .append("groupTitle", getGroupTitle())
            .append("category", getCategory())
            .append("title", getTitle())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
