package com.hujiang.project.zhgd.hjZhgdVehicle.domain;

public class UploadCarin {



    private String service;//string	接口名称	uploadcarin
    private String parkid;//string	车场ID	20180001
    private String order_id;//string	订单记录号	118881
    private String car_number;//string	车牌号	粤B99999
    private String in_time;//string	入场时间	2018-07-25 19:35:40
    private Integer car_type;//int	车辆类型	0小车 1大车
    private Integer card_type;//int	车牌类型	0临时车 1月租车 2充值车 3贵宾车 4免费车 8收费月租车
    private Integer gateinid;// int	入场通道ID	1
    private String gateinname;//string	入场通道名称	南门入口
    private String operatorin;//string	入场操作员	张三
    private String remark;// string	备注


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

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }

    public Integer getCar_type() {
        return car_type;
    }

    public void setCar_type(Integer car_type) {
        this.car_type = car_type;
    }

    public Integer getCard_type() {
        return card_type;
    }

    public void setCard_type(Integer card_type) {
        this.card_type = card_type;
    }

    public Integer getGateinid() {
        return gateinid;
    }

    public void setGateinid(Integer gateinid) {
        this.gateinid = gateinid;
    }

    public String getGateinname() {
        return gateinname;
    }

    public void setGateinname(String gateinname) {
        this.gateinname = gateinname;
    }

    public String getOperatorin() {
        return operatorin;
    }

    public void setOperatorin(String operatorin) {
        this.operatorin = operatorin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
