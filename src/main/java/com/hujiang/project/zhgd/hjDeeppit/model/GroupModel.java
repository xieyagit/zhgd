package com.hujiang.project.zhgd.hjDeeppit.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *@ClassName GroupsModel
 *@Description TODO
 *@Author xieya
 *@Date 2020/5/10  14:54
 */
public class GroupModel implements Serializable {

    private Integer id;
    private String name;
    private String typeId;
    private List<StationModel> stations;

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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public List<StationModel> getStations() {
        return stations;
    }

    public void setStations(List<StationModel> stations) {
        this.stations = stations;
    }
}