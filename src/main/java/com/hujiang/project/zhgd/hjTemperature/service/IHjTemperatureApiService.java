package com.hujiang.project.zhgd.hjTemperature.service;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjTemperature.domain.HjTemperature;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IHjTemperatureApiService {

    /**
     * @Author xieya
     * @Description 查询体温列表
     * @Date 2020/3/24 13:30
     * @param pid
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    List<HjTemperature> temperatureList(@RequestParam String pid);

    /**
     * @Author xieya
     * @Description 
     * @Date 2020/3/24 15:55
     * @param pid
     * @param temperature
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    int addTemperature(String pid, String temperature);

    /**
     * @Author xieya
     * @Description 
     * @Date 2020/3/24 15:55
     * @param id
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    int deleteTemperature(@RequestParam Integer id);

    /**
     * @Author xieya
     * @Description 
     * @Date 2020/3/24 15:58
     * @param id
     * @param pid
     * @return int
     **/
    int forbidTemperatures(@RequestParam Integer id, @RequestParam String pid, @RequestParam Integer enter);

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/24 16:00
     * @param pid
     * @param temperature
     * @return int
     **/
    int selectByPidAndTemperature(String pid, String temperature);
}
