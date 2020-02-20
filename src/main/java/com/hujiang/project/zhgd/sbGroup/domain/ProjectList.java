package com.hujiang.project.zhgd.sbGroup.domain;

public class ProjectList
{
	/** 经度 */
	private String longitude;
	/**纬度 */
	private String latitude;
	/**项目名称 */
	private String name;
	/** 项目名称 */
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
