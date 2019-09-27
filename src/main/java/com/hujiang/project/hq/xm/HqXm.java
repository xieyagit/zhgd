package com.hujiang.project.hq.xm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/provider/instructionsXm")
public class HqXm {
@Autowired
private IHjDeviceProjectworkersService hjDeviceProjectworkersService;

@Autowired
private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @PostMapping(value = "/xm")
    public JSONObject xm(@RequestParam(value = "deviceNo") String deviceNo)throws  Exception{
        int i=0;
        Map<String,String > param=new HashMap<String ,String>();
        param.put("deviceNo",deviceNo);
        param.put("status","0");
        JSONObject json=new JSONObject();
        json.put("result","0");
        json.put("msg","获取成功");
        JSONObject content=new JSONObject();
        List<HjProjectWorkers> hList1=hjDeviceProjectworkersService.selectHjProjectworkersListTwo(param);
        HjDeviceProjectworkers hd;
        if(hList1.size()>0){
            i++;
            content.put("comcount",i);
            JSONArray addemps=new JSONArray();
            JSONObject add;
            for(HjProjectWorkers hw: hList1){
                add=new JSONObject();
                add.put("comid",hw.getId());
                add.put("comtype","105");
                add.put("name",hw.getEmpName());
                add.put("user_id",hw.getId());
                add.put("ic","");
                add.put("id_card",hw.getIdCode());
                add.put("enroll_type","0");
                add.put("face_template",BASE64DecodedMultipartFile.ImageToBase64ByOnline(hw.getFaceUrl()).replaceAll("\r|\n", ""));
                add.put("finger_template","");
                addemps.add(add);
                hd=new HjDeviceProjectworkers();
                hd.setStatus("1");
                hd.setDeviceNo(deviceNo);
                hd.setProjectWorkersId(hw.getId());
                hjDeviceProjectworkersService.updateHjDeviceProjectworkersTwo(hd);
            }
            content.put("addemps",addemps);
        }
        param.put("status","2");
        List<HjProjectWorkers> hList2=hjDeviceProjectworkersService.selectHjProjectworkersListTwo(param);
        if(hList2.size()>0){
            i++;
            content.put("comcount",i);
            JSONArray deleteemps=new JSONArray();
            JSONObject del;
            for(HjProjectWorkers hw: hList2){
                del=new JSONObject();
                del.put("comid","30");
                del.put("comtype","106");
                del.put("name",hw.getEmpName());
                del.put("user_id",hw.getId());
                deleteemps.add(del);
                hd=new HjDeviceProjectworkers();
                hd.setProjectWorkersId(hw.getId());
                hd.setDeviceNo(deviceNo);
                hjDeviceProjectworkersService.deleteHjDeviceProjectworkersTwo(hd);
            }
            content.put("deleteemps",deleteemps);
        }
        json.put("content",content);
        System.out.println(json);
        updateConnectTime(deviceNo);
        return json;
    }

    public void  updateConnectTime(String deviceNo){
        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setDeviceNo(deviceNo);
        had.setConnectTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        hjAttendanceDeviceService.updateHjAttendanceDeviceTwo(had);
    }

    @PostMapping(value = "/feedBack")
    public void feedBack(@RequestBody String json){
        System.out.println(json);
    }

    private String getImg(String img)throws Exception{
        MultipartFile image = BASE64DecodedMultipartFile.base64ToMultipartOnt(BASE64DecodedMultipartFile.ImageToBase64ByOnline(img));
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }
//如果上传目录为/static/images/upload/,则可以如下获取
        File upload = new File(path.getAbsolutePath(), "static/upload/");

        if (!upload.exists()) {
            upload.mkdirs();
        }
        String getPath=upload.getAbsolutePath();
        String fileName2=getPath+"\\"+new Date().getTime()+img.substring(img.lastIndexOf("/")+1,img.lastIndexOf("."))+".jpg";
        File localFile = new File(fileName2);
        image.transferTo(localFile);
        System.gc();

        String base64Img=BASE64DecodedMultipartFile.ImageToBase64ByLocal(fileName2);
//        localFile.delete();.replaceAll("\r|\n", "");
        return base64Img;
    }
}
