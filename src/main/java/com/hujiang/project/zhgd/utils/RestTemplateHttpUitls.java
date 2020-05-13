package com.hujiang.project.zhgd.utils;

import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.common.LoggerUitls;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @ClassName httpUitls
 * @Description TODO
 * @Author xieya
 * @Date 2020/4/1  16:58
 */
public class RestTemplateHttpUitls {

    private static final RestTemplate restTemplate = new RestTemplate();



    /**
     * @Author xieya
     * @Description
     * @Date 2020/4/2 12:44
     * @param url 请求url
     * @param request 入参对象
     * @param msg 接口名称
     * @return java.lang.String
     **/
    public static <T> String postForObject(String url, T request, String msg) {
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("utf-8")));

        LoggerUitls.logInfo(msg + "请求开始");
        Long startTime = System.currentTimeMillis();
        String result = restTemplate.postForObject(url, request, String.class);
        LoggerUitls.logInfo(msg + "接口返回数据", result);
        Long endTime = System.currentTimeMillis();
        LoggerUitls.logInfo(msg + "接口请求耗时" + (endTime - startTime) + "毫秒");
        LoggerUitls.logInfo(msg + "请求结束");

        return result;
    }

    /**
     * @Author xieya
     * @Description
     * @Date 2020/4/2 12:47
     * @param url 请求url
     * @param map 入参
     * @param msg 接口名称
     * @return java.lang.String
     **/
    private static String postForMap(String url, Map<String, Object> map, String msg) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        LoggerUitls.logInfo(msg + "请求开始");
        Long startTime = System.currentTimeMillis();
        String result = restTemplate.postForObject(url, map, String.class);
        LoggerUitls.logInfo(msg + "接口返回数据", result);
        Long endTime = System.currentTimeMillis();
        LoggerUitls.logInfo(msg + "接口请求耗时" + (endTime - startTime) + "毫秒");
        LoggerUitls.logInfo(msg + "请求结束");
        return result;
    }

    /**
     * @Author xieya
     * @Description
     * @Date 2020/4/2 13:07
     * @param url 请求url
     * @param json 入参
     * @param msg 接口名称
     * @return java.lang.String
     **/
    private static String postForJson(String url, JSONObject json, String msg) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        LoggerUitls.logInfo(msg + "请求开始");
        Long startTime = System.currentTimeMillis();
        String result = restTemplate.postForObject(url, json, String.class);
        LoggerUitls.logInfo(msg + "接口返回数据", result);
        Long endTime = System.currentTimeMillis();
        LoggerUitls.logInfo(msg + "接口请求耗时" + (endTime - startTime) + "毫秒");
        LoggerUitls.logInfo(msg + "请求结束");
        return result;
    }

    /**
     * @Author xieya
     * @Description
     * @Date 2020/5/9 18:06
     * @param url  列子 http://localhost:8080/test?strs={strs}
     * @param map  入参map   列子map.put("strs","hello");
     * @param msg
     * @return java.lang.String
     **/
    public static String getForObject(String url, Map map, String msg) {
        RestTemplate restTemplate = new RestTemplate();
        LoggerUitls.logInfo(msg + "请求开始");
        Long startTime = System.currentTimeMillis();
        String result = restTemplate.getForObject(url, String.class, map);
        LoggerUitls.logInfo(msg + "接口返回数据", result);
        Long endTime = System.currentTimeMillis();
        LoggerUitls.logInfo(msg + "接口请求耗时" + (endTime - startTime) + "毫秒");
        LoggerUitls.logInfo(msg + "请求结束");
        return result;
    }

    /**
     * @Author xieya
     * @Description 基坑请求接口  post
     * @Date 2020/5/9 21:03
     * @param url
     * @param msg
     * @return java.lang.String
     **/
    public static String postForJiKeng(String url, String msg) {

        String authorization = Util.EncodeBase64(ConstantByJiKeng.APP_ID + ":" + ConstantByJiKeng.APP_SECRET);

        String authorizations = authorization.replaceAll("\r|\n", "");
        HttpHeaders header = new HttpHeaders();
        header.add("Authorization", "Basic " + authorizations);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type","client_credentials");

        // 需求需要传参为form-data格式
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, header);
        LoggerUitls.logInfo(msg + "请求开始");
        Long startTime = System.currentTimeMillis();
        String result = restTemplate.postForObject(url, httpEntity, String.class);
        LoggerUitls.logInfo(msg + "接口返回数据", result);
        Long endTime = System.currentTimeMillis();
        LoggerUitls.logInfo(msg + "接口请求耗时" + (endTime - startTime) + "毫秒");
        LoggerUitls.logInfo(msg + "请求结束");
        System.out.println(result);

        return result;
    }

    /**
     * @Author xieya
     * @Description
     * @Date 2020/5/9 18:06
     * @param url  列子 http://localhost:8080/test?strs={strs}
     * @param map  入参map   列子map.put("strs","hello");
     * @param msg
     * @return java.lang.String
     **/
    public static String getForJiKeng(String url, Map map, String msg) {
        RestTemplate restTemplate = new RestTemplate();
        LoggerUitls.logInfo(msg + "请求开始", url, map, msg);
        Long startTime = System.currentTimeMillis();
        String result = restTemplate.getForObject(url, String.class, map);
        LoggerUitls.logInfo(msg + "接口返回数据", result);
        Long endTime = System.currentTimeMillis();
        LoggerUitls.logInfo(msg + "接口请求耗时" + (endTime - startTime) + "毫秒");
        LoggerUitls.logInfo(msg + "请求结束");
        return result;
    }

    public static void main(String[] args) {

        postForJiKeng("https://api.zhiwucloud.com/api/v1/oauth2/token", null);

//        Map map = new HashMap();
//        map.put("curpage", 1);
//        map.put("name", "银海蓝天华庭");
//
//        User request = new User();
//        request.setCurpage(1);
//        request.setName("银海蓝天华庭");
//        String result = postForObject("http://139.159.251.33/misInter/authorize/getProjInfos?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5", request, "haha");
//        String result1 = postForMap("http://139.159.251.33/misInter/authorize/getProjInfos?token=CF8116A785BAF1296C6A2C19E4A426E45E0A19C5", map, "haha");

    }

}