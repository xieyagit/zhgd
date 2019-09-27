package com.hujiang.project.zhgd.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hujiang.project.zhgd.hjProjectWorkers.domain.HjProjectWorkers;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ZCAPIClientTwo {
    public static void main(String[] args) throws Exception {
        //添加企业信息-- AddCompany
        //移除企业信息-- ProjectRemoveCompany
        //添加班组-- AddTeam
        //上报实名制信息-- RegisterEmployee
        //人员离场-- userLeaveProject
        //考勤记录-- UploadPassedLog
        String a="http://szwb.sz.gov.cn:2019/CWRService/UploadPassedLog";
        JSONObject body=new JSONObject();
//        JSONObject body2=new JSONObject();
//        JSONArray ja=new JSONArray();
////
        body.put("Project_ID","44030120170223017");
        //============参建单位
//        body.put("Company_Name","深圳广田集团股份有限公司");
//        body.put("Type","001");
//        body.put("SUID","91440300192359041F");
//        body.put("Legal_Person","陈海军");
//        body.put("entry_time","2019-08-21 00:00:00");
//        body.put("exit_time","2019-08-21 00:00:00");
//===============班组
//        body.put("team_name","管理人员");
//        body.put("team_type_order","006");
//        body.put("team_type_name","普工班");
//        body.put("emp_company","深圳广田集团股份有限公司");
//        body.put("SUID","91440300192359041F");//461574dffadd4c6d87c39cb76d13fc9c
        //================人员
//        body.put("id_code","320382196511198358");
//        body.put("id_photo","c01891682dd14e5eb79f7cdccf3fb512");
//        body.put("emp_name","徐振刚");
//        body.put("emp_phone","15852359505");
//        body.put("emp_nativeplace","江苏省邳州市八路镇祠堂村徐庙82号");
//        body.put("emp_nation","汉");
//        body.put("pass_period","2019-07-30 00:00:00");
//        body.put("match_flag","Y");
//        body.put("facephoto","857a9ab5350c41f292236efa4cfc3ec0");
//        body.put("emp_company","深圳广田集团股份有限公司");
//        body.put("SUID","91440300192359041F");
//        body.put("team_id","461574dffadd4c6d87c39cb76d13fc9c");
//        body.put("team_name","管理人员");
//        body.put("emp_category","00");
//        body.put("job_order","38");
//        body.put("job_name","杂工");
//        body.put("contract_status","1");
//        body.put("id_agency","邳州市公安局");
        //=================离场
//        JSONObject body2=new JSONObject();
//        JSONArray ja=new JSONArray();
//        body2.put("id_code","320382196511198358");
//        body2.put("exit_time","2019-04-22 00:00:00");
//        ja.add(body2);
//        body.put("userLeaveProject_list",ja);
        //===============考勤
        JSONObject body2=new JSONObject();
        JSONArray ja=new JSONArray();
        body.put("Device_ID",Tools.encodeToMD5s("1310355"));

        body2.put("data_id","0318d6797b794066a4724cd33d8252d3"+Tools.encodeToMD5s("445221199205216551"));
        body2.put("person_id","320382196511198358");
        body2.put("person_name","黄才鑫");
        body2.put("passed_time",new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        body2.put("direction","out");
        body2.put("way","1");
        body2.put("site_photo",ZCgetImageId.getImageId("0318d6797b794066a4724cd33d8252d3","http://gd.17hr.net:8018/Ephotoes/20190719/20190719004016652-19070028.JPEG"));
        ja.add(body2);
        body.put("passedlog_list",ja);
        //==============

//        body.put("Device_ID",Tools.encodeToMD5s("1310355"));
//
//        body2.put("data_id","0318d6797b794066a4724cd33d8252d3"+Tools.encodeToMD5s("445221199205216551"));
//        body2.put("person_id","445221199205216551");
//        body2.put("person_name","黄才鑫");
//        body2.put("passed_time",new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        body2.put("direction","out");
//        body2.put("way","1");
//        body2.put("site_photo",ZCgetImageId.getImageId("0318d6797b794066a4724cd33d8252d3","http://gd.17hr.net:8018/Ephotoes/20190719/20190719004016652-19070028.JPEG"));
//        ja.add(body2);
//        body.put("passedlog_list",ja);
//        body2.put("id_code","320382196511198358");
//        body2.put("exit_time","2019-03-28 00:00:00");
//        ja.add(body2);
//        body.put("userLeaveProject_list",ja);
//        System.out.println(body.toJSONString());




        //===============================
//        String tag="007-05";
//        String[] q=tag.split("-");
//        System.out.println(q[0]);

        //=============
//        System.out.println(ZCgetImageId.getImageId("45aca55f3d1449dfb8b0765b5fa31a69","http://gd.17hr.net:8018/Resources/RsEmpPhoto/306/306_320382196511198358_1.jpg"));
        //====================
//        String a="ss普工一班";
//        String b="普工";
//        a=a.replace("班","");
//        a=a.substring(a.lastIndexOf("-")+1);
//        System.out.println(a);
//        System.out.println(a.indexOf(b));
//        //==========================
//        String a=Constants.ZC_FORMALHOST+"AddTeam";
//        JSONObject body=new JSONObject();
//        body.put("Project_ID","XM2016048402");
//
//        body.put("team_name","中建八局-管理人员2");
//        body.put("team_type_order","018");
//        body.put("team_type_name","信号指挥班");
//        body.put("emp_company","中国建筑第八工程局有限公司");
//        body.put("SUID","9131000063126503X1");
//        System.out.println(body.toString());
//
        String s=getUrl("0caed82cf71c4362a39a8b3f6d2894f3","f83e172a79d94ec09b9d166506d70307","1.0","f83e172a79d94ec09b9d166506d70307",body.toString(),a);

        httpPostWithJSON(s,body);
//        //==============================================
//        String s="{\"result\":\"true\",\"code\":\"00\",\"status\":\"200\",\"message\":\"成功\",\"result_data\":{\"dict_list\":[{\"orders\":\"waterproof\",\"value\":\"防水班\"},{\"orders\":\"CONCRETE\",\"value\":\"混凝土班\"},{\"orders\":\"FITTER\",\"value\":\"钢筋班\"},{\"orders\":\"CARPENTRY\",\"value\":\"木工班\"},{\"orders\":\"001\",\"value\":\"装饰装修班\"},{\"orders\":\"002\",\"value\":\"机械设备班\"},{\"orders\":\"003\",\"value\":\"架子班\"},{\"orders\":\"004\",\"value\":\"外架租赁班\"},{\"orders\":\"005\",\"value\":\"铝模班\"},{\"orders\":\"006\",\"value\":\"普工班\"},{\"orders\":\"007\",\"value\":\"铝合金班\"},{\"orders\":\"008\",\"value\":\"古建筑装饰班\"},{\"orders\":\"009\",\"value\":\"园林绿化班\"},{\"orders\":\"010\",\"value\":\"地基基础班\"},{\"orders\":\"011\",\"value\":\"石方爆破班\"},{\"orders\":\"012\",\"value\":\"防水/防腐/保温班\"},{\"orders\":\"013\",\"value\":\"砌筑/抹灰班\"},{\"orders\":\"014\",\"value\":\"普工/零工班\"},{\"orders\":\"015\",\"value\":\"安保后勤队\"},{\"orders\":\"016\",\"value\":\"路面施工班\"},{\"orders\":\"017\",\"value\":\"大型设备司机班\"},{\"orders\":\"018\",\"value\":\"信号指挥班\"},{\"orders\":\"019\",\"value\":\"搬运工/杂工班\"},{\"orders\":\"020\",\"value\":\"幕墙安装班\"},{\"orders\":\"021\",\"value\":\"消防/管道班\"},{\"orders\":\"022\",\"value\":\"水暖/电气班\"},{\"orders\":\"023\",\"value\":\"边坡支护施工班\"},{\"orders\":\"024\",\"value\":\"吊装/起重班\"},{\"orders\":\"025\",\"value\":\"测量/测绘班\"},{\"orders\":\"026\",\"value\":\"脚手架班\"},{\"orders\":\"INFRASTRUCTURE\",\"value\":\"桩基础施工班\"},{\"orders\":\"EXPLOSIVE\",\"value\":\"爆破班\"},{\"orders\":\"PAINT\",\"value\":\"油漆班\"},{\"orders\":\"BRICKLAYER\",\"value\":\"瓦工班\"},{\"orders\":\"TEMPLATE\",\"value\":\"模板班\"},{\"orders\":\"ELECTRIC_WELDING\",\"value\":\"焊工班\"},{\"orders\":\"ELECTRIC\",\"value\":\"电工班\"}]}}";
//        s=s.replace("\\","");
//        System.out.println(s);
        
    }

    /**
     * 获取url
     * @param api_secret
     * @param api_Key
     * @param api_Version
     * @param client_Serial
     * @param jsonParam
     * @param server
     * @return
     */
    public static String getUrl(String api_secret, String api_Key,
                                String api_Version, String client_Serial, String jsonParam,
                                String server) {
        String timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        StringBuilder str4MD5 = new StringBuilder();
        str4MD5.append(api_secret).append("api_key").append(api_Key)
                .append("api_version").append(api_Version).append("body")
                .append(jsonParam).append("client_serial")
                .append(client_Serial).append("timestamp").append(timestamp)
                .append(api_secret);
//        System.out.println(str4MD5);
        // MD5加密报文获取signature
//        System.out.println(str4MD5.toString());
        String serverSignature = Tools.encodeToMD5s(str4MD5.toString());
        StringBuilder url = new StringBuilder();
        url.append(server).append("?api_key=").append(api_Key)
                .append("&api_version=").append(api_Version)
                .append("&client_serial=").append(client_Serial)
                .append("&timestamp=").append(timestamp)
                .append("&signature=").append(serverSignature);
        return url.toString();

    }

    /**
     * post报文客户端
     * @param urlString
     * @param jsonParam
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static String httpPostWithJSON(String urlString, JSONObject jsonParam) throws URISyntaxException, IOException {
        // 由于URL字符串当中存在空格等特殊字符，所以需要转为URI。
        CloseableHttpClient client;
        URL url = new URL(urlString);
        URI uri = new URI(url.getProtocol(), url.getHost() + ":"
                + url.getPort(), url.getPath(), url.getQuery(), null);
        org.apache.http.client.methods.HttpPost httpPost = new HttpPost(uri);
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
}
