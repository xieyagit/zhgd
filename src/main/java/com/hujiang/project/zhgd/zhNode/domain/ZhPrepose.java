package com.hujiang.project.zhgd.zhNode.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 节点关联表 zh_prepose
 * 
 * @author hujiang
 * @date 2019-08-05
 */
public class ZhPrepose
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 主节点id */
	private Integer mainPlan;
	/** 前置节点id */
	private Integer preposePian;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setMainPlan(Integer mainPlan) 
	{
		this.mainPlan = mainPlan;
	}

	public Integer getMainPlan() 
	{
		return mainPlan;
	}
	public void setPreposePian(Integer preposePian) 
	{
		this.preposePian = preposePian;
	}

	public Integer getPreposePian() 
	{
		return preposePian;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mainPlan", getMainPlan())
            .append("preposePian", getPreposePian())
            .toString();
    }
}
