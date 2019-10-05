package com.hujiang.project.zhgd.sbElevatorAddparams.domain;

public class OptionsElevator {
    private Integer id;
    //载重
    private Double capacity;
    //设备编号32
    private String deviceNo;
    //设备编号
    private String hxzId;
    //设备名称
    private String elevatorName;
    /** 广东省统一安装告知编号*/
    private String serialNum;
    /** 项目监督编号*/
    private String jdbh;
    /** 项目编号*/
    private String xmid;
    /** 工程ID*/
    private String subId;
    /** 上传平台*/
    private String scznl;
    /** 设备厂商id*/
    private int manufacturerId;
    /** 最大高度（m)*/
    private Double height;
    /** 设备安装单位*/
    private String installCompany;


    public String getInstallCompany() {
        return installCompany;
    }

    public void setInstallCompany(String installCompany) {
        this.installCompany = installCompany;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getJdbh() {
        return jdbh;
    }

    public void setJdbh(String jdbh) {
        this.jdbh = jdbh;
    }

    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getScznl() {
        return scznl;
    }

    public void setScznl(String scznl) {
        this.scznl = scznl;
    }

    public int getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }


    private Integer projectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getElevatorName() {
        return elevatorName;
    }

    public void setElevatorName(String elevatorName) {
        this.elevatorName = elevatorName;
    }

    public String getHxzId() {
        return hxzId;
    }

    public void setHxzId(String hxzId) {
        this.hxzId = hxzId;
    }
}
