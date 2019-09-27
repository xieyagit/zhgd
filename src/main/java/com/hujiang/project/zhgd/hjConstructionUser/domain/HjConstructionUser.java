package com.hujiang.project.zhgd.hjConstructionUser.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.hujiang.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 分包单位与用户关系表 hj_construction_user
 * 
 * @author hujiang
 * @date 2019-07-10
 */
public class HjConstructionUser
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Integer constructionId;
	/**  */
	private Integer userId;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setConstructionId(Integer constructionId) 
	{
		this.constructionId = constructionId;
	}

	public Integer getConstructionId() 
	{
		return constructionId;
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
            .append("constructionId", getConstructionId())
            .append("userId", getUserId())
            .toString();
    }
}
