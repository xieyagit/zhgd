package com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain;

public class PcManagement {
    private String safetyId;
    private String initiatorName;
    private String constructionName;
    private String initiatorTime;
    private Integer status;

    public String getSafetyId() {
        return safetyId;
    }

    public void setSafetyId(String safetyId) {
        this.safetyId = safetyId;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getInitiatorTime() {
        return initiatorTime;
    }

    public void setInitiatorTime(String initiatorTime) {
        this.initiatorTime = initiatorTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PcManagement{" +
                "safetyId='" + safetyId + '\'' +
                ", initiatorName='" + initiatorName + '\'' +
                ", constructionName='" + constructionName + '\'' +
                ", initiatorTime='" + initiatorTime + '\'' +
                ", status=" + status +
                '}';
    }
}
