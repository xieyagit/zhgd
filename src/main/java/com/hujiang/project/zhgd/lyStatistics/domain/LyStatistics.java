package com.hujiang.project.zhgd.lyStatistics.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 楼宇人员进出每日统计表 ly_statistics
 * 
 * @author hujiang
 * @date 2020-03-13
 */
public class LyStatistics
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 项目id */
	private Integer pid;
	/** 日期 yyyy-MM-dd */
	private String time;
	/** 人员类型，1办公人员，2，访客 */
	private String type;
	/** 人员进出数 */
	private String inout;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setPid(Integer pid) 
	{
		this.pid = pid;
	}

	public Integer getPid() 
	{
		return pid;
	}
	public void setTime(String time) 
	{
		this.time = time;
	}

	public String getTime() 
	{
		return time;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setInout(String inout) 
	{
		this.inout = inout;
	}

	public String getInout() 
	{
		return inout;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("time", getTime())
            .append("type", getType())
            .append("inout", getInout())
            .toString();
    }
}
