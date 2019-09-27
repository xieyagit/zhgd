package com.hujiang.project.zhgd.hjReport.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 工作汇报表 hj_report
 *
 * @author hujiang
 * @date 2019-07-08
 */
public class HjReport
{
	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;
	/** 报告时间开始 */
	private String beginTime;
	/** 报告时间结束 */
	private String finishTime;
	/** 报告名称 */
	private String reportDesignation;
	/** 报告生成人 */
	private String reportName;
	/** 生成时间 */
	private Date reportDate;
	/** 报告类型（0、日报，1、周报，2、月报） */
	private Integer reportType;
	/** 两制问题 */
	private String issue;
	/** 解决方案 */
	private String solution;
	/** 备注 */
	private String remark;
	/**项目ID*/
	private Integer projectId;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}
	public void setBeginTime(String beginTime)
	{
		this.beginTime = beginTime;
	}

	public String getBeginTime()
	{
		return beginTime;
	}
	public void setFinishTime(String finishTime)
	{
		this.finishTime = finishTime;
	}

	public String getFinishTime()
	{
		return finishTime;
	}
	public void setReportDesignation(String reportDesignation)
	{
		this.reportDesignation = reportDesignation;
	}

	public String getReportDesignation()
	{
		return reportDesignation;
	}
	public void setReportName(String reportName)
	{
		this.reportName = reportName;
	}

	public String getReportName()
	{
		return reportName;
	}
	public void setReportDate(Date reportDate)
	{
		this.reportDate = reportDate;
	}

	public Date getReportDate()
	{
		return reportDate;
	}
	public void setReportType(Integer reportType)
	{
		this.reportType = reportType;
	}

	public Integer getReportType()
	{
		return reportType;
	}
	public void setIssue(String issue)
	{
		this.issue = issue;
	}

	public String getIssue()
	{
		return issue;
	}
	public void setSolution(String solution)
	{
		this.solution = solution;
	}

	public String getSolution()
	{
		return solution;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getRemark()
	{
		return remark;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("beginTime", getBeginTime())
				.append("finishTime", getFinishTime())
				.append("reportDesignation", getReportDesignation())
				.append("reportName", getReportName())
				.append("reportDate", getReportDate())
				.append("reportType", getReportType())
				.append("issue", getIssue())
				.append("solution", getSolution())
				.append("remark", getRemark())
				.toString();
	}
}
