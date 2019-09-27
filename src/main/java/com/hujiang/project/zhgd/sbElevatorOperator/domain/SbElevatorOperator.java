package com.hujiang.project.zhgd.sbElevatorOperator.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 升降机操作记录表 sb_elevator_operator
 * 
 * @author hujiang
 * @date 2019-06-27
 */
public class SbElevatorOperator
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 设备编号 */
	private String deviceNo;
	/** 操作开始时间 */
	private String startTime;
	/** 操作结束时间 */
	private String endTime;
	/** 方向(1 上行,2 下行) */
	private Integer direction;
	/** 最高承重值 */
	private Double maxLoadValue;
	/** 是否发生超载(0.否1.是) */
	private Integer isOverLoad;
	/** 持卡人姓名 */
	private String operatorName;
	/** 是否非持卡人操作(0.否1.是) */
	private Integer isillegalOperation;
	/** 设备编号 */
	private String hxzid;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
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
	public void setStartTime(String startTime) 
	{
		this.startTime = startTime;
	}

	public String getStartTime() 
	{
		return startTime;
	}
	public void setEndTime(String endTime) 
	{
		this.endTime = endTime;
	}

	public String getEndTime() 
	{
		return endTime;
	}
	public void setDirection(Integer direction) 
	{
		this.direction = direction;
	}

	public Integer getDirection() 
	{
		return direction;
	}
	public void setMaxLoadValue(Double maxLoadValue) 
	{
		this.maxLoadValue = maxLoadValue;
	}

	public Double getMaxLoadValue() 
	{
		return maxLoadValue;
	}
	public void setIsOverLoad(Integer isOverLoad) 
	{
		this.isOverLoad = isOverLoad;
	}

	public Integer getIsOverLoad() 
	{
		return isOverLoad;
	}
	public void setOperatorName(String operatorName) 
	{
		this.operatorName = operatorName;
	}

	public String getOperatorName() 
	{
		return operatorName;
	}
	public void setIsillegalOperation(Integer isillegalOperation) 
	{
		this.isillegalOperation = isillegalOperation;
	}

	public Integer getIsillegalOperation() 
	{
		return isillegalOperation;
	}
	public void setHxzid(String hxzid) 
	{
		this.hxzid = hxzid;
	}

	public String getHxzid() 
	{
		return hxzid;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceNo", getDeviceNo())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("direction", getDirection())
            .append("maxLoadValue", getMaxLoadValue())
            .append("isOverLoad", getIsOverLoad())
            .append("operatorName", getOperatorName())
            .append("isillegalOperation", getIsillegalOperation())
            .append("hxzid", getHxzid())
            .toString();
    }
}
