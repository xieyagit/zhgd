package com.hujiang.project.zhgd.sbCurrentTemperature.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 温度及漏电流记录表 sb_current_temperature
 * 
 * @author hujiang
 * @date 2019-06-24
 */
public class SbCurrentTemperature
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 配电箱编号 */
	private String electricityBoxId;
	/** 漏电流 */
	private BigDecimal current;
	/** 环境温度 */
	private BigDecimal envirwarm;
	/** A相温度(电缆) */
	private BigDecimal awarm;
	/** B相温度 */
	private BigDecimal bwarm;
	/** C相温度 */
	private BigDecimal cwarm;
	/** N相温度 */
	private BigDecimal nwarm;
	/** 数据上传时间 */
	private String tm;
	private String doorType;//门状态

	private Integer wranType;
	private String jdbh;
	private Integer projectId;

	public String getJdbh() {
		return jdbh;
	}

	public void setJdbh(String jdbh) {
		this.jdbh = jdbh;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getWranType() {
		return wranType;
	}

	public void setWranType(Integer wranType) {
		this.wranType = wranType;
	}

	public String getDoorType() {
		return doorType;
	}

	public void setDoorType(String doorType) {
		this.doorType = doorType;
	}

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
	public void setCurrent(BigDecimal current) 
	{
		this.current = current;
	}

	public BigDecimal getCurrent() 
	{
		return current;
	}
	public void setEnvirwarm(BigDecimal envirwarm) 
	{
		this.envirwarm = envirwarm;
	}

	public BigDecimal getEnvirwarm() 
	{
		return envirwarm;
	}
	public void setAwarm(BigDecimal awarm) 
	{
		this.awarm = awarm;
	}

	public BigDecimal getAwarm() 
	{
		return awarm;
	}
	public void setBwarm(BigDecimal bwarm) 
	{
		this.bwarm = bwarm;
	}

	public BigDecimal getBwarm() 
	{
		return bwarm;
	}
	public void setCwarm(BigDecimal cwarm) 
	{
		this.cwarm = cwarm;
	}

	public BigDecimal getCwarm() 
	{
		return cwarm;
	}
	public void setNwarm(BigDecimal nwarm) 
	{
		this.nwarm = nwarm;
	}

	public BigDecimal getNwarm() 
	{
		return nwarm;
	}
	public void setTm(String tm) 
	{
		this.tm = tm;
	}

	public String getTm() 
	{
		return tm;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("electricityBoxId", getElectricityBoxId())
            .append("current", getCurrent())
            .append("envirwarm", getEnvirwarm())
            .append("awarm", getAwarm())
            .append("bwarm", getBwarm())
            .append("cwarm", getCwarm())
            .append("nwarm", getNwarm())
            .append("tm", getTm())
            .append("doorType", getDoorType())
            .toString();
    }
}
