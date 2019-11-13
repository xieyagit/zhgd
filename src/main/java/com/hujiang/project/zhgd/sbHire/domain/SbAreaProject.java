package com.hujiang.project.zhgd.sbHire.domain;

public class SbAreaProject {
    private String imei;
    private Integer userId;
    private Integer areaId;
    private String areaName;
    private Integer projectId;
    private Integer constructionId;
    private String userName;
    private String userPhone;
    private Integer adminId;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Integer constructionId) {
        this.constructionId = constructionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "SbAreaProject{" +
                "imei='" + imei + '\'' +
                ", userId=" + userId +
                ", areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", projectId=" + projectId +
                ", constructionId=" + constructionId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", adminId=" + adminId +
                '}';
    }
}
