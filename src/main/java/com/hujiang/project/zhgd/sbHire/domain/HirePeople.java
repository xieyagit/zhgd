package com.hujiang.project.zhgd.sbHire.domain;

import java.util.Date;

public class HirePeople {
    private Integer pId;      //项目ID
    private String projectName;   //项目名称
    private Integer areaId;   //工区ID
    private String areaName;  //工区名称
    private Integer peopleId;     //用户ID
    private String peopleName;    //用户名称
    private String imei;      //设备编号
    private String watchDate; //定位时间
    private String constructionName;//所属公司
    private Double xloc;    //经度
    private Double yloc;    //纬度
    private Double radius;  //半径
    private String address;
    private Integer bat;
    private Integer way;
    private Integer array;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(String watchDate) {
        this.watchDate = watchDate;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public Double getXloc() {
        return xloc;
    }

    public void setXloc(Double xloc) {
        this.xloc = xloc;
    }

    public Double getYloc() {
        return yloc;
    }

    public void setYloc(Double yloc) {
        this.yloc = yloc;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBat() {
        return bat;
    }

    public void setBat(Integer bat) {
        this.bat = bat;
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public Integer getArray() {
        return array;
    }

    public void setArray(Integer array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "HirePeople{" +
                "pId=" + pId +
                ", projectName='" + projectName + '\'' +
                ", areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", peopleId=" + peopleId +
                ", peopleName='" + peopleName + '\'' +
                ", imei='" + imei + '\'' +
                ", watchDate='" + watchDate + '\'' +
                ", constructionName='" + constructionName + '\'' +
                ", xloc=" + xloc +
                ", yloc=" + yloc +
                ", radius=" + radius +
                ", address='" + address + '\'' +
                ", bat=" + bat +
                '}';
    }
}
