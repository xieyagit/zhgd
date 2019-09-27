package com.hujiang.project.zhgd.sbProjectElectricityBox.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.domain.AjaxResult;
import com.hujiang.project.zhgd.sbCurrentTemperature.domain.SbCurrentTemperature;
import com.hujiang.project.zhgd.sbCurrentTemperature.service.ISbCurrentTemperatureService;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectElectricityBox.domain.SbProjectElectricityBox;
import com.hujiang.project.zhgd.sbProjectElectricityBox.service.ISbProjectElectricityBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-24 11:52
 **/
@RestController
@RequestMapping(value = "/provider/appProjectElectricityBox",method = RequestMethod.POST)
public class AppProjectElectricityBoxApi {
    @Autowired
    private ISbProjectElectricityBoxService boxService;
@Autowired
private ISbCurrentTemperatureService sbCurrentTemperatureService;
    /**
     * 根据项目id获取电箱设备编号
     * @param projectId
     * @return
     */
    @PostMapping("getAppProjectElectricityBox")
    public JSONObject getAppProjectElectricityBox(@RequestParam(value = "projectId") Integer projectId){
        JSONObject result = new JSONObject();
        SbProjectElectricityBox box = new SbProjectElectricityBox();
        box.setProjectId(projectId);
        List<SbProjectElectricityBox> sbProjectElectricityBoxes = boxService.selectSbProjectElectricityBoxList(box);
        if(sbProjectElectricityBoxes!=null && sbProjectElectricityBoxes.size()>0){
            result.put("msg","查询设备列表成功");
            result.put("code",0);
            JSONArray array = new JSONArray();
            for(SbProjectElectricityBox box1:sbProjectElectricityBoxes){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("deviceName",box1.getComments());
                jsonObject.put("deviceId",box1.getElectricityBoxId());
                array.add(jsonObject);
            }
            result.put("data",array);
        }else{
            result.put("msg","无设备");
            result.put("code",-1);
        }
        return result;
    }
    @PostMapping("getBoxList")
    public AjaxResult getBoxList(@RequestParam(value = "pid") Integer pid){
        JSONObject result = new JSONObject();
        SbProjectElectricityBox box = new SbProjectElectricityBox();
        box.setProjectId(pid);
        List<SbProjectElectricityBox> sbProjectElectricityBoxes = boxService.selectSbProjectElectricityBoxList(box);
        if(sbProjectElectricityBoxes!=null && sbProjectElectricityBoxes.size()>0){
            SbCurrentTemperature sb=new SbCurrentTemperature();
            sb.setElectricityBoxId(sbProjectElectricityBoxes.get(0).getElectricityBoxId());
            List<SbCurrentTemperature> sList=sbCurrentTemperatureService.selectSbCurrentTemperatureListTwo(sb);
            return AjaxResult.success(sList);
        }
        return AjaxResult.error();
    }

}
