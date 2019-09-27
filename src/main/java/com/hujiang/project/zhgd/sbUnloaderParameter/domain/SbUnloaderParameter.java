package com.hujiang.project.zhgd.sbUnloaderParameter.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 卸料基础参数表 sb_unloader_parameter
 * 
 * @author hujiang
 * @date 2019-09-10
 */
public class SbUnloaderParameter
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 黑匣子编号32位*/
	private String deviceNo;
	/** 黑匣子编号 */
	private String hxzId;
	/** 升降机类型0:移动式1:落地式2:悬挑式 */
	private String unloadingPlatformType;
	/** 配置载重检测功能0:未配置1:已配置 */
	private String weightSet;
	/** 配置倾角X检测功能0:未配置1:已配置 */
	private String obliguityXSet;
	/** 配置倾角Y检测功能0:未配置1:已配置 */
	private String obliguityYSet;
	/** 配置GPS定位功能0:未配置1:已配置 */
	private String gpsSet;
	/** 载重预警阈值0.00~2.00t */
	private Float weightPreAlarmValue;
	/** 倾角X预警阈值0.00~9.99° */
	private Float obliguityXPreAlarmValue;
	/** 倾角Y预警阈值0.00~9.99° */
	private Float obliguityYPreAlarmValue;
	/** 电池电量预警阈值%0-100 */
	private Float batteryPreAlarmValue;
	/** 载重报警阈值0.00~2.00t */
	private Float weightAlarmValue;
	/** 倾角X报警阈值0.00~9.99° */
	private Float obliguityXAlarmValue;
	/** 倾角Y报警阈值0.00~9.99° */
	private Float obliguityYAlarmValue;
	/** 电池电量报警阈值%0-100 */
	private Float batteryAlarmValue;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public void setHxzId(String hxzId)
	{
		this.hxzId = hxzId;
	}

	public String getHxzId() 
	{
		return hxzId;
	}
	public void setUnloadingPlatformType(String unloadingPlatformType) 
	{
		this.unloadingPlatformType = unloadingPlatformType;
	}

	public String getUnloadingPlatformType() 
	{
		return unloadingPlatformType;
	}
	public void setWeightSet(String weightSet) 
	{
		this.weightSet = weightSet;
	}

	public String getWeightSet() 
	{
		return weightSet;
	}
	public void setObliguityXSet(String obliguityXSet) 
	{
		this.obliguityXSet = obliguityXSet;
	}

	public String getObliguityXSet() 
	{
		return obliguityXSet;
	}
	public void setObliguityYSet(String obliguityYSet) 
	{
		this.obliguityYSet = obliguityYSet;
	}

	public String getObliguityYSet() 
	{
		return obliguityYSet;
	}
	public void setGpsSet(String gpsSet) 
	{
		this.gpsSet = gpsSet;
	}

	public String getGpsSet() 
	{
		return gpsSet;
	}
	public void setWeightPreAlarmValue(Float weightPreAlarmValue) 
	{
		this.weightPreAlarmValue = weightPreAlarmValue;
	}

	public Float getWeightPreAlarmValue() 
	{
		return weightPreAlarmValue;
	}
	public void setObliguityXPreAlarmValue(Float obliguityXPreAlarmValue) 
	{
		this.obliguityXPreAlarmValue = obliguityXPreAlarmValue;
	}

	public Float getObliguityXPreAlarmValue() 
	{
		return obliguityXPreAlarmValue;
	}
	public void setObliguityYPreAlarmValue(Float obliguityYPreAlarmValue) 
	{
		this.obliguityYPreAlarmValue = obliguityYPreAlarmValue;
	}

	public Float getObliguityYPreAlarmValue() 
	{
		return obliguityYPreAlarmValue;
	}
	public void setBatteryPreAlarmValue(Float batteryPreAlarmValue) 
	{
		this.batteryPreAlarmValue = batteryPreAlarmValue;
	}

	public Float getBatteryPreAlarmValue() 
	{
		return batteryPreAlarmValue;
	}
	public void setWeightAlarmValue(Float weightAlarmValue) 
	{
		this.weightAlarmValue = weightAlarmValue;
	}

	public Float getWeightAlarmValue() 
	{
		return weightAlarmValue;
	}
	public void setObliguityXAlarmValue(Float obliguityXAlarmValue) 
	{
		this.obliguityXAlarmValue = obliguityXAlarmValue;
	}

	public Float getObliguityXAlarmValue() 
	{
		return obliguityXAlarmValue;
	}
	public void setObliguityYAlarmValue(Float obliguityYAlarmValue) 
	{
		this.obliguityYAlarmValue = obliguityYAlarmValue;
	}

	public Float getObliguityYAlarmValue() 
	{
		return obliguityYAlarmValue;
	}
	public void setBatteryAlarmValue(Float batteryAlarmValue) 
	{
		this.batteryAlarmValue = batteryAlarmValue;
	}

	public Float getBatteryAlarmValue() 
	{
		return batteryAlarmValue;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceNo", getDeviceNo())
            .append("hxzId", getHxzId())
            .append("unloadingPlatformType", getUnloadingPlatformType())
            .append("weightSet", getWeightSet())
            .append("obliguityXSet", getObliguityXSet())
            .append("obliguityYSet", getObliguityYSet())
            .append("gpsSet", getGpsSet())
            .append("weightPreAlarmValue", getWeightPreAlarmValue())
            .append("obliguityXPreAlarmValue", getObliguityXPreAlarmValue())
            .append("obliguityYPreAlarmValue", getObliguityYPreAlarmValue())
            .append("batteryPreAlarmValue", getBatteryPreAlarmValue())
            .append("weightAlarmValue", getWeightAlarmValue())
            .append("obliguityXAlarmValue", getObliguityXAlarmValue())
            .append("obliguityYAlarmValue", getObliguityYAlarmValue())
            .append("batteryAlarmValue", getBatteryAlarmValue())
            .toString();
    }
}
