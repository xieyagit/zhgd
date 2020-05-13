package com.hujiang.project.zhgd.hjDeeppit.model;

import lombok.Data;

import java.io.Serializable;

/**
 *@ClassName StationModel
 *@Description TODO
 *@Author xieya
 *@Date 2020/5/10  14:56
 */
@Data
public class StationModel implements Serializable {
    private Integer id;
    private String name;
    private String portrait;
    private Boolean manualData;
}