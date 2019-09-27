package com.hujiang.project.zhgd.sbProjectDustEmission.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.domain.SdDustEmission;
import com.hujiang.project.zhgd.sbDustEmission.service.ISbDustEmissionService;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.service.ISbProjectDustEmissionService;
import com.hujiang.project.zhgd.sbProjectDustEmission.task.JPushSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 山东
 */
@RestController
@RequestMapping(value = "/provider/dustEmission")
public class ShanDongDustEmission {
    @Autowired
    private ISbDustEmissionService dustEmissionService;
    @Resource
    private JPushSMS jpushDustEmission;
    @Autowired
    private ISbProjectDustEmissionService projectDustEmissionService;
    @Autowired
    private SbDustEmission dustEmission;

    @PostMapping("/addDustEmission")
    public Map<String,Object>  addDustEmission(@RequestBody String json){
        JSONObject jsonResult = JSONObject.parseObject(json);
        SdDustEmission sdDustEmission = JSONObject.parseObject(jsonResult.toJSONString(), SdDustEmission.class);
        return this.insertDustEmission(sdDustEmission);
    }
    private Map<String,Object> insertDustEmission(SdDustEmission sdDustEmission){
        Map<String,Object> map = new HashMap<>();
        dustEmission.setSn(sdDustEmission.getSn());
        dustEmission.setDate(sdDustEmission.getDate());
        dustEmission.setPm25(sdDustEmission.getPm25());
        dustEmission.setPm10(sdDustEmission.getPm10());
        dustEmission.setTsp(sdDustEmission.getTsp());
        dustEmission.setNoise(sdDustEmission.getNoise());
        dustEmission.setTemperature(sdDustEmission.getTemperature());
        dustEmission.setHumidity(sdDustEmission.getHumidity());
        dustEmission.setWindSpeed(sdDustEmission.getWindSpeed());
        dustEmission.setWinddirection(sdDustEmission.getWinddirection());
        dustEmission.setWindPower(sdDustEmission.getWindPower());
        dustEmission.setAirPressure(sdDustEmission.getAirPressure());
        SbProjectDustEmission sbProjectDustEmission = new SbProjectDustEmission();
        sbProjectDustEmission.setSn(sdDustEmission.getSn());
        List<SbProjectDustEmission> projectDustEmissions = projectDustEmissionService.selectSbProjectDustEmissionList(sbProjectDustEmission);
        for (SbProjectDustEmission p : projectDustEmissions) {

            String site = p.getComments();
            String sn=p.getSn();

            dustEmission.setSn(sn);
            dustEmission.setSite(site);
            jpushDustEmission.JPushAndJSMS(dustEmission,p.getProjectId().intValue());
            if(dustEmissionService.insertSbDustEmission(dustEmission)>0){
                map.put("msg","成功");
                map.put("code",0);
            }else {
                map.put("msg","失败");
                map.put("code",-1);
            }
        }

        return map;
    }
}
