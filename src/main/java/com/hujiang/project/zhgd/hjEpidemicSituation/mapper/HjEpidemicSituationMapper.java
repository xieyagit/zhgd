package com.hujiang.project.zhgd.hjEpidemicSituation.mapper;

import com.hujiang.project.zhgd.hjArea.domain.HjArea;
import com.hujiang.project.zhgd.hjEpidemicSituation.domain.EpidemicSituation;

import java.util.List;

/**
 * @Author xieya
 * @Description
 * @Date 2020/3/23 18:43
 * @return
 **/
public interface HjEpidemicSituationMapper {

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/23 18:49
     * @param epidemicSituation
     * @return void
     **/
    int insertEpidemicSituation(EpidemicSituation epidemicSituation);

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/23 20:15
     * @param areaId
     * @param pid
     * @return com.hujiang.project.zhgd.hjEpidemicSituation.domain.EpidemicSituation
     **/
    EpidemicSituation selectEpidemicSituationByIdAndPid(Integer areaId, String pid);

    int deleteAreaSetting(Integer areaId, String pid);

    int forbidAreaSetting(Integer areaId, String pid, Integer enter);

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/25 12:08
     * @param pid
     * @return java.util.List<com.hujiang.project.zhgd.hjArea.domain.HjArea>
     **/
    List<EpidemicSituation> selectEpidemicSituationByPid(String pid);
}
