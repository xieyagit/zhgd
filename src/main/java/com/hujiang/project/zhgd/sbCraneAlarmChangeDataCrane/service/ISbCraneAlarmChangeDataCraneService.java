package com.hujiang.project.zhgd.sbCraneAlarmChangeDataCrane.service;

import com.hujiang.project.zhgd.sbCraneAlarmChangeDataCrane.domain.SbCraneAlarmChangeDataCrane;

public interface ISbCraneAlarmChangeDataCraneService {

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/27 20:24
     * @param hxzId
     * @return com.hujiang.project.zhgd.sbCraneAlarmChangeDataCrane.domain.SbCraneAlarmChangeDataCrane
     **/
    SbCraneAlarmChangeDataCrane selectByHxzId(String hxzId);


    int insertSbCraneAlarmChangeDataCrane(SbCraneAlarmChangeDataCrane sbCraneAlarmChangeDataCrane);


    int updateSbCraneAlarmChangeDataCrane(SbCraneAlarmChangeDataCrane sbCraneAlarmChangeDataCrane);
}
