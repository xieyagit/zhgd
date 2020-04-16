package com.hujiang.project.zhgd.lyPersonYunmou.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 云眸人员表 ly_person_yunmou
 * 
 * @author hujiang
 * @date 2020-04-14
 */
public class LyPersonYunmou
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer id;
	/** 身份证号（云眸人员账号统一用身份证号） */
	private String idCard;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setIdCard(String idCard) 
	{
		this.idCard = idCard;
	}

	public String getIdCard() 
	{
		return idCard;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("idCard", getIdCard())
            .toString();
    }
}
