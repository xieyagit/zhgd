package com.hujiang.project.hs.hsFaceAPi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ha.sdk.HaCamera;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkers;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.domain.HjDeviceProjectworkersParam;
import com.hujiang.project.zhgd.hjDeviceProjectworkers.service.IHjDeviceProjectworkersService;
import com.hujiang.project.zhgd.hjLogging.domain.HjLogging;
import com.hujiang.project.zhgd.hjLogging.service.IHjLoggingService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/provider/hs")
public class HsFaceApi {
    @Autowired
    private IHjDeviceProjectworkersService hjDeviceProjectworkersService;
    @Autowired
    private IHjLoggingService hjLoggingService;
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    /**
     * 初始化sdk
     */
    @PostConstruct
    public void init(){ HaCamera.init();
    }

    /**
     * 对艾信达人脸机下发指令
     * @param json
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/person")
    public String  addPerson(@RequestBody String json)throws Exception{
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"应答："+json);
        JSONObject a=JSONObject.parseObject(json);
        String reply=a.getString("reply");
        updateConnectTime(a.getString("device_sn"));
        int i=0;
        //正常心跳
        if("".equals(reply)||reply==null) {
        //返回指令
String aa=getJson(a);
//            System.out.println(aa);
            return aa;
             }else{
            //设备应答，非心跳
            //操作成功的应答
            if("0".equals(a.getString("code"))){
                //收到添加人员成功的应答，修改指令库状态
                if("add person".equals(a.getString("cmd"))){
                    HjDeviceProjectworkers  hd=new HjDeviceProjectworkers();
                    hd.setStatus("1");
                    hd.setDeviceNo(a.getString("device_sn"));
                    hd.setProjectWorkersId(a.getInteger("id"));
                    hjDeviceProjectworkersService.updateHjDeviceProjectworkersTwo(hd);
                }
                //收到删除人员成功的应答，删除指令库指令
                if("delete person(s)".equals(a.getString("cmd"))){
                    HjDeviceProjectworkers    hd=new HjDeviceProjectworkers();
                    hd.setProjectWorkersId(a.getInteger("id"));
                    hd.setDeviceNo(a.getString("device_sn"));
                    hjDeviceProjectworkersService.deleteHjDeviceProjectworkersTwo(hd);
                }
                return null;

            }

        }
        if("delete person(s)".equals(a.getString("cmd"))&&"3".equals(a.getString("code"))){
            hjDeviceProjectworkersService.deleteHjDeviceProjectworkersByIds(a.getString("command_id"));
        }
          return null;
    }
    private void  updateConnectTime(String deviceNo){
        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setDeviceNo(deviceNo);
        had.setConnectTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        hjAttendanceDeviceService.updateHjAttendanceDeviceTwo(had);
    }
    private  String getJson(JSONObject a) {
//        System.out.println("i==========:"+(i++));
        String version = a.getString("version");
        String sn = a.getString("device_sn");
        Map<String, String> param = new HashMap<String, String>();
        param.put("deviceNo", sn);
//        param.put("status", "1");
        List<HjDeviceProjectworkersParam> hList1 = hjDeviceProjectworkersService.selectHjProjectworkersListThree(param);
        if (hList1.size() > 0) {
            HjDeviceProjectworkersParam hdp = hList1.get(0);
            JSONObject add = new JSONObject();
            //指令是0就添加
            if ("0".equals(hdp.getStatus())) {
                Object[] a1 = HaCamera.twistImage(BASE64DecodedMultipartFile.ImageToByteArray(hdp.getFaceUrl()));

                if(a1.length==3) {
                    System.out.println(hdp.getEmpName());
                    BASE64Encoder encode = new BASE64Encoder();

                    add.put("version", version);
                    add.put("cmd", "add person");
                    add.put("id", hdp.getPid().toString());
                    add.put("name", hdp.getEmpName());
                    add.put("role", 1);
                    add.put("feature_unit_size", 0);
                    add.put("feature_num", 0);
                    add.put("feature_data", new JSONArray());
                    add.put("image_num", 1);
                    JSONArray ja = new JSONArray();
                    JSONObject c = new JSONObject();
                    c.put("format", "jpg");
                    c.put("image_data", encode.encode((byte[]) a1[1]).replaceAll("\r|\n", ""));
                    ja.add(c);
                    add.put("reg_images", ja);
                    add.put("wg_card_id", hdp.getPid());
                    add.put("term", "forever");
//                add.put("command_id", hdp.getDid());
                    add.put("norm_image_num", 1);
                    JSONArray ja2 = new JSONArray();
                    JSONObject c2 = new JSONObject();
                    c2.put("width", 150);
                    c2.put("height", 150);
                    c2.put("image_data", encode.encode((byte[]) a1[2]).replaceAll("\r|\n", ""));
                    ja2.add(c2);
                    add.put("norm_images", ja2);
                    add.put("term_start", "useless");

                }else{
                    HjDeviceProjectworkers h=new HjDeviceProjectworkers();
                    h.setId(hdp.getDid());
                    h.setStatus("3");
                    hjDeviceProjectworkersService.updateHjDeviceProjectworkers(h);
                    HjProjectWorkers hpw=hjProjectWorkersService.selectHjProjectWorkersById(hdp.getPid());
                    HjLogging hl=new HjLogging();
                    hl.setProjectId(hpw.getProjectId());
                    hl.setLoggingMessage("人脸照片不符合要求！");
                    hl.setLoggingData(a.toJSONString());
                    hl.setInOut("向人脸机下发人脸失败！");
                    hl.setUserName(hpw.getEmpName());
                    hl.setLoggingTag("RecordDevice");
                    hl.setLoggingTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    hjLoggingService.insertHjLogging(hl);
                    return getJson(a);
                }
            } else {
                //指令是2就删除
                add.put("version", version);
                add.put("cmd", "delete person(s)");
                add.put("flag", -1);
                add.put("id", hdp.getPid());
                add.put("command_id", hdp.getDid().toString());
            }
            return add.toString();
        }
        return null;
    }
}
