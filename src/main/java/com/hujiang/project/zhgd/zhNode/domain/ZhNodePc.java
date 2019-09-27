package com.hujiang.project.zhgd.zhNode.domain;

import com.hujiang.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 节点计划详情表 zh_node
 * 
 * @author hujiang
 * @date 2019-08-01
 */
public class ZhNodePc {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Excel(name = "节点编号")
	private Integer id;
	/** 节点编号 */
	//@Excel(name="节点编号")
	//private Integer number;
	/** 父节点id(顶级为0) */
	//@Excel(name="父节点id)")
	//private Integer parentId;
	/**
	 * 节点名称
	 */
	@Excel(name = "节点名称")
	private String name;
	/** 状态 0:正常开始 1:未开始 2:延期未开始 3:延期开始 4:延期完成 5:正常完成 */
	//@Excel(name="状态 0:正常开始 1:未开始 2:延期未开始 3:延期开始 4:延期完成 5:正常完成")
	//private Integer state;
	/**
	 * 预计开始时间
	 */
	@Excel(name = "预计开始时间")
	private String predictStart;
	/**
	 * 预计结束时间
	 */
	@Excel(name = "预计结束时间")
	private String predictEnd;
	/**
	 * 实际开始时间
	 */
	@Excel(name = "实际开始时间")
	private String start;
	/**
	 * 实际结束时间
	 */
	@Excel(name = "实际结束时间")
	private String end;
	/**
	 * 进度(%)
	 */
	@Excel(name = "进度(%)")
	private Integer progress;
	/** 详情 */
	//@Excel(name="详情")
	//private String content;
	/** 创建时间 */
	//@Excel(name="创建时间")
	//private Date found;
	/** 创建人id */
	//@Excel(name="创建人id")
	//private Integer creatorId;
	/**
	 * 管控级别
	 */
	@Excel(name = "管控级别")
	private Integer controlRank;
	/**
	 * 负责人
	 */
	@Excel(name = "负责人")
	private Integer principal;
	/** 流水段id */
	//@Excel(name="流水段id")
	//private Integer pipeliningSegment;
	/**
	 * 备注
	 */
	@Excel(name = "备注")
	private String comment;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ZhNodePc{" +
				"id=" + id +
				", name='" + name + '\'' +
				", predictStart='" + predictStart + '\'' +
				", predictEnd='" + predictEnd + '\'' +
				", start='" + start + '\'' +
				", end='" + end + '\'' +
				", progress=" + progress +
				", controlRank=" + controlRank +
				", principal=" + principal +
				", comment='" + comment + '\'' +
				'}';
	}
}