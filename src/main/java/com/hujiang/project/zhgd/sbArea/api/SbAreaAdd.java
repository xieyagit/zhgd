package com.hujiang.project.zhgd.sbArea.api;

import com.hujiang.project.zhgd.sbHire.domain.SbAreaLocaltion;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class SbAreaAdd {
    private String areaName;
    private String areaAddress;
    private Integer constructionId;
    private Double areaXloc;
    private Double areaYloc;
    private Double radius;
    private int way;
    private List<SbAreaLocaltion> array;
    private Integer projectId;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaAddress() {
        return areaAddress;
    }

    public void setAreaAddress(String areaAddress) {
        this.areaAddress = areaAddress;
    }

    public Integer getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Integer constructionId) {
        this.constructionId = constructionId;
    }

    public Double getAreaXloc() {
        return areaXloc;
    }

    public void setAreaXloc(Double areaXloc) {
        this.areaXloc = areaXloc;
    }

    public Double getAreaYloc() {
        return areaYloc;
    }

    public void setAreaYloc(Double areaYloc) {
        this.areaYloc = areaYloc;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public int getWay() {
        return way;
    }

    public void setWay(int way) {
        this.way = way;
    }

    public List<SbAreaLocaltion> getArray() {
        return array;
    }

    public void setArray(List<SbAreaLocaltion> array) {
        this.array = array;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
