package com.hujiang.project.zhgd.hujiangGroup.domain;

import com.hujiang.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 工人表 hujiang_worker
 * 
 * @author hujiang
 * @date 2019-04-18
 */
public class HuJiangWorker extends BaseEntity
{
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
	private Date createTime;
	/**  */
	private Date dateOfBirth;
	/**  */
	private String email;
	/**  */
	private Date idCardValiditydate;
	/**  */
	private String idNumber;
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
	private String signingOrganization;
	/**  */
	private String telephone;
	/**  */
	private String title;
	/**  */
	private String bankId;
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
	private Long realtimephotoId;
	/**  */
	private String sexId;
	/**  */
	private Long worktypeId;
	/**  */
	private String idAgency;
	/**  */
	private String idValiddate;

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
	public void setIdCardValiditydate(Date idCardValiditydate) 
	{
		this.idCardValiditydate = idCardValiditydate;
	}

	public Date getIdCardValiditydate() 
	{
		return idCardValiditydate;
	}
	public void setIdNumber(String idNumber) 
	{
		this.idNumber = idNumber;
	}

	public String getIdNumber() 
	{
		return idNumber;
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
	public void setBankId(String bankId) 
	{
		this.bankId = bankId;
	}

	public String getBankId() 
	{
		return bankId;
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
	public void setWorktypeId(Long worktypeId) 
	{
		this.worktypeId = worktypeId;
	}

	public Long getWorktypeId() 
	{
		return worktypeId;
	}
	public void setIdAgency(String idAgency) 
	{
		this.idAgency = idAgency;
	}

	public String getIdAgency() 
	{
		return idAgency;
	}
	public void setIdValiddate(String idValiddate) 
	{
		this.idValiddate = idValiddate;
	}

	public String getIdValiddate() 
	{
		return idValiddate;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("accountType", getAccountType())
            .append("address", getAddress())
            .append("bankAddress", getBankAddress())
            .append("bankCode", getBankCode())
            .append("createTime", getCreateTime())
            .append("dateOfBirth", getDateOfBirth())
            .append("email", getEmail())
            .append("idCardValiditydate", getIdCardValiditydate())
            .append("idNumber", getIdNumber())
            .append("nation", getNation())
            .append("nativePlace", getNativePlace())
            .append("obtainACertificate", getObtainACertificate())
            .append("phone", getPhone())
            .append("remarks", getRemarks())
            .append("signingOrganization", getSigningOrganization())
            .append("telephone", getTelephone())
            .append("title", getTitle())
            .append("bankId", getBankId())
            .append("educationId", getEducationId())
            .append("facephotoId", getFacephotoId())
            .append("idphotofrontId", getIdphotofrontId())
            .append("idphotorearId", getIdphotorearId())
            .append("politicaloutlookId", getPoliticaloutlookId())
            .append("realtimephotoId", getRealtimephotoId())
            .append("sexId", getSexId())
            .append("worktypeId", getWorktypeId())
            .append("idAgency", getIdAgency())
            .append("idValiddate", getIdValiddate())
            .toString();
    }
}
