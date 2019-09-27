package com.hujiang.project.zhgd.jishijiaoDate.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.jishijiaoDate.domain.JishijiaoDate;
import com.hujiang.project.zhgd.jishijiaoDate.service.IJishijiaoDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 极视角数据接收
 */
@RestController
@RequestMapping(value = "/provider/jishijiaoDate",method = RequestMethod.POST)
public class JishijiaoDateApi extends BaseController{

    @Autowired
    IJishijiaoDateService jishijiaoDateService;

    @PostMapping("insert")
    public AjaxResult insertData(@RequestBody JSONObject body) {
        if (body.equals(null)){
            return AjaxResult.error("空数据");
        }
        if (body.get("status").equals(0)){
            return AjaxResult.success();
        }
        JishijiaoDate jDate;
        jDate = new JishijiaoDate();
        jDate.setAid(body.getString("aid"));
        jDate.setCid(body.getString("cid"));
        jDate.setCidUrl(body.getString("cid_url"));
        jDate.setTimeStamp(body.getString("time_stamp"));
        jDate.setSrcpicData(body.getString("srcpic_data"));
        jDate.setSrcpicName(body.getString("srcpic_name"));
        jDate.setPicData(body.getString("pic_data"));
        jDate.setPicName(body.getString("pic_name"));
        jDate.setStatus(body.getInteger("status"));

        JSONObject data = body.getJSONObject("data");
        jDate.setTimeStampE(data.getString("timeStamp"));
        jDate.setNumOfHead(data.getInteger("numOfHead"));
        jDate.setAlertFlag(data.getInteger("alertFlag"));
        jDate.setHeadInfo(data.getString("headInfo"));

        return toAjax(jishijiaoDateService.insertJishijiaoDate(jDate));
    }

}

