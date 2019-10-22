package com.hujiang.project.consumer;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.HttpUtils;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.common.utils.Md5Utils;
import com.hujiang.framework.config.JmsConfig;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.framework.jms.JmsMessageType;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.sbDustEmission.domain.SbDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.domain.SbProjectDustEmission;
import com.hujiang.project.zhgd.sbProjectDustEmission.service.ISbProjectDustEmissionService;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TspCayService {

    @Autowired
    private ISbProjectDustEmissionService projectDustEmissionService;


    //log日志
    private static Logger logger = LoggerFactory.getLogger(TspCayService.class);

    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = JmsConfig.TSP_CAY_QUEUE)
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo(JmsConfig.TSP_CAY_QUEUE + "_OUT") //双向队列
    public String handleMessage(String message) {

        try {
            JmsMessageInfo info = JsonUtils.parse(message, JmsMessageInfo.class);
            if (JmsMessageType.Machine.equals(info.getType())) {
                uploadMachine(info);
            }

            if (JmsMessageType.Data.equals(info.getType())) {
                uploadData(info);
            }
        }
        catch(IOException e) {
            return message;
        } catch (Exception e) {
            return message;
        }
        return null;
    }

    /**上传扬尘数据*/
    private void uploadData(JmsMessageInfo info) throws IOException{
        ArrayList list = JsonUtils.convert(info.getBody(), ArrayList.class);
        JSONArray array = new JSONArray();
        for ( Object item : list) {
            SbDustEmission emission = JsonUtils.convert(item, SbDustEmission.class);
            if (!emission.equals("CAY")){
                logger.error("该项目不属于城安院监管项目！");
                return;
            }
            JSONObject object = new JSONObject();
            object.put("HJ_PGUID",emission.getXmid());//所属项目编号
            object.put("Jdbh", emission.getJdbh());//项目监督编号
            object.put("HJ_DGUID",Md5Utils.hash(emission.getSn()));
            object.put("HJ_ZS",emission.getNoise());
            object.put("HJ_PM10",emission.getPm10());
            object.put("HJ_PM25",emission.getPm25());
            object.put("HJ_FS",emission.getWindSpeed());
            object.put("HJ_FX",emission.getWinddirection());
            object.put("HJ_WD",emission.getTemperature());
            object.put("HJ_SD",emission.getHumidity());
            object.put("tsp",emission.getTsp());
            if (emission.getSubId()==null){
                array.add(object);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("PList",array);
                HttpResponse response = HttpUtils.doPost("http://139.159.197.174/misInter",
                        "/hj/sync_hj_data?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                        "POST", null, null,
                        JsonUtils.toJson(jsonObject));
                System.out.println(response);
            } else {
                object.put("sub_id", emission.getSubId());
                array.add(object);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("PList",array);
                HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter","/hj/sync_hj_data?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5","POST", null, null,JsonUtils.toJson(jsonObject));
                System.out.println(response);
            }
        }
    }

    /**上报设备**/
    private void uploadMachine(JmsMessageInfo info) throws IOException {
        SbProjectDustEmission body = JsonUtils.convert(info.getBody(), SbProjectDustEmission.class);
        body.setSn( Md5Utils.hash(body.getSn()) );
        if (body.getSubId()==null || body.getXmid() == null){
            logger.error("确实必传参数");
        }
        JSONObject data = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        data.put("PROJECT_ID", body.getXmid());//所属项目编号
        data.put("Jdbh", body.getJdbh());//项目监督编号
        data.put("DEV_GUID",body.getSn());//设备编号

        data.put("MD_TYPE","环境");//设备类型（“环境”固定死）
        if (body.getSubId() == null ){
            //区管
            data.put("MD_NAME",body.getComments());//设备名称
            array.add(data);
            object.put("PList",array);
            HttpResponse response = HttpUtils.doPost("http://139.159.197.174/misInter/",
                    "hj/sync_hj?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                    "POST", null, null,
                    JsonUtils.toJson(object));
            System.out.println(response);
        }else{
            //市管
            SbProjectDustEmission dustEmission = new SbProjectDustEmission();
            dustEmission.setProjectId(body.getProjectId());
            List<SbProjectDustEmission> emissions = projectDustEmissionService.selectProjectDustEmissionListData(dustEmission);
            data.put("MD_NAME",(emissions.size()+1)+"#检测点");//设备名称（命名规则：阿 拉伯数字#监测点；示例： 1#监测点，2#监测点，同 个项目下数字不能重复）
            data.put("sub_id", body.getSubId());//工程ID
            array.add(data);
            object.put("PList",array);
            HttpResponse response = HttpUtils.doPost("http://139.159.186.240/misInter/",
                    "hj/sync_hj?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5",
                    "POST", null, null,
                    JsonUtils.toJson(object));
            System.out.println(response);
        }
    }

}
