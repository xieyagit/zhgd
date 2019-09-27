package com.hujiang.project.zhgd.hjZhgdVehicle.domain;

public class BaseInfo {

    private String RecordId;//记录标识
    private String VehicleNo;//车牌
    private String PlateColor;//车牌颜色：1-蓝，2-黄，3-黑， 9-其他
    private String GateNo;//闸机编号：惟一标识，最好是摄像机的MAC地址，冒号用“-”代替
    private String RegionNo;//区域编号
    private Integer RegionType; //区域类型：1-工地，2-受纳场所
    private String RegionName;//区域名称：工地或受纳场名称
    private String LiftType;//抬杆方式：1-自动抬杆，2-手动抬杆
    private String LiftTime;//抬杆时间

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

    public String getGateNo() {
        return GateNo;
    }

    public void setGateNo(String gateNo) {
        GateNo = gateNo;
    }

    public String getRegionNo() {
        return RegionNo;
    }

    public void setRegionNo(String regionNo) {
        RegionNo = regionNo;
    }

    public Integer getRegionType() {
        return RegionType;
    }

    public void setRegionType(Integer regionType) {
        RegionType = regionType;
    }

    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String regionName) {
        RegionName = regionName;
    }

    public String getLiftType() {
        return LiftType;
    }

    public void setLiftType(String liftType) {
        LiftType = liftType;
    }

    public String getLiftTime() {
        return LiftTime;
    }

    public void setLiftTime(String liftTime) {
        LiftTime = liftTime;
    }
}
