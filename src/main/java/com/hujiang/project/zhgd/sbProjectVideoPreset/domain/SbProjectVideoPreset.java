package com.hujiang.project.zhgd.sbProjectVideoPreset.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 预置点表 sb_project_video_preset
 * 
 * @author hujiang
 * @date 2019-12-11
 */
public class SbProjectVideoPreset
{
	private static final long serialVersionUID = 1L;
	
	/** ID */
	private Integer id;
	/** 摄像头sn */
	private String videoSn;
	/** 海康返回ID */
	private Integer presetIndex;
	/** 预置点名字 */
	private String presetName;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setVideoSn(String videoSn) 
	{
		this.videoSn = videoSn;
	}

	public String getVideoSn() 
	{
		return videoSn;
	}
	public void setPresetIndex(Integer presetIndex) 
	{
		this.presetIndex = presetIndex;
	}

	public Integer getPresetIndex() 
	{
		return presetIndex;
	}
	public void setPresetName(String presetName) 
	{
		this.presetName = presetName;
	}

	public String getPresetName() 
	{
		return presetName;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("videoSn", getVideoSn())
            .append("presetIndex", getPresetIndex())
            .append("presetName", getPresetName())
            .toString();
    }
}
