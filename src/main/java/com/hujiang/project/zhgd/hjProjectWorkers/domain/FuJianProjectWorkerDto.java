package com.hujiang.project.zhgd.hjProjectWorkers.domain;

import java.io.Serializable;

/**
 *@ClassName FuJianProjectWorker
 *@Description 对接福建两制dto
 *@Author xieya
 *@Date 2020/4/20  9:40
 */
public class FuJianProjectWorkerDto implements Serializable {
    /**工人姓名*/
    private String workerName;
    /**是否班组长*/
    private int isTeamLeader;
    /**证件类型*/
    private String idCardType;
    /**证件号码*/
    private String idCardNumber;
    /**当前工种*/
    private String workType;
    /**工人类型*/
    private int workRole;
//    //发卡时间。 格式 yyyy-MM-dd
//    private String issueCardDate;
    /**民族。 身份证上民 族信息，如：汉，*/
    private String nation;
    /**住址*/
    private String address;
    /**头像。不超过 50KB 的 Base64 字符串 或图片地式(jpg) 址，支持格文件 base64 格式(文件后 缀+,+base64 字符串)*/
    private String headImage;
    /**性别(0:男，1:女)*/
    private String gender;
    /**政治面貌*/
    private String politicsType;
    /**手机号码*/
    private String cellPhone;
    /**文化程度*/
    private String cultureLevelType;
    /**发证机关*/
    private String grantOrg;

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public int getIsTeamLeader() {
        return isTeamLeader;
    }

    public void setIsTeamLeader(int isTeamLeader) {
        this.isTeamLeader = isTeamLeader;
    }

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public int getWorkRole() {
        return workRole;
    }

    public void setWorkRole(int workRole) {
        this.workRole = workRole;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPoliticsType() {
        return politicsType;
    }

    public void setPoliticsType(String politicsType) {
        this.politicsType = politicsType;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCultureLevelType() {
        return cultureLevelType;
    }

    public void setCultureLevelType(String cultureLevelType) {
        this.cultureLevelType = cultureLevelType;
    }

    public String getGrantOrg() {
        return grantOrg;
    }

    public void setGrantOrg(String grantOrg) {
        this.grantOrg = grantOrg;
    }
}