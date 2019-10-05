package com.hujiang.project.zhgd.sbProjectDustEmission.domain;

import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 项目对应的扬尘设备SN表 sb_project_dust_emission
 * 
 * @author hujiang
 * @date 2019-07-09
 */
public class SbProjectDustEmission
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 项目id */
	private Long projectId;
	/** 扬尘设备sn */
	private String sn;
	/** 扬尘设备备注 */
	private String comments;
	/** 设备类型 */
	private Integer type;
	/** 监测项 */
	private String meOption;
	/** 安装位置 */
	private String installAddress;
	/** 设备安装单位 */
	private String installCompany;
	/** 安装时间 */
	private String deviceInstallationDate;
	/** 视频地址 */
	private String videoAddress;
	/** 项目监督编号*/
	private String jdbh;
	/** 项目ID（城安院）*/
	private String xmid;
	/** 工程id（城安院）*/
	private String subId;
	/** 对接平台*/
	private String scznl;
	/** 设备厂商id*/
	private String manufacturerId;
	public String getXmid() {
		return xmid;
	}

	public void setXmid(String xmid) {
		this.xmid = xmid;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getScznl() {
		return scznl;
	}

	public void setScznl(String scznl) {
		this.scznl = scznl;
	}

	public String getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getJdbh() {
		return jdbh;
	}

	public void setJdbh(String jdbh) {
		this.jdbh = jdbh;
	}

	private List<SbDustEmission> dustEmissionList = new ArrayList<>();
	private HjProject project;

	public HjProject getProject() {
		return project;
	}

	public void setProject(HjProject project) {
		this.project = project;
	}

	public List<SbDustEmission> getDustEmissionList() {
		return dustEmissionList;
	}

	public void setDustEmissionList(List<SbDustEmission> dustEmissionList) {
		this.dustEmissionList = dustEmissionList;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setProjectId(Long projectId) 
	{
		this.projectId = projectId;
	}

	public Long getProjectId() 
	{
		return projectId;
	}
	public void setSn(String sn) 
	{
		this.sn = sn;
	}

	public String getSn() 
	{
		return sn;
	}
	public void setComments(String comments) 
	{
		this.comments = comments;
	}

	public String getComments() 
	{
		return comments;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}
	public void setMeOption(String meOption) 
	{
		this.meOption = meOption;
	}

	public String getMeOption() 
	{
		return meOption;
	}
	public void setInstallAddress(String installAddress) 
	{
		this.installAddress = installAddress;
	}

	public String getInstallAddress() 
	{
		return installAddress;
	}
	public void setInstallCompany(String installCompany) 
	{
		this.installCompany = installCompany;
	}

	public String getInstallCompany() 
	{
		return installCompany;
	}
	public void setDeviceInstallationDate(String deviceInstallationDate)
	{
		this.deviceInstallationDate = deviceInstallationDate;
	}

	public String getDeviceInstallationDate()
	{
		return deviceInstallationDate;
	}
	public void setVideoAddress(String videoAddress) 
	{
		this.videoAddress = videoAddress;
	}

	public String getVideoAddress() 
	{
		return videoAddress;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("sn", getSn())
            .append("comments", getComments())
            .append("type", getType())
            .append("meOption", getMeOption())
            .append("installAddress", getInstallAddress())
            .append("installCompany", getInstallCompany())
            .append("deviceInstallationDate", getDeviceInstallationDate())
            .append("videoAddress", getVideoAddress())
            .toString();
    }
}
