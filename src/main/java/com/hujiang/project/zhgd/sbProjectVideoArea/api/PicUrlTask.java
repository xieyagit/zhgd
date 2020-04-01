package com.hujiang.project.zhgd.sbProjectVideoArea.api;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.framework.AutoTaskBase;
import com.hujiang.project.lc.util.LcUtil;
import com.hujiang.project.ys.util.YsUtil;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.sbProjectVideo.domain.SbProjectVideo;
import com.hujiang.project.zhgd.sbProjectVideo.service.ISbProjectVideoService;
import com.hujiang.project.zhgd.sbProjectVideoArea.domain.VideoPicUrl;
import com.hujiang.project.zhgd.sbProjectVideoArea.service.ISbProjectVideoAreaService;
import com.hujiang.project.zhgd.utils.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@RestController
@RequestMapping(value = "/provider/abc")
public class PicUrlTask extends AutoTaskBase {
    @Autowired
    private ISbProjectVideoAreaService sbProjectVideoAreaService;
    @Resource
    private YsUtil ysUtil;
    @Autowired
    private ISbProjectVideoService sbProjectVideoService;
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    @Resource
    private LcUtil lcUtil;
    @Scheduled(cron="0 0 13 * * ?")
    public void task1() {
        super.exec(new Runnable() {
            @Override
            public void run() {
                try {
                    setVideoPicUrl();
                }
                catch (Exception e) {
                    // logger
                }
            }
        });
    }
    @PostMapping(value = "/abc")
    public void setVideoPicUrl() throws Exception{
        List<VideoPicUrl> vpList=sbProjectVideoAreaService.getVideoPicUrl();
        List<NameValuePair> params;
        for(int i=0;i<vpList.size();i++){
            String token =ysUtil.getAccessToken2(vpList.get(i).getPid());
//                =getToken(vpList.get(i).getPid());
                if(!"".equals(token)){
                List<SbProjectVideo> spList=vpList.get(i).getVideoList();
                for(int j=0;j<spList.size();j++) {
                    SbProjectVideo sp=spList.get(j);
                    String sn=sp.getVideoSn();
                    String channelNo=sp.getChannelNo();
                    String factory=sp.getFactory();

                    if(!"".equals(sn)&&sn!=null) {
                        if("HIK".equals(factory)) {
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("accessToken", token));
                            params.add(new BasicNameValuePair("deviceSerial", sn));
                            params.add(new BasicNameValuePair("channelNo", channelNo));
                            System.out.println(sn);
                            String result = ysUtil.httpPostWithJSON(Constants.OPEN_YS_LAPP + "lapp/device/capture", params);
                            JSONObject s = JSONObject.parseObject(result);
                            if ("200".equals(s.getString("code"))) {
                                JSONObject data = s.getJSONObject("data");
                                if (!"http://img.ys7.com/fail:null".equals(data.getString("picUrl"))) {
                                    String picUrl = sp.getPicUrl();
                                    if (!"".equals(picUrl) && picUrl != null) {
                                        AliyunOSSClientUtil.deleteFile(AliyunOSSClientUtil.getOSSClient(), "hujiang", picUrl.substring(picUrl.lastIndexOf("/") + 1));
                                    }

                                    MultipartFile file = BASE64DecodedMultipartFile.base64ToMultipartOnt(BASE64DecodedMultipartFile.ImageToBase64ByOnline(data.getString("picUrl")));
                                    String FileImgurl = AliyunOSSClientUtil.uploadFileImg(file, "hujiang", sn + System.currentTimeMillis() + ".jpg");
                                    String name = FileImgurl.substring(0, FileImgurl.lastIndexOf("?"));
                                    sp.setPicUrl(name);
                                    sbProjectVideoService.updateSbProjectVideoPicUrl(sp);
                                }
                            }
                        }

                    }
                }
            }}

    }
    public  String getToken(Integer pid) throws  Exception{
        String token="";
        token=ysUtil.getAccessToken2(pid);
        if("".equals(token)||token==null){
            HjSynchronizationInformation hs = new HjSynchronizationInformation();
            hs.setProjectId(pid);
            hs.setPlatformName("LCGETACCESSTOKEN");
            List<HjSynchronizationInformation> hsList = hjSynchronizationInformationService.selectHjSynchronizationInformationList(hs);
            if (hsList.size() > 0) {
                Long time = (long) (new Date().getTime() / 1000);
                token=lcUtil.getToken(hsList.get(0), time, Tools.encodeToMD5(time*1000+""));
            }
        }

        return token;
    }

}
