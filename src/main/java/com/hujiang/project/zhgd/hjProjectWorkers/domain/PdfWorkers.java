package com.hujiang.project.zhgd.hjProjectWorkers.domain;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-05 19:19
 **/
public class PdfWorkers {
    /** 姓名  */
    private String empName;
    /** 身份证号码 */
    private String idCode;
    /** 性别 */
    private String empSex;
    /** 电话 */
    private String empPhon;
    /** 进场时间 */
    private String startTime;
    /** 总包单位名 */
    private String companyName;
    /** 工种 */
    private String title;
    /** 项目名 */
    private String projectName;
    /** 参建单位名 */
    private String constructionName;
    /** 班组名 */
    private String teamName;
    /** 是否班组长 0否 1是 */
    private Integer isTeam;
    /** 参建单位法人代表 */
    private String legalPerson;
    /** 参建单位负责人 */
    private String contacts;
    /** 参建单位地址 */
    private String address;
    /** 出生日期 */
    private String dateOfBirth;
    /** 项目地址 */
    private String projectAddress;
    /** 项目开工日期 */
    private String startingTime;
    /** 身份证正面 */
    private String idphotoScan;
    /** 身份证反面 */
    private String idphotoScan2;

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

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }

    public String getEmpPhon() {
        return empPhon;
    }

    public void setEmpPhon(String empPhon) {
        this.empPhon = empPhon;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getIsTeam() {
        return isTeam;
    }

    public void setIsTeam(Integer isTeam) {
        this.isTeam = isTeam;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PdfWorkers{" +
                "empName='" + empName + '\'' +
                ", idCode='" + idCode + '\'' +
                ", empSex='" + empSex + '\'' +
                ", empPhon='" + empPhon + '\'' +
                ", startTime='" + startTime + '\'' +
                ", companyName='" + companyName + '\'' +
                ", title='" + title + '\'' +
                ", projectName='" + projectName + '\'' +
                ", constructionName='" + constructionName + '\'' +
                ", teamName='" + teamName + '\'' +
                ", isTeam=" + isTeam +
                ", legalPerson='" + legalPerson + '\'' +
                ", contacts='" + contacts + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", projectAddress='" + projectAddress + '\'' +
                ", startingTime='" + startingTime + '\'' +
                ", idphotoScan='" + idphotoScan + '\'' +
                ", idphotoScan2='" + idphotoScan2 + '\'' +
                '}';
    }
}
