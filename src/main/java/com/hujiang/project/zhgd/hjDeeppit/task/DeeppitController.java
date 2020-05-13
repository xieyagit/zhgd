package com.hujiang.project.zhgd.hjDeeppit.task;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.DateUtils;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjDeeppit.domain.HjDeeppitData;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitFactor;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitGroup;
import com.hujiang.project.zhgd.hjDeeppit.model.DataModel;
import com.hujiang.project.zhgd.hjDeeppit.model.FactorModel;
import com.hujiang.project.zhgd.hjDeeppit.model.FactorsModel;
import com.hujiang.project.zhgd.hjDeeppit.model.GroupModel;
import com.hujiang.project.zhgd.hjDeeppit.model.StationModel;
import com.hujiang.project.zhgd.hjDeeppit.model.StationsModel;
import com.hujiang.project.zhgd.hjDeeppit.model.StructuresModel;
import com.hujiang.project.zhgd.hjDeeppit.service.IHjDeeppitDataService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitFactorService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitGroupService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbProjectDeeppitStructuresService;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.RestTemplateHttpUitls;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *@ClassName DeeppitTask
 *@Description 水位  轴力接口对接
 *@Author xieya
 *@Date 2020/5/10  12:27
 */
@RestController
@RequestMapping(value = "/provider/DeeppitJiKeng")
@Component
public class DeeppitController {

    private static final Logger logger = LoggerFactory.getLogger(DeeppitController.class);

    @Autowired
    private IHjDeeppitDataService hjDeeppitDataService;
    @Autowired
    private ISbProjectDeeppitStructuresService sbProjectDeeppitStructuresService;
    @Autowired
    private ISbDeeppitGroupService sbDeeppitGroupService;
    @Autowired
    private ISbDeeppitFactorService sbDeeppitFactorService;

    /**
     * @Author xieya
     * @Description 一个小时执行一次
     * @Date 2020/5/11 15:39
     * @param
     * @return void
     **/
//    @Scheduled(cron = "0 0 * * * ？")
//    public void structuresTask() {
//        structures();
//    }

    /**
     * @Author xieya
     * @Description 获取结构物 下的所有数据插入数据库
     * @Date 2020/5/10 13:07
     * @return com.hujiang.framework.web.domain.AjaxResult
     **/
    @PostMapping("structures")
    public AjaxResult structures() {
        Map paramMap = new HashMap<>();
        String token = null;
//        String token = (String) request.getSession().getAttribute("token");
        if (token == null) {
            String resultToken = RestTemplateHttpUitls.postForJiKeng(Constants.LIFTING_PIT + "/oauth2/token", "基坑对接获取Token");
            JSONObject jsonObject = JSONObject.parseObject(resultToken);
            token = jsonObject.getString("token");
//            request.getSession().setAttribute("token", token);
        }
        logger.info("token=" + token);
        paramMap.put("token", token);
        String result = RestTemplateHttpUitls.getForJiKeng(Constants.LIFTING_PIT + "/structures?token=" + token, paramMap, "获取所有结构物");
        List<StructuresModel> structuresModelList = JSONObject.parseArray(result, StructuresModel.class);

        for (StructuresModel structuresModel : structuresModelList) {
            paramMap.put("display", true);
            String resultFactors = RestTemplateHttpUitls.getForJiKeng(Constants.LIFTING_PIT + "/structures/" + structuresModel.getId() + "/factors?token=" + token, paramMap, "获取结构物下监测因素 ");
            logger.info("resultFactors=" + resultFactors);

            List<FactorsModel> list = new ArrayList<>();
            List<FactorsModel> factorsModelList = JSONObject.parseArray(resultFactors, FactorsModel.class);
            for (FactorsModel factorsModel : factorsModelList) {
                if ("地下水位".equals(factorsModel.getName())) {
                    list.add(factorsModel);
                }
                if ("支撑轴力".equals(factorsModel.getName())) {
                    list.add(factorsModel);
                }
            }

            Map factorParamMap = new HashMap();
            StringBuffer waterLevelSb = new StringBuffer();
            StringBuffer forceSb = new StringBuffer();
            for (FactorsModel factorsModel : list) {
                factorParamMap.put("factorId", factorsModel.getId());
                factorParamMap.put("token", token);
                String resultStations = RestTemplateHttpUitls.getForJiKeng(Constants.LIFTING_PIT + "/structures/" + structuresModel.getId() + "/stations?factorId=" + factorsModel.getId() + "&token=" + token, factorParamMap, "获取结构物下测点 ");
                logger.info("resultStations=" + resultStations);
                List<FactorModel> factorModelList = JSONObject.parseArray(resultStations, FactorModel.class);

                //插入数据库
                insertSbDeeppitGroup(factorsModel, factorModelList);

                for (FactorModel factorModel : factorModelList) {
                    for (GroupModel groupModel : factorModel.getGroups()) {
                        for (StationModel stationModel : groupModel.getStations()) {
                            if ("地下水位".equals(groupModel.getName())) {
                                if (waterLevelSb.length() == 0) {
                                    waterLevelSb.append(stationModel.getId());
                                } else {
                                    waterLevelSb.append("," + stationModel.getId());
                                }
                            }
                            if ("支撑轴力".equals(groupModel.getName())) {
                                if (forceSb.length() == 0) {
                                    forceSb.append(stationModel.getId());
                                } else {
                                    forceSb.append("," + stationModel.getId());
                                }
                            }
                        }
                    }
                }
            }

            String startTime = DateUtils.startDate();
            String endTime = DateUtils.endDate();
            //水位入参
            Map waterLeveMap = setParam(token, waterLevelSb.toString(), startTime, endTime);
            String resultStationList = RestTemplateHttpUitls.getForJiKeng(Constants.LIFTING_PIT + "/stations/theme/data?stations=" + waterLevelSb.toString() + "&startTime=" + startTime + "&endTime=" + endTime + "&limit=" + 10000 + "&token=" + token, waterLeveMap, "获取水位数据");
            logger.info("resultStationList=" + resultStationList);
            //水位数据处理
            dataProcessing(resultStationList, "1");

            //轴力入参
            Map forceMap = setParam(token, forceSb.toString(), startTime, endTime);
            String resultStationList1 = RestTemplateHttpUitls.getForJiKeng(Constants.LIFTING_PIT + "/stations/theme/data?stations=" + forceSb.toString() + "&startTime=" + startTime + "&endTime=" + endTime + "&limit=" + 10000 + "&token=" + token, forceMap, "获取轴力数据");
            logger.info("resultStation=" + resultStationList1);
            //轴力数据处理
            dataProcessing(resultStationList1, "2");

        }

        return AjaxResult.success();
    }

    /**
     * @Author xieya
     * @Description 获取当天数据入参
     * @Date 2020/5/11 11:02
     * @param token
     * @param stringSb
     * @param startTime
     * @param endTime
     * @return java.util.Map
     **/
    private Map setParam(String token, String stringSb, String startTime, String endTime) {
        Map map = new HashMap();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("stations", stringSb);
        map.put("token", token);
        return map;
    }

    /**
     * @Author xieya
     * @Description
     * @Date 2020/5/11 14:06
     * @param factorsModel
     * @param factorModelList
     * @return void
     **/
    private void insertSbDeeppitGroup(FactorsModel factorsModel, List<FactorModel> factorModelList) {
        SbDeeppitGroup sbDeeppitGroup = new SbDeeppitGroup();
        sbDeeppitGroup.setReserved(String.valueOf(factorsModel.getId()));
        List<SbDeeppitGroup> ldpList = sbDeeppitGroupService.selectSbDeeppitGroupList(sbDeeppitGroup);
        if (ldpList == null || ldpList.size() == 0) {
            for (FactorModel factorModel : factorModelList) {
                sbDeeppitGroup.setName(factorModel.getFactorName());
                sbDeeppitGroup.setReserved(String.valueOf(factorsModel.getId()));
                for (GroupModel groupModel : factorModel.getGroups()) {
                    sbDeeppitGroup.setId(groupModel.getId());
                }
                sbDeeppitGroupService.insertSbDeeppitGroup(sbDeeppitGroup);
            }
        }

        for (FactorModel factorModel : factorModelList) {
            for (GroupModel groupModel : factorModel.getGroups()) {
                SbDeeppitFactor sbDeeppitFactor = new SbDeeppitFactor();
                for (StationModel stationModel : groupModel.getStations()) {
                    sbDeeppitFactor.setId(stationModel.getId());
                    List<SbDeeppitFactor> list = sbDeeppitFactorService.selectSbDeeppitFactorList(sbDeeppitFactor);
                    if(list == null || list.size() == 0){
                        sbDeeppitFactor.setReserved(String.valueOf(groupModel.getId()));
                        sbDeeppitFactor.setName(groupModel.getName());
                        sbDeeppitFactorService.insertSbDeeppitFactor(sbDeeppitFactor);
                    }
                }
            }
        }
    }

    private void dataProcessing(String resultStationList, String types) {
        JSONObject jsonObject = JSONObject.parseObject(resultStationList);
        String stationsStr = jsonObject.getString("stations");

        List<StationsModel> stationsModelList = JSONObject.parseArray(stationsStr, StationsModel.class);
        for (StationsModel stationsModel : stationsModelList) {
            List<DataModel> dataModelList = stationsModel.getData();

            //查询当天水位数据
            List<HjDeeppitData> hjDeeppitDataList = hjDeeppitDataService.selectHjDeeppitDataByTime(types);
            for (DataModel dataModel : dataModelList) {
                Integer factorId = stationsModel.getId();
                String time = dataModel.getTime();

                List<HjDeeppitData> hjDeeppitDatas = hjDeeppitDataList.stream().filter(a -> a.getFactorId().equals(factorId) && a.getCreation().equals(time)).collect(Collectors.toList());
                HjDeeppitData hjDeeppitData = new HjDeeppitData();
                hjDeeppitData.setFactorId(factorId);
                hjDeeppitData.setCreation(time);
                //水位
                if ("1".equals(types)) {
                    hjDeeppitData.setWaterLevel(dataModel.getWaterLevel());
                }
                //轴力
                if ("2".equals(types)) {
                    hjDeeppitData.setFactorForce(dataModel.getForce());
                }

                hjDeeppitData.setTypes(types);
                if (hjDeeppitDatas.size() <= 0) {
                    hjDeeppitDataService.insertHjDeeppitData(hjDeeppitData);
                } else {
                    hjDeeppitDataService.updateHjDeeppitData(hjDeeppitData);
                }

            }
        }
    }


    public static void main(String[] args) {

    }
}