package com.hujiang.project.ys.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.Constants;
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
 * 对接海康工具类
 */
@Repository("ysUtil")
public  class YsUtil {
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;
    public   String  getAccessToken()throws  URISyntaxException,IOException{
        HjSynchronizationInformation hs=hjSynchronizationInformationService.selectHjSynchronizationInformationById(Constants.ACCESSTOKEN_ID);
        Long time= new Date().getTime();
        Long expireTime=Long.valueOf(hs.getProjectNumber());
        //是否过期
        if(time>=expireTime){
            //过期重新获取
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("appKey",hs.getApiKey()));
            params.add(new BasicNameValuePair("appSecret",hs.getApiSecret()));
           String result= httpPostWithJSON("https://open.ys7.com/api/lapp/token/get",params);
           JSONObject data=JSONObject.parseObject(result).getJSONObject("data");
            hs.setProjectNumber(data.getString("expireTime"));
            hs.setClientSerial(data.getString("accessToken"));
            hjSynchronizationInformationService.updateHjSynchronizationInformation(hs);
            return data.getString("accessToken");
        }else {
            return hs.getClientSerial();
        }
    }

    /**
     * 通信
     */
    public  String httpPostWithJSON(String urlString, List<NameValuePair> params) throws URISyntaxException, IOException {
        // 由于URL字符串当中存在空格等特殊字符，所以需要转为URI。
        CloseableHttpClient client;
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);
        HttpPost httpPost = new HttpPost(uri);
//        httpPost.addHeader("content-type","application/x-www-form-urlencoded;charset=utf-8");

        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。

        httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));

        HttpResponse resp = client.execute(httpPost);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        // 如需要重发机制，可在此部分编写重发代码
        System.out.println("请求服务:" + urlString );
        System.out.println(respContent);

        return respContent;
    }
}
