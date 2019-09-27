package com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain;

public class Safety
{
    private int projectId;
    private int constructionId;     //责任分包单位ID
    private String constructionName;//责任分包单位名称
    private int areaId;     //区域ID
    private String areaName;    //区域名称
    private int userId;        //用户ID
    private String userName;    //用户名称
    private int hiddenId;       //隐患ID
    private String hiddenName;  //隐患名称
    private int gradeId;        //问题级别ID
    private String gradeName;   //问题级别名称

    public int getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(int constructionId) {
        this.constructionId = constructionId;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(int hiddenId) {
        this.hiddenId = hiddenId;
    }

    public String getHiddenName() {
        return hiddenName;
    }

    public void setHiddenName(String hiddenName) {
        this.hiddenName = hiddenName;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "Safety{" +
                "projectId=" + projectId +
                ", constructionId=" + constructionId +
                ", constructionName='" + constructionName + '\'' +
                ", areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", hiddenId=" + hiddenId +
                ", hiddenName='" + hiddenName + '\'' +
                ", gradeId=" + gradeId +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }
}
