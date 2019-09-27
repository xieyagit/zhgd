package com.hujiang.project.zhgd.sbCraneAddrecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 塔式起重机实时数据表 sb_crane_addrecord
 * 
 * @author hujiang
 * @date 2019-06-21
 */
public class SbCraneAddrecord
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	private Long id;
	/** 设备编号--32位 */
	private String deviceNo;
	/** 运行时,格式为yyyy-MM-dd hh:mm:ss */
	private String runtime;
	/** 载重（T) */
	private Double load;
	/** 载重比 */
	private Double loadRatio;
	/** 力矩 */
	private Double moment;
	/** 力矩比 */
	private Double momentRatio;
	/** 回转角度 */
	private Double slewingSpeed;
	/** 是否左限位报警(0.否1.是) */
	private Integer isLeftWarning;
	/** 是否右限位报警(0.否1.是) */
	private Integer isRightWarning;
	/** 幅度 */
	private Double range;
	/** 是否前限位报警(0.否1.是) */
	private Integer isForwardWarning;
	/** 是否后限位报警(0.否1.是) */
	private Integer isBackwardWarning;
	/** 高度 */
	private Double height;
	/** 是否上限位报警(0.否1.是) */
	private Integer isUpWarning;
	/** 塔机工作环境温度 */
	private Double workEnvironment;
	/** 实时标志(0历史数据,1实时数据) */
	private Integer realTimeFlag;
	/** 安装方式 */
	private Integer installationMethod;
	/** 风速 m/s */
	private Double windSpeed;
	/** 持卡卡号 */
	private String cardNum;
	/** 持卡人姓名 */
	private String operatorName;
	/** 设备编号 */
	private String hxzid;
	/** 倍率 */
	private Double magnification;
	/**额定载重*/
	private  Double ratedWeight;
	/**倾角*/
	private String obliguity;


	private String dname; 	//塔吊名称
	private Integer userid;


    public String getObliguity() {
        return obliguity;
    }

    public void setObliguity(String obliguity) {
        this.obliguity = obliguity;
    }

    public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Double getRatedWeight() {
		return ratedWeight;
	}

	public void setRatedWeight(Double ratedWeight) {
		this.ratedWeight = ratedWeight;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
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
	public void setLoad(Double load) 
	{
		this.load = load;
	}

	public Double getLoad() 
	{
		return load;
	}
	public void setLoadRatio(Double loadRatio) 
	{
		this.loadRatio = loadRatio;
	}

	public Double getLoadRatio() 
	{
		return loadRatio;
	}
	public void setMoment(Double moment) 
	{
		this.moment = moment;
	}

	public Double getMoment() 
	{
		return moment;
	}
	public void setMomentRatio(Double momentRatio) 
	{
		this.momentRatio = momentRatio;
	}

	public Double getMomentRatio() 
	{
		return momentRatio;
	}
	public void setSlewingSpeed(Double slewingSpeed) 
	{
		this.slewingSpeed = slewingSpeed;
	}

	public Double getSlewingSpeed() 
	{
		return slewingSpeed;
	}
	public void setIsLeftWarning(Integer isLeftWarning) 
	{
		this.isLeftWarning = isLeftWarning;
	}

	public Integer getIsLeftWarning() 
	{
		return isLeftWarning;
	}
	public void setIsRightWarning(Integer isRightWarning) 
	{
		this.isRightWarning = isRightWarning;
	}

	public Integer getIsRightWarning() 
	{
		return isRightWarning;
	}
	public void setRange(Double range) 
	{
		this.range = range;
	}

	public Double getRange() 
	{
		return range;
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
	public void setHeight(Double height) 
	{
		this.height = height;
	}

	public Double getHeight() 
	{
		return height;
	}
	public void setIsUpWarning(Integer isUpWarning) 
	{
		this.isUpWarning = isUpWarning;
	}

	public Integer getIsUpWarning() 
	{
		return isUpWarning;
	}
	public void setWorkEnvironment(Double workEnvironment) 
	{
		this.workEnvironment = workEnvironment;
	}

	public Double getWorkEnvironment() 
	{
		return workEnvironment;
	}
	public void setRealTimeFlag(Integer realTimeFlag) 
	{
		this.realTimeFlag = realTimeFlag;
	}

	public Integer getRealTimeFlag() 
	{
		return realTimeFlag;
	}
	public void setInstallationMethod(Integer installationMethod) 
	{
		this.installationMethod = installationMethod;
	}

	public Integer getInstallationMethod() 
	{
		return installationMethod;
	}
	public void setWindSpeed(Double windSpeed) 
	{
		this.windSpeed = windSpeed;
	}

	public Double getWindSpeed() 
	{
		return windSpeed;
	}
	public void setCardNum(String cardNum) 
	{
		this.cardNum = cardNum;
	}

	public String getCardNum() 
	{
		return cardNum;
	}
	public void setOperatorName(String operatorName) 
	{
		this.operatorName = operatorName;
	}

	public String getOperatorName() 
	{
		return operatorName;
	}
	public void setHxzid(String hxzid) 
	{
		this.hxzid = hxzid;
	}

	public String getHxzid() 
	{
		return hxzid;
	}
	public void setMagnification(Double magnification) 
	{
		this.magnification = magnification;
	}

	public Double getMagnification() 
	{
		return magnification;
	}

    @Override
    public String toString() {
        return "SbCraneAddrecord{" +
                "id=" + id +
                ", deviceNo='" + deviceNo + '\'' +
                ", runtime='" + runtime + '\'' +
                ", load=" + load +
                ", loadRatio=" + loadRatio +
                ", moment=" + moment +
                ", momentRatio=" + momentRatio +
                ", slewingSpeed=" + slewingSpeed +
                ", isLeftWarning=" + isLeftWarning +
                ", isRightWarning=" + isRightWarning +
                ", range=" + range +
                ", isForwardWarning=" + isForwardWarning +
                ", isBackwardWarning=" + isBackwardWarning +
                ", height=" + height +
                ", isUpWarning=" + isUpWarning +
                ", workEnvironment=" + workEnvironment +
                ", realTimeFlag=" + realTimeFlag +
                ", installationMethod=" + installationMethod +
                ", windSpeed=" + windSpeed +
                ", cardNum='" + cardNum + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", hxzid='" + hxzid + '\'' +
                ", magnification=" + magnification +
                ", ratedWeight=" + ratedWeight +
                ", obliguity='" + obliguity + '\'' +
                ", dname='" + dname + '\'' +
                ", userid=" + userid +
                '}';
    }
}
