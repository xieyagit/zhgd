package com.hujiang.project.zhgd.sbCameraBinding.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

    
/**
 * 摄像头设备表 sb_camera_binding
 * 
 * @author hujiang
 * @date 2019-10-15
 */
public class SbCameraBinding
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 设备序列号 */
	private String equipmentSerialNumber;
	/** 设备型号 */
	private String equipmentType;
	/** 设备名称 */
	private String equipmentName;
	/** 添加时间 */
	private String addingTime;
	/** 状态（0.正常，1.非正常） */
	private Integer deviceStatus;
	/** 项目ID */
	private Integer projectId;
	/** 摄像头客户表id */
	private Integer camearId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setEquipmentSerialNumber(String equipmentSerialNumber) 
	{
		this.equipmentSerialNumber = equipmentSerialNumber;
	}

	public String getEquipmentSerialNumber() 
	{
		return equipmentSerialNumber;
	}
	public void setEquipmentType(String equipmentType) 
	{
		this.equipmentType = equipmentType;
	}

	public String getEquipmentType() 
	{
		return equipmentType;
	}
	public void setEquipmentName(String equipmentName) 
	{
		this.equipmentName = equipmentName;
	}

	public String getEquipmentName() 
	{
		return equipmentName;
	}
	public void setAddingTime(String addingTime)
	{
		this.addingTime = addingTime;
	}

	public String getAddingTime()
	{
		return addingTime;
	}
	public void setDeviceStatus(Integer deviceStatus) 
	{
		this.deviceStatus = deviceStatus;
	}

	public Integer getDeviceStatus() 
	{
		return deviceStatus;
	}
	public void setProjectId(Integer projectId) 
	{
		this.projectId = projectId;
	}

	public Integer getProjectId() 
	{
		return projectId;
	}
	public void setCamearId(Integer camearId) 
	{
		this.camearId = camearId;
	}

	public Integer getCamearId() 
	{
		return camearId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("equipmentSerialNumber", getEquipmentSerialNumber())
            .append("equipmentType", getEquipmentType())
            .append("equipmentName", getEquipmentName())
            .append("addingTime", getAddingTime())
            .append("deviceStatus", getDeviceStatus())
            .append("projectId", getProjectId())
            .append("camearId", getCamearId())
            .toString();
    }
}
