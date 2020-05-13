package com.hujiang.project.zhgd.hjDeeppit.model;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *@ClassName FactorModel
 *@Description TODO
 *@Author xieya
 *@Date 2020/5/10  14:53
 */
public class FactorModel implements Serializable {

    private Integer factorId;
    private String factorName;
    private String factorProto;
    private List<GroupModel> groups;

    public Integer getFactorId() {
        return factorId;
    }

    public void setFactorId(Integer factorId) {
        this.factorId = factorId;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public String getFactorProto() {
        return factorProto;
    }

    public void setFactorProto(String factorProto) {
        this.factorProto = factorProto;
    }

    public List<GroupModel> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupModel> groups) {
        this.groups = groups;
    }
}