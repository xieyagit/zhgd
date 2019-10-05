package com.hujiang.project.zhgd.sbCraneAddparams.domain;

public class OptionsCrane {
    private Integer id;
    private Double frontArmLength;
    private Double backArmLength;
    private Integer projectId;
    private String craneName;
    //设备编号32
    private String deviceNo;
    //设备编号
    private String hxzId;

    String serialNum;//广东省统一安装告 知编号（产权备案 编号）
    String tcMaxScope;//最大幅度（M）
    String tcMaxHeight;//最大高度（M)
    String tcLoadCapacity;//最大载重（kg）
    String tcLoadMoment;//额定起重力矩（N·m）
    String jdbh;
    String xmid;
    String subId;
    String scznl;
    String manufacturerId;
    String installCompany;//设备安装单位

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getTcMaxScope() {
        return tcMaxScope;
    }

    public void setTcMaxScope(String tcMaxScope) {
        this.tcMaxScope = tcMaxScope;
    }

    public String getTcMaxHeight() {
        return tcMaxHeight;
    }

    public void setTcMaxHeight(String tcMaxHeight) {
        this.tcMaxHeight = tcMaxHeight;
    }

    public String getTcLoadCapacity() {
        return tcLoadCapacity;
    }

    public void setTcLoadCapacity(String tcLoadCapacity) {
        this.tcLoadCapacity = tcLoadCapacity;
    }

    public String getTcLoadMoment() {
        return tcLoadMoment;
    }

    public void setTcLoadMoment(String tcLoadMoment) {
        this.tcLoadMoment = tcLoadMoment;
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

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getInstallCompany() {
        return installCompany;
    }

    public void setInstallCompany(String installCompany) {
        this.installCompany = installCompany;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getFrontArmLength() {
        return frontArmLength;
    }

    public void setFrontArmLength(Double frontArmLength) {
        this.frontArmLength = frontArmLength;
    }

    public Double getBackArmLength() {
        return backArmLength;
    }

    public void setBackArmLength(Double backArmLength) {
        this.backArmLength = backArmLength;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCraneName() {
        return craneName;
    }

    public void setCraneName(String craneName) {
        this.craneName = craneName;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getHxzId() {
        return hxzId;
    }

    public void setHxzId(String hxzId) {
        this.hxzId = hxzId;
    }
}
