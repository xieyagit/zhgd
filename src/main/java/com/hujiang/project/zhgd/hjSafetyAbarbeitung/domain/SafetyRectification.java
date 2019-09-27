package com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain;

public class SafetyRectification {
    private String makeId;
    private String makeName;
    private Integer userId;
    private Integer status;

    private Integer areaId;
    private String areaName;
    private Integer hiddenId;
    private String hiddenName;
    private String gradeName;

    private Integer safetyId;
    private String safetyRequire;
    private String safetyDeadline;
    private String safetyPhotos;
    private String safetyCreateTime;
    private String safetyDescribe;

    private Integer initiatorId;
    private String initiatorTime;
    private String initiatorName;



    private Integer rectifyId;
    private String rectifyResult;
    private String rectifyPhotos;
    private String rectifyTime;
    private String rectifyName;
    private String reviewName;



    private Integer constructionId;
    private String constructionName;


    private Integer reviewId;
    private String reviewPath;
    private String reviewOpinions;
    private String reviewResult;
    private String reviewTime;
    public int getSafetyId() {
        return safetyId;
    }

    public void setSafetyId(int safetyId) {
        this.safetyId = safetyId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSafetyRequire() {
        return safetyRequire;
    }

    public void setSafetyRequire(String safetyRequire) {
        this.safetyRequire = safetyRequire;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public String getSafetyDeadline() {
        return safetyDeadline;
    }

    public void setSafetyDeadline(String safetyDeadline) {
        this.safetyDeadline = safetyDeadline;
    }

    public String getSafetyPhotos() {
        return safetyPhotos;
    }

    public void setSafetyPhotos(String safetyPhotos) {
        this.safetyPhotos = safetyPhotos;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getRectifyName() {
        return rectifyName;
    }

    public void setRectifyName(String rectifyName) {
        this.rectifyName = rectifyName;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public String getSafetyCreateTime() {
        return safetyCreateTime;
    }

    public void setSafetyCreateTime(String safetyCreateTime) {
        this.safetyCreateTime = safetyCreateTime;
    }

    public String getHiddenName() {
        return hiddenName;
    }

    public void setHiddenName(String hiddenName) {
        this.hiddenName = hiddenName;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getSafetyDescribe() {
        return safetyDescribe;
    }

    public void setSafetyDescribe(String safetyDescribe) {
        this.safetyDescribe = safetyDescribe;
    }

    public String getInitiatorTime() {
        return initiatorTime;
    }

    public void setInitiatorTime(String initiatorTime) {
        this.initiatorTime = initiatorTime;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getRectifyResult() {
        return rectifyResult;
    }

    public void setRectifyResult(String rectifyResult) {
        this.rectifyResult = rectifyResult;
    }

    public String getRectifyPhotos() {
        return rectifyPhotos;
    }

    public void setRectifyPhotos(String rectifyPhotos) {
        this.rectifyPhotos = rectifyPhotos;
    }

    public String getReviewPath() {
        return reviewPath;
    }

    public void setReviewPath(String reviewPath) {
        this.reviewPath = reviewPath;
    }

    public String getReviewOpinions() {
        return reviewOpinions;
    }

    public void setReviewOpinions(String reviewOpinions) {
        this.reviewOpinions = reviewOpinions;
    }

    public String getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(String reviewResult) {
        this.reviewResult = reviewResult;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMakeId() {
        return makeId;
    }

    public void setMakeId(String makeId) {
        this.makeId = makeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setSafetyId(Integer safetyId) {
        this.safetyId = safetyId;
    }

    public String getRectifyTime() {
        return rectifyTime;
    }

    public void setRectifyTime(String rectifyTime) {
        this.rectifyTime = rectifyTime;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Integer getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Integer constructionId) {
        this.constructionId = constructionId;
    }

    public Integer getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(Integer initiatorId) {
        this.initiatorId = initiatorId;
    }

    public Integer getRectifyId() {
        return rectifyId;
    }

    public void setRectifyId(Integer rectifyId) {
        this.rectifyId = rectifyId;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(Integer hiddenId) {
        this.hiddenId = hiddenId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    @Override
    public String toString() {
        return "SafetyRectification{" +
                "makeId='" + makeId + '\'' +
                ", status=" + status +
                ", safetyId=" + safetyId +
                ", areaName='" + areaName + '\'' +
                ", safetyRequire='" + safetyRequire + '\'' +
                ", initiatorName='" + initiatorName + '\'' +
                ", safetyDeadline='" + safetyDeadline + '\'' +
                ", safetyPhotos='" + safetyPhotos + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", rectifyName='" + rectifyName + '\'' +
                ", reviewName='" + reviewName + '\'' +
                ", safetyCreateTime='" + safetyCreateTime + '\'' +
                ", hiddenName='" + hiddenName + '\'' +
                ", constructionId=" + constructionId +
                ", constructionName='" + constructionName + '\'' +
                ", safetyDescribe='" + safetyDescribe + '\'' +
                ", initiatorTime='" + initiatorTime + '\'' +
                ", rectifyResult='" + rectifyResult + '\'' +
                ", rectifyPhotos='" + rectifyPhotos + '\'' +
                ", rectifyTime='" + rectifyTime + '\'' +
                ", reviewPath='" + reviewPath + '\'' +
                ", reviewOpinions='" + reviewOpinions + '\'' +
                ", reviewResult='" + reviewResult + '\'' +
                ", reviewTime='" + reviewTime + '\'' +
                ", initiatorId=" + initiatorId +
                ", rectifyId=" + rectifyId +
                ", reviewId=" + reviewId +
                ", hiddenId=" + hiddenId +
                ", areaId=" + areaId +
                ", makeName='" + makeName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
