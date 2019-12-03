package com.hujiang.project.zhgd.zhNode.domain;

import com.hujiang.common.utils.DateUtils;
import com.hujiang.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;


import java.util.Date;


/**
 * 节点计划详情表 zh_node
 *
 * @author hujiang
 * @date 2019-08-01
 */
@ApiModel(value = "节点计划模型")
public class ZhNode {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 是否关键节点
     */
    private boolean crux;
    /**
     * 节点编号
     */
    private Integer number;
    /**
     * 父节点id(顶级为0)
     */
    private Integer parentId;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 状态 0:开始 1:未开始 2:已完成
     */
    private Integer state;
    /**
     * 预计开始时间
     */
    private String predictStart;
    /**
     * 预计结束时间
     */
    private String predictEnd;
    /**
     * 实际开始时间
     */
    private String start;
    /**
     * 实际结束时间
     */
    private String end;
    /**
     * 进度(%)
     */
    private Integer progress;
    /**
     * 详情
     */
    private String content;
    /**
     * 创建时间
     */
    private String found;
    /**
     * 创建人id
     */
    private Integer creatorId;
    /**
     * 管控级别
     */
    private Integer controlRank;
    /**
     * 负责人
     */
    private Integer principal;
    /**
     * 流水段id
     */
    private Integer pipeliningSegment;
    /**
     * 备注
     */
    private String comment;

    /**
     * 状态 0:正常开始 1:未开始 2:延期未开始 3:延期开始 4:延期完成 5:正常完成 6:提前开始 7：提前完成
     */
    private Integer status;
    /**
     * 能否在计划中导入
     */
    private boolean addAble;

    private boolean hasNode;
    /**
     * 是否前置节点
     */
    private boolean prepose;
    /**
     * 是否可以作为父级节点
     */
    private boolean befather;

    public boolean isBefather() {
        return befather;
    }

    public void setBefather(boolean befather) {
        this.befather = befather;
    }

    public boolean isPrepose() {
        return prepose;
    }

    public void setPrepose(boolean prepose) {
        this.prepose = prepose;
    }

    public boolean isHasNode() {
        return hasNode;
    }

    public void setHasNode(boolean hasNode) {
        this.hasNode = hasNode;
    }

    public boolean isAddAble() {
        return addAble;
    }

    public void setAddAble(boolean addAble) {
        this.addAble = addAble;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        Date date = new Date();
        if (state != null) {
            switch (state) {
                case 0:
                    if (!StringUtils.isEmpty(predictStart)&&!StringUtils.isEmpty(start)) {
                        if (DateUtils.getDate(predictStart).getTime() == DateUtils.getDate(start).getTime()) {
                            status = 0;
                        } else if (DateUtils.getDate(predictStart).getTime() > DateUtils.getDate(start).getTime()) {
                            status = 6;
                        } else if (DateUtils.getDate(predictStart).getTime() < DateUtils.getDate(start).getTime()) {
                            status = 3;
                        }
                        return status;
                    }
                    break;
                case 1:
                    if (!StringUtils.isEmpty(predictStart)) {
                        if (DateUtils.getDate(predictStart).getTime() > date.getTime()) {
                            status = 1;
                        } else if (DateUtils.getDate(predictStart).getTime() < date.getTime()) {
                            status = 2;
                        }
                        return status;
                    }
                    break;
                case 2:
                    if (!StringUtils.isEmpty(predictEnd)&&!StringUtils.isEmpty(end)){
                        if (DateUtils.getDate(predictEnd).getTime() == DateUtils.getDate(end).getTime()) {
                            status = 5;
                        } else if (DateUtils.getDate(predictEnd).getTime() > DateUtils.getDate(end).getTime()) {
                            status = 7;
                        } else if (DateUtils.getDate(predictEnd).getTime() < DateUtils.getDate(end).getTime()) {
                            status = 4;
                        }
                        return status;
                    }
                    break;
            }
        }
        return status;
    }


    public boolean isCrux() {
        return crux;
    }

    public void setCrux(boolean crux) {
        this.crux = crux;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {

        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPredictStart() {
        return predictStart;
    }

    public void setPredictStart(String predictStart) {
        this.predictStart = predictStart;
    }

    public String getPredictEnd() {
        return predictEnd;
    }

    public void setPredictEnd(String predictEnd) {
        this.predictEnd = predictEnd;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFound() {
        return found;
    }

    public void setFound(String found) {
        this.found = found;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getControlRank() {
        return controlRank;
    }

    public void setControlRank(Integer controlRank) {
        this.controlRank = controlRank;
    }

    public Integer getPrincipal() {
        return principal;
    }

    public void setPrincipal(Integer principal) {
        this.principal = principal;
    }

    public Integer getPipeliningSegment() {
        return pipeliningSegment;
    }

    public void setPipeliningSegment(Integer pipeliningSegment) {
        this.pipeliningSegment = pipeliningSegment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ZhNode{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", crux=" + crux +
                ", number=" + number +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", predictStart='" + predictStart + '\'' +
                ", predictEnd='" + predictEnd + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", progress=" + progress +
                ", content='" + content + '\'' +
                ", found='" + found + '\'' +
                ", creatorId=" + creatorId +
                ", controlRank=" + controlRank +
                ", principal=" + principal +
                ", pipeliningSegment=" + pipeliningSegment +
                ", comment='" + comment + '\'' +
                '}';
    }
}
