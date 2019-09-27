package com.hujiang.project.zhgd.hjCompanyLibrary.domain;

import com.hujiang.project.zhgd.hjDictionaries.domain.HjDictionaries;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 公司库表 hj_company_library
 * 
 * @author hujiang
 * @date 2019-04-29
 */
public class HjCompanyLibrary
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 企业名称 */
	private String companyName;
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
	/** 状态（0.显示 1.隐藏） */
	private Integer showState;
	/** 备注 */
	private String remark;
	/** 创建时间 */
	private String createDate;
	/** 修改时间 */
	private String updateDate;
	//单位类型
	private HjDictionaries dictionaries;

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
	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}

	public String getCompanyName() 
	{
		return companyName;
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
	public void setShowState(Integer showState) 
	{
		this.showState = showState;
	}

	public Integer getShowState() 
	{
		return showState;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
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
            .append("companyName", getCompanyName())
            .append("shortName", getShortName())
            .append("capital", getCapital())
            .append("companyType", getCompanyType())
            .append("legalPerson", getLegalPerson())
            .append("suid", getSuid())
            .append("organizationCode", getOrganizationCode())
            .append("bankOpen", getBankOpen())
            .append("bankNum", getBankNum())
            .append("bankAddress", getBankAddress())
            .append("address", getAddress())
            .append("contacts", getContacts())
            .append("mobilePhone", getMobilePhone())
            .append("email", getEmail())
            .append("showState", getShowState())
            .append("remark", getRemark())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
