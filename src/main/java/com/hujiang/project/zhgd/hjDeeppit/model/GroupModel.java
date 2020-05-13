package com.hujiang.project.zhgd.hjDeeppit.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *@ClassName GroupsModel
 *@Description TODO
 *@Author xieya
 *@Date 2020/5/10  14:54
 */
@Data
public class GroupModel implements Serializable {

    private Integer id;
    private String name;
    private String typeId;
    private List<StationModel> stations;
}