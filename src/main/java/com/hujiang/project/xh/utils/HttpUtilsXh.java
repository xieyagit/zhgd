package com.hujiang.project.xh.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpUtilsXh {
    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url1 发送请求的 URL
     * @param  authorization
     * @return 所代表远程资源的响应结果
     */
    public static String xhHttpGet(String url1, String authorization) throws URISyntaxException, IOException
    {
        CloseableHttpClient client;
        URL url = new URL(url1);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);

            HttpGet http= new HttpGet(uri);

        if(!"".equals(authorization)&&authorization!="") {

            http.addHeader("authorization", "Bearer " + authorization);
        }
        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。
//        StringEntity entity = new StringEntity(param,"UTF-8");
//        entity.setContentType("application/json");
//        http.setEntity(entity);
        HttpResponse resp = client.execute(http);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        // 如需要重发机制，可在此部分编写重发代码
        System.out.println("请求服务:" + url1);
        System.out.println(respContent);
        return respContent;
    }
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url1 发送请求的 URL
     * @param param 请求参数，请求参数应该是 JOSNObject.toString 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String xhHttpPost(String url1, String param,String authorization) throws URISyntaxException, IOException
    {
        CloseableHttpClient client;
        URL url = new URL(url1);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);

            HttpPost http=new HttpPost(uri);


        if(!"".equals(authorization)&&authorization!="") {

            http.addHeader("authorization", "Bearer " + authorization);
        }

        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。
        if(!"".equals(param)&&param!="") {
            StringEntity entity = new StringEntity(param, "UTF-8");
            entity.setContentType("application/json");
            http.setEntity(entity);
        }
        HttpResponse resp = client.execute(http);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        // 如需要重发机制，可在此部分编写重发代码
        System.out.println("请求服务:" + url1);
        System.out.println(respContent);
        return respContent;
    }
    /**
     * 向指定 URL 发送PUT方法的请求
     *
     * @param url1 发送请求的 URL
     * @param param 请求参数，请求参数应该是 JOSNObject.toString 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String xhHttpPut(String url1, String param,String authorization) throws URISyntaxException, IOException
    {
        CloseableHttpClient client;
        URL url = new URL(url1);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);

            HttpPut http=new HttpPut(uri);

        if(!"".equals(authorization)&&authorization!="") {

            http.addHeader("authorization", "Bearer " + authorization);
        }

        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。
        if(!"".equals(param)&&param!="") {
            StringEntity entity = new StringEntity(param, "UTF-8");
            entity.setContentType("application/json");
            http.setEntity(entity);
        }
        HttpResponse resp = client.execute(http);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        // 如需要重发机制，可在此部分编写重发代码
        System.out.println("请求服务:" + url1);
        System.out.println(respContent);
        return respContent;
    }
    /**
     * 向指定 URL 发送PATCH方法的请求
     *
     * @param url1 发送请求的 URL
     * @param param 请求参数，请求参数应该是 JOSNObject.toString 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String xhHttpPatch(String url1, String param,String authorization) throws URISyntaxException, IOException
    {
        CloseableHttpClient client;
        URL url = new URL(url1);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);

            HttpPatch http =new HttpPatch(uri);

        if(!"".equals(authorization)&&authorization!="") {

            http.addHeader("authorization", "Bearer " + authorization);
        }

        client = HttpClients.createDefault();
        String respContent = null;
        // 设置报文实体编码为UTF-8，否则会出现中文乱码以及无法正确调用服务。
        if(!"".equals(param)&&param!="") {
            StringEntity entity = new StringEntity(param, "UTF-8");
            entity.setContentType("application/json");
            http.setEntity(entity);
        }
        HttpResponse resp = client.execute(http);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        // 如需要重发机制，可在此部分编写重发代码
        System.out.println("请求服务:" + url1);
        System.out.println(respContent);
        return respContent;
    }
    /**
     * 向指定 URL 发送DELETE方法的请求
     *
     * @param url1 发送请求的 URL
     * @param authorization 请求参数，请求参数应该是 JOSNObject.toString 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String xhHttpDelete(String url1,String authorization) throws URISyntaxException, IOException
    {
        CloseableHttpClient client;
        URL url = new URL(url1);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);


            HttpDelete http=new HttpDelete(uri);

        if(!"".equals(authorization)&&authorization!="") {

            http.addHeader("authorization", "Bearer " + authorization);
        }

        client = HttpClients.createDefault();
        String respContent = null;
        HttpResponse resp = client.execute(http);
        if (resp.getStatusLine().getStatusCode() == 200) {
            HttpEntity rspEntity = resp.getEntity();
            respContent = EntityUtils.toString(rspEntity, "UTF-8");
        }
        client.close();
        // 如需要重发机制，可在此部分编写重发代码
        System.out.println("请求服务:" + url1);
        System.out.println(respContent);
        return respContent;
    }
}
