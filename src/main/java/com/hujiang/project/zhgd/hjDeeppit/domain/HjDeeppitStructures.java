package com.hujiang.project.zhgd.hjDeeppit.domain;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 *@ClassName HjDeeppitStructures
 *@Description TODO
 *@Author xieya
 *@Date 2020/5/11  13:08
 */
@Data
public class HjDeeppitStructures implements Serializable {

    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String desc;
}