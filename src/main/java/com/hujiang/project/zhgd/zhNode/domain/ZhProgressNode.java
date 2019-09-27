package com.hujiang.project.zhgd.zhNode.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 进度节点表 zh_progress_node
 * 
 * @author hujiang
 * @date 2019-08-06
 */
public class ZhProgressNode
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 进度计划id */
	private Integer progressId;
	/** 节点id */
	private Integer nodeId;
	/** 节点占进度百分比(%) */
	private Integer nodeProgressRatio;
	/** 进度占节点百分比(%) */
	private Integer progressNodeRatio;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setProgressId(Integer progressId) 
	{
		this.progressId = progressId;
	}

	public Integer getProgressId() 
	{
		return progressId;
	}
	public void setNodeId(Integer nodeId) 
	{
		this.nodeId = nodeId;
	}

	public Integer getNodeId() 
	{
		return nodeId;
	}
	public void setNodeProgressRatio(Integer nodeProgressRatio) 
	{
		this.nodeProgressRatio = nodeProgressRatio;
	}

	public Integer getNodeProgressRatio() 
	{
		return nodeProgressRatio;
	}
	public void setProgressNodeRatio(Integer progressNodeRatio) 
	{
		this.progressNodeRatio = progressNodeRatio;
	}

	public Integer getProgressNodeRatio() 
	{
		return progressNodeRatio;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("progressId", getProgressId())
            .append("nodeId", getNodeId())
            .append("nodeProgressRatio", getNodeProgressRatio())
            .append("progressNodeRatio", getProgressNodeRatio())
            .toString();
    }
}
