package com.hujiang.project.zhgd.utils;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.utils.Constants;
import com.hujiang.project.zhgd.utils.Tools;
import com.hujiang.project.zhgd.utils.Util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZCAPIClient {

    /**
     * 中车上报设备数据
     * @param url  请求地址
     * @param jsonObject  提交的数据
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static String  reportedData(String url, JSONObject jsonObject)throws URISyntaxException, IOException {
        //获取时间
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //拼接签名
        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append("api_key").append(jsonObject.getString("api_key"))
                .append("api_version").append(jsonObject.getString("api_version"))
                .append("body").append(jsonObject.getString("body"))
                .append("eng_code=").append(jsonObject.getString("eng_code"))
                .append("project_code=").append(jsonObject.getString("project_code"))
                .append("timestamp").append(simpleDateFormat);
        //转MD5 32位大写
        String serverSignature = Tools.encodeToMD5(str4MD5.toString()).toUpperCase();
        //设置签名
        jsonObject.put("signature",serverSignature);
        jsonObject.put("timestamp",simpleDateFormat);
        System.out.println(jsonObject);
        String s = Util.httpPostWithJSON(Constants.ZCAPI+url, jsonObject);
        System.out.println("请求服务："+url);
        return s;
    }


    /**
     * 中车上报设备数据
     * @param url  请求地址
     * @param jsonObject  提交的数据
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static String  reportedData2019(String url, JSONObject jsonObject)throws URISyntaxException, IOException {
        //获取时间
        String simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //拼接签名
        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append("api_key").append(jsonObject.getString("api_key"))
                .append("api_version").append(jsonObject.getString("api_version"))
                .append("body").append(jsonObject.getString("body"))
                .append("eng_code=").append(jsonObject.getString("eng_code"))
                .append("project_code=").append(jsonObject.getString("project_code"))
                .append("timestamp").append(simpleDateFormat);
        //转MD5 32位大写
        String serverSignature = Tools.encodeToMD5(str4MD5.toString()).toUpperCase();
        //设置签名
        jsonObject.put("signature",serverSignature);
        jsonObject.put("timestamp",simpleDateFormat);
        System.out.println(jsonObject);
        String s = Util.httpPostWithJSON(Constants.Version+url, jsonObject);
        System.out.println("请求服务："+url);
        return s;
    }

    /**
     * 上传至城安院获取项目id（市管项目）
     * */
    public static final String reportedCay2019(String url ,JSONObject jsonObject)throws URISyntaxException, IOException {
        String s = Util.httpPostWithJSON(Constants.CAY_CS+url+Constants.TOKEN_CS,jsonObject);
        System.out.println("请求服务："+Constants.CAY_CS+url+Constants.TOKEN_CS);
        JSONObject a=JSONObject.parseObject(s);
        JSONArray data = a.getJSONArray("res");
        JSONObject datas = data.getJSONObject(0);
        String k = datas.getString("xmid");
        return k;
    }

    /**
     * 上传至城安院获取项目监督编号和工程ID（市管项目）
     * */
    public static final JSONObject reportedCay(String url,JSONObject jsonObject)throws URISyntaxException, IOException {
        String s = Util.httpPostWithJSON(Constants.CAY_CS+url+Constants.TOKEN_CS,jsonObject);
        System.out.println("请求服务："+Constants.CAY_CS+url+Constants.TOKEN_CS);
        JSONObject a=JSONObject.parseObject(s);
        JSONArray data = a.getJSONArray("res");
        JSONObject datas = data.getJSONObject(0);
        String gcid = datas.getString("gcid");
        String jdbh = datas.getString("jdbh");
        String xmid = datas.getString("xmid");
        JSONObject object = new JSONObject();
        object.put("gcid",gcid);
        object.put("jdbh",jdbh);
        object.put("xmid",xmid);
        return object;
    }

    /**
     * 上传至城安院获取项目id&项目监督编号(区管项目)
     * */
    public static final JSONObject reportedCay2019s(String url,JSONObject jsonObject)throws URISyntaxException, IOException {
        String s = Util.httpPostWithJSON(Constants.CAY_QGXM+url+Constants.TOKEN_CS,jsonObject);
        System.out.println("请求服务："+Constants.CAY_QGXM+Constants.TOKEN_CS);
        JSONObject a=JSONObject.parseObject(s);
        JSONArray data = a.getJSONArray("res");
        JSONObject datas = data.getJSONObject(0);
        String xmid = datas.getString("xmid");
        String jdbh = datas.getString("jdbh");
        JSONObject object = new JSONObject();
        object.put("xmid",xmid);
        object.put("jdbh",jdbh);
        return object;
    }
    /**
     * 上传至城安院升降机基本信息(区管项目)
     * */
    public static final String QGXMCAY(String url,JSONObject jsonObject) throws IOException, URISyntaxException {
        String s = Util.httpPostWithJSON(Constants.CAY_QGXM+url+Constants.TOKEN_CS,jsonObject);
        return s;
    }

    /**
     * 上传至城安院升降机基本信息(市管项目)
     * */
    public static final String SGXMCAY(String url,JSONObject jsonObject) throws IOException, URISyntaxException {
        String s = Util.httpPostWithJSON(Constants.CAY_CS+url+Constants.TOKEN_CS,jsonObject);
        return s;
    }
}
