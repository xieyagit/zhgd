package com.hujiang.project.zhgd.hjTeam.domain;

import com.hujiang.project.zhgd.hjConstructionCompany.domain.HjConstructionCompany;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

    
/**
 * 班组表 hj_team
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class HjTeam
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 班组名称 */
	private String teamName;
	/** 参建单位id */
	private Integer constructionId;
	/** 项目ID */
	private Integer projectId;
	/** 创建时间 */
	private String createDate;
	/** 修改时间 */
	private String updateDate;
	/** 备注 */
	private String remark;
	/** 项目名称*/
	private String projectName;
	/** 参建单位名称 */
	private String companyName;
	//班组类型
	private String teamType;
	//班组id(工务署专属id)
	private String teamId;
	//班组编号
	private String teamSysno;

	//班组名称
	private String constructionName;

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public String getTeamType() {
		return teamType;
	}

	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setTeamName(String teamName) 
	{
		this.teamName = teamName;
	}

	public String getTeamName() 
	{
		return teamName;
	}
	public void setConstructionId(Integer constructionId) 
	{
		this.constructionId = constructionId;
	}

	public Integer getConstructionId() 
	{
		return constructionId;
	}
	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
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

	public String getTeamSysno() {
		return teamSysno;
	}

	public void setTeamSysno(String teamSysno) {
		this.teamSysno = teamSysno;
	}

	@Override
	public String toString() {
		return "HjTeam{" +
				"id=" + id +
				", teamName='" + teamName + '\'' +
				", constructionId=" + constructionId +
				", projectId=" + projectId +
				", createDate='" + createDate + '\'' +
				", updateDate='" + updateDate + '\'' +
				", remark='" + remark + '\'' +
				", projectName='" + projectName + '\'' +
				", companyName='" + companyName + '\'' +
				", teamType='" + teamType + '\'' +
				", teamId='" + teamId + '\'' +
				", constructionName='" + constructionName + '\'' +
				'}';
	}
}
