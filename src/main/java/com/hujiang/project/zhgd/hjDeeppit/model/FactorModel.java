package com.hujiang.project.zhgd.hjDeeppit.model;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *@ClassName FactorModel
 *@Description TODO
 *@Author xieya
 *@Date 2020/5/10  14:53
 */
@Data
public class FactorModel implements Serializable {

    private Integer factorId;
    private String factorName;
    private String factorProto;
    private List<GroupModel> groups;
}