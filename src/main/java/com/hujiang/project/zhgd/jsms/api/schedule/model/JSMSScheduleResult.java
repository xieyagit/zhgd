package com.hujiang.project.zhgd.jsms.api.schedule.model;

import cn.jiguang.common.resp.BaseResult;
import com.google.gson.annotations.Expose;

public class JSMSScheduleResult extends BaseResult {

    @Expose String schedule_id;

    public String getScheduleId() {
        return schedule_id;
    }
}
