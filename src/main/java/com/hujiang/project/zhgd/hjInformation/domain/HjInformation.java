package com.hujiang.project.zhgd.hjInformation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 资料表 hj_information
 * 
 * @author hujiang
 * @date 2019-07-01
 */
public class HjInformation
{
	private static final long serialVersionUID = 1L;

	/** ID */
	private Integer id;
	/** 菜单ID */
	private Integer menuId;
	/** 文件名称 */
	private String fileName;
	/** 文件路径 */
	private String filePath;
	/** 上传时间 */
	private Date uploadingDate;
	/** 上传人姓名 */
	private String uploadingName;
	/** 备注 */
	private String remark;
	private Integer projectId;

	private MultipartFile[] file;
	private String[] fileNames;
	private String[] filePaths;

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	public String[] getFilePaths() {
		return filePaths;
	}

	public void setFilePaths(String[] filePaths) {
		this.filePaths = filePaths;
	}


	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public MultipartFile[] getFile() {
		return file;
	}

	public void setFile(MultipartFile[] file) {
		this.file = file;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}
	public void setMenuId(Integer menuId)
	{
		this.menuId = menuId;
	}

	public Integer getMenuId()
	{
		return menuId;
	}
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getFileName()
	{
		return fileName;
	}
	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public String getFilePath()
	{
		return filePath;
	}
	public void setUploadingDate(Date uploadingDate)
	{
		this.uploadingDate = uploadingDate;
	}

	public Date getUploadingDate()
	{
		return uploadingDate;
	}
	public void setUploadingName(String uploadingName)
	{
		this.uploadingName = uploadingName;
	}

	public String getUploadingName()
	{
		return uploadingName;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getRemark()
	{
		return remark;
	}

	@Override
	public String toString() {
		return "HjInformation{" +
				"id=" + id +
				", menuId=" + menuId +
				", fileName='" + fileName + '\'' +
				", filePath='" + filePath + '\'' +
				", uploadingDate=" + uploadingDate +
				", uploadingName='" + uploadingName + '\'' +
				", remark='" + remark + '\'' +
				", projectId=" + projectId +
				", file=" + Arrays.toString(file) +
				'}';
	}
}
