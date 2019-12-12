package com.hujiang.project.zhgd.sbAccountTalkback.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 集团对讲机列表 sb_account_talkback
 * 
 * @author hujiang
 * @date 2019-12-05
 */
public class SbAccountTalkback
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer id;
	/** 集团对讲机账号ID */
	private Integer gtid;
	/** 对讲机名字 */
	private String atName;
	/** 对讲机呼叫号码 */
	private String atAccount;
	/** 项目id */
	private Integer pid;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setGtid(Integer gtid) 
	{
		this.gtid = gtid;
	}

	public Integer getGtid() 
	{
		return gtid;
	}
	public void setAtName(String atName) 
	{
		this.atName = atName;
	}

	public String getAtName() 
	{
		return atName;
	}
	public void setAtAccount(String atAccount) 
	{
		this.atAccount = atAccount;
	}

	public String getAtAccount() 
	{
		return atAccount;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("gtid", getGtid())
            .append("atName", getAtName())
            .append("atAccount", getAtAccount())
            .toString();
    }
}
