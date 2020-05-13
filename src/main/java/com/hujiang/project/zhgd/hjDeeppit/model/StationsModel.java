package com.hujiang.project.zhgd.hjDeeppit.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *@ClassName StationsModel
 *@Description TODO
 *@Author xieya
 *@Date 2020/5/10  16:01
 */
@Data
public class StationsModel implements Serializable {

    private Integer id;
    private String name;
    private List<DataModel> data;

}