package com.hujiang.project.zhgd.hjZhgdVehicle.api;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.project.zhgd.hjZhgdPkcount.domain.HjZhgdPkcount;
import com.hujiang.project.zhgd.hjZhgdPkcount.service.IHjZhgdPkcountService;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Vehicle;
import com.hujiang.project.zhgd.hjZhgdVehicle.service.IVehicleService;
import com.hujiang.project.zhgd.vehicleImg.service.IVehicleImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 移动端
 * */
@RestController
@RequestMapping(value = "/provider/vehicle/app",method = RequestMethod.POST)
public class VehicleAppApi extends BaseController {

    @Autowired
    private IVehicleService vehicleService;

    @Autowired
    private IVehicleImgService vehicleImgService;

    @Autowired
    private IHjZhgdPkcountService iHjZhgdPkcountService;
    /**
     * 查询所有数据
     * @return
     * */
    @PostMapping( value = "/selectAll")
    public JSONObject selectAll(Vehicle vehicle, PageDomain pageDomain){
        startPage();
        JSONObject result = new JSONObject();
        vehicle.setLiftTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        List<Vehicle> vehicle1 = vehicleService.selectAll(vehicle);
        JSONArray jsonArray = new JSONArray();
        for (Vehicle list : vehicle1 ){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("vehicleNo",list.getVehicleNo());    //车牌
            jsonObject.put("inOut",list.getInOut());    //进出类型 1.进，2.出
            jsonObject.put("liftTime",list.getLiftTime()); //进出时间
            jsonObject.put("cardType",list.getCardType()); //0.临时车 ，1.月租车...
            jsonArray.add(jsonObject);
        }
        result.put("msg","查询成功");
        result.put("code",0);
        result.put("data",jsonArray);
        return result;
    }


    /**
     * 查询某车场进出总数和剩余车位
     * */
    @PostMapping(value = "/carcount")
    public JSONObject countcar(@RequestBody Vehicle vehicle){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = "yyyy-MM-dd";//日期格式
        String date = sdf.format(new Date());
        vehicle.setLiftTime(date);
        Vehicle result = vehicleService.countcar(vehicle);
        JSONObject re = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        re.put("count",result.getCount());
        re.put("pkcount",result.getPkcount());
        jsonObject.put("msg","查询成功");
        jsonObject.put("code",0);
        jsonObject.put("data",re);
        return jsonObject;
    }


    /**
     *查询项目下所有车场id
     * @return
     * */
    @PostMapping(value = "/selectpkcounts")
    public JSONObject parkingspace(@RequestBody HjZhgdPkcount hjZhgdPkcount){
        List<HjZhgdPkcount> result = iHjZhgdPkcountService.pkcount(hjZhgdPkcount);
        JSONObject object = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (HjZhgdPkcount list : result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("deviceId",list.getDeptID());
            jsonObject.put("deviceName",list.getCarName());
            jsonArray.add(jsonObject);
        }
        object.put("msg","查询车场编号成功");
        object.put("code",0);
        object.put("data",jsonArray);
        return object;
    }


}
