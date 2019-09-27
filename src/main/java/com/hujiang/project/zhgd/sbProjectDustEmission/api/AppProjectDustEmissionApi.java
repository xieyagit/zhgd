package com.hujiang.project.zhgd.sbProjectDustEmission.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.service.ISbProjectDustEmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-18 15:36
 **/
@RestController
@RequestMapping(value = "/provider/appProjectDustEmission",method = RequestMethod.POST)
public class AppProjectDustEmissionApi {
    private Logger logger = Logger.getLogger(AppProjectDustEmissionApi.class.getName());
    @Autowired
    private ISbProjectDustEmissionService projectDustEmissionService;

    /**
     * 根据项目id查询项目扬尘设备列表
     * @param projectId
     * @return
     */
    @PostMapping("getAppProjectDustEmission")
    public JSONObject getAppProjectDustEmission(@RequestParam(value = "projectId")Long projectId){
        JSONObject result = new JSONObject();
        SbProjectDustEmission projectDustEmission = new SbProjectDustEmission();
        projectDustEmission.setProjectId(projectId);
        //查询项目设备
        List<SbProjectDustEmission> sbProjectDustEmissions = projectDustEmissionService.selectSbProjectDustEmissionList(projectDustEmission);

        if(sbProjectDustEmissions!=null && sbProjectDustEmissions.size()>0){
            result.put("msg","查询设备列表成功");
            result.put("code",0);
            JSONArray array = new JSONArray();
            for(SbProjectDustEmission sbProjectDustEmission:sbProjectDustEmissions){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("deviceName",sbProjectDustEmission.getComments());
                jsonObject.put("deviceId",sbProjectDustEmission.getSn());
                array.add(jsonObject);
            }
            result.put("data",array);
        }else{
            result.put("msg","无设备");
            result.put("code",-1);
        }

        return result;
    }

}
