package com.hujiang.project.zhgd.lyAttendanceRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;


/**
 * 考勤记录表 ly_attendance_record
 * 
 * @author hujiang
 * @date 2020-03-08
 */
public class LyAttendanceRecord
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 项目编号 */
	private Integer projectId;
	/** 员工编号 */
	private Integer employeeId;
	/** 通过时间 ”yyyy-MM-dd hh:mm:ss” */
	private String passedTime;
	/** 通行方向  in—进，out—出 */
	private String direction;
	/** 通行方式 1—人脸识别，2—虹膜识别，3—指纹识别，4—掌形识别，5—身份证识别，6—实名卡，7—异常清退（适用于人员没有通过闸机系统出工地而导致人员状态不一致的情况），8—一键开闸(需要与闸机交互)， 9—应急通道（不需要与闸机交互），10—二维码识别，11-其他方式 */
	private Integer way;
	/** 工地现场采集的人脸照片，url */
	private String sitePhoto;
	/** 创建时间 */
	private Date createDate;
	/** 上传时间 */
	private String uploadTime;
	/** 考勤设备类型（Android，iOS...） */
	private String deviceType;
	/** 考勤设备唯一标识码 */
	private String deviceSn;
	/** 体温 */
	private String temperature;
	/** 体温是否报警 */
	private String alarm;
	private String empName;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

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
	public void setEmployeeId(Integer employeeId) 
	{
		this.employeeId = employeeId;
	}

	public Integer getEmployeeId() 
	{
		return employeeId;
	}
	public void setPassedTime(String passedTime) 
	{
		this.passedTime = passedTime;
	}

	public String getPassedTime() 
	{
		return passedTime;
	}
	public void setDirection(String direction) 
	{
		this.direction = direction;
	}

	public String getDirection() 
	{
		return direction;
	}
	public void setWay(Integer way) 
	{
		this.way = way;
	}

	public Integer getWay() 
	{
		return way;
	}
	public void setSitePhoto(String sitePhoto) 
	{
		this.sitePhoto = sitePhoto;
	}

	public String getSitePhoto() 
	{
		return sitePhoto;
	}
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}

	public Date getCreateDate()
	{
		return createDate;
	}
	public void setUploadTime(String uploadTime) 
	{
		this.uploadTime = uploadTime;
	}

	public String getUploadTime() 
	{
		return uploadTime;
	}
	public void setDeviceType(String deviceType) 
	{
		this.deviceType = deviceType;
	}

	public String getDeviceType() 
	{
		return deviceType;
	}
	public void setDeviceSn(String deviceSn) 
	{
		this.deviceSn = deviceSn;
	}

	public String getDeviceSn() 
	{
		return deviceSn;
	}
	public void setTemperature(String temperature) 
	{
		this.temperature = temperature;
	}

	public String getTemperature() 
	{
		return temperature;
	}
	public void setAlarm(String alarm) 
	{
		this.alarm = alarm;
	}

	public String getAlarm() 
	{
		return alarm;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("employeeId", getEmployeeId())
            .append("passedTime", getPassedTime())
            .append("direction", getDirection())
            .append("way", getWay())
            .append("sitePhoto", getSitePhoto())
            .append("createDate", getCreateDate())
            .append("uploadTime", getUploadTime())
            .append("deviceType", getDeviceType())
            .append("deviceSn", getDeviceSn())
            .append("temperature", getTemperature())
            .append("alarm", getAlarm())
            .toString();
    }
}
