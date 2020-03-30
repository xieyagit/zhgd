package com.hujiang.project.zhgd.sbCraneAlarmChangeDataCrane.service;

import com.hujiang.project.zhgd.sbCraneAlarmChangeDataCrane.domain.SbCraneAlarmChangeDataCrane;
import com.hujiang.project.zhgd.sbCraneAlarmChangeDataCrane.mapper.SbCraneAlarmChangeDataCraneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SbCraneAlarmChangeDataCraneServiceImpl
 * @Description TODO
 * @Author xieya
 * @Date 2020/3/27  20:22
 */
@Service
public class SbCraneAlarmChangeDataCraneServiceImpl implements ISbCraneAlarmChangeDataCraneService {

    @Autowired
    private SbCraneAlarmChangeDataCraneMapper sbCraneAlarmChangeDataCraneMapper;

    @Override
    public SbCraneAlarmChangeDataCrane selectByHxzId(String hxzId) {
        return sbCraneAlarmChangeDataCraneMapper.selectByHxzId(hxzId);
    }

    @Override
    public int insertSbCraneAlarmChangeDataCrane(SbCraneAlarmChangeDataCrane sbCraneAlarmChangeDataCrane) {
        return sbCraneAlarmChangeDataCraneMapper.insertSbCraneAlarmChangeDataCrane(sbCraneAlarmChangeDataCrane);
    }

    @Override
    public int updateSbCraneAlarmChangeDataCrane(SbCraneAlarmChangeDataCrane sbCraneAlarmChangeDataCrane) {
        return sbCraneAlarmChangeDataCraneMapper.updateSbCraneAlarmChangeDataCrane(sbCraneAlarmChangeDataCrane);
    }
}