package com.hujiang.project.zhgd.vehicleEquipment.domain;

import java.time.LocalDateTime;

/**
 * 车牌设备表 hujiang_vehicle_equipment
 * 
 * @author hujiang
 * @date 2019-05-07
 */
public class  VehicleEquipment
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 出口地址 */
	private Integer address;
	/** 摄像头IP */
	private String addressIP;
	/** 辅助摄像头IP */
	private String assistAddressIP;
	/** 摄像头唯一标识 */
	private String cameraMac;
	/** 电脑IP */
	private String computerIp;
	/** 自增ID */
	private Integer devID;
	/** 设备名称 */
	private String devName;
	/** 摄像头类型 1-地面库 2-地库 */
	private Integer devType;
	/** GUid */
	private String gUID;
	/** 进出标识 1-进 2-出 */
	private Integer inOROut;
	/** 项目id（智慧工地的项目id） */
	private Integer deptID;
	/** 车位剩余数 */
	private Integer pkCount;

	private LocalDateTime writeInTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setAddress(Integer address) 
	{
		this.address = address;
	}

	public Integer getAddress() 
	{
		return address;
	}
	public void setAddressIP(String addressIP) 
	{
		this.addressIP = addressIP;
	}

	public String getAddressIP() 
	{
		return addressIP;
	}
	public void setAssistAddressIP(String assistAddressIP) 
	{
		this.assistAddressIP = assistAddressIP;
	}

	public String getAssistAddressIP() 
	{
		return assistAddressIP;
	}
	public void setCameraMac(String cameraMac) 
	{
		this.cameraMac = cameraMac;
	}

	public String getCameraMac() 
	{
		return cameraMac;
	}
	public void setComputerIp(String computerIp) 
	{
		this.computerIp = computerIp;
	}

	public String getComputerIp() 
	{
		return computerIp;
	}
	public void setDevID(Integer devID) 
	{
		this.devID = devID;
	}

	public Integer getDevID() 
	{
		return devID;
	}
	public void setDevName(String devName) 
	{
		this.devName = devName;
	}

	public String getDevName() 
	{
		return devName;
	}
	public void setDevType(Integer devType) 
	{
		this.devType = devType;
	}

	public Integer getDevType() 
	{
		return devType;
	}
	public void setGUID(String gUID) 
	{
		this.gUID = gUID;
	}

	public String getGUID() 
	{
		return gUID;
	}
	public void setInOROut(Integer inOROut) 
	{
		this.inOROut = inOROut;
	}

	public Integer getInOROut() 
	{
		return inOROut;
	}
	public void setDeptID(Integer deptID) 
	{
		this.deptID = deptID;
	}

	public Integer getDeptID() 
	{
		return deptID;
	}
	public void setPkCount(Integer pkCount) 
	{
		this.pkCount = pkCount;
	}

	public Integer getPkCount() 
	{
		return pkCount;
	}

	public LocalDateTime getWriteInTime() {
		return writeInTime;
	}

	public void setWriteInTime(LocalDateTime writeInTime) {
		this.writeInTime = writeInTime;
	}

	@Override
	public String toString() {
		return "VehicleEquipment{" +
				"id=" + id +
				", address=" + address +
				", addressIP='" + addressIP + '\'' +
				", assistAddressIP='" + assistAddressIP + '\'' +
				", cameraMac='" + cameraMac + '\'' +
				", computerIp='" + computerIp + '\'' +
				", devID=" + devID +
				", devName='" + devName + '\'' +
				", devType=" + devType +
				", gUID='" + gUID + '\'' +
				", inOROut=" + inOROut +
				", deptID=" + deptID +
				", pkCount=" + pkCount +
				", writeInTime=" + writeInTime +
				'}';
	}
}
