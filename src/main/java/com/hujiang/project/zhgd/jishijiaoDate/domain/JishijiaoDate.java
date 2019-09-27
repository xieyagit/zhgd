package com.hujiang.project.zhgd.jishijiaoDate.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 极视角异常数据表 jishijiao_date
 * 
 * @author hujiang
 * @date 2019-08-15
 */
public class JishijiaoDate
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Integer id;
	/** 算法ID，由极视角定义的算法ID，只读 */
	private String aid;
	/** 摄像头ID */
	private String cid;
	/** 摄像头rtsp地址 */
	private String cidUrl;
	/** 算法服务器时间戳，unix标准时间戳格式 */
	private String timeStamp;
	/** 原始分析图片，base64格式编码 */
	private String srcpicData;
	/** 报警输出图片关联的原始分析图片 */
	private String srcpicName;
	/** 状态值，0/无报警，1/有报警 */
	private Integer status;
	/** 报警图片命名，格式为“时间（精确到秒）_us（微秒）_cid（摄像头ID）_fix（输入或输出）.jpg”，例：20180719121005_266236_3_out.jpg */
	private String picName;
	/** 报警结果图片，base64格式编码 */
	private String picData;
	/** 是否报警 */
	private Integer alertFlag;
	/** 检测到未戴安全帽的数量 */
	private Integer numOfHead;
	/** 报警时间戳，秒 */
	private String timeStampE;
	/** 头部信息 x y width height头部坐标位置 */
	private String headInfo;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setAid(String aid) 
	{
		this.aid = aid;
	}

	public String getAid() 
	{
		return aid;
	}
	public void setCid(String cid) 
	{
		this.cid = cid;
	}

	public String getCid() 
	{
		return cid;
	}
	public void setCidUrl(String cidUrl) 
	{
		this.cidUrl = cidUrl;
	}

	public String getCidUrl() 
	{
		return cidUrl;
	}
	public void setTimeStamp(String timeStamp) 
	{
		this.timeStamp = timeStamp;
	}

	public String getTimeStamp() 
	{
		return timeStamp;
	}
	public void setSrcpicData(String srcpicData) 
	{
		this.srcpicData = srcpicData;
	}

	public String getSrcpicData() 
	{
		return srcpicData;
	}
	public void setSrcpicName(String srcpicName) 
	{
		this.srcpicName = srcpicName;
	}

	public String getSrcpicName() 
	{
		return srcpicName;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setPicName(String picName) 
	{
		this.picName = picName;
	}

	public String getPicName() 
	{
		return picName;
	}
	public void setPicData(String picData) 
	{
		this.picData = picData;
	}

	public String getPicData() 
	{
		return picData;
	}
	public void setAlertFlag(Integer alertFlag) 
	{
		this.alertFlag = alertFlag;
	}

	public Integer getAlertFlag() 
	{
		return alertFlag;
	}
	public void setNumOfHead(Integer numOfHead) 
	{
		this.numOfHead = numOfHead;
	}

	public Integer getNumOfHead() 
	{
		return numOfHead;
	}
	public void setTimeStampE(String timeStampE) 
	{
		this.timeStampE = timeStampE;
	}

	public String getTimeStampE() 
	{
		return timeStampE;
	}
	public void setHeadInfo(String headInfo) 
	{
		this.headInfo = headInfo;
	}

	public String getHeadInfo() 
	{
		return headInfo;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("aid", getAid())
            .append("cid", getCid())
            .append("cidUrl", getCidUrl())
            .append("timeStamp", getTimeStamp())
            .append("srcpicData", getSrcpicData())
            .append("srcpicName", getSrcpicName())
            .append("status", getStatus())
            .append("picName", getPicName())
            .append("picData", getPicData())
            .append("alertFlag", getAlertFlag())
            .append("numOfHead", getNumOfHead())
            .append("timeStampE", getTimeStampE())
            .append("headInfo", getHeadInfo())
            .toString();
    }
}
