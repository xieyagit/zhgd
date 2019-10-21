package com.hujiang.project.zhgd.sbElevatorAddrecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 2.5.3升降机实时数据表 sb_elevator_addrecord
 *
 * @author hujiang
 * @date 2019-07-04
 */
public class SbElevatorAddrecord
{
	private static final long serialVersionUID = 1L;

	/**  */
	private Integer id;
	/** 设备编号 */
	private String deviceNo;
	/** 运行时,格式为yyyy-MM-dd hh:mm:ss， */
	private String runtime;
	/** 载重（T） */
	private Double laod;
	/** 载重比 */
	private Double loadRatio;
	/** 左右笼标记(0单笼,1左笼,2右笼) */
	private Integer sign;
	/** 载重人数 */
	private Integer loadingCapacity;
	/** 升降机司机名字 */
	private String operatorName;
	/** IC卡号 */
	private Integer icNum;
	/** 方向(0停止,1上行,2下行) */
	private Integer direction;
	/** 高度 */
	private Double height;
	/** 实时标志(0历史数据,1实时数据) */
	private Integer realTimeFlag;
	/** 状态(32监理授64加节模式128 下限位报警) */
	private Integer status;
	/** 制动距离 */
	private Integer brakingDistance;
	/** 是否上限位报警（0-否，1-是，2-无上限位） */
	private Integer isUpWarning;
	/** 是否下限位报警( 0-否，1-是，2-无下限位） */
	private Integer isDownWarning;
	/** 是否安全器报警(0.否1.是) */
	private Integer isSafetyDeviceWarn;
	/** 是否超重报警(0.否1.是) */
	private Integer isOverWarning;
	/** 是否前限位报警(0.否1.是) */
	private Integer isForwardWarning;
	/** 是否后限位报警(0.否1.是) */
	private Integer isBackwardWarning;
	/** 是否超限位报警*/
	private Integer isLimitWarning;
	/** 设备编号 */
	private String hxzid;
	/** 人数报警0:正常1:报警 */
	private Integer peopleCntAlarm;
	/** 载重报警0:正常1:报警 */
	private Integer weightAlarm;
	/** 速度报警0:正常1:报警 */
	private Integer speedAlarm;
	/** 倾角X报警0:正常1:报警 */
	private Integer obliguityXAlarm;
	/** 倾角Y报警0:正常1:报警 */
	private Integer obliguityYAlarm;
	/** 速度 */
	private Double speed;
	/** 风速 */
	private Double windSpeed;
	/** 风级 */
	private Double windLevel;
	/** 楼层 */
	private Double floor;
	/** 倾角X */
	private Double obliguityX;
	/** 倾角Y */
	private Double obliguityY;

	private String maxRuntime;
	private String minRuntime;
	private Integer userid;
	/** 项目监督编号 */
	private String jdbh;
	private String peopleCnt;	//人数
	private Integer projectId;

	private String xmid;
	private String elevatorName;
	private String installCompany;
	private String serialNum;
	private String dname;
	private String subId;
	private String capacity;
	private String DownlineTime;
	private String PunchTime;
	private String ClosingTime;

	public String getPunchTime() {
		return PunchTime;
	}

	public void setPunchTime(String punchTime) {
		PunchTime = punchTime;
	}

	public String getClosingTime() {
		return ClosingTime;
	}

	public void setClosingTime(String closingTime) {
		ClosingTime = closingTime;
	}


	public String getDownlineTime() {
		return DownlineTime;
	}

	public void setDownlineTime(String downlineTime) {
		DownlineTime = downlineTime;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getInstallCompany() {
		return installCompany;
	}

	public void setInstallCompany(String installCompany) {
		this.installCompany = installCompany;
	}

	public String getElevatorName() {
		return elevatorName;
	}

	public void setElevatorName(String elevatorName) {
		this.elevatorName = elevatorName;
	}

	public String getXmid() {
		return xmid;
	}

	public void setXmid(String xmid) {
		this.xmid = xmid;
	}

	public Integer getIsLimitWarning() {
		return isLimitWarning;
	}

	public void setIsLimitWarning(Integer isLimitWarning) {
		this.isLimitWarning = isLimitWarning;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getPeopleCnt() {
		return peopleCnt;
	}

	public void setPeopleCnt(String peopleCnt) {
		this.peopleCnt = peopleCnt;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getMaxRuntime() {
		return maxRuntime;
	}

	public void setMaxRuntime(String maxRuntime) {
		this.maxRuntime = maxRuntime;
	}

	public String getMinRuntime() {
		return minRuntime;
	}

	public void setMinRuntime(String minRuntime) {
		this.minRuntime = minRuntime;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}
	public void setDeviceNo(String deviceNo)
	{
		this.deviceNo = deviceNo;
	}

	public String getDeviceNo()
	{
		return deviceNo;
	}
	public void setRuntime(String runtime)
	{
		this.runtime = runtime;
	}

	public String getRuntime()
	{
		return runtime;
	}
	public void setLaod(Double laod)
	{
		this.laod = laod;
	}

	public Double getLaod()
	{
		return laod;
	}
	public void setLoadRatio(Double loadRatio)
	{
		this.loadRatio = loadRatio;
	}

	public Double getLoadRatio()
	{
		return loadRatio;
	}
	public void setSign(Integer sign)
	{
		this.sign = sign;
	}

	public Integer getSign()
	{
		return sign;
	}
	public void setLoadingCapacity(Integer loadingCapacity)
	{
		this.loadingCapacity = loadingCapacity;
	}

	public Integer getLoadingCapacity()
	{
		return loadingCapacity;
	}
	public void setOperatorName(String operatorName)
	{
		this.operatorName = operatorName;
	}

	public String getOperatorName()
	{
		return operatorName;
	}
	public void setIcNum(Integer icNum)
	{
		this.icNum = icNum;
	}

	public Integer getIcNum()
	{
		return icNum;
	}
	public void setDirection(Integer direction)
	{
		this.direction = direction;
	}

	public Integer getDirection()
	{
		return direction;
	}
	public void setHeight(Double height)
	{
		this.height = height;
	}

	public Double getHeight()
	{
		return height;
	}
	public void setRealTimeFlag(Integer realTimeFlag)
	{
		this.realTimeFlag = realTimeFlag;
	}

	public Integer getRealTimeFlag()
	{
		return realTimeFlag;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Integer getStatus()
	{
		return status;
	}
	public void setBrakingDistance(Integer brakingDistance)
	{
		this.brakingDistance = brakingDistance;
	}

	public Integer getBrakingDistance()
	{
		return brakingDistance;
	}
	public void setIsUpWarning(Integer isUpWarning)
	{
		this.isUpWarning = isUpWarning;
	}

	public Integer getIsUpWarning()
	{
		return isUpWarning;
	}
	public void setIsDownWarning(Integer isDownWarning)
	{
		this.isDownWarning = isDownWarning;
	}

	public Integer getIsDownWarning()
	{
		return isDownWarning;
	}
	public void setIsSafetyDeviceWarn(Integer isSafetyDeviceWarn)
	{
		this.isSafetyDeviceWarn = isSafetyDeviceWarn;
	}

	public Integer getIsSafetyDeviceWarn()
	{
		return isSafetyDeviceWarn;
	}
	public void setIsOverWarning(Integer isOverWarning)
	{
		this.isOverWarning = isOverWarning;
	}

	public Integer getIsOverWarning()
	{
		return isOverWarning;
	}
	public void setIsForwardWarning(Integer isForwardWarning)
	{
		this.isForwardWarning = isForwardWarning;
	}

	public Integer getIsForwardWarning()
	{
		return isForwardWarning;
	}
	public void setIsBackwardWarning(Integer isBackwardWarning)
	{
		this.isBackwardWarning = isBackwardWarning;
	}

	public Integer getIsBackwardWarning()
	{
		return isBackwardWarning;
	}
	public void setHxzid(String hxzid)
	{
		this.hxzid = hxzid;
	}

	public String getHxzid()
	{
		return hxzid;
	}
	public void setPeopleCntAlarm(Integer peopleCntAlarm)
	{
		this.peopleCntAlarm = peopleCntAlarm;
	}

	public Integer getPeopleCntAlarm()
	{
		return peopleCntAlarm;
	}
	public void setWeightAlarm(Integer weightAlarm)
	{
		this.weightAlarm = weightAlarm;
	}

	public Integer getWeightAlarm()
	{
		return weightAlarm;
	}
	public void setSpeedAlarm(Integer speedAlarm)
	{
		this.speedAlarm = speedAlarm;
	}

	public Integer getSpeedAlarm()
	{
		return speedAlarm;
	}
	public void setObliguityXAlarm(Integer obliguityXAlarm)
	{
		this.obliguityXAlarm = obliguityXAlarm;
	}

	public Integer getObliguityXAlarm()
	{
		return obliguityXAlarm;
	}
	public void setObliguityYAlarm(Integer obliguityYAlarm)
	{
		this.obliguityYAlarm = obliguityYAlarm;
	}

	public Integer getObliguityYAlarm()
	{
		return obliguityYAlarm;
	}
	public void setSpeed(Double speed)
	{
		this.speed = speed;
	}

	public Double getSpeed()
	{
		return speed;
	}
	public void setWindSpeed(Double windSpeed)
	{
		this.windSpeed = windSpeed;
	}

	public Double getWindSpeed()
	{
		return windSpeed;
	}
	public void setWindLevel(Double windLevel)
	{
		this.windLevel = windLevel;
	}

	public Double getWindLevel()
	{
		return windLevel;
	}
	public void setFloor(Double floor)
	{
		this.floor = floor;
	}

	public Double getFloor()
	{
		return floor;
	}
	public void setObliguityX(Double obliguityX)
	{
		this.obliguityX = obliguityX;
	}

	public Double getObliguityX()
	{
		return obliguityX;
	}
	public void setObliguityY(Double obliguityY)
	{
		this.obliguityY = obliguityY;
	}

	public Double getObliguityY()
	{
		return obliguityY;
	}

	public String getJdbh() {
		return jdbh;
	}

	public void setJdbh(String jdbh) {
		this.jdbh = jdbh;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("deviceNo", getDeviceNo())
				.append("runtime", getRuntime())
				.append("laod", getLaod())
				.append("loadRatio", getLoadRatio())
				.append("sign", getSign())
				.append("loadingCapacity", getLoadingCapacity())
				.append("operatorName", getOperatorName())
				.append("icNum", getIcNum())
				.append("direction", getDirection())
				.append("height", getHeight())
				.append("realTimeFlag", getRealTimeFlag())
				.append("status", getStatus())
				.append("brakingDistance", getBrakingDistance())
				.append("isUpWarning", getIsUpWarning())
				.append("isDownWarning", getIsDownWarning())
				.append("isSafetyDeviceWarn", getIsSafetyDeviceWarn())
				.append("isOverWarning", getIsOverWarning())
				.append("isForwardWarning", getIsForwardWarning())
				.append("isBackwardWarning", getIsBackwardWarning())
				.append("hxzid", getHxzid())
				.append("peopleCntAlarm", getPeopleCntAlarm())
				.append("weightAlarm", getWeightAlarm())
				.append("speedAlarm", getSpeedAlarm())
				.append("obliguityXAlarm", getObliguityXAlarm())
				.append("obliguityYAlarm", getObliguityYAlarm())
				.append("speed", getSpeed())
				.append("windSpeed", getWindSpeed())
				.append("windLevel", getWindLevel())
				.append("floor", getFloor())
				.append("obliguityX", getObliguityX())
				.append("obliguityY", getObliguityY())
				.toString();
	}
}
