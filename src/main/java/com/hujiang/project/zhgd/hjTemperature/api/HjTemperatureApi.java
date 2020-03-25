package com.hujiang.project.zhgd.hjTemperature.api;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjTemperature.service.IHjTemperatureApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @ClassName HjTemperatureApi
 * @Description TODO
 * @Author xieya
 * @Date 2020/3/24  14:07
 */
@RestController
@RequestMapping(value = "/provider/temperature", method = RequestMethod.POST)
public class HjTemperatureApi extends BaseController {

    private Logger logger = Logger.getLogger(HjTemperatureApi.class.getName());

    @Autowired
    private IHjTemperatureApiService hjTemperatureApiService;

    @RequestMapping("temperatureList")
    public AjaxResult temperatureList(@RequestParam String pid) {
        return AjaxResult.success(hjTemperatureApiService.temperatureList(pid));
    }

    @RequestMapping("addTemperature")
    public AjaxResult addTemperature(@RequestParam String pid, @RequestParam String temperature) {
        try {
            //先查询是否有数据
            int count = hjTemperatureApiService.selectByPidAndTemperature(pid, temperature);
            if(count == 0){
                hjTemperatureApiService.addTemperature(pid, temperature);
                return AjaxResult.success();
            }
            return AjaxResult.success("数据库已有数据");
        }catch (Exception e){
            logger.info("查询选中的城市存入数据库异常");
            e.printStackTrace();
            return AjaxResult.error(1, "操作异常");
        }
    }

    @RequestMapping("deleteTemperature")
    public AjaxResult deleteTemperature(@RequestParam Integer id) {
        int del = hjTemperatureApiService.deleteTemperature(id);
        if(del == 1){
            return AjaxResult.success();
        }
        if(del == 0){
            return AjaxResult.success("数据库没有数据");
        }
        return AjaxResult.error();
    }

    @RequestMapping("forbidTemperatures")
    public AjaxResult forbidTemperatures(@RequestParam Integer id, @RequestParam String pid, @RequestParam Integer enter) {
        int update = hjTemperatureApiService.forbidTemperatures(id, pid, enter);
        if(update == 1){
            return AjaxResult.success();
        }
        if(update == 0){
            return AjaxResult.success("数据库没有数据");
        }
        return AjaxResult.error();
    }
}