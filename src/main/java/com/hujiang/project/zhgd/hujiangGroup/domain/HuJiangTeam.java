package com.hujiang.project.zhgd.hujiangGroup.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hujiang.framework.web.domain.BaseEntity;

import java.util.Date;


/**
 * 班组表 hujiang_team
 * 
 * @author hujiang
 * @date 2019-04-18
 */
public class HuJiangTeam extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/**  */
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date entranceDate;
	/**  */
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date retreatDate;
	/**  */
	private String groupTitle;
	/**  */
	private String projectTitle;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEntranceDate() {
		return entranceDate;
	}

	public void setEntranceDate(Date entranceDate) {
		this.entranceDate = entranceDate;
	}

	public Date getRetreatDate() {
		return retreatDate;
	}

	public void setRetreatDate(Date retreatDate) {
		this.retreatDate = retreatDate;
	}

	public String getGroupTitle() {
		return groupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
}
