package com.hujiang.project.zhgd.zhNode.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 进度计划表 zh_progress_plan
 * 
 * @author hujiang
 * @date 2019-08-02
 */
public class ZhProgressPlan
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 计划名称 */
	private String name;
	/** 状态 0:正常开始 1:未开始 2:延期未开始 3:延期开始 4:延期完成 5:正常完成 */
	private Integer state;
	/** 预计开始 */
	private String predictStart;
	/** 预计结束 */
	private String predictEnd;
	/** 实际开始 */
	private String start;
	/** 实际结束 */
	private String end;
	/** 创建人id */
	private Integer creatorId;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	@Override
	public String toString() {
		return "ZhProgressPlan{" +
				"id=" + id +
				", name='" + name + '\'' +
				", state=" + state +
				", predictStart='" + predictStart + '\'' +
				", predictEnd='" + predictEnd + '\'' +
				", start='" + start + '\'' +
				", end='" + end + '\'' +
				", creatorId=" + creatorId +
				'}';
	}
}
