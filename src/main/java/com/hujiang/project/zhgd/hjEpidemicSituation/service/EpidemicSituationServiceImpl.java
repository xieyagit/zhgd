package com.hujiang.project.zhgd.hjEpidemicSituation.service;

import com.hujiang.project.zhgd.hjArea.domain.HjArea;
import com.hujiang.project.zhgd.hjEpidemicSituation.domain.EpidemicSituation;
import com.hujiang.project.zhgd.hjEpidemicSituation.mapper.HjEpidemicSituationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName EpidemicSituationServiceImpl
 * @Description
 * @Author xieya
 * @Date 2020/3/23  18:15
 */
@Service
@Transactional
public class EpidemicSituationServiceImpl implements IEpidemicSituationService {

    @Autowired
    private HjEpidemicSituationMapper hjEpidemicSituationMapper;

    @Override
    public int insertEpidemicSituation(EpidemicSituation epidemicSituation) {
        return hjEpidemicSituationMapper.insertEpidemicSituation(epidemicSituation);
    }

    @Override
    public EpidemicSituation selectEpidemicSituationByIdAndPid(Integer areaId, String pid) {
        return hjEpidemicSituationMapper.selectEpidemicSituationByIdAndPid(areaId, pid);
    }

    @Override
    public int deleteAreaSetting(Integer areaId, String pid) {
        return hjEpidemicSituationMapper.deleteAreaSetting(areaId, pid);
    }

    @Override
    public int forbidAreaSetting(Integer areaId, String pid, Integer enter) {
        return hjEpidemicSituationMapper.forbidAreaSetting(areaId, pid, enter);
    }

    @Override
    public List<EpidemicSituation> areaSettingList(String pid) {
        return hjEpidemicSituationMapper.selectEpidemicSituationByPid(pid);
    }
}