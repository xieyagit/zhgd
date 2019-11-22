package com.hujiang.project.zhgd.hjProjectImage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 项目效果图表 hj_project_image
 * 
 * @author hujiang
 * @date 2019-11-15
 */
public class HjProjectImage
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 项目id */
	private Integer projectId;
	/** 素材地址 */
	private String url;

	/** 类型，1，图片，2，视频，3，视频流地址 */
	private String type;
	/** 位置 */
	private Integer position;
	/** 所属模块  1看板轮播图， 2进度管理轮播图 */
	private String module;

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
	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getUrl() 
	{
		return url;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setPosition(Integer position) 
	{
		this.position = position;
	}

	public Integer getPosition() 
	{
		return position;
	}
	public void setModule(String module) 
	{
		this.module = module;
	}

	public String getModule() 
	{
		return module;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("url", getUrl())
            .append("type", getType())
            .append("position", getPosition())
            .append("module", getModule())
            .toString();
    }
}
