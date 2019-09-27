package com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain;

import org.apache.ibatis.annotations.Param;

public class PcInspectionRecord {
    private Integer safetyId;
    private Integer status;
    private String safetyDescribe;
    private String gradeName;
    private String areaName;
    private String hiddenName;
    private String initiatorName;
    private String initiatorTime;
    private String rectifyName;
    private String constructionName;
    private String safetyDeadline;

    public Integer getSafetyId() {
        return safetyId;
    }

    public void setSafetyId(Integer safetyId) {
        this.safetyId = safetyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSafetyDescribe() {
        return safetyDescribe;
    }

    public void setSafetyDescribe(String safetyDescribe) {
        this.safetyDescribe = safetyDescribe;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getHiddenName() {
        return hiddenName;
    }

    public void setHiddenName(String hiddenName) {
        this.hiddenName = hiddenName;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public String getInitiatorTime() {
        return initiatorTime;
    }

    public void setInitiatorTime(String initiatorTime) {
        this.initiatorTime = initiatorTime;
    }

    public String getRectifyName() {
        return rectifyName;
    }

    public void setRectifyName(String rectifyName) {
        this.rectifyName = rectifyName;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getSafetyDeadline() {
        return safetyDeadline;
    }

    public void setSafetyDeadline(String safetyDeadline) {
        this.safetyDeadline = safetyDeadline;
    }

    @Override
    public String toString() {
        return "PcInspectionRecord{" +
                "safetyId=" + safetyId +
                ", status=" + status +
                ", safetyDescribe='" + safetyDescribe + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", hiddenName='" + hiddenName + '\'' +
                ", initiatorName='" + initiatorName + '\'' +
                ", initiatorTime='" + initiatorTime + '\'' +
                ", rectifyName='" + rectifyName + '\'' +
                ", constructionName='" + constructionName + '\'' +
                ", safetyDeadline='" + safetyDeadline + '\'' +
                '}';
    }
}
