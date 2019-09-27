package com.hujiang.project.zhgd.hjDeeppit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 深基坑结构物-项目表 sb_project_deeppit_structures
 * 
 * @author hujiang
 * @date 2019-09-02
 */
public class SbProjectDeeppitStructures
{
	private static final long serialVersionUID = 1L;

	/** id */
	private Integer id;
	/** 结构件id */
	private Integer structuresId;
	/** 公司id */
	private Integer projectId;
	/** usserId */
	private Integer userId;
	/** appId */
	private String appD;
	/** appSecret */
	private String appSecret;
	/** 供应商id */
	private int supplier;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStructuresId() {
		return structuresId;
	}

	public void setStructuresId(Integer structuresId) {
		this.structuresId = structuresId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAppD() {
		return appD;
	}

	public void setAppD(String appD) {
		this.appD = appD;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public int getSupplier() {
		return supplier;
	}

	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "SbProjectDeeppitStructures{" +
				"id=" + id +
				", structuresId=" + structuresId +
				", projectId=" + projectId +
				", userId=" + userId +
				", appD='" + appD + '\'' +
				", appSecret='" + appSecret + '\'' +
				", supplier='" + supplier + '\'' +
				'}';
	}
}
