package com.hujiang.project.zhgd.hjghformwork.domain;


import com.hujiang.framework.web.domain.BaseEntity;

/**
 * 高支模监测因素表 sb_highformwork_display
 * 
 * @author hujiang
 * @date 2019-09-09
 */
public class HighformworkDisplay extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 监测因素名称 */
	private String name;
	/** 结构物是否已配置该监测因素，{true:已配置, false:未配置} */
	private String checked;
	/** 是否可配置分时阈值 */
	private String timeDivision;
	/** 结构物id */
	private String reserved;
	/** 供应商字段 */
	private int supplier;
	/** 因素id+供应商加密生成 */
	private String displayKey;

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
	public void setChecked(String checked) 
	{
		this.checked = checked;
	}

	public String getChecked() 
	{
		return checked;
	}
	public void setTimeDivision(String timeDivision) 
	{
		this.timeDivision = timeDivision;
	}

	public String getTimeDivision() 
	{
		return timeDivision;
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

	public String getDisplayKey() {
		return displayKey;
	}

	public void setDisplayKey(String displayKey) {
		this.displayKey = displayKey;
	}

	@Override
	public String toString() {
		return "HighformworkDisplay{" +
				"id=" + id +
				", name='" + name + '\'' +
				", checked='" + checked + '\'' +
				", timeDivision='" + timeDivision + '\'' +
				", reserved='" + reserved + '\'' +
				", supplier=" + supplier +
				", displayKey='" + displayKey + '\'' +
				'}';
	}
}
