package com.hujiang.project.zhgd.hjZhgdVehicle.domain;

public class Parkingspace {

    private String service;
    private String parkid;
    private int spacetotal;
    private int spaceLeft;
    private int spaceused;
    private String time;
    private String remark;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getParkid() {
        return parkid;
    }

    public void setParkid(String parkid) {
        this.parkid = parkid;
    }

    public int getSpacetotal() {
        return spacetotal;
    }

    public void setSpacetotal(int spacetotal) {
        this.spacetotal = spacetotal;
    }

    public int getSpaceLeft() {
        return spaceLeft;
    }

    public void setSpaceLeft(int spaceLeft) {
        this.spaceLeft = spaceLeft;
    }

    public int getSpaceused() {
        return spaceused;
    }

    public void setSpaceused(int spaceused) {
        this.spaceused = spaceused;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
