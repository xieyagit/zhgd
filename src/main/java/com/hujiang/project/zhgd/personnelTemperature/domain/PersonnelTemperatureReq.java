package com.hujiang.project.zhgd.personnelTemperature.domain;

/**
 * @ClassName PersonnelTemperatureReq
 * @Description TODO
 * @Author xieya
 * @Date 2020/3/25  10:23
 */
public class PersonnelTemperatureReq {

    /**姓名*/
    private String empName;
    /**开始时间*/
    private String startDate;
    /**结束时间*/
    private String endDate;
    /**是否需要隔离*/
    private String quarantine;
    /**项目id*/
    private String projectId;
    /**体温*/
    private String temperature;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}