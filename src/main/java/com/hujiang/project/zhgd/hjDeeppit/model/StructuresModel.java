package com.hujiang.project.zhgd.hjDeeppit.model;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *@ClassName StructuresModel
 *@Description 获取结构物接口返回对象
 *@Author xieya
 *@Date 2020/5/10  13:19
 */
public class StructuresModel implements Serializable {

    /**结构id*/
    private Integer id;
    /**name*/
    private String name;
    /**经度*/
    private Double latitude;
    /**纬度*/
    private Double longitude;
    /**图片url*/
    private String portrait;
    /**描述*/
    private String desc;
    /***/
    private List<TypeModel> typeModelList;

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<TypeModel> getTypeModelList() {
        return typeModelList;
    }

    public void setTypeModelList(List<TypeModel> typeModelList) {
        this.typeModelList = typeModelList;
    }
}