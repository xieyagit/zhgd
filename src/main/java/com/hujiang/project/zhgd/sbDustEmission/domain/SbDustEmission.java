package com.hujiang.project.zhgd.sbDustEmission.domain;

import com.hujiang.project.zhgd.dustEmissionThresholdValue.domain.DustEmissionThresholdValue;
import com.hujiang.project.zhgd.dustEmissionThresholdValue.service.IDustEmissionThresholdValueService;
import com.hujiang.project.zhgd.jpush.api.examples.PushExample;
import com.hujiang.project.zhgd.jpush.api.push.model.PushPayload;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * 扬尘数据表 sb_dust_emission
 * 
 * @author hujiang
 * @date 2019-06-18
 */
@Component
public class SbDustEmission extends BaseSmsMessage<SbDustEmission>
{
	private static final long serialVersionUID = 1L;

	//扬尘模块权限
	private static final Integer PRIVILEGESID=26;

	//短信推送标题
	private static final  String TITLE = "TSP检测";

	private static final String ALTER="您有一条新的扬尘超标记录";

	//短信模板ID
	private static final Integer TEMPID = 167973;

	@Autowired
	private IDustEmissionThresholdValueService iDustEmissionThresholdValueService;

	public SbDustEmission() {
		super();
		setPrivilegesId(PRIVILEGESID);
	}
	
	/** id */
	private Long id;
	/** 扬尘设备sn */
	private String sn;
	/** 获取时间 */
	private String date;
	/** PM2.5 扬尘,单位:μg/m3 */
	private BigDecimal pm25;
	/** PM10 扬尘,单位:μg/m3 */
	private BigDecimal pm10;
	/** 扬尘Tsp */
	private BigDecimal tsp;
	/** 噪声,单位:DB */
	private BigDecimal noise;
	/** 温度,单位:℃ */
	private BigDecimal temperature;
	/** 湿度 */
	private BigDecimal humidity;
	/** 风速,单位:km/h */
	private BigDecimal windSpeed;
	/** 风向,0-15 */
	private Long winddirection;
	/** 风力 */
	private BigDecimal windPower;
	/** 气压 */
	private BigDecimal airPressure;

	/**项目监督编号**/
	private String Jdbh;
	/** 项目ID（城安院） */
	private String xmid;
	/** 工程ID */
	private String subId;



	private String site;

	private Integer projectId;

	public String getXmid() {
		return xmid;
	}

	public void setXmid(String xmid) {
		this.xmid = xmid;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public BigDecimal getTsp() {
		return tsp;
	}

	public void setTsp(BigDecimal tsp) {
		this.tsp = tsp;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setSn(String sn) 
	{
		this.sn = sn;
	}

	public String getSn() 
	{
		return sn;
	}
	public void setDate(String date)
	{
		this.date = date;
	}

	public String getDate()
	{
		return date;
	}
	public void setPm25(BigDecimal pm25) 
	{
		this.pm25 = pm25;
	}

	public BigDecimal getPm25() 
	{
		return pm25;
	}
	public void setPm10(BigDecimal pm10) 
	{
		this.pm10 = pm10;
	}

	public BigDecimal getPm10() 
	{
		return pm10;
	}
	public void setNoise(BigDecimal noise) 
	{
		this.noise = noise;
	}

	public BigDecimal getNoise() 
	{
		return noise;
	}
	public void setTemperature(BigDecimal temperature) 
	{
		this.temperature = temperature;
	}

	public BigDecimal getTemperature() 
	{
		return temperature;
	}
	public void setHumidity(BigDecimal humidity) 
	{
		this.humidity = humidity;
	}

	public BigDecimal getHumidity() 
	{
		return humidity;
	}
	public void setWindSpeed(BigDecimal windSpeed) 
	{
		this.windSpeed = windSpeed;
	}

	public BigDecimal getWindSpeed() 
	{
		return windSpeed;
	}
	public void setWinddirection(Long winddirection) 
	{
		this.winddirection = winddirection;
	}

	public Long getWinddirection() 
	{
		return winddirection;
	}
	public void setWindPower(BigDecimal windPower) 
	{
		this.windPower = windPower;
	}

	public BigDecimal getWindPower() 
	{
		return windPower;
	}
	public void setAirPressure(BigDecimal airPressure) 
	{
		this.airPressure = airPressure;
	}

	public BigDecimal getAirPressure() 
	{
		return airPressure;
	}

	public String getJdbh() {
		return Jdbh;
	}

	public void setJdbh(String jdbh) {
		Jdbh = jdbh;
	}

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sn", getSn())
            .append("date", getDate())
            .append("pm25", getPm25())
            .append("pm10", getPm10())
            .append("tsp", getTsp())
            .append("noise", getNoise())
            .append("temperature", getTemperature())
            .append("humidity", getHumidity())
            .append("windSpeed", getWindSpeed())
            .append("winddirection", getWinddirection())
            .append("windPower", getWindPower())
            .append("airPressure", getAirPressure())
            .toString();
    }

	@Override
	public void push(Integer userId, Integer projectId, String alias, boolean isApnsProduction, String userPhone) {
		String saveTitle = null;
		String saveAlert = site + "扬尘检查设备";
		Map<String, String> tempPara = null;
		StringBuffer alertBuffer = new StringBuffer();
		Map<String,String> extras = new HashMap<>();
		extras.put("skip", String.valueOf(PRIVILEGESID));
		extras.put("append","");
		DustEmissionThresholdValue dustEmissionThresholdValue = iDustEmissionThresholdValueService.selectDustEmissionThresholdById(projectId);
		if (dustEmissionThresholdValue != null) {
			Double limitPm25 = dustEmissionThresholdValue.getPm25().doubleValue();
			Double limitPm10 = dustEmissionThresholdValue.getPm10().doubleValue();
			Double limitTsp = dustEmissionThresholdValue.getTsp().doubleValue();
			Double limitNoise = dustEmissionThresholdValue.getNoise().doubleValue();
			Double limitTemperature = dustEmissionThresholdValue.getTemperature().doubleValue();
			Double limitHumidity = dustEmissionThresholdValue.getHumidity().doubleValue();
			Double limitWindSpeed = dustEmissionThresholdValue.getWindSpeed().doubleValue();
			PushExample pushExample = new PushExample();
			if (this.getPm25().doubleValue() >= limitPm25) {
				saveTitle = "PM2.5超标";
				alertBuffer.append("PM2.5超标：超标值：" + limitPm25 + "μg/m3,现已达到："
						+ this.getPm25().doubleValue() + "μg/m3\n");

				//推送
				PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
						(ALTER, alias, TITLE, isApnsProduction,extras);
				pushExample.testSendPush(payload);
				//将推送消息保存到数据库
				this.saveExcessive(saveTitle, this.getSn(), projectId, userId, saveAlert, PRIVILEGESID);
			}
			if (this.getPm10().doubleValue() >= limitPm10) {
				saveTitle = "PM10超标";
				alertBuffer.append("PM10超标：超标值：" + limitPm10 + "μg/m3,现已达到："
						+ this.getPm10().doubleValue() + "μg/m3\n");
				//推送
				PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
						(ALTER, alias, TITLE, isApnsProduction,extras);
				pushExample.testSendPush(payload);
				//将推送消息保存到数据库
				this.saveExcessive(saveTitle, this.getSn(), projectId, userId, saveAlert, PRIVILEGESID);

			}
			if (this.getTsp().doubleValue() >= limitTsp) {
				saveTitle = "TSP超标";
				alertBuffer.append("TSP超标：超标值：" + limitTsp + "μg/m3,现已达到："
						+ this.getTsp().doubleValue() + "μg/m3\n");
				//推送
				PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
						(ALTER, alias, TITLE, isApnsProduction,extras);
				pushExample.testSendPush(payload);
				//将推送消息保存到数据库
				this.saveExcessive(saveTitle, this.getSn(), projectId, userId, saveAlert, PRIVILEGESID);
			}
			if (this.getNoise().doubleValue() >= limitNoise) {

				saveTitle = "噪声超标";
				alertBuffer.append("噪声超标：超标值：" + limitNoise + "DB,现已达到："
						+ this.getNoise().doubleValue() + "DB\n");
				//推送
				PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
						(ALTER, alias, TITLE, isApnsProduction,extras);
				pushExample.testSendPush(payload);
				//将推送消息保存到数据库
				this.saveExcessive(saveTitle, this.getSn(), projectId, userId, saveAlert, PRIVILEGESID);
			}
			if (this.getTemperature().doubleValue() >= limitTemperature) {
				saveTitle = "高温预警";
				alertBuffer.append("高温预警：超标值：" + limitTemperature + "℃,现已达到："
						+ this.getTemperature().doubleValue() + "℃\n");
				//推送
				PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
						(ALTER, alias, TITLE, isApnsProduction,extras);
				pushExample.testSendPush(payload);
				//将推送消息保存到数据库
				this.saveExcessive(saveTitle, this.getSn(), projectId, userId, saveAlert, PRIVILEGESID);
			}
			if (this.getHumidity().doubleValue() >= limitHumidity) {
				saveTitle = "湿度超标";
				alertBuffer.append("湿度超标：超标值：" + limitHumidity + "%RH,现已达到："
						+ this.getHumidity().doubleValue() + "%RH\n");
				//推送
//                PushExample pushExample = new PushExample();
				PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
						(ALTER, alias, TITLE, isApnsProduction,extras);
				pushExample.testSendPush(payload);
				//将推送消息保存到数据库
				this.saveExcessive(saveTitle, this.getSn(), projectId, userId, saveAlert, PRIVILEGESID);
			}
			if (this.getWindSpeed().doubleValue() >= limitWindSpeed) {
				saveTitle = "风速超标";
				alertBuffer.append("风速超标：超标值：" + limitWindSpeed + "km/h,现已达到："
						+ this.getWindSpeed().doubleValue()
						+ "km/h\n");
				//推送
//                PushExample pushExample = new PushExample();
				PushPayload payload = PushExample.buildPushObjectAllRegistrationIdAlertWithTitle
						(ALTER, alias, TITLE, isApnsProduction,extras);
				pushExample.testSendPush(payload);
				//将推送消息保存到数据库
				this.saveExcessive(saveTitle, this.getSn(), projectId, userId, saveAlert, PRIVILEGESID);
			}


			tempPara = new HashMap<>();
			tempPara.put("title", TITLE);
			tempPara.put("site", site);  //站点
			tempPara.put("content", alertBuffer.toString());   //内容
			if(alertBuffer.toString()!=null && !("").equals(alertBuffer.toString())){
				this.shortCreedNumber(userPhone, projectId, userId, tempPara, TEMPID, "扬尘超标");
			}
		}
	}
}
