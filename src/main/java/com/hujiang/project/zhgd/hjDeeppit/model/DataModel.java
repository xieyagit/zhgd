package com.hujiang.project.zhgd.hjDeeppit.model;

import lombok.Data;

import java.io.Serializable;

/**
 *@ClassName DataModel
 *@Description 数据model
 *@Author xieya
 *@Date 2020/5/10  16:02
 */
@Data
public class DataModel implements Serializable {
    /**水位*/
    private String waterLevel;
    /**轴力*/
    private String force;
    /**时间*/
    private String time;
}