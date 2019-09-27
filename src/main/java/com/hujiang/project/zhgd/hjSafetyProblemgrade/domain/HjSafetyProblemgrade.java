package com.hujiang.project.zhgd.hjSafetyProblemgrade.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hujiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 问题级别表 hj_safety_problemgrade
 * 
 * @author hujiang
 * @date 2019-07-10
 */
public class HjSafetyProblemgrade
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 问题严重性 */
	private String grade;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setGrade(String grade) 
	{
		this.grade = grade;
	}

	public String getGrade() 
	{
		return grade;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("grade", getGrade())
            .toString();
    }
}
