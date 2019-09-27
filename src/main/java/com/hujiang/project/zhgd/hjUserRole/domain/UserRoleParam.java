package com.hujiang.project.zhgd.hjUserRole.domain;

public class UserRoleParam {

    /** 用户ID */
    private Integer userId;
    /** 账户类型（0.集团，1：公司  2：项目） */
    private Integer userType;
    /** 集团 公司 项目 id */
    private Integer parameterId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getParameterId() {
        return parameterId;
    }

    public void setParameterId(Integer parameterId) {
        this.parameterId = parameterId;
    }
}
