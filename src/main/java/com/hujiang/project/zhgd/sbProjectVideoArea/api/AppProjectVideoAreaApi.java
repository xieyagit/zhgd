package com.hujiang.project.zhgd.sbProjectVideoArea.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.SbProjectVideoArea;
import com.hujiang.project.zhgd.sbProjectVideoArea.service.ISbProjectVideoAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-23 14:03
 **/
@RestController
@RequestMapping(value = "/provider/appProjectVideoAreaApi",method = RequestMethod.POST)
public class AppProjectVideoAreaApi {
    @Autowired
    private ISbProjectVideoAreaService areaService;

    /**
     * 根据项目id获取项目视频区
     * @param projectId
     * @return
     */
    @PostMapping("getAppProjectVideoArea")
    public JSONObject getAppProjectVideoArea(@RequestParam(value = "projectId") Integer projectId){
        JSONObject result = new JSONObject();
        SbProjectVideoArea videoArea = new SbProjectVideoArea();
        videoArea.setProjectid(projectId);
        List<SbProjectVideoArea> sbProjectVideoAreas = areaService.selectSbProjectVideoAreaList(videoArea);
        if(sbProjectVideoAreas!=null && sbProjectVideoAreas.size()>0){
            result.put("msg","查询设备列表成功");
            result.put("code",0);
            JSONArray array = new JSONArray();
            for(SbProjectVideoArea area:sbProjectVideoAreas){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("deviceName",area.getAreaName());
                jsonObject.put("deviceId",area.getId());
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
