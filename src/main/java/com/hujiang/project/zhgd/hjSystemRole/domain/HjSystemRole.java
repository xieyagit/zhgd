package com.hujiang.project.zhgd.hjSystemRole.domain;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 系统-角色表 hj_system_role
 * 
 * @author hujiang
 * @date 2019-05-15
 */
public class HjSystemRole
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 角色名 */
	private String roleName;
	/** 备注 */
	private String remark;
	/** 所属层级（0.集团，1.公司，2项目） */
	private Integer hierarchy;
	/** 属于（创建角色的集团id，公司id，项目id） */
	private Integer belong;
	/** 创建时间 */
	private String createDate;
	/** 修改时间 */
	private String updateDate;
	/** 数据权限 */
	private String dataPermission;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setRoleName(String roleName) 
	{
		this.roleName = roleName;
	}

	public String getRoleName() 
	{
		return roleName;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setHierarchy(Integer hierarchy) 
	{
		this.hierarchy = hierarchy;
	}

	public Integer getHierarchy() 
	{
		return hierarchy;
	}
	public void setBelong(Integer belong) 
	{
		this.belong = belong;
	}

	public Integer getBelong() 
	{
		return belong;
	}
	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getCreateDate()
	{
		return createDate;
	}
	public void setUpdateDate(String updateDate)
	{
		this.updateDate = updateDate;
	}

	public String getUpdateDate()
	{
		return updateDate;
	}
	public void setDataPermission(String dataPermission) 
	{
		this.dataPermission = dataPermission;
	}

	public String getDataPermission() 
	{
		return dataPermission;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("roleName", getRoleName())
            .append("remark", getRemark())
            .append("hierarchy", getHierarchy())
            .append("belong", getBelong())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("dataPermission", getDataPermission())
            .toString();
    }
}
