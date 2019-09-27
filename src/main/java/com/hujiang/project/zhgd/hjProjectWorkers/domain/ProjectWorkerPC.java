package com.hujiang.project.zhgd.hjProjectWorkers.domain;

import com.hujiang.framework.aspectj.lang.annotation.Excel;

import java.util.Date;

/**
 * 项目工人表 hj_project_workers
 * 
 * @author hujiang
 * @date 2019-05-19
 */
public class ProjectWorkerPC
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	@Excel(name = "员工编号")
	private Integer id;
	/** 姓名 */
	@Excel(name = "姓名")
	private String empName;
	//班组名
	@Excel(name = "班组名")
	private String teamName;
	//参建单位名
	@Excel(name = "参建单位名")
	private String constructionName;
	//项目名
	@Excel(name = "项目名")
	private String projectName;
	//工种名
	@Excel(name = "工种名")
	private String title;
	//是否班组长
	@Excel(name = "是否班组长",readConverterExp = "0=否,1=是")
	private Integer isTeam;
	/** 进退场状态（0、进场。1、退场,2未同步） */
	@Excel(name = "进退场状态",readConverterExp = "0=进场,1=退场,2=未同步")
	private Integer enterAndRetreatCondition;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIsTeam() {
		return isTeam;
	}

	public void setIsTeam(Integer isTeam) {
		this.isTeam = isTeam;
	}

	public Integer getEnterAndRetreatCondition() {
		return enterAndRetreatCondition;
	}

	public void setEnterAndRetreatCondition(Integer enterAndRetreatCondition) {
		this.enterAndRetreatCondition = enterAndRetreatCondition;
	}

	@Override
	public String toString() {
		return "ProjectWorkerPC{" +
				"id=" + id +
				", empName='" + empName + '\'' +
				", teamName='" + teamName + '\'' +
				", constructionName='" + constructionName + '\'' +
				", projectName='" + projectName + '\'' +
				", title='" + title + '\'' +
				", isTeam=" + isTeam +
				", enterAndRetreatCondition=" + enterAndRetreatCondition +
				'}';
	}
}
