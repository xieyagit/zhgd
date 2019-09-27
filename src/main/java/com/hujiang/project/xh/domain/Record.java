package com.hujiang.project.xh.domain;

/**
 * 门禁类
 */
public class Record {
    public String userId;//用户ID
    public String phone;//手机号
    public String type;//出入类型
    public String extend1;//扩展字段

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtend1() {
        return extend1;
    }

    public void setExtend1(String extend1) {
        this.extend1 = extend1;
    }
}
