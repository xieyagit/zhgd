package com.hujiang.project.zhgd.sbExcessiveDust.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

    
/**
 * 扬尘超标记录表 sb_excessive_dust
 * 
 * @author hujiang
 * @date 2019-07-25
 */
public class SbExcessiveDust
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 项目ID*/
	private Integer projectId;
	/** 设备sn*/
	private String sn;
	/** 用户ID */
	private int userId;
	/** 标题 */
	private String title;
	/** 内容 */
	private String alert;
	/** 时间 */
	private String createTime;
	/** 严重级别 */
	private String grade;

	private Integer status;
	private Integer privilegesId;


	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setAlert(String alert) 
	{
		this.alert = alert;
	}

	public String getAlert() 
	{
		return alert;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setGrade(String grade)
	{
		this.grade = grade;
	}

	public String getGrade() 
	{
		return grade;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPrivilegesId() {
		return privilegesId;
	}

	public void setPrivilegesId(Integer privilegesId) {
		this.privilegesId = privilegesId;
	}

	@Override
	public String toString() {
		return "SbExcessiveDust{" +
				"id=" + id +
				", projectId=" + projectId +
				", sn='" + sn + '\'' +
				", userId=" + userId +
				", title='" + title + '\'' +
				", alert='" + alert + '\'' +
				", createTime='" + createTime + '\'' +
				", grade='" + grade + '\'' +
				", status=" + status +
				'}';
	}
}
