package com.hujiang.project.zhgd.moredian.moredianAttendanceRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 考勤记录表 sb_moredian_attendance_record
 * 
 * @author hujiang
 * @date 2019-05-14
 */
public class MoredianAttendanceRecord
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 魔点人员Id */
	private String memberId;
	/** 设备sn */
	private String deviceSn;
	/** 考勤时间 */
	private String recognizeTime;
	/** 图片 */
	private String fileKey;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
	}
	public void setDeviceSn(String deviceSn) 
	{
		this.deviceSn = deviceSn;
	}

	public String getDeviceSn() 
	{
		return deviceSn;
	}
	public void setRecognizeTime(String recognizeTime) 
	{
		this.recognizeTime = recognizeTime;
	}

	public String getRecognizeTime() 
	{
		return recognizeTime;
	}
	public void setFileKey(String fileKey) 
	{
		this.fileKey = fileKey;
	}

	public String getFileKey() 
	{
		return fileKey;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("memberId", getMemberId())
            .append("deviceSn", getDeviceSn())
            .append("recognizeTime", getRecognizeTime())
            .append("fileKey", getFileKey())
            .toString();
    }
}
