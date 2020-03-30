package com.hujiang.project.zhgd.sbCraneHeart.service;

import com.hujiang.project.zhgd.sbCraneHeart.domain.SbCraneHeart;


public interface ISbCraneHeartService {

    /**
     * @Author xieya
     * @Description 根据id查询数据
     * @Date 2020/3/27 17:40
     * @param hxzId
     * @return com.hujiang.project.zhgd.sbCraneHeart.domain.SbCraneHeart
     **/
    SbCraneHeart selectByHxzId(String hxzId);

    /**
     * @Author xieya
     * @Description 新增数据
     * @Date 2020/3/27 17:45
     * @param sbCraneHeart
     * @return int
     **/
    int insertSbCraneHeart(SbCraneHeart sbCraneHeart);

    /**
     * @Author xieya
     * @Description 跟新数据
     * @Date 2020/3/27 17:45
     * @param sbCraneHeart
     * @return int
     **/
    int updateSbCraneHeart(SbCraneHeart sbCraneHeart);
}
