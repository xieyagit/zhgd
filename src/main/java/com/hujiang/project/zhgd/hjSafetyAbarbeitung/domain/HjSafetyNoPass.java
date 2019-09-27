package com.hujiang.project.zhgd.hjSafetyAbarbeitung.domain;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class HjSafetyNoPass {

    private int id;
    private int safetyId;      //整改ID
    private String safetyName;  //整改几次
    private String safetyCreateTime;    //整改创建时间

    private String rectifyResult;   //整改结果
    private String rectifyPhotos;   //整改后现场照片
    private String rectifyTime;     //整改后时间

    private String reviewOpinions;  //复查意见
    private String reviewResult;    //复查结果
    private String reviewPath;  //复查后照片
    private String reviewTime;      //复查时间


    private String initiatorName;   //发起整改人姓名
    private String rectifyName;     //整改人姓名
    private String reviewName;      //复查人姓名

    private Integer userId;     //用户ID

    private int status;

    private MultipartFile[] file;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSafetyId() {
        return safetyId;
    }

    public void setSafetyId(int safetyId) {
        this.safetyId = safetyId;
    }

    public String getSafetyName() {
        return safetyName;
    }

    public void setSafetyName(String safetyName) {
        this.safetyName = safetyName;
    }

    public String getSafetyCreateTime() {
        return safetyCreateTime;
    }

    public void setSafetyCreateTime(String safetyCreateTime) {
        this.safetyCreateTime = safetyCreateTime;
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

    public String getRectifyTime() {
        return rectifyTime;
    }

    public void setRectifyTime(String rectifyTime) {
        this.rectifyTime = rectifyTime;
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

    public String getReviewPath() {
        return reviewPath;
    }

    public void setReviewPath(String reviewPath) {
        this.reviewPath = reviewPath;
    }

    public String getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
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

    public MultipartFile[] getFile() {
        return file;
    }

    public void setFile(MultipartFile[] file) {
        this.file = file;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "HjSafetyNoPass{" +
                "id=" + id +
                ", safetyId=" + safetyId +
                ", safetyName='" + safetyName + '\'' +
                ", safetyCreateTime='" + safetyCreateTime + '\'' +
                ", rectifyResult='" + rectifyResult + '\'' +
                ", rectifyPhotos='" + rectifyPhotos + '\'' +
                ", rectifyTime='" + rectifyTime + '\'' +
                ", reviewOpinions='" + reviewOpinions + '\'' +
                ", reviewResult='" + reviewResult + '\'' +
                ", reviewPath='" + reviewPath + '\'' +
                ", reviewTime='" + reviewTime + '\'' +
                ", initiatorName='" + initiatorName + '\'' +
                ", rectifyName='" + rectifyName + '\'' +
                ", reviewName='" + reviewName + '\'' +
                ", userId=" + userId +
                ", status=" + status +
                ", file=" + Arrays.toString(file) +
                '}';
    }
}
