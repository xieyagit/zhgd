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
