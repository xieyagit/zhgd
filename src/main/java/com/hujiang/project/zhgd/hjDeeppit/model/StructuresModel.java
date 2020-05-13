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
@Data
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
}