package com.hujiang.project.zhgd.hjZhgdVehicle.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AreaProject {

    @JsonProperty( "AreaName")
    private String AreaName;
    @JsonProperty( "DeptID")
    private String  DeptID;
    @JsonProperty( "DeptName")
    private String  DeptName;
    @JsonProperty( "Location")
    private String  Location;
    @JsonProperty( "EmpCount")
    private String  EmpCount;
    @JsonProperty( "ZCCount")
    private String  ZCCount;

    @JsonIgnore
    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }
    @JsonIgnore
    public String getDeptID() {
        return DeptID;
    }

    public void setDeptID(String deptID) {
        DeptID = deptID;
    }
    @JsonIgnore
    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }
    @JsonIgnore
    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
    @JsonIgnore
    public String getEmpCount() {
        return EmpCount;
    }

    public void setEmpCount(String empCount) {
        EmpCount = empCount;
    }
    @JsonIgnore
    public String getZCCount() {
        return ZCCount;
    }

    public void setZCCount(String ZCCount) {
        this.ZCCount = ZCCount;
    }

}
