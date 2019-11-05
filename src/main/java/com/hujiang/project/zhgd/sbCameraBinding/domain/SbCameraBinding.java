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
	/** 摄像头类型（1危区2安全帽3摄像头） */
	private Integer typeId;
	/** 摄像头客户表id */
	private Integer camearId;
	/** 视频流 */
	private String video;
	/** 项目ID */
	private Integer projectId;
	/** hls */
    private String hls;
    /** hlsHd */
    private String hlsHd;
    /** rtmp */
    private String rtmp;
    /** rtmpHd */
    private String rtmpHd;

    public String getHls() {
        return hls;
    }

    public void setHls(String hls) {
        this.hls = hls;
    }

    public String getHlsHd() {
        return hlsHd;
    }

    public void setHlsHd(String hlsHd) {
        this.hlsHd = hlsHd;
    }

    public String getRtmp() {
        return rtmp;
    }

    public void setRtmp(String rtmp) {
        this.rtmp = rtmp;
    }

    public String getRtmpHd() {
        return rtmpHd;
    }

    public void setRtmpHd(String rtmpHd) {
        this.rtmpHd = rtmpHd;
    }

    public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

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
        return "SbCameraBinding{" +
                "id=" + id +
                ", equipmentSerialNumber='" + equipmentSerialNumber + '\'' +
                ", equipmentType='" + equipmentType + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", addingTime='" + addingTime + '\'' +
                ", deviceStatus=" + deviceStatus +
                ", typeId=" + typeId +
                ", camearId=" + camearId +
                ", video='" + video + '\'' +
                ", projectId=" + projectId +
                ", hls='" + hls + '\'' +
                ", hlsHd='" + hlsHd + '\'' +
                ", rtmp='" + rtmp + '\'' +
                ", rtmpHd='" + rtmpHd + '\'' +
                '}';
    }
}
