package com.hujiang.project.zhgd.hjTemperature.service;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjTemperature.domain.HjTemperature;
import com.hujiang.project.zhgd.hjTemperature.mapper.HjTemperatureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName HjTemperatureApiServiceImpl
 * @Description TODO
 * @Author xieya
 * @Date 2020/3/24  13:27
 */
@Service
public class HjTemperatureApiServiceImpl implements IHjTemperatureApiService {

    @Autowired
    private HjTemperatureMapper hjTemperatureMapper;

    @Override
    public List<HjTemperature> temperatureList(@RequestParam String pid) {
        return hjTemperatureMapper.temperatureList(pid);
    }

    @Override
    public int addTemperature(String pid, String temperature) {
        //开关默认关闭
        Integer enter = 1;
        return hjTemperatureMapper.addTemperature(pid, temperature, enter);
    }

    @Override
    public int deleteTemperature(@RequestParam Integer id) {
        return hjTemperatureMapper.deleteTemperature(id);
    }

    @Override
    public int forbidTemperatures(@RequestParam Integer id, @RequestParam String pid, @RequestParam Integer enter) {
        return hjTemperatureMapper.forbidTemperatures(id, pid, enter);
    }

    @Override
    public int selectByPidAndTemperature(String pid, String temperature) {
        return hjTemperatureMapper.selectByPidAndTemperature(pid, temperature);
    }
}