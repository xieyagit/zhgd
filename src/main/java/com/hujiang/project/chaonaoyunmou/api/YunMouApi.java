package com.hujiang.project.chaonaoyunmou.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.chaonaoyunmou.util.YunMouUtil;
import com.hujiang.project.zhgd.hjAttendanceDevice.domain.HjAttendanceDevice;
import com.hujiang.project.zhgd.hjAttendanceDevice.service.IHjAttendanceDeviceService;
import com.hujiang.project.zhgd.hjAttendanceRecord.service.IHjAttendanceRecordService;
import com.hujiang.project.zhgd.hjProject.domain.HjProject;
import com.hujiang.project.zhgd.hjProject.service.IHjProjectService;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import com.hujiang.project.zhgd.hjProjectWorkers.service.IHjProjectWorkersService;
import com.hujiang.project.zhgd.sbProjectVideo.domain.SbProjectVideo;
import com.hujiang.project.zhgd.sbProjectVideo.service.ISbProjectVideoService;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.SbProjectVideoArea;
import com.hujiang.project.zhgd.sbProjectVideoArea.service.ISbProjectVideoAreaService;
import com.hujiang.project.zhgd.utils.BASE64DecodedMultipartFile;
import com.hujiang.project.zhgd.utils.Constants;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/ymUtil")
public class YunMouApi {
    @Resource
    private YunMouUtil yunMouUtil;
    @Autowired
    private IHjAttendanceDeviceService hjAttendanceDeviceService;
    @Autowired
    private ISbProjectVideoAreaService sbProjectVideoAreaService;
    @Autowired
    private IHjProjectService hjProjectService;
    @Autowired
    private ISbProjectVideoService sbProjectVideoService;
    @PostMapping("/addGroup")
    public void  addGroup()throws Exception{
        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("groupName", "华远北京市"));
//        params.add(new BasicNameValuePair("groupNo", "huayuan"));
//        JSONObject params=new JSONObject();
        String url="/v2/groups/add?groupName=华远北京市&groupNo=huayuan";
//        params.put("groupName","华远北京市");
//        params.put("groupNo","huayuan");
        String result = yunMouUtil.httpPostWithJSONX(Constants.YUNMOU+url, params,yunMouUtil.getToken());
    }

    /**
     * 添加设备，前提是已经创建项目
     * @param pid
     * @param sn
     * @param validateCode
     * @throws Exception
     */
    @PostMapping("addDevice")
    public void  addDevice(Integer pid,String sn,String validateCode)throws Exception{
        String token=yunMouUtil.getToken();
        //注册设备
        List<NameValuePair> params1 = new ArrayList<NameValuePair>();
        params1.add(new BasicNameValuePair("groupId", "e6ceeb5731d44a9086c2a58105862315"));
        params1.add(new BasicNameValuePair("deviceSerial", sn));
        params1.add(new BasicNameValuePair("validateCode", validateCode));
        String result=yunMouUtil.httpPostWithJSONX(Constants.YUNMOU+"/v1/devices/add",params1,yunMouUtil.getToken());
        JSONObject s1=JSONObject.parseObject(result);
        if("200".equals(s1.getString("code"))){
            JSONObject data=s1.getJSONObject("data");
            String deviceId=data.getString("deviceId");
            HjAttendanceDevice hd=new HjAttendanceDevice();
            hd.setDeviceNo(sn);
            hd.setDeviceName(data.getString("deviceName"));
            hd.setDeviceFactory("cn");
            hd.setStatus("1");
            hd.setRemark(data.getString("deviceId"));
            hd.setProjectId(pid);
            hd.setSystemType("ly");
            hjAttendanceDeviceService.insertHjAttendanceDevice(hd);
            //关闭设备视频加密
            List<NameValuePair> params6 = new ArrayList<NameValuePair>();
            params6.add(new BasicNameValuePair("deviceId", deviceId));
            params6.add(new BasicNameValuePair("validateCode", validateCode));
            yunMouUtil.httpPostWithJSONX(Constants.YUNMOU+"/v1/devices/"+deviceId+"/encrypt/off",params6,token);
            //注册人脸库
           JSONObject params2=new JSONObject();
           params2.put("deviceId",data.getString("deviceId"));
           JSONArray params3=new JSONArray();
           JSONObject params4=new JSONObject();
           params4.put("index",1);
           params4.put("faceLibName","华远电气");
           params4.put("customFaceLibId","huayuan");
           params4.put("thresholdValue",80);
           params3.add(params4);
           params2.put("faceLibs",params3);
           String result2=yunMouUtil.httpPostWithJSONH(Constants.YUNMOU+"/api/v1/community/superBrains/faceLibs",params2,yunMouUtil.getToken());
           //
            HjProject h=hjProjectService.selectHjProjectById(pid);
            SbProjectVideoArea spa=new SbProjectVideoArea();
            spa.setProjectid(pid);
            spa.setAreaName(h.getShortName());
            sbProjectVideoAreaService.insertSbProjectVideoArea(spa);
            //同步设备通道
            List<NameValuePair> params5 = new ArrayList<NameValuePair>();
            params5.add(new BasicNameValuePair("deviceId", deviceId));
            yunMouUtil.httpPostWithJSONX(Constants.YUNMOU+"/v1/devices/"+deviceId+"/synchChannels",params5,token);
            //查询设备通道
            String url="v1/channels/list?deviceId="+deviceId+"&pageNo=1&pageSize=999";
            String result3=yunMouUtil.httpGetWithJSON(Constants.YUNMOU+url,token);
            JSONObject s3=JSONObject.parseObject(result3);
            if("200".equals(s3.getString("code")));
            JSONArray list=s3.getJSONObject("data").getJSONArray("rows");
            JSONObject a;
            for(Object o : list){
                a  = JSONObject.parseObject(o.toString());
                String channelId=a.getString("channelId");
                //开通直播
                List<NameValuePair> params7 = new ArrayList<NameValuePair>();
                params7.add(new BasicNameValuePair("channelIds", channelId));
                yunMouUtil.httpPostWithJSONX(Constants.YUNMOU+"/v1/devices/liveVideoOpen",params7,token);
                //获取直播地址
                String url2="/v1/devices/liveAddress?channelId="+channelId;
                String result4=yunMouUtil.httpGetWithJSON(Constants.YUNMOU+url2,token);
                JSONObject s4=JSONObject.parseObject(result4);
                if("200".equals(s4.getString("code"))){
                    //保存直播地址
                    JSONObject data2=s4.getJSONObject("data");
                    SbProjectVideo sv=new SbProjectVideo();
                    sv.setAreaId(spa.getId());
                    sv.setUrl(data2.getString("rtmp"));
                    sv.setHdurl(data2.getString("rtmpHd"));
                    sv.setIsControl("0");
                    sv.setVideoName(h.getProjectName()+data2.getInteger("channelNo"));
                    sv.setChannelNo(data2.getString("channelNo"));
                    sv.setVideoSn(data2.getString("deviceSerial"));
                    sv.setFactory("HIK");
                    sbProjectVideoService.insertSbProjectVideo(sv);
                }

            }
        }
    }
    @Autowired
    private IHjProjectWorkersService hjProjectWorkersService;
    @PostMapping("addPerson")
    public void  addPerson(Integer id)throws Exception{
        HjProjectWorkers hp=hjProjectWorkersService.selectHjProjectWorkersById(18203296);
        JSONObject json=new JSONObject();
        json.put("employeeNo","430923199605237217");
        json.put("personName","谌超球");
        JSONObject json1=new JSONObject();
        json1.put("faceName","谌超球");
        json1.put("faceData", BASE64DecodedMultipartFile.ImageToBase64ByOnline(hp.getFaceUrl()).replaceAll("\r|\n", ""));
        json.put("face",json1);
        String r=yunMouUtil.httpPostWithJSONH(Constants.YUNMOU+"/api/v1/community/superBrains/persons",json,yunMouUtil.getToken());
    }
}
