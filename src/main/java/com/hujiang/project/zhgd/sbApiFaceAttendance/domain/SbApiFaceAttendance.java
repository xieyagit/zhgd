package com.hujiang.project.zhgd.sbApiFaceAttendance.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 人脸摄像机考勤记录表 sb_api_face_attendance
 * 
 * @author hujiang
 * @date 2019-07-01
 */
public class SbApiFaceAttendance
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 第三方人员ID */
	private String personId;
	/** 考勤时间 */
	private String attendanceTime;
	/** 考勤设备编号 */
	private String equipmentNumber;
	/** 考勤人脸图 */
	private String faceUrl;
	/** 请求的ip地址 */
	private String ip;

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
	public void setAttendanceTime(String attendanceTime) 
	{
		this.attendanceTime = attendanceTime;
	}

	public String getAttendanceTime() 
	{
		return attendanceTime;
	}
	public void setEquipmentNumber(String equipmentNumber) 
	{
		this.equipmentNumber = equipmentNumber;
	}

	public String getEquipmentNumber() 
	{
		return equipmentNumber;
	}
	public void setFaceUrl(String faceUrl) 
	{
		this.faceUrl = faceUrl;
	}

	public String getFaceUrl() 
	{
		return faceUrl;
	}
	public void setIp(String ip) 
	{
		this.ip = ip;
	}

	public String getIp() 
	{
		return ip;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("personId", getPersonId())
            .append("attendanceTime", getAttendanceTime())
            .append("equipmentNumber", getEquipmentNumber())
            .append("faceUrl", getFaceUrl())
            .append("ip", getIp())
            .toString();
    }
}
