package com.hujiang.project.zhgd.hjEpidemicSituation.service;

import com.hujiang.project.zhgd.hjArea.domain.HjArea;
import com.hujiang.project.zhgd.hjEpidemicSituation.domain.EpidemicSituation;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IEpidemicSituationService {

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/23 18:50
     * @param epidemicSituation
     * @return int
     **/
    int insertEpidemicSituation(EpidemicSituation epidemicSituation);

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/23 20:13
     * @param areaId
     * @param pid
     * @return com.hujiang.project.zhgd.hjEpidemicSituation.domain.EpidemicSituation
     **/
    EpidemicSituation selectEpidemicSituationByIdAndPid(Integer areaId, String pid);

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/24 17:06
     * @param id
     * @param pid
     * @return int
     **/
    int deleteAreaSetting(Integer id, String pid);

    /**
     * @Author xieya
     * @Description
     * @Date 2020/3/24 17:36
     * @param id
     * @param pid
     * @return int
     **/
    int forbidAreaSetting(Integer id, String pid, Integer enter);

    /**
     * @Author xieya
     * @Description 根据项目id查询疫情城市
     * @Date 2020/3/25 12:07
     * @param pid
     * @return java.util.List<com.hujiang.project.zhgd.hjArea.domain.HjArea>
     **/
    List<EpidemicSituation> areaSettingList(String pid);
}
