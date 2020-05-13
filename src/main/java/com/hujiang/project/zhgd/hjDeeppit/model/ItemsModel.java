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
@Data
public class ItemsModel implements Serializable {
    private Integer id;
    private String name;
    private String fieldName;
    private String unit;
}