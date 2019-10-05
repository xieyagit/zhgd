package com.hujiang.project.zhgd.sbElevatorAddparams.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 升降机参数  表 sb_elevator_addparams
 * 
 * @author hujiang
 * @date 2019-06-27
 */
public class SbElevatorAddparams
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 设备编号 */
	private String deviceNo;
	/** 设备型号 */
	private String model;
	/** 设备名称 */
	private String name;
	/** 最大载重（T) */
	private Double lLoadCapacity;
	/** 最大高度(M) */
	private Double lHeight;
	/** 最大自由高度(M) */
	private Double lHeight2;
	/** 向下采集点输入方式 */
	private Integer lUpcollect;
	/** 向上采集点输入方式 */
	private Integer lDowncollection;
	/** 齿轮模数(0-20) */
	private Integer lGearmodules;
	/** 下限位输入方式 */
	private Integer lLowlimit;
	/** 主接触器输出方式 */
	private Integer lMContract;
	/** 副接触器输出方式 */
	private Integer lSContract;
	/** 监控控制方式 */
	private Integer lMonitorstyle;
	/** 升节节数 */
	private Integer lSections;
	/** 升降机控制方式 */
	private Integer lControlstyle;
	/** 分时限载载重（T) */
	private Double lLimitCapacity;
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
	public void setLLoadCapacity(Double lLoadCapacity) 
	{
		this.lLoadCapacity = lLoadCapacity;
	}

	public Double getLLoadCapacity() 
	{
		return lLoadCapacity;
	}
	public void setLHeight(Double lHeight) 
	{
		this.lHeight = lHeight;
	}

	public Double getLHeight() 
	{
		return lHeight;
	}
	public void setLHeight2(Double lHeight2) 
	{
		this.lHeight2 = lHeight2;
	}

	public Double getLHeight2() 
	{
		return lHeight2;
	}
	public void setLUpcollect(Integer lUpcollect) 
	{
		this.lUpcollect = lUpcollect;
	}

	public Integer getLUpcollect() 
	{
		return lUpcollect;
	}
	public void setLDowncollection(Integer lDowncollection) 
	{
		this.lDowncollection = lDowncollection;
	}

	public Integer getLDowncollection() 
	{
		return lDowncollection;
	}
	public void setLGearmodules(Integer lGearmodules) 
	{
		this.lGearmodules = lGearmodules;
	}

	public Integer getLGearmodules() 
	{
		return lGearmodules;
	}
	public void setLLowlimit(Integer lLowlimit) 
	{
		this.lLowlimit = lLowlimit;
	}

	public Integer getLLowlimit() 
	{
		return lLowlimit;
	}
	public void setLMContract(Integer lMContract) 
	{
		this.lMContract = lMContract;
	}

	public Integer getLMContract() 
	{
		return lMContract;
	}
	public void setLSContract(Integer lSContract) 
	{
		this.lSContract = lSContract;
	}

	public Integer getLSContract() 
	{
		return lSContract;
	}
	public void setLMonitorstyle(Integer lMonitorstyle) 
	{
		this.lMonitorstyle = lMonitorstyle;
	}

	public Integer getLMonitorstyle() 
	{
		return lMonitorstyle;
	}
	public void setLSections(Integer lSections) 
	{
		this.lSections = lSections;
	}

	public Integer getLSections() 
	{
		return lSections;
	}
	public void setLControlstyle(Integer lControlstyle) 
	{
		this.lControlstyle = lControlstyle;
	}

	public Integer getLControlstyle() 
	{
		return lControlstyle;
	}
	public void setLLimitCapacity(Double lLimitCapacity) 
	{
		this.lLimitCapacity = lLimitCapacity;
	}

	public Double getLLimitCapacity() 
	{
		return lLimitCapacity;
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
            .append("model", getModel())
            .append("name", getName())
            .append("lLoadCapacity", getLLoadCapacity())
            .append("lHeight", getLHeight())
            .append("lHeight2", getLHeight2())
            .append("lUpcollect", getLUpcollect())
            .append("lDowncollection", getLDowncollection())
            .append("lGearmodules", getLGearmodules())
            .append("lLowlimit", getLLowlimit())
            .append("lMContract", getLMContract())
            .append("lSContract", getLSContract())
            .append("lMonitorstyle", getLMonitorstyle())
            .append("lSections", getLSections())
            .append("lControlstyle", getLControlstyle())
            .append("lLimitCapacity", getLLimitCapacity())
            .append("hxzid", getHxzid())
            .toString();
    }
}
