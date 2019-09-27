package com.hujiang.project.zhgd.hujiangGroup.domain;


import com.hujiang.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 项目人员表 hujiang_hire
 *
 * @author hujiang
 * @date 2019-04-18
 */
public class HujiangHire extends BaseEntity {



    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;
    /**  */
    private String accountType;
    /**  */
    private String address;
    /**  */
    private String bankAddress;
    /**  */
    private String bankCode;
    /**  */
    private String contractId;
    /**  */
    private Date createTime;
    /**  */
    private Date dateOfBirth;
    /**  */
    private String email;
    /**  */
    private Boolean enterAndRetreatCondition;
    /**  */
    private Date entranceDate;
    /**  */
    private String faceToken;
    /**  */
    private String idAgency;
    /**  */
    private String idNumber;
    /**  */
    private String idValiddate;
    /**  */
    private Boolean isLeader;
    /**  */
    private Boolean isSynchronization;
    /**  */
    private Boolean isTrain;
    /**  */
    private String nation;
    /**  */
    private String nativePlace;
    /**  */
    private String obtainACertificate;
    /**  */
    private String phone;
    /**  */
    private String remarks;
    /**  */
    private Date retreatDate;
    /**  */
    private String signingOrganization;
    /**  */
    private String telephone;
    /**  */
    private String title;
    /**  */
    private String workNumber;
    /**  */
    private Long areaId;
    /**  */
    private String bankId;
    /**  */
    private Long bankphotorearId;
    /**  */
    private Long buildcompanyId;
    /**  */
    private Long contractfileId;
    /**  */
    private String educationId;
    /**  */
    private Long facephotoId;
    /**  */
    private Long idphotofrontId;
    /**  */
    private Long idphotorearId;
    /**  */
    private String politicaloutlookId;
    /**  */
    private Long projectId;
    /**  */
    private Long realtimephotoId;
    /**  */
    private String sexId;
    /**  */
    private Long teamId;
    /**  */
    private Long worktypeId;
    /**  */
    private String buildNumber;
    /**  */
    private Boolean contract;
    /**  */
    private Boolean entrance;
    /**  */
    private Boolean exitPdf;
    /**  */
    private Boolean iDCardPDF;
    /**  */
    private Boolean workConfirm;
    /**  */
    private String empcategoryId;
    /**  */
    private String jobtypenameId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAccountType(String accountType)
    {
        this.accountType = accountType;
    }

    public String getAccountType()
    {
        return accountType;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setBankAddress(String bankAddress)
    {
        this.bankAddress = bankAddress;
    }

    public String getBankAddress()
    {
        return bankAddress;
    }
    public void setBankCode(String bankCode)
    {
        this.bankCode = bankCode;
    }

    public String getBankCode()
    {
        return bankCode;
    }
    public void setContractId(String contractId)
    {
        this.contractId = contractId;
    }

    public String getContractId()
    {
        return contractId;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }
    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEnterAndRetreatCondition(Boolean enterAndRetreatCondition)
    {
        this.enterAndRetreatCondition = enterAndRetreatCondition;
    }

    public Boolean getEnterAndRetreatCondition()
    {
        return enterAndRetreatCondition;
    }
    public void setEntranceDate(Date entranceDate)
    {
        this.entranceDate = entranceDate;
    }

    public Date getEntranceDate()
    {
        return entranceDate;
    }
    public void setFaceToken(String faceToken)
    {
        this.faceToken = faceToken;
    }

    public String getFaceToken()
    {
        return faceToken;
    }
    public void setIdAgency(String idAgency)
    {
        this.idAgency = idAgency;
    }

    public String getIdAgency()
    {
        return idAgency;
    }
    public void setIdNumber(String idNumber)
    {
        this.idNumber = idNumber;
    }

    public String getIdNumber()
    {
        return idNumber;
    }
    public void setIdValiddate(String idValiddate)
    {
        this.idValiddate = idValiddate;
    }

    public String getIdValiddate()
    {
        return idValiddate;
    }
    public void setIsLeader(Boolean isLeader)
    {
        this.isLeader = isLeader;
    }

    public Boolean getIsLeader()
    {
        return isLeader;
    }
    public void setIsSynchronization(Boolean isSynchronization)
    {
        this.isSynchronization = isSynchronization;
    }

    public Boolean getIsSynchronization()
    {
        return isSynchronization;
    }
    public void setIsTrain(Boolean isTrain)
    {
        this.isTrain = isTrain;
    }

    public Boolean getIsTrain()
    {
        return isTrain;
    }
    public void setNation(String nation)
    {
        this.nation = nation;
    }

    public String getNation()
    {
        return nation;
    }
    public void setNativePlace(String nativePlace)
    {
        this.nativePlace = nativePlace;
    }

    public String getNativePlace()
    {
        return nativePlace;
    }
    public void setObtainACertificate(String obtainACertificate)
    {
        this.obtainACertificate = obtainACertificate;
    }

    public String getObtainACertificate()
    {
        return obtainACertificate;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public String getRemarks()
    {
        return remarks;
    }
    public void setRetreatDate(Date retreatDate)
    {
        this.retreatDate = retreatDate;
    }

    public Date getRetreatDate()
    {
        return retreatDate;
    }
    public void setSigningOrganization(String signingOrganization)
    {
        this.signingOrganization = signingOrganization;
    }

    public String getSigningOrganization()
    {
        return signingOrganization;
    }
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public String getTelephone()
    {
        return telephone;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setWorkNumber(String workNumber)
    {
        this.workNumber = workNumber;
    }

    public String getWorkNumber()
    {
        return workNumber;
    }
    public void setAreaId(Long areaId)
    {
        this.areaId = areaId;
    }

    public Long getAreaId()
    {
        return areaId;
    }
    public void setBankId(String bankId)
    {
        this.bankId = bankId;
    }

    public String getBankId()
    {
        return bankId;
    }
    public void setBankphotorearId(Long bankphotorearId)
    {
        this.bankphotorearId = bankphotorearId;
    }

    public Long getBankphotorearId()
    {
        return bankphotorearId;
    }
    public void setBuildcompanyId(Long buildcompanyId)
    {
        this.buildcompanyId = buildcompanyId;
    }

    public Long getBuildcompanyId()
    {
        return buildcompanyId;
    }
    public void setContractfileId(Long contractfileId)
    {
        this.contractfileId = contractfileId;
    }

    public Long getContractfileId()
    {
        return contractfileId;
    }
    public void setEducationId(String educationId)
    {
        this.educationId = educationId;
    }

    public String getEducationId()
    {
        return educationId;
    }
    public void setFacephotoId(Long facephotoId)
    {
        this.facephotoId = facephotoId;
    }

    public Long getFacephotoId()
    {
        return facephotoId;
    }
    public void setIdphotofrontId(Long idphotofrontId)
    {
        this.idphotofrontId = idphotofrontId;
    }

    public Long getIdphotofrontId()
    {
        return idphotofrontId;
    }
    public void setIdphotorearId(Long idphotorearId)
    {
        this.idphotorearId = idphotorearId;
    }

    public Long getIdphotorearId()
    {
        return idphotorearId;
    }
    public void setPoliticaloutlookId(String politicaloutlookId)
    {
        this.politicaloutlookId = politicaloutlookId;
    }

    public String getPoliticaloutlookId()
    {
        return politicaloutlookId;
    }
    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    public Long getProjectId()
    {
        return projectId;
    }
    public void setRealtimephotoId(Long realtimephotoId)
    {
        this.realtimephotoId = realtimephotoId;
    }

    public Long getRealtimephotoId()
    {
        return realtimephotoId;
    }
    public void setSexId(String sexId)
    {
        this.sexId = sexId;
    }

    public String getSexId()
    {
        return sexId;
    }
    public void setTeamId(Long teamId)
    {
        this.teamId = teamId;
    }

    public Long getTeamId()
    {
        return teamId;
    }
    public void setWorktypeId(Long worktypeId)
    {
        this.worktypeId = worktypeId;
    }

    public Long getWorktypeId()
    {
        return worktypeId;
    }
    public void setBuildNumber(String buildNumber)
    {
        this.buildNumber = buildNumber;
    }

    public String getBuildNumber()
    {
        return buildNumber;
    }
    public void setContract(Boolean contract)
    {
        this.contract = contract;
    }

    public Boolean getContract()
    {
        return contract;
    }
    public void setEntrance(Boolean entrance)
    {
        this.entrance = entrance;
    }

    public Boolean getEntrance()
    {
        return entrance;
    }
    public void setExitPdf(Boolean exitPdf)
    {
        this.exitPdf = exitPdf;
    }

    public Boolean getExitPdf()
    {
        return exitPdf;
    }
    public void setIDCardPDF(Boolean iDCardPDF)
    {
        this.iDCardPDF = iDCardPDF;
    }

    public Boolean getIDCardPDF()
    {
        return iDCardPDF;
    }
    public void setWorkConfirm(Boolean workConfirm)
    {
        this.workConfirm = workConfirm;
    }

    public Boolean getWorkConfirm()
    {
        return workConfirm;
    }
    public void setEmpcategoryId(String empcategoryId)
    {
        this.empcategoryId = empcategoryId;
    }

    public String getEmpcategoryId()
    {
        return empcategoryId;
    }
    public void setJobtypenameId(String jobtypenameId)
    {
        this.jobtypenameId = jobtypenameId;
    }

    public String getJobtypenameId()
    {
        return jobtypenameId;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("accountType", getAccountType())
                .append("address", getAddress())
                .append("bankAddress", getBankAddress())
                .append("bankCode", getBankCode())
                .append("contractId", getContractId())
                .append("createTime", getCreateTime())
                .append("dateOfBirth", getDateOfBirth())
                .append("email", getEmail())
                .append("enterAndRetreatCondition", getEnterAndRetreatCondition())
                .append("entranceDate", getEntranceDate())
                .append("faceToken", getFaceToken())
                .append("idAgency", getIdAgency())
                .append("idNumber", getIdNumber())
                .append("idValiddate", getIdValiddate())
                .append("isLeader", getIsLeader())
                .append("isSynchronization", getIsSynchronization())
                .append("isTrain", getIsTrain())
                .append("nation", getNation())
                .append("nativePlace", getNativePlace())
                .append("obtainACertificate", getObtainACertificate())
                .append("phone", getPhone())
                .append("remarks", getRemarks())
                .append("retreatDate", getRetreatDate())
                .append("signingOrganization", getSigningOrganization())
                .append("telephone", getTelephone())
                .append("title", getTitle())
                .append("workNumber", getWorkNumber())
                .append("areaId", getAreaId())
                .append("bankId", getBankId())
                .append("bankphotorearId", getBankphotorearId())
                .append("buildcompanyId", getBuildcompanyId())
                .append("contractfileId", getContractfileId())
                .append("educationId", getEducationId())
                .append("facephotoId", getFacephotoId())
                .append("idphotofrontId", getIdphotofrontId())
                .append("idphotorearId", getIdphotorearId())
                .append("politicaloutlookId", getPoliticaloutlookId())
                .append("projectId", getProjectId())
                .append("realtimephotoId", getRealtimephotoId())
                .append("sexId", getSexId())
                .append("teamId", getTeamId())
                .append("worktypeId", getWorktypeId())
                .append("buildNumber", getBuildNumber())
                .append("contract", getContract())
                .append("entrance", getEntrance())
                .append("exitPdf", getExitPdf())
                .append("iDCardPDF", getIDCardPDF())
                .append("workConfirm", getWorkConfirm())
                .append("empcategoryId", getEmpcategoryId())
                .append("jobtypenameId", getJobtypenameId())
                .toString();
    }


}
