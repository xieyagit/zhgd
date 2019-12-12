package com.hujiang.project.zhgd.sbProjectVideoArea.domain;

import java.util.List;

/**
 * 施工队
 */
public class Construction {


    private String constructionName;//单位名称
    private String constructionType;//单位类型
    private String contacts;//单位负责人
    private String mobilePhone;//负责人联系方式

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }
}
