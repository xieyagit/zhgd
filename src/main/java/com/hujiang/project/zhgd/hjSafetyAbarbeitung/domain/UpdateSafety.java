package com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain;


public class UpdateSafety {
    private Integer id;
    private Integer safetyId;
    private Integer initiatorId;
    private Integer rectifyId;
    private Integer reviewId;
    private String makeId;
    private Integer areaId;
    private String safetyDeadline;
    private Integer hiddenId;
    private String safetyRequire;
    private String initiatorTime;
    private String rectifyTime;
    private String reviewTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMakeId() {
        return makeId;
    }

    public void setMakeId(String makeId) {
        this.makeId = makeId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getSafetyDeadline() {
        return safetyDeadline;
    }

    public void setSafetyDeadline(String safetyDeadline) {
        this.safetyDeadline = safetyDeadline;
    }

    public Integer getHiddenId() {
        return hiddenId;
    }

    public void setHiddenId(Integer hiddenId) {
        this.hiddenId = hiddenId;
    }

    public String getSafetyRequire() {
        return safetyRequire;
    }

    public void setSafetyRequire(String safetyRequire) {
        this.safetyRequire = safetyRequire;
    }

    public String getInitiatorTime() {
        return initiatorTime;
    }

    public void setInitiatorTime(String initiatorTime) {
        this.initiatorTime = initiatorTime;
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

    public Integer getSafetyId() {
        return safetyId;
    }

    public void setSafetyId(Integer safetyId) {
        this.safetyId = safetyId;
    }

    @Override
    public String toString() {
        return "UpdateSafety{" +
                "id=" + id +
                ", safetyId=" + safetyId +
                ", initiatorId=" + initiatorId +
                ", rectifyId=" + rectifyId +
                ", reviewId=" + reviewId +
                ", makeId='" + makeId + '\'' +
                ", areaId=" + areaId +
                ", safetyDeadline='" + safetyDeadline + '\'' +
                ", hiddenId=" + hiddenId +
                ", safetyRequire='" + safetyRequire + '\'' +
                ", initiatorTime='" + initiatorTime + '\'' +
                ", rectifyTime='" + rectifyTime + '\'' +
                ", reviewTime='" + reviewTime + '\'' +
                '}';
    }
}
