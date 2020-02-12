package com.hujiang.project.zhgd.sbProjectVideoArea.domain;

import com.hujiang.project.zhgd.sbProjectVideo.domain.SbProjectVideo;

import java.util.List;

/**
 *
 */
public class VideoPicUrl {

    private Integer pid;

    private List<SbProjectVideo> videoList;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public List<SbProjectVideo> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<SbProjectVideo> videoList) {
        this.videoList = videoList;
    }
}
