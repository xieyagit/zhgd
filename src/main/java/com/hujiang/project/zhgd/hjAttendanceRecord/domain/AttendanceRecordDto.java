package com.hujiang.project.zhgd.hjAttendanceRecord.domain;

import java.io.Serializable;

/**
 *@ClassName AttendanceRecordDto
 *@Description TODO
 *@Author xieya
 *@Date 2020/4/20  15:17
 */
public class AttendanceRecordDto implements Serializable {

    /**证件类型*/
    private String idCardType;
    /**证件号码。 AES*/
    private String idCardNumber;
    /**进退场日期，yyyy-MM-dd HH:mm:ss*/
    private String date;
    /**刷卡进出方向   01 入场   02 出场*/
    private String direction;

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}