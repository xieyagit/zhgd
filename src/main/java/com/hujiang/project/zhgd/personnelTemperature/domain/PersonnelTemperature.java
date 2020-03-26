package com.hujiang.project.zhgd.personnelTemperature.domain;

/**
 * @ClassName PersonnelTemperature
 * @Description 人员体温管理
 * @Author xieya
 * @Date 2020/3/25  10:20
 */
public class PersonnelTemperature {

    /**人员id*/
    private Integer id;
    /**姓名*/
    private String empName;
    /**是否隔离，1，隔离，0或空，正常*/
    private String quarantine;
    /**参建单位名称*/
    private String constructionName;
    /**工种*/
    private String title;
    /**进出情况*/
    private String direction;
    /**通过时间*/
    private String passedTime;
    /**体温*/
    private String temperature;
    /**项目id*/
    private String projectId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getQuarantine() {
        return quarantine;
    }

    public void setQuarantine(String quarantine) {
        this.quarantine = quarantine;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPassedTime() {
        return passedTime;
    }

    public void setPassedTime(String passedTime) {
        this.passedTime = passedTime;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "PersonnelTemperature{" +
                "id=" + id +
                ", empName='" + empName + '\'' +
                ", quarantine='" + quarantine + '\'' +
                ", constructionName='" + constructionName + '\'' +
                ", title='" + title + '\'' +
                ", direction='" + direction + '\'' +
                ", passedTime='" + passedTime + '\'' +
                ", temperature='" + temperature + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}