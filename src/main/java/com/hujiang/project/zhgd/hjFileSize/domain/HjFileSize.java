package com.hujiang.project.zhgd.hjFileSize.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 项目存储空间大小表 hj_file_size
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public class HjFileSize
{
	private static final long serialVersionUID = 1L;
	
	/** 项目id */
	private Integer projectId;
	/** 存储空间大小M */
	private Long fileSize;

	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	public void setFileSize(Long fileSiz)
	{
		this.fileSize = fileSiz;
	}

	public Long getFileSize()
	{
		return fileSize;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId", getProjectId())
            .append("fileSize", getFileSize())
            .toString();
    }
}
