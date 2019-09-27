package com.hujiang.project.zhgd.hjExcessiveSafety.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 巡检短息记录表 hj_excessive_safety
 * 
 * @author hujiang
 * @date 2019-07-29
 */
public class HjExcessiveSafety
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 项目ID */
	private Integer projectId;
	/** 用户ID */
	private Integer userId;
	/** 内容 */
	private String content;
	/** 创建时间 */
	private String createTime;

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
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}

	public String getContent() 
	{
		return content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "HjExcessiveSafety{" +
				"id=" + id +
				", projectId=" + projectId +
				", userId=" + userId +
				", content='" + content + '\'' +
				", createTime='" + createTime + '\'' +
				'}';
	}
}
