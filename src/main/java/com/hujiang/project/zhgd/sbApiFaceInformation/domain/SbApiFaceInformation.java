package com.hujiang.project.zhgd.sbApiFaceInformation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 考勤人脸表 sb_api_face_information
 * 
 * @author hujiang
 * @date 2019-07-01
 */
public class SbApiFaceInformation
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 第三方人员ID */
	private String personId;
	/** 识别人脸url */
	private String facialImage;
	/** 设备编号 */
	private String equipmentId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setPersonId(String personId) 
	{
		this.personId = personId;
	}

	public String getPersonId() 
	{
		return personId;
	}
	public void setFacialImage(String facialImage) 
	{
		this.facialImage = facialImage;
	}

	public String getFacialImage() 
	{
		return facialImage;
	}
	public void setEquipmentId(String equipmentId) 
	{
		this.equipmentId = equipmentId;
	}

	public String getEquipmentId() 
	{
		return equipmentId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("personId", getPersonId())
            .append("facialImage", getFacialImage())
            .append("equipmentId", getEquipmentId())
            .toString();
    }
}
