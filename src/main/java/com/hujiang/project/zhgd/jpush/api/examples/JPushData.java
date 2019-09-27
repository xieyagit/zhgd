package com.hujiang.project.zhgd.jpush.api.examples;

import java.util.List;

public class JPushData {
    private String title;
    private String content;
    private String tag;
    private List<String> alias;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
