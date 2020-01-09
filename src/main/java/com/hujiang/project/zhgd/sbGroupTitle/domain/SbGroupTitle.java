package com.hujiang.project.zhgd.sbGroupTitle.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 项目标题表 sb_group_title
 * 
 * @author hujiang
 * @date 2020-01-03
 */
public class SbGroupTitle
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer id;
	/** 集团id */
	private Integer cid;
	/** 名称 */
	private String cTitle;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setCid(Integer cid) 
	{
		this.cid = cid;
	}

	public Integer getCid() 
	{
		return cid;
	}
	public void setCTitle(String cTitle) 
	{
		this.cTitle = cTitle;
	}

	public String getCTitle() 
	{
		return cTitle;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cid", getCid())
            .append("cTitle", getCTitle())
            .toString();
    }
}
