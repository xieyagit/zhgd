package com.hujiang.project.zhgd.hjZhgdVehicle.domain;

public class UploadFile {


    private String service;	// 接口名称	uploadfile
    private String parkid; // 车场ID	20180001
    private String recordid; // 记录ID	118881
    private Integer pic_type;//	图
    private Integer pic_source;// 图片来源	1入场小图，2入场大图，3出场小图，4出场大图，5手动开闸图片
    private String content; // 图片数据	图片文件base64编码


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getParkid() {
        return parkid;
    }

    public void setParkid(String parkid) {
        this.parkid = parkid;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public Integer getPic_type() {
        return pic_type;
    }

    public void setPic_type(Integer pic_type) {
        this.pic_type = pic_type;
    }

    public Integer getPic_source() {
        return pic_source;
    }

    public void setPic_source(Integer pic_source) {
        this.pic_source = pic_source;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
