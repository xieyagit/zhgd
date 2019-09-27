package com.hujiang.project.zhgd.hjProjectWorkers.domain;

public class ProjectWorkers {

    private Integer workersId; // 工人id
    private Integer projectId; // 项目id
    private String empPhon; // 手机号码
    private String isTeam; // 是否班组长(0否，1是)
    private String startTime; // 进场日期
    private String titleName; // 工种
    private String personneType; // 人员类型
    private String typeName; // 岗位
    private String projectName; // 项目名称
    private String teamName; // 班组名称
    private String constructionName; // 参建单位

    public String getEmpPhon() {
        return empPhon;
    }

    public void setEmpPhon(String empPhon) {
        this.empPhon = empPhon;
    }

    public String getIsTeam() {
        return isTeam;
    }

    public void setIsTeam(String isTeam) {
        this.isTeam = isTeam;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getPersonneType() {
        return personneType;
    }

    public void setPersonneType(String personneType) {
        this.personneType = personneType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public Integer getWorkersId() {
        return workersId;
    }

    public void setWorkersId(Integer workersId) {
        this.workersId = workersId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
