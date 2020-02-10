package com.hujiang.project.zhgd.hjghformwork.domain;


import com.hujiang.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * 高支模报警记录表 hj_highformwork_alarm_data
 *
 * @author hujiang
 * @date 2019-09-09
 */
public class HighformworkAlarmData extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** id */
	private String id;
	/** 结构件id */
	private Integer structuresId;
	/** 结构件id */
	private String structuresName;
	/** 报警id */
	private String alarmId;
	/** 产生告警的对象id（即，告警源） */
	private String sourceId;
	/** 产生告警的对象名称 */
	private String sourceName;
	/** 告警源类型id */
	private Integer sourceTypeId;
	/** 告警源类型名称 */
	private String sourceTypeName;
	/** 告警类型码 */
	private String alarmTypeCode;
	/** 告警等级 */
	private Integer level;
	/** 告警信息 */
	private String content;
	/** 告警产生的次数 */
	private Integer count;
	/** 告警状态，{0:告警新创建, 1:告警重复产生, 2:告警等级提升, 3:告警已自动消除, 4:告警已人工确认 */
	private Integer state;
	/** 告警产生时间 */
	private String startTime;
	/** 告警更新时间 */
	private String endTime;
	private String endTime2;

	public String getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(String endTime2) {
		this.endTime2 = endTime2;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}
	public void setStructuresId(Integer structuresId)
	{
		this.structuresId = structuresId;
	}

	public Integer getStructuresId()
	{
		return structuresId;
	}

	public String getStructuresName() {
		return structuresName;
	}

	public void setStructuresName(String structuresName) {
		this.structuresName = structuresName;
	}

	public void setAlarmId(String alarmId)
	{
		this.alarmId = alarmId;
	}

	public String getAlarmId()
	{
		return alarmId;
	}
	public void setSourceId(String sourceId)
	{
		this.sourceId = sourceId;
	}

	public String getSourceId()
	{
		return sourceId;
	}
	public void setSourceName(String sourceName)
	{
		this.sourceName = sourceName;
	}

	public String getSourceName()
	{
		return sourceName;
	}
	public void setSourceTypeId(Integer sourceTypeId)
	{
		this.sourceTypeId = sourceTypeId;
	}

	public Integer getSourceTypeId()
	{
		return sourceTypeId;
	}
	public void setSourceTypeName(String sourceTypeName)
	{
		this.sourceTypeName = sourceTypeName;
	}

	public String getSourceTypeName()
	{
		return sourceTypeName;
	}
	public void setAlarmTypeCode(String alarmTypeCode)
	{
		this.alarmTypeCode = alarmTypeCode;
	}

	public String getAlarmTypeCode()
	{
		return alarmTypeCode;
	}
	public void setLevel(Integer level)
	{
		this.level = level;
	}

	public Integer getLevel()
	{
		return level;
	}
	public void setContent(String content)
	{
		this.content = content;
	}

	public String getContent()
	{
		return content;
	}
	public void setCount(Integer count)
	{
		this.count = count;
	}

	public Integer getCount()
	{
		return count;
	}
	public void setState(Integer state)
	{
		this.state = state;
	}

	public Integer getState()
	{
		return state;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "HighformworkAlarmData{" +
				"id=" + id +
				", structuresId=" + structuresId +
				", alarmId='" + alarmId + '\'' +
				", sourceId='" + sourceId + '\'' +
				", sourceName='" + sourceName + '\'' +
				", sourceTypeId=" + sourceTypeId +
				", sourceTypeName='" + sourceTypeName + '\'' +
				", alarmTypeCode='" + alarmTypeCode + '\'' +
				", level=" + level +
				", content='" + content + '\'' +
				", count=" + count +
				", state=" + state +
				", startTime=" + startTime +
				", endTime=" + endTime +
				'}';
	}
}
