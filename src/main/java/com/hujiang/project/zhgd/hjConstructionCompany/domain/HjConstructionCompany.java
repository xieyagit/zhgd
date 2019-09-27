package com.hujiang.project.zhgd.hjConstructionCompany.domain;

import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

    
/**
 * 参建单位表 hj_construction_company
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class HjConstructionCompany
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 参建单位名称 */
	private String constructionName;
	/** 简称 */
	private String shortName;
	/** 注册资金（：万元） */
	private Integer capital;
	/** 单位类型（从字典获取相应数据） */
	private Integer companyType;
	/** 法人代表 */
	private String legalPerson;
	/** 社会统一信用代码 */
	private String suid;
	/** 组织机构代码 */
	private String organizationCode;
	/** 基本账户开户银行 */
	private String bankOpen;
	/** 基本账户银行账户 */
	private String bankNum;
	/** 开户地址 */
	private String bankAddress;
	/** 单位详细地址 */
	private String address;
	/** 负责人 */
	private String contacts;
	/** 电话 */
	private String mobilePhone;
	/** 电子邮箱 */
	private String email;
	/** 备注 */
	private String remark;
	/** 状态（0.显示 1.隐藏） */
	private Integer showState;
	/** 人脸组 */
	private String faceGroup;
	/** 创建时间 */
	private String createDate;
	/** 修改时间 */
	private String updateDate;
	//单位类型
	private HjDictionaries dictionaries;
	//项目ID
	private Integer projectId;
	//企业ID
	private  Integer comId;

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public HjDictionaries getDictionaries() {
		return dictionaries;
	}

	public void setDictionaries(HjDictionaries dictionaries) {
		this.dictionaries = dictionaries;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setConstructionName(String constructionName) 
	{
		this.constructionName = constructionName;
	}

	public String getConstructionName() 
	{
		return constructionName;
	}
	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}

	public String getShortName() 
	{
		return shortName;
	}
	public void setCapital(Integer capital) 
	{
		this.capital = capital;
	}

	public Integer getCapital() 
	{
		return capital;
	}
	public void setCompanyType(Integer companyType) 
	{
		this.companyType = companyType;
	}

	public Integer getCompanyType() 
	{
		return companyType;
	}
	public void setLegalPerson(String legalPerson) 
	{
		this.legalPerson = legalPerson;
	}

	public String getLegalPerson() 
	{
		return legalPerson;
	}
	public void setSuid(String suid) 
	{
		this.suid = suid;
	}

	public String getSuid() 
	{
		return suid;
	}
	public void setOrganizationCode(String organizationCode) 
	{
		this.organizationCode = organizationCode;
	}

	public String getOrganizationCode() 
	{
		return organizationCode;
	}
	public void setBankOpen(String bankOpen) 
	{
		this.bankOpen = bankOpen;
	}

	public String getBankOpen() 
	{
		return bankOpen;
	}
	public void setBankNum(String bankNum) 
	{
		this.bankNum = bankNum;
	}

	public String getBankNum() 
	{
		return bankNum;
	}
	public void setBankAddress(String bankAddress) 
	{
		this.bankAddress = bankAddress;
	}

	public String getBankAddress() 
	{
		return bankAddress;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setContacts(String contacts) 
	{
		this.contacts = contacts;
	}

	public String getContacts() 
	{
		return contacts;
	}
	public void setMobilePhone(String mobilePhone) 
	{
		this.mobilePhone = mobilePhone;
	}

	public String getMobilePhone() 
	{
		return mobilePhone;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getEmail() 
	{
		return email;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setShowState(Integer showState) 
	{
		this.showState = showState;
	}

	public Integer getShowState() 
	{
		return showState;
	}
	public void setFaceGroup(String faceGroup) 
	{
		this.faceGroup = faceGroup;
	}

	public String getFaceGroup() 
	{
		return faceGroup;
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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "HjConstructionCompany{" +
				"id=" + id +
				", constructionName='" + constructionName + '\'' +
				", shortName='" + shortName + '\'' +
				", capital=" + capital +
				", companyType=" + companyType +
				", legalPerson='" + legalPerson + '\'' +
				", suid='" + suid + '\'' +
				", organizationCode='" + organizationCode + '\'' +
				", bankOpen='" + bankOpen + '\'' +
				", bankNum='" + bankNum + '\'' +
				", bankAddress='" + bankAddress + '\'' +
				", address='" + address + '\'' +
				", contacts='" + contacts + '\'' +
				", mobilePhone='" + mobilePhone + '\'' +
				", email='" + email + '\'' +
				", remark='" + remark + '\'' +
				", showState=" + showState +
				", faceGroup='" + faceGroup + '\'' +
				", createDate='" + createDate + '\'' +
				", updateDate='" + updateDate + '\'' +
				", dictionaries=" + dictionaries +
				", projectId=" + projectId +'\''+
				", comId=" + comId +
				'}';
	}
}
