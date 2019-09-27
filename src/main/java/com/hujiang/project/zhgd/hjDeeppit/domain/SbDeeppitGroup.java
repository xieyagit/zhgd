package com.hujiang.project.zhgd.hjDeeppit.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 深基坑测点分组表 sb_deeppit_group
 * 
 * @author hujiang
 * @date 2019-09-02
 */
public class SbDeeppitGroup
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 分组名称 */
	private String name;
	/** 分组类型 */
	private String type;
	/** 预留 */
	private String reserved;
	/** 供应商字段 */
	private int supplier;
	/** 因素id+供应商加密生成 */
	private String groupKey;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setType(String type) 
	{
		this.type = type;
	}

	public String getType() 
	{
		return type;
	}
	public void setReserved(String reserved) 
	{
		this.reserved = reserved;
	}

	public String getReserved() 
	{
		return reserved;
	}

	public int getSupplier() {
		return supplier;
	}

	public void setSupplier(int supplier) {
		this.supplier = supplier;
	}

	public String getGroupKey() {
		return groupKey;
	}

	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}

	@Override
	public String toString() {
		return "SbDeeppitGroup{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", reserved='" + reserved + '\'' +
				", supplier=" + supplier +
				", groupKey='" + groupKey + '\'' +
				'}';
	}
}
