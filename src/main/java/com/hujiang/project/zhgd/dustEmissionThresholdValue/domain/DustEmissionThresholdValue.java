package com.hujiang.project.zhgd.dustEmissionThresholdValue.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 项目扬尘设备阈值表 dust_emission_threshold_value
 * 
 * @author hujiang
 * @date 2019-07-08
 */
public class DustEmissionThresholdValue
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 项目id */
	private Integer projectId;
	/** pm25 */
	private Integer pm25;
	/** pm10 */
	private Integer pm10;
	/** tsp */
	private Integer tsp;
	/** 噪声 */
	private Integer noise;
	/** 温度 */
	private Integer temperature;
	/** 湿度 */
	private Integer humidity;
	/** 风速 */
	private Integer windSpeed;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	public void setPm25(Integer pm25) 
	{
		this.pm25 = pm25;
	}

	public Integer getPm25() 
	{
		return pm25;
	}
	public void setPm10(Integer pm10) 
	{
		this.pm10 = pm10;
	}

	public Integer getPm10() 
	{
		return pm10;
	}
	public void setTsp(Integer tsp) 
	{
		this.tsp = tsp;
	}

	public Integer getTsp() 
	{
		return tsp;
	}
	public void setNoise(Integer noise) 
	{
		this.noise = noise;
	}

	public Integer getNoise() 
	{
		return noise;
	}
	public void setTemperature(Integer temperature) 
	{
		this.temperature = temperature;
	}

	public Integer getTemperature() 
	{
		return temperature;
	}
	public void setHumidity(Integer humidity) 
	{
		this.humidity = humidity;
	}

	public Integer getHumidity() 
	{
		return humidity;
	}
	public void setWindSpeed(Integer windSpeed) 
	{
		this.windSpeed = windSpeed;
	}

	public Integer getWindSpeed() 
	{
		return windSpeed;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("pm25", getPm25())
            .append("pm10", getPm10())
            .append("tsp", getTsp())
            .append("noise", getNoise())
            .append("temperature", getTemperature())
            .append("humidity", getHumidity())
            .append("windSpeed", getWindSpeed())
            .toString();
    }
}
