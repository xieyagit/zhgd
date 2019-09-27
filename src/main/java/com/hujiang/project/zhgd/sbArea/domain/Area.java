package com.hujiang.project.zhgd.sbArea.domain;

import java.util.Date;

public class Area {
    /**项目ID*/
    private int jid;
    /** 项目名 */
    private String projectName;
    /**科技园ID*/
    private int aid;
    /**科技园名*/
    private String aname;
    /**科技园经度*/
    private Double axloc;
    /**科技园纬度*/
    private Double ayloc;
    /**科技园半径*/
    private Double radius;
    /**人员ID*/
    private int pid;
    /**人员名*/
    private String pname;
    /**设备ID*/
    private String imei;
    /**创建时间*/
    private Date createDate;
    /**人员设备经度*/
    private double pxloc;
    /**人员设备纬度*/
    private double pyloc;

    public int getJid() {
        return jid;
    }

    public void setJid(int jid) {
        this.jid = jid;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Double getAxloc() {
        return axloc;
    }

    public void setAxloc(Double axloc) {
        this.axloc = axloc;
    }

    public Double getAyloc() {
        return ayloc;
    }

    public void setAyloc(Double ayloc) {
        this.ayloc = ayloc;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

//    public String getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(String createDate) {
//        this.createDate = createDate;
//    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getPxloc() {
        return pxloc;
    }

    public void setPxloc(double pxloc) {
        this.pxloc = pxloc;
    }

    public double getPyloc() {
        return pyloc;
    }

    public void setPyloc(double pyloc) {
        this.pyloc = pyloc;
    }
}
