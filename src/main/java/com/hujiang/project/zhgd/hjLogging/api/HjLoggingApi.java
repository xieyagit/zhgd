package com.hujiang.project.zhgd.hjLogging.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.hjLogging.domain.HjLogging;
import com.hujiang.project.zhgd.hjLogging.service.IHjLoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-27 10:59
 **/
@RestController
@RequestMapping(value = "/provider/HjLogging")
public class HjLoggingApi extends BaseController {
    @Autowired
    private IHjLoggingService loggingService;

    /**
     * 导出Excel数据源
     * @param projectId
     * @param startTime
     * @param endTime
     * @param userName
     * @return
     */
    @PostMapping("getLoggingExcel")
    public List<HjLogging> getLoggingExcel(@RequestParam(value = "projectId") Integer projectId,
                                    @RequestParam(value = "startTime",required = false) String startTime,
                                    @RequestParam(value = "endTime",required = false) String endTime,
                                    @RequestParam(value = "userName",required = false) String userName){
        Map<String,Object> map = new HashMap<>();
        map.put("projectId",projectId);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("userName",userName);
        return loggingService.selectHjLoggingListNew(map);
    }

    /**
     * 根据项目id查询日志
     * @param projectId
     * @return
     */
    @PostMapping("getLog")
    public JSONObject getLog(@RequestParam(value = "projectId") Integer projectId,
                             @RequestParam(value = "startTime",required = false) String startTime,
                             @RequestParam(value = "endTime",required = false) String endTime,
                             @RequestParam(value = "userName",required = false) String userName){
        Map<String,Object> map = new HashMap<>();
        map.put("projectId",projectId);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("userName",userName);
        startPage();
        JSONObject result = new JSONObject();
        HjLogging logging = new HjLogging();
        logging.setProjectId(projectId);
        List<HjLogging> hjLoggings = loggingService.selectHjLoggingListNew(map);
        result.put("mag","查询成功");
        result.put("data",getDataTable(hjLoggings));
        result.put("code",0);
        return result;
    }

    /**
     * 根据编号删除记录
     * @param ids
     * @return
     */
    @PostMapping( "/remove")
    public JSONObject remove(@RequestParam(value = "ids")String ids)
    {
        int i = loggingService.deleteHjLoggingByIds(ids);
        JSONObject result = new JSONObject();
        result.put("mag",i>0?"删除成功":"删除失败");
        result.put("code",0);
        return result;
    }

}
