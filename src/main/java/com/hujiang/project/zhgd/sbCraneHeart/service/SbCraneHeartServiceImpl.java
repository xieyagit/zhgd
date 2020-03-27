package com.hujiang.project.zhgd.sbCraneHeart.service;

import com.hujiang.project.zhgd.sbCraneHeart.domain.SbCraneHeart;
import com.hujiang.project.zhgd.sbCraneHeart.mapper.SbCraneHeartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName SbCraneHeartImpl
 * @Description TODO
 * @Author xieya
 * @Date 2020/3/27  17:38
 */
@Service
public class SbCraneHeartServiceImpl implements ISbCraneHeartService{

    @Autowired
    private SbCraneHeartMapper sbCraneHeartMapper;

    @Override
    public SbCraneHeart selectByHxzId(String hxzId) {
        return sbCraneHeartMapper.selectByHxzId(hxzId);
    }

    @Override
    public int insertSbCraneHeart(SbCraneHeart sbCraneHeart) {
        return sbCraneHeartMapper.insertSbCraneHeart(sbCraneHeart);
    }

    @Override
    public int updateSbCraneHeart(SbCraneHeart sbCraneHeart) {
        return sbCraneHeartMapper.updateSbCraneHeart(sbCraneHeart);
    }
}