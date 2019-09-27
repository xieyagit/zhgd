package com.hujiang.project.zhgd.hjSafetyArea.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 巡检工区表 hj_safety_area
 * 
 * @author hujiang
 * @date 2019-07-23
 */
public class HjSafetyArea
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private String area;
	private String address;


	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setArea(String area) 
	{
		this.area = area;
	}

	public String getArea() 
	{
		return area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "HjSafetyArea{" +
				"id=" + id +
				", area='" + area + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
