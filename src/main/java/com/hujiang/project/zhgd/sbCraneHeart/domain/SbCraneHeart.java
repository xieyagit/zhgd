package com.hujiang.project.zhgd.sbCraneHeart.domain;

/**
 * @ClassName SbCraneHeart
 * @Description 上报心跳数据(德业对接)
 * @Author xieya
 * @Date 2020/3/27  17:23
 */
public class SbCraneHeart {
    /**id*/
    private Integer id;
    /**黑匣子厂家*/
    private String hxzFactory;
    /**黑匣子编号*/
    private String hxzId;
    /**风速*/
    private float windSpeed;
    /**风级*/
    private Integer windLevel;
    /**风速报警   0:正常   1:报警  2:预警*/
    private String windSpeedAlarm;
    /**工号*/
    private Integer workNo;
    /**操作员名称*/
    private String name;
    /**身份证号*/
    private String idNo;

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
        return hxzId;
    }

    public void setHxzId(String hxzId) {
        this.hxzId = hxzId;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindLevel() {
        return windLevel;
    }

    public void setWindLevel(Integer windLevel) {
        this.windLevel = windLevel;
    }

    public String getWindSpeedAlarm() {
        return windSpeedAlarm;
    }

    public void setWindSpeedAlarm(String windSpeedAlarm) {
        this.windSpeedAlarm = windSpeedAlarm;
    }

    public Integer getWorkNo() {
        return workNo;
    }

    public void setWorkNo(Integer workNo) {
        this.workNo = workNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
}