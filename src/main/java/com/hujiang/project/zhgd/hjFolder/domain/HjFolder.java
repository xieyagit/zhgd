package com.hujiang.project.zhgd.hjFolder.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 文件夹表 hj_folder
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public class HjFolder
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 文件夹名 */
	private String folderName;
	/** 项目id */
	private Integer projectId;
	/** 创建时间 */
	private Date createTime;
	/** 父文件夹id */
	private Integer parentLevel;
	/** 子菜单 */
	private List<HjFolder> children = new ArrayList<HjFolder>();

	public List<HjFolder> getChildren() {
		return children;
	}

	public void setChildren(List<HjFolder> children) {
		this.children = children;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setFolderName(String folderName) 
	{
		this.folderName = folderName;
	}

	public String getFolderName() 
	{
		return folderName;
	}
	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setParentLevel(Integer parentLevel) 
	{
		this.parentLevel = parentLevel;
	}

	public Integer getParentLevel() 
	{
		return parentLevel;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("folderName", getFolderName())
            .append("projectId", getProjectId())
            .append("createTime", getCreateTime())
            .append("parentLevel", getParentLevel())
            .toString();
    }
}
