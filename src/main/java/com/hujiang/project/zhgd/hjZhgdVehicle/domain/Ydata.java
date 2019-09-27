package com.hujiang.project.zhgd.hjZhgdVehicle.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ydata {

    @JsonProperty( "DeptID")
    private Integer DeptID;
    @JsonProperty( "DeptNum")
    private String DeptNum;
    @JsonProperty( "DeptName")
    private String DeptName;

    @JsonIgnore
    public Integer getDeptID() {
        return DeptID;
    }

    public void setDeptID(Integer deptID) {
        DeptID = deptID;
    }
    @JsonIgnore
    public String getDeptNum() {
        return DeptNum;
    }

    public void setDeptNum(String deptNum) {
        DeptNum = deptNum;
    }
    @JsonIgnore
    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

}
