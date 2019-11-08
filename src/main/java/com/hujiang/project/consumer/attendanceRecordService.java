package com.hujiang.project.consumer;


import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.JsonUtils;
import com.hujiang.common.utils.StringUtil;
import com.hujiang.framework.config.JmsConfig;
import com.hujiang.framework.jms.JmsMessageInfo;
import com.hujiang.project.zhgd.hjAttendanceRecord.domain.HjAttendanceRecord;
import com.hujiang.project.zhgd.hjAttendanceRecord.mapper.HjAttendanceRecordMapper;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.utils.APIClient;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import com.hujiang.common.utils.AliyunOSSClientUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class attendanceRecordService {

    @Resource
    APIClient apiClient;
    @Autowired
    private HjAttendanceRecordMapper hjAttendanceRecordMapper;
    // 使用JmsListener配置消费者监听的队列，其中name是接收到的消息
    @JmsListener(destination = JmsConfig.ATTENDANCE_RECORD)
    // SendTo 会将此方法返回的数据, 写入到 OutQueue 中去.
    @SendTo(JmsConfig.ATTENDANCE_RECORD + "_OUT") //双向队列
    public String handleMessage(String message)throws Exception {

        try {
            JmsMessageInfo info = JsonUtils.parse(message, JmsMessageInfo.class);

            String map = JsonUtils.convert(info.getBody(), String.class);
            JSONObject s=JSONObject.parseObject(map);
            HjProjectWorkers hjProjectWorkers=JSONObject.parseObject(s.getString("hjProjectWorkers"),HjProjectWorkers.class);
            HjAttendanceRecord hjAttendanceRecord=JSONObject.parseObject(s.getString("hjAttendanceRecord"),HjAttendanceRecord.class);
            String d=hjAttendanceRecord.getDirection();
            String imgbase=s.getString("imgBase");
            MultipartFile file=  BASE64DecodedMultipartFile.base64ToMultipartOnt(imgbase);
            boolean re = apiClient.uploadPassedLogTest(hjProjectWorkers,d,imgbase);
            String folder = AliyunOSSClientUtil.createFolder(AliyunOSSClientUtil.getOSSClient(), "hujiang", d.trim()+"/");  // 文件夹名称
            String filename= hjProjectWorkers.getId()+new Date().getTime()+".jpg";
            String fileUrl = AliyunOSSClientUtil.uploadFileImg(file, folder,filename);
            String nameUel = fileUrl.substring(0,fileUrl.lastIndexOf("?"));

            hjAttendanceRecord.setCreateDate(new Date());  // 保存到考勤记录
            hjAttendanceRecord.setWay(1);
            hjAttendanceRecord.setPassedTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            hjAttendanceRecord.setEmployeeId(hjProjectWorkers.getId());
            hjAttendanceRecord.setSitePhoto(nameUel);
            if(re) {
                hjAttendanceRecord.setUploadTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
            int i1 = hjAttendanceRecordMapper.insertHjAttendanceRecord(hjAttendanceRecord);

                System.out.println(hjProjectWorkers.getEmpName()+":考勤消息队列执行完毕");

        }
        catch(IOException e) {
            e.printStackTrace();
            return message;
        }
        return null;
    }
}
