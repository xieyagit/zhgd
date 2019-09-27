package com.hujiang.project.zhgd.hjSafetyHidden.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hujiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 隐患类型表 hj_safety_hidden
 * 
 * @author hujiang
 * @date 2019-07-10
 */
public class HjSafetyHidden
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 隐患名称 */
	private String hiddenName;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setHiddenName(String hiddenName) 
	{
		this.hiddenName = hiddenName;
	}

	public String getHiddenName() 
	{
		return hiddenName;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("hiddenName", getHiddenName())
            .toString();
    }
}
