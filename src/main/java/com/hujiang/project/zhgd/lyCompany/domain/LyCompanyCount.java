package com.hujiang.project.zhgd.lyCompany.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * 
 * @author hujiang
 * @date 2020-03-05
 */
public class LyCompanyCount
{
	private static final long serialVersionUID = 1L;
	
	private String companyName;//公司名称
	private String kqrs;//考勤人数
	private String zrs;//总人数


	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getKqrs() {
		return kqrs;
	}

	public void setKqrs(String kqrs) {
		this.kqrs = kqrs;
	}

	public String getZrs() {
		return zrs;
	}

	public void setZrs(String zrs) {
		this.zrs = zrs;
	}
}
