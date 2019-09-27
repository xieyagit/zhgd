package com.hujiang.project.zhgd.sbEquipmentRecord;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.sbDeviceimei.service.ISbDeviceimeiService;
import com.hujiang.project.zhgd.sbEquipmentRecord.domain.SbEquipmentRecord;
import com.hujiang.project.zhgd.sbEquipmentRecord.service.ISbEquipmentRecordService;
import com.hujiang.project.zhgd.utils.Constants;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/provider/locationTask",method = RequestMethod.POST)
//@Component()
//@EnableScheduling
public class locationTask {
    @Autowired
    private ISbDeviceimeiService deviceimeiService;
    @Autowired
    private ISbEquipmentRecordService equipmentRecordService;


    @PostMapping(value = "/add")
//    @Scheduled(cron="0 0/10 * * * ?")
    public void add() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -10);
        String startTime=format.format(beforeTime.getTime());
        String endTime=format.format(date.getTime());
//          String startTime="2019-08-05 14:30:00";
//          String endTime="2019-08-05 14:40:00";
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
        String status=originalData.getString("status");
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
}
