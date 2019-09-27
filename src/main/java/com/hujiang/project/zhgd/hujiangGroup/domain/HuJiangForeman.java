package com.hujiang.project.zhgd.hujiangGroup.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hujiang.framework.web.domain.BaseEntity;

import java.util.Date;

public class HuJiangForeman extends BaseEntity {


    /** 班组名 */
    private String groupName;
    /** 姓名 */
    private String userName;
    private String nation; //民族
    private String teamName; // 区域
    /**时间 */
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createTime;
    private String photoName; // 照片
    private String phone; // 手机

    private Integer isLeader; //是不是班组长
    private String buildCompanyName; //公司

    private String teamId; // 区域

    private Integer worKing;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsLeader() {
        return isLeader;
    }

    public void setIsLeader(Integer isLeader) {
        this.isLeader = isLeader;
    }

    public String getBuildCompanyName() {
        return buildCompanyName;
    }

    public void setBuildCompanyName(String buildCompanyName) {
        this.buildCompanyName = buildCompanyName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public Integer getWorKing() {
        return worKing;
    }

    public void setWorKing(Integer worKing) {
        this.worKing = worKing;
    }
}
