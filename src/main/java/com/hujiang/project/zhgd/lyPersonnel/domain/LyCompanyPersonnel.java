package com.hujiang.project.zhgd.lyPersonnel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 公司人员
 * 
 * @author hujiang
 * @date 2020-03-06
 */
public class LyCompanyPersonnel
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 名字 */
	private String companyName;
	private Integer size;//公司人数
	private List<LyPersonnel> pList;//公司人员

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public List<LyPersonnel> getpList() {
		return pList;
	}

	public void setpList(List<LyPersonnel> pList) {
		this.pList = pList;
	}
}
