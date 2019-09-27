package com.hujiang.project.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjArea.domain.HjArea;
import com.hujiang.project.zhgd.hjArea.service.IHjAreaService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.utils.Util;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 两制人员统计
 */
@RestController
@RequestMapping(value = "/provider/lz/get", method = RequestMethod.GET)
public class ApiLZController {
    @Autowired
    private IHjProjectService projectService;
    @Autowired
    private IHjAreaService hjAreaService;
    /**
     * 根据项目id获取城市天气
     *
     * @param pid
     * @return
     */
    @RequestMapping("getWeather")
    public JSONObject getWeather(@RequestParam(value = "pid") Integer pid)throws Exception {
        JSONObject jsonObject = new JSONObject();
        //获取项目信息
        HjProject hjProject = projectService.selectHjProjectById(pid);
        //获取项目地址
        String[] split = hjProject.getProjectRegion().split(",");
        HjArea area = hjAreaService.selectHjAreaById(Long.parseLong(split[2]));
        HjArea shi = hjAreaService.selectHjAreaById(Long.parseLong(split[1]));
        //天气
        String weather = Util.sendGet("https://www.tianqiapi.com/api/", "version=v1&appsecret=PDsIqZt7&appid=74243526&city="+area.getTitle().replaceAll("新区","").replaceAll("区","").replaceAll("市","").replaceAll("县",""));
        String weatherShi = Util.sendGet("https://www.tianqiapi.com/api/", "version=v6&appsecret=PDsIqZt7&appid=74243526&city="+area.getTitle().replaceAll("新区","").replaceAll("区","").replaceAll("市","").replaceAll("县",""));
        jsonObject.put("weather", JSONObject.parse(StringEscapeUtils.unescapeJava(weather) ));//天气
        return jsonObject;
    }

}
