package com.hujiang.project.zhgd.sbCraneAlarmChangeDataCrane.domain;

/**
 * @ClassName SbCraneAlarmChangeDataCrane
 * @Description
 * @Author xieya
 * @Date 2020/3/27  18:59
 */
public class SbCraneAlarmChangeDataCrane {

    private Integer id;
    /**黑匣子厂家*/
    private String hxzFactory;
    /**黑匣子编号*/
    private String HxzId;
    /**采集时间*/
    private String RTime;
    /**报警动作   0：解除  1：产生*/
    private Integer AlarmAction;
    /**报警类型
     * 11：角度左限位报警
     * 12：角度右限位报警
     * 13：变幅内限位报警
     * 14：变幅外限位报警
     * 15：高度上限位报警
     * 21：多机左转报警
     * 22：多机右转报警
     * 23：多机向内变幅报警
     * 24：多机向外变幅报警
     * 31：禁吊区左转报警
     * 32：禁吊区右转报警
     * 33：禁吊区向内变幅报警
     * 34：禁吊区向外变幅报警
     * 41：禁入区左转报警
     * 42：禁入区右转报警
     * 51：吊重报警
     * 61：风速报警
     * 71：未打卡操作报警
     * 81：倾角报警
     *
     * */
    private Integer AlarmType;
    /**力矩百分比*/
    private float Moment;
    /**载重*/
    private float Weight;
    /**额定载重*/
    private float RatedWeight;
    /**风速*/
    private float WindSpeed;
    /**风级*/
    private Integer WindLevel;
    /**幅度*/
    private float RRange;
    /**高度*/
    private float Height;
    /**角度*/
    private float Angle;
    /**倾角*/
    private float Obliguity;
    /**倾角X*/
    private float ObliguityX;
    /**倾角Y*/
    private float ObliguityY;
    /**工号  管理员：0     操作员：1~99     未登陆：255*/
    private Integer WorkNo;
    /**操作人员姓名*/
    private String Name;
    /**身份证号*/
    private String IdNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHxzFactory() {
        return hxzFactory;
    }

    public void setHxzFactory(String hxzFactory) {
        this.hxzFactory = hxzFactory;
    }

    public String getHxzId() {
        return HxzId;
    }

    public void setHxzId(String hxzId) {
        HxzId = hxzId;
    }

    public String getRTime() {
        return RTime;
    }

    public void setRTime(String RTime) {
        this.RTime = RTime;
    }

    public Integer getAlarmAction() {
        return AlarmAction;
    }

    public void setAlarmAction(Integer alarmAction) {
        AlarmAction = alarmAction;
    }

    public Integer getAlarmType() {
        return AlarmType;
    }

    public void setAlarmType(Integer alarmType) {
        AlarmType = alarmType;
    }

    public float getMoment() {
        return Moment;
    }

    public void setMoment(float moment) {
        Moment = moment;
    }

    public float getWeight() {
        return Weight;
    }

    public void setWeight(float weight) {
        Weight = weight;
    }

    public float getRatedWeight() {
        return RatedWeight;
    }

    public void setRatedWeight(float ratedWeight) {
        RatedWeight = ratedWeight;
    }

    public float getWindSpeed() {
        return WindSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        WindSpeed = windSpeed;
    }

    public Integer getWindLevel() {
        return WindLevel;
    }

    public void setWindLevel(Integer windLevel) {
        WindLevel = windLevel;
    }

    public float getRRange() {
        return RRange;
    }

    public void setRRange(float RRange) {
        this.RRange = RRange;
    }

    public float getHeight() {
        return Height;
    }

    public void setHeight(float height) {
        Height = height;
    }

    public float getAngle() {
        return Angle;
    }

    public void setAngle(float angle) {
        Angle = angle;
    }

    public float getObliguity() {
        return Obliguity;
    }

    public void setObliguity(float obliguity) {
        Obliguity = obliguity;
    }

    public float getObliguityX() {
        return ObliguityX;
    }

    public void setObliguityX(float obliguityX) {
        ObliguityX = obliguityX;
    }

    public float getObliguityY() {
        return ObliguityY;
    }

    public void setObliguityY(float obliguityY) {
        ObliguityY = obliguityY;
    }

    public Integer getWorkNo() {
        return WorkNo;
    }

    public void setWorkNo(Integer workNo) {
        WorkNo = workNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIdNo() {
        return IdNo;
    }

    public void setIdNo(String idNo) {
        IdNo = idNo;
    }
}