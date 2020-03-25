package com.hujiang.project.zhgd.personnelTemperature.mapper;

import com.hujiang.project.zhgd.personnelTemperature.domain.PersonnelTemperature;
import com.hujiang.project.zhgd.personnelTemperature.domain.PersonnelTemperatureReq;

import java.util.List;

public interface PersonnelTemperatureMapper {

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/25 10:33
     * @param personnelTemperatureReq
     * @return java.util.List<com.hujiang.project.zhgd.personnelTemperature.domain.PersonnelTemperature>
     **/
    List<PersonnelTemperature> selectPersonnelTemperatureList(PersonnelTemperatureReq personnelTemperatureReq);
}
