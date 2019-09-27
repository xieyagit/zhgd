package com.hujiang.project.zhgd.hjZhgdVehicle.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class VehicleInList {


    /** 记录唯一标识 */
    @JsonProperty
    private String RecordId;
    /** 车牌 */
    @JsonProperty
    private String VehicleNo;
    /** 车牌颜色 默认 1 */
    @JsonProperty
    private String PlateColor;
    /** 项目id（智慧工地的项目id） */
    @JsonProperty
    private Integer DeptID;
    /** 摄像头编号 */
    @JsonProperty
    private String Camera_Mac;
    /** 抬杆方式 1-自动抬杠 2-手动抬杆 */
    @JsonProperty
    private String LiftType;
    /** 抬杆时间 */
    @JsonProperty
    private Date LiftTime;
    /** 进出方向 1-进 2-出 */
    @JsonProperty
    private Integer InOut;
    /** 图片 */
    @JsonProperty
    private String img;
    /** 0-临时车 1-月租车 */
    @JsonProperty
    private Integer CardType;
    /** 车牌颜色 */
    @JsonProperty
    private String color;

    /** 出口地址 */
    @JsonProperty
    private Integer Address;
    /** 摄像头IP */
    @JsonProperty
    private String AddressIP;
    /** 辅助摄像头IP */
    @JsonProperty
    private String AssistAddressIP;
    /** 电脑IP */
    @JsonProperty
    private String Computer_IP;
    /** 自增ID */
    @JsonProperty
    private Integer DevID;
    /** 设备名称 */
    @JsonProperty
    private String DevName;
    /** 摄像头类型 1-地面库 2-地库 */
    @JsonProperty
    private Integer DevType;
    /** GUid */
    @JsonProperty
    private String GUID;
    /** 进出标识 1-进 2-出 */
    @JsonProperty
    private Integer InOROut;
    /** 车位剩余数 */
    @JsonProperty
    private Integer PkCount;


    public String getRecordId() {
        return RecordId;
    }

    public void setRecordId(String recordId) {
        RecordId = recordId;
    }

    public String getVehicleNo() {
        return VehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        VehicleNo = vehicleNo;
    }

    public String getPlateColor() {
        return PlateColor;
    }

    public void setPlateColor(String plateColor) {
        PlateColor = plateColor;
    }

    public Integer getDeptID() {
        return DeptID;
    }

    public void setDeptID(Integer deptID) {
        DeptID = deptID;
    }

    public String getCamera_Mac() {
        return Camera_Mac;
    }

    public void setCamera_Mac(String camera_Mac) {
        Camera_Mac = camera_Mac;
    }

    public String getLiftType() {
        return LiftType;
    }

    public void setLiftType(String liftType) {
        LiftType = liftType;
    }

    public Date getLiftTime() {
        return LiftTime;
    }

    public void setLiftTime(Date liftTime) {
        LiftTime = liftTime;
    }

    public Integer getInOut() {
        return InOut;
    }

    public void setInOut(Integer inOut) {
        InOut = inOut;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCardType() {
        return CardType;
    }

    public void setCardType(Integer cardType) {
        CardType = cardType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAddress() {
        return Address;
    }

    public void setAddress(Integer address) {
        Address = address;
    }

    public String getAddressIP() {
        return AddressIP;
    }

    public void setAddressIP(String addressIP) {
        AddressIP = addressIP;
    }

    public String getAssistAddressIP() {
        return AssistAddressIP;
    }

    public void setAssistAddressIP(String assistAddressIP) {
        AssistAddressIP = assistAddressIP;
    }

    public String getComputer_IP() {
        return Computer_IP;
    }

    public void setComputer_IP(String computer_IP) {
        Computer_IP = computer_IP;
    }

    public Integer getDevID() {
        return DevID;
    }

    public void setDevID(Integer devID) {
        DevID = devID;
    }

    public String getDevName() {
        return DevName;
    }

    public void setDevName(String devName) {
        DevName = devName;
    }

    public Integer getDevType() {
        return DevType;
    }

    public void setDevType(Integer devType) {
        DevType = devType;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public Integer getInOROut() {
        return InOROut;
    }

    public void setInOROut(Integer inOROut) {
        InOROut = inOROut;
    }

    public Integer getPkCount() {
        return PkCount;
    }

    public void setPkCount(Integer pkCount) {
        PkCount = pkCount;
    }
}
