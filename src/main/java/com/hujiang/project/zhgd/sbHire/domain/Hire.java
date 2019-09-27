package com.hujiang.project.zhgd.sbHire.domain;

import java.util.Date;

public class Hire {
    private int id;
    private String userName;    //姓名
    private String userPhone;   //电话
    private String constructionName; //劳务公司
    private String imei; //设备编号
    private int bat;    //设备电量
    private Date watchDate; //定位时间
    private Double xloc;    //经度
    private Double yloc;    //纬度
    private String address; //定位地址
    private String areaName;
    private Double areaXloc;
    private Double areaYloc;
    private Double areaRadius;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getBat() {
        return bat;
    }

    public void setBat(int bat) {
        this.bat = bat;
    }

    public Date getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(Date watchDate) {
        this.watchDate = watchDate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Double getAreaRadius() {
        return areaRadius;
    }

    public void setAreaRadius(Double areaRadius) {
        this.areaRadius = areaRadius;
    }

    @Override
    public String toString() {
        return "Hire{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", constructionName='" + constructionName + '\'' +
                ", imei='" + imei + '\'' +
                ", bat=" + bat +
                ", watchDate=" + watchDate +
                ", xloc=" + xloc +
                ", yloc=" + yloc +
                ", address='" + address + '\'' +
                ", areaName='" + areaName + '\'' +
                ", areaXloc=" + areaXloc +
                ", areaYloc=" + areaYloc +
                ", areaRadius=" + areaRadius +
                '}';
    }
}
