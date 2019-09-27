package com.hujiang.project.zhgd.hjProjectWorkers.domain;

import java.util.Date;

/**
 * 出勤工作统计
 * 
 * @author hujiang
 * @date 2019-05-19
 */
public class Cqgztj
{
	private static final long serialVersionUID = 1L;

	Integer rs;//人数
	String jobNames;//工作名称

	public Integer getRs() {
		return rs;
	}

	public void setRs(Integer rs) {
		this.rs = rs;
	}

	public String getJobNames() {
		return jobNames;
	}

	public void setJobNames(String jobNames) {
		this.jobNames = jobNames;
	}
}
