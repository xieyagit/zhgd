package com.hujiang.project.zhgd.vehicleImg.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * 车牌照片表 hujiang_vehicle_img
 * 
 * @author hujiang
 * @date 2019-05-13
 */
public class VehicleImg
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer id;
	/** 车场ID(项目id) */
	private String parkid;
	/** 记录ID(唯一标识) */
	private String recordid;
	/** 图片地址 */
	private String imgUrl;
	/**  进场方向*/
	private Integer picSource;

	public Integer getPicSource() {
		return picSource;
	}

	public void setPicSource(Integer picSource) {
		this.picSource = picSource;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setParkid(String parkid) 
	{
		this.parkid = parkid;
	}

	public String getParkid() 
	{
		return parkid;
	}
	public void setRecordid(String recordid) 
	{
		this.recordid = recordid;
	}

	public String getRecordid() 
	{
		return recordid;
	}
	public void setImgUrl(String imgUrl) 
	{
		this.imgUrl = imgUrl;
	}

	public String getImgUrl() 
	{
		return imgUrl;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parkid", getParkid())
            .append("recordid", getRecordid())
            .append("imgUrl", getImgUrl())
            .append("picSource", getPicSource())
            .toString();
    }
}
