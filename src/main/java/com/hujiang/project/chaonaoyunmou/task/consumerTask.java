package com.hujiang.project.chaonaoyunmou.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.StringUtils;
import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.chaonaoyunmou.util.YunMouUtil;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.lyAttendanceRecord.domain.LyAttendanceRecord;
import com.hujiang.project.zhgd.lyAttendanceRecord.service.ILyAttendanceRecordService;
import com.hujiang.project.zhgd.lyPersonnel.domain.LyPersonnel;
import com.hujiang.project.zhgd.lyPersonnel.service.ILyPersonnelService;
import com.hujiang.project.zhgd.utils.Constants;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 * 获取云眸消费任务
 */
@Component
public class consumerTask extends AutoTaskBase {
    private Logger logger = Logger.getLogger(consumerTask.class.getName());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private YunMouUtil yunMouUtil;
    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @Autowired
    private ILyAttendanceRecordService lyAttendanceRecordService;
    @Autowired
            private ILyPersonnelService lyPersonnelService;

    String consumerId="";

    @Scheduled(cron="0/20 * * * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    addBB();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }

    public void addBB() throws  Exception{
        if(StringUtils.isBlank(consumerId)){
            consumerId=getConsumerId();
        }
        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("consumerId", consumerId));
        params1.add(new BasicNameValuePair("autoCommit", "true"));
        String result=yunMouUtil.httpPostWithJSONX(Constants.YUNMOU+"/api/v1/mq/consumer/messages",params1,yunMouUtil.getToken());
//    String result="{\"code\":200,\"message\":\"操作成功\",\"data\":[{\"msgId\":\"7f462d72ece1429fbdcb694512edd5c3\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:13:56.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191405479-E31767807-1-10000$encrypt=2,2020-04-23T19:13:56,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191406754-E31767807-1-10000$encrypt=2,2020-04-23T19:13:57,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.288,\\\"width\\\":0.126,\\\"x\\\":0.556,\\\"y\\\":0.22},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.7,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.7,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191406103-E31767807-0-10000$encrypt=2,2020-04-23T19:13:57,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.7}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"yes\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":37.2,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"31e05fa5d8de41d59b0605c6f53825f4\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:14:04.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191413480-E31767807-1-10000$encrypt=2,2020-04-23T19:14:04,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191414800-E31767807-1-10000$encrypt=2,2020-04-23T19:14:05,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.303,\\\"width\\\":0.133,\\\"x\\\":0.428,\\\"y\\\":0.231},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.74,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.74,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191414086-E31767807-0-10000$encrypt=2,2020-04-23T19:14:05,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.74}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"yes\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":37.1,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"a1dbe62a301f40f49ab70cf98ce6ecbb\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:14:10.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191419510-E31767807-1-10000$encrypt=2,2020-04-23T19:14:10,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191420691-E31767807-1-10000$encrypt=2,2020-04-23T19:14:11,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.25,\\\"width\\\":0.11,\\\"x\\\":0.506,\\\"y\\\":0.227},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.45,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.45,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191420092-E31767807-0-10000$encrypt=2,2020-04-23T19:14:11,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.45}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"yes\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":37.1,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"940c55535cf54cf7a8367c688ad95599\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:14:18.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191428125-E31767807-1-10000$encrypt=2,2020-04-23T19:14:19,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191429570-E31767807-1-10000$encrypt=2,2020-04-23T19:14:20,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.267,\\\"width\\\":0.116,\\\"x\\\":0.437,\\\"y\\\":0.223},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.53,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.53,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191428869-E31767807-0-10000$encrypt=2,2020-04-23T19:14:19,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.53}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"yes\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"yes\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":37.1,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"cdeff223174244f1b80675aa1bed33e0\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:14:24.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191434264-E31767807-1-10000$encrypt=2,2020-04-23T19:14:25,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191435503-E31767807-1-10000$encrypt=2,2020-04-23T19:14:26,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.269,\\\"width\\\":0.117,\\\"x\\\":0.291,\\\"y\\\":0.326},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.52,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.52,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191434910-E31767807-0-10000$encrypt=2,2020-04-23T19:14:25,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.52}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"yes\\\"},\\\"smile\\\":{\\\"value\\\":\\\"no\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"poker-faced\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":37.0,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"eb7d9c743cb14c84a61223efd0118fb8\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:16:19.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191628380-E31767807-1-10000$encrypt=2,2020-04-23T19:16:19,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191629748-E31767807-1-10000$encrypt=2,2020-04-23T19:16:20,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.185,\\\"width\\\":0.08,\\\"x\\\":0.589,\\\"y\\\":0.201},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.74,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.74,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191628990-E31767807-0-10000$encrypt=2,2020-04-23T19:16:19,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.74}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"yes\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":36.9,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"9f20df44d46445208fdeeab700b8895e\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:16:27.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191636461-E31767807-1-10000$encrypt=2,2020-04-23T19:16:27,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191638882-E31767807-1-10000$encrypt=2,2020-04-23T19:16:29,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.195,\\\"width\\\":0.084,\\\"x\\\":0.539,\\\"y\\\":0.171},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.93,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.93,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191638012-E31767807-0-10000$encrypt=2,2020-04-23T19:16:29,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.93}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"yes\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"no\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":36.9,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"3f38b6a473a1419ca8345f48d060a844\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:16:34.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191643615-E31767807-1-10000$encrypt=2,2020-04-23T19:16:34,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191644924-E31767807-1-10000$encrypt=2,2020-04-23T19:16:35,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.228,\\\"width\\\":0.101,\\\"x\\\":0.573,\\\"y\\\":0.204},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.83,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.83,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191644284-E31767807-0-10000$encrypt=2,2020-04-23T19:16:35,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.83}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"yes\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":36.9,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"cd48ec82cd3a4994afde342b8bf6f1bb\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:16:40.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191649597-E31767807-1-10000$encrypt=2,2020-04-23T19:16:40,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191650932-E31767807-1-10000$encrypt=2,2020-04-23T19:16:41,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.187,\\\"width\\\":0.08,\\\"x\\\":0.622,\\\"y\\\":0.197},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.66,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.66,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191650236-E31767807-0-10000$encrypt=2,2020-04-23T19:16:41,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.66}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"no\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":37.0,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"8962d6e8609a4d45a936588ac86f41a1\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:16:48.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191658170-E31767807-1-10000$encrypt=2,2020-04-23T19:16:49,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191659509-E31767807-1-10000$encrypt=2,2020-04-23T19:16:50,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.235,\\\"width\\\":0.103,\\\"x\\\":0.51,\\\"y\\\":0.26},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.36,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.36,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191658821-E31767807-0-10000$encrypt=2,2020-04-23T19:16:49,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.36}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"no\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":36.9,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"fd2fa6b3415a4c22bf5ecaef49714b89\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:16:54.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191704382-E31767807-1-10000$encrypt=2,2020-04-23T19:16:55,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191705664-E31767807-1-10000$encrypt=2,2020-04-23T19:16:56,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.175,\\\"width\\\":0.074,\\\"x\\\":0.545,\\\"y\\\":0.177},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.79,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.79,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191704955-E31767807-0-10000$encrypt=2,2020-04-23T19:16:55,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.79}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"no\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":36.8,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"d5715ddb90ef40ad8b2ebdd53517826c\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:17:03.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191712512-E31767807-1-10000$encrypt=2,2020-04-23T19:17:03,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191714029-E31767807-1-10000$encrypt=2,2020-04-23T19:17:05,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.252,\\\"width\\\":0.111,\\\"x\\\":0.417,\\\"y\\\":0.206},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.48,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.48,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191713145-E31767807-0-10000$encrypt=2,2020-04-23T19:17:04,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.48}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"yes\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":36.8,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"e5f2d66a190b46b2acc5c3a5bfc85e38\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:17:13.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191722811-E31767807-1-10000$encrypt=2,2020-04-23T19:17:13,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191724308-E31767807-1-10000$encrypt=2,2020-04-23T19:17:14,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.269,\\\"width\\\":0.096,\\\"x\\\":0.206,\\\"y\\\":0.387},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.33,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.33,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191723561-E31767807-0-10000$encrypt=2,2020-04-23T19:17:14,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.33}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"yes\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":36.8,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"},{\"msgId\":\"634dad67b9f845bfb9cd8175830a0ea6\",\"msgType\":\"open_event_super_brain_faces_comparison\",\"content\":\"[{\\\"deviceSerial\\\":\\\"E31767807\\\",\\\"channelNo\\\":1,\\\"targetAttrs\\\":{\\\"faceTime\\\":\\\"2020-04-23 19:17:18.000\\\",\\\"bkgUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191727893-E31767807-1-10000$encrypt=2,2020-04-23T19:17:18,91921185aa8f79a8eeb2ee8e24c35b21\\\"},\\\"faces\\\":[{\\\"url\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_1/20200423191729306-E31767807-1-10000$encrypt=2,2020-04-23T19:17:19,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"faceRect\\\":{\\\"height\\\":0.182,\\\"width\\\":0.079,\\\"x\\\":0.511,\\\"y\\\":0.263},\\\"identify\\\":[{\\\"maxsimilarity\\\":0.48,\\\"candidate\\\":[{\\\"reserveField\\\":{\\\"name\\\":\\\"吴茂智\\\",\\\"gender\\\":\\\"unknown\\\",\\\"bornTime\\\":\\\"\\\",\\\"city\\\":\\\"00\\\",\\\"certificateType\\\":\\\"unknown\\\",\\\"certificateNumber\\\":\\\"\\\",\\\"phoneNumber\\\":\\\"\\\"},\\\"similarity\\\":0.48,\\\"customFaceLibId\\\":\\\"huayuan\\\",\\\"employeeNo\\\":\\\"362532198903201339\\\",\\\"humanData\\\":[{\\\"facePicUrl\\\":\\\"https://open.ys7.com/api/lapp/mq/downloadurl?appKey=a7cb8b1d58ae42cc9b841fe13d9618e5&fileKey=ISAPI_FILES/E31767807_0/20200423191728648-E31767807-0-10000$encrypt=2,2020-04-23T19:17:19,91921185aa8f79a8eeb2ee8e24c35b21\\\",\\\"similarity\\\":0.48}]}]}],\\\"faceProperties\\\":{\\\"age\\\":{\\\"ageGroup\\\":\\\"young\\\"},\\\"gender\\\":{\\\"value\\\":\\\"male\\\"},\\\"glass\\\":{\\\"value\\\":\\\"no\\\"},\\\"smile\\\":{\\\"value\\\":\\\"yes\\\"},\\\"beard\\\":{\\\"value\\\":\\\"no\\\"},\\\"mask\\\":{\\\"value\\\":\\\"yes\\\"},\\\"faceExpression\\\":{\\\"value\\\":\\\"happy\\\"}},\\\"temperature\\\":{\\\"faceSnapThermometryEnabled\\\":true,\\\"currTemperature\\\":36.9,\\\"isAbnomalTemperature\\\":false,\\\"thermometryUnit\\\":\\\"celsius\\\"}}]}]\"}],\"success\":true}";
    JSONObject s=JSONObject.parseObject(result);
        if("200".equals(s.getString("code"))){
            JSONObject a;
            JSONArray list=s.getJSONArray("data");
            for(Object o : list  ){
                a  = JSONObject.parseObject(o.toString());
                if("open_event_super_brain_faces_comparison".equals(a.getString("msgType"))){
                    //超脑人脸比对消息类型
                    facesComparison(a.getString("content"));
                }else if("open_device_alarm".equals(a.getString("msgType"))){
                    //设备报警消息
                    deviceAlarm(a.getString("content"));
                }else if("open_message_tma".equals(a.getString("msgType"))){
                    //测温报警消息
                    messageTma(a.getString("content"));
                }

            }

        }
    }
    public void   messageTma(String s){
        JSONObject a=JSONObject.parseObject(s);
        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setDeviceNo(a.getString("deviceSerial"));
        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
        //有此设备才进行操作
        if(hadList.size()>0) {
            //一般设备只会有一个，所以只取第一条
            HjAttendanceDevice had2 = hadList.get(0);
            Integer pid = had2.getProjectId();
            LyAttendanceRecord lr=new LyAttendanceRecord();
            lr.setProjectId(pid);
            String time = a.getString("dateTime").replaceAll("T"," ");
            time=   time.substring(0,time.indexOf("+"));
            lr.setPassedTime(time);
            lr.setSitePhoto(a.getString("visibleLightURL"));
            lr.setDeviceType("chaonan_messageTma");
            lr.setDeviceSn(a.getString("deviceSerial"));
            lyAttendanceRecordService.insertLyAttendanceRecord(lr);
        }
    }
    /**
     * 设备报警消息
     * @param s
     */
    public void   deviceAlarm(String s){
        JSONObject a=JSONObject.parseObject(s);
        HjAttendanceDevice had=new HjAttendanceDevice();
        had.setDeviceNo(a.getString("devSerial"));
        List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
        //有此设备才进行操作
        if(hadList.size()>0) {
            //一般设备只会有一个，所以只取第一条
            HjAttendanceDevice had2 = hadList.get(0);
            Integer pid=had2.getProjectId();
            LyAttendanceRecord lr=new LyAttendanceRecord();
            lr.setProjectId(pid);
            lr.setPassedTime(a.getString("alarmTime").replaceAll("T"," "));
            JSONObject picture=(JSONObject) a.getJSONArray("pictureList").get(0);
            lr.setSitePhoto(picture.getString("url"));
            lr.setDeviceType("chaonan_alarm");
            lr.setDeviceSn(a.getString("devSerial"));
            lyAttendanceRecordService.insertLyAttendanceRecord(lr);
        }
    }
    /**
     * 超脑比对人脸消息
     * @param s
     * @throws Exception
     */
    public void facesComparison(String s)throws  Exception{
        JSONArray list=JSONArray.parseArray(s);
        JSONObject a;
        for(Object o : list){
            a  = JSONObject.parseObject(o.toString());
            HjAttendanceDevice had=new HjAttendanceDevice();
            had.setDeviceNo(a.getString("deviceSerial"));

            List<HjAttendanceDevice> hadList=hjAttendanceDeviceService.selectHjAttendanceDeviceList(had);
            //有此设备才进行操作
            if(hadList.size()>0) {
                //一般设备只会有一个，所以只取第一条
                HjAttendanceDevice had2=hadList.get(0);
                Integer pid=had2.getProjectId();
                String time = sdf.format(sdf.parse(a.getJSONObject("targetAttrs").getString("faceTime")));

                JSONArray faces=a.getJSONArray("faces");
                JSONObject a2;
                for(Object o2 : faces) {
                    a2  = JSONObject.parseObject(o2.toString());

                    JSONObject identify=(JSONObject) a2.getJSONArray("identify").get(0);
                    JSONObject candidate=(JSONObject) identify.getJSONArray("candidate").get(0);
                    String idCode=candidate.getString("employeeNo");
                    LyPersonnel lp=new LyPersonnel();
                    lp.setPid(pid);
                    lp.setIdCode(idCode);
                    List<LyPersonnel> lpList=lyPersonnelService.selectLyPersonnelList(lp);
                    if(lpList.size()>0){
                        LyAttendanceRecord lr=new LyAttendanceRecord();
                        lr.setProjectId(pid);
                        lr.setEmployeeId(lpList.get(0).getId());
                        lr.setPassedTime(time);
                        lr.setWay(1);
                        lr.setSitePhoto(a2.getString("url"));
                        lr.setDeviceType("chaonao");
                        lr.setDirection(had2.getDirection());
                        lr.setDeviceSn(had2.getDeviceNo());
                        lr.setTemperature(a2.getJSONObject("temperature").getString("currTemperature"));
                        lyAttendanceRecordService.insertLyAttendanceRecord(lr);
                    }

                }
            }
        }


    }

    public String getConsumerId()throws Exception{
        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("consumerName", "group1"));
        String result=yunMouUtil.httpPostWithJSONX(Constants.YUNMOU+"/api/v1/mq/consumer/group1",params1,yunMouUtil.getToken());
        JSONObject s=JSONObject.parseObject(result);
        if("200".equals(s.getString("code"))){
            JSONObject data=s.getJSONObject("data");
            return data.getString("consumerId");
        }
        logger.info("=====:"+s.getString("message"));
        return "";
    }
}
