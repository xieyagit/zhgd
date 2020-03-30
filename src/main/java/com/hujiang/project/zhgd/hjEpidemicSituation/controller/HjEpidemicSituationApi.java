package com.hujiang.project.zhgd.hjEpidemicSituation.controller;

import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjArea.domain.HjArea;
import com.hujiang.project.zhgd.hjArea.service.IHjAreaService;
import com.hujiang.project.zhgd.hjEpidemicSituation.domain.EpidemicSituation;
import com.hujiang.project.zhgd.hjEpidemicSituation.service.IEpidemicSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * @ClassName EpidemicSituationApi
 * @Description 疫情城市
 * @Author xieya
 * @Date 2020/3/23  18:09
 */
@RestController
@RequestMapping(value = "/provider/hjarea")
public class HjEpidemicSituationApi {

    private Logger logger = Logger.getLogger(HjEpidemicSituationApi.class.getName());

    @Autowired
    private IHjAreaService hjAreaService;
    @Autowired
    private IEpidemicSituationService epidemicSituationService;

    /**
     * @Author xieya
     * @Description 查询所有省和市
     * @Date 2020/3/25 13:39
     * @param
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping("/selectAllProvinceAndCity")
    public AjaxResult selectAllProvinceAndCity() {
        logger.info("开始查询所有省和市");
        List<HjArea> list = hjAreaService.selectAllProvinceAndCity();
        logger.info("省和市查询结果list=" + list);
        return AjaxResult.success(list);
    }

    /**
     * @Author xieya
     * @Description 查询所有疫情城市
     * @Date 2020/3/24 16:56
     * @param
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping("/areaSettingList")
    public AjaxResult areaSettingList(@RequestParam String pid) {
        logger.info("开始查询所有省和市");
        List<EpidemicSituation> list = epidemicSituationService.areaSettingList(pid);
        logger.info("省和市查询结果list=" + list);
        return AjaxResult.success(list);
    }

    /**
     * @Author xieya
     * @Description 新增数据
     * @Date 2020/3/23 20:08
     * @param ids
     * @param pid
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping("/addAreaSetting")
    public AjaxResult addAreaSetting(@RequestParam String ids, @RequestParam String pid) {
        String[] strs = ids.split(",");
        logger.info("查询选中的城市存入数据库");

        try {
            //根据ids查询城市数据
            List<HjArea> list = hjAreaService.selectProvinceAndCityByIds(strs);
            logger.info("省和市查询结果list=" + list);

            for (HjArea hjArea : list) {
                EpidemicSituation epidemicSituation = new EpidemicSituation();
                epidemicSituation.setAreaId(hjArea.getId().intValue());
                epidemicSituation.setParentId(hjArea.getParentId().intValue());
                epidemicSituation.setTitle(hjArea.getTitle());
                epidemicSituation.setType(hjArea.getType());
                epidemicSituation.setPid(pid);
                //默认关闭
                epidemicSituation.setEnter(1);

                EpidemicSituation epidemicSituation1 = epidemicSituationService.selectEpidemicSituationByIdAndPid(epidemicSituation.getAreaId(), pid);
                if(epidemicSituation1 == null){
                    epidemicSituationService.insertEpidemicSituation(epidemicSituation);
                }
            }
            return AjaxResult.success();
        }catch (Exception e){
            logger.info("查询选中的城市存入数据库异常");
            e.printStackTrace();
            return AjaxResult.error(1, "操作异常");
        }
    }

    /**
     * @Author xieya
     * @Description 删除数据
     * @Date 2020/3/25 13:41
     * @param id
     * @param pid
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping("deleteAreaSetting")
    public AjaxResult deleteAreaSetting(@RequestParam Integer id, @RequestParam String pid){
        int del = epidemicSituationService.deleteAreaSetting(id, pid);
        if(del == 1){
            return AjaxResult.success();
        }
        if(del == 0){
            return AjaxResult.success("数据库没有数据");
        }
        return AjaxResult.error();
    }

    /**
     * @Author xieya
     * @Description 修改状态
     * @Date 2020/3/25 13:41
     * @param id
     * @param pid
     * @param enter
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @RequestMapping("forbidAreaSetting")
    public AjaxResult forbidAreaSetting(@RequestParam Integer id, @RequestParam String pid, @RequestParam Integer enter){
        int update = epidemicSituationService.forbidAreaSetting(id, pid, enter);
        System.out.println(update);
        if(update == 1){
            return AjaxResult.success();
        }
        if(update == 0){
            return AjaxResult.success("数据库没有数据");
        }
        return AjaxResult.error();
    }

}