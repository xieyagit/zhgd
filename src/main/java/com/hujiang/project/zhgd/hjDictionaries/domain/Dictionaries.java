package com.hujiang.project.zhgd.hjDictionaries.domain;

public class Dictionaries {
    /** 标识（例：PROJECT_MUNICIPAL） */
    private String tag;
    /** 数据类型名称（例：项目类型） */
    private String groupTitle;
    /** 标题（例：市政、房建） */
    private String title;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Dictionaries{" +
                "tag='" + tag + '\'' +
                ", groupTitle='" + groupTitle + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
