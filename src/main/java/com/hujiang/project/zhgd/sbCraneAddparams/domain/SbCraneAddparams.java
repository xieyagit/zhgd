package com.hujiang.project.zhgd.sbCraneAddparams.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 塔式起重机参数表 sb_crane_addparams
 * 
 * @author hujiang
 * @date 2019-06-20
 */
public class SbCraneAddparams
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 设备编号--32位 */
	private String deviceNo;
	/** 设备型号 */
	private String model;
	/** 设备名称 */
	private String name;
	/** 最大幅度（M） */
	private Double tcMaxscope;
	/** 幅度前减速（M) */
	private Double tcSbSpeedcut;
	/** 幅度后减速（M) */
	private Double tcSaSpeedcut;
	/** 最大高度（M) */
	private Double tcMaxheight;
	/** 附着后最大悬臂高度 */
	private Double tcMaxXbHeight;
	/** 最小高度（M) */
	private Double tcMinheight;
	/** 高度上减速(M) */
	private Double tcHuSpeedcut;
	/** 最小幅度（M） */
	private Integer tcMinscope;
	/** 高度下减速（M) */
	private Double tcHbSpeedcut;
	/** 最大载重（T) */
	private Double tcLoadcapacity;
	/** 最大幅度允许最大载重（T) */
	private Integer tcMsLoadcapacity;
	/** 最大载重达到最大幅度（M) */
	private Double tcMlMaxscope;
	/** 塔机倍率（M) */
	private Double tcMultiple;
	/** 塔机类型（0-平臂式，1-动臂式，2-其他） */
	private Integer towerType;
	/** 设备编号 */
	private String hxzid;
	//臂长
	private Double frontArmLength;
	//尾臂长
	private Double backArmLength;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setDeviceNo(String deviceNo) 
	{
		this.deviceNo = deviceNo;
	}

	public String getDeviceNo() 
	{
		return deviceNo;
	}
	public void setModel(String model) 
	{
		this.model = model;
	}

	public String getModel() 
	{
		return model;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setTcMaxscope(Double tcMaxscope) 
	{
		this.tcMaxscope = tcMaxscope;
	}

	public Double getTcMaxscope() 
	{
		return tcMaxscope;
	}
	public void setTcSbSpeedcut(Double tcSbSpeedcut) 
	{
		this.tcSbSpeedcut = tcSbSpeedcut;
	}

	public Double getTcSbSpeedcut() 
	{
		return tcSbSpeedcut;
	}
	public void setTcSaSpeedcut(Double tcSaSpeedcut) 
	{
		this.tcSaSpeedcut = tcSaSpeedcut;
	}

	public Double getTcSaSpeedcut() 
	{
		return tcSaSpeedcut;
	}
	public void setTcMaxheight(Double tcMaxheight) 
	{
		this.tcMaxheight = tcMaxheight;
	}

	public Double getTcMaxheight() 
	{
		return tcMaxheight;
	}
	public void setTcMaxXbHeight(Double tcMaxXbHeight) 
	{
		this.tcMaxXbHeight = tcMaxXbHeight;
	}

	public Double getTcMaxXbHeight() 
	{
		return tcMaxXbHeight;
	}
	public void setTcMinheight(Double tcMinheight) 
	{
		this.tcMinheight = tcMinheight;
	}

	public Double getTcMinheight() 
	{
		return tcMinheight;
	}
	public void setTcHuSpeedcut(Double tcHuSpeedcut) 
	{
		this.tcHuSpeedcut = tcHuSpeedcut;
	}

	public Double getTcHuSpeedcut() 
	{
		return tcHuSpeedcut;
	}
	public void setTcMinscope(Integer tcMinscope) 
	{
		this.tcMinscope = tcMinscope;
	}

	public Integer getTcMinscope() 
	{
		return tcMinscope;
	}
	public void setTcHbSpeedcut(Double tcHbSpeedcut) 
	{
		this.tcHbSpeedcut = tcHbSpeedcut;
	}

	public Double getTcHbSpeedcut() 
	{
		return tcHbSpeedcut;
	}
	public void setTcLoadcapacity(Double tcLoadcapacity)
	{
		this.tcLoadcapacity = tcLoadcapacity;
	}

	public Double getTcLoadcapacity()
	{
		return tcLoadcapacity;
	}
	public void setTcMsLoadcapacity(Integer tcMsLoadcapacity) 
	{
		this.tcMsLoadcapacity = tcMsLoadcapacity;
	}

	public Integer getTcMsLoadcapacity() 
	{
		return tcMsLoadcapacity;
	}
	public void setTcMlMaxscope(Double tcMlMaxscope) 
	{
		this.tcMlMaxscope = tcMlMaxscope;
	}

	public Double getTcMlMaxscope() 
	{
		return tcMlMaxscope;
	}
	public void setTcMultiple(Double tcMultiple) 
	{
		this.tcMultiple = tcMultiple;
	}

	public Double getTcMultiple() 
	{
		return tcMultiple;
	}
	public void setTowerType(Integer towerType) 
	{
		this.towerType = towerType;
	}

	public Integer getTowerType() 
	{
		return towerType;
	}
	public void setHxzid(String hxzid) 
	{
		this.hxzid = hxzid;
	}

	public String getHxzid() 
	{
		return hxzid;
	}

	public Double getFrontArmLength() {
		return frontArmLength;
	}

	public void setFrontArmLength(Double frontArmLength) {
		this.frontArmLength = frontArmLength;
	}

	public Double getBackArmLength() {
		return backArmLength;
	}

	public void setBackArmLength(Double backArmLength) {
		this.backArmLength = backArmLength;
	}

	@Override
	public String toString() {
		return "SbCraneAddparams{" +
				"id=" + id +
				", deviceNo='" + deviceNo + '\'' +
				", model='" + model + '\'' +
				", name='" + name + '\'' +
				", tcMaxscope=" + tcMaxscope +
				", tcSbSpeedcut=" + tcSbSpeedcut +
				", tcSaSpeedcut=" + tcSaSpeedcut +
				", tcMaxheight=" + tcMaxheight +
				", tcMaxXbHeight=" + tcMaxXbHeight +
				", tcMinheight=" + tcMinheight +
				", tcHuSpeedcut=" + tcHuSpeedcut +
				", tcMinscope=" + tcMinscope +
				", tcHbSpeedcut=" + tcHbSpeedcut +
				", tcLoadcapacity=" + tcLoadcapacity +
				", tcMsLoadcapacity=" + tcMsLoadcapacity +
				", tcMlMaxscope=" + tcMlMaxscope +
				", tcMultiple=" + tcMultiple +
				", towerType=" + towerType +
				", hxzid='" + hxzid + '\'' +
				", frontArmLength=" + frontArmLength +
				", backArmLength=" + backArmLength +
				'}';
	}
}
