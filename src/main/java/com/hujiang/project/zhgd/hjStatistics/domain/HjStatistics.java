package com.hujiang.project.zhgd.hjStatistics.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

    
/**
 * 每日考勤统计表 hj_statistics
 * 
 * @author hujiang
 * @date 2019-05-30
 */
public class HjStatistics
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer id;
	/** 时间 */
	private String time;
	/** 类型。1管理人员，2，劳务工人 */
	private String type;
	/** 当前在场人数 */
	private Integer numberOne;
	/** 考勤总人数 */
	private Integer numberTwo;
	private Integer projectId;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setNumberOne(Integer numberOne) 
	{
		this.numberOne = numberOne;
	}

	public Integer getNumberOne() 
	{
		return numberOne;
	}
	public void setNumberTwo(Integer numberTwo) 
	{
		this.numberTwo = numberTwo;
	}

	public Integer getNumberTwo() 
	{
		return numberTwo;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("time", getTime())
            .append("type", getType())
            .append("numberOne", getNumberOne())
            .append("numberTwo", getNumberTwo())
            .append("projectId", getProjectId())
            .toString();
    }
}
