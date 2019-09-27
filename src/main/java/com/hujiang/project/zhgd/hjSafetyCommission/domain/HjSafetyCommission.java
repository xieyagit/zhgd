package com.hujiang.project.zhgd.hjSafetyCommission.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

    
/**
 * 巡检通知页面数据表 hj_safety_commission
 * 
 * @author hujiang
 * @date 2019-08-01
 */
public class HjSafetyCommission
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 标题 */
	private String title;
	/** 发起整改人 */
	private String initiator;
	/** 整改人 */
	private String rectify;
	/** 创建时间 */
	private String createTime;
	/** 状态0未读1已读 */
	private Integer status;
	private Integer safetyId;

	private Integer projectId;
	private Integer userId;
	private Integer differentiate;

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
	public void setInitiator(String initiator)
	{
		this.initiator = initiator;
	}

	public String getInitiator()
	{
		return initiator;
	}
	public void setRectify(String rectify)
	{
		this.rectify = rectify;
	}

	public String getRectify()
	{
		return rectify;
	}
	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getCreateTime()
	{
		return createTime;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
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

	public Integer getStatus()
	{
		return status;
	}

	public Integer getDifferentiate() {
		return differentiate;
	}

	public void setDifferentiate(Integer differentiate) {
		this.differentiate = differentiate;
	}

	public Integer getSafetyId() {
		return safetyId;
	}

	public void setSafetyId(Integer safetyId) {
		this.safetyId = safetyId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "HjSafetyCommission{" +
				"id=" + id +
				", title='" + title + '\'' +
				", initiator='" + initiator + '\'' +
				", rectify='" + rectify + '\'' +
				", createTime='" + createTime + '\'' +
				", status=" + status +
				", safetyId=" + safetyId +
				", projectId=" + projectId +
				", userId=" + userId +
				", differentiate=" + differentiate +
				'}';
	}
}
