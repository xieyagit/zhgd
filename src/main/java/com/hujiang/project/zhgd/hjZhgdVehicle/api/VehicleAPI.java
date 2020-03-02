package com.hujiang.project.zhgd.hjZhgdVehicle.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.web.controller.BaseController;
import com.hujiang.framework.web.page.PageDomain;
import com.hujiang.framework.web.page.TableDataInfo;
import com.hujiang.project.zhgd.hjZhgdPkcount.domain.HjZhgdPkcount;
import com.hujiang.project.zhgd.hjZhgdPkcount.service.IHjZhgdPkcountService;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.*;
import com.hujiang.project.zhgd.hjZhgdVehicle.service.IVehicleService;
import com.hujiang.project.zhgd.secretkey.service.ISecretkeyService;
import com.hujiang.project.zhgd.vehicleImg.service.IVehicleImgService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

@RestController
@RequestMapping("/provider/parkings")
public class VehicleAPI extends BaseController {

    @Autowired
    private IVehicleService vehicleService;

    @Autowired
    private IVehicleImgService vehicleImgService;

    @Autowired
    private ISecretkeyService secretkeyService;

    @Autowired
    private IHjZhgdPkcountService iHjZhgdPkcountService;

    PrintJobToImg printJobToImg;
    /**
     * 查询所有数据
     * @return
     * */
    @PostMapping( value = "/selectAll")
    public JSONObject selectAll(@RequestBody Vehicle vehicle, PageDomain pageDomain){
        startPage();
        List<Vehicle> veh = vehicleService.selectAll(vehicle);
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("msg","查询成功");
        jsonObject.put("code",0);
        jsonObject.put("data",getDataTable(veh));
        return jsonObject;
    }

    @PostMapping( value = "/selectAlls")
    public List<Vehicle> selectAlls(@RequestBody Vehicle vehicle){
       return vehicleService.selectAll(vehicle);
    }

    // color #2395439
    public static Color getColor(String color) {
        if (color.charAt(0) == '#') {
            color = color.substring(1);
        }
        if (color.length() != 6) {
            return null;
        }
        try {
            int r = Integer.parseInt(color.substring(0, 2), 16);
            int g = Integer.parseInt(color.substring(2, 4), 16);
            int b = Integer.parseInt(color.substring(4), 16);
            return new Color(r, g, b);
        } catch (NumberFormatException nfe) {
            return null;
        }
    }


    /**
     *查询在场车辆
     * @return
     * */
    @PostMapping(value = "/parking/selectscene")
    public JSONObject selectscene(@RequestBody Vehicle vehicle, PageDomain pageDomain) throws ParseException {

        JSONObject jsonObject = new JSONObject();
        Vehicle gicle = new Vehicle();
        if( vehicle.getIhour() != null && vehicle.getIhour() != 0) {
            String returnstr = ""; //返回值
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - vehicle.getIhour());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = "yyyy-MM-dd HH:mm:ss";//日期格式
            returnstr = sdf.format(calendar.getTime());
            gicle.setLiftTime(String.valueOf(returnstr));
//            gicle.setLiftTime(dates);

            System.out.println(gicle.getLiftTime());

        }
        gicle.setVehicleNo(vehicle.getVehicleNo());
        gicle.setCardType(vehicle.getCardType());
        gicle.setCarType(vehicle.getCarType());
        gicle.setDeptID(vehicle.getDeptID());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        startPage();
        List<Vehicle> vehicleList = vehicleService.selectscene(gicle);
        TableDataInfo dataTable = getDataTable(vehicleList);
        for (Vehicle vehicleList2 : vehicleList) {
            try {
                Date date =  new Date();
                Timestamp timestamp = new Timestamp(date.getTime());//获取当前时间 格式2019-07-17 10:07:35
                Date parse = df.parse(vehicleList2.getLiftTime());
                Timestamp timestamp1 = new Timestamp(parse.getTime());//获取时间点 比当前时间小 格式2019-07-16 10:07:35
                Date d1 = df.parse(String.valueOf(timestamp));
                Date d2 = df.parse(String.valueOf(timestamp1));
                long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
                Long a = diff / (1000 * 60 * 60 * 24);
                Long b = (diff - a * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                Long c = (diff - a * (1000 * 60 * 60 * 24) - b * (1000 * 60 * 60)) / (1000 * 60);
                Integer days = Integer.parseInt(String.valueOf(a));
                Integer hours = Integer.parseInt(String.valueOf(b));
                Integer minutes = Integer.parseInt(String.valueOf(c));
                vehicleList2.setDays(days);
                vehicleList2.setHours(hours);
                vehicleList2.setMinutes(minutes);
                vehicleList2.setTotal((int) dataTable.getTotal());
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("msg", "查询成功");
                jsonObject.put("code", -1);
                jsonObject.put("data", dataTable);
            }
            jsonObject.put("msg", "查询成功");
            jsonObject.put("code", 0);
            jsonObject.put("data", dataTable);
        }
        return jsonObject;
    }

    /**
     *查询剩余车位，车场编号及车场名称
     * @return
     * */
    @PostMapping(value = "/selectpkcount")
    public JSONObject parkingspace(@RequestBody HjZhgdPkcount hjZhgdPkcount){
        HjZhgdPkcount pkcount = new HjZhgdPkcount();
        pkcount.setProjectId(hjZhgdPkcount.getProjectId());
        pkcount.setDeptID(hjZhgdPkcount.getDeptID());
        List<HjZhgdPkcount> result = iHjZhgdPkcountService.pkcount(pkcount);
        JSONObject object = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (HjZhgdPkcount list : result) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",list.getId());
            jsonObject.put("pkcount",list.getPkcount());
            jsonObject.put("deviceId",list.getDeptID());
            jsonObject.put("deviceName",list.getCarName());
            jsonObject.put("sn",list.getSn());
            jsonObject.put("snName",list.getSnName());
            jsonArray.add(jsonObject);
        }
        object.put("msg","查询成功");
        object.put("code",0);
        object.put("count",result.size());
        object.put("data",jsonArray);
        return object;
    }

    /** 设备添加*/
    @PostMapping(value = "/vehicleSnAdd")
    public JSONObject add(@RequestBody HjZhgdPkcount hjZhgdPkcount){
        JSONObject jsonObject = new JSONObject();
        int i = iHjZhgdPkcountService.add(hjZhgdPkcount);
        if (i>0){
            jsonObject.put("data",0);
            jsonObject.put("msg","添加设备成功");
        } else {
            jsonObject.put("data",-1);
            jsonObject.put("msg","添加设备失败");
        }
        return jsonObject;
    }
    /** 设备编辑*/
    @PostMapping(value = "/vehicleSnUpd")
    public JSONObject upd(@RequestBody HjZhgdPkcount hjZhgdPkcount){
        JSONObject jsonObject = new JSONObject();
        int i = iHjZhgdPkcountService.updateHjZhgdPkcount(hjZhgdPkcount);
        if (i>0){
            jsonObject.put("data",0);
            jsonObject.put("msg","修改设备成功");
        } else {
            jsonObject.put("data",-1);
            jsonObject.put("msg","修改设备失败");
        }
        return jsonObject;
    }
    /** 删除设备*/
    @PostMapping(value = "/vehicleSnDel")
    public JSONObject del(@RequestParam Integer id){
        JSONObject jsonObject = new JSONObject();
        int i = iHjZhgdPkcountService.deleteHjZhgdPkcountById(id);
        if (i>0){
            jsonObject.put("data",0);
            jsonObject.put("msg","删除设备成功");
        } else {
            jsonObject.put("data",-1);
            jsonObject.put("msg","删除设备失败");
        }
        return jsonObject;
    }

    /** 场内车位设置*/
    @PostMapping(value = "/carUpd")
    public JSONObject carUpd(@RequestParam(value = "deptId") String deptId,@RequestParam(value = "pkcount") Integer pkcount){
        JSONObject jsonObject = new JSONObject();
        int i = iHjZhgdPkcountService.carUpd(deptId,pkcount);
        if (i>0){
            jsonObject.put("data",0);
            jsonObject.put("msg","成功");
        }else{
            jsonObject.put("data",1);
            jsonObject.put("msg","失败");
        }
        return jsonObject;
    }


    /** 智慧工地1.0 今日进出总数*/
    @PostMapping(value = "/todaycount")
    public JSONObject todaycount(@RequestParam(value = "projectId") Integer projectId){
        HjZhgdPkcount hjZhgdPkcount = new HjZhgdPkcount();
        hjZhgdPkcount.setProjectId(projectId);
        List<HjZhgdPkcount> pkcount = iHjZhgdPkcountService.pkcount(hjZhgdPkcount);
        JSONArray jsonArray = new JSONArray();
        for (HjZhgdPkcount hjZhgdPkcount1 : pkcount) {
            Integer deptID = hjZhgdPkcount1.getDeptID();
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateNowStr = sdf.format(d);
            String liftTime = dateNowStr;
            Vehicle eh = vehicleService.todaycount(deptID, liftTime);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count", eh.getCount());
            List<Vehicle> vehicleList = vehicleService.list(deptID, liftTime);
            jsonObject.put("vehicleList", vehicleList);
            JSONObject object = new JSONObject();
            object.put("name", hjZhgdPkcount1.getCarName());
            object.put("pkcount", hjZhgdPkcount1.getPkcount());
            object.put("snName", hjZhgdPkcount1.getSnName());
            object.put("todaycount", jsonObject);
            jsonArray.add(object);
        }
        if (pkcount.size()==0){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count", "");
            jsonObject.put("vehicleList", "");
            JSONObject object = new JSONObject();
            object.put("name", "");
            object.put("pkcount","");
            object.put("todaycount", jsonObject);
            jsonArray.add(object);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",jsonArray);
        return jsonObject;
    }

}


