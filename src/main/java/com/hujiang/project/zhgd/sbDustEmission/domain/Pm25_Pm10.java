package com.hujiang.project.zhgd.sbDustEmission.domain;

import java.math.BigDecimal;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-18 18:22
 **/
public class Pm25_Pm10 {
    /** PM2.5 扬尘,单位:μg/m3 */
    private BigDecimal pm25;
    /** PM10 扬尘,单位:μg/m3 */
    private BigDecimal pm10;
    /** 噪声,单位:DB */
    private BigDecimal noise;

    public BigDecimal getPm100() {
        return pm100;
    }

    public void setPm100(BigDecimal pm100) {
        this.pm100 = pm100;
    }

    private BigDecimal pm100;

    public BigDecimal getNoise() {
        return noise;
    }

    public void setNoise(BigDecimal noise) {
        this.noise = noise;
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

    @Override
    public String toString() {
        return "Pm25_Pm10{" +
                "pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", noise=" + noise +
                '}';
    }
}
