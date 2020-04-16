package com.hujiang.project.zhgd.sbHire.domain;

public class SbAreaCertificate {
    private Integer tertiaryEducation;
    private Integer laborContract;
    private Integer digitalTraining;
    private String  skilCertificate;
    private Integer userId;

    public Integer getTertiaryEducation() {
        return tertiaryEducation;
    }

    public void setTertiaryEducation(Integer tertiaryEducation) {
        this.tertiaryEducation = tertiaryEducation;
    }

    public Integer getLaborContract() {
        return laborContract;
    }

    public void setLaborContract(Integer laborContract) {
        this.laborContract = laborContract;
    }

    public Integer getDigitalTraining() {
        return digitalTraining;
    }

    public void setDigitalTraining(Integer digitalTraining) {
        this.digitalTraining = digitalTraining;
    }

    public String getSkilCertificate() {
        return skilCertificate;
    }

    public void setSkilCertificate(String skilCertificate) {
        this.skilCertificate = skilCertificate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
