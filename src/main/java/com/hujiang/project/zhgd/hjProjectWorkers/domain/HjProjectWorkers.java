package com.hujiang.project.zhgd.hjProjectWorkers.domain;

import java.util.Date;
import java.util.List;

/**
 * 项目工人表 hj_project_workers
 * 
 * @author hujiang
 * @date 2019-05-19
 */
public class HjProjectWorkers
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
	/** 进场日期 */
	private String startTime;
	/** 退场日期 */
	private String endTime;
	/** 所属班组id */
	private Integer workTypenameId;
	/** 是否班组长(0否，1是) */
	private Integer isTeam;
	/** 是否重要人员(0否，1是) */
	private String cwrIskeypsn;
	/** 人员类别 */
	private String jobTypename;
	/** 工种名称 */
	private String jobName;
	/** 人员类型 */
	private String empCategory;
	/** 是否办理合同(0否，1是) */
	private Integer ifContract;
	/** 所属工做部门 */
	private String empDept;
	/** 现工作部门 */
	private String obDept;
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
	/** 简易劳动合同上传状态（0、否。1、是） */
	private Integer contract;
	/** 工人进场承诺书上传状态（0、否。1、是） */
	private Integer entrance;
	/** 工人退场承诺书上传状态（0、否。1、是） */
	private Integer exeunt;
	/** 两制“工作”确认书上传状态（0、否。1、是） */
	private Integer workConfirm;
	/** 身份证正反面文件上传状态（0、否。1、是） */
	private Integer iDCardPDF;
	/** 安全教育培训是否合格（0、否。1、是） */
	private Integer isTrain;
	/** 信息是否公开（0、否。1、是） */
	private Integer information;
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
	/** hj_project 外键 id */
	private Integer projectId;
	/** 进退场状态（0、进场。1、退场,2未同步） */
	private Integer enterAndRetreatCondition;
	/** 人脸库token */
	private String faceToken;
	/** 创建时间 */
	private Date createDate;
	/** 修改时间 */
	private String updateDate;
	/** 参见单位 */
	private Integer constructionId;
	/** 参见单位名称*/
	private String constructionName;
	/** */
	private String workTypenameName;

	private Integer count;
	private String quarantine;

	private List<String> adsList;

	public String getQuarantine() {
		return quarantine;
	}

	public void setQuarantine(String quarantine) {
		this.quarantine = quarantine;
	}

	/**
	 * 东莞劳务工花名册id
	 */
	private Integer rosterWokerId;
	/** 是否上传 */
	private  String isUpload;

	public String getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

	public Integer getRosterWokerId() {
		return rosterWokerId;
	}

	public void setRosterWokerId(Integer rosterWokerId) {
		this.rosterWokerId = rosterWokerId;
	}

	public String getWorkTypenameName() {
		return workTypenameName;
	}

	public void setWorkTypenameName(String workTypenameName) {
		this.workTypenameName = workTypenameName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getEmpPhon() {
		return empPhon;
	}

	public void setEmpPhon(String empPhon) {
		this.empPhon = empPhon;
	}

	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public String getEmpNation() {
		return empNation;
	}

	public void setEmpNation(String empNation) {
		this.empNation = empNation;
	}

	public String getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(String idAddress) {
		this.idAddress = idAddress;
	}

	public String getIdAgency() {
		return idAgency;
	}

	public void setIdAgency(String idAgency) {
		this.idAgency = idAgency;
	}

	public String getIdValiddate() {
		return idValiddate;
	}

	public void setIdValiddate(String idValiddate) {
		this.idValiddate = idValiddate;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getWorkTypenameId() {
		return workTypenameId;
	}

	public void setWorkTypenameId(Integer workTypenameId) {
		this.workTypenameId = workTypenameId;
	}

	public Integer getIsTeam() {
		return isTeam;
	}

	public void setIsTeam(Integer isTeam) {
		this.isTeam = isTeam;
	}

	public String getCwrIskeypsn() {
		return cwrIskeypsn;
	}

	public void setCwrIskeypsn(String cwrIskeypsn) {
		this.cwrIskeypsn = cwrIskeypsn;
	}

	public String getJobTypename() {
		return jobTypename;
	}

	public void setJobTypename(String jobTypename) {
		this.jobTypename = jobTypename;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getEmpCategory() {
		return empCategory;
	}

	public void setEmpCategory(String empCategory) {
		this.empCategory = empCategory;
	}

	public Integer getIfContract() {
		return ifContract;
	}

	public void setIfContract(Integer ifContract) {
		this.ifContract = ifContract;
	}

	public String getEmpDept() {
		return empDept;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public String getObDept() {
		return obDept;
	}

	public void setObDept(String obDept) {
		this.obDept = obDept;
	}

	public String getEmpBankname() {
		return empBankname;
	}

	public void setEmpBankname(String empBankname) {
		this.empBankname = empBankname;
	}

	public String getEmpCardnum() {
		return empCardnum;
	}

	public void setEmpCardnum(String empCardnum) {
		this.empCardnum = empCardnum;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountAddress() {
		return accountAddress;
	}

	public void setAccountAddress(String accountAddress) {
		this.accountAddress = accountAddress;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getContract() {
		return contract;
	}

	public void setContract(Integer contract) {
		this.contract = contract;
	}

	public Integer getEntrance() {
		return entrance;
	}

	public void setEntrance(Integer entrance) {
		this.entrance = entrance;
	}

	public Integer getExeunt() {
		return exeunt;
	}

	public void setExeunt(Integer exeunt) {
		this.exeunt = exeunt;
	}

	public Integer getWorkConfirm() {
		return workConfirm;
	}

	public void setWorkConfirm(Integer workConfirm) {
		this.workConfirm = workConfirm;
	}

	public Integer getiDCardPDF() {
		return iDCardPDF;
	}

	public void setiDCardPDF(Integer iDCardPDF) {
		this.iDCardPDF = iDCardPDF;
	}

	public Integer getIsTrain() {
		return isTrain;
	}

	public void setIsTrain(Integer isTrain) {
		this.isTrain = isTrain;
	}

	public String getFaceUrl() {
		return faceUrl;
	}

	public void setFaceUrl(String faceUrl) {
		this.faceUrl = faceUrl;
	}

	public String getEmpNaticeplace() {
		return empNaticeplace;
	}

	public void setEmpNaticeplace(String empNaticeplace) {
		this.empNaticeplace = empNaticeplace;
	}

	public String getIdphotoScan() {
		return idphotoScan;
	}

	public void setIdphotoScan(String idphotoScan) {
		this.idphotoScan = idphotoScan;
	}

	public String getIdphotoScan2() {
		return idphotoScan2;
	}

	public void setIdphotoScan2(String idphotoScan2) {
		this.idphotoScan2 = idphotoScan2;
	}

	public String getBankCardUrl() {
		return bankCardUrl;
	}

	public void setBankCardUrl(String bankCardUrl) {
		this.bankCardUrl = bankCardUrl;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getEnterAndRetreatCondition() {
		return enterAndRetreatCondition;
	}

	public void setEnterAndRetreatCondition(Integer enterAndRetreatCondition) {
		this.enterAndRetreatCondition = enterAndRetreatCondition;
	}

	public String getFaceToken() {
		return faceToken;
	}

	public void setFaceToken(String faceToken) {
		this.faceToken = faceToken;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(Integer constructionId) {
		this.constructionId = constructionId;
	}

	public Integer getInformation() {
		return information;
	}

	public void setInformation(Integer information) {
		this.information = information;
	}

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public List<String> getAdsList() {
		return adsList;
	}

	public void setAdsList(List<String> adsList) {
		this.adsList = adsList;
	}

	@Override
	public String toString() {
		return "HjProjectWorkers{" +
				"id=" + id +
				", empName='" + empName + '\'' +
				", idCode='" + idCode + '\'' +
				", empPhon='" + empPhon + '\'' +
				", empSex='" + empSex + '\'' +
				", empNation='" + empNation + '\'' +
				", idAddress='" + idAddress + '\'' +
				", idAgency='" + idAgency + '\'' +
				", idValiddate='" + idValiddate + '\'' +
				", dateOfBirth='" + dateOfBirth + '\'' +
				", nativePlace='" + nativePlace + '\'' +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", workTypenameId=" + workTypenameId +
				", isTeam=" + isTeam +
				", cwrIskeypsn='" + cwrIskeypsn + '\'' +
				", jobTypename='" + jobTypename + '\'' +
				", jobName='" + jobName + '\'' +
				", empCategory='" + empCategory + '\'' +
				", ifContract=" + ifContract +
				", empDept='" + empDept + '\'' +
				", obDept='" + obDept + '\'' +
				", empBankname='" + empBankname + '\'' +
				", empCardnum='" + empCardnum + '\'' +
				", accountType='" + accountType + '\'' +
				", accountAddress='" + accountAddress + '\'' +
				", credential='" + credential + '\'' +
				", remark='" + remark + '\'' +
				", contract=" + contract +
				", entrance=" + entrance +
				", exeunt=" + exeunt +
				", workConfirm=" + workConfirm +
				", iDCardPDF=" + iDCardPDF +
				", isTrain=" + isTrain +
				", information=" + information +
				", faceUrl='" + faceUrl + '\'' +
				", empNaticeplace='" + empNaticeplace + '\'' +
				", idphotoScan='" + idphotoScan + '\'' +
				", idphotoScan2='" + idphotoScan2 + '\'' +
				", bankCardUrl='" + bankCardUrl + '\'' +
				", projectId=" + projectId +
				", enterAndRetreatCondition=" + enterAndRetreatCondition +
				", faceToken='" + faceToken + '\'' +
				", createDate=" + createDate +
				", updateDate='" + updateDate + '\'' +
				", constructionId=" + constructionId +
				", constructionName='" + constructionName + '\'' +
				", workTypenameName='" + workTypenameName + '\'' +
				", count=" + count +
				'}';
	}
}
