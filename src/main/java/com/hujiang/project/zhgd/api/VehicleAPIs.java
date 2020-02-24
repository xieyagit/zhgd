package com.hujiang.project.zhgd.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjZhgdVehicle.api.VehicleParkingAPI;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.Parkingspace;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.UploadCarin;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.UploadCarout;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-29 10:20
 **/
@RestController
@RequestMapping(value = "/parking/")
public class VehicleAPIs {
    private Logger logger = Logger.getLogger(com.hujiang.project.zhgd.hjZhgdVehicle.api.VehicleParkingAPI.class.getName());

    @Autowired
    private VehicleParkingAPI client;

    /**
     * 车牌
     * @return
     */
    @PostMapping
    public Map<String,Object> parking(@RequestBody String json){
        JSONObject data =(JSONObject) JSONObject.parse(json);
        logger.info("\r com.hujiang.project.zhgd.hjZhgdVehicle.api.VehicleParkingAPI.parking" +
                "获取数据："+data);
        switch (data.getString("service")){
            case "uploadcarin":  //进场
                UploadCarin uploadCarin = data.toJavaObject(UploadCarin.class);
                return client.uploadcarin(uploadCarin);
            case "uploadcarout":    //出场
               UploadCarout uploadCarout = data.toJavaObject(UploadCarout.class);
                return client.uploadcarout(uploadCarout);
            case "uploadfile":      //图片上传
                UploadFile uploadFile = data.toJavaObject(UploadFile.class);
                return client.file(uploadFile);
            case "parkingspace":      //车位信息
                Parkingspace parkingspace = data.toJavaObject(Parkingspace.class);
                return client.parkingspaces(parkingspace);
            default :
                Map<String,Object> map = new HashMap<>();
                map.put("msg","没有"+data.getString("service")+"接口");
                return map;
        }

    }

    /**
     * 图片上传
     * @param json
     * @return
     */
    @PostMapping("uploadfile")
    public Map<String,Object> uploadFile(@RequestBody String json){
        JSONObject data =(JSONObject) JSONObject.parse(json);
        logger.info("com.hujiang.project.zhgd.hjZhgdVehicle.api.VehicleParkingAPI.uploadFile" +
                "获取数据："+data);
        UploadFile uploadFile = data.toJavaObject(UploadFile.class);
        return client.file(uploadFile);
    }



}
