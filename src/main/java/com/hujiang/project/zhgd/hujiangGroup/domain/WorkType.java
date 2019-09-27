package com.hujiang.project.zhgd.hujiangGroup.domain;

import com.hujiang.framework.web.domain.BaseEntity;


public class WorkType extends BaseEntity {
	

	private Long id;

	private String title;			//工种名称


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
