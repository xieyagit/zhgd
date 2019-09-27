package com.hujiang.project.zhgd.hjZhgdVehicle.domain;

public class UploadCarout {


    private String service; // 	是	string	接口名称	uploadcarout
    private String parkid;//	是	string	车场ID	20180001
    private String order_id;//	是	string	订单记录号	118881
    private String car_number;//	是	string	车牌号	粤B99999
    private String in_time;//	是	string	入场时间	2018-07-25 19:35:40
    private String out_time;//	是	string	出场时间	2018-07-25 22:14:11
    private Integer car_type;//	是	int	车辆类型	0小车1大车
    private Integer card_type;//	是	int	车牌类型	0临时车1月租车2充值车3贵宾车4免费车8收费月租车
    private Integer gateinid;//	是	int	入场通道ID	1
    private String gateinname;//	是	string	入场通道名称	南门入口
    private Integer gateoutid;//	是	int	出场通道ID	2
    private String gateoutname;//	是	string	出场通道名称	南门出口
    private String operatorin;//	是	string	入场操作员	张三
    private String operatorout;//	是	string	出场操作员	李四
    private String paycharge;//	是	string	应收金额	20
    private String realcharge;//	是	string	实收金额	10
    private String breaks_amount;//	否	string	减免金额	4.5
    private String discount_amount;//	否	string	优惠金额	5.5
    private String discount_no;//	否	string	优惠券	YHQ1234554
    private String discount_reason;//	否	string	优惠原因	购物后打折
    private String pay_type;//	是	string	支付方式	现金扫描枪 预支付 出口直付 无感支付
    private Integer payed;//	是	int	是否已支付	0已支付 1未支付
    private String remark;//	否	string	备注


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

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
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

    public Integer getGateoutid() {
        return gateoutid;
    }

    public void setGateoutid(Integer gateoutid) {
        this.gateoutid = gateoutid;
    }

    public String getGateoutname() {
        return gateoutname;
    }

    public void setGateoutname(String gateoutname) {
        this.gateoutname = gateoutname;
    }

    public String getOperatorin() {
        return operatorin;
    }

    public void setOperatorin(String operatorin) {
        this.operatorin = operatorin;
    }

    public String getOperatorout() {
        return operatorout;
    }

    public void setOperatorout(String operatorout) {
        this.operatorout = operatorout;
    }

    public String getPaycharge() {
        return paycharge;
    }

    public void setPaycharge(String paycharge) {
        this.paycharge = paycharge;
    }

    public String getRealcharge() {
        return realcharge;
    }

    public void setRealcharge(String realcharge) {
        this.realcharge = realcharge;
    }

    public String getBreaks_amount() {
        return breaks_amount;
    }

    public void setBreaks_amount(String breaks_amount) {
        this.breaks_amount = breaks_amount;
    }

    public String getDiscount_amount() {
        return discount_amount;
    }

    public void setDiscount_amount(String discount_amount) {
        this.discount_amount = discount_amount;
    }

    public String getDiscount_no() {
        return discount_no;
    }

    public void setDiscount_no(String discount_no) {
        this.discount_no = discount_no;
    }

    public String getDiscount_reason() {
        return discount_reason;
    }

    public void setDiscount_reason(String discount_reason) {
        this.discount_reason = discount_reason;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public Integer getPayed() {
        return payed;
    }

    public void setPayed(Integer payed) {
        this.payed = payed;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
