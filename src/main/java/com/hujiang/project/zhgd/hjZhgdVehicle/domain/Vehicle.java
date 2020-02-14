package com.hujiang.project.zhgd.hjZhgdVehicle.domain;

import java.util.Date;


/**
 * 车牌数据表 hujiang_vehicle
 *
 * @author hujiang
 * @date 2019-06-19
 */
public class Vehicle
{
	private static final long serialVersionUID = 1L;

	/** id */
	private Integer id;
	/** 记录唯一标识 */
	private String recordId;
	/** 车牌 */
	private String vehicleNo;
	/** 车牌颜色 默认 1 */
	private String plateColor;
	/** 项目id（智慧工地的项目id） */
	private Integer deptID;
	/** 摄像头编号 */
	private String cameraMac;
	/** 抬杆方式 1-自动抬杠 2-手动抬杆 */
	private String liftType;
	/** 抬杆时间 */
	private String liftTime;
	private String endTime;
	/** 进出方向 1-进 2-出 */
	private Integer inOut;
	/** 图片 */
	private String img;
	/** 0-临时车 1-月租车 */
	private Integer cardType;
	/** 车牌颜色 */
	private String color;
	/** 订单id */
	private String orderId;
	/**驾驶员姓名*/
	private String name;
	/** 操作员姓名*/
	private String operatorin;
	/** 车辆类型 0、小车，1、大车*/
	private Integer carType;
	private String carTypedesc;
	/*模糊查询驾驶员与车牌*/
	private String all;
	/** 通道名称*/
	private String gateinname;
	/**外表*/
	private String projectname;
	/**图片*/
	private String imgUrl;

	private Integer count;	//进出总数
	private Integer days; //天数
	private Integer hours;//小时钟
	private Integer minutes; //分钟数
	private Integer ihour; //获取多少个小时前的数据
	private Integer total; //总条数
	private Integer pkcount; //剩余车位

	private String sn;//设备sn

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getCarTypedesc() {
		return carTypedesc;
	}

	public void setCarTypedesc(String carTypedesc) {
		this.carTypedesc = carTypedesc;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}

	public Integer getPkcount() {
		return pkcount;
	}

	public void setPkcount(Integer pkcount) {
		this.pkcount = pkcount;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getIhour() {
		return ihour;
	}

	public void setIhour(Integer ihour) {
		this.ihour = ihour;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getOperatorin() {
		return operatorin;
	}

	public void setOperatorin(String operatorin) {
		this.operatorin = operatorin;
	}

	public String getGateinname() {
		return gateinname;
	}

	public void setGateinname(String gateinname) {
		this.gateinname = gateinname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}

	public Integer getDeptID() {
		return deptID;
	}

	public void setDeptID(Integer deptID) {
		this.deptID = deptID;
	}

	public String getCameraMac() {
		return cameraMac;
	}

	public void setCameraMac(String cameraMac) {
		this.cameraMac = cameraMac;
	}

	public String getLiftType() {
		return liftType;
	}

	public void setLiftType(String liftType) {
		this.liftType = liftType;
	}

	public String getLiftTime() {
		return liftTime;
	}

	public void setLiftTime(String liftTime) {
		this.liftTime = liftTime;
	}

	public Integer getInOut() {
		return inOut;
	}

	public void setInOut(Integer inOut) {
		this.inOut = inOut;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "Vehicle{" +
				"id=" + id +
				", recordId='" + recordId + '\'' +
				", vehicleNo='" + vehicleNo + '\'' +
				", plateColor='" + plateColor + '\'' +
				", deptID=" + deptID +
				", cameraMac='" + cameraMac + '\'' +
				", liftType='" + liftType + '\'' +
				", liftTime=" + liftTime +
				", inOut=" + inOut +
				", img='" + img + '\'' +
				", cardType=" + cardType +
				", color='" + color + '\'' +
				", orderId='" + orderId + '\'' +
				", name='" + name + '\'' +
				", operatorin='" + operatorin + '\'' +
				", all='" + all + '\'' +
				", gateinname='" + gateinname + '\'' +
				", projectname='" + projectname + '\'' +
				", imgUrl='" + imgUrl + '\'' +
				", count=" + count +
				", days=" + days +
				", hours=" + hours +
				", minutes=" + minutes +
				", ihour=" + ihour +
				", total=" + total +
				'}';
	}
}
