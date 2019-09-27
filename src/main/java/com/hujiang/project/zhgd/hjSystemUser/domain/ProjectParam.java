package com.hujiang.project.zhgd.hjSystemUser.domain;

public class ProjectParam {


    /** 项目id */
    private Integer projectId;
    /** 项目名称 */
    private String projectName;


    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "ProjectParam{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
