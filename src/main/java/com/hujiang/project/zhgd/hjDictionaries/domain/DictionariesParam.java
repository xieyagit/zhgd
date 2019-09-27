package com.hujiang.project.zhgd.hjDictionaries.domain;

public class DictionariesParam {


    /** ID */
    private Integer id;
    /** 标识（例：PROJECT_MUNICIPAL） */
    private String tag;
    /** 数据类型名称（例：项目类型） */
    private String title;
    /** 类别（例：PROJECT_TYPE） */
    private String category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "DictionariesParam{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
