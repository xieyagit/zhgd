package com.hujiang.project.zhgd.sbEquipmentWarning.api;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.sbEquipmentWarning.domain.SbEquipmentWarning;
import com.hujiang.project.zhgd.sbEquipmentWarning.service.ISbEquipmentWarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/provider/pcEquipmentWarning",method = RequestMethod.POST)
public class PcEquipmentWarning extends BaseController {

    @Autowired
    private ISbEquipmentWarningService equipmentWarningService;
    @PostMapping("/warningCount")
    public JSONObject warningCount(@RequestParam(value = "projectId") Integer projectId){
        JSONObject jsonObject = new JSONObject();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        String format = sp.format(new Date());  //获取当天信息
        SbEquipmentWarning sos = new SbEquipmentWarning();
        sos.setWarningType(0);
        sos.setWarningTime(format);
        sos.setProjectId(projectId);
        SbEquipmentWarning fall = new SbEquipmentWarning();
        fall.setWarningType(1);
        fall.setWarningTime(format);
        fall.setProjectId(projectId);
        SbEquipmentWarning move = new SbEquipmentWarning();
        move.setWarningType(2);
        move.setWarningTime(format);
        move.setProjectId(projectId);
        SbEquipmentWarning bat = new SbEquipmentWarning();
        bat.setWarningType(3);
        bat.setWarningTime(format);
        bat.setProjectId(projectId);
        int countSos = equipmentWarningService.warningCount(sos);
        int countFall = equipmentWarningService.warningCount(fall);
        int countMove = equipmentWarningService.warningCount(move);
        int countBat = equipmentWarningService.warningCount(bat);
        Map map = new HashMap();
        map.put("countSos",countSos);
        map.put("countFall",countFall);
        map.put("countMove",countMove);
        map.put("countBat",countBat);

        jsonObject.put("msg","查询成功");
        jsonObject.put("code",0);
        jsonObject.put("data",map);
        return jsonObject;
    }
    @PostMapping("/getWarningList")
    public JSONObject getWarningList(@RequestParam(value = "userName",required = false)String userName,
                                     @RequestParam(value = "userId",required = false)Integer userId,
                                     @RequestParam(value = "areaName",required = false)String areaName,
                                     @RequestParam(value = "areaId",required =  false)Integer areaId,
                                     @RequestParam(value = "projectId",required = false)Integer projectId,
                                     @RequestParam(value = "startTime",required = false)String startTime,
                                     @RequestParam(value = "endTime",required = false)String endTime,
                                     @RequestParam(value = "warningType",required = false)Integer warningType,
                                     PageDomain pageDomain){
        JSONObject jsonObject = new JSONObject();
        SbEquipmentWarning sbEquipmentWarning = new SbEquipmentWarning();
        sbEquipmentWarning.setUserName(userName);
        sbEquipmentWarning.setUserId(userId);
        sbEquipmentWarning.setAreaName(areaName);
        sbEquipmentWarning.setAreaId(areaId);
        sbEquipmentWarning.setProjectId(projectId);
        sbEquipmentWarning.setStartTime(startTime);
        sbEquipmentWarning.setEndTime(endTime);
        sbEquipmentWarning.setWarningType(warningType);
        startPage();
        List<SbEquipmentWarning> equipmentWarningList = equipmentWarningService.getWarningList(sbEquipmentWarning);
        TableDataInfo dataTable = getDataTable(equipmentWarningList);
        List<SbEquipmentWarning> rows = (List<SbEquipmentWarning>)dataTable.getRows();
        int count = Integer.parseInt(String.valueOf(dataTable.getTotal()));
        if(pageDomain.getPageNum()!=null && pageDomain.getPageSize()!=null) {
            if ((pageDomain.getPageNum()) <= Math.ceil(count / (double) pageDomain.getPageSize())) {
                if (rows != null && rows.size() > 0) {
                    if (equipmentWarningList != null && equipmentWarningList.size() > 0) {
                        jsonObject.put("msg", "查询成功");
                        jsonObject.put("code", 0);
                        jsonObject.put("total",dataTable.getTotal());//总记录数
                        jsonObject.put("data", rows);
                    }
                }
            } else {
                jsonObject.put("msg", "查询成功");
                jsonObject.put("code", 0);
                jsonObject.put("total", 0);//总记录数
                jsonObject.put("data", Collections.emptyList());
            }
        } else {
                if(rows!=null&& rows.size()>0){
                    if(equipmentWarningList != null && equipmentWarningList.size()>0){
                        jsonObject.put("msg","查询成功");
                        jsonObject.put("code",0);
                        jsonObject.put("total",dataTable.getTotal());//总记录数
                        jsonObject.put("data",rows);
                    }
                }
            }


        return jsonObject;
    }
}
