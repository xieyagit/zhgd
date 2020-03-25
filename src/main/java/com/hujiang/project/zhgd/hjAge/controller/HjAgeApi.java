package com.hujiang.project.zhgd.hjAge.controller;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjAge.domain.HjAge;
import com.hujiang.project.zhgd.hjAge.service.IHjAgeService;
import com.hujiang.project.zhgd.hjArea.api.AreaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * @ClassName HjAgeApi
 * @Description
 * @Author xieya
 * @Date 2020/3/24  11:16
 */
@RestController
@RequestMapping(value = "/provider/age",method = RequestMethod.POST)
public class HjAgeApi extends BaseController {

    private Logger logger = Logger.getLogger(HjAgeApi.class.getName());

    @Autowired
    private IHjAgeService hjAgeService;

    /**
     * @Author xieya
     * @Description 查询所有年龄
     * @Date 2020/3/24 11:21
     * @param
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping("limitAgeList")
    public AjaxResult limitAgeList(String pid){
        List<HjAge> list = hjAgeService.limitAgeList(pid);
        return AjaxResult.success(list);
    }

    /**
     * @Author xieya
     * @Description 插入年龄
     * @Date 2020/3/24 12:56
     * @param pid
     * @param age
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping("addForbidAge")
    public AjaxResult addForbidAge(String pid, Integer age){
        try {
            //先查询是否有数据
            int count = hjAgeService.selectByPidAndAge(pid, age);
            if(count == 0){
                hjAgeService.addForbidAge(pid, age);
                return AjaxResult.success();
            }
            return AjaxResult.success("数据库已有数据");
        }catch (Exception e){
            logger.info("查询选中的城市存入数据库异常");
            e.printStackTrace();
            return AjaxResult.error(1, "操作异常");
        }

    }

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/24 13:03
     * @param id
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping("deleteForbidAge")
    public AjaxResult deleteForbidAge(@RequestParam Integer id){
        int del = hjAgeService.deleteForbidAge(id);
        if(del == 1){
            return AjaxResult.success();
        }
        if(del == 0){
            return AjaxResult.success("数据库没有数据");
        }
        return AjaxResult.error();
    }

    /**
     * @Author xieya
     * @Description 把年龄状态改成禁止
     * @Date 2020/3/24 13:03
     * @param id
     * @param pid
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping("forbidAge")
    public AjaxResult forbidAge(@RequestParam Integer id, @RequestParam String pid, @RequestParam Integer enter){
        int update = hjAgeService.forbidAge(id, pid, enter);
        System.out.println(update);
        if(update == 1){
            return AjaxResult.success();
        }
        if(update == 0){
            return AjaxResult.success("数据库没有数据");
        }
        return AjaxResult.error();
    }
}