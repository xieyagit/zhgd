package com.hujiang.project.zhgd.hjProjectWorkers.domain;

public class EmpName {

    /** ID */
    private Integer id;
    /** 姓名 */
    private String empName;
    /** 人脸照片 */
    private String faceUrl;

    /** 人脸照片 */
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

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
