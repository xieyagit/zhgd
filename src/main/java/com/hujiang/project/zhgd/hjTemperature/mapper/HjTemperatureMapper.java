package com.hujiang.project.zhgd.hjTemperature.mapper;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjTemperature.domain.HjTemperature;

import java.util.List;

public interface HjTemperatureMapper {

    /**
     * @Author xieya
     * @Description 查询温度列表
     * @Date 2020/3/24 13:40
     * @param pid
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    List<HjTemperature> temperatureList(String pid);

    /**
     * @Author xieya
     * @Description 增加体温数据
     * @Date 2020/3/24 14:03
     * @param pid
     * @param temperature
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    int addTemperature(String pid, String temperature, Integer enter);

    /**
     * @Author xieya
     * @Description 删除体温数据
     * @Date 2020/3/24 14:04
     * @param id
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    int deleteTemperature(Integer id);

    /**
     * @Author xieya
     * @Description 修改体温状态
     * @Date 2020/3/24 14:04
     * @param id
     * @param pid
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    int forbidTemperatures(Integer id, String pid, Integer enter);

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/24 16:02
     * @param pid
     * @param temperature
     * @return int
     **/
    int selectByPidAndTemperature(String pid, String temperature);
}
