package com.hujiang.project.zhgd.hujiangGroup.domain;


import com.hujiang.framework.web.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;


public class UtilArea extends BaseEntity {

    private static final long serialVersionUID = 1L;
	
	private Long id;

    private String title;

    private String type;

    private Boolean hotCity;

    private Integer orderIndex;

    private Long parentId;

    /** 子菜单 */
    private List<UtilArea> children = new ArrayList<UtilArea>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getHotCity() {
        return hotCity;
    }

    public void setHotCity(Boolean hotCity) {
        this.hotCity = hotCity;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<UtilArea> getChildren() {
        return children;
    }

    public void setChildren(List<UtilArea> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "UtilArea{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", hotCity=" + hotCity +
                ", orderIndex=" + orderIndex +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
