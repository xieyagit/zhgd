package com.hujiang.project.zhgd.hjUserFeedback.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户反馈表 hj_user_feedback
 * 
 * @author hujiang
 * @date 2019-07-03
 */
public class HjUserFeedback
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 文件名称 */
	private String fileName;
	/** 文件路径 */
	private String filePath;
	/** 问题 */
	private String problem;
	/** 电话 */
	private String phone;
	/** 邮箱 */
	private String email;
	/** 用户ID */
	private Integer userId;

	private MultipartFile[] file;


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
	public void setProblem(String problem) 
	{
		this.problem = problem;
	}

	public String getProblem() 
	{
		return problem;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getPhone() 
	{
		return phone;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getEmail() 
	{
		return email;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileName", getFileName())
            .append("filePath", getFilePath())
            .append("problem", getProblem())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("userId", getUserId())
            .toString();
    }
}
