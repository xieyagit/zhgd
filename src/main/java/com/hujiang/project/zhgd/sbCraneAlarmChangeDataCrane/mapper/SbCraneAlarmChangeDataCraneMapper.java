package com.hujiang.project.zhgd.sbCraneAlarmChangeDataCrane.mapper;

import com.hujiang.project.zhgd.sbCraneAlarmChangeDataCrane.domain.SbCraneAlarmChangeDataCrane;

public interface SbCraneAlarmChangeDataCraneMapper {

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/27 20:26
     * @param hxzId
     * @return com.hujiang.project.zhgd.sbCraneAlarmChangeDataCrane.domain.SbCraneAlarmChangeDataCrane
     **/
    SbCraneAlarmChangeDataCrane selectByHxzId(String hxzId);

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/27 21:11
     * @param sbCraneAlarmChangeDataCrane
     * @return int
     **/
    int insertSbCraneAlarmChangeDataCrane(SbCraneAlarmChangeDataCrane sbCraneAlarmChangeDataCrane);

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/27 21:11
     * @param sbCraneAlarmChangeDataCrane
     * @return int
     **/
    int updateSbCraneAlarmChangeDataCrane(SbCraneAlarmChangeDataCrane sbCraneAlarmChangeDataCrane);
}
