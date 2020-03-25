package com.hujiang.project.zhgd.personnelTemperature.service;

import com.hujiang.project.zhgd.personnelTemperature.domain.PersonnelTemperature;
import com.hujiang.project.zhgd.personnelTemperature.domain.PersonnelTemperatureReq;

import java.util.List;

public interface IPersonnelTemperatureService {

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/25 10:26
     * @param personnelTemperatureReq
     * @return java.util.List<com.hujiang.project.zhgd.personnelTemperature.domain.PersonnelTemperature>
     **/
    List<PersonnelTemperature> selectPersonnelTemperatureList(PersonnelTemperatureReq personnelTemperatureReq);
}
