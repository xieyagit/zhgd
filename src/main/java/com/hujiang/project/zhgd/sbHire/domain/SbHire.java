package com.hujiang.project.zhgd.sbHire.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hujiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 设备人员表 sb_hire
 * 
 * @author hujiang
 * @date 2019-07-04
 */
public class SbHire
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private String imei;
	/**  */
	private Integer peopleId;
	/**  */
	private Integer areaId;

	private String idCode;

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setImei(String imei) 
	{
		this.imei = imei;
	}

	public String getImei() 
	{
		return imei;
	}
	public void setPeopleId(Integer peopleId) 
	{
		this.peopleId = peopleId;
	}

	public Integer getPeopleId() 
	{
		return peopleId;
	}
	public void setAreaId(Integer areaId) 
	{
		this.areaId = areaId;
	}

	public Integer getAreaId() 
	{
		return areaId;
	}

	@Override
	public String toString() {
		return "SbHire{" +
				"id=" + id +
				", imei='" + imei + '\'' +
				", peopleId=" + peopleId +
				", areaId=" + areaId +
				", idCode='" + idCode + '\'' +
				'}';
	}
}
