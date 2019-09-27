package com.hujiang.project.zhgd.hjWorkers.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

    
/**
 * 工人库表 hj_workers
 * 
 * @author hujiang
 * @date 2019-05-16
 */
public class HjWorkers
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 姓名 */
	private String empName;
	/** 身份证号码 */
	private String idCode;
	/** 手机号码 */
	private String empPhon;
	/** 性别 */
	private String empSex;
	/** 民族 */
	private String empNation;
	/** 身份证地址 */
	private String idAddress;
	/** 签发机关 */
	private String idAgency;
	/** 有效期限 */
	private String idValiddate;
	/** 出生日期 */
	private String dateOfBirth;
	/** 籍贯 */
	private String nativePlace;
	/** 是否班组长(0否，1是) */
	private Integer isTeam;
	/** 工种名称 */
	private String jobName;
	/** 开户行 */
	private String empBankname;
	/** 银行账号 */
	private String empCardnum;
	/** 账户类型 */
	private String accountType;
	/** 开户地址 */
	private String accountAddress;
	/** 获得证书 */
	private String credential;
	/** 备注 */
	private String remark;
	/** 人脸照片 */
	private String faceUrl;
	/** 身份证人脸照片 */
	private String empNaticeplace;
	/** 身份证正面照片 */
	private String idphotoScan;
	/** 身份证反面照片 */
	private String idphotoScan2;
	/** 银行卡照片 */
	private String bankCardUrl;
	/** 创建时间 */
	private String createDate;
	/** 修改时间 */
	private String updateDate;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setEmpName(String empName) 
	{
		this.empName = empName;
	}

	public String getEmpName() 
	{
		return empName;
	}
	public void setIdCode(String idCode) 
	{
		this.idCode = idCode;
	}

	public String getIdCode() 
	{
		return idCode;
	}
	public void setEmpPhon(String empPhon)
	{
		this.empPhon = empPhon;
	}

	public String getEmpPhon()
	{
		return empPhon;
	}
	public void setEmpSex(String empSex) 
	{
		this.empSex = empSex;
	}

	public String getEmpSex() 
	{
		return empSex;
	}
	public void setEmpNation(String empNation) 
	{
		this.empNation = empNation;
	}

	public String getEmpNation() 
	{
		return empNation;
	}
	public void setIdAddress(String idAddress) 
	{
		this.idAddress = idAddress;
	}

	public String getIdAddress() 
	{
		return idAddress;
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
	public void setDateOfBirth(String dateOfBirth) 
	{
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirth() 
	{
		return dateOfBirth;
	}
	public void setNativePlace(String nativePlace) 
	{
		this.nativePlace = nativePlace;
	}

	public String getNativePlace() 
	{
		return nativePlace;
	}
	public void setIsTeam(Integer isTeam) 
	{
		this.isTeam = isTeam;
	}

	public Integer getIsTeam() 
	{
		return isTeam;
	}
	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}

	public String getJobName()
	{
		return jobName;
	}
	public void setEmpBankname(String empBankname) 
	{
		this.empBankname = empBankname;
	}

	public String getEmpBankname() 
	{
		return empBankname;
	}
	public void setEmpCardnum(String empCardnum) 
	{
		this.empCardnum = empCardnum;
	}

	public String getEmpCardnum() 
	{
		return empCardnum;
	}
	public void setAccountType(String accountType) 
	{
		this.accountType = accountType;
	}

	public String getAccountType() 
	{
		return accountType;
	}
	public void setAccountAddress(String accountAddress) 
	{
		this.accountAddress = accountAddress;
	}

	public String getAccountAddress() 
	{
		return accountAddress;
	}
	public void setCredential(String credential) 
	{
		this.credential = credential;
	}

	public String getCredential() 
	{
		return credential;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setFaceUrl(String faceUrl) 
	{
		this.faceUrl = faceUrl;
	}

	public String getFaceUrl() 
	{
		return faceUrl;
	}
	public void setEmpNaticeplace(String empNaticeplace) 
	{
		this.empNaticeplace = empNaticeplace;
	}

	public String getEmpNaticeplace() 
	{
		return empNaticeplace;
	}
	public void setIdphotoScan(String idphotoScan) 
	{
		this.idphotoScan = idphotoScan;
	}

	public String getIdphotoScan() 
	{
		return idphotoScan;
	}
	public void setIdphotoScan2(String idphotoScan2) 
	{
		this.idphotoScan2 = idphotoScan2;
	}

	public String getIdphotoScan2() 
	{
		return idphotoScan2;
	}
	public void setBankCardUrl(String bankCardUrl) 
	{
		this.bankCardUrl = bankCardUrl;
	}

	public String getBankCardUrl() 
	{
		return bankCardUrl;
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
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("empName", getEmpName())
            .append("idCode", getIdCode())
            .append("empPhon", getEmpPhon())
            .append("empSex", getEmpSex())
            .append("empNation", getEmpNation())
            .append("idAddress", getIdAddress())
            .append("idAgency", getIdAgency())
            .append("idValiddate", getIdValiddate())
            .append("dateOfBirth", getDateOfBirth())
            .append("nativePlace", getNativePlace())
            .append("isTeam", getIsTeam())
            .append("jobName", getJobName())
            .append("empBankname", getEmpBankname())
            .append("empCardnum", getEmpCardnum())
            .append("accountType", getAccountType())
            .append("accountAddress", getAccountAddress())
            .append("credential", getCredential())
            .append("remark", getRemark())
            .append("faceUrl", getFaceUrl())
            .append("empNaticeplace", getEmpNaticeplace())
            .append("idphotoScan", getIdphotoScan())
            .append("idphotoScan2", getIdphotoScan2())
            .append("bankCardUrl", getBankCardUrl())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
