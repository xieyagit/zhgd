package com.hujiang.project.zhgd.sbGroupTalkback.domain;

import com.hujiang.project.zhgd.sbAccountTalkback.domain.SbAccountTalkback;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 集团对讲账号表 sb_group_talkback
 * 
 * @author hujiang
 * @date 2019-12-05
 */
public class SbGroupTalkback
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer id;
	/** 所属公司或项目ID */
	private Integer cpid;
	/** 对讲登录账号 */
	private String gtAccount;
	/** 对讲登录密码 */
	private String gtPassword;
	/** 是集团还是项目 */
	private String isIdType;
	/** 对讲列表 */
	private List<SbAccountTalkback> atList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCpid() {
		return cpid;
	}

	public void setCpid(Integer cpid) {
		this.cpid = cpid;
	}

	public String getGtAccount() {
		return gtAccount;
	}

	public void setGtAccount(String gtAccount) {
		this.gtAccount = gtAccount;
	}

	public String getGtPassword() {
		return gtPassword;
	}

	public void setGtPassword(String gtPassword) {
		this.gtPassword = gtPassword;
	}

	public String getIsIdType() {
		return isIdType;
	}

	public void setIsIdType(String isIdType) {
		this.isIdType = isIdType;
	}

	public List<SbAccountTalkback> getAtList() {
		return atList;
	}

	public void setAtList(List<SbAccountTalkback> atList) {
		this.atList = atList;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cpid", getCpid())
            .append("gtAccount", getGtAccount())
            .append("gtPassword", getGtPassword())
            .toString();
    }
}
