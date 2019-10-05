package com.hujiang.project.zhgd.hjghformwork.api;

import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjDeeppit.domain.*;
import com.hujiang.project.zhgd.hjDeeppit.service.*;
import com.hujiang.project.zhgd.hjghformwork.domain.*;
import com.hujiang.project.zhgd.hjghformwork.service.*;
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
@RequestMapping(value = "/provider/HjGhformworktApi",method = RequestMethod.POST)
public class HjGhformworktApi extends BaseController {

    @Autowired
    ISbDeeppitStructuresService sbDeeppitStructuresService;
    @Autowired
    ISbProjectDeeppitStructuresService sbProjectDeeppitStructuresService;
    @Autowired
    IHighformworkDisplayService highformworkDisplayService;
    @Autowired
    IHighformworkGroupService highformworkGroupService;
    @Autowired
    IHighformworkFactorService highformworkFactorService;
    @Autowired
    IHighformworkDataService highformworkDataService;
    @Autowired
    IHighformworkAlarmDataService highformworkAlarmDataService;


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
        HighformworkDisplay dd = new HighformworkDisplay();
        dd.setReserved(structureId.toString());
        List<HighformworkDisplay> ldd = highformworkDisplayService.selectHighformworkDisplayList(dd);

        return AjaxResult.success(ldd);
    }

    /**
     * 查询所有的监测点
     * @return
     */
    @PostMapping(value = "getFactorList" )
    public AjaxResult getFactorList(@RequestParam(value = "structureId")Integer structureId,@RequestParam(value = "displayId") Integer displayId){
        AjaxResult ajaxResult = new AjaxResult();
        if (displayId == null){
            return  AjaxResult.error(0,"参数错误！");
        }

        if (displayId == null){
            return  AjaxResult.error(0,"参数错误！");
        }
        HighformworkGroup  dp = new HighformworkGroup();
        HighformworkFactor df;
        dp.setReserved(displayId.toString());
        List<HighformworkGroup> ldp = highformworkGroupService.selectHighformworkGroupList(dp);
        if (ldp == null){
            ajaxResult.put("msg","参数错误！");
            ajaxResult.put("code",1);
            return ajaxResult;
        }
        JSONArray jdf =new JSONArray();
        for (int i = 0;i < ldp.size();i++){
            df = new HighformworkFactor();
            df.setReserved(ldp.get(i).getId().toString());
            List<HighformworkFactor> ldf = highformworkFactorService.selectHighformworkFactorList(df);
            if (ldf == null){
                ajaxResult.put("msg","参数错误！");
                ajaxResult.put("code",1);
                return ajaxResult;
            }
            for (HighformworkFactor sf:ldf){
                sf.setCreateTime(new Date());
                sf.setUpdateTime(new Date());
                jdf.add(sf);
            }
            ajaxResult.put("code",0);
            ajaxResult.put("data",jdf);
        }

        return ajaxResult;
    }

    /**
     * 查询测点历史数据
     * @param factorId
     * @return
     */
    @PostMapping(value = "getFactorData" )
    public AjaxResult getFactorData(@RequestParam(value = "factorId") Integer factorId,@RequestParam(value = "date") String date, PageDomain pageDomain){
        AjaxResult jb = new AjaxResult();
        if (factorId == null){
            jb.put("msg", "参数不能为空");
        }
        startPage();
        HighformworkData hjDeeppitData = new HighformworkData();
        hjDeeppitData.setFactorId(factorId);
        hjDeeppitData.setCreation(date);

        List<HighformworkData> hjDeeppitDataList = highformworkDataService.selectHighformworkDataList(hjDeeppitData);
        TableDataInfo dataTable = getDataTable(hjDeeppitDataList);

        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
       if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            jb.put("msg", "查询成功");
            jb.put("code", 0);
            jb.put("data", hjDeeppitDataList);
       }else {
            jb.put("msg", "查询成功");
            jb.put("code", 0);
            jb.put("data", Collections.emptyList());
        }
        return jb;
    }

    /**
     * 查询报警数据
     * @param structureId
     * @return
     */
    @PostMapping(value = "selectUserAlarms" )
    public AjaxResult selectUserAlarms(@RequestParam(value = "structureId")Integer structureId,@RequestParam(value = "date") String date, PageDomain pageDomain){
        AjaxResult ajaxResult = new AjaxResult();
        if (structureId == null){
            return AjaxResult.error("未添加参数");
        }

        AjaxResult jsonObject = new AjaxResult();
        if (structureId == null){
            jsonObject.put("msg", "参数不能为空");
        }
        startPage();
        HighformworkAlarmData aData = new HighformworkAlarmData();
        aData.setStructuresId(structureId);
        aData.setEndTime(date);
        List<HighformworkAlarmData> lf = highformworkAlarmDataService.selectHighformworkAlarmDataList(aData);
        TableDataInfo dataTable = getDataTable(lf);
        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("sum", highformworkAlarmDataService.selectHighformworkAlarmDataList(aData).size());
            jsonObject.put("data", lf);
        }else {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 1);
            jsonObject.put("data", Collections.emptyList());
        }
        return jsonObject;

    }


    /**
     * 按时间段查询历史数据
     * @param factorId
     * @param startTime
     * @param endTime
     * @return
     */
    @PostMapping(value = "getFactorDataT" )
    public AjaxResult getFactorDataT(@RequestParam(value = "factorId") Integer factorId,@RequestParam(value = "startTime") String startTime, @RequestParam(value = "endTime")String endTime, PageDomain pageDomain){

        AjaxResult jsonObject = new AjaxResult();
        List<HighformworkData> highformworkData = highformworkDataService.selectHighformworkDataListT(factorId,startTime,endTime);
        TableDataInfo dataTable = getDataTable(highformworkData);

        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", highformworkData);
            jsonObject.put("sum",highformworkData.size());
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
            case 14:
                avg.setSubside(displayId.toString());
                avg.setSubside("subside");
                avg.setFactorId(factorId);
                avgR = highformworkDataService.selectParmeterAvg(avg);
                //avgS = avgR.substring(0,avgR.indexOf(".")+3);
                maxR = highformworkDataService.selectParmeterMax(avg);
                //maxS = maxR.substring(0,maxR.indexOf(".")+3);
                minR = highformworkDataService.selectParmeterMin(avg);
                //minS = minR.substring(0,minR.indexOf(".")+3);
                map.put("avg",avgR);
                map.put("min",minR);
                map.put("max",maxR);
                break;
            case 13:
                avg.setSubside(displayId.toString());
                avg.setSubside("displacement");
                avg.setFactorId(factorId);
                avgR = highformworkDataService.selectParmeterAvg(avg);
                //avgS = avgR.substring(0,avgR.indexOf(".")+3);
                maxR = highformworkDataService.selectParmeterMax(avg);
                //maxS = maxR.substring(0,maxR.indexOf(".")+3);
                minR = highformworkDataService.selectParmeterMin(avg);
                //minS = minR.substring(0,minR.indexOf(".")+3);
                map.put("avg",avgR);
                map.put("min",minR);
                map.put("max",maxR);
                break;
            case 12:
                avg.setSubside(displayId.toString());
                avg.setSubside("force_r");
                avg.setFactorId(factorId);
                avgR = highformworkDataService.selectParmeterAvg(avg);
                //avgS = avgR.substring(0,avgR.indexOf(".")+3);
                maxR = highformworkDataService.selectParmeterMax(avg);
                //maxS = maxR.substring(0,maxR.indexOf(".")+3);
                minR = highformworkDataService.selectParmeterMin(avg);
                //minS = minR.substring(0,minR.indexOf(".")+3);
                map.put("avg",avgR);
                map.put("min",minR);
                map.put("max",maxR);
                break;
            default:
        }
        return AjaxResult.success(map);
    }

    /**
     * 当日数据表特殊点
     * @param factorId
     * @return
     */
    @PostMapping(value = "selectSpecial" )
    public List selectSpecial(@RequestParam(value = "factorId")Integer factorId,@RequestParam(value = "param")String param,@RequestParam(value = "date") String date){
        String s1  = highformworkDataService.selectHighformworkDataListV(factorId,param,date+" 00:00:01",date+" 04:00:00");
        String s2  = highformworkDataService.selectHighformworkDataListV(factorId,param,date+" 04:00:01",date+" 08:00:00");
        String s3  = highformworkDataService.selectHighformworkDataListV(factorId,param,date+" 08:00:01",date+" 12:00:00");
        String s4  = highformworkDataService.selectHighformworkDataListV(factorId,param,date+" 12:00:01",date+" 16:00:00");
        String s5  = highformworkDataService.selectHighformworkDataListV(factorId,param,date+" 16:00:01",date+" 20:00:00");
        String s6  = highformworkDataService.selectHighformworkDataListV(factorId,param,date+" 20:00:01",date+" 24:00:00");
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

        List<HighformworkData> highformworkData = highformworkDataService.selectHighformworkDataListT(factorId,startTime,endTime);
        JSONArray array = new JSONArray();
        JSONArray array1;
        for (HighformworkData data : highformworkData){
            array1 = new JSONArray();
            switch (displayId){
                case 14:
                    array1.add(data.getCreation());
                    array1.add(data.getSubside());
                    array.add(array1);
                    break;
                case 13:
                    array1.add(data.getCreation());
                    array1.add(data.getDisplacement());
                    array.add(array1);
                    break;
                case 12:
                    array1.add(data.getCreation());
                    array1.add(data.getForce());
                    array.add(array1);
                    break;
                case 15:
                    array1.add(data.getCreation());
                    array1.add(data.getTiltX());
                    array1.add(data.getTiltY());
                    array.add(array1);
                    break;
                default:
            }
        }

        return array;
    }

    /**
     * 报警数据统计等级比例
     * @param structureId
     * @return
     */
    @PostMapping(value = "statisticsAlertor" )
    public AjaxResult statisticsAlertor(@RequestParam(value = "structureId")Integer structureId){
        List<StatisticsAlertor> list = highformworkAlarmDataService.statisticsAlertor(structureId);
        return AjaxResult.success(list);
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
        HighformworkAlarmData aData = new HighformworkAlarmData();
        aData.setSourceName(factorName);
        aData.setEndTime(date);
        List<HighformworkAlarmData> lf = highformworkAlarmDataService.selectHighformworkAlarmDataList(aData);
        TableDataInfo dataTable = getDataTable(lf);
        ajaxResult.put("data",dataTable);
        return ajaxResult;
    }

}
