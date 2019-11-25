package com.hujiang.project.zhgd.hjZhgdVehicle.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.AliyunOSSClientUtil;
import com.hujiang.common.utils.StringUtil;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.hjZhgdDriver.domain.HjZhgdDriver;
import com.hujiang.project.zhgd.hjZhgdDriver.service.IHjZhgdDriverService;
import com.hujiang.project.zhgd.hjZhgdPkcount.domain.HjZhgdPkcount;
import com.hujiang.project.zhgd.hjZhgdPkcount.service.IHjZhgdPkcountService;
import com.hujiang.project.zhgd.hjZhgdVehicle.domain.*;
import com.hujiang.project.zhgd.hjZhgdVehicle.service.IVehicleService;
import com.hujiang.project.zhgd.secretkey.domain.Secretkey;
import com.hujiang.project.zhgd.secretkey.service.ISecretkeyService;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import com.hujiang.project.zhgd.utils.ZCAPIClient;
import com.hujiang.project.zhgd.utils.ZCgetImageId;
import com.hujiang.project.zhgd.vehicleImg.domain.VehicleImg;
import com.hujiang.project.zhgd.vehicleImg.service.IVehicleImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * @program: Provider01
 * @description:
 * @author: Mr.LiuYong
 * @create: 2019-06-29 10:20
 **/
@RestController
@RequestMapping(value = "/provider/parking")
public class VehicleParkingAPI {

    @Autowired
    private IHjProjectService iHjProjectService;

    @Autowired
    private IVehicleService vehicleService;

    @Autowired
    private IVehicleImgService vehicleImgService;

    @Autowired
    private ISecretkeyService secretkeyService;

    @Autowired
    private IHjZhgdPkcountService iHjZhgdPkcountService;

    @Autowired
    private IHjZhgdDriverService iHjZhgdDriverService;

    PrintJobToImg printJobToImg;

    /**
     * 出场
     * @return
     */
    @PostMapping("uploadcarout")
    public Map<String,Object> uploadcarout(@RequestBody UploadCarout uploadCarout){
        return this.out(uploadCarout);
    }
    /**
     * 入场
     * @return
     */
    @PostMapping("uploadcarin")
    public Map<String,Object> uploadcarin(@RequestBody UploadCarin uploadCarin){
        return this.in(uploadCarin);
    }

    /**
     * 文件
     * @return
     */
    @PostMapping("uploadfile")
    public Map<String,Object> file(@RequestBody UploadFile uploadFile){
        return this.uploadfile(uploadFile);
    }

    /**
     * 车位
     * @return
     * */
    @PostMapping("parkingspace")
    public Map<String,Object> parkingspaces(@RequestBody Parkingspace parkingspace){
        return this.parkingspace(parkingspace);
    }

    /**
     * 车辆类型
     * @return
     * */
    @PostMapping("uploadcartype")
    public Map<String,Object> uploadcartypes(@RequestBody Uploadcartype uploadcartype){
        return this.uploadcartype(uploadcartype);
    }

    /**
     * 车辆出场
     * @param uploadCarout
     * @return
     */
    private Map<String,Object> out( UploadCarout uploadCarout) {
        // 返回值
        Map<String,Object>  result = new HashMap<>();
        HjZhgdDriver zhgdDriver = new HjZhgdDriver();
        zhgdDriver.setVehicle(uploadCarout.getCar_number());
        zhgdDriver.setDeptId(uploadCarout.getParkid());
        List<HjZhgdDriver> drivers = iHjZhgdDriverService.selectHjZhgdDriverList(zhgdDriver);
        try {
            Vehicle vehicle = new Vehicle();
            if (drivers.size()>0){
                vehicle.setName(drivers.get(0).getDriver());
            }
            vehicle.setDeptID(Integer.parseInt(uploadCarout.getParkid()));// 车场ID
            vehicle.setRecordId(uploadCarout.getOrder_id()); // 订单记录号
            vehicle.setVehicleNo(uploadCarout.getCar_number()); // 车牌号
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(uploadCarout.getOut_time(), pos);
            vehicle.setLiftTime(uploadCarout.getOut_time());//抬杆时间
//            vehicle.setLiftTime(String.valueOf(strtodate));//抬杆时间
            vehicle.setInOut(2); // 进出方向 1-进 2-出
            vehicle.setCardType(uploadCarout.getCard_type()); // 车牌类型	0临时车 1月租车 2充值车 3贵宾车 4免费车 8收费月租车
            vehicle.setGateinname(uploadCarout.getGateoutname());//通道名称
            vehicleService.insertVehicle(vehicle);
            //修改车位
            HjZhgdPkcount pkcount = new HjZhgdPkcount();
            pkcount.setDeptID(vehicle.getDeptID());
            HjZhgdPkcount zhgdPkcount = iHjZhgdPkcountService.carpkcount(pkcount);
            pkcount.setPkcount(zhgdPkcount.getPkcount()-1);
            iHjZhgdPkcountService.carUpd(uploadCarout.getParkid(),pkcount.getPkcount());
            result.put("service",uploadCarout.getService());
            result.put("result_code",0);
            result.put("order_id",uploadCarout.getOrder_id());
            result.put("message","上传成功");
            // 对接中车
            JSONObject objectMap = new JSONObject();
            objectMap.put("type", 5); // 车辆类型
            objectMap.put("plate_number", uploadCarout.getCar_number()); // 车牌号
            objectMap.put("direction", 0); // 0.出 1.进
            objectMap.put("color", null); // 车牌颜色
            objectMap.put("gate_no", uploadCarout.getParkid()); // 闸机编号
            objectMap.put("region_no",2); // 区域编号
            objectMap.put("region_type",1); //区域类型：1-工地，2-受纳场所
            objectMap.put("region_no", uploadCarout.getGateoutname()); // 经区域名称
            objectMap.put("lift_type", null); //抬杆方式：1-自动，2-手动
            List list = new ArrayList();
            JSONObject map = new JSONObject();
            map.put("img_type", 1); // 图片类型：1-车头，2-车身
            String filename= StringUtil.getRandomStringByLength(6)+
                    new SimpleDateFormat("yyyy-MM-ddHHmmss").format(new Date());
            String img_name = "lz_img"+filename;
            map.put("img_name", img_name); // 图片名称(不允许重复)
            int picSource = 2;
            VehicleImg vehicleImg = vehicleImgService.selectVehicleImgUrl(uploadCarout.getParkid(),uploadCarout.getOrder_id(),picSource);
            map.put("img_data", BASE64DecodedMultipartFile.ImageToBase64ByOnline(vehicleImg.getImgUrl())); // 内容：jpeg格式，base64数据
            list.add(map);
            objectMap.put("images", list);
            List<Map<String,Object>>  mapList = new ArrayList<>();
            mapList.add(objectMap);
            Long id = new Long(5);
            Secretkey secretkey = secretkeyService.selectSecretkeyById(id);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("api_key",secretkey.getApiKey());
            jsonObject.put("api_version",secretkey.getApiVersion());
            jsonObject.put("body",mapList);
            jsonObject.put("eng_code",secretkey.getEngCode());
            jsonObject.put("project_code",secretkey.getProjectCode());
//            String resultOnt = ZCAPIClient.reportedData("/vehicle/uploadData",jsonObject);
//            System.out.println("-------车辆:"+resultOnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 对接建筑废弃物监管平台（出场）
     * */
    private void outsc(UploadCarout uploadCarout){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("RecordId",uploadCarout.getOrder_id());//记录标识
        jsonObject.put("VehicleNo",uploadCarout.getCar_number());//车牌号
        jsonObject.put("PlateColor","");//车牌颜色
        HjZhgdPkcount hjZhgdPkcount = new HjZhgdPkcount();
        hjZhgdPkcount.setDeptID(Integer.valueOf(uploadCarout.getParkid()));
        hjZhgdPkcount.setSnName(uploadCarout.getGateoutname());
        HjZhgdPkcount pkcount = iHjZhgdPkcountService.selectSN(hjZhgdPkcount);
        jsonObject.put("GateNo",pkcount.getSn());//闸机编号
        jsonObject.put("LiftType","");//抬杠方式
        jsonObject.put("LiftTime",uploadCarout.getOut_time());//抬杠时间
        JSONObject object = new JSONObject();
        object.put("BaseInfo",jsonObject);
    }


    /**
     * 车辆进场
     * @param uploadCarin
     * @return
     */
    private Map<String,Object> in(UploadCarin uploadCarin) {
        // 返回值
        Map<String,Object>  result = new HashMap<>();
        HjZhgdDriver zhgdDriver = new HjZhgdDriver();
        zhgdDriver.setVehicle(uploadCarin.getCar_number());
        List<HjZhgdDriver> drivers = iHjZhgdDriverService.selectHjZhgdDriverList(zhgdDriver);
        try {
            Vehicle vehicle = new Vehicle();
            if (drivers.size()>0){
                vehicle.setName(drivers.get(0).getDriver());
            }
            vehicle.setDeptID(Integer.parseInt(uploadCarin.getParkid()));// 车场ID
            vehicle.setRecordId(uploadCarin.getOrder_id()); // 订单记录号
            vehicle.setVehicleNo(uploadCarin.getCar_number()); // 车牌号
            vehicle.setGateinname(uploadCarin.getGateinname());//通道名称
            vehicle.setCarType(uploadCarin.getCar_type());//车辆类型0、小车，1、大车
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            Date strtodate = formatter.parse(uploadCarin.getIn_time(), pos);
            vehicle.setLiftTime(uploadCarin.getIn_time());//抬杆时间
//            vehicle.setLiftTime(strtodate);//抬杆时间
            vehicle.setInOut(1); // 进出方向 1-进 2-出
            vehicle.setCardType(uploadCarin.getCard_type()); // 车牌类型	0临时车 1月租车 2充值车 3贵宾车 4免费车 8收费月租车
            vehicle.setGateinname(uploadCarin.getGateinname()); //通道名称
            vehicle.setName(uploadCarin.getOperatorin());
            vehicleService.insertVehicle(vehicle);
            //修改车位
            HjZhgdPkcount pkcount = new HjZhgdPkcount();
            pkcount.setDeptID(vehicle.getDeptID());
            HjZhgdPkcount zhgdPkcount = iHjZhgdPkcountService.carpkcount(pkcount);
//            pkcount.setPkcount(zhgdPkcount.getPkcount()+1);
            int i = zhgdPkcount.getPkcount();
            i = i+1;
            iHjZhgdPkcountService.carUpd(uploadCarin.getParkid(),i);
            result.put("service",uploadCarin.getService());
            result.put("result_code",0);
            result.put("order_id",uploadCarin.getOrder_id());
            result.put("message","上传成功");

            // 对接中车
            JSONObject objectMap = new JSONObject();
            objectMap.put("type", 5); // 车辆类型
            objectMap.put("plate_number", uploadCarin.getCar_number()); // 车牌号
            objectMap.put("direction", 1); // 0.出 1.进
            objectMap.put("color", null); // 车牌颜色
            objectMap.put("gate_no", uploadCarin.getParkid()); // 闸机编号
            objectMap.put("region_no",2); // 区域编号
            objectMap.put("region_type",1); //区域类型：1-工地，2-受纳场所
            objectMap.put("region_no", uploadCarin.getGateinname()); // 经区域名称
            objectMap.put("lift_type", null); //抬杆方式：1-自动，2-手动

            List list = new ArrayList();
            JSONObject map = new JSONObject();
            map.put("img_type", 1); // 图片类型：1-车头，2-车身
            String filename= StringUtil.getRandomStringByLength(6)+
                    new SimpleDateFormat("yyyy-MM-ddHHmmss").format(new Date());
            String img_name = "lz_img"+filename;
            map.put("img_name", img_name); // 图片名称(不允许重复)
            int picSource = 1;
            VehicleImg vehicleImg = vehicleImgService.selectVehicleImgUrl(uploadCarin.getParkid(),uploadCarin.getOrder_id(),picSource);
            map.put("img_data", BASE64DecodedMultipartFile.ImageToBase64ByOnline(vehicleImg.getImgUrl())); // 内容：jpeg格式，base64数据
            list.add(map);
            objectMap.put("images", list); //抬杆方式：1-自动，2-手动
            List<Map<String,Object>>  mapList = new ArrayList<>();
            mapList.add(objectMap);
            Long id = new Long(5);
            Secretkey secretkey = secretkeyService.selectSecretkeyById(id);
            if(secretkey!=null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("api_key",secretkey.getApiKey());
                jsonObject.put("api_version",secretkey.getApiVersion());
                jsonObject.put("body",mapList);
                jsonObject.put("eng_code",secretkey.getEngCode());
                jsonObject.put("project_code",secretkey.getProjectCode());
//                String resultOnt = ZCAPIClient.reportedData("/vehicle/uploadData",jsonObject);
//                System.out.println("-------车辆:"+resultOnt);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 对接建筑废弃物监管平台（进场）
     * */
    public void insc(UploadCarin uploadCarin){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("RecordId",uploadCarin.getOrder_id());//记录标识
        jsonObject.put("VehicleNo",uploadCarin.getCar_number());//车牌号
        jsonObject.put("PlateColor","");//车牌颜色(可为空)
        HjZhgdPkcount hjZhgdPkcount = new HjZhgdPkcount();
        hjZhgdPkcount.setDeptID(Integer.valueOf(uploadCarin.getParkid()));
        hjZhgdPkcount.setSnName(uploadCarin.getGateinname());
        HjZhgdPkcount pkcount = iHjZhgdPkcountService.selectSN(hjZhgdPkcount);
        jsonObject.put("GateNo",pkcount.getSn());//闸机编号
        jsonObject.put("LiftType","");//抬杆方式：1-自动抬杆，2- 手动抬杆（可为空）
        jsonObject.put("LiftTime",uploadCarin.getIn_time());//抬杆时间
        JSONObject object = new JSONObject();
        object.put("BaseInfo",jsonObject);
    }

    /**
     * 车牌图片
     * @param uploadFile
     * @return
     */
    private Map<String,Object> uploadfile( UploadFile uploadFile) {
        // 返回值
        Map<String,Object>  result = new HashMap<>();
        try {
           String filename = StringUtil.getRandomStringByLength(6)+
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(uploadFile.getContent());
            //文件名称
            String url = null;
            try {
                url = AliyunOSSClientUtil.uploadFileImg(file, "lz_img",  filename+".jpeg");
            } catch (IOException e) {
                e.printStackTrace();
            }
            String name = url.substring(0,url.lastIndexOf("?"));

            VehicleImg vehicleImg = new VehicleImg();
            vehicleImg.setImgUrl(name);
            vehicleImg.setParkid(uploadFile.getParkid()); // 停车场id
            vehicleImg.setRecordid(uploadFile.getRecordid()); // 记录id 唯一标识
            Integer inout = 0;
            if (uploadFile.getPic_source()==1 || uploadFile.getPic_source()==2){
                inout = 1;
            }else {
                inout = 2;
            }
            vehicleImg.setPicSource(inout);//进出方向
            vehicleImgService.insertVehicleImg(vehicleImg);

            FileOutputStream fos = null;
            BufferedInputStream bis = null;
            HttpURLConnection httpUrl = null;
            URL url1 = null;
            int BUFFER_SIZE = 1024;
            byte[] buf = new byte[BUFFER_SIZE];
            int size = 0;
            try {
                url1 = new URL(name);
                httpUrl = (HttpURLConnection) url1.openConnection();
                httpUrl.connect();
                bis = new BufferedInputStream(httpUrl.getInputStream());
                fos = new FileOutputStream("c:\\haha.gif");
                while ((size = bis.read(buf)) != -1) {
                    fos.write(buf, 0, size);
                }
                fos.flush();
                Vehicle vehicle = new Vehicle();
                vehicle.setDeptID(Integer.valueOf(uploadFile.getParkid()));
                vehicle.setRecordId(uploadFile.getRecordid());
                vehicle.setInOut(inout);
                Vehicle upload = vehicleService.selectscenes(vehicle);
                vehicle.setRecordId(uploadFile.getRecordid());
                vehicle.setImg(name);
                vehicle.setInOut(inout);
                vehicleService.update(vehicle);
                /** 进行对接（工务署）取消注释*/
//                Integer k = uploadFile.getPic_source();
//                this.djzc(upload,inout,name,k);
                /** 添加水印进行取消注释*/
//                HjZhgdPkcount hjZhgdPkcount = iHjZhgdPkcountService.selectProjectId(Integer.valueOf(uploadFile.getParkid()));
//                HjProject hjProject = iHjProjectService.selectHjProjectById(hjZhgdPkcount.getProjectId());
//                this.tjsy(hjProject.getProjectName(),inout,filename);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                    bis.close();
                    httpUrl.disconnect();
                } catch (IOException e) {
                } catch (NullPointerException e) {
                }
                result.put("service",uploadFile.getService());
                result.put("result_code",0);
                result.put("recordid",uploadFile.getRecordid());
                result.put("message","上传成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 对接建筑废弃物监管平台（图片）
     * */
    private void filesc(UploadFile uploadFile){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ImgName",uploadFile.getRecordid()+".jpeg");//图片名称
        jsonObject.put("ImgType",uploadFile.getPic_source());//图片类型：
        jsonObject.put("ImgData",uploadFile.getContent());//图 片 内 容
        jsonObject.put("TakeTime",new Date());//拍照时间
        jsonObject.put("RecordId",uploadFile.getRecordid());//车牌识别记录标识
        JSONObject object = new JSONObject();
        object.put("BaseInfo",jsonObject);
    }


    /**
     * 修改车位数
     * @param parkingspace
     * @return
     * */
    private Map<String,Object> parkingspace(Parkingspace parkingspace){
        Map<String,Object> map = new HashMap<>();
//        try{
//            Parkingspace spacel = new Parkingspace();
//            spacel.setService(parkingspace.getService());
//            spacel.setParkid(parkingspace.getParkid());
//            spacel.setSpaceLeft(parkingspace.getSpaceLeft());   //剩余车位
//            spacel.setSpacetotal(parkingspace.getSpacetotal());
//            spacel.setSpaceused(parkingspace.getSpaceused());
//            spacel.setTime(parkingspace.getTime());
//            spacel.setRemark(parkingspace.getRemark());
//            HjZhgdPkcount hjZhgdPkcount = new HjZhgdPkcount();
//            hjZhgdPkcount.setDeptID(Integer.parseInt(spacel.getParkid()));
//            hjZhgdPkcount.setPkcount(spacel.getSpaceLeft());
//            iHjZhgdPkcountService.updateHjZhgdPkcount(hjZhgdPkcount);
//            map.put("service",parkingspace.getService());
//            map.put("result_code",0);
//            map.put("recordid",parkingspace.getParkid());
//            map.put("message","修改成功");
//        } catch (Exception e){
//            e.printStackTrace();
//            map.put("service",parkingspace.getService());
//            map.put("result_code",0);
//            map.put("recordid",parkingspace.getParkid());
//            map.put("message","修改失败");
//        }
        return map;
    }



    private Map<String,Object> uploadcartype(Uploadcartype uploadcartype){
        Map<String,Object> map = new HashMap<>();
        try{
            Vehicle vehicle = new Vehicle();
            vehicle.setDeptID(Integer.parseInt(uploadcartype.getParkid()));
            vehicle.setCarTypedesc(uploadcartype.getCar_typedesc());
            vehicleService.updatezhgd(vehicle);
            map.put("service",uploadcartype.getService());
            map.put("result_code",0);
            map.put("recordid",uploadcartype.getParkid());
            map.put("message","修改成功");
        } catch (Exception e){
            e.printStackTrace();
            map.put("service",uploadcartype.getService());
            map.put("result_code",0);
            map.put("recordid",uploadcartype.getParkid());
            map.put("message","修改失败");
        }
        return map;
    }
    /** 对接工务署*/
    private void djzc(Vehicle uploadCarin,int inout,String name,Integer k) throws Exception {
        Object resukt = ZCgetImageId.getCurrentImg("be13227b099e4b5a858b136b52f75a84", name);
        if (inout == 1) {
            // 对接中车入场
            /**获取图片路径*/
            System.out.println(resukt);
            JSONObject objectMap = new JSONObject();
            objectMap.put("type", 5); // 车辆类型
            objectMap.put("plate_number", uploadCarin.getVehicleNo()); // 车牌号
            objectMap.put("direction", 1); // 0.出 1.进
            HjZhgdPkcount hjZhgdPkcount = iHjZhgdPkcountService.selectAll(String.valueOf(uploadCarin.getDeptID()),uploadCarin.getGateinname());
            objectMap.put("color", 2); // 车牌颜色：1-蓝，2-黄，3-黑，4-白，9-其他
            objectMap.put("gate_no", hjZhgdPkcount.getSnName()); // 闸机编号
            objectMap.put("region_no", "1199.19"); // 区域编号
            objectMap.put("region_type", 1); //区域类型：1-工地，2-受纳场所
            objectMap.put("lift_time", uploadCarin.getLiftTime());
//            objectMap.put("lift_time", "2019-07-31 14:35:12");
            JSONArray array = new JSONArray();
            JSONObject img = new JSONObject();
            img.put("img_type", 1);
            img.put("img_data", ((JSONObject) resukt).remove("resultData"));
            array.add(img);
            objectMap.put("images", array); // 图片名称(不允许重复  )
            List<Map<String, Object>> mapList = new ArrayList<>();
            mapList.add(objectMap);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("api_key", "be13227b099e4b5a858b136b52f75a84");
            jsonObject.put("project_code", "XM20160484");
            jsonObject.put("eng_code", "XM2016048402");
            jsonObject.put("api_version", 1.0);
            jsonObject.put("body", mapList);
            String resultOnt = ZCAPIClient.reportedData2019("vehicle/uploadData", jsonObject);
            System.out.println("-------车辆:" + resultOnt);
        } else {
            // 对接中车出场
            /**获取图片路径*/
            System.out.println(resukt);
            JSONObject objectMap = new JSONObject();
            objectMap.put("type", 5); // 车辆类型
            objectMap.put("plate_number", uploadCarin.getVehicleNo()); // 车牌号
            objectMap.put("direction", 0); // 0.出 1.进
            objectMap.put("color", 2); // 车牌颜色：1-蓝，2-黄，3-黑，4-白，9-其他
            HjZhgdPkcount hjZhgdPkcount = iHjZhgdPkcountService.selectAll(String.valueOf(uploadCarin.getDeptID()),uploadCarin.getGateinname());
            objectMap.put("gate_no",hjZhgdPkcount.getSnName()); // 闸机编号
            objectMap.put("region_no", "1199.19"); // 区域编号
            objectMap.put("region_type", 1); //区域类型：1-工地，2-受纳场所
            objectMap.put("lift_time", uploadCarin.getLiftTime());
            JSONArray array = new JSONArray();
            JSONObject img = new JSONObject();
            img.put("img_type", 1);
            img.put("img_data", ((JSONObject) resukt).remove("resultData"));
            array.add(img);
            objectMap.put("images", array); // 图片名称(不允许重复  )
            List<Map<String, Object>> mapList = new ArrayList<>();
            mapList.add(objectMap);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("api_key", "be13227b099e4b5a858b136b52f75a84");
            jsonObject.put("project_code", "XM20160484");
            jsonObject.put("eng_code", "XM2016048402");
            jsonObject.put("api_version", 1.0);
            jsonObject.put("body", mapList);
            String resultOnt = ZCAPIClient.reportedData2019("vehicle/uploadData", jsonObject);
            System.out.println("-------车辆:" + resultOnt);
        }
    }

    /** 图片添加水印*/
    private void tjsy(String did , Integer inout , String filename) throws IOException {

        Font font = new Font("04b_08", Font.PLAIN, 18);//字体
//        String sourceImg = "c:\\haha.gif"; //源图片地址
        File file2 = new File("");
        String sourceImg = file2.getCanonicalPath()+File.separator+"src\\"+"main\\"+"resources\\"+"img\\"+filename;; //源图片地址

        sourceImg = URLDecoder.decode(sourceImg, "utf-8");
        System.out.println(sourceImg);
        sourceImg = sourceImg.replace('\\', File.separatorChar);
        System.out.println(sourceImg);
//        String targetImg = "D:\\a\\"+did+".jpeg"; //新存储的地址
        String targetImg = file2.getCanonicalPath()+File.separator+"src\\"+"main\\"+"resources\\"+"image\\"+filename+".jpeg"; //新存储的地址
        String in;
        if (inout == 1) {
            in = "进";
        } else {
            in = "出";
        }
//        String watermark = "库坑中学土石方与基坑支护" + "  " + in + "  " + new Date();//水印内容
        String watermark = did + "  " + in + "  " + new Date();//水印内容
        Color color = new Color(255, 0, 0);
//                printJobToImg.addWatermark(sourceImg, targetImg, watermark, color, font);
        File srcImgFile = new File(sourceImg);
        Image srcImg = ImageIO.read(srcImgFile);
        int srcImgWidth = srcImg.getWidth(null);
        int srcImgHeight = srcImg.getHeight(null);
        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        g.setColor(color);
        g.setFont(font);
        //设置水印的坐标
//        int x = srcImgWidth - (g.getFontMetrics(g.getFont()).charsWidth(watermark.toCharArray(), 0, 1);
        int x = srcImgWidth - (g.getFontMetrics(g.getFont()).charsWidth(watermark.toCharArray(), 0, 1));
        int y = srcImgHeight - 25;
        g.drawString(watermark, x, y);  //加水印
        g.dispose();
        // 输出图片
        FileOutputStream outImgStream = new FileOutputStream(targetImg);
        ImageIO.write(bufImg, "jpg", outImgStream);
        System.out.println("添加水印完成");
        File file1 = new File(targetImg);
        FileInputStream inputFile = new FileInputStream(file1);
        byte[] buffer = new byte[(int)file1.length()];
        inputFile.read(buffer);
        inputFile.close();
        MultipartFile files = BASE64DecodedMultipartFile.base64ToMultipartOnt(new BASE64Encoder().encode(buffer));
        String urls = AliyunOSSClientUtil.uploadFileImg(files, "lz_img",  filename+".jpeg");
        String names = urls.substring(0,urls.lastIndexOf("?"));
        System.out.println(names);
        outImgStream.flush();
        outImgStream.close();
    }
}

