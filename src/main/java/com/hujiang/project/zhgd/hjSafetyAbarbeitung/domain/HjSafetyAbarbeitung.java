package com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hujiang.framework.web.domain.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 整改表 hj_safety_abarbeitung
 * 
 * @author hujiang
 * @date 2019-07-10
 */
public class HjSafetyAbarbeitung
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 区分质量和安全巡检*/
	private Integer differentiate;
	/** 状态：1待整改/2待复查/3复查通过/4复查未通过/5超期未整改 */
	private Integer status;
	/**检查区域 */
	private Integer areaId;
	/** 发起整改人ID */
	private Integer initiatorId;
	/** 整改人ID */
	private Integer rectifyId;
	/** 复查人ID */
	private Integer reviewId;
	/** 责任分包单位ID */
	private Integer constructionId;
	/** 现场照片路径 */
	private String safetyPhotos;
	/** 安全隐患ID */
	private Integer hiddenId;
	/** 问题级别ID */
	private Integer problemGradeId;
	/** 整改问题描述 */
	private String safetyDescribe;
	/** 整改要求 */
	private String safetyRequire;
	/** 整改期限 */
	private String safetyDeadline;
	/** 发起整改时间 */
	private String initiatorTime;
	/** 整改创建时间 */
	private String creationTime;
	private Integer projectId;
	/** 抄送人 */
	private String makeId;

	private int result;

	private MultipartFile[] file;

	private int[] make;

	private Integer userId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getInitiatorId() {
		return initiatorId;
	}

	public void setInitiatorId(Integer initiatorId) {
		this.initiatorId = initiatorId;
	}

	public Integer getRectifyId() {
		return rectifyId;
	}

	public void setRectifyId(Integer rectifyId) {
		this.rectifyId = rectifyId;
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public Integer getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(Integer constructionId) {
		this.constructionId = constructionId;
	}

	public String getSafetyPhotos() {
		return safetyPhotos;
	}

	public void setSafetyPhotos(String safetyPhotos) {
		this.safetyPhotos = safetyPhotos;
	}

	public Integer getHiddenId() {
		return hiddenId;
	}

	public void setHiddenId(Integer hiddenId) {
		this.hiddenId = hiddenId;
	}

	public Integer getProblemGradeId() {
		return problemGradeId;
	}

	public void setProblemGradeId(Integer problemGradeId) {
		this.problemGradeId = problemGradeId;
	}

	public String getSafetyDescribe() {
		return safetyDescribe;
	}

	public void setSafetyDescribe(String safetyDescribe) {
		this.safetyDescribe = safetyDescribe;
	}

	public String getSafetyRequire() {
		return safetyRequire;
	}

	public void setSafetyRequire(String safetyRequire) {
		this.safetyRequire = safetyRequire;
	}

	public String getSafetyDeadline() {
		return safetyDeadline;
	}

	public void setSafetyDeadline(String safetyDeadline) {
		this.safetyDeadline = safetyDeadline;
	}

	public String getInitiatorTime() {
		return initiatorTime;
	}

	public void setInitiatorTime(String initiatorTime) {
		this.initiatorTime = initiatorTime;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public String getMakeId() {
		return makeId;
	}

	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}

	public MultipartFile[] getFile() {
		return file;
	}

	public void setFile(MultipartFile[] file) {
		this.file = file;
	}

	public int getDifferentiate() {
		return differentiate;
	}

	public void setDifferentiate(int differentiate) {
		this.differentiate = differentiate;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}




	public void setDifferentiate(Integer differentiate) {
		this.differentiate = differentiate;
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

	public int[] getMake() {
		return make;
	}

	public void setMake(int[] make) {
		this.make = make;
	}

	@Override
	public String toString() {
		return "HjSafetyAbarbeitung{" +
				"id=" + id +
				", differentiate=" + differentiate +
				", status=" + status +
				", areaId=" + areaId +
				", initiatorId=" + initiatorId +
				", rectifyId=" + rectifyId +
				", reviewId=" + reviewId +
				", constructionId=" + constructionId +
				", safetyPhotos='" + safetyPhotos + '\'' +
				", hiddenId=" + hiddenId +
				", problemGradeId=" + problemGradeId +
				", safetyDescribe='" + safetyDescribe + '\'' +
				", safetyRequire='" + safetyRequire + '\'' +
				", safetyDeadline='" + safetyDeadline + '\'' +
				", initiatorTime='" + initiatorTime + '\'' +
				", creationTime='" + creationTime + '\'' +
				", projectId=" + projectId +
				", makeId='" + makeId + '\'' +
				", result=" + result +
				", file=" + Arrays.toString(file) +
				", make=" + Arrays.toString(make) +
				", userId=" + userId +
				'}';
	}
}
