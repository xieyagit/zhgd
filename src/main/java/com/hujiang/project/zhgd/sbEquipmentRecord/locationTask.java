package com.hujiang.project.zhgd.sbEquipmentRecord;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.zhgd.moduleToPush.domain.ModuleToPush;
import com.hujiang.project.zhgd.moduleToPush.service.IModuleToPushService;
import com.hujiang.project.zhgd.sbDeviceimei.service.ISbDeviceimeiService;
import com.hujiang.project.zhgd.sbEquipmentRecord.domain.SbEquipmentRecord;
import com.hujiang.project.zhgd.sbEquipmentRecord.service.ISbEquipmentRecordService;
import com.hujiang.project.zhgd.sbEquipmentWarning.domain.SbEquipmentWarning;
import com.hujiang.project.zhgd.sbEquipmentWarning.service.ISbEquipmentWarningService;
import com.hujiang.project.zhgd.sbHire.domain.SbAreaProject;
import com.hujiang.project.zhgd.sbHire.mapper.SbHireMapper;
import com.hujiang.project.zhgd.sbProjectDustEmission.task.JPushSMS;
import com.hujiang.project.zhgd.utils.Constants;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/provider/locationTask",method = RequestMethod.POST)
@Component()
//@EnableScheduling
public class locationTask extends AutoTaskBase {
    @Autowired
    private ISbDeviceimeiService deviceimeiService;
    @Autowired
    private ISbEquipmentRecordService equipmentRecordService;
    @Autowired
    private ISbEquipmentWarningService equipmentWarningService;

    @Autowired
    private SbEquipmentWarning sbEquipmentWarning;
    @Autowired
    private IModuleToPushService moduleToPushService;
    @Autowired
    private SbHireMapper sbHireMapper;
    @Resource
    private JPushSMS jPushSMS;

    @Scheduled(cron="0 0/10 * * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    add();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }
    @Scheduled(cron="0 */5 * * * ?")
    public void task2() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    addWarning();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }

//    @PostMapping(value = "/add")
    public void add() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -10);
        String startTime=format.format(beforeTime.getTime());
        String endTime=format.format(date.getTime());
        List<String> deviceList =deviceimeiService.selectDeviceimeiListAll(); //所有设备编号imei
        String jsonData = Constants.LOCALTIONDATA+"?starttime="+startTime+"&endtime="+endTime;
        CloseableHttpClient client;
        URL url = new URL(jsonData);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);
        HttpPost httpPost = new HttpPost(uri);
        client = HttpClients.createDefault();
        String respContent = null;
        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        String jsonParam = respContent;
        //获取的原始数据转换成json对象
        JSONObject originalData = JSONObject.parseObject(jsonParam);
        String status=originalData.getString("state");
        //是否成功
        if("true".equals(status)){
            JSONArray list=JSONArray.parseArray(originalData.getString("data"));
            for(Object jo : list) {
                JSONObject a = JSONObject.parseObject(jo.toString());
                a.remove("id");
                String imei = a.getString("imei");
                if (deviceList.contains(imei)) {
                    //转换成实体类数据
                    SbEquipmentRecord equipmentRecord = JSONObject.parseObject(a.toJSONString(), SbEquipmentRecord.class);
                    //保存定位数据
                    equipmentRecordService.insertSbEquipmentRecord(equipmentRecord);

                }
            }
        }
    }

    @PostMapping(value = "/addWarning")
    public void addWarning() throws Exception {
        SbEquipmentWarning waring = equipmentWarningService.selectSbEquipmentWarning();
        ModuleToPush moduleToPush = new ModuleToPush();
        moduleToPush.setPrivilegesId(3);
        List<ModuleToPush> moduleToPushes = moduleToPushService.getModuleToPushList(moduleToPush);
        List<String> deviceList =deviceimeiService.selectDeviceimeiListAll(); //所有设备编号imei
        String jsonData = Constants.LOCALTIONDATAWARNING+"?starttime="+waring.getWarningTime();
        CloseableHttpClient client;
        URL url = new URL(jsonData);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);
        HttpPost httpPost = new HttpPost(uri);
        client = HttpClients.createDefault();
        String respContent = null;
        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        String jsonParam = respContent;
        //获取的原始数据转换成json对象
        JSONObject originalData = JSONObject.parseObject(jsonParam);
        String status=originalData.getString("state");
        //是否成功
        if("true".equals(status)){
            List<SbAreaProject> areaProjectList = sbHireMapper.selectAreaProjectList();
            JSONArray list=JSONArray.parseArray(originalData.getString("data"));
            for(Object jo : list) {
                JSONObject data = JSONObject.parseObject(jo.toString());
                data.remove("id");
                String imei = data.getString("imei");
                if (deviceList.contains(imei)) {
                    //转换成实体类数据
                    List<SbEquipmentWarning> equipmentWarningList = new ArrayList<>();
                    List<SbAreaProject> areaProjects = areaProjectList.stream()
                            .filter(c -> c.getImei().equals(imei))
                            .collect(Collectors.toList());
                    SbEquipmentWarning equipmentWarning = JSONObject.parseObject(data.toJSONString(), SbEquipmentWarning.class);
                    equipmentWarning.setProjectId(areaProjects.get(0).getProjectId());
                    //保存定位数据
                    equipmentWarningList.add(equipmentWarning);
                    equipmentWarningService.insertSbEquipmentWarning(equipmentWarningList);

                    List<ModuleToPush> pushes = moduleToPushes.stream()
                            .filter(s -> s.getPrivilegesId().equals(3) && (s.getOnOff().equals(1) || s.getFall().equals(1) ||
                                    s.getMove().equals(1) || s.getBat().equals(1)))
                            .collect(Collectors.toList());

                    if(pushes.stream().filter(a->a.getUserId().equals(areaProjects.get(0).getAdminId())).findFirst().isPresent()) {
                        ModuleToPush pushItem = pushes.stream().filter(a -> a.getUserId().equals(areaProjects.get(0).getAdminId())).findFirst().get();
                        sbEquipmentWarning.setImei(equipmentWarning.getImei());
                        sbEquipmentWarning.setWarningType(equipmentWarning.getWarningType());
                        sbEquipmentWarning.setWarningTime(equipmentWarning.getWarningTime());
                        sbEquipmentWarning.setUserName(areaProjects.get(0).getUserName());
                        sbEquipmentWarning.setAreaName(areaProjects.get(0).getAreaName());
                        sbEquipmentWarning.setUserPhone(areaProjects.get(0).getUserPhone()!=null ? areaProjects.get(0).getUserPhone():"0");
                        sbEquipmentWarning.setAdminId(pushItem.getUserId());
                        jPushSMS.JPushAndJSMS(sbEquipmentWarning,areaProjects.get(0).getProjectId());
                    }




                }
            }
        }
    }
}
