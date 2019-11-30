package com.hujiang.project.zhgd.sbProjectVideoArea.domain;

import java.util.List;

/**
 * 项目
 */
public class ProjectVideoJT {

    private Integer pid;
    private String projectName;
    private List<Video> videoList;

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
