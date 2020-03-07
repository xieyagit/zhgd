package com.hujiang.project.zhgd.lyRegistrationRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 登记记录表 ly_registration_record
 * 
 * @author hujiang
 * @date 2020-03-06
 */
public class LyRegistrationRecord
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 人员id */
	private Integer pwid;
	/** 登记时间 */
	private String time;
	/** 所属公司或拜访公司 */
	private String companyName;
	/** 所属楼层 */
	private String floor;
	/** 备注 */
	private String bz;
	/** 所属部门或拜访对象 */
	private String subordinate;
private  String ispresent;

	public String getIspresent() {
		return ispresent;
	}

	public void setIspresent(String ispresent) {
		this.ispresent = ispresent;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setPwid(Integer pwid) 
	{
		this.pwid = pwid;
	}

	public Integer getPwid() 
	{
		return pwid;
	}
	public void setTime(String time) 
	{
		this.time = time;
	}

	public String getTime() 
	{
		return time;
	}
	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}

	public String getCompanyName() 
	{
		return companyName;
	}
	public void setFloor(String floor) 
	{
		this.floor = floor;
	}

	public String getFloor() 
	{
		return floor;
	}
	public void setBz(String bz) 
	{
		this.bz = bz;
	}

	public String getBz() 
	{
		return bz;
	}
	public void setSubordinate(String subordinate) 
	{
		this.subordinate = subordinate;
	}

	public String getSubordinate() 
	{
		return subordinate;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pwid", getPwid())
            .append("time", getTime())
            .append("companyName", getCompanyName())
            .append("floor", getFloor())
            .append("bz", getBz())
            .append("subordinate", getSubordinate())
            .toString();
    }
}
