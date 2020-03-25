package com.hujiang.project.zhgd.hjAge.service;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjAge.domain.HjAge;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IHjAgeService {

    /***
     * @Author xieya
     * @Description 查询所有年龄
     * @Date 2020/3/24 11:22
     * @param
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    List<HjAge> limitAgeList(String pid);

    /**
     * @Author xieya
     * @Description 插入年龄
     * @Date 2020/3/24 12:48
     * @param pid
     * @param age
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    int addForbidAge(String pid, Integer age);

    /**
     * @Author xieya
     * @Description 删除年龄
     * @Date 2020/3/24 13:01
     * @param id
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    int deleteForbidAge(@RequestParam Integer id);

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/24 13:04
     * @param id
     * @param pid
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    int forbidAge(Integer id, String pid, Integer enter);

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/24 14:55
     * @param pid
     * @param age
     * @return com.hujiang.project.zhgd.hjAge.domain.HjAge
     **/
    int selectByPidAndAge(String pid, Integer age);
}
