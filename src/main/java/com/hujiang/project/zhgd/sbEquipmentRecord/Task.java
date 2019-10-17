package com.hujiang.project.zhgd.sbEquipmentRecord;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.AutoTaskBase;
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



//@RestController
//@RequestMapping(value = "/provider/task",method = RequestMethod.POST)
@Component("task")
//@EnableScheduling
public class Task extends AutoTaskBase {
    @Autowired
    private ISbDeviceimeiService deviceimeiService;
    @Autowired
    private ISbEquipmentRecordService equipmentRecordService;


    @Scheduled(cron="0 0/10 * * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    insert();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }
//    @PostMapping(value = "/insert")
    public void insert() throws Exception{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String endTime=format.format(date.getTime());
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -10);// 10分钟之前的时间
        String startTime=format.format(beforeTime.getTime());
        List<String> imeiList =deviceimeiService.selectDeviceimeiListAll(); //所有imei（设备编号）
        String jsonParam = Constants.LOCALTION+"?starttime="+startTime+"&endtime="+endTime;
        CloseableHttpClient client;
        URL url = new URL(jsonParam);
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
        String s=respContent;
        //获取的原始数据转换成json对象
        JSONObject originalData = JSONObject.parseObject(s);
        String status=originalData.getString("status");
        //是否成功
        if("true".equals(status)){
            String data=originalData.getString("data");
            JSONArray list=JSONArray.parseArray(data);
            JSONObject  a;
            String watchDate;
            String createDate;
            String imei;
            for(Object jo : list) {
                a = JSONObject.parseObject(jo.toString());
                imei = a.getString("imei");
                if (imeiList.contains(imei)) {
                    //将yyyy-MM-dd hh-mm-ss 格式转换成yyyy-MM-dd hh:mm:ss
                    watchDate = a.getString("watchDate");
                    watchDate = watchDate.substring(0, watchDate.lastIndexOf(' ')) + (watchDate.substring(watchDate.lastIndexOf(' ')).replace('-', ':'));
                    createDate = a.getString("createDate");
                    createDate = createDate.substring(0, createDate.lastIndexOf(' ')) + (createDate.substring(createDate.lastIndexOf(' ')).replace('-', ':'));
                    a.put("watchDate", watchDate);
                    a.put("createDate", createDate);
                    //转换成实体类数据
                    SbEquipmentRecord equipmentRecord = JSONObject.parseObject(a.toJSONString(), SbEquipmentRecord.class);
                    //保存定位数据
                   equipmentRecordService.insertSbEquipmentRecord(equipmentRecord);

                }
            }
        }
    }
}
