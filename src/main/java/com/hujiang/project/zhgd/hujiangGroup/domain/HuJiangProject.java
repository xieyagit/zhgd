package com.hujiang.project.zhgd.hujiangGroup.domain;

import com.hujiang.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 项目表 hujiang_project
 * 
 * @author hujiang
 * @date 2019-04-18
 */
public class HuJiangProject extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/**  */
	private String buildLicense;
	/**  */
	private String builtUpArea;
	/**  */
	private String constructionUnitName;
	/**  */
	private Date createTime;
	/**  */
	private String designUnitame;
	/**  */
	private Date finishDate;
	/**  */
	private String groupId;
	/**  */
	private Integer numberOfLayers;
	/**  */
	private String projectAddress;
	/**  */
	private Integer projectCost;
	/**  */
	private String projectId;
	/**  */
	private String qualityReportNumber;
	/**  */
	private String remarks;
	/**  */
	private String safetyReportNumber;
	/**  */
	private String shortTitle;
	/**  */
	private Date startDate;
	/**  */
	private String supervisoryAgency;
	/**  */
	private String surveyUnit;
	/**  */
	private String title;
	/**  */
	private Long areaId;
	/**  */
	private Long companyId;
	/**  */
	private Long projectadminId;
	/**  */
	private String projecttypeId;
	/**  */
	private String statusId;
	/**  */
	private String structuretypeId;
	/**  */
	private Long supervisorId;
	/**  */
	private String apiKey;
	/**  */
	private String apiSecret;
	/**  */
	private String clientSerila;
	/**  */
	private Integer numberManager;
	/** 保证金是否上传（0否 1是） */
	private Boolean cashDeposit;
	/** 工资发放人数 */
	private Integer payoffSum;
	/** 三方协议是否上传（0否 1是） */
	private Boolean tripartiteAgreement;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setBuildLicense(String buildLicense) 
	{
		this.buildLicense = buildLicense;
	}

	public String getBuildLicense() 
	{
		return buildLicense;
	}
	public void setBuiltUpArea(String builtUpArea) 
	{
		this.builtUpArea = builtUpArea;
	}

	public String getBuiltUpArea() 
	{
		return builtUpArea;
	}
	public void setConstructionUnitName(String constructionUnitName) 
	{
		this.constructionUnitName = constructionUnitName;
	}

	public String getConstructionUnitName() 
	{
		return constructionUnitName;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setDesignUnitame(String designUnitame) 
	{
		this.designUnitame = designUnitame;
	}

	public String getDesignUnitame() 
	{
		return designUnitame;
	}
	public void setFinishDate(Date finishDate) 
	{
		this.finishDate = finishDate;
	}

	public Date getFinishDate() 
	{
		return finishDate;
	}
	public void setGroupId(String groupId) 
	{
		this.groupId = groupId;
	}

	public String getGroupId() 
	{
		return groupId;
	}
	public void setNumberOfLayers(Integer numberOfLayers) 
	{
		this.numberOfLayers = numberOfLayers;
	}

	public Integer getNumberOfLayers() 
	{
		return numberOfLayers;
	}
	public void setProjectAddress(String projectAddress) 
	{
		this.projectAddress = projectAddress;
	}

	public String getProjectAddress() 
	{
		return projectAddress;
	}
	public void setProjectCost(Integer projectCost) 
	{
		this.projectCost = projectCost;
	}

	public Integer getProjectCost() 
	{
		return projectCost;
	}
	public void setProjectId(String projectId) 
	{
		this.projectId = projectId;
	}

	public String getProjectId() 
	{
		return projectId;
	}
	public void setQualityReportNumber(String qualityReportNumber) 
	{
		this.qualityReportNumber = qualityReportNumber;
	}

	public String getQualityReportNumber() 
	{
		return qualityReportNumber;
	}
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}

	public String getRemarks() 
	{
		return remarks;
	}
	public void setSafetyReportNumber(String safetyReportNumber) 
	{
		this.safetyReportNumber = safetyReportNumber;
	}

	public String getSafetyReportNumber() 
	{
		return safetyReportNumber;
	}
	public void setShortTitle(String shortTitle) 
	{
		this.shortTitle = shortTitle;
	}

	public String getShortTitle() 
	{
		return shortTitle;
	}
	public void setStartDate(Date startDate) 
	{
		this.startDate = startDate;
	}

	public Date getStartDate() 
	{
		return startDate;
	}
	public void setSupervisoryAgency(String supervisoryAgency) 
	{
		this.supervisoryAgency = supervisoryAgency;
	}

	public String getSupervisoryAgency() 
	{
		return supervisoryAgency;
	}
	public void setSurveyUnit(String surveyUnit) 
	{
		this.surveyUnit = surveyUnit;
	}

	public String getSurveyUnit() 
	{
		return surveyUnit;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getTitle() 
	{
		return title;
	}
	public void setAreaId(Long areaId) 
	{
		this.areaId = areaId;
	}

	public Long getAreaId() 
	{
		return areaId;
	}
	public void setCompanyId(Long companyId) 
	{
		this.companyId = companyId;
	}

	public Long getCompanyId() 
	{
		return companyId;
	}
	public void setProjectadminId(Long projectadminId) 
	{
		this.projectadminId = projectadminId;
	}

	public Long getProjectadminId() 
	{
		return projectadminId;
	}
	public void setProjecttypeId(String projecttypeId) 
	{
		this.projecttypeId = projecttypeId;
	}

	public String getProjecttypeId() 
	{
		return projecttypeId;
	}
	public void setStatusId(String statusId) 
	{
		this.statusId = statusId;
	}

	public String getStatusId() 
	{
		return statusId;
	}
	public void setStructuretypeId(String structuretypeId) 
	{
		this.structuretypeId = structuretypeId;
	}

	public String getStructuretypeId() 
	{
		return structuretypeId;
	}
	public void setSupervisorId(Long supervisorId) 
	{
		this.supervisorId = supervisorId;
	}

	public Long getSupervisorId() 
	{
		return supervisorId;
	}
	public void setApiKey(String apiKey) 
	{
		this.apiKey = apiKey;
	}

	public String getApiKey() 
	{
		return apiKey;
	}
	public void setApiSecret(String apiSecret) 
	{
		this.apiSecret = apiSecret;
	}

	public String getApiSecret() 
	{
		return apiSecret;
	}
	public void setClientSerila(String clientSerila) 
	{
		this.clientSerila = clientSerila;
	}

	public String getClientSerila() 
	{
		return clientSerila;
	}
	public void setNumberManager(Integer numberManager) 
	{
		this.numberManager = numberManager;
	}

	public Integer getNumberManager() 
	{
		return numberManager;
	}
	public void setCashDeposit(Boolean cashDeposit) 
	{
		this.cashDeposit = cashDeposit;
	}

	public Boolean getCashDeposit() 
	{
		return cashDeposit;
	}
	public void setPayoffSum(Integer payoffSum) 
	{
		this.payoffSum = payoffSum;
	}

	public Integer getPayoffSum() 
	{
		return payoffSum;
	}
	public void setTripartiteAgreement(Boolean tripartiteAgreement) 
	{
		this.tripartiteAgreement = tripartiteAgreement;
	}

	public Boolean getTripartiteAgreement() 
	{
		return tripartiteAgreement;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("buildLicense", getBuildLicense())
            .append("builtUpArea", getBuiltUpArea())
            .append("constructionUnitName", getConstructionUnitName())
            .append("createTime", getCreateTime())
            .append("designUnitame", getDesignUnitame())
            .append("finishDate", getFinishDate())
            .append("groupId", getGroupId())
            .append("numberOfLayers", getNumberOfLayers())
            .append("projectAddress", getProjectAddress())
            .append("projectCost", getProjectCost())
            .append("projectId", getProjectId())
            .append("qualityReportNumber", getQualityReportNumber())
            .append("remarks", getRemarks())
            .append("safetyReportNumber", getSafetyReportNumber())
            .append("shortTitle", getShortTitle())
            .append("startDate", getStartDate())
            .append("supervisoryAgency", getSupervisoryAgency())
            .append("surveyUnit", getSurveyUnit())
            .append("title", getTitle())
            .append("areaId", getAreaId())
            .append("companyId", getCompanyId())
            .append("projectadminId", getProjectadminId())
            .append("projecttypeId", getProjecttypeId())
            .append("statusId", getStatusId())
            .append("structuretypeId", getStructuretypeId())
            .append("supervisorId", getSupervisorId())
            .append("apiKey", getApiKey())
            .append("apiSecret", getApiSecret())
            .append("clientSerila", getClientSerila())
            .append("numberManager", getNumberManager())
            .append("cashDeposit", getCashDeposit())
            .append("payoffSum", getPayoffSum())
            .append("tripartiteAgreement", getTripartiteAgreement())
            .toString();
    }
}
