package com.hujiang.project.zhgd.hjDeeppit.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *@ClassName FactorsModel
 *@Description 结构物下监测因素返回对象
 *@Author xieya
 *@Date 2020/5/10  14:09
 */
public class FactorsModel implements Serializable {

    private Integer id;
    private String name;
    private String org;
    private List<ItemsModel> items;

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

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public List<ItemsModel> getItems() {
        return items;
    }

    public void setItems(List<ItemsModel> items) {
        this.items = items;
    }
}