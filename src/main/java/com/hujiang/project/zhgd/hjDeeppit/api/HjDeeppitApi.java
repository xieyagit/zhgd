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
                                        @RequestParam(value = "factorId") Integer factorId,
                                        @RequestParam(value = "types") Integer types) {
        SbAvg avg = new SbAvg();
        Map<String, String> map = new HashMap<>();
        String avgR;
        String maxR;
        String minR;
        avg.setSubside(displayId.toString());
        avg.setFactorId(factorId);
        if(types == 1){
            avg.setSubside("water_level");
        }else if(types == 2){
            avg.setSubside("factor_force");
        }
        avgR = hjDeeppitDataService.selectParmeterAvg(avg);
        minR = hjDeeppitDataService.selectParmeterMax(avg);
        maxR = hjDeeppitDataService.selectParmeterMin(avg);
        map.put("avg", avgR);
        map.put("min", minR);
        map.put("max", maxR);


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
                              @RequestParam(value = "date") String date,
                              @RequestParam(value = "types") String types) {
        //查询书当天所有数据
        List<HjDeeppitData> deeppitDataList = hjDeeppitDataService.selectToDay(factorId, date, types);

        if("1".equals(types)){
            List list = new ArrayList();
            for(HjDeeppitData hjDeeppitData : deeppitDataList){
                list.add(hjDeeppitData.getWaterLevel());
            }
            return list;
        }

        if("2".equals(types)){
            List list = new ArrayList();
            for(HjDeeppitData hjDeeppitData : deeppitDataList){
                list.add(hjDeeppitData.getFactorForce());
            }
            return list;
        }
        return null;
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

