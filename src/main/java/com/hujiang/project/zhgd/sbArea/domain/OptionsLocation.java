package com.hujiang.project.zhgd.sbArea.domain;

public class OptionsLocation {
    private Integer areaId;
    private String areaName;
    private String areaAddress;
    private Integer constructionId;
    private String constructionName;
    private Integer userId;
    private String userName;
    private Double areaXloc;
    private Double areaYloc;
    private Double radius;
    private Integer projectId;

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

    public String getAreaAddress() {
        return areaAddress;
    }

    public void setAreaAddress(String areaAddress) {
        this.areaAddress = areaAddress;
    }

    public Integer getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Integer constructionId) {
        this.constructionId = constructionId;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getAreaXloc() {
        return areaXloc;
    }

    public void setAreaXloc(Double areaXloc) {
        this.areaXloc = areaXloc;
    }

    public Double getAreaYloc() {
        return areaYloc;
    }

    public void setAreaYloc(Double areaYloc) {
        this.areaYloc = areaYloc;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
