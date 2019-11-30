package com.hujiang.project.zhgd.sbProjectVideoArea.domain;

public class Video {
    private String videoSn;
    private String isControl;
    private String url;
    private String videoName;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoSn() {
        return videoSn;
    }

    public void setVideoSn(String videoSn) {
        this.videoSn = videoSn;
    }

    public String getIsControl() {
        return isControl;
    }

    public void setIsControl(String isControl) {
        this.isControl = isControl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
