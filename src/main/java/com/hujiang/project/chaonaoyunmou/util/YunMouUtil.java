package com.hujiang.project.chaonaoyunmou.util;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.common.utils.StringUtils;
import com.hujiang.project.zhgd.hjSynchronizationInformation.domain.HjSynchronizationInformation;
import com.hujiang.project.zhgd.hjSynchronizationInformation.service.IHjSynchronizationInformationService;
import com.hujiang.project.zhgd.utils.Constants;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 海康云眸工具类
 */
@Repository("ymUtil")
public class YunMouUtil {
    @Autowired
    private IHjSynchronizationInformationService hjSynchronizationInformationService;


    public String  getToken()throws Exception{


            HjSynchronizationInformation hs = hjSynchronizationInformationService.selectHjSynchronizationInformationById(Constants.YUNMOUTOKEN_ID);
            if(StringUtils.isBlank(hs.getProjectNumber())){
                return  getAccessToken(hs);
            }
            Long times= new Date().getTime();
            Long expireTime=Long.valueOf(hs.getProjectNumber());
            //是否过期
            if(times>=expireTime){
                return  getAccessToken(hs);
            }
            return hs.getClientSerial();

    }

    public String getAccessToken(HjSynchronizationInformation hs) throws Exception{
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("client_id", hs.getApiKey()));
        params.add(new BasicNameValuePair("client_secret", hs.getApiSecret()));
        params.add(new BasicNameValuePair("grant_type", "client_credentials"));
        String result = httpPostWithJSON("https://api2.hik-cloud.com/oauth/token", params);
        JSONObject data = JSONObject.parseObject(result);
        String token=data.getString("access_token");
        hs.setProjectNumber(String.valueOf(new Date().getTime()+data.getLong("expires_in")*1000));
        hs.setClientSerial(token);
        hjSynchronizationInformationService.updateHjSynchronizationInformation(hs);
    return token;
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
    /**
     * 通信
     */
    public  String httpPostWithJSONX(String urlString, List<NameValuePair> params,String token) throws URISyntaxException, IOException {
        // 由于URL字符串当中存在空格等特殊字符，所以需要转为URI。
        CloseableHttpClient client;
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);
        HttpPost httpPost = new HttpPost(uri);
//        httpPost.addHeader("content-type","application/x-www-form-urlencoded;charset=utf-8");
        httpPost.addHeader("Authorization","bearer "+token);
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
    public  String httpPostWithJSONH(String urlString, JSONObject jsonParam,String token) throws URISyntaxException, IOException {
        // 由于URL字符串当中存在空格等特殊字符，所以需要转为URI。
        CloseableHttpClient client;
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("Authorization","bearer "+token);
        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。
        StringEntity entity = new StringEntity(jsonParam.toString(),
                "UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
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
    public  String httpGetWithJSON(String urlString,String token) throws URISyntaxException, IOException {
        // 由于URL字符串当中存在空格等特殊字符，所以需要转为URI。
        CloseableHttpClient client;
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);
//        HttpPost httpPost = new HttpPost(uri);
        HttpGet httpGet=new HttpGet(uri);
//        httpPost.addHeader("content-type","application/x-www-form-urlencoded;charset=utf-8");
        httpGet.addHeader("Authorization","bearer "+token);
        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。

//        httpGet.setEntity(new UrlEncodedFormEntity(params,"utf-8"));

        HttpResponse resp = client.execute(httpGet);
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
    public  String httpDeleteWithJSON(String urlString,String token) throws URISyntaxException, IOException {
        // 由于URL字符串当中存在空格等特殊字符，所以需要转为URI。
        CloseableHttpClient client;
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);
//        HttpPost httpPost = new HttpPost(uri);
        HttpDelete httpDelete=new HttpDelete(uri);
//        httpPost.addHeader("content-type","application/x-www-form-urlencoded;charset=utf-8");
        httpDelete.addHeader("Authorization","bearer "+token);
        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。

//        httpGet.setEntity(new UrlEncodedFormEntity(params,"utf-8"));

        HttpResponse resp = client.execute(httpDelete);
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
