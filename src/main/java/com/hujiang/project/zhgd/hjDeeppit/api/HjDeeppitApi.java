package com.hujiang.project.zhgd.hjDeeppit.api;

import com.hujiang.common.utils.DateUtils;
import com.hujiang.common.utils.StringUtils;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjDeeppit.domain.DeeppitAlarmData;
import com.hujiang.project.zhgd.hjDeeppit.domain.HjDeeppitData;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbAvg;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitDisplay;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitFactor;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitGroup;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitStructures;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbDeeppitStructuresItem;
import com.hujiang.project.zhgd.hjDeeppit.domain.SbProjectDeeppitStructures;
import com.hujiang.project.zhgd.hjDeeppit.domain.StatisticsAlertor;
import com.hujiang.project.zhgd.hjDeeppit.service.IDeeppitAlarmDataService;
import com.hujiang.project.zhgd.hjDeeppit.service.IHjDeeppitDataService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitDisplayService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitFactorService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitGroupService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitStructuresService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbDeeppitTokenService;
import com.hujiang.project.zhgd.hjDeeppit.service.ISbProjectDeeppitStructuresService;
import com.hujiang.project.zhgd.hjDeeppit.task.ElectricityDeeppitTask;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hujiang.project.zhgd.hjDeeppit.domain.*;
import com.hujiang.project.zhgd.hjDeeppit.service.*;
import com.hujiang.project.zhgd.hjDeeppit.task.ElectricityDeeppitTask;
import com.hujiang.project.zhgd.hjghformwork.domain.HighformworkAlarmData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @program: 基坑api
 * @description:
 * @author: tony
 * @create:
 **/
@RestController
@RequestMapping(value = "/provider/hjDeeppit", method = RequestMethod.POST)
public class HjDeeppitApi extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(HjDeeppitApi.class);

    String url = "https://api.zhiwucloud.com/api/v1";

    @Autowired
    ISbDeeppitTokenService sbDeeppitTokenService;
    @Autowired
    ISbDeeppitStructuresService sbDeeppitStructuresService;
    @Autowired
    ISbDeeppitDisplayService sbDeeppitDisplayService;
    @Autowired
    IHjDeeppitDataService hjDeeppitDataService;
    @Autowired
    ISbDeeppitFactorService sbDeeppitFactorService;
    @Autowired
    ISbDeeppitGroupService sbDeeppitGroupService;
    @Autowired
    ISbProjectDeeppitStructuresService sbProjectDeeppitStructuresService;
    @Autowired
    private IDeeppitAlarmDataService deeppitAlarmDataService;

    /**
     * 关联项目
     * @param sPD
     * @return
     */
    @PostMapping(value = "insertDeeppit")
    public AjaxResult insertDeeppit(@RequestBody SbProjectDeeppitStructures sPD) {
        int i = sbProjectDeeppitStructuresService.insertSbProjectDeeppitStructures(sPD);
        ElectricityDeeppitTask e = new ElectricityDeeppitTask();
        if (i > 0) {
            e.getStructures(sPD.getProjectId());
            return toAjax(i);
        }
        return toAjax(0);
    }

    /**
     * 查询结构物列表
     * @param projectId
     * @return
     */
    @PostMapping(value = "selectStructure")
    public AjaxResult selectStructure(@RequestParam("projectId") Integer projectId) {
        if (projectId == null) {
            return AjaxResult.error(0, "参数错误！");
        }
        SbDeeppitStructures dsti = new SbDeeppitStructures();
        dsti.setReservedO(projectId.toString());
        List<SbDeeppitStructures> dst = sbDeeppitStructuresService.selectSbDeeppitStructuresList(dsti);
        List<SbDeeppitStructuresItem> lDST = new ArrayList<>();
        SbDeeppitStructuresItem sDT;
        for (SbDeeppitStructures sD : dst) {
            sDT = new SbDeeppitStructuresItem();
            sDT.setDeviceId(sD.getId());
            sDT.setDeviceName(sD.getName());
            lDST.add(sDT);
        }
        return AjaxResult.success(lDST);
    }

    /**
     * 查询所有的因素列表
     * @return
     */
    @PostMapping(value = "selectDisplay")
    public AjaxResult selectDisplay(@RequestParam(value = "structureId") Integer structureId) {
        if (structureId == null) {
            return AjaxResult.error(0, "参数错误！");
        }
        SbDeeppitDisplay dd = new SbDeeppitDisplay();
        dd.setReserved(structureId.toString());
        List<SbDeeppitDisplay> ldd = sbDeeppitDisplayService.selectSbDeeppitDisplayList(dd);

        return AjaxResult.success(ldd);
    }

    /**
     * 查询所有的监测点
     * @return
     */
    @PostMapping(value = "getFactorList")
    public AjaxResult getFactorList(@RequestParam(value = "structureId") Integer structureId, @RequestParam(value = "displayId") Integer displayId) {
        if (displayId == null) {
            return AjaxResult.error(0, "参数错误！");
        }
        SbDeeppitGroup dp = new SbDeeppitGroup();
        SbDeeppitFactor df;
        dp.setReserved(displayId.toString());
        List<SbDeeppitGroup> ldp = sbDeeppitGroupService.selectSbDeeppitGroupList(dp);
        JSONArray jdf = new JSONArray();
        for (int i = 0; i < ldp.size(); i++) {
            df = new SbDeeppitFactor();
            df.setReserved(ldp.get(i).getId().toString());
            List<SbDeeppitFactor> ldf = sbDeeppitFactorService.selectSbDeeppitFactorList(df);
            for (SbDeeppitFactor sf : ldf) {
                jdf.add(sf);
            }
        }
        return AjaxResult.success(jdf);
    }

    /**
     * 查询测点历史数据
     * @param factorId
     * @return
     */
    @PostMapping(value = "getFactorData")
    public JSONObject getFactorData(@RequestParam(value = "factorId") Integer factorId,
                                    @RequestParam(value = "date") String date,
                                    @RequestParam(value = "endTime") String endTime,
                                    PageDomain pageDomain) {

        JSONObject jsonObject = new JSONObject();
        if (factorId == null) {
            jsonObject.put("msg", "参数不能为空");
        }
        startPage();
        HjDeeppitData hjDeeppitData = new HjDeeppitData();
        hjDeeppitData.setFactorId(factorId);
        hjDeeppitData.setCreation(date);
        hjDeeppitData.setEndTime(endTime);

        List<HjDeeppitData> hjDeeppitDataList = hjDeeppitDataService.selectHjDeeppitDataList(hjDeeppitData);
        TableDataInfo dataTable = getDataTable(hjDeeppitDataList);

        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", hjDeeppitDataList);
        } else {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }

    /**
     * 查询历史数据最大、最小、平均值
     * @param displayId
     * @return
     */
    @PostMapping(value = "getParmeterAvg")
    public AjaxResult getFactorDataInfo(@RequestParam(value = "displayId") Integer displayId,
                                        @RequestParam(value = "factorId") Integer factorId) {
        SbAvg avg = new SbAvg();
        Map<String, String> map = new HashMap<>();
        String avgR;
        String maxR;
        String minR;
        avg.setSubside(displayId.toString());
        avg.setSubside("water_level");
        avg.setFactorId(factorId);
        avgR = hjDeeppitDataService.selectParmeterAvg(avg);
        minR = hjDeeppitDataService.selectParmeterMax(avg);
        maxR = hjDeeppitDataService.selectParmeterMin(avg);
        map.put("avg", avgR);
        map.put("min", minR);
        map.put("max", maxR);



//        switch (displayId){
//            case 126:
//                avg.setSubside(displayId.toString());
//                avg.setSubside("subside");
//                avg.setFactorId(factorId);
//                avgR = hjDeeppitDataService.selectParmeterAvg(avg);
//                avgS = avgR.substring(0,avgR.indexOf(".")+3);
//                maxR = hjDeeppitDataService.selectParmeterMax(avg);
//                maxS = maxR.substring(0,maxR.indexOf(".")+3);
//                minR = hjDeeppitDataService.selectParmeterMin(avg);
//                minS = minR.substring(0,minR.indexOf(".")+3);
//                map.put("avg",avgR);
//                map.put("min",minR);
//                map.put("max",maxR);
//                break;
//            case 33:
//                avg.setSubside(displayId.toString());
//                avg.setSubside("water_level");
//                avg.setFactorId(factorId);
//                avgR = hjDeeppitDataService.selectParmeterAvg(avg);
//                //avgS = avgR.substring(0,avgR.indexOf(".")+3);
//                maxR = hjDeeppitDataService.selectParmeterMax(avg);
//                //maxS = maxR.substring(0,maxR.indexOf(".")+3);
//                minR = hjDeeppitDataService.selectParmeterMin(avg);
//                //minS = minR.substring(0,minR.indexOf(".")+3);
//                map.put("avg",avgR);
//                map.put("min",minR);
//                map.put("max",maxR);
//                break;
//
//            default:
//                map.put("msg","没有这个检测类型");
//        }
        return AjaxResult.success(map);
    }

    /**
     * 查询报警数据
     * @param structureId
     * @return
     */
    @PostMapping(value = "selectUserAlarms")
    public AjaxResult selectUserAlarms(@RequestParam(value = "structureId") Integer structureId,
                                       @RequestParam(value = "date") String date,
                                       @RequestParam(value = "endTime") String endTime,
                                       PageDomain pageDomain) {
        AjaxResult jsonObject = new AjaxResult();
        if (structureId == null) {
            jsonObject.put("msg", "参数不能为空");
        }
        startPage();
        DeeppitAlarmData deeppitAlarmData = new DeeppitAlarmData();
        deeppitAlarmData.setStructuresId(structureId);
        deeppitAlarmData.setEndTime(date);
        deeppitAlarmData.setEndTime2(endTime);
        List<DeeppitAlarmData> hjDeeppitDataList = deeppitAlarmDataService.selectDeeppitAlarmDataList(deeppitAlarmData);
        TableDataInfo dataTable = getDataTable(hjDeeppitDataList);
        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("sum", deeppitAlarmDataService.selectDeeppitAlarmDataList(deeppitAlarmData).size());
            jsonObject.put("data", hjDeeppitDataList);
        } else {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }

    /**
     * 报警数据统计等级比例
     * @param structureId
     * @return
     */
    @PostMapping(value = "statisticsAlertor")
    public AjaxResult statisticsAlertor(@RequestParam(value = "structureId") Integer structureId) {
        List<StatisticsAlertor> list = deeppitAlarmDataService.statisticsAlertor(structureId);
        return AjaxResult.success(list);
    }

    /**
     * 按时间段查询历史数据
     * @param factorId
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping(value = "getFactorDataT")
    public AjaxResult getFactorDataT(@RequestParam(value = "factorId") Integer factorId, @RequestParam(value = "startTime") String startTime, @RequestParam(value = "endTime") String endTime, PageDomain pageDomain) {

        startPage();
        AjaxResult jsonObject = new AjaxResult();
        List<HjDeeppitData> hjDeeppitDataList = hjDeeppitDataService.selectHjDeeppitDataListT(factorId, startTime, endTime);
        TableDataInfo dataTable = getDataTable(hjDeeppitDataList);

        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", hjDeeppitDataList);
            jsonObject.put("sum", hjDeeppitDataService.selectHjDeeppitDataListT(factorId, startTime, endTime).size());
        } else {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;
    }


    /**
     * 当日数据表特殊点
     * @param factorId
     * @return
     */
    @PostMapping(value = "selectSpecial")
    public List selectSpecial(@RequestParam(value = "factorId") Integer factorId,
                              @RequestParam(value = "param") String param,
                              @RequestParam(value = "date") String date) {
        //查询书当天所有数据
        List<HjDeeppitData> list = hjDeeppitDataService.selectToDay(factorId, param, date);
        logger.info("list=", list);
        return retuenObj(list);
    }

    private List<Object> retuenObj(List<HjDeeppitData> list){
        double sum1 = 0;double sum2 = 0;double sum3 = 0;double sum4 = 0;double sum5 = 0;double sum6 = 0;double sum7 = 0;double sum8 = 0;double sum9 = 0;double sum10 = 0;double sum11 = 0;double sum12 = 0;
        double sum13 = 0;double sum14 = 0;double sum15 = 0;double sum16 = 0;double sum17 = 0;double sum18 = 0;double sum19 = 0;double sum20 = 0;double sum21 = 0;double sum22 = 0;double sum23 = 0;double sum24 = 0;
//        double sum25 = 0;double sum26 = 0;double sum27 = 0;double sum28 = 0;double sum29 = 0;double sum30 = 0;double sum31 = 0;double sum32 = 0;double sum33 = 0;double sum34 = 0;double sum35 = 0;double sum36 = 0;
//        double sum37 = 0;double sum38 = 0;double sum39 = 0;double sum40 = 0;double sum41 = 0;double sum42 = 0;double sum43 = 0;double sum44 = 0;double sum45 = 0;double sum46 = 0;double sum47 = 0;double sum48 = 0;
        int index1 = 0;int index2 = 0;int index3 = 0;int index4 = 0;int index5 = 0;int index6 = 0;int index7 = 0;int index8 = 0;int index9 = 0;int index10 = 0;int index11 = 0;int index12 = 0;
        int index13 = 0;int index14 = 0;int index15 = 0;int index16 = 0;int index17 = 0;int index18 = 0;int index19 = 0;int index20 = 0;int index21 = 0;int index22 = 0;int index23 = 0;int index24 = 0;
//        int index25 = 0;int index26 = 0;int index27 = 0;int index28 = 0;int index29 = 0;int index30 = 0;int index31 = 0;int index32 = 0;int index33 = 0;int index34 = 0;int index35 = 0;int index36 = 0;
//        int index37 = 0;int index38 = 0;int index39 = 0;int index40 = 0;int index41 = 0;int index42 = 0;int index43 = 0;int index44 = 0;int index45 = 0;int index46 = 0;int index47 = 0;int index48 = 0;
        for(HjDeeppitData hjDeeppitData :list){
            if(hjDeeppitData.getWaterLevel() != null){
                //判断在哪一个区间
                int index = DateUtils.index(hjDeeppitData.getCreation());
                switch(index){
                    case 0 :
                        index1++;
                        sum1 = sum1 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 1 :
                        index2++;
                        sum2 = sum2 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 2 :
                        index3++;
                        sum3 = sum3 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 3 :
                        index4++;
                        sum4 = sum4 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 4 :
                        index5++;
                        sum5 = sum5 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 5 :
                        index6++;
                        sum6 = sum6 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 6 :
                        index7++;
                        sum7 = sum7 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 7 :
                        index8++;
                        sum8 = sum8 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 8 :
                        index9++;
                        sum9 = sum9 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 9 :
                        index10++;
                        sum10 = sum10 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 10 :
                        index11++;
                        sum11 = sum11 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 11 :
                        index12++;
                        sum12 = sum12 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 12 :
                        index13++;
                        sum13 = sum13 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 13 :
                        index14++;
                        sum14 = sum14 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 14 :
                        index15++;
                        sum15 = sum15 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 15 :
                        index16++;
                        sum16 = sum16 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 16 :
                        index17++;
                        sum17 = sum17 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 17 :
                        index18++;
                        sum18 = sum18 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 18 :
                        index19++;
                        sum19 = sum19 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 19 :
                        index20++;
                        sum20 = sum20 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 20 :
                        index21++;
                        sum21 = sum21 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 21 :
                        index22++;
                        sum22 = sum22 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 22 :
                        index23++;
                        sum23 = sum23 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
                    case 23 :
                        index24++;
                        sum24 = sum24 + Double.valueOf(hjDeeppitData.getWaterLevel());
                        break;
//                    case 24 :
//                        index25++;
//                        sum25 = sum25 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 25 :
//                        index26++;
//                        sum26 = sum26 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 26 :
//                        index27++;
//                        sum27 = sum27 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 27 :
//                        index28++;
//                        sum28 = sum28 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 28 :
//                        index29++;
//                        sum29 = sum29 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 29 :
//                        index30++;
//                        sum30 = sum30 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 30 :
//                        index31++;
//                        sum31 = sum31 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 31 :
//                        index32++;
//                        sum32 = sum32 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 32 :
//                        index33++;
//                        sum33 = sum33 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 33 :
//                        index34++;
//                        sum34 = sum34 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 34 :
//                        index35++;
//                        sum35 = sum35 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 35 :
//                        index36++;
//                        sum36 = sum36 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 36 :
//                        index37++;
//                        sum37 = sum37 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 37 :
//                        index38++;
//                        sum38 = sum38 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 38 :
//                        index39++;
//                        sum39 = sum39 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 39 :
//                        index40++;
//                        sum40 = sum40 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 40 :
//                        index41++;
//                        sum41 = sum41 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 41 :
//                        index42++;
//                        sum42 = sum42 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 42 :
//                        index43++;
//                        sum43 = sum43 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 43 :
//                        index44++;
//                        sum44 = sum44 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 44 :
//                        index45++;
//                        sum45 = sum45 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 45 :
//                        index46++;
//                        sum46 = sum46 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 46 :
//                        index47++;
//                        sum47 = sum47 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
//                    case 47 :
//                        index48++;
//                        sum48 = sum48 + Double.valueOf(hjDeeppitData.getWaterLevel());
//                        break;
                    default :

                }

            }
        }
        List<Object> returnList = new ArrayList<>();
        returnList.add(index1 != 0 ? sum1/index1 : null);
        returnList.add(index2 != 0 ? sum2/index2 : null);
        returnList.add(index3 != 0 ? sum3/index3 : null);
        returnList.add(index4 != 0 ? sum4/index4 : null);
        returnList.add(index5 != 0 ? sum5/index5 : null);
        returnList.add(index6 != 0 ? sum6/index6 : null);
        returnList.add(index7 != 0 ? sum7/index7 : null);
        returnList.add(index8 != 0 ? sum8/index8 : null);
        returnList.add(index9 != 0 ? sum9/index9 : null);
        returnList.add(index10 != 0 ? sum10/index10 : null);
        returnList.add(index11 != 0 ? sum11/index11 : null);
        returnList.add(index12 != 0 ? sum12/index12 : null);
        returnList.add(index13 != 0 ? sum13/index13 : null);
        returnList.add(index14 != 0 ? sum14/index14 : null);
        returnList.add(index15 != 0 ? sum15/index15 : null);
        returnList.add(index16 != 0 ? sum16/index16 : null);
        returnList.add(index17 != 0 ? sum17/index17 : null);
        returnList.add(index18 != 0 ? sum18/index18 : null);
        returnList.add(index19 != 0 ? sum19/index19 : null);
        returnList.add(index20 != 0 ? sum20/index20 : null);
        returnList.add(index21 != 0 ? sum21/index21 : null);
        returnList.add(index22 != 0 ? sum22/index22 : null);
        returnList.add(index23 != 0 ? sum23/index23 : null);
        returnList.add(index24 != 0 ? sum24/index24 : null);
//        returnList.add(index25 != 0 ? sum25/index25 : null);
//        returnList.add(index26 != 0 ? sum26/index26 : null);
//        returnList.add(index27 != 0 ? sum27/index27 : null);
//        returnList.add(index28 != 0 ? sum28/index28 : null);
//        returnList.add(index29 != 0 ? sum29/index29 : null);
//        returnList.add(index30 != 0 ? sum30/index30 : null);
//        returnList.add(index31 != 0 ? sum31/index31 : null);
//        returnList.add(index32 != 0 ? sum32/index32 : null);
//        returnList.add(index33 != 0 ? sum33/index33 : null);
//        returnList.add(index34 != 0 ? sum34/index34 : null);
//        returnList.add(index35 != 0 ? sum35/index35 : null);
//        returnList.add(index36 != 0 ? sum36/index36 : null);
//        returnList.add(index37 != 0 ? sum37/index37 : null);
//        returnList.add(index38 != 0 ? sum38/index38 : null);
//        returnList.add(index39 != 0 ? sum39/index39 : null);
//        returnList.add(index40 != 0 ? sum40/index40 : null);
//        returnList.add(index41 != 0 ? sum41/index41 : null);
//        returnList.add(index42 != 0 ? sum42/index42 : null);
//        returnList.add(index43 != 0 ? sum43/index43 : null);
//        returnList.add(index44 != 0 ? sum44/index44 : null);
//        returnList.add(index45 != 0 ? sum45/index45 : null);
//        returnList.add(index46 != 0 ? sum46/index46 : null);
//        returnList.add(index47 != 0 ? sum47/index47 : null);
//        returnList.add(index48 != 0 ? sum48/index48 : null);


        return returnList;
    }


    /**
     * 历史数据图表
     * @param factorId
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping(value = "selectSpecialS")
    public JSONArray getFactorDataT(@RequestParam(value = "displayId") Integer displayId,
                                    @RequestParam(value = "factorId") Integer factorId,
                                    @RequestParam(value = "startTime") String startTime,
                                    @RequestParam(value = "endTime") String endTime) {

        List<HjDeeppitData> hjDeeppitDataList = hjDeeppitDataService.selectHjDeeppitDataListT(factorId, startTime, endTime);
        JSONArray array = new JSONArray();
        JSONArray array1;
        for (HjDeeppitData data : hjDeeppitDataList) {
            array1 = new JSONArray();
            array1.add(data.getCreation());
            if (data.getSubside() != null && data.getSubside() != "") {
                array1.add(data.getSubside());
            }
            if (data.getWaterLevel() != null && data.getWaterLevel() != "") {
                array1.add(data.getWaterLevel());
            }
            if (data.getLevelX() != null && data.getLevelX() != "") {
                array1.add(data.getLevelX());
            }
            if (data.getLevelY() != null && data.getLevelY() != "") {
                array1.add(data.getLevelY());
            }
            if (data.getStrainFrequency() != null && data.getStrainFrequency() != "") {
                array1.add(data.getStrainFrequency());
            }
            if (data.getStrainTemperature() != null && data.getStrainTemperature() != "") {
                array1.add(data.getStrainTemperature());
            }
            if (data.getTiltX() != null && data.getTiltX() != "") {
                array1.add(data.getTiltX());
            }
            if (data.getTiltY() != null && data.getTiltY() != "") {
                array1.add(data.getTiltY());
            }
            array.add(array1);
        }
//        switch (displayId){
//            //沉降
//            case 126 :
//                for (HjDeeppitData data : hjDeeppitDataList){
//                    array1 = new JSONArray();
//                    array1.add(data.getCreation());
//                    array1.add(data.getSubside());
//                    array.add(array1);
//                }
//                break;
//            //地下水位
//            case 33 :
//                for (HjDeeppitData data : hjDeeppitDataList){
//                    array1 = new JSONArray();
//                    array1.add(data.getCreation());
//                    array1.add(data.getWaterLevel());
//                    array.add(array1);
//                }
//                break;
//            //深层水平位移
//            case 21 :
//                for (HjDeeppitData data : hjDeeppitDataList){
//                    array1 = new JSONArray();
//                    array1.add(data.getCreation());
//                    array1.add(data.getLevelX());
//                    array1.add(data.getLevelY());
//                    array.add(array1);
//                }
//                break;
//            //应变原始数据
//            case 22 :
//                for (HjDeeppitData data : hjDeeppitDataList){
//                    array1 = new JSONArray();
//                    array1.add(data.getCreation());
//                    array1.add(data.getStrainFrequency());
//                    array1.add(data.getStrainTemperature());
//                    array.add(array1);
//                }
//                break;
//            //建筑物倾斜
//            case 127 :
//                for (HjDeeppitData data : hjDeeppitDataList){
//                    array1 = new JSONArray();
//                    array1.add(data.getCreation());
//                    array1.add(data.getTiltX());
//                    array1.add(data.getTiltY());
//                    array.add(array1);
//                }
//                break;
//            default:
//                array.add("参数错误");
//        }

        return array;
    }

    /**
     * 查询报警数据
     * @param factorName
     * @return
     */
    @PostMapping(value = "selectUserAlarmsByFactor")
    public AjaxResult selectUserAlarmsByFactor(@RequestParam(value = "factorName") String factorName, @RequestParam(value = "date") String date) {
        AjaxResult ajaxResult = new AjaxResult();
        if (factorName == null) {
            return AjaxResult.error("未添加参数");
        }
        startPage();
        DeeppitAlarmData aData = new DeeppitAlarmData();
        aData.setSourceName(factorName);
        aData.setEndTime(date);
        List<DeeppitAlarmData> lf = deeppitAlarmDataService.selectDeeppitAlarmDataList(aData);
        TableDataInfo dataTable = getDataTable(lf);
        ajaxResult.put("data", dataTable);
        return ajaxResult;
    }
}

