package com.hujiang.project.zhgd.hjWorkerRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

    
/**
 * 工人从业记录表 hj_worker_record
 * 
 * @author hujiang
 * @date 2019-05-17
 */
public class HjWorkerRecord
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 身份证号码 */
	private String idCode;
	/** 项目id */
	private Integer projectId;
	/** 参建单位id */
	private Integer constructionId;
	/** 班组id */
	private Integer teamId;
	/** 创建时间 */
	private String createDate;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setIdCode(String idCode) 
	{
		this.idCode = idCode;
	}

	public String getIdCode() 
	{
		return idCode;
	}
	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	public void setConstructionId(Integer constructionId) 
	{
		this.constructionId = constructionId;
	}

	public Integer getConstructionId() 
	{
		return constructionId;
	}
	public void setTeamId(Integer teamId) 
	{
		this.teamId = teamId;
	}

	public Integer getTeamId() 
	{
		return teamId;
	}
	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getCreateDate()
	{
		return createDate;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("idCode", getIdCode())
            .append("projectId", getProjectId())
            .append("constructionId", getConstructionId())
            .append("teamId", getTeamId())
            .append("createDate", getCreateDate())
            .toString();
    }
}
