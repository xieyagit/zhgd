package com.hujiang.project.zhgd.hjZhgdPkcount.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 车位剩余表 hj_zhgd_pkcount
 * 
 * @author hujiang
 * @date 2019-07-09
 */
public class HjZhgdPkcount
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 剩余车位 */
	private Integer pkcount;
	/** 车场ID */
	private Integer deptID;
	/**外建项目ID*/
	private Integer projectId;
	/**停车场名称*/
	private String carName;
	/**设备sn*/
	private String sn;
	/**设备名称*/
	private String snName;

	public String getSnName() {
		return snName;
	}

	public void setSnName(String snName) {
		this.snName = snName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

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
	public void setPkcount(Integer pkcount) 
	{
		this.pkcount = pkcount;
	}

	public Integer getPkcount() 
	{
		return pkcount;
	}
	public void setDeptID(Integer deptID)
	{
		this.deptID = deptID;
	}

	public Integer getDeptID() 
	{
		return deptID;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
