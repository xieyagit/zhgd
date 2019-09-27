package com.hujiang.project.zhgd.sbUnloaderRegistration.domain;

import com.hujiang.framework.aspectj.lang.annotation.Excel;

public class ExportUnloaderRealtime {
    /** 采集时间 */
    @Excel(name = "时间")
    private String rTime;
    /** 载重0.00~2.00t */
    @Excel(name = "载重")
    private Float weight;
    /** 载重百分比0.00~120.00% */
    @Excel(name = "载重百分比")
    private Float weightPercent;
    /** 倾角X-9.99°~9.99° */
    @Excel(name = "倾角X°")
    private Float obliguityX;
    /** 倾角Y-9.99°~9.99° */
    @Excel(name = "倾角Y°")
    private Float obliguityY;


    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getWeightPercent() {
        return weightPercent;
    }

    public void setWeightPercent(Float weightPercent) {
        this.weightPercent = weightPercent;
    }

    public Float getObliguityX() {
        return obliguityX;
    }

    public void setObliguityX(Float obliguityX) {
        this.obliguityX = obliguityX;
    }

    public Float getObliguityY() {
        return obliguityY;
    }

    public void setObliguityY(Float obliguityY) {
        this.obliguityY = obliguityY;
    }


}
