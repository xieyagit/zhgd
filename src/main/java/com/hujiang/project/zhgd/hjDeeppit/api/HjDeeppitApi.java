package com.hujiang.project.zhgd.hjDeeppit.api;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
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
@RequestMapping(value = "/provider/hjDeeppit",method = RequestMethod.POST)
public class HjDeeppitApi extends BaseController {
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
    public  AjaxResult insertDeeppit(@RequestBody SbProjectDeeppitStructures sPD){
        int i =sbProjectDeeppitStructuresService.insertSbProjectDeeppitStructures(sPD);
        ElectricityDeeppitTask e = new ElectricityDeeppitTask();
        if (i > 0){
            e.getStructures(sPD.getProjectId());
            return toAjax(i);
        }
        return toAjax(0 );
    }
    /**
     * 查询结构物列表
     * @param projectId
     * @return
     */
    @PostMapping(value = "selectStructure")
    public  AjaxResult selectStructure(@RequestParam("projectId")Integer projectId){
        if (projectId == null){
            return AjaxResult.error(0,"参数错误！");
        }
        SbDeeppitStructures dsti = new SbDeeppitStructures();
        dsti.setReservedO(projectId.toString());
        List<SbDeeppitStructures> dst = sbDeeppitStructuresService.selectSbDeeppitStructuresList(dsti);
        List<SbDeeppitStructuresItem> lDST = new ArrayList<>();
        SbDeeppitStructuresItem sDT;
        for(SbDeeppitStructures sD:dst){
            sDT= new SbDeeppitStructuresItem();
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
    @PostMapping(value = "selectDisplay" )
    public AjaxResult selectDisplay(@RequestParam(value = "structureId")Integer structureId){
        if (structureId == null){
            return  AjaxResult.error(0,"参数错误！");
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
    @PostMapping(value = "getFactorList" )
    public AjaxResult getFactorList(@RequestParam(value = "structureId")Integer structureId, @RequestParam(value = "displayId") Integer displayId){
        if (displayId == null){
            return  AjaxResult.error(0,"参数错误！");
        }
        SbDeeppitGroup  dp = new SbDeeppitGroup();
        SbDeeppitFactor df;
        dp.setReserved(displayId.toString());
        List<SbDeeppitGroup> ldp = sbDeeppitGroupService.selectSbDeeppitGroupList(dp);
        JSONArray jdf =new JSONArray();
        for (int i = 0;i < ldp.size();i++){
            df = new SbDeeppitFactor();
            df.setReserved(ldp.get(i).getId().toString());
            List<SbDeeppitFactor> ldf = sbDeeppitFactorService.selectSbDeeppitFactorList(df);
            for (SbDeeppitFactor sf:ldf){
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
    @PostMapping(value = "getFactorData" )
    public JSONObject getFactorData(@RequestParam(value = "factorId") Integer factorId,
                                    @RequestParam(value = "date") String date,
                                    @RequestParam(value = "endTime") String endTime,
                                    PageDomain pageDomain){

        JSONObject jsonObject = new JSONObject();
        if (factorId == null){
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
        }else {
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
    @PostMapping(value = "getParmeterAvg" )
    public AjaxResult getFactorDataInfo(@RequestParam(value = "displayId") Integer displayId,@RequestParam(value = "factorId") Integer factorId){
        SbAvg avg = new SbAvg();
        Map<String,String> map = new HashMap<>();;
        String avgR;
        String maxR;
        String minR;

        String avgS =null;
        String maxS=null;
        String minS=null;

        switch (displayId){
            case 126:
                avg.setSubside(displayId.toString());
                avg.setSubside("subside");
                avg.setFactorId(factorId);
                avgR = hjDeeppitDataService.selectParmeterAvg(avg);
                avgS = avgR.substring(0,avgR.indexOf(".")+3);
                maxR = hjDeeppitDataService.selectParmeterMax(avg);
                maxS = maxR.substring(0,maxR.indexOf(".")+3);
                minR = hjDeeppitDataService.selectParmeterMin(avg);
                minS = minR.substring(0,minR.indexOf(".")+3);
                map.put("avg",avgR);
                map.put("min",minR);
                map.put("max",maxR);
                break;
            case 33:
                avg.setSubside(displayId.toString());
                avg.setSubside("water_level");
                avg.setFactorId(factorId);
                avgR = hjDeeppitDataService.selectParmeterAvg(avg);
                //avgS = avgR.substring(0,avgR.indexOf(".")+3);
                maxR = hjDeeppitDataService.selectParmeterMax(avg);
                //maxS = maxR.substring(0,maxR.indexOf(".")+3);
                minR = hjDeeppitDataService.selectParmeterMin(avg);
                //minS = minR.substring(0,minR.indexOf(".")+3);
                map.put("avg",avgR);
                map.put("min",minR);
                map.put("max",maxR);
                break;

            default:
                map.put("msg","没有这个检测类型");
        }
        return AjaxResult.success(map);
    }

    /**
     * 查询报警数据
     * @param structureId
     * @return
     */
    @PostMapping(value = "selectUserAlarms" )
    public AjaxResult selectUserAlarms(@RequestParam(value = "structureId")Integer structureId,
                                       @RequestParam(value = "date")String date,
                                       @RequestParam(value = "endTime")String endTime,
                                       PageDomain pageDomain){
        AjaxResult jsonObject = new AjaxResult();
        if (structureId == null){
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
        }else {
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
    @PostMapping(value = "statisticsAlertor" )
    public AjaxResult statisticsAlertor(@RequestParam(value = "structureId")Integer structureId){
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
    @PostMapping(value = "getFactorDataT" )
    public AjaxResult getFactorDataT(@RequestParam(value = "factorId") Integer factorId,
                                     @RequestParam(value = "startTime") String startTime,
                                     @RequestParam(value = "endTime")String endTime, PageDomain pageDomain){

        startPage();
        AjaxResult jsonObject = new AjaxResult();
        List<HjDeeppitData> hjDeeppitDataList = hjDeeppitDataService.selectHjDeeppitDataListT(factorId,startTime,endTime);
        TableDataInfo dataTable = getDataTable(hjDeeppitDataList);

        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", hjDeeppitDataList);
            jsonObject.put("sum",hjDeeppitDataService.selectHjDeeppitDataListT(factorId,startTime,endTime).size());
        }else {
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
    @PostMapping(value = "selectSpecial" )
    public List selectSpecial(@RequestParam(value = "factorId")Integer factorId,@RequestParam(value = "param")String param,@RequestParam(value = "date") String date){
        String s1  = hjDeeppitDataService.selectHjDeeppitDataListV(factorId,param,date+" 00:00:01",date+" 04:00:00");
        String s2  = hjDeeppitDataService.selectHjDeeppitDataListV(factorId,param,date+" 04:00:01",date+" 08:00:00");
        String s3  = hjDeeppitDataService.selectHjDeeppitDataListV(factorId,param,date+" 08:00:01",date+" 12:00:00");
        String s4  = hjDeeppitDataService.selectHjDeeppitDataListV(factorId,param,date+" 12:00:01",date+" 16:00:00");
        String s5  = hjDeeppitDataService.selectHjDeeppitDataListV(factorId,param,date+" 16:00:01",date+" 20:00:00");
        String s6  = hjDeeppitDataService.selectHjDeeppitDataListV(factorId,param,date+" 20:00:01",date+" 24:00:00");
        List list = new ArrayList();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);
        return list;
    }


    /**
     * 历史数据图表
     * @param factorId
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping(value = "selectSpecialS" )
    public JSONArray getFactorDataT(@RequestParam(value = "displayId") Integer displayId,@RequestParam(value = "factorId") Integer factorId,@RequestParam(value = "startTime") String startTime, @RequestParam(value = "endTime")String endTime) {

        List<HjDeeppitData> hjDeeppitDataList = hjDeeppitDataService.selectHjDeeppitDataListT(factorId,startTime,endTime);
        JSONArray array = new JSONArray();
        JSONArray array1;
        switch (displayId){
            //沉降
            case 126 :
                for (HjDeeppitData data : hjDeeppitDataList){
                    array1 = new JSONArray();
                    array1.add(data.getCreation());
                    array1.add(data.getSubside());
                    array.add(array1);
                }
                break;
            //地下水位
            case 33 :
                for (HjDeeppitData data : hjDeeppitDataList){
                    array1 = new JSONArray();
                    array1.add(data.getCreation());
                    array1.add(data.getWaterLevel());
                    array.add(array1);
                }
                break;
            //深层水平位移
            case 21 :
                for (HjDeeppitData data : hjDeeppitDataList){
                    array1 = new JSONArray();
                    array1.add(data.getCreation());
                    array1.add(data.getLevelX());
                    array1.add(data.getLevelY());
                    array.add(array1);
                }
                break;
            //应变原始数据
            case 22 :
                for (HjDeeppitData data : hjDeeppitDataList){
                    array1 = new JSONArray();
                    array1.add(data.getCreation());
                    array1.add(data.getStrainFrequency());
                    array1.add(data.getStrainTemperature());
                    array.add(array1);
                }
                break;
            //建筑物倾斜
            case 127 :
                for (HjDeeppitData data : hjDeeppitDataList){
                    array1 = new JSONArray();
                    array1.add(data.getCreation());
                    array1.add(data.getTiltX());
                    array1.add(data.getTiltY());
                    array.add(array1);
                }
                break;
            default:
                array.add("参数错误");
        }

        return array;
    }

    /**
     * 查询报警数据
     * @param factorName
     * @return
     */
    @PostMapping(value = "selectUserAlarmsByFactor" )
    public AjaxResult selectUserAlarmsByFactor(@RequestParam(value = "factorName")String factorName,@RequestParam(value = "date") String date){
        AjaxResult ajaxResult = new AjaxResult();
        if (factorName == null){
            return AjaxResult.error("未添加参数");
        }
        startPage();
        DeeppitAlarmData aData = new DeeppitAlarmData();
        aData.setSourceName(factorName);
        aData.setEndTime(date);
        List<DeeppitAlarmData> lf = deeppitAlarmDataService.selectDeeppitAlarmDataList(aData);
        TableDataInfo dataTable = getDataTable(lf);
        ajaxResult.put("data",dataTable);
        return ajaxResult;
    }
}
