package com.hujiang.project.lc.util;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.Tools;
import com.hujiang.project.zhgd.utils.ZCAPIClientTwo;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 对接大华工具类
 */
@Repository("lcUtil")
public  class LcUtil {
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;



    /**
     * 获取签名
     * @return
     */
    public    String  getSign(HjSynchronizationInformation hs,Long time,String nonce){
            StringBuilder string=new StringBuilder("time:"+time+",nonce:"+nonce+",appSecret:"+hs.getApiSecret());
                    return Tools.encodeToMD5(string.toString());
    }
    /**
     * 获取乐橙平台token
     * @return
     */
    public    String  getToken(HjSynchronizationInformation hs,Long time,String nonce)throws  URISyntaxException,IOException{
        Long times= new Date().getTime();
        Long expireTime=Long.valueOf(hs.getProjectNumber());
        //是否过期
        if(times>=expireTime){
            JSONObject json=new JSONObject();
            json.put("ver","1.0");
            json.put("sign",getSign(hs,time,nonce));
            json.put("appId",hs.getApiKey());
            json.put("time",time.toString());
            json.put("nonce",nonce);
            JSONObject d=new JSONObject();
            d.put("system",json);
            d.put("params",new JSONObject());
            d.put("id",nonce);

            String s=    ZCAPIClientTwo.httpPostWithJSON(Constants.OPEN_LC+"accessToken",d);
            JSONObject ss=JSONObject.parseObject(s);
            JSONObject result=ss.getJSONObject("result");
            JSONObject data=result.getJSONObject("data");
            Date date=new Date();
            date.setTime(date.getTime()+data.getInteger("expireTime")*1000);
            hs.setProjectNumber(String.valueOf(date.getTime()));
            hs.setClientSerial(data.getString("accessToken"));
            hjSynchronizationInformationService.updateHjSynchronizationInformation(hs);
            return data.getString("accessToken");
        }
            return hs.getClientSerial();

    }
}
