package com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain;

public class Project {
    private int projectId;
    private int constructionId;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(int constructionId) {
        this.constructionId = constructionId;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", constructionId=" + constructionId +
                '}';
    }
}
