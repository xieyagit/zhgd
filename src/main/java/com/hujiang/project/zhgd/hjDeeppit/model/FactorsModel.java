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
@Data
public class FactorsModel implements Serializable {

    private Integer id;
    private String name;
    private String org;
    private List<ItemsModel> items;
}