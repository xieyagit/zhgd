package com.hujiang.project.zhgd.hjAttendanceRecord.domain;

/**
 * 考勤动态
 */
public class DongTai {

    private static final long serialVersionUID = 1L;

    private  String name;//姓名
    private String passedTime;//通行时间
    private String direction;//进出
    private String idCode;//身份证号码

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassedTime() {
        return passedTime;
    }

    public void setPassedTime(String passedTime) {
        this.passedTime = passedTime;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
