package com.hujiang.project.zhgd.sbDustEmission.pcApi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.service.ISbDustEmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 **/
@RestController
@RequestMapping(value = "/provider/app/PcDustEmission",method = RequestMethod.POST)
public class PcDustEmissionApi extends BaseController {
    private Logger logger = Logger.getLogger(PcDustEmissionApi.class.getName());
    @Autowired
    private ISbDustEmissionService dustEmissionService;


    /**
     * 查询扬尘记录
     * @param sn sn编号
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @PostMapping("getDustEmission")
    public JSONObject getDustEmission(@RequestParam(value = "sn")String sn,
                                      @RequestParam(value = "startTime",required = false)String startTime,
                                      @RequestParam(value = "endTime",required = false)String endTime){
        startPage();
        JSONObject result = new JSONObject();
        Map<String,Object> map = new HashMap<>();
        map.put("sn",sn);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        //获取分页数据
        List<SbDustEmission> sbDustEmissions = dustEmissionService.selectSbDustEmissionByTime(map);
        if(sbDustEmissions!=null && sbDustEmissions.size()>0){
            result.put("msg","扬尘记录查询成功");
            result.put("code",0);
            result.put("data", getDataTable(sbDustEmissions));
        }else{
            result.put("msg","扬尘记录查询失败");
            result.put("code",-1);
            result.put("data",null);
        }
        return result;
    }







}
