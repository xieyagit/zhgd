package com.hujiang.project.zhgd.hjghformwork.domain;


import com.hujiang.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 高支模数据记录表 hj_highformwork_data
 * 
 * @author hujiang
 * @date 2019-09-09
 */
public class HighformworkData extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;
	/** 结构件id */
	private Integer structuresId;
	/** 监测因素id */
	private Integer displayId;
	/** 测点id */
	private Integer factorId;
	/** 立杆轴力（kN） */
	private String force;
	/** 支架水平位移（mm） */
	private String displacement;
	/** 模板沉降（mm） */
	private String subside;
	/** 高支模倾斜X（°） */
	private String tiltX;
	/** 高支模倾斜Y（°） */
	private String tiltY;
	/** 预留1 */
	private String reservedO;
	/** 预留2 */
	private String reservedT;
	/** 创建时间 */
	private String creation;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStructuresId() {
		return structuresId;
	}

	public void setStructuresId(Integer structuresId) {
		this.structuresId = structuresId;
	}

	public Integer getDisplayId() {
		return displayId;
	}

	public void setDisplayId(Integer displayId) {
		this.displayId = displayId;
	}

	public Integer getFactorId() {
		return factorId;
	}

	public void setFactorId(Integer factorId) {
		this.factorId = factorId;
	}

	public String getForce() {
		return force;
	}

	public void setForce(String force) {
		this.force = force;
	}

	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

	public String getSubside() {
		return subside;
	}

	public void setSubside(String subside) {
		this.subside = subside;
	}

	public String getTiltX() {
		return tiltX;
	}

	public void setTiltX(String tiltX) {
		this.tiltX = tiltX;
	}

	public String getTiltY() {
		return tiltY;
	}

	public void setTiltY(String tiltY) {
		this.tiltY = tiltY;
	}

	public String getReservedO() {
		return reservedO;
	}

	public void setReservedO(String reservedO) {
		this.reservedO = reservedO;
	}

	public String getReservedT() {
		return reservedT;
	}

	public void setReservedT(String reservedT) {
		this.reservedT = reservedT;
	}

	public String getCreation() {
		return creation;
	}

	public void setCreation(String creation) {
		this.creation = creation;
	}

	@Override
	public String toString() {
		return "HighformworkData{" +
				"id=" + id +
				", structuresId=" + structuresId +
				", displayId=" + displayId +
				", factorId=" + factorId +
				", force='" + force + '\'' +
				", displacement='" + displacement + '\'' +
				", subside='" + subside + '\'' +
				", tiltX='" + tiltX + '\'' +
				", tiltY='" + tiltY + '\'' +
				", reservedO='" + reservedO + '\'' +
				", reservedT='" + reservedT + '\'' +
				", creation=" + creation +
				'}';
	}
}
