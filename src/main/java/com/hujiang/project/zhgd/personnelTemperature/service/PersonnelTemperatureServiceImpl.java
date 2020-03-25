package com.hujiang.project.zhgd.personnelTemperature.service;

import com.hujiang.project.zhgd.hjProjectWorkers.mapper.HjProjectWorkersMapper;
import com.hujiang.project.zhgd.personnelTemperature.domain.PersonnelTemperature;
import com.hujiang.project.zhgd.personnelTemperature.domain.PersonnelTemperatureReq;
import com.hujiang.project.zhgd.personnelTemperature.mapper.PersonnelTemperatureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PersonnelTemperatureServiceImpl
 * @Description TODO
 * @Author xieya
 * @Date 2020/3/25  10:22
 */
@Service
public class PersonnelTemperatureServiceImpl implements IPersonnelTemperatureService {

//    @Autowired
//    private PersonnelTemperatureMapper personnelTemperatureMapper;

    @Override
    public List<PersonnelTemperature> selectPersonnelTemperatureList(PersonnelTemperatureReq personnelTemperatureReq) {
//        return personnelTemperatureMapper.selectPersonnelTemperatureList(personnelTemperatureReq);
        return null;
    }
}