package com.hujiang.project.zhgd.hjSystemPrivileges.domain;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 系统-权限表 hj_system_privileges
 * 
 * @author hujiang
 * @date 2019-05-15
 */
public class HjSystemPrivileges
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 权限名称 */
	private String privilegesName;
	/** 请求路径 */
	private String url;
	/** 权限类型（0菜单，1目录，2按钮） */
	private Integer privilegesType;
	/** 是否可见（0.显示  1.隐藏） */
	private Integer whetherOrNotVisible;
	/** 父级ID（1级为0） */
	private Integer parentId;
	/** 创建时间 */
	private String createDate;
	/** 修改时间 */
	private String updateDate;
	/** app、pc后台、pc公司、pc项目、pc看板权限菜单（app 0,pc后台1，pc公司 ,2, pc项目3、pc看板 4） */
	private Integer appOrPc;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setPrivilegesName(String privilegesName) 
	{
		this.privilegesName = privilegesName;
	}

	public String getPrivilegesName() 
	{
		return privilegesName;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getUrl() 
	{
		return url;
	}
	public void setPrivilegesType(Integer privilegesType) 
	{
		this.privilegesType = privilegesType;
	}

	public Integer getPrivilegesType() 
	{
		return privilegesType;
	}
	public void setWhetherOrNotVisible(Integer whetherOrNotVisible) 
	{
		this.whetherOrNotVisible = whetherOrNotVisible;
	}

	public Integer getWhetherOrNotVisible() 
	{
		return whetherOrNotVisible;
	}
	public void setParentId(Integer parentId) 
	{
		this.parentId = parentId;
	}

	public Integer getParentId() 
	{
		return parentId;
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
	public void setAppOrPc(Integer appOrPc) 
	{
		this.appOrPc = appOrPc;
	}

	public Integer getAppOrPc() 
	{
		return appOrPc;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("privilegesName", getPrivilegesName())
            .append("url", getUrl())
            .append("privilegesType", getPrivilegesType())
            .append("whetherOrNotVisible", getWhetherOrNotVisible())
            .append("parentId", getParentId())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .append("appOrPc", getAppOrPc())
            .toString();
    }
}
