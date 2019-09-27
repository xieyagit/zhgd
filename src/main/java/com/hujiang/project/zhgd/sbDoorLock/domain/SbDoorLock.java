package com.hujiang.project.zhgd.sbDoorLock.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 门锁记录表 sb_door_lock
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public class SbDoorLock
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 配电箱编号 */
	private String electricityBoxId;
	/** 锁状态 */
	private String lockType;
	/** 门状态 */
	private String doorType;
	/** 数据更新时间 */
	private String handleTime;
	/** 开门时间 */
	private String opendoorTime;
	/** 关门时间 */
	private String closedoorTime;
	/** 开锁时间 */
	private String openlockTime;
	/** 关锁时间 */
	private String closelockTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setElectricityBoxId(String electricityBoxId) 
	{
		this.electricityBoxId = electricityBoxId;
	}

	public String getElectricityBoxId() 
	{
		return electricityBoxId;
	}
	public void setLockType(String lockType) 
	{
		this.lockType = lockType;
	}

	public String getLockType() 
	{
		return lockType;
	}
	public void setDoorType(String doorType) 
	{
		this.doorType = doorType;
	}

	public String getDoorType() 
	{
		return doorType;
	}
	public void setHandleTime(String handleTime) 
	{
		this.handleTime = handleTime;
	}

	public String getHandleTime() 
	{
		return handleTime;
	}
	public void setOpendoorTime(String opendoorTime) 
	{
		this.opendoorTime = opendoorTime;
	}

	public String getOpendoorTime() 
	{
		return opendoorTime;
	}
	public void setClosedoorTime(String closedoorTime) 
	{
		this.closedoorTime = closedoorTime;
	}

	public String getClosedoorTime() 
	{
		return closedoorTime;
	}
	public void setOpenlockTime(String openlockTime) 
	{
		this.openlockTime = openlockTime;
	}

	public String getOpenlockTime() 
	{
		return openlockTime;
	}
	public void setCloselockTime(String closelockTime) 
	{
		this.closelockTime = closelockTime;
	}

	public String getCloselockTime() 
	{
		return closelockTime;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("electricityBoxId", getElectricityBoxId())
            .append("lockType", getLockType())
            .append("doorType", getDoorType())
            .append("handleTime", getHandleTime())
            .append("opendoorTime", getOpendoorTime())
            .append("closedoorTime", getClosedoorTime())
            .append("openlockTime", getOpenlockTime())
            .append("closelockTime", getCloselockTime())
            .toString();
    }
}
