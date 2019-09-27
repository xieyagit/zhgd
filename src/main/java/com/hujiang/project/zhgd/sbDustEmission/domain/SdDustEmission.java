package com.hujiang.project.zhgd.sbDustEmission.domain;

import java.math.BigDecimal;
//山东扬尘对接
public class SdDustEmission {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getPm25() {
        return pm25;
    }

    public void setPm25(BigDecimal pm25) {
        this.pm25 = pm25;
    }

    public BigDecimal getPm10() {
        return pm10;
    }

    public void setPm10(BigDecimal pm10) {
        this.pm10 = pm10;
    }

    public BigDecimal getTsp() {
        return tsp;
    }

    public void setTsp(BigDecimal tsp) {
        this.tsp = tsp;
    }

    public BigDecimal getNoise() {
        return noise;
    }

    public void setNoise(BigDecimal noise) {
        this.noise = noise;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Long getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(Long winddirection) {
        this.winddirection = winddirection;
    }

    public BigDecimal getWindPower() {
        return windPower;
    }

    public void setWindPower(BigDecimal windPower) {
        this.windPower = windPower;
    }

    public BigDecimal getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(BigDecimal airPressure) {
        this.airPressure = airPressure;
    }

    @Override
    public String toString() {
        return "SdDustEmission{" +
                "id=" + id +
                ", sn='" + sn + '\'' +
                ", date='" + date + '\'' +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", tsp=" + tsp +
                ", noise=" + noise +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", winddirection=" + winddirection +
                ", windPower=" + windPower +
                ", airPressure=" + airPressure +
                '}';
    }
}
