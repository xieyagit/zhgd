package com.hujiang.project.zhgd.lyPersonnel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 楼宇人员表 ly_personnel
 * 
 * @author hujiang
 * @date 2020-03-06
 */
public class LyPersonnel
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 名字 */
	private String empName;
	/** 身份证好 */
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
	/** 有效期 */
	private String idValiddate;
	/** 出生日期 */
	private String dateOfBirth;
	/** 人脸照片 */
	private String faceUrl;
	/** 身份证正面 */
	private String idphotoScan;
	/** 身份证反面 */
	private String idphotoScan2;
	/** 项目id */
	private Integer pid;
	/** 所属公司id */
	private Integer companyId;
	/** 所属部门或拜访对象 */
	private String subordinate;
	/** 是否在场，0在场，1离场 */
	private String ispresent;
	/** 类别，1，在职人员，2，访客 */
	private String type;
	/** 备注 */
	private String bz;
private String companyName;
private  String floor;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

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
	public void setFaceUrl(String faceUrl) 
	{
		this.faceUrl = faceUrl;
	}

	public String getFaceUrl() 
	{
		return faceUrl;
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
	public void setPid(Integer pid) 
	{
		this.pid = pid;
	}

	public Integer getPid() 
	{
		return pid;
	}
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
	}
	public void setSubordinate(String subordinate) 
	{
		this.subordinate = subordinate;
	}

	public String getSubordinate() 
	{
		return subordinate;
	}
	public void setIspresent(String ispresent) 
	{
		this.ispresent = ispresent;
	}

	public String getIspresent() 
	{
		return ispresent;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setBz(String bz) 
	{
		this.bz = bz;
	}

	public String getBz() 
	{
		return bz;
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
            .append("faceUrl", getFaceUrl())
            .append("idphotoScan", getIdphotoScan())
            .append("idphotoScan2", getIdphotoScan2())
            .append("pid", getPid())
            .append("companyId", getCompanyId())
            .append("subordinate", getSubordinate())
            .append("ispresent", getIspresent())
            .append("type", getType())
            .append("bz", getBz())
            .toString();
    }
}
