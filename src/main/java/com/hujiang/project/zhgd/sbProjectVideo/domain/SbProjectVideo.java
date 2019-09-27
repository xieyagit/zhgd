package com.hujiang.project.zhgd.sbProjectVideo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 项目视频表 sb_project_video
 * 
 * @author hujiang
 * @date 2019-06-23
 */
public class SbProjectVideo
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 项目视频区id */
	private Integer areaId;
	/** 视频地址 */
	private String url;
	/** 视频账户 */
	private String account;
	/** 摄像头名称 */
	private String videoName;
	/** 摄像头型号 */
	private String videoType;
	/** 摄像头所在位置 */
	private String videoAddress;
	/** 监控方式 */
	private String videoWay;
	/** 联系人 */
	private String contacts;
	/** 联系方式 */
	private String contactsWay;
	/** 所属部门 */
	private String department;
	/** 职位 */
	private String position;
	/** 所在位置照片 */
	private String photograph;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setAreaId(Integer areaId) 
	{
		this.areaId = areaId;
	}

	public Integer getAreaId() 
	{
		return areaId;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getUrl() 
	{
		return url;
	}
	public void setAccount(String account) 
	{
		this.account = account;
	}

	public String getAccount() 
	{
		return account;
	}
	public void setVideoName(String videoName) 
	{
		this.videoName = videoName;
	}

	public String getVideoName() 
	{
		return videoName;
	}
	public void setVideoType(String videoType) 
	{
		this.videoType = videoType;
	}

	public String getVideoType() 
	{
		return videoType;
	}
	public void setVideoAddress(String videoAddress) 
	{
		this.videoAddress = videoAddress;
	}

	public String getVideoAddress() 
	{
		return videoAddress;
	}
	public void setVideoWay(String videoWay) 
	{
		this.videoWay = videoWay;
	}

	public String getVideoWay() 
	{
		return videoWay;
	}
	public void setContacts(String contacts) 
	{
		this.contacts = contacts;
	}

	public String getContacts() 
	{
		return contacts;
	}
	public void setContactsWay(String contactsWay) 
	{
		this.contactsWay = contactsWay;
	}

	public String getContactsWay() 
	{
		return contactsWay;
	}
	public void setDepartment(String department) 
	{
		this.department = department;
	}

	public String getDepartment() 
	{
		return department;
	}
	public void setPosition(String position) 
	{
		this.position = position;
	}

	public String getPosition() 
	{
		return position;
	}
	public void setPhotograph(String photograph) 
	{
		this.photograph = photograph;
	}

	public String getPhotograph() 
	{
		return photograph;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("areaId", getAreaId())
            .append("url", getUrl())
            .append("account", getAccount())
            .append("videoName", getVideoName())
            .append("videoType", getVideoType())
            .append("videoAddress", getVideoAddress())
            .append("videoWay", getVideoWay())
            .append("contacts", getContacts())
            .append("contactsWay", getContactsWay())
            .append("department", getDepartment())
            .append("position", getPosition())
            .append("photograph", getPhotograph())
            .toString();
    }
}
