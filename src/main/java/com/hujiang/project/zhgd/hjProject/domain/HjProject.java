package com.hujiang.project.zhgd.hjProject.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

    
/**
 * 项目表 hj_project
 * 
 * @author hujiang
 * @date 2019-05-24
 */
public class HjProject
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 项目名 */
	private String projectName;
	/** 简称 */
	private String shortName;
	/** 项目负责人 */
	private String projectPrincipal;
	/** 联系方式 */
	private String phone;
	/** 项目类型（从字典获取相应数据） */
	private String projectType;
	/** 项目状态 */
	private String projectState;
	/** 项目管理人数 */
	private Integer projectNumber;
	/** 所属地区（三级联动） */
	private String projectRegion;
	/** 施工许可证 */
	private String builderLicense;
	/** 项目地址 */
	private String projectAddress;
	/** 起始时间 */
	private String startingTime;
	/** 结束时间 */
	private String finishTime;
	/** 施工企业（公司库获取） */
	private Integer constructionId;
	/** 监理企业（公司库获取） */
	private Integer supervisorId;
	/** 建筑面积(平米) */
	private String acreage;
	/** 工程造价(万元) */
	private String projectCost;
	/** 经度 */
	private String longitude;
	/** 纬度 */
	private String latitude;
	/** 安全报监编号 */
	private String securityCode;
	/** 质量报监编号 */
	private String qualityNumber;
	/** 设计单位 */
	private String designOrganization;
	/** 勘察单位 */
	private String explorationUnit;
	/** 备注 */
	private String remark;
	/** 项目效果图 */
	private String projectImage;
	/** 状态（0.显示 1.隐藏） */
	private Integer showState;
	/** 创建时间 */
	private String createDate;
	/** 修改时间 */
	private String updateDate;
	/** 人脸库编号 */
	private String faceGroup;

	//总包单位
	private String constructionName;
	//分包单位
	private String name;
	//上传返回唯一编号
	private Integer itemId;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setProjectName(String projectName) 
	{
		this.projectName = projectName;
	}

	public String getProjectName() 
	{
		return projectName;
	}
	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}

	public String getShortName() 
	{
		return shortName;
	}
	public void setProjectPrincipal(String projectPrincipal) 
	{
		this.projectPrincipal = projectPrincipal;
	}

	public String getProjectPrincipal() 
	{
		return projectPrincipal;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setProjectType(String projectType) 
	{
		this.projectType = projectType;
	}

	public String getProjectType() 
	{
		return projectType;
	}
	public void setProjectState(String projectState) 
	{
		this.projectState = projectState;
	}

	public String getProjectState() 
	{
		return projectState;
	}
	public void setProjectNumber(Integer projectNumber) 
	{
		this.projectNumber = projectNumber;
	}

	public Integer getProjectNumber() 
	{
		return projectNumber;
	}
	public void setProjectRegion(String projectRegion) 
	{
		this.projectRegion = projectRegion;
	}

	public String getProjectRegion() 
	{
		return projectRegion;
	}
	public void setBuilderLicense(String builderLicense) 
	{
		this.builderLicense = builderLicense;
	}

	public String getBuilderLicense() 
	{
		return builderLicense;
	}
	public void setProjectAddress(String projectAddress) 
	{
		this.projectAddress = projectAddress;
	}

	public String getProjectAddress() 
	{
		return projectAddress;
	}
	public void setStartingTime(String startingTime)
	{
		this.startingTime = startingTime;
	}

	public String getStartingTime()
	{
		return startingTime;
	}
	public void setFinishTime(String finishTime)
	{
		this.finishTime = finishTime;
	}

	public String getFinishTime()
	{
		return finishTime;
	}
	public void setConstructionId(Integer constructionId) 
	{
		this.constructionId = constructionId;
	}

	public Integer getConstructionId() 
	{
		return constructionId;
	}
	public void setSupervisorId(Integer supervisorId) 
	{
		this.supervisorId = supervisorId;
	}

	public Integer getSupervisorId() 
	{
		return supervisorId;
	}
	public void setAcreage(String acreage) 
	{
		this.acreage = acreage;
	}

	public String getAcreage() 
	{
		return acreage;
	}
	public void setProjectCost(String projectCost) 
	{
		this.projectCost = projectCost;
	}

	public String getProjectCost() 
	{
		return projectCost;
	}
	public void setLongitude(String longitude) 
	{
		this.longitude = longitude;
	}

	public String getLongitude() 
	{
		return longitude;
	}
	public void setLatitude(String latitude) 
	{
		this.latitude = latitude;
	}

	public String getLatitude() 
	{
		return latitude;
	}
	public void setSecurityCode(String securityCode) 
	{
		this.securityCode = securityCode;
	}

	public String getSecurityCode() 
	{
		return securityCode;
	}
	public void setQualityNumber(String qualityNumber) 
	{
		this.qualityNumber = qualityNumber;
	}

	public String getQualityNumber() 
	{
		return qualityNumber;
	}
	public void setDesignOrganization(String designOrganization) 
	{
		this.designOrganization = designOrganization;
	}

	public String getDesignOrganization() 
	{
		return designOrganization;
	}
	public void setExplorationUnit(String explorationUnit) 
	{
		this.explorationUnit = explorationUnit;
	}

	public String getExplorationUnit() 
	{
		return explorationUnit;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setProjectImage(String projectImage) 
	{
		this.projectImage = projectImage;
	}

	public String getProjectImage() 
	{
		return projectImage;
	}
	public void setShowState(Integer showState) 
	{
		this.showState = showState;
	}

	public Integer getShowState() 
	{
		return showState;
	}
	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getCreateDate()
	{
		return createDate;
	}
	public void setUpdateDate(String updateDate) 
	{
		this.updateDate = updateDate;
	}

	public String getUpdateDate() 
	{
		return updateDate;
	}
	public void setFaceGroup(String faceGroup) 
	{
		this.faceGroup = faceGroup;
	}

	public String getFaceGroup() 
	{
		return faceGroup;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectName", getProjectName())
            .append("shortName", getShortName())
            .append("projectPrincipal", getProjectPrincipal())
            .append("phone", getPhone())
            .append("projectType", getProjectType())
            .append("projectState", getProjectState())
            .append("projectNumber", getProjectNumber())
            .append("projectRegion", getProjectRegion())
            .append("builderLicense", getBuilderLicense())
            .append("projectAddress", getProjectAddress())
            .append("startingTime", getStartingTime())
            .append("finishTime", getFinishTime())
            .append("constructionId", getConstructionId())
            .append("supervisorId", getSupervisorId())
            .append("acreage", getAcreage())
            .append("projectCost", getProjectCost())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("securityCode", getSecurityCode())
            .append("qualityNumber", getQualityNumber())
            .append("designOrganization", getDesignOrganization())
            .append("explorationUnit", getExplorationUnit())
            .append("remark", getRemark())
            .append("projectImage", getProjectImage())
            .append("showState", getShowState())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("faceGroup", getFaceGroup())
            .append("itemId", getItemId())
            .toString();
    }
}
