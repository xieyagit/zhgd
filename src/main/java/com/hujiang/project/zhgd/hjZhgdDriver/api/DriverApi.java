package com.hujiang.project.zhgd.hjZhgdDriver.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjZhgdDriver.domain.HjZhgdDriver;
import com.hujiang.project.zhgd.hjZhgdDriver.service.IHjZhgdDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/provider/driver",method = RequestMethod.POST)
public class DriverApi extends BaseController {
    @Autowired
    private IHjZhgdDriverService iHjZhgdDriverService;

    @PostMapping(value = "/insertDriver")
    public JSONObject add(@RequestParam("driver") String driver, @RequestParam("vehicle") String vehicle, @RequestParam("projectId") String projectId){
        JSONObject jsonObject = new JSONObject();
        int i = iHjZhgdDriverService.insertHjZhgdDriver(driver, vehicle, projectId);
        if (i>0){
            jsonObject.put("data",0);
            jsonObject.put("msg","添加成功");
        }else {
            jsonObject.put("data",1);
            jsonObject.put("msg","添加失败");
        }
        return jsonObject;
    }

    @PostMapping(value = "/selectAll")
    public JSONObject all(@RequestBody HjZhgdDriver hjZhgdDriver){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        startPage();
        List<HjZhgdDriver> list = iHjZhgdDriverService.selectHjZhgdDriverList(hjZhgdDriver);
        TableDataInfo dataTable = getDataTable(list);
        if (list.size()>0){
            jsonArray.add(dataTable);
        }else {
            JSONObject object = new JSONObject();
            object.put("id","");
            object.put("driver","");
            object.put("vehicle","");
            object.put("deptId","");
            jsonArray.add(object);
        }
        jsonObject.put("msg","查询成功");
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }

    @PostMapping(value = "/delDriver")
    public JSONObject del(@RequestParam("id") Integer id){
        JSONObject jsonObject = new JSONObject();
        int i = iHjZhgdDriverService.deleteHjZhgdDriverById(id);
        if (i>0){
            jsonObject.put("mag","删除成功");
            jsonObject.put("data",0);
        }else{
            jsonObject.put("msg","删除失败");
            jsonObject.put("data",1);
        }
        return jsonObject;
    }

    @PostMapping(value = "/updateDriver")
    public JSONObject uod(@RequestBody HjZhgdDriver hjZhgdDriver){
        JSONObject jsonObject = new JSONObject();
        int i = iHjZhgdDriverService.updateHjZhgdDriver(hjZhgdDriver);
        if (i>0){
            jsonObject.put("msg","信息修改成功");
            jsonObject.put("data",0);
        }else{
            jsonObject.put("msg","信息修改失败");
            jsonObject.put("data",1);
        }
        return jsonObject;
    }
}
