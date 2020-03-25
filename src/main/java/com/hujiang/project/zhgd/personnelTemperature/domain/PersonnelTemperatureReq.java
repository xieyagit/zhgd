package com.hujiang.project.zhgd.personnelTemperature.domain;

/**
 * @ClassName PersonnelTemperatureReq
 * @Description TODO
 * @Author xieya
 * @Date 2020/3/25  10:23
 */
public class PersonnelTemperatureReq {

    /**姓名*/
    private String name;
    /**开始时间*/
    private String startDate;
    /**结束时间*/
    private String endDate;
    /**是否需要隔离*/
    private String quarantine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getQuarantine() {
        return quarantine;
    }

    public void setQuarantine(String quarantine) {
        this.quarantine = quarantine;
    }
}