package com.hujiang.project.zhgd.hjghformwork.domain;


import com.hujiang.framework.web.domain.BaseEntity;

/**
 * 高支模测点表 sb_highformwork_group
 * 
 * @author hujiang
 * @date 2019-09-09
 */
public class HighformworkGroup extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 测点名称 */
	private String name;
	/** 测点类型 */
	private String type;
	/** 检测因素id */
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
		return "HighformworkGroup{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", reserved='" + reserved + '\'' +
				", supplier=" + supplier +
				", groupKey='" + groupKey + '\'' +
				'}';
	}
}
