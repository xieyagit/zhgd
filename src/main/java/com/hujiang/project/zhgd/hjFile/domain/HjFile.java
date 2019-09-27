package com.hujiang.project.zhgd.hjFile.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文件路径表 hj_file
 * 
 * @author hujiang
 * @date 2019-06-28
 */
public class HjFile
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 文件夹编号 */
	private Integer folderId;
	/** 文件路径 */
	private String fileUrl;
	/** 文件大小KB */
	private Long fileSize;
	/** 上传文件的账户 */
	private String uploadAccount;
	/** 文件名 */
	private String fileName;
	/** 上传时间 */
	private String uploadTime;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setFolderId(Integer folderId) 
	{
		this.folderId = folderId;
	}

	public Integer getFolderId() 
	{
		return folderId;
	}
	public void setFileUrl(String fileUrl) 
	{
		this.fileUrl = fileUrl;
	}

	public String getFileUrl() 
	{
		return fileUrl;
	}
	public void setFileSize(Long fileSize)
	{
		this.fileSize = fileSize;
	}

	public Long getFileSize()
	{
		return fileSize;
	}
	public void setUploadAccount(String uploadAccount) 
	{
		this.uploadAccount = uploadAccount;
	}

	public String getUploadAccount() 
	{
		return uploadAccount;
	}
	public void setFileName(String fileName) 
	{
		this.fileName = fileName;
	}

	public String getFileName() 
	{
		return fileName;
	}

	@Override
	public String toString() {
		return "HjFile{" +
				"id=" + id +
				", folderId=" + folderId +
				", fileUrl='" + fileUrl + '\'' +
				", fileSize=" + fileSize +
				", uploadAccount='" + uploadAccount + '\'' +
				", fileName='" + fileName + '\'' +
				", uploadTime='" + uploadTime + '\'' +
				'}';
	}
}
