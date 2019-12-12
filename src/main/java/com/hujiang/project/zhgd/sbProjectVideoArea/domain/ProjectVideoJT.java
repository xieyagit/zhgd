package com.hujiang.project.zhgd.sbProjectVideoArea.domain;

import java.util.List;

/**
 * 项目
 */
public class ProjectVideoJT {

    private Integer pid;
    private String projectName;
    private List<Video> videoList;
    private String  projectAddress;
    private String remark;
    private List<Construction> cList;
    private String projectDept;//所属部门
    private String phone;//项目经理电话
    private String projectPrincipal;//项目经理

    public String getProjectDept() {
        return projectDept;
    }

    public void setProjectDept(String projectDept) {
        this.projectDept = projectDept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProjectPrincipal() {
        return projectPrincipal;
    }

    public void setProjectPrincipal(String projectPrincipal) {
        this.projectPrincipal = projectPrincipal;
    }

    public List<Construction> getcList() {
        return cList;
    }

    public void setcList(List<Construction> cList) {
        this.cList = cList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

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
