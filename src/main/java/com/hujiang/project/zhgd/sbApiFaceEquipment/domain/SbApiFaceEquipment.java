package com.hujiang.project.zhgd.sbApiFaceEquipment.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 考勤人脸表 sb_api_face_equipment
 * 
 * @author hujiang
 * @date 2019-07-01
 */
public class SbApiFaceEquipment
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 设备编号 */
	private String equipmentId;
	/** in:标识为上班考勤设备，out:标识为下班考勤设备，all:标识为上下班考勤设备（13:00之前为上班考勤） */
	private String inOrOut;
	/** 设备厂家 */
	private String equipmentManufacturer;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setEquipmentId(String equipmentId) 
	{
		this.equipmentId = equipmentId;
	}

	public String getEquipmentId() 
	{
		return equipmentId;
	}
	public void setInOrOut(String inOrOut) 
	{
		this.inOrOut = inOrOut;
	}

	public String getInOrOut() 
	{
		return inOrOut;
	}
	public void setEquipmentManufacturer(String equipmentManufacturer) 
	{
		this.equipmentManufacturer = equipmentManufacturer;
	}

	public String getEquipmentManufacturer() 
	{
		return equipmentManufacturer;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("equipmentId", getEquipmentId())
            .append("inOrOut", getInOrOut())
            .append("equipmentManufacturer", getEquipmentManufacturer())
            .toString();
    }
}
