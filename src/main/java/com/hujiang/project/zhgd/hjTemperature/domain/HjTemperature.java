package com.hujiang.project.zhgd.hjTemperature.domain;

/**
 * @ClassName HjTemperature
 * @Description hj_temperature表
 * @Author xieya
 * @Date 2020/3/24  13:24
 */
public class HjTemperature {

    /**id*/
    private Integer id;
    /**体温*/
    private String temperature;
    /**禁止入内的开关，0是关闭1是打开*/
    private Integer enter;
    /**项目id*/
    private String pid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Integer getEnter() {
        return enter;
    }

    public void setEnter(Integer enter) {
        this.enter = enter;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "TemperatureApi{" +
                "id=" + id +
                ", temperature='" + temperature + '\'' +
                ", enter=" + enter +
                ", pid='" + pid + '\'' +
                '}';
    }
}