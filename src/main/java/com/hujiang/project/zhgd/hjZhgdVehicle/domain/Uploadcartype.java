package com.hujiang.project.zhgd.hjZhgdVehicle.domain;

public class Uploadcartype {

    private String service;
    private String parkid;
    private int car_type;
    private String car_typedesc;
    private int operate_type;

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

    public int getCar_type() {
        return car_type;
    }

    public void setCar_type(int car_type) {
        this.car_type = car_type;
    }

    public String getCar_typedesc() {
        return car_typedesc;
    }

    public void setCar_typedesc(String car_typedesc) {
        this.car_typedesc = car_typedesc;
    }

    public int getOperate_type() {
        return operate_type;
    }

    public void setOperate_type(int operate_type) {
        this.operate_type = operate_type;
    }
}
