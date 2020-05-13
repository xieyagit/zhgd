package com.hujiang.project.zhgd.hjDeeppit.model;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 *@ClassName ItemsModel
 *@Description TODO
 *@Author xieya
 *@Date 2020/5/10  14:11
 */
public class ItemsModel implements Serializable {
    private Integer id;
    private String name;
    private String fieldName;
    private String unit;

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

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}