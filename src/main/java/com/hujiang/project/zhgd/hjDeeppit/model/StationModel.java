package com.hujiang.project.zhgd.hjDeeppit.model;

import lombok.Data;

import java.io.Serializable;

/**
 *@ClassName StationModel
 *@Description TODO
 *@Author xieya
 *@Date 2020/5/10  14:56
 */
public class StationModel implements Serializable {
    private Integer id;
    private String name;
    private String portrait;
    private Boolean manualData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Boolean getManualData() {
        return manualData;
    }

    public void setManualData(Boolean manualData) {
        this.manualData = manualData;
    }
}