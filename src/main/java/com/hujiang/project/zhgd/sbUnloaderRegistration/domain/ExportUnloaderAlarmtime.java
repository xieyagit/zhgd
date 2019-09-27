package com.hujiang.project.zhgd.sbUnloaderRegistration.domain;

import com.hujiang.framework.aspectj.lang.annotation.Excel;

public class ExportUnloaderAlarmtime {
    /** 报警值：载重：0.00~2.00t，倾角X：-9.99°~9.99°，倾角Y：-9.99°~9.99°，电池电量：0~100 */
    @Excel(name = "报警值")
    private Float alarmValue;
    /** 报警开始时间 */
    @Excel(name = "时间")
    private String startTime;
    public Float getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(Float alarmValue) {
        this.alarmValue = alarmValue;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
