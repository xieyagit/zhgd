package com.hujiang.project.zhgd.hjAge.service;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjAge.domain.HjAge;
import com.hujiang.project.zhgd.hjAge.mapper.HjAgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName HjAgeServiceImpl
 * @Description
 * @Author xieya
 * @Date 2020/3/24  11:18
 */
@Service
public class HjAgeServiceImpl implements IHjAgeService {

    @Autowired
    private HjAgeMapper hjAgeMapper;

    @Override
    public List<HjAge> limitAgeList(String pid) {
        return hjAgeMapper.limitAgeList(pid);
    }

    @Override
    public int selectByPidAndAge(String pid, Integer age) {
        return hjAgeMapper.selectByPidAndAge(pid, age);
    }

    @Override
    public int addForbidAge(String pid, Integer age) {
        //开关  默认关闭
        Integer enter = 1;
        return hjAgeMapper.addForbidAge(pid, age, enter);
    }

    @Override
    public int deleteForbidAge(@RequestParam Integer id) {
        return hjAgeMapper.deleteForbidAge(id);
    }

    @Override
    public int forbidAge(Integer id, String pid, Integer enter) {
        return hjAgeMapper.forbidAge(id, pid, enter);
    }

}