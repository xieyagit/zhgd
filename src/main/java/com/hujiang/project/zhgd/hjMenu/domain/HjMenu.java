package com.hujiang.project.zhgd.hjMenu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 两制服务菜单表 hj_menu
 * 
 * @author hujiang
 * @date 2019-07-01
 */
public class HjMenu
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 资料名称 */
	private String materialName;
	/** 上传状态 */
	private String uploadingType;
	/** 分类菜单（1、项目信息表菜单，2、分包信息表菜单） */
	private Integer typeId;

	private String uploadingname;

	private Object uploadingDate;

	private int projectId;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getUploadingname() {
		return uploadingname;
	}

	public void setUploadingname(String uploadingname) {
		this.uploadingname = uploadingname;
	}

	public Object getUploadingDate() {
		return uploadingDate;
	}

	public void setUploadingDate(Object uploadingDate) {
		this.uploadingDate = uploadingDate;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setMaterialName(String materialName) 
	{
		this.materialName = materialName;
	}

	public String getMaterialName() 
	{
		return materialName;
	}
	public void setUploadingType(String uploadingType) 
	{
		this.uploadingType = uploadingType;
	}

	public String getUploadingType() 
	{
		return uploadingType;
	}
	public void setTypeId(Integer typeId) 
	{
		this.typeId = typeId;
	}

	public Integer getTypeId() 
	{
		return typeId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("materialName", getMaterialName())
            .append("uploadingType", getUploadingType())
            .append("typeId", getTypeId())
            .toString();
    }
}
